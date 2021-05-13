package com.sansui.servlet;

import com.alibaba.fastjson.JSONObject;
import com.sansui.entity.Student;
import com.sansui.util.JedisDriver;
import com.sansui.util.MysqlDr;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 14:40
 * @modified By  西西里_SanSui in 2021/5/12 14:40
 * @description AddDescriptionHere
 */
public class AddRedis extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置上下文类型
        resp.setContentType("text/html;charset=utf-8");
        // 设置统一文档编码
        req.setCharacterEncoding("UTF-8");
        JedisDriver jd = new JedisDriver();
        Jedis jedis = jd.getJedisConnection();
        Student student = new Student();

        String id=req.getParameter("id");
        System.out.println("id---------->"+id);
        String name=req.getParameter("name");
        String birthday = req.getParameter("birthday");
        String description = req.getParameter("description");
        Double avgscore = Double.parseDouble(req.getParameter("avgscore"));
        if(id == null || name == null || birthday == null || description == null || avgscore == null){
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
        student.setId(id);
        student.setName(name);
        student.setBirthday(birthday);
        student.setDescription(description);
        student.setAvgscore(avgscore);
        String str7  = JSONObject.toJSONString(student);
         jedis.zadd("student",avgscore,str7);

                //保存成功，“跳转”到成功页面
                //转发:刷新多次前台页面(浏览器)后数据库表中会新增多个相同的记录
                //req.getRequestDispatcher("/success.html").forward(req, resp);
                //重定向:（在浏览器上刷新的最后一次的请求记录），刷新多次后，数据库表中仍然只会存在刚刚新增的一条记录；
                resp.sendRedirect(req.getContextPath()+"/redismaintest");



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
