package com.emcs.supers;

import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
public abstract class PubService {
    protected Logger log = LoggerFactory.getLogger(PubService.class);
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    protected OneTableSelectMapper oneSelect;
    @Resource
    protected ManyTableSelectMapper manySelect;
    public abstract void process(Map<String, Object> data);
}
