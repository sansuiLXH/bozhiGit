package com.sansui.servlet;

import com.sansui.util.MysqlDr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 10:28
 * @modified By  西西里_SanSui in 2021/5/12 10:28
 * @description AddDescriptionHere
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置上下文类型
        resp.setContentType("text/html;charset=utf-8");
        // 设置统一文档编码
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        MysqlDr mysqlDr = new MysqlDr();
        Connection conn = mysqlDr.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        int count = 0;
        String id=req.getParameter("id");
        System.out.println("id---------->"+id);
        String name=req.getParameter("name");
        String birthday=req.getParameter("birthday");
        String description=req.getParameter("description");
        Double avgscore=Double.parseDouble(req.getParameter("avgscore"));
        if(id == null || name == null || birthday == null || description == null || avgscore == null){
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }
        try {
            stmt = conn.createStatement();
            String sql = "insert into student (id,name,birthday,description,avgscore) values (?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, birthday);
            ps.setString(4, description);
            ps.setDouble(5, avgscore);

            count = ps.executeUpdate();
            System.out.println("count---------->"+count);
            //req.getRequestDispatcher("/StudentMain.jsp").forward(req, resp);

            if(count > 0){
                //保存成功，“跳转”到成功页面
                //转发:刷新多次前台页面(浏览器)后数据库表中会新增多个相同的记录
                //req.getRequestDispatcher("/success.html").forward(req, resp);
                //重定向:（在浏览器上刷新的最后一次的请求记录），刷新多次后，数据库表中仍然只会存在刚刚新增的一条记录；
                resp.sendRedirect(req.getContextPath()+"/maintest");
            }else if (count <= 0) {
                resp.sendRedirect(req.getContextPath()+"/error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
//                    rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
