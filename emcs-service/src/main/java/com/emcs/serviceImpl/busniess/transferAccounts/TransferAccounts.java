package com.emcs.serviceImpl.busniess.transferAccounts;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.Super.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import com.emcs.serviceImpl.busniess.common.InsertCmAcctTranSeq;
import com.emcs.serviceImpl.busniess.common.UpdateCmAcctTranSeq;
import com.emcs.serviceImpl.busniess.recharge.CustRecharge;
import com.emcs.serviceImpl.busniess.recharge.MerchRecharge;
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
    @Override
    protected void process(Map<String, Object> param) {
        //2.数据库级校验
        param.put("status","N");//正常
        param.put("acct_status","N");
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)throw new BusiException(PlatErrorCode.VAP001.code(),PlatErrorCode.VAP001.val());

        if(BusiConstant.ROLE_CUST.equals(param.get("payer_type"))){
            param.put("vir_acct_type","301");
            param.put("cust_virid",param.get("payer_id"));
            //对付款方上锁
            oneSelect.selectCustVirtualAcctBalLock(param);
            //扣减付款方余额
            oneDML.updateVaCustVirtualAcctBalAdd(param);
            if(BusiConstant.ROLE_CUST.equals(param.get("payee_type"))){
                param.put("cust_virid",param.get("payee_id"));
                //对收款方上锁
                oneSelect.selectCustVirtualAcctBalLock(param);
                //增加收款方余额
                oneDML.updateVaCustVirtualAcctBalSub(param);

            }else if(BusiConstant.ROLE_MERCH.equals(param.get("payee_type"))){
                param.put("vir_acct_type","201");
                param.put("merch_virid",param.get("payee_id"));
                //对收款方上锁
                oneSelect.selectVaMerchVirtualAcctBalLock(param);
                //增加收款方余额
                oneDML.updateVaMerchVirtualAcctBalAdd(param);

            }else{
                throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
            }
        }else if(BusiConstant.ROLE_MERCH.equals(param.get("payer_type"))){
            param.put("vir_acct_type","201");
            param.put("merch_virid",param.get("payer_id"));
            oneSelect.selectVaMerchVirtualAcctBalLock(param);
            oneDML.updateVaMerchVirtualAcctBalSub(param);
            if(BusiConstant.ROLE_CUST.equals(param.get("payee_type"))){
                param.put("vir_acct_type","301");
                param.put("cust_virid",param.get("payee_id"));
                oneSelect.selectCustVirtualAcctBalLock(param);
                oneDML.updateVaCustVirtualAcctBalAdd(param);

            }else if(BusiConstant.ROLE_MERCH.equals(param.get("payee_type"))){
                param.put("merch_virid",param.get("payee_id"));
                oneSelect.selectVaMerchVirtualAcctBalLock(param);
                oneDML.updateVaMerchVirtualAcctBalAdd(param);
            }else{
                throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
            }
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
        }
    }
}