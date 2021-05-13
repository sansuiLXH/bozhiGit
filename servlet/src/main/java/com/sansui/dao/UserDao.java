package com.sansui.dao;

import com.sansui.entity.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 2:31
 * @modified By  西西里_SanSui in 2021/5/10 2:31
 * @description AddDescriptionHere
 */
public interface UserDao {
    public User getLoginUser(Connection connection,String name);
    public List<User> queryAll(Connection connection);
    int insertuser(Connection connection,User user);
    int updateuser(Connection connection,User user);
}
