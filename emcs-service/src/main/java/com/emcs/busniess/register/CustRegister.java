package com.emcs.busniess.register;

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
        param.put("status","N");
        param.put("length",Quence.CUST.length());
        param.put("seqname",Quence.CUST.gname());
        String cust_id = Role.CUST.vaue()+param.get("plat_id")+ oneSelect.getNextVal(param);
        param.put("cust_id",cust_id);

        //4.注册会员信息
        oneDML.insertVaCustInfo(param);

        param.put("acct_status","N");
        param.put("length",Quence.CUST_VIRT.length());
        param.put("seqname",Quence.CUST_VIRT.gname());
        String vir_acct_id = AcctProperty.ACCT_VIR.value()+ cust_id;
        param.put("cust_virid",vir_acct_id);
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

        param.put("length",Quence.PLAT_BANK.length());
        param.put("seqname",Quence.PLAT_BANK.gname());
        String acct_id = AcctProperty.ACCT_BAN.value()+Role.PLAT.vaue()+ oneSelect.getNextVal(param);
        param.put("acct_id",acct_id);
        //7.绑定会员与银行账户信息
        oneDML.insertVaCustAccInfo(param);
    }
}