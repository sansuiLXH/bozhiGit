package com.sansui.service;

import com.sansui.entity.User;

import java.util.List;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 2:44
 * @modified By  西西里_SanSui in 2021/5/10 2:44
 * @description AddDescriptionHere
 */
public interface UserService {
    public User login(String name,String password);
    public List<User> queryAll();
    public int insert(User user);
}
