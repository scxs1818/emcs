package com.emcs.busniess.order;

import com.emcs.supers.PubServiceY;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class MerchPurchaseApply extends PubServiceY {
    @Override
    public void process(Map<String, Object> data) {
        Map<String,Object>  payerMap = (Map<String,Object>)data.get("payerInfo");
        BigDecimal usable_bal = new BigDecimal(payerMap.get("usable_bal")+"");
        BigDecimal tran_amt = new BigDecimal(data.get("tran_amt")+"");
        if(usable_bal.compareTo(tran_amt)==-1){
            data.put("usable_bal",usable_bal);
            data.put("recharge_bal",tran_amt.subtract(usable_bal));
        }else{
            data.put("usable_bal",tran_amt);
            data.put("recharge_bal",0);
        }
        oneDML.updateVaMerchVirtualAcctBalSub(data);

        //插入订单信息
        data.put("order_status","01");//订单确认
        data.put("create_date", data.get("tran_date")+""+data.get("tran_time"));
        oneDML.insertVaOrderInfo(data);

        //插入订单流水信息
        oneDML.insertVaOrderSeq(data);
    }
}