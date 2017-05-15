<%--
  Created by IntelliJ IDEA.
  User: cuihua
  Date: 2017/5/13
  Time: 下午7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <title>股票市场</title>
</head>
<body>
<div id = "candlestick_chart" style="width:100%;height:600px"></div>


<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>
<script type="text/javascript">
    var candlestickChart = createCandlestickChart("candlestick_chart",${candlestickData},${volumeData});
</script>
</body>
</html>
