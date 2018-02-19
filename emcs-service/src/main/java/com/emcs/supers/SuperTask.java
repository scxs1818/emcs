package com.emcs.supers;

import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/19.
 */
@Service
public abstract class SuperTask {
    protected Logger log = LoggerFactory.getLogger(PubService.class);
    public abstract void process(Map<String, Object> data, OneTableSelectMapper oneSelect, OneTableDMLMapper oneDML);
}
