package com.emcs.tool;

import com.emcs.mapper.ManyTableDMLMapper;
import com.emcs.mapper.ManyTableSelectMapper;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/2/6.
 */
public class DataBaseOperate {
    @Resource
    public OneTableDMLMapper oneDML;
    @Resource
    public OneTableSelectMapper oneSelect;
    @Resource
    public ManyTableDMLMapper manyDML;
    @Resource
    public ManyTableSelectMapper manySelect;
    private DataBaseOperate(){}
    private static DataBaseOperate dbo = null;
    public static DataBaseOperate getDbo(){
        if(dbo==null){
            synchronized (DataBaseOperate.class){
                if(dbo==null){
                    dbo = new DataBaseOperate();
                }
            }
        }
        return dbo;
    }
}
