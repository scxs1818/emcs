package com.emcs.Script;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.RandomAccessFile;

public class MakeInsertSqls {
    public static void main(String[] args) {
        String dir = SqlUtil.getDbScriptDir()+"/tables/CM_ACCT_TRAN_SEQ_账务流水表.sql";
        RandomAccessFile raf = null,raf2 = null;
        StringBuilder sb1 = new StringBuilder(),sb2 = new StringBuilder();
        try {
            String str = null;
                raf = new RandomAccessFile(dir,"r");
                while ((str=raf.readLine())!=null){
                    if(str.startsWith("drop")||str.startsWith("create")||str.startsWith(")")){
                        if(str.startsWith("drop"))
                            sb1.append("insert into "+str.split("`")[1]+"(");
                    }else{
                        if(str.contains("primary"))break;
                        String arr = str.split("`")[1];
                        sb1.append(arr+",");
                        sb2.append("#{"+arr+"},");
                    }
                }
            String str1 = sb1.toString(),str2=sb2.toString();
            str1 = str1.substring(0,str1.length()-1)+") values (";
                    str2=str2.substring(0,str2.length()-1)+")";
            System.out.print(str1+str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}