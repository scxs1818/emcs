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
    private static DbUtil dbUtil = null;
    private DbUtil(){}
    public  static DbUtil getInstance(){
        if(dbUtil==null){
            synchronized (DbUtil.class){
                if(dbUtil==null){
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }

    public Map<String,Object> selectVaCustVirtualAcctInfo(Map<String,Object> param){
        List<Map<String,Object>> virAccInfoList = oneSelect.selectVaCustVirtualAcctInfo(param);
        if(virAccInfoList!=null&&virAccInfoList.size()==0){
           return virAccInfoList.get(0);
        }
        return null;
    }
}
