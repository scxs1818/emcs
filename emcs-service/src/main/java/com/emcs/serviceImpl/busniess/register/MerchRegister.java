package com.emcs.serviceImpl.busniess.register;

import com.emcs.Super.ServiceTransactionalY;
import com.emcs.common.BusiCommon;
import com.emcs.exception.BusiException;
import com.emcs.tool.ServiceUtil;
import com.emcs.util.CommonResult;
import org.springframework.stereotype.Service;

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
        param.put("status","N");
        if(oneSelect.selectIsExistVaPlatInfo(param)==0)throw new BusiException("交易平台不存在或者处于异常状态","600003");
        if(oneSelect.selectIsExistVaMerchInfo(param)>0)throw new BusiException("该商户已经注册","600004");

        //3.生成商户编号
        String merchSeq = ServiceUtil.getSeqNo(oneSelect, BusiCommon.SEQ_NAME_MERCH,BusiCommon.SEQ_NO_MERCH_LENGTH);
        String palt_id =  param.get("plat_id")+"";
        palt_id = palt_id.substring(1);//去掉首位,因为首位为标志位,剩下的才是序号
        String merch_id = BusiCommon.ROLE_MERCH+palt_id+merchSeq;
        param.put("merch_id",merch_id);

        //4.注册商户信息
        oneDML.insertVaMerchInfo(param);

        //5.生成商户银行账户编号
        String acctSeq = ServiceUtil.getSeqNo(oneSelect, BusiCommon.SEQ_NAME_MERCH_BAN_ACC,BusiCommon.SEQ_NO_MERCH_BAN_LENGTH);
        param.put("acct_id",BusiCommon.ACCT_BAN+merch_id);
        //6.保存商户银行账户信息
        oneDML.insertVaMerchAccInfo(param);

        param.put("vir_acct_type","202");
        List<Map<String,Object>> virAcctTypeList = oneSelect.selectVaVirtualAcctType(param);
        param.putAll(virAcctTypeList.get(0));

        //7.生产商户虚拟账户编号
        String virSeq = ServiceUtil.getSeqNo(oneSelect, BusiCommon.SEQ_NAME_MERCH_VIR_ACC,BusiCommon.SEQ_NO_MERCH_VIR_LENGTH);
        param.put("vir_acct_id",BusiCommon.ACCT_VIR+merch_id);

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