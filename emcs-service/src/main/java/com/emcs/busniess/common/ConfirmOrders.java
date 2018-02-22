package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.cache.CacheData;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.SuperTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/21.
 */
@Service
public class ConfirmOrders extends SuperTask {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void process(Map<String, Object> data) {
        Map<String,Object> sysMap = CacheData.getCacheObj(oneS,BusiConstant.Cache.CM_SYSTEM.val());
        List<Map<String, Object>> orderList = oneS.selectVaOrderInfoLock(data);
        BigDecimal sumAmt = new BigDecimal(0);
        String time = new SimpleDateFormat("HHmmss").format(new Date());
        for (Map<String, Object> orderMap : orderList) {
            orderMap.put("create_date",sysMap.get("run_date")+time);
            orderMap.put("update_date",orderMap.get("create_date"));

            //1.插入账务流水
            orderMap.put("tran_seq_no", oneS.getNextVal(BusiConstant.Quence.TRAN_SEQ_NO.val()));
            oneDML.insertCmAcctTranSeq(orderMap);

            //2.更新订单状态
            oneDML.updateVaOrderInfo(orderMap);

            //3.插入订单流水信息
            oneDML.insertVaOrderSeq(orderMap);

            //4.累计订单金额
            sumAmt.add((BigDecimal)orderMap.get("tran_amt"));
        }

        //5.增加商户虚拟账户余额
        data.put("tran_amt",sumAmt);
        data.put("usable_bal",sumAmt);
        data.put("recharge_bal",0);
        log.info("data="+data);
        oneS.selectVaMerchVirtualAcctBalLock(data);
        oneDML.updateVaMerchVirtualAcctBalAdd(data);
    }
}