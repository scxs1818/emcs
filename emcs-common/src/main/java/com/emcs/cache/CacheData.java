package com.emcs.cache;

/**
 * Created by Administrator on 2018/2/4.
 */
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.emcs.Constant.BusiConstant;
import com.emcs.exception.BusiException;
import com.emcs.mapper.OneTableSelectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 缓存常用数据库配置类 */
public class CacheData {
    CacheData(){}
    private Log log = LogFactory.getLog(CacheData.class);

    /** 获取应用的根目录 */
    private final String FILEDIR = System.getProperty("com.dc.branch.deploy_path")+ "/brconfig/cache/";

    /** 缓存sqlId配置文件名称 */
    private final String FIELNAME = "cache_sqlId.xml";

    /** 缓存容器 ,私有化,防止外部直接操作,导致安全隐患 */
    private Map<String, Map<String, Map<String, Object>>> cacheMapss = new ConcurrentHashMap<String, Map<String, Map<String, Object>>>();

    /** 存放sqlId */
    private Map<String, String> cachesql = null;

    /** 根据表名和行关键词获取缓存行信息--表名就是缓存对象名称 */
    public Map<String, Object> getCacheData(OneTableSelectMapper oneSelect,String tableName) {
        return getCacheObjectRow(getCacheObject(oneSelect,tableName), tableName, null);
    }

    /** 根据表名获取缓存信息--表名就是缓存对象名称 */
    public Map<String, Object> getCacheData(OneTableSelectMapper oneSelect,String tableName, String key) {
        return getCacheObjectRow(getCacheObject(oneSelect,tableName), tableName, key);
    }

    /**获取缓存对象*/
    private Map<String, Map<String, Object>> getCacheObject(OneTableSelectMapper oneSelect,String tableName) {
        if (isEmpty(tableName)){
            log.warn("###缓存对象名称为空");
            return null;
        }

        /**从缓存容器获取缓存对象,若不存在该缓存对象,则根据缓存名称从数据库表里加载*/
        tableName = tableName.toLowerCase();
        Map<String, Map<String, Object>>  cacheMaps= cacheMapss.get(tableName);
        if(isEmpty(cacheMaps)){
//            String sqlId = getSqlId(tableName);
//            cacheMaps = loadCache(tableName,sqlId);
             cacheMaps = loadCache(oneSelect,tableName,null);
//            if(isEmpty(cacheMaps)){
//                log.warn("缓存对象名称=["+tableName+"],sqlId=["+sqlId+"]加载缓存数据为空");
//                return null;
//            }
        }
        return cacheMaps;
    }


    /**获取缓存对象的某1行信息*/
    private Map<String, Object> getCacheObjectRow(Map<String, Map<String, Object>> cacheMaps,String tableName,String key) {
        if(isEmpty(cacheMaps))return null;

        String tmp = isEmpty(key)?tableName:key;
        if(isEmpty(cacheMaps.get(tmp))){
            log.warn("缓存对象名称为["+tableName+"],key值为["+tmp+"]的缓存值为空");
            return null;
        }

        /** 返回缓存对象副本,防止缓存数据被篡改 */
        Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
        retMap.putAll(cacheMaps.get(tmp));
        return retMap;
    }

    /**从数据加载缓存*/
    private Map<String, Map<String, Object>> loadCache(OneTableSelectMapper oneSelect,String tableName,String sqlId) {
        log.info("-----------dads----------tableName="+tableName);
        List<Map<String, Object>> list = loadtable(oneSelect,tableName);
        log.info("!!!!!!!!!!!!!list="+list);
        if(list==null||list.size()==0)return null;
        Map<String,Map<String, Object>> cacheMaps = new ConcurrentHashMap<String,Map<String,Object>>();
        for (Map<String, Object> tempData : list) {
            /**创建高并发容器存放缓存数据*/
            Map<String,Object> map = new ConcurrentHashMap<String,Object>();
            /**将临时数据放入缓存容器,并将临时数据置为null,便于垃圾回收*/
            map.putAll(tempData);
            tempData = null;
            if(isEmpty(map.get("cache_key1"))){
                cacheMaps.put(tableName, map);
                break;
            }
            cacheMaps.put(map.get("cache_key1")+ (isEmpty(map.get("cache_key2"))? "" : "|"+ map.get("cache_key2")), map);
        }
        cacheMapss.put(tableName, cacheMaps);
        log.info("添加的缓存对象名称:[" + tableName + "],缓存对象的内容为:"+cacheMaps);
        return cacheMaps;
    }

    private List<Map<String,Object>> loadtable(OneTableSelectMapper oneSelect,String tableName) {
        List<Map<String,Object>> list = null;
        if(BusiConstant.CACHE_VA_VIRTUAL_ACCT_TYPE.equals(tableName)){
            log.info("122-----------------");
            list = oneSelect.selectVaVirtualAcctType(null);
        }else if(BusiConstant.CACHE_CM_BUSINESS_PARA.equals(tableName)){
            log.info("123-----------------");
            list = oneSelect.selectCmBusinessParaForCache(null);
        }else if(BusiConstant.CACHE_CM_SYSTEM.equals(tableName)){
            log.info("124-----------------");
            list = oneSelect.selectCmSystemForCache(null);
        }
        return list;
    }

    /**重新加载缓存配置文件*/
    public void reParseCacheFile() {
        cachesql = null;
        parseCacheFile();
    }

    /** 解析缓存sqlId的配置,并将解析sqlId放入缓存 */
    private void parseCacheFile() {
        RandomAccessFile raf = null;
        cachesql = new ConcurrentHashMap<String, String>();
        try {
            raf = new RandomAccessFile(new File(FILEDIR, FIELNAME), "r");
            String line;
            while ((line = raf.readLine())!=null) {
                String[] arr = line.split("=");
                if (arr.length == 2)
                    cachesql.put(arr[0], arr[1]);
            }
            log.info("缓存了sqlId的信息为:" + cachesql);
        } catch (IOException e) {
            log.error("读取缓存配置文件[" + FILEDIR + FIELNAME + "]异常", e);
        } finally {
            try {
                if (raf != null)
                    raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** 根据缓存名称获取该缓存对象配置的sqlId */
    private String getSqlId(String tableName) {
        if (isEmpty(cachesql))
            parseCacheFile();

        tableName = tableName.toUpperCase();
        String sqlId = cachesql.get(tableName);
        if (isEmpty(sqlId))
            throw new BusiException("请检查是否配置了缓存对象[" + tableName + "]的sqlId","660001");
        return sqlId;
    }

    /** 清除指定缓存对象(注:该操作发生在缓存对象在数据库发生改变时,需清除老的缓存时使用) */
    public void clear(String tableName) {
        Map<String,Map<String, Object>> removeMap = cacheMapss.remove(tableName);
        if (!isEmpty(removeMap)) {
            removeMap = null;
            log.info("移除的缓存对象名称:[" + tableName + "]");
        }
    }

    /** 清除所有缓存对象 */
    public void clearAll() {
        Iterator<String> it = cacheMapss.keySet().iterator();
        while (it.hasNext()) {
            String tableName = it.next();
            Map<String,Map<String, Object>> removeMap = cacheMapss.remove(tableName);
            if (!isEmpty(removeMap)) {
                removeMap = null;
                log.info("移除的缓存对象名称:[" + tableName + "]");
            }
        }
    }

    /**修改缓存数据[tableName-缓存对象名称]*/
    public void addCacheData(String tableName,Map<String, Object> cacheMap) {
        addCacheData(tableName,null,cacheMap);
    }

    /**修改缓存数据[tableName-缓存对象名称,keyColumn-缓存对的key值]*/
    public void addCacheData(String tableName,String keyColumn,Map<String, Object> cacheMap) {
        if (isEmpty(tableName)){
            log.warn("缓存对象名称不能为空");
            return;
        }

        if (isEmpty(cacheMap)){
            log.warn("缓存对象数据为空");
            return;
        }

        tableName = tableName.toUpperCase();
        //获取缓存对象
        Map<String, Map<String, Object>> addtemps = cacheMapss.get(tableName);
        String key = isEmpty(keyColumn)?tableName:keyColumn;
        if(isEmpty(addtemps)){
            addtemps = new ConcurrentHashMap<String, Map<String,Object>>();
            addtemps.put(key, cacheMap);
        }else{
            Map<String, Object> addtemp = addtemps.remove(keyColumn);//移除旧值
            if(!isEmpty(addtemp)){
                addtemp=null;
                log.info("移除缓存对象:["+tableName+"]的key为:[" + key + "]的一行记录");
            }
            addtemps.put(key, cacheMap);//存放新值
        }

        cacheMapss.put(tableName, addtemps);//更新缓存
    }

    private boolean isEmpty(Object obj){
        return obj==null||"".equals(obj.toString().trim());
    }
    private boolean isEmpty(Map<String,?> map){
        return map==null||map.isEmpty();
    }
}
