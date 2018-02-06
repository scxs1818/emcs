package com.emcs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2018/2/6.
 */
public class MakeCreateTable {
    public static void main(String[] args) {
        File file =new File(Class.class.getClass().getResource("/").getPath());
        String dir = new File(file.getParent()).getParent()+"/dbScript";
        File file2 = new File(dir+"/tables");
        File[] files = file2.listFiles();
        RandomAccessFile raf = null;
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

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (raf!=null)
                    raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            File targe = new File(dir+"/total.sql");
            if(!targe.exists())targe.createNewFile();
            raf = new RandomAccessFile(new File(dir+"/total.sql"),"rw");
            raf.writeBytes(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (raf!=null)
                    raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}