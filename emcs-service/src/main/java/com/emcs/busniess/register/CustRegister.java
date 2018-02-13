package com.emcs.busniess.register;

import com.emcs.supers.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant.*;
import com.emcs.exception.BusiException;
import com.emcs.supers.SupperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
@Service
public class CustRegister extends SupperService {
    @Override
    protected void process(Map<String, Object> param) {
        //2.判断是否已经注册
        if(oneSelect.selectIsExistVaCustInfo(param)>0)throw new BusiException("该会员信息已经存在","600004");

        //3.生成会员编号
        param.put("status","N");
        param.put("cust_id",param.get("plat_id")+ oneSelect.getNextVal(Quence.CUST.val()));

        //4.注册会员信息
        oneDML.insertVaCustInfo(param);

        param.put("acct_status","N");
        param.put("cust_virid",oneSelect.getNextVal(Quence.CUST_VIRT.val()));
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

        //7.绑定会员与银行账户信息
        oneDML.insertVaCustAccInfo(param);
    }
}