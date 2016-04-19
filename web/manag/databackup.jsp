<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>数据库备份</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>
<body bgcolor="c7edcc">
<form action="${pageContext.request.contextPath}/manag/DbServlet?method=backup" method="post">
    <h3>备份名称默认为当前时间</h3>
    备份描述: <br>
    <textarea name="description" rows="5" cols="50"> </textarea>
    <br>
    <input type="submit" value="确认">
</form>

</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/13
  Time: 10:54
  To change this template use File | Settings | File Templates.
-->