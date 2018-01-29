package com.emcs.pub.runtime.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by edianzu on 2018/1/24.
 */
public class LoggerFactory {
    private static Map<String, Logger> loggerCache = new ConcurrentHashMap();

    public static  Logger getLogger(Class<?> zClass){
        String className = zClass.getName();
        Logger logger = loggerCache.get(className);
        if(logger == null){
            logger = new Logger(zClass);
            loggerCache.put(className,logger);
        }
        return logger;
    }

}
