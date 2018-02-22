package com.emcs.busniess.register;

import com.emcs.Constant.BusiConstant.*;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.busniess.card.BindCard;
import com.emcs.exception.BusiException;
import com.emcs.supers.InServiceY;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
@Service
public class MerchRegister extends InServiceY {
    @Autowired
    BindCard bindCard;
    @Override
    public void process(Map<String, Object> data) {
        //1.非空和域合法性校验

        //2.数据库级的校验
        Map info = new HashMap<>();
        info.put("status","N");
        info.put("plat_id",data.get("plat_id"));
        data.put("create_date",data.get("tran_date")+""+data.get("tran_time"));
        
        if(oneSelect.selectIsExistVaPlatInfo(info)==0)throw new BusiException(PlatErrorCode.VAP001.code(),PlatErrorCode.VAP001.val());

        info.put("tel_no",data.get("tel_no"));
        if(oneSelect.selectIsExistVaMerchInfo(info)>0)throw new BusiException(MerchErrorCode.VAB004.code(),MerchErrorCode.VAB004.val());

        //3.生成商户编号
        data.put("status","N");
        data.put("acct_status","N");

        data.put("merch_id",data.get("plat_id")+oneSelect.getNextVal(Quence.MERCH.val()));

        //4.注册商户信息
        oneDML.insertVaMerchInfo(data);

        data.put("vir_acct_type","201");
        List<Map<String,Object>> virAcctTypeList = oneSelect.selectVaVirtualAcctType(data);
        data.putAll(virAcctTypeList.get(0));

        //7.生产商户虚拟账户编号
        data.put("merch_virid", oneSelect.getNextVal(Quence.MERCH_VIRT.val()));

        //8.注册虚拟账户信息
        oneDML.insertVaMerchVirtualAcct(data);

        //9.注册虚拟账户余额信息
        data.put("actural_bal",0);
        data.put("usable_bal",0);
        data.put("freeze_bal",0);
        data.put("recharge_bal",0);
        oneDML.insertVaMerchVirtualAcctBal(data);

        //绑卡
        if(!CheckEmpty.isEmpty(data.get("acct_no"))){
            try{
                data.remove("pay_merch_id");
                data.put("member_id",data.get("merch_id"));
                data.put("role_type", Role.MERCH.val());
                bindCard.process(data);
            }catch (Exception e){
                log.error("绑卡失败",e);
            }
        }
    }
}