<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>IT图书大减价了!!</title>
    <style>
        body {
            text-align: center;
            margin: 0px;
            padding: 0px;
            /*width: 90%;*/
        }

        #container{
            width: 980px;
            height: 300px;
            text-align: left;
        }
        #main{
            margin-top: 20px;
        }
        #head{
            text-align: center;
        }

        #cotegorys{
            border: solid 1px blue;
            padding-left: 20px;
            width: 200px;
            height: 300px;
            line-height: 40px;
            float: left;
        }

        #books{
            float: left;
            margin-left: 40px;
        }

        #image{
            float: left;
        }

        #info{
            float: left;
        }

        #book{
            margin-top: 40px;
            float: left;
            width:230px;
        }

    </style>
</head>
<body bgcolor="c7edcc">

<div id="container">
    <div align="center">
        <jsp:include page="head.jsp"/><%--包含头页面--%>
    </div>

    <div id="main">
        <div id="cotegorys">
            书籍分类列表:
            <c:forEach var="category" items="${categorys}">
                <li>
                    <a href="${pageContext.request.contextPath}/client/IndexServlet?category_id=${category.id}">${category.name}</a>
                </li>
            </c:forEach>
        </div>
        <div id="books"> <%--显示书籍3*3--%>
            <c:forEach items="${pagebean.booklist}" var="book" varStatus="status">
            <div id="book">
                <div id="image">
                    <img src="${pageContext.request.contextPath}/imgages/${book.image}" alt="图书">
                </div>
                <div id="info">
                    <li> ${book.name} </li>
                    <li> ${book.author} </li>
                    <li> ${book.price} </li>
                    <li>
                        <a href="${pageContext.request.contextPath }/client/BuyServlet?id=${book.id }">购买</a>
                    </li>
                </div>
                <div style="clear: both"></div>
            </div>
                <c:if test="${status.count%3==0}">
                <div style="clear: both"></div>
                <br/>
                </c:if>
            </c:forEach>
            <div style="clear: both"></div>
            <br><br><br><br><br>

        <div id="pagebar" align="center">
            总共${pagebean.totalpage }页
            当前${pagebean.currentpage }页
            <c:forEach var="pagenum" items="${pagebean.pagebar}">
                <a href="${pageContext.request.contextPath }/client/IndexServlet?currentpage=${pagenum }&category_id=${param.category_id }">${pagenum }</a>
            </c:forEach>

        </div><hr>
        </div>


    </div>
</div>
</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

Created by IntelliJ IDEA.
User: Administrator
Date: 2016/4/12
Time: 19:21
To change this template use File | Settings | File Templates.
-->