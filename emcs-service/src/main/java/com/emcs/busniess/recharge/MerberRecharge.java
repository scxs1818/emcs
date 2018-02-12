package com.emcs.busniess.recharge;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant;
import com.emcs.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class MerberRecharge extends ServiceTransactionalY{
    @Autowired
    CustRecharge custRecharge;
    @Autowired
    MerchRecharge merchRecharge;
    @Autowired
    LimitValidate validate;
    @Override
    protected void process(Map<String, Object> param) {
        //初始化会员和会员账户状态
        param.put("status","N");
        param.put("acct_status","N");

        //校验平台
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)
            throw new BusiException(PlatErrorCode.VAP001.code(), PlatErrorCode.VAP001.val());

        if(BusiConstant.ROLE_CUST.equals(param.get("role_type"))){
            param.put("tran_type", BusiConstant.TranType.CUST_RECHARGE.vaue());
            param.put("payee_type",BusiConstant.ROLE_CUST);
            param.put("cust_id",param.get("payer_id"));

            validate.validatePayee(param);

            if(Integer.parseInt(oneSelect.selectVaCustRechargeSeqSum(param).get(0).get("sum_cnt")+"")>0)
                throw new BusiException(PubErrorCode.VAZ020.code(),PubErrorCode.VAZ020.val());

            custRecharge.process(param);
        }else if(BusiConstant.ROLE_MERCH.equals(param.get("role_type"))){
            param.put("payee_type",BusiConstant.ROLE_MERCH);
            param.put("merch_id",param.get("payer_id"));

            validate.validatePayee(param);

            if(Integer.parseInt(oneSelect.selectVaMerchRechargeSeqSum(param).get(0).get("sum_cnt")+"")>0)
                throw new BusiException(PubErrorCode.VAZ020.code(),PubErrorCode.VAZ020.val());

            merchRecharge.process(param);
        }else{
            throw new BusiException(PubErrorCode.VAZ007.code(),PubErrorCode.VAZ007.val());
        }
    }
}