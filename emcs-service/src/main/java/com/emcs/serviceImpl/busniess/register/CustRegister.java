package com.emcs.serviceImpl.busniess.register;

import com.emcs.Super.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant.*;
import com.emcs.exception.BusiException;
import com.emcs.tool.ServiceUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
@Service
public class CustRegister extends ServiceTransactionalY {
    @Override
    protected void process(Map<String, Object> param) {
        //2.判断是否已经注册
        if(oneSelect.selectIsExistVaCustInfo(param)>0)throw new BusiException("该会员信息已经存在","600004");

        //3.生成会员编号
        String cust_id = Role.CUST+ ServiceUtil.getSeqNo(oneSelect, Quence.CUST.gname(), Quence.CUST.length());
        param.put("cust_id",cust_id);

        //4.注册会员信息
        oneDML.insertVaCustInfo(param);

        String vir_acct_id = AcctProperty.ACCT_VIR.value()+ Role.CUST.vaue()+ ServiceUtil.getSeqNo(oneSelect, Quence.CUST.gname(), Quence.CUST.length());
        param.put("vir_acct_id",vir_acct_id);

        param.put("vir_acct_type","301");
        List<Map<String,Object>> mapList= oneSelect.selectVaVirtualAcctType(param);
        param.putAll(mapList.get(0));

        //5.注册会员虚拟账户信息
        oneDML.insertVaCustVirtualAcct(param);

        //6.注册会员虚拟账户余额信息
        param.put("actural_bal",0);
        param.put("usable_bal",0);
        param.put("freeze_bal",0);
        param.put("recharge_bal",0);
        oneDML.insertVaCustVirtualAcctBal(param);

        String acct_id = AcctProperty.ACCT_BAN.value()+Role.PLAT.vaue()+ ServiceUtil.getSeqNo(oneSelect, Quence.PLAT_BANK.gname(), Quence.PLAT_BANK.length());
        param.put("acct_id",acct_id);
        //7.保存会员银行账户信息
        oneDML.insertVaCustAccInfo(param);
    }
}