package com.emcs.busniess.common;

import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.util.CheckEmpty;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/22.
 */
public class ServiceUtil {
    private static Logger log = LoggerFactory.getLogger(ServiceUtil.class);
    public static boolean validatePrdIsDoWell(OneTableSelectMapper oneSelect,Map<String, Object> param){
        List<Map<String,Object>> prdList = oneSelect.selectEodProcLog(param);
        if(!CheckEmpty.isEmpty(prdList)){
            if("000000".equals(prdList.get(0).get("status"))){
                log.info("产品号为["+param.get("prd_no")+"]的任务已经处理完成");
                return true;
            }
            param.put("proc_log_seq",prdList.get(0).get("proc_log_seq"));
        }
        return false;
    }
}