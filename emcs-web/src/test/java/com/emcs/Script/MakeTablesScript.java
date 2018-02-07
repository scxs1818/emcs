package com.emcs.Script;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MakeTablesScript {
    public static void main(String[] args) {
        String dir = SqlUtil.getDbScriptDir();
        File file = new File(dir+"/tables");
        File[] files = file.listFiles();
        RandomAccessFile raf = null,raf2 = null;
        StringBuilder sb = new StringBuilder();
        try {
            String str = null;
            for(File file3:files){
                raf = new RandomAccessFile(file3,"r");
                while ((str=raf.readLine())!=null){
                    sb.append(str+"\n");
                }
                sb.append("\n");
            }
            File targe = new File(dir+"/total.sql");
            if(!targe.exists())targe.createNewFile();
            raf2 = new RandomAccessFile(new File(dir+"/total.sql"),"rw");
            raf2.writeBytes(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
         try {
             if(raf!=null)
                raf.close();
             if(raf2!=null)
                raf2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
