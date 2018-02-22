package com.emcs.busniess.order;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.supers.InServiceY;
import com.emcs.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class PurchaseApply extends InServiceY {
    @Autowired
    LimitValidate validate;
    @Autowired
    CustPurchaseApply custPurchase;
    @Autowired
    MerchPurchaseApply merchPurchase;

    @Override
    protected void process(Map<String, Object> data) {
        //初始化会员和会员账户状态
        data.put("status","N");
        data.put("acct_status","N");
        data.put("pay_type","0");

        //校验平台
        if(oneSelect.selectIsExistVaPlatInfo(data)==0)
            throw new BusiException(ErrorCodeConstant.PlatErrorCode.VAP001.code(), ErrorCodeConstant.PlatErrorCode.VAP001.val());

        if(Integer.parseInt(oneSelect.selectVaOrderInfoForRepeat(data)+"")>0)throw new BusiException(ErrorCodeConstant.PubErrorCode.VAZ020.code(), ErrorCodeConstant.PubErrorCode.VAZ020.val());

        if(BusiConstant.ROLE_CUST.equals(data.get("role_type"))){
            data.put("tran_type", BusiConstant.TranType.CUST_PURCHASE_APPLY.val());

            data.put("payer_type",BusiConstant.ROLE_CUST);
            data.put("cust_id",data.get("payer_id"));
            validate.validatePayer(data);

            data.put("payee_type",BusiConstant.ROLE_MERCH);
            data.put("merch_id",data.get("payee_id"));
            validate.validatePayee(data);

            custPurchase.process(data);
        }else if(BusiConstant.ROLE_MERCH.equals(data.get("role_type"))){
            data.put("tran_type", BusiConstant.TranType.MERCH_PURCHASE_APPLY.val());

            data.put("payer_type",BusiConstant.ROLE_MERCH);
            data.put("merch_id",data.get("payer_id"));
            validate.validatePayer(data);

            data.put("payee_type",BusiConstant.ROLE_MERCH);
            data.put("merch_id",data.get("payee_id"));
            validate.validatePayee(data);

            merchPurchase.process(data);
        }else{
            throw new BusiException(ErrorCodeConstant.PubErrorCode.VAZ007.code(), ErrorCodeConstant.PubErrorCode.VAZ007.val());
        }
    }
}