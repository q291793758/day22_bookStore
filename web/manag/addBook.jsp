<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加图书界面</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>
<body bgcolor="c7edcc">
<form action="${pageContext.request.contextPath}/manag/BookServlet?method=addBook" method="post" enctype="multipart/form-data">
    <table width="500px">
        <tr>
            <td>书名</td>
            <td><input type="text" name="name" width="200px"></td>
        </tr>
        <tr>
            <td>作者</td>
            <td><input type="text" name="author" style="width: 200px"></td>
        </tr>
        <tr>
            <td>售价</td>
            <td><input type="text" name="price" style="width: 200px"></td>
        </tr>
        <tr>
            <td>图片</td>
            <td><input type="file" name="image" style="width: 200px"></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><textarea rows="4" cols="40" name="description"></textarea></td>
        </tr>

        <tr>
            <td>所属分类</td>
            <td>
                <select name="category_id">
                    <c:forEach var="category"  items="${categorys}">
                        <option value="${category.id }">${category.name }</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td><td><input type="submit" value="添加书籍"></td>
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
  Time: 16:24
  To change this template use File | Settings | File Templates.
-->