<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>后台管理页面</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
</head>

<frameset rows="18%,*">
    <frame src="${pageContext.request.contextPath}/manager/head.jsp" name="head">
    <frameset  cols="15%,*">
        <frame src="${pageContext.request.contextPath}/manager/left.jsp" name="left">
        <frame src="#" name="right">
    </frameset>
</frameset>

</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/9
  Time: 9:10
  To change this template use File | Settings | File Templates.
-->