<%@ page import="com.edu.nju.asi.model.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.edu.nju.asi.infoCarrier.traceBack.StageDetail" %>
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

<body >
<header>
    <%@ include file="header.jsp" %>
</header>

<div class="row ">
    <div class="col-md-10 col-md-offset-1">
        <div class="row" style="margin-bottom: 30px">
            <h3 class="col-md-6" style="color:#4890c8">${nowStrategy.strategyID}</h3>

            <div hidden id="favor">
                <button class="btn btn-primary btn  col-md-1 col-md-offset-1"  style="margin-top: 20px">
                    <span class="glyphicon glyphicon-heart"></span>
                    <span class="txt">收藏</span>
                </button>
            </div>
            <div hidden id="cancelFavor">
            <button class="btn btn-default  col-md-1 col-md-offset-1"  style="margin-top: 20px">
                <span class="txt">取消收藏</span>
            </button>
            </div>
            <div hidden  id="optimization">
                <button id="optimizationBt" class="btn btn-primary btn  col-md-1 col-md-offset-2"  style="margin-top: 20px">
                    <span class="txt">优化策略</span>
                </button>
            </div>
            <div hidden id="delete">
            <button id="deleteBt" class="btn btn-primary btn  col-md-1 col-md-offset-1"  style="margin-top: 20px">
                <span class="txt">删除</span>
            </button>
            </div>
        </div>


    </div>
</div>

<div class="row">
    <div class="panel panel-default col-md-10 col-md-offset-1">
        <div class="panel-body">
            <div class="row" style="margin-top: 20px">
                <div class="col-md-5 row ">
                    <div class="row col-md-12">
                        <ul id="strategyDetail" class="list-inline">
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
                    <div id="radar" style=" width:100%;height:220px"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row" style="z-index: 2">

    <ul id="myTab" class="col-md-offset-1 col-md-10 nav nav-tabs" role="tablist">
        <li class="active"><a href="#chartPanel" role="tab" data-toggle="tab">收益曲线</a></li>
        <li><a href="#cyclePanel" role="tab" data-toggle="tab">收益周期统计</a></li>
        <li><a href="#holdingDetailPanel" role="tab" data-toggle="tab">交易详情</a></li>
        <li><a href="#recentlySoldPanel" role="tab" data-toggle="tab">卖出股票</a></li>
    </ul>
</div>
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
                <tr>
                    <td>本策略</td>
                    <td>${traceBackNums.get(0)}</td>
                    <td>${traceBackNums.get(1)}</td>
                    <td>${traceBackNums.get(2)}</td>
                    <td>${traceBackNums.get(3)}</td>
                    <td>${traceBackNums.get(4)}</td>
                    <td>${traceBackNums.get(5)}</td>
                    <td>${traceBackNums.get(6)}</td>
                </tr>
                <tr>
                    <td>基准股票</td>
                    <td>${traceBackNums.get(7)}</td>
                    <td>${traceBackNums.get(8)}</td>
                    <td>${traceBackNums.get(9)}</td>
                    <td>${traceBackNums.get(10)}</td>
                    <td>${traceBackNums.get(11)}</td>
                    <td>${traceBackNums.get(12)}</td>
                    <td>${traceBackNums.get(13)}</td>
                </tr>
                <tr>
                    <td>相对收益</td>
                    <td>${traceBackNums.get(14)}</td>
                    <td>${traceBackNums.get(15)}</td>
                    <td>${traceBackNums.get(16)}</td>
                    <td>${traceBackNums.get(17)}</td>
                    <td>${traceBackNums.get(18)}</td>
                    <td>${traceBackNums.get(19)}</td>
                    <td>${traceBackNums.get(20)}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row">
            <div id="trace_back_chart" style="margin:0px auto; width:1100px;height:500px"></div>
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
                    <tr>
                        <td>${absoluteReturnPeriod.positivePeriodsNum}</td>
                        <td>${absoluteReturnPeriod.negativePeriodNum}</td>
                        <td>${absoluteReturnPeriod.winRate}</td>
                    </tr>
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
                    <tr>
                        <td>${relativeReturnPeriod.positivePeriodsNum}</td>
                        <td>${relativeReturnPeriod.negativePeriodNum}</td>
                        <td>${relativeReturnPeriod.winRate}</td>
                    </tr>
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
                        <th>策略收益</th>
                        <th>基准收益</th>
                        <th>超额收益</th>
                        <th>模拟投资</th>
                    </tr>
                    </thead>
                    <tbody id="tb_detail">
                    <c:forEach items="${holdingDetails}" var="holdingDetail" varStatus="vs">
                        <tr>
                            <td class='circle' style='color:#7291CA'>${holdingDetail.get(0)}</td>
                            <td>${holdingDetail.get(1)}</td>
                            <td>${holdingDetail.get(2)}</td>
                            <td>${holdingDetail.get(3)}</td>
                            <td>${holdingDetail.get(4)}</td>
                            <td>${holdingDetail.get(5)}</td>
                            <td>${holdingDetail.get(6)}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="tab-pane" id="recentlySoldPanel">
        <div class="row">
            <div class="col-md-12 table-responsive pre-scrollable" style="max-height: 640px">
                <table class="table table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>股票名</th>
                        <th>股票代码</th>
                        <th>买入日期</th>
                        <th>卖出日期</th>
                        <th>买入价格</th>
                        <th>卖出价格</th>
                        <th>涨幅</th>
                    </tr>
                    </thead>
                    <tbody id="sold_stock_detail">
                    <c:forEach items="${transferDayDetails}" var="transferDayDetail" varStatus="vs">
                        <tr>
                            <td>${transferDayDetail.get(0)}</td>
                            <td>${transferDayDetail.get(1)}</td>
                            <td>${transferDayDetail.get(2)}</td>
                            <td>${transferDayDetail.get(3)}</td>
                            <td>${transferDayDetail.get(4)}</td>
                            <td>${transferDayDetail.get(5)}</td>
                            <td>${transferDayDetail.get(6)}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>


<%@ include file="logIn.jsp" %>

<div class="modal fade" id="optimizationModal" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title">智能优化参数设置</h4>
            </div>
            <div class="modal-body" style="margin-top: -10px">

                <div class="row" style="margin-bottom: 20px">
                    <div class="col-md-offset-1 col-md-1">
                        <span>策略: </span>
                    </div>
                    <div class="col-md-offset-1 col-md-3">
                        <span class="nameOfStrategy">${nowStrategy.strategyID}</span>
                    </div>
                    <div class="col-md-offset-2 col-md-3">
                        <span class="">搜索空间节点数 </span><span id="resultNum" style="color:indianred">1</span><span> 个</span>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 20px">
                    <div class="col-md-offset-1 col-md-2">
                        <span style="margin-top: 10px">目标核心函数: </span>
                    </div>
                    <div class="col-md-2">
                        <select class="form-control" id="mainFunc">
                            <option value="夏普比率">夏普比率</option>
                            <option value="年化收益">年化收益</option>
                        </select>
                    </div>

                    <div class="col-md-offset-2 col-md-3" hidden  id="inputError">
                        <strong style="color:indianred">参数原值不得超出最大最小值范围</strong>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-2 col-md-offset-1"><p>筛选条件</p></div>
                    <div class="col-md-8" style="margin-left: -40px">
                        <div class="row ">
                            <div class=" col-md-6 ">
                                <span class="col-md-6">指标</span><span class="col-md-4">比较符</span> <span class="col-md-1">值</span>
                            </div>
                        </div>

                       <c:forEach items="${filterConditions}" var="condition" varStatus="vs">
                           <div class="row chooseRow">
                               <div class=" col-md-6 ">
                                   <span class="col-md-6 indicatorType">${condition.indicatorType}</span><span class="col-md-4 comparatorType">${condition.comparatorType}</span> <span class="col-md-1 value">${condition.value}</span>
                               </div>
                               <div class=" col-md-6 ">
                                   <div class="col-md-4">
                                       <input type="text" class="form-control max_num num"    placeholder="最大">
                                   </div>
                                   <div class="col-md-4">
                                       <input type="text" class="form-control min_num num"   placeholder="最小">
                                   </div>
                                   <div class="col-md-4">
                                       <input type="text" class="form-control length_num num"   placeholder="步长">
                                   </div>
                               </div>
                           </div>
                        </c:forEach>

                    </div>

                </div>
                <div class="row" style="margin-top: 5px">
                    <div class="col-md-2 col-md-offset-1"><p>排名条件</p></div>
                    <div class="col-md-8" style="margin-left: -40px">
                        <div class="row ">
                            <div class=" col-md-6 ">
                                <span class="col-md-6">指标</span><span class="col-md-4">次序</span> <span class="col-md-2" style="padding-right: 0px">权重</span>
                            </div>
                        </div>
                        <!--<c:forEach items="${rankConditions}" var="condition" varStatus="vs">-->
                        <div class="row rankRow">
                            <div class=" col-md-6 ">
                                <span class="col-md-6 indicatorType">${condition.indicatorType}</span><span class="col-md-4 rankType">${condition.rankType}</span> <span class="col-md-1 weight">${condition.weight}</span>
                            </div>
                            <div class=" col-md-6 ">
                                <div class="col-md-4">
                                    <input type="text" class="form-control max_num num"    placeholder="最大">
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control min_num num"   placeholder="最小">
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control length_num num"   placeholder="步长">
                                </div>
                            </div>
                        </div>
                        <!--</c:forEach>-->

                    </div>
                </div>
                <button class="btn btn-primary col-md-offset-6" id="submitBt" style="margin-top: 20px;margin-bottom: 30px">提交调优</button>

                <div class="row" id="optimizationTable">
                    <div class="col-md-12 table-responsive pre-scrollable" style="max-height: 640px">
                        <table class="table table-hover table-condensed">
                            <thead>
                            <tr id="optimizationHead">

                            </tr>
                            </thead>
                            <tbody id="optimizationList">
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div><!-- /.modal -->
</div>

<footer style="margin-top: 700px">

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
<script src="../js/optimization.js"></script>
<%--<script src="../js/dbDatePicker.js"></script>--%>

<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">


    $("#optimizationBt").click(function () {
        $("#optimizationModal").modal("toggle");
    })

    var user = "<%= ((User)session.getAttribute("user")).getUserName()%>";
    var creator="${nowStrategy.creator}";
    if(user==creator){
            $("#delete").show();
            $("#optimization").show();
    }else{
            var hasSub=${hasSubscribe};
            if(hasSub){
                $("#cancelFavor").show();
            }else{
                $("#favor").show();
            }
    }
//            alert("123");
    $("#strategyDetail").find("li").addClass("col-md-6 ");
    $("#strategyDetail").find("li").find("span").css("color", "#9e9e9e");
    $("#strategyDetail").find("li").css("margin-bottom", "10px")

    $("#cancelFavor").click(function () {
        $("#favor").hide();
        $("#cancelFavor").show();
        //TODO fjj 取消收藏
    });


    $("#favor").click(function () {
        //TODO fjj 收藏
        $("#cancelFavor").show();
        $("#favor").hide();
    });

    $("#delete").click(function () {
        //TODO fjj 删除 此策略


        //TODO fjj 跳转到userManager界面

//        window.location.href=""
    });





    $("#stocks").click(function() {
        $("body").removeClass('loaded');
        window.location.href = "/stocks"
        $("#stocks").unbind("click");
    });
    $("#commity").addClass("act");




    // 处理图表的信息
    var strategyData = ${strategyCumulativeReturnChart};
    var baseData = ${baseCumulativeReturnChart};
    createTraceBackChart("trace_back_chart", strategyData, baseData, ['策略', '基准'], '1', '1');

    var abHistogramData =${absoluteReturnPeriodChart};
    createHistogramChart("absolute_histogram_chart", abHistogramData, " ");

    var reHistogramData = ${relativeReturnPeriodChart};
    createHistogramChart("relative_histogram_chart", reHistogramData, " ");

    var radar = [];
    radar[0] = [${rankResult.profit}*100, ${rankResult.antiRisk}*100, ${rankResult.fluidity}*100, ${rankResult.stability}*100, ${rankResult.reality}*100];
    var radar_paramter = ["收益分数", "抗风险分数", "流动性分数", "稳定性分数", "实盘分数"];
    createRadarChart("radar", radar, ["1"], radar_paramter);

</script>
</body>
</html>
