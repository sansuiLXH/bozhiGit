package com.sansui.servlet;

import com.sansui.entity.User;
import com.sansui.service.UserService;
import com.sansui.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 2:59
 * @modified By  西西里_SanSui in 2021/5/10 2:59
 * @description AddDescriptionHere
 */
public class LoginServlet2 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入loginServlet----");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String verCode = req.getParameter("verCode");
        String remember = req.getParameter("remember");
        HttpSession session = req.getSession();
        String encode = (String) session.getAttribute("verCode");

        //调用业务层
        UserService userService = new UserServiceImpl();
        if(verCode.equals(encode) || remember.equals("remember")){
        User loginUser = userService.login(name,password);
        if(loginUser != null) {
            if (name.equals(loginUser.getName()) && password.equals(loginUser.getPassword())) {
                //6.输入的信息正确，查看是否需要记住账号和密码
                if (remember != null) {
                    //8.需要记住账号和密码实现自动登录，创建cookie
                    Cookie uCookie = new Cookie("name", name);
                    uCookie.setMaxAge(60 * 60 * 24 * 30);
                    resp.addCookie(uCookie);

                    Cookie pCookie = new Cookie("password", password);
                    pCookie.setMaxAge(60 * 60 * 24 * 30);
                    resp.addCookie(pCookie);

                    Cookie codeCookie = new Cookie("verCode", "remember");
                    codeCookie.setMaxAge(60 * 60 * 24 * 30);
                    resp.addCookie(codeCookie);
                }
                req.getSession().setAttribute("loginuser", loginUser);
                //9.把登录后的信息存储在会话域中，方面访问别的页面使用
                session.setAttribute("name", name);
                //10.跳转到index页面
                resp.sendRedirect("/s1/userlist.jsp");
            }

        }

            //跳转到主页面

        }else{
            req.setAttribute("error","用户名或密码不正确");
            resp.sendRedirect("/s1/login21.html");
//            req.getRequestDispatcher("/s1/login21.html").forward(req,resp);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
