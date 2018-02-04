package com.emcs;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2018/2/4.
 */
public class DoSql {
    public static void main (String[] args){
        doSql();
    }
    private static void doSql() {
        try {
            RandomAccessFile raf = new RandomAccessFile(new File("C:/Users/Administrator/Desktop/dosql.sql"), "r");
            String str = null;
            int i=0;
            StringBuilder sb1 = new StringBuilder();
            sb1.append("insert into ");
            StringBuilder sb2 = new StringBuilder();
            while((str=raf.readLine())!=null){
                String []arr = str.split("`");
                if(arr.length==1)break;
                if(i>1){
                    if(arr[1].toLowerCase().contains("primary"))break;
                    sb1.append(arr[1]+",");
                    sb2.append("#{"+arr[1]+"},");
                }else{
                    if(i==0){
                        sb1.append(arr[1]+"( ");
                    }
                }
                i++;
            }
            String str1 = sb1.toString(),str2 = sb2.toString();
            str1 = str1.substring(0,str1.length()-1);
            str1+=" ) values ( ";
            str2 = str2.substring(0,str2.length()-1);
            str2+=")";
            System.out.println(str1+str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}