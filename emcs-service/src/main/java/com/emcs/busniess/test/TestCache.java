package com.emcs.busniess.test;

import com.emcs.Constant.BusiConstant;
import com.emcs.cache.CacheData;
import com.emcs.supers.PubService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/11.
 */
@Service
public class TestCache extends PubService {

    @Override
    public void process(Map<String, Object> param) {
        log.info("%%%%%%%%%%");
       Map map =  CacheData.getCacheObj(oneSelect, BusiConstant.CACHE_CM_SYSTEM);
        log.info("############"+map);
    }
}
