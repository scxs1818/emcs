package com.emcs.util;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {
    /**
     * 自动补齐序列位数
     * @param length
     * @param num
     * @return
     */
    public static String getSeq(int length,String num){
        if(StringUtils.isEmpty(num)){
            num="";
        }
        StringBuffer sb = new StringBuffer(num);
        if(length>num.length()){
            for(int i=0;i<length-num.length();i++){
                num+="0"+num;
            }
        }
        return num;
    }
}
