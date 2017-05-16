<%--
  Created by IntelliJ IDEA.
  User: cuihua
  Date: 2017/5/14
  Time: 下午8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title>回测系统</title>
</head>
<body>
<div id = "trace_back_chart" style="width:100%;height:600px"></div>
<div id = "absolute_histogram_chart" style="width:100%;height:600px"></div>
<div id = "relative_histogram_chart" style="width:100%;height:600px"></div>
<div id = "formates_excess_chart" style="width:100%;height:600px"></div>
<div id = "formates_win_chart" style="width:100%;height:600px"></div>
<div id = "holdings_excess_chart" style="width:100%;height:600px"></div>
<div id = "holdings_win_chart" style="width:100%;height:600px"></div>
<h1>欢迎开始回测</h1>

<script src = "../js/echarts.min.js"></script>
<script src = "../js/chart.js"></script>
<script type = "text/javascript">
    var trace_back_chart = createTraceBackChart("trace_back_chart"${strategyData},${baseData},['策略','基准'],'1','1');
    var absolute_histogram_chart = createHistogramChart("absolute_histogram_chart",${absoluteHistogramData});
    var relative_histogram_chart = createHistogramChart("relative_histogram_chart",${relativeHistogramData});
    var formates_excess_chart = createAreaChart("formates_excess_chart",${certainFormatesExcessData},'胜率');
    var formates_win_chart = createAreaChart("formates_win_chart",${certainFormatesWinData},'赢率');
    var holdings_excess_chart = createAreaChart("holdings_excess_chart",${certainHoldingsExcessData},'胜率');
    var holdings_win_chart = createAreaChart("holdings_win_chart",${certainHoldingsWinData},'赢率');
</script>
</body>
</html>
