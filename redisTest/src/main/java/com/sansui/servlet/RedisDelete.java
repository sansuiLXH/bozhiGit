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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Set;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 23:37
 * @modified By  西西里_SanSui in 2021/5/12 23:37
 * @description AddDescriptionHere
 */
public class RedisDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置上下文类型
        resp.setContentType("text/html;charset=utf-8");
        // 设置统一文档编码
        req.setCharacterEncoding("UTF-8");
        JedisDriver jd = new JedisDriver();
        Jedis jedis = jd.getJedisConnection();
        String id = req.getParameter("id");
        Set<String> temp = jedis.zrevrange("student", 0, -1);
        for (String s : temp) {
            Student parse = JSONObject.parseObject(s, Student.class);
            System.out.println("进入了");
            if(id.equals(parse.getId()) ){
                String deletestr = JSONObject.toJSONString(parse);
                System.out.println("开始移除");
                jedis.zrem("student",deletestr);
            }
        }

        resp.sendRedirect(req.getContextPath()+"/redismaintest");

        }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
