package com.emcs.busniess.test;

import com.emcs.Constant.BusiConstant;
import com.emcs.busniess.common.TestSevice;
import com.emcs.cache.CacheData;
import com.emcs.pub.runtime.core.Logger;
import com.emcs.supers.PubServiceY;
import com.emcs.tool.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/11.
 */
@Service
public class TestCache extends PubServiceY {
    @Override
    public void process(Map<String, Object> param){
        log.info("%%%%%%%%%%");
//       Map map =  CacheData.getCacheObj(oneSelect, BusiConstant.CACHE_CM_SYSTEM);
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        String classBean = "com.emcs.busniess.common.TestSevice";
        Class sl = null;
        try {
            sl = Class.forName(classBean);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("%%%%%%%%%%%%%=");
        Object obj = null;
        try {
            obj = sl.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ((TestSevice)obj).process();

//        Method method = ReflectionUtils.findMethod(clazz,"process");
//        log.info("***********="+method.getName());
//        ReflectionUtils.invokeMethod(method,wac.getBean(classBean));
        log.info("%%%%%%%%%%2");
//        log.info("############"+map);
    }
}
