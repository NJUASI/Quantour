<%@ page import="com.edu.nju.asi.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/6/3
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap-select.css">
    <link rel="stylesheet" href="../css/bootstrap-datetimepicker.css">
    <link href="../css/startLoader.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        footer {
            margin-top: 700px;
            width: 100%;
            height: 100px;
            background-color: #444444;
        }

        body {
            margin-top: 80px;
        }

        /*#myTab{*/
        /*display: none;*/
        /*}*/
        /*#chartPanel{*/
        /*display: none;*/
        /*}*/
    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>策略详情</title>
</head>

<body>
<header>
    <%@ include file="header.jsp" %>
</header>


<%--<div class="row ">--%>
    <%--<div class="col-md-8 col-md-offset-2">--%>
        <%--<div class="row">--%>
            <%--<h3 class="col-md-2" style="color:#4890c8">中小板股指</h3>--%>
            <%--&lt;%&ndash;TODO 高源 收藏、取消收藏、编辑修改、删除策略 (strategy.js  strategy_modify...) &ndash;%&gt;--%>
            <%--<button class="btn btn-primary btn  col-md-1 col-md-offset-1" style="margin-top: 20px">--%>
                <%--<span class="glyphicon glyphicon-heart"></span>--%>
                <%--<span class="txt">收藏</span>--%>
            <%--</button>--%>

            <%--<button class="btn btn-default  col-md-1 col-md-offset-1" style="margin-top: 20px">--%>
                <%--<span class="txt">取消收藏</span>--%>
            <%--</button>--%>

            <%--<button id="modifyBt" class="btn btn-primary btn  col-md-1 col-md-offset-1" style="margin-top: 20px">--%>
                <%--<span class="txt">编辑</span>--%>
            <%--</button>--%>

        <%--</div>--%>


    <%--</div>--%>
<%--</div>--%>

<%--<div class="row">--%>
    <%--<div class="panel panel-default col-md-8 col-md-offset-2">--%>
        <%--<div class="panel-body">--%>
            <%--<div class="row" style="margin-top: 20px">--%>
                <%--<div class="col-md-5 row ">--%>
                    <%--<div class="row col-md-12">--%>
                    <%--<ul id="strategyDetail" class="list-inline">--%>
                        <%--<li>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:&nbsp; <span>2017-01-12</span></li>--%>
                        <%--<li>板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块:&nbsp; <span>1231</span></li>--%>
                        <%--<li>创建日期:&nbsp; <span>2017-01-12</span></li>--%>
                        <%--<li>ST&nbsp;&nbsp;&nbsp;信息:&nbsp; <span>123333</span></li>--%>
                        <%--<li>开始日期:&nbsp; <span>13</span></li>--%>
                        <%--<li>结束日期:&nbsp; <span>14</span></li>--%>
                        <%--<li>调仓周期:&nbsp; <span>1233</span></li>--%>
                        <%--<li>最大持仓:&nbsp; <span>1233</span></li>--%>
                        <%--<li>收益基准:&nbsp; <span>1233</span></li>--%>
                    <%--</ul>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-3 "><p>筛选条件</p></div>--%>
                        <%--<div class="col-md-9" style="margin-left: -40px">--%>
                        <%--<div class="row ">--%>
                            <%--<div class=" col-md-12 ">--%>
                                <%--<span class="col-md-6">指标</span><span class="col-md-4">比较符</span> <span class="col-md-1">值</span>--%>
                            <%--</div>--%>
                            <%--<div class=" col-md-12 ">--%>
                                <%--<span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">20</span>--%>
                            <%--</div>--%>
                            <%--<div class=" col-md-12 ">--%>
                                <%--<span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">20</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>

                    <%--</div>--%>
                    <%--<div class="row" style="margin-top: 5px">--%>
                        <%--<div class="col-md-3 "><p>排名条件</p></div>--%>
                        <%--<div class="col-md-9" style="margin-left: -40px">--%>
                            <%--<div class="row ">--%>
                                <%--<div class=" col-md-12 ">--%>
                                    <%--<span class="col-md-6">指标</span><span class="col-md-4">次序</span> <span class="col-md-2" style="padding-right: 0px">权重</span>--%>
                                <%--</div>--%>
                                <%--<div class=" col-md-12 ">--%>
                                    <%--<span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">1</span>--%>
                                <%--</div>--%>
                                <%--<div class=" col-md-12 ">--%>
                                    <%--<span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-2">1</span>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</div>--%>
                <%--<div class="col-md-3">--%>
                    <%--<div class="col-md-12 ">--%>
                        <%--策略描述:--%>
                    <%--</div>--%>
                    <%--<p class="col-md-12 " style="color:#9e9e9e">--%>
                        <%--不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的 </p>--%>
                <%--</div>--%>
                <%--<div class="col-md-4" style="border-left: 1px solid slategray">--%>
                    <%--<div id="candlestick" style=" width:100%;height:220px"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


<div class="row ">
    <div class="col-md-8 col-md-offset-2">
        <div class="row">
            <h3 class="col-md-2" style="color:#4890c8">${nowStrategy.strategyID}</h3>
            <%--TODO 高源 收藏、取消收藏、编辑修改、删除策略 (strategy.js  strategy_modify...) --%>
            <div hidden>
                <button class="btn btn-primary btn  col-md-1 col-md-offset-1"  style="margin-top: 20px">
                    <span class="glyphicon glyphicon-heart"></span>
                    <span class="txt">收藏</span>
                </button>
            </div>
            <div hidden>
            <button class="btn btn-default  col-md-1 col-md-offset-1"  style="margin-top: 20px">
                <span class="txt">取消收藏</span>
            </button>
            </div>
            <div hidden>
            <button id="modifyBt" class="btn btn-primary btn  col-md-1 col-md-offset-1"  style="margin-top: 20px">
                <span class="txt">编辑</span>
            </button>
            </div>
            <div hidden>
            <button id="deleteBt" class="btn btn-primary btn  col-md-1 col-md-offset-1"  style="margin-top: 20px">
                <span class="txt">删除</span>
            </button>
            </div>
        </div>


    </div>
</div>

<div class="row">
    <div class="panel panel-default col-md-8 col-md-offset-2">
        <div class="panel-body">
            <div class="row" style="margin-top: 20px">
                <div class="col-md-5 row ">
                    <div class="row col-md-12">
                        <ul id="strategyDetail" class="list-inline">
                            <%--TODO 数据添加--%>
                            <li>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:&nbsp; <span>${nowStrategy.creator}</span></li>
                            <%--<li>板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块:&nbsp; <span>${traceBackCriteria.stockPoolCriteria}</span></li>--%>
                            <li>创建日期:&nbsp; <span>${nowStrategy.date}</span></li>
                            <%--<li>ST&nbsp;&nbsp;&nbsp;信息:&nbsp; <span>${traceBackCriteria.}</span></li>--%>
                            <li>开始日期:&nbsp; <span>${traceBackCriteria.startDate}</span></li>
                            <li>结束日期:&nbsp; <span>${traceBackCriteria.endDate}</span></li>
                            <li>调仓周期:&nbsp; <span>${traceBackCriteria.holdingPeriod}</span></li>
                            <li>最大持仓:&nbsp; <span>${traceBackCriteria.maxHoldingNum}</span></li>
                            <li>收益基准:&nbsp; <span>${traceBackCriteria.baseStockName}</span></li>
                        </ul>
                    </div>
                    <div class="row">
                        <div class="col-md-3 "><p>筛选条件</p></div>
                        <div class="col-md-9" style="margin-left: -40px">
                            <div class="row ">
                                <div class=" col-md-12 ">
                                    <span class="col-md-6">指标</span><span class="col-md-4">比较符</span> <span class="col-md-1">值</span>
                                </div>
                                <c:forEach items="${filterConditions}" var="condition" varStatus="vs">
                                <div class=" col-md-12 ">
                                    <span class="col-md-6">${condition.indicatorType}</span><span class="col-md-4">${condition.comparatorType}</span> <span class="col-md-1">${condition.value}</span>
                                </div>
                                </c:forEach>
                            </div>
                        </div>

                    </div>
                    <div class="row" style="margin-top: 5px">
                        <div class="col-md-3 "><p>排名条件</p></div>
                        <div class="col-md-9" style="margin-left: -40px">
                            <div class="row ">
                                <div class=" col-md-12 ">
                                    <span class="col-md-6">指标</span><span class="col-md-4">次序</span> <span class="col-md-2" style="padding-right: 0px">权重</span>
                                </div>
                                <c:forEach items="${rankConditions}" var="condition" varStatus="vs">
                                    <div class=" col-md-12 ">
                                        <span class="col-md-6">${condition.indicatorType}</span><span class="col-md-4">${condition.rankType}</span> <span class="col-md-1">${condition.weight}</span>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-md-3">
                    <div class="col-md-12 ">
                        策略描述:
                    </div>
                    <p class="col-md-12 " style="color:#9e9e9e">
                        ${nowStrategy.description}
                    </p>
                </div>
                <div class="col-md-4" style="border-left: 1px solid slategray">
                    <div id="candlestick" style=" width:100%;height:220px"></div>
                </div>
            </div>
        </div>
    </div>
</div>



<%@ include file="tracebackAnalyse.jsp" %>

<%@ include file="logIn.jsp" %>
<footer>

</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>


<script src="../js/echarts.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/quotaSelect.js"></script>
<script src="../js/startLoaded.js"></script>
<script src="../js/logIn.js"></script>
<%--<script src="../js/dbDatePicker.js"></script>--%>

<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">


    var user = "<%= ((User)session.getAttribute("user")).getUserName()%>";
    alert(user);
    var creator=JSON.stringify(${nowStrategy.creator});
    alert(user);
    if(user==creator){
              $("#delete").show();
    }
            var hasScr=${hasSubscribe};
            alert(hasScr);
    alert("123");
    $("#strategyDetail").find("li").addClass("col-md-6 ");
    $("#strategyDetail").find("li").find("span").css("color", "#9e9e9e");
    $("#strategyDetail").find("li").css("margin-bottom", "10px")
    var paramter = ["我去", 2, 3, 4, 5];
    var data1 = [[2.6, 5.9, 9.0, 26.4, 28.7]];
    createRadarChart('candlestick', data1, ['1'], paramter);
    $(function () {
        $("#modifyBt").click(function () {
            $("#modifyPanel").modal("toggle");
        });
    });




    function openStock() {
        $("body").removeClass('loaded');
        window.location.href="/stocks"
    }
    $("#commity").addClass("act");



    <%--var numberValues = eval("(" +  ${traceBackNums} + ")");          // List<String>--%>
    <%--var abReturnPeriod = ${absoluteReturnPeriod};            // ReturnPeriod--%>
    <%--var reReturnPeriod =${relativeReturnPeriod};            // ReturnPeriod--%>
    <%--var holdingDetails = ${holdingDetails};            // List<HoldingDetail>--%>
    <%--var transferDetails = ${transferDayDetails};           // List<TransferDetail>--%>
    <%--// var certainFormates = eval("(" + array[5] + ")");           // List<ExcessAndWinRateDist>--%>
    <%--// var certainHoldings = eval("(" + array[6] + ")");           // List<ExcessAndWinRateDist>--%>

    <%--// 回测的数值型数据--%>
    <%--$("#tb_chart").empty();--%>
    <%--for (var i = 0; i < 3; i++) {--%>
        <%--$("#tb_chart").append("<tr>");--%>

        <%--switch (i) {--%>
            <%--case 0:--%>
                <%--$("#tb_chart").append("<td>本策略</td>");--%>
                <%--break;--%>
            <%--case 1:--%>
                <%--$("#tb_chart").append("<td>基准股票</td>");--%>
                <%--break;--%>
            <%--case 2:--%>
                <%--$("#tb_chart").append("<td>相对收益</td>");--%>
                <%--break;--%>
        <%--}--%>

        <%--for (var j = 0; j < 7; j++) {--%>
            <%--$("#tb_chart").append("<td>" + numberValues[i * 7 + j] + "</td>");--%>
        <%--}--%>
        <%--$("#tb_chart").append("</tr>");--%>
    <%--}--%>
    <%--// alert("--------------------1----------------");--%>

    <%--// 股票周期的对比图--%>
    <%--$("#tb_cycle_ab").empty();--%>
    <%--$("#tb_cycle_ab").append("<tr>" +--%>
        <%--"<td>" + abReturnPeriod["positivePeriodsNum"] + "</td>" +--%>
        <%--"<td>" + abReturnPeriod["negativePeriodNum"] + "</td>" +--%>
        <%--"<td>" + abReturnPeriod["winRate"] + "</td>" +--%>
        <%--"</tr>");--%>

    <%--$("#tb_cycle_re").empty();--%>
    <%--$("#tb_cycle_re").append("<tr>" +--%>
        <%--"<td>" + reReturnPeriod["positivePeriodsNum"] + "</td>" +--%>
        <%--"<td>" + reReturnPeriod["negativePeriodNum"] + "</td>" +--%>
        <%--"<td>" + reReturnPeriod["winRate"] + "</td>" +--%>
        <%--"</tr>");--%>
    <%--// alert("--------------------2----------------");--%>


    <%--// 持有周期详情--%>
    <%--$("#tb_detail").empty();--%>
    <%--for (var i = 0; i < holdingDetails.length; i++) {--%>
        <%--$("#tb_detail").append("<tr>" +--%>
            <%--"<td style='text-align: center'><span class='circle' style='color:#7291CA'>" + holdingDetails[i]["periodSerial"] + "<span></td>" +--%>
            <%--"<td>" + holdingDetails[i]["startDate"] + "</td>" +--%>
            <%--"<td>" + holdingDetails[i]["endDate"] + "</td>" +--%>
            <%--"<td>" + holdingDetails[i]["holdingNum"] + "</td>" +--%>
            <%--"<td>" + (holdingDetails[i]["strategyReturn"] * 100).toFixed(2) + "%" + "</td>" +--%>
            <%--"<td>" + (holdingDetails[i]["baseReturn"] * 100).toFixed(2) + "%" + "</td>" +--%>
            <%--"<td>" + (holdingDetails[i]["excessReturn"] * 100).toFixed(2) + "%" + "</td>" +--%>
            <%--"<td>" + holdingDetails[i]["remainInvestment"].toFixed(2) + "</td>" +--%>
            <%--"</tr>");--%>
    <%--}--%>

    <%--$(".circle").click(function () {--%>
        <%--alert($(this).html());--%>

        <%--//TODO $(this).html() 为当前需要看的周期，加一下此周期持有股票信息--%>
<%--//                        $("#circleList").empty();--%>
<%--//                        for (var i = 0; i < circleList.length; i++) {--%>
<%--//                            $("#circleList").append("<tr>"+--%>
<%--//                                "<td>" + 股票代码+ "</td>"+--%>
<%--//                                "<td>" + 股票名" + "</td>"+--%>
<%--//                                "<td>" + 行业分类+ "</td>"+--%>
<%--//                                "<td>" + 开始价格+ "</td>"+--%>
<%--//                                "<td>" + 结束价格 + "%" + "</td>"+--%>
<%--//                                "<td>" + 涨幅 + "</td>"+--%>
<%--//                                "<td>" + 本期起始仓 + "</td>"+--%>
<%--//                                "<td>" + 当日成交额 + "</td>"+--%>
<%--//                                "<td>" + 股价振幅 + "</td>"+--%>
<%--//                                "</tr>");--%>
<%--//                        }--%>
        <%--$("#circleModal").modal("toggle");--%>
    <%--})--%>

    <%--$(".circle").hover(function () {--%>
        <%--$(this).css({"cursor": "pointer", "text-decoration": " underline"});--%>
    <%--}, function () {--%>
        <%--$(this).css({"color": "#7291CA", "text-decoration": " none"});--%>
    <%--});--%>

    <%--// alert("--------------------3----------------");--%>

    <%--// 卖出的股票详情--%>
    <%--$("#sold_stock_detail").empty();--%>
    <%--for (var i = 0; i < transferDetails.length; i++) {--%>
        <%--$("#sold_stock_detail").append("<tr>" +--%>
            <%--"<td>" + transferDetails[i]["stockName"] + "</td>" +--%>
            <%--"<td>" + transferDetails[i]["stockCode"] + "</td>" +--%>
            <%--"<td>" + transferDetails[i]["buyDate"] + "</td>" +--%>
            <%--"<td>" + transferDetails[i]["sellDate"] + "</td>" +--%>
            <%--"<td>" + transferDetails[i]["buyPrice"] + "</td>" +--%>
            <%--"<td>" + transferDetails[i]["sellPrice"] + "</td>" +--%>
            <%--"<td>" + (transferDetails[i]["changeRate"] * 100).toFixed(2) + "%" + "</td>" +--%>
            <%--"</tr>");--%>
    <%--}--%>


    <%--// 处理图表的信息--%>
    <%--var strategyData = ${strategyCumulativeReturnChart};            //List<List<String>>--%>
    <%--var baseData = ${baseCumulativeReturnChart};                //List<List<String>>--%>
    <%--var abHistogramData =${absoluteReturnPeriodChart};--%>
    <%--var reHistogramData = ${relativeReturnPeriodChart};--%>
    <%--// var formateExcessData = JSON.parse(array[11]);--%>
    <%--// var formateWinData = JSON.parse(array[12]);--%>
    <%--// var holdingExcessData = JSON.parse(array[13]);--%>
    <%--// var holdingWinData = JSON.parse(array[14]);--%>

    <%--// alert(strategyData + "\n\n" + baseData + "\n\n" + abHistogramData + "\n\n" + reHistogramData + "\n\n" + formateExcessData--%>
    <%--//     + "\n\n" + formateExcessData + "\n\n" + holdingExcessData + "\n\n" + holdingWinData);--%>


    <%--var trace_back_chart = createTraceBackChart("trace_back_chart", strategyData, baseData, ['策略', '基准'], '1', '1');--%>
    <%--var absolute_histogram_chart = createHistogramChart("absolute_histogram_chart", abHistogramData, " ");--%>
    <%--var relative_histogram_chart = createHistogramChart("relative_histogram_chart", reHistogramData, " ");--%>

</script>
</body>
</html>
