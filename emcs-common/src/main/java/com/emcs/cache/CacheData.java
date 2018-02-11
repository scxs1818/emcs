package com.emcs.cache;

/**
 * Created by Administrator on 2018/2/4.
 */
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.emcs.Constant.BusiConstant.*;
import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.util.CheckEmpty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 缓存常用数据库配置类 */
public class CacheData {
    CacheData(){}
    private Log log = LogFactory.getLog(CacheData.class);

    /** 缓存容器 ,私有化,防止外部直接操作,导致安全隐患 */
    private Map<String, Map<String, Object>> cacheMaps = new ConcurrentHashMap<String, Map<String, Object>>();

    /** 根据表名和行关键词获取缓存行信息--表名就是缓存对象名称 */
    public Map<String, Object> getCacheData(OneTableSelectMapper oneSelect,String tableName) {
        if (CheckEmpty.isEmpty(tableName)){
            log.warn("###缓存对象名称为空");
            return null;
        }

        /**从缓存容器获取缓存对象,若不存在该缓存对象,则根据缓存名称从数据库表里加载*/
        tableName = tableName.toLowerCase();
        Map<String, Object>  cacheMap= cacheMaps.get(tableName);
        if(CheckEmpty.isEmpty(cacheMap))
            cacheMap = loadCache(oneSelect,tableName);
        return cacheMap;
    }

    /**从数据加载缓存*/
    private Map<String, Object> loadCache(OneTableSelectMapper oneSelect,String tableName) {
        List<Map<String, Object>> list = loadtable(oneSelect,tableName);
        if(CheckEmpty.isEmpty(list))return null;
        Map<String,Object> cacheMap = new ConcurrentHashMap<String,Object>();
        for (Map<String, Object> tempData : list) {
            /**创建高并发容器存放缓存数据*/
            Map<String,Object> map = new ConcurrentHashMap<String,Object>();
            /**将临时数据放入缓存容器,并将临时数据置为null,便于垃圾回收*/
            map.putAll(tempData);
            tempData = null;
            if(CheckEmpty.isEmpty(map.get("cache_key1"))){
                cacheMap.put(tableName, map);
                break;
            }
            cacheMap.put(map.get("cache_key1")+ (CheckEmpty.isEmpty(map.get("cache_key2"))? "" : "|"+ map.get("cache_key2")), map.get("para_value"));
        }
        cacheMap.put(tableName, cacheMap);
        log.info("添加的缓存对象名称:[" + tableName + "],缓存对象的内容为:"+cacheMap);
        return cacheMap;
    }

    private List<Map<String,Object>> loadtable(OneTableSelectMapper oneSelect,String tableName) {
        List<Map<String,Object>> list = null;
        if(Cache.VA_VIRTUAL_ACCT_TYPE.vaue().equals(tableName)){
            list = oneSelect.selectVaVirtualAcctType(null);
        }else if(Cache.CM_BUSINESS_PARA.vaue().equals(tableName)){
            list = oneSelect.selectCmBusinessParaForCache(null);
        }else if(Cache.CM_SYSTEM.vaue().equals(tableName)){
            list = oneSelect.selectCmSystemForCache(null);
        }
        return list;
    }

    /** 清除指定缓存对象(注:该操作发生在缓存对象在数据库发生改变时,需清除老的缓存时使用) */
    public void clear(String tableName) {
        Map<String,Object> removeMap = cacheMaps.remove(tableName);
        if (!CheckEmpty.isEmpty(removeMap)) {
            removeMap = null;
            log.info("移除的缓存对象名称:[" + tableName + "]");
        }
    }

    /** 清除所有缓存对象 */
    public void clearAll() {
        Iterator<String> it = cacheMaps.keySet().iterator();
        while (it.hasNext()) {
            String tableName = it.next();
            Map<String,Object> removeMap = cacheMaps.remove(tableName);
            if (!CheckEmpty.isEmpty(removeMap)) {
                removeMap = null;
                log.info("移除的缓存对象名称:[" + tableName + "]");
            }
        }
    }

    /**修改缓存数据[tableName-缓存对象名称]*/
    public void addCacheData(String tableName,Map<String, Object> cacheMap) {
        if (CheckEmpty.isEmpty(tableName)){
            log.warn("缓存对象名称不能为空");
            return;
        }

        if (CheckEmpty.isEmpty(cacheMap)){
            log.warn("缓存对象数据为空");
            return;
        }

        tableName = tableName.toLowerCase();
        //获取缓存对象
        Map<String, Object> oldMap = cacheMaps.get(tableName);
        if(!CheckEmpty.isEmpty(oldMap)){
            cacheMaps.remove(tableName);//移除旧值
            oldMap=null;
            log.info("移除缓存对象:["+tableName+"]");
        }
        cacheMaps.put(tableName, cacheMap);//存放新值
    }
}