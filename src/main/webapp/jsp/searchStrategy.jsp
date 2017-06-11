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


<div class="row ">
    <div class="col-md-8 col-md-offset-2">
        <div class="row">
            <h3 class="col-md-2" style="color:#4890c8">中小板股指</h3>
            <%--TODO 高源 收藏、取消收藏、编辑修改、删除策略 (strategy.js  strategy_modify...) --%>
            <button class="btn btn-primary btn  col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="glyphicon glyphicon-heart"></span>
                <span class="txt">收藏</span>
            </button>

            <button class="btn btn-default  col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="txt">取消收藏</span>
            </button>

            <button id="modifyBt" class="btn btn-primary btn  col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="txt">编辑</span>
            </button>

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
                        <li>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:&nbsp; <span>2017-01-12</span></li>
                        <li>板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块:&nbsp; <span>1231</span></li>
                        <li>创建日期:&nbsp; <span>2017-01-12</span></li>
                        <li>ST&nbsp;&nbsp;&nbsp;信息:&nbsp; <span>123333</span></li>
                        <li>开始日期:&nbsp; <span>13</span></li>
                        <li>结束日期:&nbsp; <span>14</span></li>
                        <li>调仓周期:&nbsp; <span>1233</span></li>
                        <li>最大持仓:&nbsp; <span>1233</span></li>
                        <li>收益基准:&nbsp; <span>1233</span></li>
                    </ul>
                    </div>
                    <div class="row">
                        <div class="col-md-3 "><p>筛选条件</p></div>
                        <div class="col-md-9" style="margin-left: -40px">
                        <div class="row ">
                            <div class=" col-md-12 ">
                                <span class="col-md-6">指标</span><span class="col-md-4">比较符</span> <span class="col-md-1">值</span>
                            </div>
                            <div class=" col-md-12 ">
                                <span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">20</span>
                            </div>
                            <div class=" col-md-12 ">
                                <span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">20</span>
                            </div>
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
                                <div class=" col-md-12 ">
                                    <span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">1</span>
                                </div>
                                <div class=" col-md-12 ">
                                    <span class="col-md-6">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-2">1</span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-md-3">
                    <div class="col-md-12 ">
                        策略描述:
                    </div>
                    <p class="col-md-12 " style="color:#9e9e9e">
                        不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的 </p>
                </div>
                <div class="col-md-4" style="border-left: 1px solid slategray">
                    <div id="candlestick" style=" width:100%;height:220px"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row ">
    <div class="col-md-8 col-md-offset-2">
        <div class="row">
            <h3 class="col-md-2" style="color:#4890c8">${nowStrategy.strategyID}</h3>
            <%--TODO 高源 收藏、取消收藏、编辑修改、删除策略 (strategy.js  strategy_modify...) --%>
            <button class="btn btn-primary btn  col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="glyphicon glyphicon-heart"></span>
                <span class="txt">收藏</span>
            </button>

            <button class="btn btn-default  col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="txt">取消收藏</span>
            </button>

            <button id="modifyBt" class="btn btn-primary btn  col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="txt">编辑</span>
            </button>
            <button id="deleteBt" class="btn btn-primary btn  col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="txt">删除</span>
            </button>
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
                            <li>板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块:&nbsp; <span>${traceBackCriteria.stockPoolCriteria}</span></li>
                            <li>创建日期:&nbsp; <span>${nowStrategy.date}</span></li>
                            <li>ST&nbsp;&nbsp;&nbsp;信息:&nbsp; <span>${traceBackCriteria.}</span></li>
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



<div id="coverPanel" style="position: absolute;background: #EBEFF0;width: 100%;height: 90px;z-index: 20">

</div>

<div class="row" style="z-index: 2">

    <ul id="myTab" class="col-md-offset-1 col-md-10 nav nav-tabs" role="tablist">
        <li class="active"><a href="#chartPanel" role="tab" data-toggle="tab">收益曲线</a></li>
        <li><a href="#cyclePanel" role="tab" data-toggle="tab">收益周期统计</a></li>
        <li><a href="#holdingDetailPanel" role="tab" data-toggle="tab">交易详情</a></li>

    </ul>
</div>
<!-- 选项卡面板 -->
<div id="myTabContent" class="col-md-10 col-lg-offset-1 tab-content">

    <div class="tab-pane active" id="chartPanel">
        <div class="col-md-12 table-responsive">
            <table class="table table-hover table-condensed">
                <thead>
                <tr>
                    <th>投资组合</th>
                    <th>总收益</th>
                    <th>年化收益</th>
                    <th>夏普比率</th>
                    <th>最大回撤率</th>
                    <th>收益波动率</th>
                    <th>贝塔率</th>
                    <th>阿尔法比率</th>
                </tr>
                </thead>
                <tbody id="tb_chart">
                </tbody>
            </table>
        </div>
        <div class="row">
            <div id="trace_back_chart" style="width:1100px;height:500px"></div>
        </div>
    </div>
    <div class="tab-pane" id="cyclePanel">
        <div class="row">
            <div class="col-md-4 table-responsive">
                <table class="table table-hover table-condensed">
                    <caption class="text-center"><h3>绝对收益直方图</h3></caption>
                    <thead>
                    <tr>
                        <th>正收益周期数</th>
                        <th>负收益周期数</th>
                        <th>赢率</th>
                    </tr>
                    </thead>
                    <tbody id="tb_cycle_ab">
                    </tbody>
                </table>
            </div>
            <div class="row col-md-6">
                <div id="absolute_histogram_chart" style="width:600px;height:300px"></div>
            </div>


        </div>

        <div class="row">
            <div class="col-md-4 table-responsive">
                <table class="table table-hover table-condensed">
                    <caption class="text-center"><h3>相对收益直方图</h3></caption>
                    <thead>
                    <tr>
                        <th>正收益周期数</th>
                        <th>负收益周期数</th>
                        <th>赢率</th>
                    </tr>
                    </thead>
                    <tbody id="tb_cycle_re">
                    </tbody>
                </table>
            </div>
            <div class="row col-md-6">
                <div id="relative_histogram_chart" style="width:600px;height:300px"></div>
            </div>
        </div>


    </div>
    <div class="tab-pane" id="holdingDetailPanel">
        <div class="row">
            <div class="col-md-12 table-responsive pre-scrollable" style="max-height: 640px">
                <table class="table table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>周期序号</th>
                        <th>开始日期</th>
                        <th>结束日期</th>
                        <th>股票持有只数</th>
                        <th>策略收益</th>
                        <th>基准收益</th>
                        <th>超额收益</th>
                        <th>模拟投资</th>
                    </tr>
                    </thead>
                    <tbody id="tb_detail">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

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
</script>
</body>
</html>
