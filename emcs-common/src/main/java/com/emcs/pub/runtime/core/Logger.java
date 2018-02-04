package com.emcs.pub.runtime.core;

import com.emcs.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Logger {
    private org.apache.log4j.Logger logger= null;
    private static Map<String, org.apache.log4j.Logger> loggerCache = new ConcurrentHashMap();

    public Logger(Class<?> zClass) {
        String className = zClass.getName();
        logger = loggerCache.get(className);
        if(logger == null){
            logger = org.apache.log4j.Logger.getLogger(zClass);
        }
    }

    public void info( String message) {
        this.info(message, (Object[])null);
    }

    public void info(String message, Object[] params) {
        logger.info(StringUtil.format(message, params));
    }

    public void info(String message,Throwable t) {
        logger.info(message,t);
    }
    public void error(String message,Throwable t) {
        logger.error(message,t);
    }

}
