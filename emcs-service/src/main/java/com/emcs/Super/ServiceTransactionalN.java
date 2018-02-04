package com.emcs.Super;

import com.emcs.exception.BusiException;
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
public abstract class ServiceTransactionalN extends ServiceSupper{
    protected CommonResult before(Map<String, Object> param){
        return null;
    }
    protected CommonResult after(Map<String, Object> param){
        return null;
    }
    protected abstract void process(Map<String, Object> param);
}
