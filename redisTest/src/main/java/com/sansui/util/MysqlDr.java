package com.sansui.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 9:38
 * @modified By  西西里_SanSui in 2021/5/12 9:38
 * @description AddDescriptionHere
 */
public class MysqlDr {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static{
        Properties properties = new Properties();
        InputStream is = MysqlDr.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
    //获取数据库的连接
    public  Connection getConnection(){
            Connection connection = null;
            try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
