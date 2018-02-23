package com.emcs.Script;

import com.emcs.busniess.common.TestSevice;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/19.
 */
public class Test {
    public static void main(String[] args) {
        try {
//            Class cls = Class.forName("com.emcs.busniess.common.TestSevice");
//            ((TestSevice)cls.newInstance()).process();
//            System.out.print(cls+"\n");
//            System.out.print(SqlUtil.class);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            System.out.print(sdf.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
