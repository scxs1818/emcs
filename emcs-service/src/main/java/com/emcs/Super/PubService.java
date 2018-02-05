package com.emcs.Super;

import com.emcs.mapper.OneTableDMLMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public abstract class PubService {
    @Resource
    protected OneTableDMLMapper oneDML;
    protected abstract void process(Map<String, Object> param);
}
