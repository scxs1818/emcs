package com.emcs.busniess.common;

import com.emcs.Constant.BusiConstant;
import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.supers.PubServiceY;
import com.emcs.util.CheckEmpty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/22.
 */
@Transactional
@Service
public class TaskPretreatment {
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    ManyTableDMLMapper manyDML;

    public void process(Map<String, Object> data) {
        if (CheckEmpty.isEmpty(data.get("proc_log_seq"))) {
            data.put("tran_time", new SimpleDateFormat(BusiConstant.FOR_STAN_06).format(new Date()));
            data.put("status", "666666");//正在执行中
            //插入产品日志信息
            oneDML.insertEodProcLog(data);
            //插入产品任务日志信息
            manyDML.insertEodProcPrdLogs(data);
        } else {
            //将处理不成功的产品任务状态还原到初始化
            oneDML.updateEodProcPrdLog(data);
        }
    }
}