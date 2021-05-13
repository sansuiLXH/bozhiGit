package com.sansui.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/9 21:22
 * @modified By  西西里_SanSui in 2021/5/9 21:22
 * @description AddDescriptionHere
 */
public class HelloServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // ServletOutputStream outputStream = resp.getOutputStream();
        System.out.println("进入doGet方法");
        //响应流
        PrintWriter writer = resp.getWriter();
        writer.println("hello test servlet");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
