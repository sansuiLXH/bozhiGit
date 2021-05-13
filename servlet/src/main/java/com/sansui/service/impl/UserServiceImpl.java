package com.sansui.service.impl;


import com.sansui.dao.BaseDao;
import com.sansui.dao.UserDao;
import com.sansui.dao.UserDaoImpl;
import com.sansui.entity.User;
import com.sansui.service.UserService;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 2:45
 * @modified By  西西里_SanSui in 2021/5/10 2:45
 * @description AddDescriptionHere
 */

public class UserServiceImpl implements UserService {


     UserDao userDao;
     public UserServiceImpl(){
         userDao = new UserDaoImpl();
     }

    Connection connection = null;
    @Override
    public User login(String name, String password) {

        User user = null;
        connection = BaseDao.getConnection();
        user = userDao.getLoginUser(connection,name);
        BaseDao.closeResource(connection,null,null);
        return user;
    }

    @Override
    public List<User> queryAll() {
        connection = BaseDao.getConnection();
        List<User> list = null;
        list = userDao.queryAll(connection);
        System.out.println("service---->"+list);
        BaseDao.closeResource(connection,null,null);
        return list;
    }

    @Override
    public int insert(User user) {
        connection = BaseDao.getConnection();
        int count = userDao.insertuser(connection,user);
        BaseDao.closeResource(connection,null,null);
        return count;
    }

//        //测试插入
//        @Test
//        public void test(){
//        System.out.println("进入----");
//        UserService userService = new UserServiceImpl();
//        User user = new User("nihao","12345678Bb","67@gmail.com","四川","绵阳","看书，唱歌");
//        System.out.println("user====>"+user);
//       int count = userService.insert(user);
//            System.out.println("count--->"+userService.insert(user));
//    }

    //测试queryAll()
//    @Test
//    public void test(){
//        System.out.println("进入----");
//        UserService userService = new UserServiceImpl();
//        List<User> list = userService.queryAll();
//        System.out.println("list====>"+list);
////        for(User user : list){
////            System.out.println("user---->"+user);
////        }
//    }

}
