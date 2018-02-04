package com.emcs.serviceImpl.busniess.recharge;

import com.emcs.Super.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import com.emcs.tool.DbUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class CustRecharge extends ServiceTransactionalY{
    @Override
    protected void process(Map<String, Object> param) {
        if(oneSelect.selectIsExistVaCustInfo(param)==0)throw new BusiException("会员信息不存在或者处于异常状态","600006");

        List<Map<String,Object>> virAcctList = oneSelect.selectVaCustVirtualAcctInfo(param);
        if(virAcctList==null||virAcctList.size()==0)throw new BusiException("会员虚拟账户不存在或者处于异常状态","600008");

        Map<String,Object> virAcctMap = virAcctList.get(0);
        log.info("***************="+virAcctMap);
        if(!"Y".equals(virAcctMap.get("is_in")))throw new BusiException("会员虚拟账户不允许转入","600009");






    }
}
