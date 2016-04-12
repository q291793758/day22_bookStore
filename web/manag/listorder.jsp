<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>订单信息</title>
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
    <caption><h2>订单信息</h2></caption>
    <tr>
        <td>订单人</td>
        <td>下单时间</td>
        <td>订单状态</td>
        <td>订单总价</td>
        <td>操作</td>
    </tr>

    <c:forEach var="order" items="${list}">
        <tr>
            <td>
                    ${order.user.username }
            </td>
            <td>
                    ${order.ordertime }
            </td>
            <td>
                    ${order.state==false?'未发货':'已发货' }
            </td>
            <td>
                    ${order.price }
            </td>
            <td>
                <a href="${pageContext.request.contextPath }/manag/getOrderServlet?method=find&id=${order.id }">查看明细</a>
                <a href="#">修改</a>
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
Time: 0:16
To change this template use File | Settings | File Templates.
-->