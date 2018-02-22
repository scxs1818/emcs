package com.emcs.busniess.common;

import com.emcs.eod.AutoConfirmOrders;
import com.emcs.eod.BatchVirAccountBalance;
import com.emcs.eod.DaySwich;
import com.emcs.eod.FromRecharBalToUsableBal;
import com.emcs.exception.BusiException;
import com.emcs.supers.PubServiceY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/22.
 */
@Service
public class TaskExecute extends PubServiceY{
    @Autowired
    DaySwich daySwich;
    @Autowired
    FromRecharBalToUsableBal fromRecharBalToUsableBal;
    @Autowired
    BatchVirAccountBalance batchVirAccountBalance;
    @Autowired
    AutoConfirmOrders autoConfirmOrders;

    @Override
    public void process(Map<String, Object> data) {
        data.put("status","666666");
        List<Map<String,Object>> taskList = oneSelect.selectEodProcPrdLog(data);
        for (Map<String,Object> taskMap:taskList){
            String classBean = taskMap.get("class_bean")+"";
            try {
                Object cls = Class.forName(classBean).newInstance();
                if(cls instanceof DaySwich){
                    daySwich.process(taskMap);
                }else if(cls instanceof DaySwich){
                    daySwich.process(taskMap);
                }else if(cls instanceof BatchVirAccountBalance){
                    batchVirAccountBalance.process(taskMap);
                }else if(cls instanceof FromRecharBalToUsableBal){
                    fromRecharBalToUsableBal.process(taskMap);
                }else if(cls instanceof AutoConfirmOrders){
                    autoConfirmOrders.process(taskMap);
                }else{
                    throw new BusiException("123456","上不支持的服务");
                }
                data.put("status","000000");
                oneDML.updateEodProcPrdLog(data);
            } catch (InstantiationException e) {
                log.error("业务服务["+classBean+"]执行失败",e);
            } catch (IllegalAccessException e) {
                log.error("业务服务["+classBean+"]执行失败",e);
            } catch (ClassNotFoundException e) {
                log.error("业务服务["+classBean+"]执行失败",e);
            } catch (Exception e) {
                log.error("业务服务["+classBean+"]执行失败",e);
                data.put("status","999999");
                data.put("remark",e.getMessage());
                oneDML.updateEodProcPrdLog(data);
            }
        }
    }
}