<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>列出分类信息</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>
<body bgcolor="c7edcc">
<br/><br/>
<table frame="border" cellpadding="0" cellspacing="0" width="90%">
    <caption><h2>书籍分类信息</h2></caption>
    <tr>
        <td>分类名称</td>
        <td>分类描述</td>
        <td>操作</td>
    </tr>

    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.name }</td>
            <td>${category.description }</td>
            <td>
                <a href="${pageContext.request.contextPath}/manag/CategoryServlet?method=updateUI&id=${category.id}" target="right">修改</a>
                <a href="${pageContext.request.contextPath}/manag/CategoryServlet?method=delete&id=${category.id}" target="right">删除</a>
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
  Date: 2016/4/12
  Time: 11:34
  To change this template use File | Settings | File Templates.
-->