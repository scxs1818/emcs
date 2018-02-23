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
import com.emcs.supers.PubServiceY;
import com.emcs.supers.ServiceE;
import com.emcs.supers.SuperTask;
import com.emcs.tool.ReflectionUtils;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.pagehelper.util.MetaObjectUtil.method;

/**
 * Created by Administrator on 2018/2/20.
 */
@Component
public class ScheduleListener {

    private Logger log = LoggerFactory.getLogger(ScheduleListener.class);
    @Resource
    OneTableSelectMapper oneS;

    @Scheduled(cron = "10 * * * * ?")
    public void process() {
        log.info("定时任务监听:[ScheduleListener]......");
        List<Map<String, Object>> jobList = oneS.selectScheduleJob(null);
        if (CheckEmpty.isEmpty(jobList)) return;
        String classBean = null;

        for (Map<String, Object> jobMap : jobList) {
            classBean = jobMap.get("class_bean") + "";
            Class sl = null;
            try {
                log.info("当前执行的定时任务为:" + classBean);
                sl = Class.forName(classBean);
                ((PubServiceY) sl.newInstance()).process(jobMap);
                log.info("定时任务[" + classBean + "]执行成功\n");
            } catch (Exception e) {
                log.error("定时任务[" + classBean + "]执行失败",e);
                throw new BusiException("999999","产品["+classBean+"]处理失败");
            }
        }
    }
}