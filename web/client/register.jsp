<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>用户注册</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>
<body bgcolor="c7edcc">
<jsp:include page="head.jsp"></jsp:include>
<form action="./RegisterServlet" method="post">
    <table width="300px">
        <caption><h2>用户注册</h2></caption>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username" style="width: 200px"></td>
        <tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" style="width: 200px"></td>
        </tr>
        <tr>
            <td>电话</td>
            <td><input type="text" name="phone" style="width: 200px"></td>
        </tr>
        <tr>
            <td>手机</td>
            <td><input type="text" name="cellphone" style="width: 200px"></td>
        </tr>
        <tr>
            <td>住址</td>
            <td><input type="text" name="address" style="width: 200px"></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" style="width: 200px">
            </td>
        </tr>
        <tr>
            <td></td><td><input type="submit" value="注册"></td>
        </tr>
    </table>
</form>

</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/12
  Time: 22:56
  To change this template use File | Settings | File Templates.
-->