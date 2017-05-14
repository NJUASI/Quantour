<%--
  Created by IntelliJ IDEA.
  User: Byron Dong
  Date: 2017/5/13
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candlestick</title>
</head>
<body>
<div id ="candlestick" style="width:100%;height: 600px"></div>
<script src = "../js/chart.js"></script>
<script src = "../js/echarts.min.js"></script>
<script type="text/javascript">
    var data = ${candlestickData};
    data.reverse();
    <%--var data2 = ${volumeData};--%>
    createCandlestickChart("candlestick",data);
</script>
</body>
</html>
