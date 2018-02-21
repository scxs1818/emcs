package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant;
import com.emcs.exception.BusiException;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.PubService;
import com.emcs.supers.SuperTask;
import com.emcs.supers.SupperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/21.
 */
@Service
public class ConfirmOrders extends SuperTask {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void process(Map<String, Object> data, OneTableSelectMapper oneS, OneTableDMLMapper oneDML) {
        List<Map<String, Object>> orderList = oneS.selectVaOrderInfoLock(data);
        BigDecimal sumAmt = new BigDecimal(0);
        for (Map<String, Object> orderMap : orderList) {
            //1.插入账务流水
            orderMap.put("tran_seq_no", oneS.getNextVal(BusiConstant.Quence.TRAN_SEQ_NO.val()));
            oneDML.insertCmAcctTranSeq(orderMap);

            //2.更新订单状态
            oneDML.updateVaOrderInfo(orderMap);

            //3.插入订单流水信息
            oneDML.insertVaOrderSeq(data);

            //4.累计订单金额
            sumAmt.add((BigDecimal)orderMap.get("tran_amt"));
        }

        //5.增加商户虚拟账户余额
        data.put("tran_amt",sumAmt);
        data.put("usable_bal",sumAmt);
        data.put("recharge_bal",0);
        oneS.selectVaMerchVirtualAcctBalLock(data);
        oneDML.updateVaMerchVirtualAcctBalAdd(data);
    }
}