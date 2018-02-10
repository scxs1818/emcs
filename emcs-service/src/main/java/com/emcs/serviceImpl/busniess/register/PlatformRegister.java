package com.emcs.serviceImpl.busniess.register;
import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant.*;
import com.emcs.Super.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant.*;
import com.emcs.exception.BusiException;
import com.emcs.tool.ServiceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class PlatformRegister extends ServiceTransactionalY {

    public void process(Map<String, Object> param){

        // 1.校验支付商户编码是否存在
        if(oneSelect.selectIsExistVaPlatInfo(param)>0) throw new BusiException(PlatErrorCode.VAP001.code(),PlatErrorCode.VAP001.val());

        // 2.生成平台编码
        param.put("length",Quence.PLAT.length());
        param.put("seqname",Quence.PLAT.gname());
        log.info("#############=length"+Quence.PLAT.length()+",="+Quence.PLAT.gname());
        String platId = Role.PLAT.vaue()+ oneSelect.getNextVal(param);
        log.info("8888888888888888888888="+platId);
        param.put("plat_id",platId);
        param.put("status","N");//首次注册为正常
        param.put("payment_type",0);//手动结算
        param.put("currency","CNY");

        //3.注册平台信息
        oneDML.insertVaPlatInfo(param);

        //4.保存登记平台资金清算专户和平台结算账户
        String acctId;
        //4.1绑定平台结算账户
        Object acct_no=param.get("settle_acct");
        param.put("length",Quence.PLAT_BANK.length());
        param.put("seqname",Quence.PLAT_BANK.gname());
        if(acct_no!=null&&!"".equals(acct_no.toString().trim())){
            acctId = AcctProperty.ACCT_BAN.value()+ Role.PLAT.vaue()+ oneSelect.getNextVal(param);
            param.put("acct_id",acctId);
            param.put("acct_type", BusiConstant.ACCT_TYPE_PLAT_SETTLE);
            param.put("acct_no",acct_no);
            param.put("acct_category",param.get("settle_acct_category"));
            oneDML.insertVaPlatAccInfo(param);
        }
        //4.2绑定平台资金清算专户
        acctId = AcctProperty.ACCT_BAN.value()+ Role.PLAT.vaue()+ oneSelect.getNextVal(param);
        log.info("***************="+acctId);
        param.put("acct_id",acctId);
        param.put("acct_type", BusiConstant.ACCT_TYPE_PLAT_DEPOSIT);
        param.put("acct_no",param.get("deposit_acct"));
        param.put("acct_name","资金清算专户");
        param.put("is_this_bank","Y");
        param.put("acct_category",param.get("deposit_acct_category"));
        param.put("acct_br_no","支付机构代码");
        param.put("acct_br_name","支付机构名称");
        oneDML.insertVaPlatAccInfo(param);

        //5.查询虚拟账户类型
        param.put("vir_acct_type","101");
        List<Map<String,Object>> virAcctTypeList = oneSelect.selectVaVirtualAcctType(param);
        param.putAll(virAcctTypeList.get(0));

        //5.注册虚拟账户信息
        param.put("plat_virid", AcctProperty.ACCT_VIR.value()+platId);
        oneDML.insertVaPlatVirtualAcct(param);

        //6.注册虚拟账户余额信息
        param.put("actural_bal",0);
        param.put("usable_bal",0);
        param.put("freeze_bal",0);
        param.put("recharge_bal",0);
        oneDML.insertVaPlatVirtualAcctBal(param);
    }
}