package com.emcs.serviceImpl.busniess.withDraw;
import com.emcs.Constant.BusiConstant;
import com.emcs.Super.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import com.emcs.serviceImpl.busniess.recharge.CustRecharge;
import com.emcs.serviceImpl.busniess.recharge.MerchRecharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public class MerberWithdraw extends ServiceTransactionalY{

    @Autowired
    CustWithdraw custRecharge;
    @Autowired
    MerchWithdraw merchRecharge;
    @Override
    protected void process(Map<String, Object> param) {
        //2.数据库级校验
        param.put("status","N");//正常
        param.put("acct_status","N");
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)throw new BusiException("交易平台不存在或者处于异常状态","600003");

        if(BusiConstant.ROLE_CUST.equals(param.get("role_type"))){
            param.put("cust_id",param.get("merber_id"));
            custRecharge.process(param);
        }else if(BusiConstant.ROLE_MERCH.equals(param.get("role_type"))){
            param.put("merch_id",param.get("merber_id"));
            merchRecharge.process(param);
        }else{
            throw new BusiException("角色类型错误","600009");
        }
    }
}