<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>订单详细信息</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>
<body bgcolor="c7edcc">
<table frame="border" cellpadding="0" cellspacing="0" width="90%">
    <h3>订单明细</h3>
    <tr>
        <td>书名</td>
        <td>售价</td>
        <td>数量</td>
        <td>应收货款</td>
    </tr>

    <c:forEach var="orderitem" items="${order.orderitems}">
        <tr>
            <td>${orderitem.book.name }</td>
            <td>${orderitem.book.price }</td>
            <td>${orderitem.quantity }</td>
            <td>${orderitem.price }元</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2">总计应收货款</td>
        <td colspan="2">${order.price }元</td>
    </tr>
</table>
<br/><br/>
<table frame="border" cellpadding="0" cellspacing="0" width="90%">
    <h3>收货人详细地址</h3>
    <tr>
        <td>用户</td>
        <td>电话</td>
        <td>手机</td>
        <td>地址</td>
        <td>邮箱</td>
    </tr>

    <tr>
        <td>${order.user.username }</td>
        <td>${order.user.phone }</td>
        <td>${order.user.cellphone }</td>
        <td>${order.user.address }</td>
        <td>${order.user.email }</td>
    </tr>
</table>
<br/>
<a href="${pageContext.request.contextPath }/manag/getOrderServlet?method=update&id=${order.id }">确认发货</a>

</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/13
  Time: 0:37
  To change this template use File | Settings | File Templates.
-->