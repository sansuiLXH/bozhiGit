package com.sansui.servlet;

import com.sansui.entity.Student;
import com.sansui.util.MysqlDr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 9:50
 * @modified By  西西里_SanSui in 2021/5/12 9:50
 * @description AddDescriptionHere
 */
public class MainServlet extends HttpServlet {
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
        Student student = null;
        List<Student> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            String sql = "select * from student order by avgscore desc limit 0,10";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
            student = new Student();
            student.setId(rs.getString("id"));
            student.setName(rs.getString("name"));
            student.setBirthday(rs.getString("birthday"));
            student.setDescription(rs.getString("description"));
            student.setAvgscore(rs.getDouble("avgscore"));
            list.add(student);
            }
            req.setAttribute("list",list);
            System.out.println("list----->"+list);
            req.getRequestDispatcher("/StudentMain.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
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
