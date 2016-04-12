<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户购物车</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            /*width: 80%;*/
        }
    </style>
</head>
<body bgcolor="c7edcc">
<%@include file="/client/head.jsp" %>

<br/><br/>
<c:if test="${cart!=null}">
<table frame="border" cellpadding="0" cellspacing="0" width="80%">
    <caption><h2>购物车页面</h2></caption>
    <tr>
        <td>书名</td>
        <td>售价</td>
        <td>数量</td>
        <td>小计</td>
    </tr>

    <c:forEach var="entry" items="${cart.map}">
        <tr>
            <td>${entry.value.book.name }</td>
            <td>${entry.value.book.price }</td>
            <td>${entry.value.quantity }</td>
            <td>${entry.value.price }元</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2">合计</td>
        <td colspan="2">${cart.price }元</td>
    </tr>
</table>

<a href="./OrderServlet?method=saveOrder">生成订单</a>
</c:if>
<c:if test="${cart==null}">
    您的购物车是空的!
</c:if>
</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/12
  Time: 22:05
  To change this template use File | Settings | File Templates.
-->