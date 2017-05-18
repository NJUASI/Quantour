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
    <link href="../css/startLoader.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        footer {
            width: 100%;
            height: 100px;
            background-color: #444444;
        }
    </style>
    <title>回测系统</title>
</head>
<body class="loaded">
<div id = "trace_back_chart" style="width:100%;height:600px"></div>
<div id = "absolute_histogram_chart" style="width:100%;height:600px"></div>
<div id = "relative_histogram_chart" style="width:100%;height:600px"></div>
<div id = "formates_excess_chart" style="width:100%;height:600px"></div>
<div id = "formates_win_chart" style="width:100%;height:600px"></div>
<div id = "holdings_excess_chart" style="width:100%;height:600px"></div>
<div id = "holdings_win_chart" style="width:100%;height:600px"></div>
<h1>欢迎开始回测</h1>
<footer>
</footer>
<script src = "../js/echarts.min.js"></script>
<script src = "../js/chart.js"></script>
<script src = "../js/startLoaded.js"></script>
<script type = "text/javascript">
    var trace_back_chart = createTraceBackChart("trace_back_chart"${json_strategyData},${json_baseData},['策略','基准'],'1','1');
    var absolute_histogram_chart = createHistogramChart("absolute_histogram_chart",${json_absoluteHistogramData}, "绝对收益直方图");
    var relative_histogram_chart = createHistogramChart("relative_histogram_chart",${json_relativeHistogramData}, "相对收益直方图");
    var formates_excess_chart = createAreaChart("formates_excess_chart",${json_certainFormatesExcessData},'胜率');
    var formates_win_chart = createAreaChart("formates_win_chart",${json_certainFormatesWinData},'赢率');
    var holdings_excess_chart = createAreaChart("holdings_excess_chart",${json_certainHoldingsExcessData},'胜率');
    var holdings_win_chart = createAreaChart("holdings_win_chart",${json_certainHoldingsWinData},'赢率');
</script>
</body>
</html>
