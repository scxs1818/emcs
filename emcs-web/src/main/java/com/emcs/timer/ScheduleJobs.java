package com.emcs.timer;

import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/2/19.
 */
//@Component
public class ScheduleJobs {
    public final static long SECOND = 1 * 1000;
    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    //@Scheduled(fixedDelay = SECOND * 2)
    public void fixedDelayJob() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("[FixedDelayJob Execute]" + fdf.format(new Date()));
    }
}

//@Component
class ScheduleJobs1 {
    public final static long SECOND = 1 * 1000;
    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    //@Scheduled(fixedRate = SECOND * 4)
    public void fixedRateJob() {
        System.out.println("[FixedRateJob Execute]" + fdf.format(new Date()));
    }
}

//@Component
class ScheduleJobs2 {
    public final static long SECOND = 1 * 1000;
    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    //@Scheduled(cron = "0/4 * * * * ?")
    public void cronJob() {
        System.out.println("[CronJob Execute]" + fdf.format(new Date()));
    }
}

//@Component
class SchedulerTask {
    private Logger log = LoggerFactory.getLogger(SchedulerTask.class);
    private int count=0;

    //@Scheduled(cron="*/6 * * * * ?")
    private void process(){
        System.out.println("this is scheduler task runing  "+(count++));
        log.info("this is scheduler task runing  "+(count++));
    }

}

//@Component
class Scheduler2Task {
    private Logger log = LoggerFactory.getLogger(Scheduler2Task.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
        log.info("现在时间：" + dateFormat.format(new Date()));
    }
}