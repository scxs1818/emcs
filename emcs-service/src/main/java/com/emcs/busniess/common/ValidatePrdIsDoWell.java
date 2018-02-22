package com.emcs.busniess.common;

import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.supers.PubServiceY;
import com.emcs.util.CheckEmpty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/22.
 */
@Service
public class ValidatePrdIsDoWell{
    protected Logger log = LoggerFactory.getLogger(ValidatePrdIsDoWell.class);
    @Resource
    protected OneTableSelectMapper oneSelect;
    public boolean process(Map<String, Object> data) {
        List<Map<String,Object>> prdList = oneSelect.selectEodProcLog(data);
        if(!CheckEmpty.isEmpty(prdList)){
            if("000000".equals(prdList.get(0).get("status"))){
                log.info("产品号为["+data.get("prd_no")+"]的任务已经处理完成");
                return true;
            }
            data.put("proc_log_seq",prdList.get(0).get("proc_log_seq"));
        }
        return false;
    }
}