package com.emcs.serviceImpl.busniess.register;
import com.emcs.Constant.RealTimeInterfaceConstant.*;
import com.emcs.Super.ServiceTransactionalY;
import com.emcs.Constant.BusiConstant;
import com.emcs.exception.BusiException;
import com.emcs.tool.ServiceUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class PlatformRegister extends ServiceTransactionalY {
    public void process(Map<String, Object> param){
        // 1.校验支付商户编码是否存在

        if(oneSelect.selectIsExistVaPlatInfo(param)>0) throw new BusiException("该平台已经注册","600003");
        if(3>2)throw new BusiException(Platform.RGI0P00000.desc(),Platform.RGI0P00000.code());
        // 2.生成平台编码
        String platId = BusiConstant.ROLE_PLAT+ ServiceUtil.getSeqNo(oneSelect, BusiConstant.SEQ_NAME_PLAT, BusiConstant.SEQ_NO_PLAT_LENGTH);
        param.put("plat_id",platId);

        //3.注册平台信息
        oneDML.insertVaPlatInfo(param);

        //4.保存登记平台资金清算专户和平台结算账户
        String acctId = BusiConstant.ACCT_BAN+ BusiConstant.ROLE_PLAT+ ServiceUtil.getSeqNo(oneSelect, BusiConstant.SEQ_NAME_PLAT_BAN_ACC, BusiConstant.SEQ_NO_PLAT_BAN_ACC_LENGTH);
        param.put("acct_id",acctId);
        oneDML.insertVaPlatAccInfo(param);

        //5.
        param.put("vir_acct_type","101");
        List<Map<String,Object>> virAcctTypeList = oneSelect.selectVaVirtualAcctType(param);
        param.putAll(virAcctTypeList.get(0));

        //5.注册虚拟账户信息
        param.put("vir_acct_id", BusiConstant.ACCT_VIR+platId);
        oneDML.insertVaPlatVirtualAcct(param);

        //6.注册虚拟账户余额信息
        param.put("actural_bal",0);
        param.put("usable_bal",0);
        param.put("freeze_bal",0);
        param.put("recharge_bal",0);
        oneDML.insertVaPlatVirtualAcctBal(param);
    }
}