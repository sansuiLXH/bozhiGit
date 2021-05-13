<%--
  Created by IntelliJ IDEA.
  User: 西西里_SanSui
  Date: 2021/5/12
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>更新数据</title>
</head>
<body>
<%
String id = request.getParameter("id");
%>
<%--<%=id%>--%>
<form action="updateStudent" method="post" id="regForm" onsubmit="return checkform()">
    学号:&nbsp;<input name="id" id="id" type="text" value="<%=id%>">
    <br>
    <!--name属性是给servlet获取值的-->
    姓名:&nbsp;<input name="name" id="name" type="text">
    <br>
    <br>
    出生日期:&nbsp;<input name="birthday" id="birthday" type="date">
    <br>
    <br>
    备注:&nbsp;<input name="description" id="description" type="text">
    <br>
    平均分:&nbsp;<input name="avgscore" id="avgscore" type="text">
    <br>

    <input value="提交" type="submit">
</form>

</body>
</html>
