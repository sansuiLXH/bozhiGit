<%--
  Created by IntelliJ IDEA.
  User: 西西里_SanSui
  Date: 2021/5/12
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.sansui.servlet.MainServlet"%>
<%@page import="com.sansui.servlet.MainServlet2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@page import="java.util.List"%>
<%@page import="com.sansui.entity.Student"%>
<%@page import="com.sansui.entity.Page"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>student list</title>
</head>
<body>
<div> <a href="${pageContext.request.contextPath}/redisAdd.jsp">新增</a> </div>
<h1  align="center">查询所有</h1>
<table border="1" width="1000"  align="center">
    <div>
        <tr>
            <th>用户ID</th>
            <th>姓名</th>
            <th>出生日期</th>
            <th>备注</th>
            <th>平均分</th>
            <th>操作</th>
            <%--        <td>删除</td>--%>
            <%--        <td>修改</td>--%>
        </tr>
            <%

        List<Student> pages=(List<Student>)request.getAttribute("list2");
//        int pagecount = 13;
//        int showpage = 1;
//        int pagenum = (int)request.getAttribute("pagenum");
//        int pagecount = (Integer) request.getAttribute("pagecount");
        int showpage = (int)request.getAttribute("showpage");
        int pagenum = (int)request.getAttribute("pagenum");
        for(Student stu:pages)
        {
    %>
        <tr >
            <td ><%=stu.getId() %></td>
            <td><%=stu.getName() %></td>
            <td><%=stu.getBirthday() %></td>
            <td><%=stu.getDescription() %></td>
            <td><%=stu.getAvgscore() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/redisdeletetest?id=<%=stu.getId()%>">删除</a> |
                <a href="${pageContext.request.contextPath}/redisUpdate.jsp?id=<%=stu.getId()%>">修改</a>
            </td>
        </tr>
            <%  }
    %>
</table>
</div>
<div align="center">

    第 <%=showpage%> 页（共 <%=pagenum%>页）

    <a href="${pageContext.request.contextPath}/redismaintest?showpage=1">首页</a>
    <a href="${pageContext.request.contextPath}/redismaintest?showpage=<%=showpage-1%>">上一页</a>
    <%	//根据pageCount的值显示每一页的数字并附加上相应的超链接
        for(int i=1;i<=pagenum;i++){
    %>
    <a href="${pageContext.request.contextPath}/redismaintest?showpage=<%=i%>"><%=i%></a>
    <% }
    %>
    <a href="${pageContext.request.contextPath}/redismaintest?showpage=<%=showpage+1%>">下一页</a>
    <a href="${pageContext.request.contextPath}/redismaintest?showpage=<%=pagenum%>">末页</a>
    <!-- 通过表单提交用户想要显示的页数 -->
    <form action="redismaintest" method="get">
        跳转到第<input type="text" name="showpage" size="2">页
        <input type="submit" name="submit" value="跳转">
    </form>
</div>


<%--</table>--%>
</body>
</html>
