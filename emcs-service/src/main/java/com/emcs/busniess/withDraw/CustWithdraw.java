package com.emcs.busniess.withDraw;

import com.emcs.supers.PubServiceY;
import com.emcs.busniess.common.InsertCmAcctTranSeq;
import com.emcs.busniess.common.SendCorePay;
import com.emcs.busniess.common.UpdateCmAcctTranSeq;
import com.emcs.busniess.common.SendNetPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public class CustWithdraw extends PubServiceY {
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
        boolean flag = false;
        try{
            //2.记账无流水
            insertCmAcctTranSeq.process(data);
            flag = true;
            //3.发核心支付(下面两种方式,根据实际实现2选1)
            sendCorePay.process(data);//核心支付
            sendNetPay.process(data);//互联网支付

            //4.支付成功
            //4.1扣减会员虚拟账户余额
            data.put("usable_bal",data.get("tran_amt"));//提现只能提可靠用金额
            data.put("recharge_bal",0);//充值金额不变
            oneDML.updateVaCustVirtualAcctBalSub(data);

            //4.2记录充值明细
            oneDML.insertVaCustWithdrawSeq(data);

            //5.更新账务流水(依据支付状态)
            data.put("tran_status","01");
            updateCmAcctTranSeq.process(data);
        }catch(Exception e){
            data.put("tran_status","02");
            if(flag)
                updateCmAcctTranSeq.process(data);
            throw e;
        }
    }
}