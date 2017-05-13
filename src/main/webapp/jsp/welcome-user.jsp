<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cuihua
  Date: 2017/5/12
  Time: 下午8:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Welcome</title>
</head>
<body>
<c:if test="${user!=null}">
    <li><a>普通用户${user.userName}已经成功登录</a></li>
    <li><a>自选股列表大小：${psList.size()}</a></li>



</c:if>

<c:forEach items="${psList}" var="stock" varStatus="vs">
    <div>
        <%-- 列表显示所有自选股当日股票信息 --%>
        <a>${stock.stockID.code} + " " + ${stock.stockID.date} + " " + ${stock.name} + " " + ${stock.open} + " " +
                ${stock.close} + " " + ${stock.high} + " " + ${stock.low} + " " + ${stock.volume}</a>
    </div>
</c:forEach>


<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="../../js/jquery.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
