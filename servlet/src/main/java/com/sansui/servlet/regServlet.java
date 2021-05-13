package com.sansui.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/9 22:33
 * @modified By  西西里_SanSui in 2021/5/9 22:33
 * @description AddDescriptionHere
 */
@WebServlet("/reg")
public class regServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bozhi";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    PreparedStatement ps=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Connection conn = null;
            Statement stmt = null;
            // 设置响应内容类型
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String province=req.getParameter("province");
        String city=req.getParameter("city");
        //String hobby=req.getParameter("hobby");
        String xingqu = "";
        String[] hobby = req.getParameterValues("ch");
        for(int i = 0 ; i < hobby.length ; i ++ ){
            xingqu += hobby[i] + " " ;
        }
        System.out.println("hobby----->"+hobby);
        System.out.println("xinqu----->"+xingqu);
        int count = 0;


            PrintWriter out = resp.getWriter();
            try {
                // 注册 JDBC 驱动器
                Class.forName(JDBC_DRIVER);

                // 打开一个连接
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                // 执行 SQL 查询
                stmt = conn.createStatement();
                String sql;
                sql = "insert into user(name, password, email, province, city, hobby) VALUES (?,?,?,?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setString(4, province);
                ps.setString(5, city);
                ps.setString(6, xingqu);

                count=ps.executeUpdate();
            } catch (SQLException se) {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception e) {
                // 处理 Class.forName 错误
                e.printStackTrace();
            } finally {
                // 最后是用于关闭资源的块
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException se2) {
                }
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        if(count==1){
            //保存成功，“跳转”到成功页面
            //转发:刷新多次前台页面(浏览器)后数据库表中会新增多个相同的记录
            //req.getRequestDispatcher("/success.html").forward(req, resp);
            //重定向:（在浏览器上刷新的最后一次的请求记录），刷新多次后，数据库表中仍然只会存在刚刚新增的一条记录；
            resp.sendRedirect(req.getContextPath()+"/success.html");
        }else if (count==2) {
            resp.sendRedirect(req.getContextPath()+"/login22.html");
        }

        }



        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}