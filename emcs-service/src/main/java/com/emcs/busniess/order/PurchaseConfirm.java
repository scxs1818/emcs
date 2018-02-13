package com.emcs.busniess.order;

import com.emcs.Constant.BusiConstant;
import com.emcs.Constant.ErrorCodeConstant;
import com.emcs.busniess.common.LimitValidate;
import com.emcs.cache.CacheData;
import com.emcs.exception.BusiException;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class PurchaseConfirm extends ServiceTransactionalY {
    @Override
    protected void process(Map<String, Object> data) {
        //初始化会员和会员账户状态
        data.put("status","N");

        //校验平台
        if(oneSelect.selectIsExistVaPlatInfo(data)==0)
            throw new BusiException(ErrorCodeConstant.PlatErrorCode.VAP001.code(), ErrorCodeConstant.PlatErrorCode.VAP001.val());

        List<Map<String,Object>> oldOrderList = oneSelect.selectVaOrderInfoForOld(data);

        if(CheckEmpty.isEmpty(oldOrderList))throw new BusiException("原订单不存在");

        if(oneSelect.selectVaOrderSeqForRepeat(data)>0)throw new BusiException("重复提交");

        Map<String,Object> oldOrderMap = oldOrderList.get(0);

        BigDecimal tran_amt = (BigDecimal)oldOrderMap.get("tran_amt");
        if(tran_amt.compareTo(new BigDecimal(data.get("tran_amt")+""))!=0)throw new BusiException("确认金额有误");

        if(!(data.get("payer_id")+"").equals(oldOrderMap.get("payer_id")))throw new BusiException("付款方信息有误");

        if(!(data.get("payee_id")+"").equals(oldOrderMap.get("payee_id")))throw new BusiException("收款方信息有误");

        String rundate = CacheData.getCacheObj(oneSelect,BusiConstant.CACHE_CM_SYSTEM).get("run_date")+"";
        BigDecimal recharge_bal = (BigDecimal)oldOrderMap.get("recharge_bal");
        BigDecimal usable_bal = (BigDecimal)oldOrderMap.get("usable_bal");

        //隔日确认处理
        if(rundate.compareTo(oldOrderMap.get("create_date")+"")>0){
            oldOrderMap.put("usable_bal",usable_bal.add(recharge_bal));
            oldOrderMap.put("recharge_bal",0);
        }

        if(BusiConstant.Role.CUST.vaue().equals(data.get("role_type"))){
            oldOrderMap.put("tran_type",BusiConstant.TranType.CUST_PURCHASE_CONFIRM.vaue());
        }else if(BusiConstant.Role.MERCH.vaue().equals(data.get("role_type"))){
            oldOrderMap.put("tran_type",BusiConstant.TranType.MERCH_PURCHASE_CONFIRM.vaue());
        }

        oldOrderMap.put("merch_id",oldOrderMap.get("payee_id"));
        oldOrderMap.put("merch_virid",oldOrderMap.get("payee_virid"));

        //增加供应商余额
        oneDML.updateVaMerchVirtualAcctBalAdd(oldOrderMap);

        //插入采购确认流水信息
        oneDML.insertVaOrderSeq(oldOrderMap);
    }
}