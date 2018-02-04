package com.emcs.Super;

import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.util.CommonResult;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
public abstract class ServiceTransactionalN {
    @Resource
    public OneTableSelectMapper oneSelect;
    @Resource
    public OneTableDMLMapper oneDML;
    @Resource
    public ManyTableSelectMapper manySelect;
    @Resource
    public ManyTableDMLMapper manyDML;
    public CommonResult result;
    private CommonResult before(Map<String, Object> param){
        return null;
    }
    private CommonResult after(Map<String, Object> param){
        return null;
    }
    public CommonResult doService(Map<String, Object> param){
        before(param);
        result = process(param);
        after(param);
        return result;
    }
    protected abstract CommonResult process(Map<String, Object> param);
}
