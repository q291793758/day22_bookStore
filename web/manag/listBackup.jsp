<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>备份的数据库信息</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>
<body bgcolor="c7edcc">

<table  frame="border" cellpadding="0" cellspacing="0">
    <caption><h1>已备份数据库</h1></caption>
    <br>
    <br>
    <tr>
        <td>名称</td>
        <td>时间</td>
        <td width="100px">备注</td>
        <td>操作</td>
    </tr>
    <c:forEach var="b" items="${list}">
        <tr>
        <td>${b.filename}</td>
        <td>${b.baktime}</td>
        <td>${b.description}</td>
        <td>
            <a href="${pageContext.request.contextPath}/manag/DbServlet?method=recover&id=${b.id}">恢复</a>
            <a href="${pageContext.request.contextPath}/manag/DbServlet?method">删除</a>
        </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/13
  Time: 12:12
  To change this template use File | Settings | File Templates.
-->