package com.emcs.busniess.register;

import com.emcs.Constant.BusiConstant;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant.*;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.exception.BusiException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
@Service
public class MerchRegister extends ServiceTransactionalY{
    @Override
    protected void process(Map<String, Object> param) {
        //1.非空和域合法性校验

        //2.数据库级的校验
        Map info = new HashMap<>();
        info.put("status","N");
        info.put("plat_id",param.get("plat_id"));
        if(oneSelect.selectIsExistVaPlatInfo(info)==0)throw new BusiException(PlatErrorCode.VAP001.code(),PlatErrorCode.VAP001.val());
        info.put("tel_no",param.get("tel_no"));
        if(oneSelect.selectIsExistVaMerchInfo(info)>0)throw new BusiException(MerchErrorCode.VAB001.code(),MerchErrorCode.VAB001.val());

        //3.生成商户编号
        param.put("status","N");
        String merchSeq = oneSelect.getNextVal(Quence.MERCH.val());
        String palt_id =  param.get("plat_id")+"";
        String merch_id = Role.MERCH.val()+palt_id+merchSeq;
        param.put("merch_id",merch_id);

        //4.注册商户信息
        oneDML.insertVaMerchInfo(param);

        //5.生成商户银行账户编号
        Object acct_no = param.get("settle_acct");
        String acctSeq = oneSelect.getNextVal(Quence.MERCH_BANK.val());
        param.put("acct_id", AcctProperty.ACCT_BAN.val()+Role.MERCH.val()+acctSeq);
        param.put("acct_no",acct_no);
        param.put("acct_type", BusiConstant.ACCT_TYPE_MERCH_SETTLE);
        param.put("acct_category",param.get("settle_acct_category"));
        param.put("acct_status","N");
        //6.绑定商户与银行账户信息
        oneDML.insertVaMerchAccInfo(param);

        param.put("vir_acct_type","202");
        List<Map<String,Object>> virAcctTypeList = oneSelect.selectVaVirtualAcctType(param);
        param.putAll(virAcctTypeList.get(0));

        //7.生产商户虚拟账户编号
        String virSeq = oneSelect.getNextVal(Quence.MERCH_VIRT.val());
        param.put("merch_virid", AcctProperty.ACCT_VIR.val()+merch_id);
        param.put("rel_bank_acct",acct_no);
        //8.注册虚拟账户信息
        oneDML.insertVaMerchVirtualAcct(param);

        //9.注册虚拟账户余额信息
        param.put("actural_bal",0);
        param.put("usable_bal",0);
        param.put("freeze_bal",0);
        param.put("recharge_bal",0);
        oneDML.insertVaMerchVirtualAcctBal(param);
    }
}