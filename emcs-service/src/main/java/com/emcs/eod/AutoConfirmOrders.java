package com.emcs.eod;

import com.emcs.Constant.BusiConstant;
import com.emcs.busniess.common.ConfirmOrders;
import com.emcs.cache.CacheData;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.SuperTask;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/21.
 */
public class AutoConfirmOrders extends SuperTask {
    @Autowired
    ConfirmOrders confirmOrders;

    @Override
    public void process(Map<String, Object> data, OneTableSelectMapper oneS, OneTableDMLMapper oneDML) {
        Map<String,Object> sysMap = CacheData.getCacheObj(oneS,BusiConstant.Cache.CM_SYSTEM.val());
        sysMap.put("status","N");
        List<Map<String,Object>> platList = oneS.selectPlatInfoSim(sysMap);

        if(CheckEmpty.isEmpty(platList))return;

        for (Map<String,Object> platMap:platList){
            List<Map<String,Object>> merchList = oneS.selectVaMerchInfoSim(platMap);
            if(CheckEmpty.isEmpty(merchList))return;

            for (Map<String,Object> merchMap:merchList){
                merchMap.put("order_status","01");
                merchMap.put("payee_virid",merchMap.get("merch_id"));

                try{
                    confirmOrders.process(merchMap,oneS,oneDML);
                }catch (Exception e){
                    log.info("商户名称为["+merchMap.get("merch_name")+"]订单批量确认失败");
                }
            }
        }
    }
}