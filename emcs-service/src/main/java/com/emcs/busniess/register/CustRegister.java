package com.emcs.busniess.register;

import com.emcs.Constant.ErrorCodeConstant.*;
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
    protected void process(Map<String, Object> data) {
        data.put("status","N");
        //1.校验平台
        if(oneSelect.selectIsExistVaPlatInfo(data)==0)
            throw new BusiException(PlatErrorCode.VAP001.code(), PlatErrorCode.VAP001.val());

        //2.判断是否已经注册
        if(oneSelect.selectIsExistVaCustInfo(data)>0)throw new BusiException(CustErrorCode.VAC004.code(),CustErrorCode.VAC004.val());

        //3.生成会员编号
        data.put("status","N");
        data.put("cust_id",data.get("plat_id")+ oneSelect.getNextVal(Quence.CUST.val()));

        //4.注册会员信息
        oneDML.insertVaCustInfo(data);

        data.put("acct_status","N");
        data.put("cust_virid",oneSelect.getNextVal(Quence.CUST_VIRT.val()));
        data.put("vir_acct_type","301");
        List<Map<String,Object>> mapList= oneSelect.selectVaVirtualAcctType(data);
        data.putAll(mapList.get(0));

        //5.注册会员虚拟账户信息
        oneDML.insertVaCustVirtualAcct(data);

        //6.注册会员虚拟账户余额信息
        data.put("actural_bal",0);
        data.put("usable_bal",0);
        data.put("freeze_bal",0);
        data.put("recharge_bal",0);
        oneDML.insertVaCustVirtualAcctBal(data);

        //7.绑定会员与银行账户信息
        oneDML.insertVaCustAccInfo(data);
    }
}