package com.emcs.busniess.transferAccounts;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.busniess.common.InsertCmAcctTranSeq;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.busniess.common.UpdateCmAcctTranSeq;
import com.emcs.busniess.recharge.CustRecharge;
import com.emcs.busniess.recharge.MerchRecharge;
import com.emcs.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public class TransferAccounts extends ServiceTransactionalY{
    @Autowired
    InsertCmAcctTranSeq insertCmAcctTranSeq;
    @Autowired
    UpdateCmAcctTranSeq updateCmAcctTranSeq;
    @Autowired
    CustRecharge custRecharge;
    @Autowired
    MerchRecharge merchRecharge;
    @Autowired
    LimitValidate limitValidate;
    @Override
    protected void process(Map<String, Object> param) {
        //2.数据库级校验
        param.put("status","N");//正常
        param.put("acct_status","N");
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)throw new BusiException(PlatErrorCode.VAP001.code(),PlatErrorCode.VAP001.val());

        //限制性校验
        limitValidate.process(param);

        Map<String,Object> payerInfo = (Map<String,Object>)param.get("payerInfo");
        Map<String,Object> payeeInfo = (Map<String,Object>)param.get("payeeInfo");


        if(BusiConstant.ROLE_CUST.equals(param.get("payer_type"))){
            param.put("vir_acct_type","301");
            param.put("payer_virid",payerInfo.get("cust_virid"));

            //扣减付款方余额
            oneDML.updateVaCustVirtualAcctBalAdd(param);
            if(BusiConstant.ROLE_CUST.equals(param.get("payee_type"))){
                param.put("tran_type",BusiConstant.TranType.TRANSFER_CUST_TO_CUST);
                param.put("payee_virid",payeeInfo.get("cust_virid"));
                //增加收款方余额
                oneDML.updateVaCustVirtualAcctBalSub(param);

            }else if(BusiConstant.ROLE_MERCH.equals(param.get("payee_type"))){
                param.put("tran_type",BusiConstant.TranType.TRANSFER_CUST_TO_MERCH);
                param.put("vir_acct_type","201");
                param.put("payee_virid",payeeInfo.get("merch_virid"));
                //增加收款方余额
                oneDML.updateVaMerchVirtualAcctBalAdd(param);
            }else{
                throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
            }
        }else if(BusiConstant.ROLE_MERCH.equals(param.get("payer_type"))){
            param.put("vir_acct_type","201");
            param.put("payer_virid",payerInfo.get("merch_virid"));
            oneDML.updateVaMerchVirtualAcctBalSub(param);
            if(BusiConstant.ROLE_CUST.equals(param.get("payee_type"))){
                param.put("tran_type",BusiConstant.TranType.TRANSFER_MERCH_TO_CUST);
                param.put("vir_acct_type","301");
                param.put("payee_virid",payeeInfo.get("cust_virid"));
                oneDML.updateVaCustVirtualAcctBalAdd(param);

            }else if(BusiConstant.ROLE_MERCH.equals(param.get("payee_type"))){
                param.put("tran_type",BusiConstant.TranType.TRANSFER_MERCH_TO_MERCH);
                param.put("payee_virid",payeeInfo.get("merch_virid"));
                oneDML.updateVaMerchVirtualAcctBalAdd(param);
            }else{
                throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
            }
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
        }
        param.put("pay_type","1");//???
        param.put("transfer_seq_no",oneSelect.getNextVal(BusiConstant.Quence.TRANSFER.val()));
        oneDML.insertVaTransferSeq(param);
    }
}