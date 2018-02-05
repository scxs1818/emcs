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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
abstract class ServiceSupper {
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
    public CommonResult doService(Map<String, Object> param){
        try{
            before(param);
            process(param);
            after(param);
            result.setMsg("交易成功");
            result.setStatus("S");
        }catch(Exception e){
            log.error("交易失败",e);
            result.setMsg(e.getMessage());
            result.setStatus("F");
            DoException.doThrowException(e);
        }
        return result;
    }
    protected abstract void process(Map<String, Object> param);
    protected abstract CommonResult after(Map<String, Object> param);
    protected abstract CommonResult before(Map<String, Object> param);
}
