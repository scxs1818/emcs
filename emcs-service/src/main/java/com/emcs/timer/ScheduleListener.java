package com.emcs.timer;

import com.emcs.Constant.BusiConstant;
import com.emcs.cache.CacheData;
import com.emcs.eod.AutoConfirmOrders;
import com.emcs.eod.BatchVirAccountBalance;
import com.emcs.eod.DaySwich;
import com.emcs.eod.FromRecharBalToUsableBal;
import com.emcs.exception.BusiException;
import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.supers.ServiceE;
import com.emcs.supers.SuperTask;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/20.
 */
@Component
public class ScheduleListener {

    private Logger log = LoggerFactory.getLogger(ScheduleListener.class);
    @Resource
    OneTableSelectMapper oneS;
    @Scheduled(cron = "20 * * * * ?")
    public void process() {
        log.info("定时任务监听:[ScheduleListener]......");
        List<Map<String, Object>> jobList = oneS.selectScheduleJob(null);
        if (CheckEmpty.isEmpty(jobList)) return;
        for (Map<String, Object> jobMap : jobList) {
            try {
                jobMap.putAll(CacheData.getCacheObj(oneS,BusiConstant.Cache.CM_SYSTEM.val()));
                String classBean = jobMap.get("class_bean") + "";
                log.info("当前执行的定时任务为:" + jobMap.get("job_code"));
                ((ServiceE)Class.forName(classBean).newInstance()).process(jobMap);
                log.info("定时任务[" + jobMap.get("job_code") + "]执行成功\n");
            } catch (Exception e) {
                log.info("定时任务[" + jobMap.get("job_code") + "]执行失败\n");
                throw new BusiException("执行定时任务过程异常", e);
            }
        }
    }
}