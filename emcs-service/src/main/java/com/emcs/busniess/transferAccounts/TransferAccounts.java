package com.emcs.busniess.transferAccounts;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.supers.InServiceY;
import com.emcs.busniess.common.InsertCmAcctTranSeq;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.busniess.common.UpdateCmAcctTranSeq;
import com.emcs.busniess.recharge.CustRecharge;
import com.emcs.busniess.recharge.MerchRecharge;
import com.emcs.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public class TransferAccounts extends InServiceY {
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
    protected void process(Map<String, Object> data) {
        //2.数据库级校验
        data.put("status","N");//正常
        data.put("acct_status","N");
        if(oneSelect.selectIsExistVaPlatInfo(data)==0)throw new BusiException(PlatErrorCode.VAP001.code(),PlatErrorCode.VAP001.val());
        data.put("tran_type",BusiConstant.TranType.TRANSFER_CUST_TO_CUST.val());
        limitValidate.validateRepeat(data);
        if(BusiConstant.ROLE_CUST.equals(data.get("payer_type"))){
            data.put("cust_id",data.get("payer_id"));
            if(BusiConstant.ROLE_CUST.equals(data.get("payee_type"))){
                data.put("tran_type",BusiConstant.TranType.TRANSFER_CUST_TO_CUST.val());
                limitValidate.validatePayer(data);
                data.put("cust_id",data.get("payee_id"));
                limitValidate.validatePayee(data);
            }else if(BusiConstant.ROLE_MERCH.equals(data.get("payee_type"))){
                data.put("tran_type",BusiConstant.TranType.TRANSFER_CUST_TO_MERCH.val());
                limitValidate.validatePayer(data);
                data.put("merch_id",data.get("payee_id"));
                limitValidate.validatePayee(data);
            }
        }else if(BusiConstant.ROLE_MERCH.equals(data.get("payer_type"))){
            data.put("merch_id",data.get("payer_id"));
            if(BusiConstant.ROLE_CUST.equals(data.get("payee_type"))){
                data.put("tran_type",BusiConstant.TranType.TRANSFER_MERCH_TO_CUST.val());
                limitValidate.validatePayer(data);
                data.put("cust_id",data.get("payee_id"));
                limitValidate.validatePayee(data);
            }else if(BusiConstant.ROLE_MERCH.equals(data.get("payee_type"))){
                data.put("tran_type",BusiConstant.TranType.TRANSFER_MERCH_TO_MERCH.val());
                limitValidate.validatePayer(data);
                data.put("merch_id",data.get("payee_id"));
                limitValidate.validatePayee(data);
            }
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
        }

        Map<String,Object> payerInfo = (Map<String,Object>)data.get("payerInfo");
        Map<String,Object> payeeInfo = (Map<String,Object>)data.get("payeeInfo");

        //增减余额信息处理
        BigDecimal usable_bal = (BigDecimal)payerInfo.get("usable_bal");
        BigDecimal recharge_bal = (BigDecimal)payerInfo.get("recharge_bal");
        BigDecimal tran_amt = new BigDecimal(data.get("tran_amt")+"");
        if(usable_bal.compareTo(tran_amt)==-1){
            data.put("usable_bal",usable_bal);
            data.put("recharge_bal",tran_amt.subtract(usable_bal));
        }else{
            data.put("usable_bal",tran_amt);
            data.put("recharge_bal",0);
        }

        if(BusiConstant.ROLE_CUST.equals(data.get("payer_type"))){
            data.put("vir_acct_type","301");
            data.put("payer_virid",payerInfo.get("cust_virid"));
            oneDML.updateVaCustVirtualAcctBalSub(data);
            if(BusiConstant.ROLE_CUST.equals(data.get("payee_type"))){
                data.put("payee_virid",payeeInfo.get("cust_virid"));
                //增加收款方余额
                oneDML.updateVaCustVirtualAcctBalAdd(data);

            }else if(BusiConstant.ROLE_MERCH.equals(data.get("payee_type"))){
                data.put("vir_acct_type","201");
                data.put("payee_virid",payeeInfo.get("merch_virid"));
                //增加收款方余额
                oneDML.updateVaMerchVirtualAcctBalAdd(data);
            }
        }else if(BusiConstant.ROLE_MERCH.equals(data.get("payer_type"))){
            data.put("vir_acct_type","201");
            data.put("payer_virid",payerInfo.get("merch_virid"));
            oneDML.updateVaMerchVirtualAcctBalSub(data);
            if(BusiConstant.ROLE_CUST.equals(data.get("payee_type"))){
                data.put("vir_acct_type","301");
                data.put("payee_virid",payeeInfo.get("cust_virid"));
                oneDML.updateVaCustVirtualAcctBalAdd(data);

            }else if(BusiConstant.ROLE_MERCH.equals(data.get("payee_type"))){
                data.put("payee_virid",payeeInfo.get("merch_virid"));
                oneDML.updateVaMerchVirtualAcctBalAdd(data);
            }
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
        }
        data.put("pay_type","1");//???
        data.put("transfer_seq_no",oneSelect.getNextVal(BusiConstant.Quence.TRANSFER.val()));
        oneDML.insertVaTransferSeq(data);
    }
}