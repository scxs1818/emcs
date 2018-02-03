package com.emcs.tool;

import com.emcs.mapper.OneTableSelectMapper;
import com.emcs.util.CommonResult;

/**
 * Created by Administrator on 2018/2/3.
 */
public class ServiceUtil {

    public static String getSeqNo(OneTableSelectMapper selectOne, String seqName,int length){
        String seqVal = selectOne.getNextVal(seqName)+"";
        int len = seqVal.length();
        if(len<length){
            for(int i=0;i<length-len;i++)
                seqVal = 0+seqVal;
        }
        return seqVal;
    }
}
