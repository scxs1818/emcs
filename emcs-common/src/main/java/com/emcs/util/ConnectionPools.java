package com.emcs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by edianzu on 2018/1/22.
 */
public class ConnectionPools {
    private Properties pro = new Properties();
    private List<Connection> connectionList = new ArrayList<Connection>();
    private static Integer CURRENTNUM = 0;
    private static Integer MAXNUM = 20;

    /**
     * get Coneection from ConnectionPools
     * @return
     */
    public Connection getConnection(){
        Connection con = null;
        if(connectionList.size() != 0){
            con = connectionList.get(0);
            MAXNUM--;
            return con;
        }

        try{
            pro.load(this.getClass().getResourceAsStream("/source.properties"));
            Class.forName(pro.getProperty("diver"));
            con = DriverManager.getConnection(pro.getProperty("url"),pro.getProperty("username"),pro.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

    /**
     * return back connection to ConnectionPools
     * @param con
     */
    public void closeConnection(Connection con){
        try{
            if(con != null){
                connectionList.add(con);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
