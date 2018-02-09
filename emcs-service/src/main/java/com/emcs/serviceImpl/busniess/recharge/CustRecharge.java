package com.emcs.serviceImpl.busniess.recharge;

import com.emcs.Constant.BusiConstant;
import com.emcs.Super.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import com.emcs.serviceImpl.busniess.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class CustRecharge extends ServiceTransactionalY{
    @Autowired
    InsertCmAcctTranSeq insertCmAcctTranSeq;
    @Autowired
    UpdateCmAcctTranSeq updateCmAcctTranSeq;
    @Autowired
    SendCorePay sendCorePay;
    @Autowired
    SendNetPay sendNetPay;
    @Override
    protected void process(Map<String, Object> param) {
        //1.数据库级校验
        //1.1判断会员信息是否存在且是否正常
        if(oneSelect.selectIsExistVaCustInfo(param)==0)throw new BusiException("该会员信息不存在或者处于异常状态","600006");

        //1.2判断会员虚拟账户是否正常
        List<Map<String,Object>> virAcctList = oneSelect.selectVaCustVirtualAcctInfo(param);
        if(virAcctList==null||virAcctList.size()==0)throw new BusiException("该会员虚拟账户不存在或者处于异常状态","600008");


        //1.3判断会员虚拟账户是否存在转入限制
        Map<String,Object> virAcctMap = virAcctList.get(0);
        if(!"Y".equals(virAcctMap.get("is_in")))throw new BusiException("该会员虚拟账户不允许转入","600009");
        virAcctMap.put("single_limit",2000);

        //1.4判断充值金额是否超出单笔限额
        if(new BigDecimal(param.get("tran_amt")+"").compareTo(new BigDecimal(virAcctMap.get("single_limit")+""))==1)throw new BusiException("会员充值已经超出单笔限额","600009");

        //1.5判断充值金额是否超出日限额
        List<Map<String,Object>> sumList = oneSelect.selectVaCustRechargeAmtDay(param);
        BigDecimal sumAmt =(BigDecimal) sumList.get(0).get("sum_amt");
        if(sumAmt.compareTo(new BigDecimal(virAcctMap.get("total_limit")+""))==1)throw new BusiException("会员充值已经超出日限额","600009");

        //1.6判断充值次数是否超出日充值最大次数
        Integer sumCnt =Integer.parseInt( sumList.get(0).get("sum_cnt")+"");
        if(new BigDecimal(sumCnt).compareTo(new BigDecimal(3))==1)throw new BusiException("会员充值已经超出日充值次数","600009");

        boolean flag = false;
        try{
            param.put("tran_type", BusiConstant.TranType.CUST_RECHARGE.vaue());
            //2.记账无流水
            insertCmAcctTranSeq.process(param);
            flag = true;
            //3.发核心支付(下面两种方式,根据实际实现2选1)
            sendCorePay.process(param);//核心支付
            sendNetPay.process(param);//互联网支付

            //4.支付成功
                //4.1增加会员虚拟账户余额
                param.put("usable_bal",0);//可用金额不变
                param.put("recharge_bal",param.get("tran_amt"));//充值金额增加
                oneDML.updateVaCustVirtualAcctBalAdd(param);

                //4.2记录充值明细
            oneDML.insertVaCustRechargeDetail(param);
            //5.更新账务流水(依据支付状态)
            updateCmAcctTranSeq.process(param);
        }catch(Exception e){
            if(flag)
                updateCmAcctTranSeq.process(param);
            throw e;
        }
    }
}