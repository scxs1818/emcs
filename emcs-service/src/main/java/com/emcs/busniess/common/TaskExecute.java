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
public class TaskExecute extends PubServiceY {
    @Override
    public void process(Map<String, Object> data) {
        data.put("status", "666666");//查询处理状态
        List<Map<String, Object>> taskList = oneSelect.selectEodProcPrdLog(data);
        for (Map<String, Object> taskMap : taskList) {
            String classBean = taskMap.get("class_bean") + "";
            Class sl = null;
            try {
                log.info("当前执行的任务为:" + classBean);
                sl = Class.forName(classBean);
                ((PubServiceY) sl.newInstance()).process(taskMap);
                log.info("任务[" + classBean + "]执行成功\n");
                data.put("status", "000000");
                oneDML.updateEodProcPrdLog(data);
            } catch (Exception e) {
                log.error("任务[" + classBean + "]执行失败", e);
                data.put("status", "999999");
                oneDML.updateEodProcPrdLog(data);
            }
        }
    }
}