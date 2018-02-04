package com.emcs.Super;

import com.emcs.exception.BusiException;
import com.emcs.exception.DoException;
import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.util.CommonResult;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
abstract class ServiceSupper {
    public ServiceSupper(){}
    protected Logger log = LoggerFactory.getLogger(ServiceTransactionalY.class);

    @Resource
    protected OneTableSelectMapper oneSelect;
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    protected ManyTableSelectMapper manySelect;
    @Resource
    protected ManyTableDMLMapper manyDML;
    protected CommonResult result = new CommonResult();

    protected CommonResult before(Map<String, Object> param){
//        Map sysMap = (Map)param.get("SYS_HEAD");
//        param.put("TRAN_DATE",sysMap.get("TRAN_DATE"));
//        oneDML.insertVaCustAccInfo(param);//插入交易流水
        return null;
    }
    protected CommonResult after(Map<String, Object> param){
//        oneDML.UpdateVaCustAccInfo(param);//更新交易流水
        return null;
    }
    public CommonResult doService(Map<String, Object> param){
        try{
            before(param);
            process(param);
            after(param);
            result.setMsg("交易成功");
            result.setStatus("S");
        }catch(Exception e){
            log.info("交易失败",e);
            result.setMsg(e.getMessage());
            result.setStatus("F");
            DoException.doThrowException(e);
        }
        return result;
    }
    protected abstract void process(Map<String, Object> param);
}
