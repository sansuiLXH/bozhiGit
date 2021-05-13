package com.sansui.servlet;

import com.alibaba.fastjson.JSON;
import com.sansui.entity.User;
import com.sansui.service.UserService;
import com.sansui.service.impl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 4:16
 * @modified By  西西里_SanSui in 2021/5/10 4:16
 * @description AddDescriptionHere
 */

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入list页面----------");
        List list2 = new ArrayList();
        UserService userService = new UserServiceImpl();
        List<User> list = userService.queryAll();
        System.out.println("servletlist---------->"+list);
        JSONArray  jsonArray = JSONArray.fromObject(list);
        System.out.println("新一轮转换----->"+jsonArray);
        System.out.println(jsonArray.size());
        int sh = jsonArray.size();
        User user = new User();
        for(int i = 0;i < sh;i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            user.setId(jsonObject.getInt("id"));
            user.setName(jsonObject.getString("name"));
            user.setPassword(jsonObject.getString("password"));
            user.setEmail(jsonObject.getString("email"));
            user.setProvince(jsonObject.getString("province"));
            user.setCity(jsonObject.getString("city"));
            user.setHobby(jsonObject.getString("hobby"));
            System.out.println("测试user-------->"+user);
            list2.add(user);
        }
        System.out.println("测试-------->"+list2);
        //JSONObject jsonObject = new JSONObject();
       // jsonObject.accum
       // List<User> objlist = JSON.parseArray(list,User.class);
//        for(User user1 : list){
//            System.out.println("user---->"+user1);
//            // 将list中的数据转成json字符串
//            String jsonObject= JSON.toJSONString(user1);
//            //将json转成需要的对象
//            User user= JSONObject.parseObject(jsonObject,User.class);
//            System.out.println("json------>"+user);
//            list2.add(user);
//        }
        //String object = JSON.toJSONString(list);


        req.setAttribute("list2",list2);
        System.out.println("进入跳转等待");
//        resp.sendRedirect(req.getContextPath()+"/userlist.jsp");
        req.getRequestDispatcher("/userlist.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
