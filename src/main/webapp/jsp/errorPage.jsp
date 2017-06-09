<%--
  Created by IntelliJ IDEA.
  User: cuihua
  Date: 2017/5/14
  Time: 下午9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title>出了一点小问题。。</title>
</head>
<body>


<div>

    <div style="height: 120px;"></div>
    <center>
        <img src="../img/NotFound.png" alt="加载失败，出了一点小问题。请静待我们的解决哦（≧∇≦）">
        <div>
            <div style="height: 25px;margin-top: 30px"></div>
            <%--错误信息显示--%>
            <span style="color: #999;margin-top: 30px;font-size: 130%;height: 30px; font-family: 'Microsoft YaHei'; ">${error}</span>
        </div>
    </center>


</div>




</body>
</html>
