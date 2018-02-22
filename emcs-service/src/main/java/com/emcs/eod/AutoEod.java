package com.emcs.eod;

import com.emcs.Constant.BusiConstant;
import com.emcs.busniess.common.*;
import com.emcs.cache.CacheData;
import com.emcs.exception.BusiException;
import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.supers.PubServiceN;
import com.emcs.supers.PubServiceY;
import com.emcs.util.CheckEmpty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/22.
 */
@Service
public class AutoEod{
    @Resource
    TaskPretreatment taskPre;
    @Resource
    ValidateIsDaySwitch validateIsDaySwitch;
    @Resource
    TaskExecute taskExecute;
    @Resource
    ValidatePrdIsDoWell validatePrdIsDoWell;

    public void process(Map<String, Object> data) {
        data.put("prd_no","9998");//代表日终
        data.put("tran_date",data.get("run_date"));

        //1.校验是否已经日切,没有日切则不能进行后续操作
        if(!validateIsDaySwitch.process(data))return;

        //2.校验是否已经日终
        if(validatePrdIsDoWell.process(data))return;

        //3.产品产品任务的预处理
        taskPre.process(data);

        //4.总调度
        taskExecute.process(data);
    }
}