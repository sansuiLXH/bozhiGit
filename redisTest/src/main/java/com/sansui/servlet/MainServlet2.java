package com.sansui.servlet;

import com.sansui.entity.Page;
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

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 15:41
 * @modified By  西西里_SanSui in 2021/5/12 15:41
 * @description AddDescriptionHere
 */
public class MainServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入方法-----");
        // 设置上下文类型
        resp.setContentType("text/html;charset=utf-8");
        // 设置统一文档编码
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        MysqlDr mysqlDr = new MysqlDr();
        Connection conn = mysqlDr.getConnection();
        int showpage2 = 1;

        Statement stmt = null;
        ResultSet rs = null;
        Student student = null;
        Page page =new Page();
        List<Student> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            //id,name,birthday,description,avgscore,count(*) countx
            String sql = "select * from student order by avgscore desc";
            rs = stmt.executeQuery(sql);
            System.out.println("13s进入--");
            while(rs.next()) {
                student = new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getString("birthday"));
                student.setDescription(rs.getString("description"));
                student.setAvgscore(rs.getDouble("avgscore"));
                list.add(student);
            }
            System.out.println("s13离开---");
//            System.out.println("list------>"+list);
            System.out.println("listsize----->"+list.size());
            System.out.println("11111我的的测试得得得----->"+req.getParameter("showpage"));
            String temp = req.getParameter("showpage");
            if (temp != null && !temp .equals("")) {
                showpage2 = Integer.parseInt(temp);
                System.out.println("csdn--->"+showpage2);
                if(showpage2 <= 0){
                    showpage2 = 1;
                }
            }
//                showpage2 = req.getParameter("showpage") == null ? 1 : Integer.valueOf(req.getParameter("showpage")).intValue() ;
            System.out.println("提前进入");
            System.out.println("我的的测试得得得----->"+req.getParameter("showpage"));
           // System.out.println("showpage--=========?>"+Integer.valueOf(req.getParameter("showpage")));
          //  int showpage2 = 1;
            System.out.println("shoupage2--->"+showpage2);
//            if(showpage2 == 0){
//                showpage2 = 1;
//            }
            int pagecount = list.size();
            page.setCount(pagecount);
            page.setPagenum(pagecount/10==0 ?pagecount/10 : pagecount/10 + 1);
            page.setPagesize(10);
            System.out.println("cs------"+pagecount+"--"+page.getPagenum()+"---"+page.getPagesize());
            List<Student> list2 = new ArrayList<>();
            if(showpage2*10 <= pagecount){
            for(int i = (showpage2-1)*10;i < showpage2*10;i++) {
                list2.add(list.get(i));
            }
            }else {
                for(int i = (showpage2-1)*10;i < pagecount;i++) {
                    list2.add(list.get(i));
                }
            }

            req.setAttribute("list2",list2);
            req.setAttribute("pagecount",page.getCount());
            req.setAttribute("showpage",showpage2);
            req.setAttribute("pagenum",page.getPagenum());
            req.setAttribute("list2",list2);
//            System.out.println("list2----->"+list2);
            System.out.println("size------->"+list2.size());
            req.getRequestDispatcher("/StudentMain.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {

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
