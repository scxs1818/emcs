package com.emcs.busniess.recharge;
import com.emcs.Constant.ErrorCodeConstant;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant;
import com.emcs.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        //2.数据库级校验
        param.put("status","N");//正常
        param.put("acct_status","N");
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)
            throw new BusiException(ErrorCodeConstant.PlatErrorCode.VAP001.code(), ErrorCodeConstant.PlatErrorCode.VAP001.val());

        if(BusiConstant.ROLE_CUST.equals(param.get("role_type"))){
            param.put("tran_type", BusiConstant.TranType.CUST_RECHARGE.vaue());
            param.put("payee_type",BusiConstant.ROLE_CUST);
            param.put("cust_id",param.get("merber_id"));
            validate.validatePayee(param);
            custRecharge.process(param);
        }else if(BusiConstant.ROLE_MERCH.equals(param.get("role_type"))){
            param.put("payee_type",BusiConstant.ROLE_MERCH);
            param.put("merch_id",param.get("merber_id"));
            validate.validatePayee(param);
            merchRecharge.process(param);
        }else{
            throw new BusiException("角色类型错误","600009");
        }
    }
}