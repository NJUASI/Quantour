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
    <link href="../css/bootstrap.css" rel="stylesheet">
    <title>Welcome</title>
</head>
<body>
<c:if test="${user!=null}">
    <li><a>普通用户${user.userName}已经成功登录</a></li>

    <c:choose>
        <c:when test="${ps_list != null}">
            <li><a>自选股列表大小：${ps_list.size()}</a></li>
            <c:forEach items="${ps_list}" var="stock" varStatus="vs">
                <div>
                        <%-- 列表显示所有自选股当日股票信息 --%>
                    <p>${stock.stockID.code} + " " + ${stock.stockID.date} + " " + ${stock.name} + " " + ${stock.open} + " " +
                            ${stock.close} + " " + ${stock.high} + " " + ${stock.low} + " " + ${stock.volume}</p>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <li><a>该用户还未添加自选股</a></li>
        </c:otherwise>
    </c:choose>
</c:if>


</body>
</html>
