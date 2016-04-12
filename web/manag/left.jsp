<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>后台管理左侧导航</title>
    <style>
        body {
            text-align: center;
            margin: auto;
            width: 80%;
        }
    </style>
    <style type="text/css">
        .dc {
            display: none;
            margin-left: 10px;
        }
    </style>

    <script language="javascript">
        function test(e) {
            e.style.display = e.style.display == 'block' ? 'none' : 'block' ;
        }
    </script>
</head>
<body bgcolor="c7edcc">
<ul>
    <li>
        <a href="#" onclick="test(children[0])">分类管理
            <div class="dc">
                <a href="${pageContext.request.contextPath}/manag/addcategory.jsp" target="right">添加分类</a><br/>
                <a href="${pageContext.request.contextPath}/manag/CategoryServlet?method=getAll" target="right">查看分类</a><br/>
            </div>
        </a>
    </li>

    <br/><br/>

    <li>
        <a href="#" onclick="test(children[0])">图书管理
            <div class="dc">
                <a href="${pageContext.request.contextPath }/manag/BookServlet?method=addBookUI"  target="right">添加图书</a><br/>
                <a href="${pageContext.request.contextPath }/manag/BookServlet?method=listbooks"  target="right">查看图书</a>
            </div>
        </a>
    </li>

    <br/><br/>

    <li>
        <a href="#" onclick="test(children[0])">订单管理
            <div class="dc">
                <a href="${pageContext.request.contextPath }/manag/OrderServlet?method=getAll&state=false"  target="right">待处理订单</a><br/>
                <a href="${pageContext.request.contextPath }/manag/OrderServlet?method=getAll&state=true"  target="right">已发货订单</a><br/>
            </div>
        </a>
    </li>

    <br/><br/>

</ul>
</body>
</html>


<%--${pageContext.request.contextPath}--%>
<!--

  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/9
  Time: 9:13
  To change this template use File | Settings | File Templates.
-->