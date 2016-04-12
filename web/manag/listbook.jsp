<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
    <caption><h2>图书信息</h2></caption>
    <tr>
        <td>书名</td>
        <td>作者</td>
        <td>描述</td>
        <td>图片</td>
        <td>操作</td>
    </tr>

    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.name }</td>
            <td>${book.author }</td>
            <td>${book.description }</td>
            <td><a href="${pageContext.request.contextPath }/imgages/${book.image }">查看图片</a></td>
            <td>
                <a href="#">修改</a>
                <a href="#">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/><br/>

</body>


</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/12
  Time: 18:10
  To change this template use File | Settings | File Templates.
-->