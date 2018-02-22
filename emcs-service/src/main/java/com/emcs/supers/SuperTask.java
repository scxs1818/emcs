package com.emcs.supers;

import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/19.
 */
@Transactional(propagation=Propagation.REQUIRES_NEW)
public abstract class SuperTask {
    @Resource
    protected OneTableSelectMapper oneS;
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    protected ManyTableSelectMapper manyS;
    @Resource
    protected ManyTableDMLMapper manyDML;

    protected Logger log = LoggerFactory.getLogger(SuperTask.class);
    public void process(Map<String, Object> data, OneTableSelectMapper oneS, OneTableDMLMapper oneDML, ManyTableSelectMapper manySelect, ManyTableDMLMapper manyDML){}
    public  void process(Map<String, Object> data, OneTableSelectMapper oneS, OneTableDMLMapper oneDML){};
    public void process(Map<String, Object> data){}
}
