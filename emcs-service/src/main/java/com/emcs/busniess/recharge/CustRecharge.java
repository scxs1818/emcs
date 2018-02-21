package com.emcs.busniess.recharge;

import com.emcs.Constant.BusiConstant;
import com.emcs.busniess.common.*;
import com.emcs.supers.PubService;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import com.emcs.supers.SupperService;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
@Transactional
public class CustRecharge extends PubService{
    @Autowired
    InsertCmAcctTranSeq insertCmAcctTranSeq;
    @Autowired
    UpdateCmAcctTranSeq updateCmAcctTranSeq;
    @Autowired
    SendCorePay sendCorePay;
    @Autowired
    SendNetPay sendNetPay;
    @Override
    public void process(Map<String, Object> data) {

        Map<String,Object> payeeMap = (Map<String,Object>)data.get("payeeInfo");
        boolean flag = false;
        try{
            //2.记账无流水
            insertCmAcctTranSeq.process(data);
            flag = true;

            //3.发支付(下面两种方式,根据实际实现2选1)
            sendCorePay.process(data);//核心支付
            sendNetPay.process(data);//互联网支付

            //4.支付成功
            //4.1增加会员虚拟账户余额
            data.put("usable_bal",0);//可用金额不变
            data.put("recharge_bal",data.get("tran_amt"));//充值金额增加
            oneDML.updateVaCustVirtualAcctBalAdd(data);

            //4.2记录充值明细
            oneDML.insertVaCustRechargeSeq(data);

            //5.更新账务流水(依据支付状态)
            data.put("tran_status","01");//记账成功
            updateCmAcctTranSeq.process(data);
        }catch(Exception e){
            data.put("tran_status","02");//记账失败
            if(flag)
                updateCmAcctTranSeq.process(data);
            throw e;
        }
    }
}