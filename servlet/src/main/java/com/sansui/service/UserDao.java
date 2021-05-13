package com.sansui.service;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 1:23
 * @modified By  西西里_SanSui in 2021/5/10 1:23
 * @description AddDescriptionHere
 */
public class UserDao {
    public static Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");		//加载驱动
            String url="jdbc:mysql://localhost:3306/bozhi";
            String username="root";
            String password="123456";
            //创建数据库连接
            conn= DriverManager.getConnection(url,username,password);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return conn;
    }


}
