package com.emcs.timer;
        import com.emcs.exception.BusiException;
        import com.emcs.mapper.OneTableDMLMapper;
        import com.emcs.mapper.OneTableSelectMapper;
        import com.emcs.pub.runtime.core.Logger;
        import com.emcs.pub.runtime.core.LoggerFactory;
        import com.emcs.supers.SuperTask;
        import com.emcs.util.CheckEmpty;
        import org.springframework.scheduling.annotation.Scheduled;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Service;
        import javax.annotation.Resource;
        import javax.servlet.ServletContextEvent;
        import java.util.*;

/**
 * Created by Administrator on 2018/2/19.
 */
@Component
public class EODTimerListener {
    private Logger log = LoggerFactory.getLogger(EODTimerListener.class);
    @Resource
    OneTableSelectMapper oneSlect;
    @Resource
    OneTableDMLMapper oneDML;

    @Scheduled(cron="0 0/1 8-20 * * ?")
    public void process() {
        List<Map<String,Object>> taskList = oneSlect.selectEodProcRule(null);
        if(CheckEmpty.isEmpty(taskList))return;
        try{
            log.info("开始执行任务...");
            for (Map<String,Object> taskMap:taskList){
                try{
                    String classBean = taskMap.get("class_bean")+"";
                    log.info("当前执行任务号为:"+taskMap.get("step_no"));

                    ((SuperTask)Class.forName(classBean).newInstance()).process( new HashMap<>(),oneSlect,oneDML);

                    log.info("任务号为["+taskMap.get("step_no")+"]执行成功");
                }catch (Exception e){
                    log.info("任务号为["+taskMap.get("step_no")+"]执行失败");
                    throw new BusiException("执行任务过程异常",e);
                }
            }
            log.info("任务执行完毕...");
        }catch (Exception e){
            log.info("执行任务过程异常",e);
        }
    }
}