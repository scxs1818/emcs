package com.emcs.exception;

/**
 * Created by Administrator on 2018/2/4.
 */
public class DoException {
    public static void doThrowException(Exception e){
        if(e instanceof BusiException)
            throw (BusiException)e;
        throw new BusiException("交易失败","999999");
    }
}