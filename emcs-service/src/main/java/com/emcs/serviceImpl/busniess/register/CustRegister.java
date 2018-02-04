package com.emcs.serviceImpl.busniess.register;

import com.emcs.Super.ServiceTransactionalY;
import com.emcs.Constant.BusiCommon;
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
        String cust_id =BusiCommon.ROLE_CUST+ ServiceUtil.getSeqNo(oneSelect, BusiCommon.SEQ_NAME_CUST,BusiCommon.SEQ_NO_CUST_LENGTH);
        param.put("cust_id",cust_id);

        //4.注册会员信息
        oneDML.insertVaCustInfo(param);

        String vir_acct_id =BusiCommon.ACCT_VIR+BusiCommon.ROLE_CUST+ ServiceUtil.getSeqNo(oneSelect, BusiCommon.SEQ_CUST_MERCH_VIR_ACC,BusiCommon.SEQ_NO_CUST_VIR_LENGTH);
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

        String acct_id =BusiCommon.ACCT_BAN+BusiCommon.ROLE_CUST+ ServiceUtil.getSeqNo(oneSelect, BusiCommon.SEQ_CUST_MERCH_BAN_ACC,BusiCommon.SEQ_NO_CUST_BAN_LENGTH);
        param.put("acct_id",acct_id);
        //7.保存会员银行账户信息
        oneDML.insertVaCustAccInfo(param);
    }
}