<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>后台管理头页面</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>
<body bgcolor="c7edcc">
<h1>在线网上书店后台管理</h1><a href="/client/IndexServlet" target="_parent">前台主页</a>
<c:if test="${user!=null}">
    欢迎您!&nbsp;&nbsp;${user.username } &nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/client/LoginServlet?method=logout">注销</a>
</c:if>
<c:if test="${user==null}">
    <form action="${pageContext.request.contextPath}/client/LoginServlet?method=login" method="post">
        用户名：<input type="text" name="username" style="width: 70px">
        密码：<input type="password" name="password" style="width: 70px">
        <input type="submit" value="登陆">
        <input type="button" value="注册" onclick="javascript:window.location.href='./client/register.jsp'">
    </form>
</c:if>
</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/9
  Time: 9:12
  To change this template use File | Settings | File Templates.
-->