package com.sansui.dao;

import com.sansui.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 2:32
 * @modified By  西西里_SanSui in 2021/5/10 2:32
 * @description AddDescriptionHere
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection connection, String name) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if (connection != null){
            String sql = "select name,password from user where name=?";
            Object[] params = {name};
            try {
                rs = BaseDao.execute(connection,sql,params,rs,pstm);
                if(rs.next()){
                    user = new User();
//                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
//                    user.setEmail(rs.getString("email"));
//                    user.setProvince(rs.getString("province"));
//                    user.setCity(rs.getString("city"));
//                    user.setHobby(rs.getString("hobby"));
                }
                BaseDao.closeResource(null,pstm,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public List<User> queryAll(Connection connection) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        List list = new ArrayList<>();
        if (connection != null){
            String sql = "select id,name,password,email,province,city,hobby from user ";
            try {
                rs = BaseDao.query(connection,sql,rs,pstm);
                System.out.println("rs--->"+rs);
                int count = rs.getMetaData().getColumnCount();
                System.out.println("count-->"+count);
                ResultSetMetaData md = rs.getMetaData();
                while(rs.next()){
                    Map rowData = new HashMap();
                    for (int i = 1; i <= count; i++) {
                        rowData.put(md.getColumnName(i), rs.getObject(i));
                    }
                    list.add(rowData);
                    }
                //list.add(user);
                System.out.println("addlist--->"+list);
                BaseDao.closeResource(null,pstm,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int insertuser(Connection connection, User user) {
        PreparedStatement pstm = null;
        int count = 0;
        if (connection != null){
            String sql = "insert into user(name,password,email,province,city,hobby) value(?,?,?,?,?,?)";
            Object[] params = {user};
            try {
                count = BaseDao.executex(connection,sql,params,pstm);
                    user.setName(user.getName());
                    user.setPassword(user.getPassword());
                    user.setEmail(user.getEmail());
                    user.setProvince(user.getProvince());
                    user.setCity(user.getCity());
                    user.setHobby(user.getHobby());
                BaseDao.closeResource(null,pstm,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    @Override
    public int updateuser(Connection connection, User user) {
        PreparedStatement pstm = null;
        int count = 0;
        if (connection != null){
            String sql = "update user set name=?,password=?,email=?,province=?,city=?,hobby=?";
            Object[] params = {user};
            try {
                count = BaseDao.executex(connection,sql,params,pstm);
                user.setName(user.getName());
                user.setPassword(user.getPassword());
                user.setEmail(user.getEmail());
                user.setProvince(user.getProvince());
                user.setCity(user.getCity());
                user.setHobby(user.getHobby());
                BaseDao.closeResource(null,pstm,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}
