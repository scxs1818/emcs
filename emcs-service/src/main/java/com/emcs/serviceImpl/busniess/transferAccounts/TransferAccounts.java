package com.emcs.serviceImpl.busniess.transferAccounts;

import com.emcs.Constant.BusiConstant;
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
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)throw new BusiException("交易平台不存在或者处于异常状态","600003");

        insertCmAcctTranSeq.process(param);
        if(BusiConstant.ROLE_CUST.equals(param.get("buyer_type"))){
            if(BusiConstant.ROLE_CUST.equals(param.get("seller_type"))){
                oneDML.updateVaCustVirtualAcctBalAdd(param);
                oneDML.updateVaCustVirtualAcctBalSub(param);

            }else if(BusiConstant.ROLE_MERCH.equals(param.get("seller_type"))){
                oneDML.updateVaCustVirtualAcctBalSub(param);
                oneDML.updateVaMerchVirtualAcctBalAdd(param);

            }else{
                throw new BusiException("角色类型错误","600009");
            }
        }else if(BusiConstant.ROLE_MERCH.equals(param.get("buyer_type"))){
            if(BusiConstant.ROLE_CUST.equals(param.get("seller_type"))){
                oneDML.updateVaMerchVirtualAcctBalSub(param);
                oneDML.updateVaCustVirtualAcctBalAdd(param);

            }else if(BusiConstant.ROLE_MERCH.equals(param.get("seller_type"))){
                oneDML.updateVaMerchVirtualAcctBalSub(param);
                oneDML.updateVaMerchVirtualAcctBalAdd(param);

            }else{
                throw new BusiException("角色类型错误","600009");
            }
        }else{
            throw new BusiException("角色类型错误","600009");
        }
        updateCmAcctTranSeq.process(param);
    }
}