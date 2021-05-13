package com.sansui.servlet;

import com.sansui.entity.User;
import com.sansui.service.UserServicex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/9 23:21
 * @modified By  西西里_SanSui in 2021/5/9 23:21
 * @description AddDescriptionHere
 */
@WebServlet(urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        System.out.println("remember---->"+remember);
        String code = request.getParameter("verCode");
        System.out.println("code---->"+code);
        //2.获取会话域中的验证码
        HttpSession session = request.getSession();
        String encode = (String) session.getAttribute("verCode");
        System.out.println("encode----------->"+encode);
        //3.判断验证码是否通过，记住密码的，或者是输入验证码正确的
        System.out.println("ss------------->"+code.equals(encode));
        User user = UserServicex.selectUser(name);

        if (code.equals(encode) || remember.equals("remember")) {
            System.out.println("通过验证码");
            //4.验证码输入正确,判断输入的信息是否正确
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                //6.输入的信息正确，查看是否需要记住账号和密码
                if (remember != null) {
                    //8.需要记住账号和密码实现自动登录，创建cookie
                    Cookie uCookie = new Cookie("name", name);
                    uCookie.setMaxAge(60*60*24*30);
                    response.addCookie(uCookie);

                    Cookie pCookie = new Cookie("password", password);
                    pCookie.setMaxAge(60*60*24*30);
                    response.addCookie(pCookie);

                    Cookie codeCookie = new Cookie("verCode", "remember");
                    codeCookie.setMaxAge(60*60*24*30);
                    response.addCookie(codeCookie);
                }
                //9.把登录后的信息存储在会话域中，方面访问别的页面使用
                session.setAttribute("name", name);
                //10.跳转到index页面
                response.sendRedirect(request.getContextPath()+"/success.html");
            } else {
                //7.输入的信息错误，跳回到原页面，需要重新输入
                request.getRequestDispatcher("/login21.html").forward(request, response);
            }
        } else {
            //5.验证码输入不正确，需要重新输入
            request.getRequestDispatcher("/login21.html").forward(request, response);
        }
    }
}

