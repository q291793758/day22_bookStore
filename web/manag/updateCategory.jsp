<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>更改分类数据</title>
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

<form action="${pageContext.request.contextPath}/manag/CategoryServlet?method=update" method="post">
    <table width="500px;">
        <tr>
            <td></td>
            <td><input type="hidden" name="id" value="${category.id}" style="width: 200px"></td>
        </tr>
        <tr>
        <tr>
            <td>分类名称：</td>
            <td><input type="text" name="name" value="${category.name}" style="width: 200px"></td>
        </tr>
        <tr>
            <td>分类描述：</td>
            <td><textarea rows="4" cols="40" name="description">${category.description}</textarea></td>
        </tr>
        <tr>
            <td></td><td><input type="submit" value="修改分类"></td>
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
  Time: 13:49
  To change this template use File | Settings | File Templates.
-->