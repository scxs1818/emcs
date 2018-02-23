package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.eod.InsertEodProcLog;
import com.emcs.supers.PubServiceY;
import com.emcs.util.CheckEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/22.
 */
@Transactional
@Service
public class TaskPretreatment extends PubServiceY{
    @Autowired
    InsertEodProcLog insertEodProcLog;
    @Autowired
    public void process(Map<String, Object> data) {
        log.info("TaskPretreatment="+data);
        data.put("status", "666666");//正在执行中
        if (CheckEmpty.isEmpty(data.get("proc_log_seq"))) {
            data.put("tran_time", new SimpleDateFormat(BusiConstant.FOR_STAN_06).format(new Date()));
            //插入产品日志信息
            insertEodProcLog.process(data);
            //插入产品任务日志信息
            manyDML.insertEodProcPrdLogs(data);
        } else {
            //将处理不成功的产品任务状态还原到初始化
            oneDML.updateEodProcPrdLog(data);
        }
    }
}