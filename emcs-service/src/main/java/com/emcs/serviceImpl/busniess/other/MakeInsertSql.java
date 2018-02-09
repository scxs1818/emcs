package com.emcs.serviceImpl.busniess.other;

import com.emcs.Super.PubService;
import com.emcs.Super.ServiceTransactionalN;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/2/9.
 */
@Service
public class MakeInsertSql extends PubService{
    private  Map<String,Map<String,Object> > cacheObject = null;
    @Override
    public void process(Map<String, Object> param) {

        AAA();
        BBB();
        CCC("");

    }

    private void CCC(String tableName) {
        StringBuilder sb1 = new StringBuilder(),sb2 = new StringBuilder();
        sb1.append("insert into "+tableName +" (");
        Map<String,Object> columnsMap = cacheObject.get(tableName);
        Iterator it = columnsMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry =  (Map.Entry)it.next();
            Object column= entry.getKey();
            Object type =entry.getValue();
            sb1.append(column+",");
            if("datetime".equals(type)||"date".equals(type)){
                sb2.append("str_to_date(#{"+column+"},'%Y%m%d'),");
            }else{
                sb2.append("#{"+column+"},");
            }
        }
        String str1=sb1.toString(),str2=sb2.toString();
        str1=str1.substring(0,str1.length()-1)+") values (";
        str2=str2.substring(0,str2.length()-1)+")";
        System.out.print(str1+str2);

    }

    private void BBB() {
        List<Object> tableNmaes = oneSelect.selectDbTables(null);
        tableNmaes.clear();
        tableNmaes.add("va_cust_info");
        cacheObject = new HashMap<>();
        for (Object tableName:tableNmaes){
            List<Map<String,Object>> rowList = oneSelect.selectDbTableColumns(tableName);
            Map<String,Object> columnPropertyMap = new HashMap<>();
            cacheObject.put(tableName+"",columnPropertyMap);
            for (Map<String,Object> rowMap:rowList){
                Object column = rowMap.get("column_name");
                Object type =  rowMap.get("data_type");
                columnPropertyMap.put(column+"",type);
            }
        }
    }

    private void AAA() {
        List<Object> tableNmaes = oneSelect.selectDbTables(null);
        tableNmaes.clear();
        tableNmaes.add("va_cust_info");
        for (Object tableName:tableNmaes){
            List<Map<String,Object>> rowList = oneSelect.selectDbTableColumns(tableName);
            StringBuilder sb1 = new StringBuilder(),sb2 = new StringBuilder();
            sb1.append("insert into "+tableName +" (");
            for (Map<String,Object> rowMap:rowList){
                Object column = rowMap.get("column_name");
                Object type =  rowMap.get("data_type");
                sb1.append(column+",");
                if("datetime".equals(type)||"date".equals(type)){
                    sb2.append("str_to_date(#{"+column+"},'%Y%m%d'),");
                }else{
                    sb2.append("#{"+column+"},");
                }
            }
            String str1=sb1.toString(),str2=sb2.toString();
            str1=str1.substring(0,str1.length()-1)+") values (";
            str2=str2.substring(0,str2.length()-1)+")";
            System.out.print(tableName+"="+str1+str2);
        }
    }
}
