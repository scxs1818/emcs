package com.emcs.Script;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.RandomAccessFile;
@Service
public class MakeInsertSqls {
    public static void main(String[] args) {
        String dir = SqlUtil.getDbScriptDir()+"/tables/VA_CUST_ACCT_INFO_会员信息表.sql";
        RandomAccessFile raf = null,raf2 = null;
        StringBuilder sb1 = new StringBuilder(),sb2 = new StringBuilder();
        try {
            String str = null;
                raf = new RandomAccessFile(dir,"r");
                while ((str=raf.readLine())!=null){
                    if(str.startsWith("DROP")||str.startsWith("CREATE")||str.startsWith(")")){
                        if(str.startsWith("DROP"))
                            sb1.append("insert into "+str.split("`")[1]+"(");
                    }else{
                        if(str.contains("PRIMARY"))break;
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