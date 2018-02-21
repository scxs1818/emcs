package com.emcs.timer;

import com.emcs.Constant.BusiConstant;
import com.emcs.cache.CacheData;
import com.emcs.exception.BusiException;
import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.supers.SuperTask;
import com.emcs.util.CheckEmpty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
    OneTableSelectMapper oneSelect;
    @Resource
    OneTableDMLMapper oneDML;
    @Scheduled(cron = "0 0/1 * * * ?")
    public void process() {
        log.info("定时任务监听:[ScheduleListener]......");

        List<Map<String, Object>> jobList = oneSelect.selectScheduleJob(null);

        if (CheckEmpty.isEmpty(jobList)) return;
        for (Map<String, Object> jobMap : jobList) {
            try {
                String classBean = jobMap.get("class_bean") + "";
                log.info("当前执行的定时任务为:" + jobMap.get("job_code"));
                ((SuperTask) Class.forName(classBean).newInstance()).process(jobMap,oneSelect,oneDML);
                log.info("定时任务[" + jobMap.get("job_code") + "]执行成功");
            } catch (Exception e) {
                log.info("定时任务[" + jobMap.get("job_code") + "]执行失败");
                throw new BusiException("执行定时任务过程异常", e);
            }
        }
    }
}