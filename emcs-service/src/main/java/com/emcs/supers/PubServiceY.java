package com.emcs.supers;

import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 公共service,事务执行
 * Created by Administrator on 2018/2/5.
 */
public abstract class PubServiceY {
    protected Logger log = LoggerFactory.getLogger(PubServiceY.class);
    @Resource
    protected OneTableDMLMapper oneDML;
    @Resource
    protected OneTableSelectMapper oneSelect;
    @Resource
    protected ManyTableSelectMapper manySelect;
    @Transactional
    public abstract void process(Map<String, Object> data);
}
