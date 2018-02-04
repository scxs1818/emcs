package com.emcs.cache;

/**
 * Created by Administrator on 2018/2/4.
 */
public class CacheUtil {
    private static CacheData cd;
    private CacheUtil(){}

    public static CacheData getInstance(){
        if(cd==null){
            synchronized (CacheUtil.class){
                if(cd==null)cd=new CacheData();
            }
        }
        return cd;
    }
}
