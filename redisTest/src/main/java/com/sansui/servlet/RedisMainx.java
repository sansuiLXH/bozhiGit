package com.sansui.servlet;

import com.alibaba.fastjson.JSONObject;
import com.sansui.entity.Page;
import com.sansui.entity.Student;
import com.sansui.util.JedisDriver;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 23:36
 * @modified By  西西里_SanSui in 2021/5/12 23:36
 * @description AddDescriptionHere
 */
public class RedisMainx extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入方法-----");
        // 设置上下文类型
        resp.setContentType("text/html;charset=utf-8");
        // 设置统一文档编码
        req.setCharacterEncoding("UTF-8");
        JedisDriver jd = new JedisDriver();
        Jedis jedis = jd.getJedisConnection();
        List<Student> redislist = new ArrayList<>();
        Set<String> temp = jedis.zrevrange("student", 0, -1);
        for (String s : temp) {
            Student parse = JSONObject.parseObject(s, Student.class);
            redislist.add(parse);
            System.out.println(parse.toString());
        }
        System.out.println("redislist----->"+redislist);

        int showpage2 = 1;
        Page page =new Page();
            String temp2 = req.getParameter("showpage");
            if (temp2 != null && !temp2 .equals("")) {
                showpage2 = Integer.parseInt(temp2);
                System.out.println("csdn--->"+showpage2);
                if(showpage2 <= 0){
                    showpage2 = 1;
                }
            }
            System.out.println("提前进入");
            System.out.println("我的的测试得得得----->"+req.getParameter("showpage"));
            System.out.println("shoupage2--->"+showpage2);

            int pagecount = redislist.size();
            page.setCount(pagecount);
            if(pagecount < 10){
                page.setPagenum(1);
            }else {
            page.setPagenum(pagecount/10==0 ?pagecount/10 : pagecount/10 + 1);
            }
            page.setPagesize(10);
            System.out.println("cs------"+pagecount+"--"+page.getPagenum()+"---"+page.getPagesize());
            List<Student> list2 = new ArrayList<>();
            if(showpage2*10 <= pagecount){
                for(int i = (showpage2-1)*10;i < showpage2*10;i++) {
                    list2.add(redislist.get(i));
                }
            }else {
                for(int i = (showpage2-1)*10;i < pagecount;i++) {
                    list2.add(redislist.get(i));
                }
            }

            req.setAttribute("list2",list2);
            req.setAttribute("pagecount",page.getCount());
            req.setAttribute("showpage",showpage2);
            req.setAttribute("pagenum",page.getPagenum());
            req.setAttribute("list2",list2);
//            System.out.println("list2----->"+list2);
            System.out.println("size------->"+list2.size());
            req.getRequestDispatcher("/redisMain.jsp").forward(req, resp);




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
