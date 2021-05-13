package com.sansui.service;

import com.sansui.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 1:23
 * @modified By  西西里_SanSui in 2021/5/10 1:23
 * @description AddDescriptionHere
 */
public class UserServicex {
    public static User selectUser(String name){
        Connection conn=UserDao.getConnection();
            String sql="select name,password from user where name = ?";
            ResultSet rst=null;
            User user = new User();
            try{
            PreparedStatement prst=conn.prepareStatement(sql);
            prst.setString(1, name);
            rst=prst.executeQuery();
            System.out.println("----->"+prst.executeQuery());
            System.out.println("userService---->"+rst);
            System.out.println("userService---->"+rst.next());
            System.out.println(rst.getString("name"));
            user.setName(rst.getString("name"));
            user.setPassword(rst.getString("password"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }


}
