package com.emcs.serviceImpl.busniess.recharge;

import com.emcs.Super.ServiceTransactionalY;
import com.emcs.exception.BusiException;
import com.emcs.serviceImpl.busniess.common.InsertCmAcctTranSeq;
import com.emcs.serviceImpl.busniess.common.SendCorePay;
import com.emcs.serviceImpl.busniess.common.SendNetPay;
import com.emcs.serviceImpl.busniess.common.UpdateCmAcctTranSeq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class MerchRecharge extends ServiceTransactionalY{
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
//        1.1判断商户信息是否存在
        if(oneSelect.selectIsExistVaMerchInfo(param)==0)throw new BusiException("商户信息不存在或者处于异常状态","600007");
        boolean flag = false;
        try{
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
