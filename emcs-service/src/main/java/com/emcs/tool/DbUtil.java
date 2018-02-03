package com.emcs.tool;

import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/3.
 */
public class DbUtil {
    @Resource
    OneTableDMLMapper oneDML;
    @Resource
    OneTableSelectMapper oneSelect;
    @Resource
    ManyTableDMLMapper manyDML;
    @Resource
    ManyTableSelectMapper manySelect;
}
