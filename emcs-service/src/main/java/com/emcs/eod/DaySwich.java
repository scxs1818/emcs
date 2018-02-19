package com.emcs.eod;

import com.emcs.Constant.BaseEnum;
import com.emcs.Constant.BusiConstant;
import com.emcs.cache.CacheData;
import com.emcs.exception.BusiException;
import com.emcs.mapper.OneTableDMLMapper;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.pub.runtime.core.LoggerFactory;
import com.emcs.supers.PubService;
import com.emcs.supers.ServiceTransactionalY;
import com.emcs.supers.SuperTask;
import com.emcs.supers.SupperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 */
@Service
@Transactional
public class DaySwich extends SuperTask {
    @Autowired
    FromRecharBalToUsableBal transfer;
    protected Logger log = LoggerFactory.getLogger(DaySwich.class);
    public void process(Map<String, Object> data,OneTableSelectMapper oneSelect,OneTableDMLMapper oneDML) {
        String server_date = new SimpleDateFormat("YYYYMMdd").format(new Date());
        Map<String,Object> cacheMap = CacheData.getCacheObj(oneSelect,BusiConstant.Cache.CM_SYSTEM.val());
        String run_date = cacheMap.get("run_date")+"";
        try{
            if(run_date.compareTo(server_date)<0){
                cacheMap.put("ser_status_flag","N");
                CacheData.addCacheData(BusiConstant.CACHE_CM_SYSTEM,cacheMap);
                transfer.process(data);
                cacheMap.put("ser_status_flag","Y");
                CacheData.addCacheData(BusiConstant.CACHE_CM_SYSTEM,cacheMap);
                data.put("run_date",server_date);
                oneDML.updateProduceDay(data);
            }
        }catch (Exception e){
            throw new BusiException("日切过程异常",e);
        }finally {
            cacheMap.put("ser_status_flag","Y");
            CacheData.addCacheData(BusiConstant.CACHE_CM_SYSTEM,cacheMap);
        }
    }
}