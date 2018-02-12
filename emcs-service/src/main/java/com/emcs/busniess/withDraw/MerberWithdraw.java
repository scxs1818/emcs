package com.emcs.busniess.withDraw;
import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public class MerberWithdraw extends ServiceTransactionalY{
    @Autowired
    LimitValidate validate;
    @Autowired
    CustWithdraw custWithdraw;
    @Autowired
    MerchWithdraw merchWithdraw;
    @Override
    protected void process(Map<String, Object> param) {
        //初始化会员和会员账户状态
        param.put("status","N");
        param.put("acct_status","N");

        //校验平台
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)
            throw new BusiException(ErrorCodeConstant.PlatErrorCode.VAP001.code(), ErrorCodeConstant.PlatErrorCode.VAP001.val());

        if(BusiConstant.ROLE_CUST.equals(param.get("role_type"))){
            param.put("tran_type", BusiConstant.TranType.CUST_RECHARGE.vaue());
            param.put("payer_type",BusiConstant.ROLE_CUST);
            param.put("cust_id",param.get("merber_id"));
            validate.validatePayee(param);
            custWithdraw.process(param);
        }else if(BusiConstant.ROLE_MERCH.equals(param.get("role_type"))){
            param.put("payer_type",BusiConstant.ROLE_MERCH);
            param.put("merch_id",param.get("merber_id"));
            validate.validatePayee(param);
            merchWithdraw.process(param);
        }else{
            throw new BusiException("角色类型错误","600009");
        }
    }
}