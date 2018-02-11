package com.emcs.Script;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MakeInsertDatasScript {
    public static void main(String[] args) {
        String dir = SqlUtil.getDbScriptDir();
        File file = new File(dir+"/datas");
        File[] files = file.listFiles();
        RandomAccessFile raf1 = null,raf2 = null;
        StringBuilder sb = new StringBuilder();
        try {
            String str = null;
            for(File file3:files){
                raf1 = new RandomAccessFile(file3,"r");
                while ((str=raf1.readLine())!=null){
                    sb.append(str+"\n");
                }
                sb.append("\n");
            }
            File targe = new File(dir+"/total_datas.sql");
            targe.delete();
            targe.createNewFile();
            raf2 = new RandomAccessFile(targe,"rw");
            sb.append("COMMIT;");
            raf2.writeBytes(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(raf1!=null)
                    raf1.close();
                if(raf2!=null)
                    raf2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}