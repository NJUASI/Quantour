<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cuihua
  Date: 2017/5/13
  Time: 下午7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="../css/stocks.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <title>股票市场</title>
</head>
<header>
    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand brand" href="#">
                    <!-- TODO -->
                    <img alt="Brand" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">首页</a></li>
                <li><a href="/stocks">大盘详情</a></li>
                <li><a href="/trace_back_home">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <li><a href="#" data-toggle="modal" data-target="#login">登录</a></li>
                <li><a href="#">注册</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>
<body>
<div class="content">
    <div class="container">
        <div class="row panel_title_wrapper">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel_title">
                    <div class="row">
                        <div class="col-md-2">
                            <ul class="nav">
                                <li class="curr text-center">沪深行情</li>
                            </ul>
                        </div>
                        <div class="col-md-3 col-md-offset-1">
                            <div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd"
                                 data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input id="stocks_date" class="form-control" size="16" type="text" onclick="getOneDate()">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar" onclick="getOneDate()"></span></span>
                            </div>
                        </div>
                        <div class="col-md-3 col-md-offset-3">
                            <form role="form">
                                <div class="input-group">
                                    <input id="stocks_id" type="text" class="form-control" placeholder="输入代码/简称/拼音">
                                    <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" onclick="getOneStock()">
                                        <span class="glyphicon glyphicon-search"> </span>
                                    </button>
                            </span>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row markets_wrapper">
            <div class="col-md-offset-1 col-md-10">
                <ul class="market">
                    <li class="each_market" id="stocks_shangzheng">
                        <h4>上证指数</h4>
                        <h3>3083.51&nbsp;&nbsp;
                            <small>+22.01&nbsp;&nbsp;</small>
                            <small>+0.71%</small>
                        </h3>
                        <ul class="market-data-extra">
                            <li><span>最高:<span class="market_high">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最低:<span
                                    class="market_low">3051.87</span></span></li>
                            <li><span>涨停:<span class="market_limit_up">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跌停:<span
                                    class="market_limit_down">3051.87</span></span></li>
                            <li><span>成交量:<span class="volume">3090</span></span></li>
                        </ul>
                    </li>
                    <li class="each_market" id="stocks_shenzheng">
                        <h4>深证指数</h4>
                        <h3>3083.51&nbsp;&nbsp;
                            <small>+22.01&nbsp;&nbsp;</small>
                            <small>+0.71%</small>
                        </h3>
                        <ul class="market-data-extra">
                            <li><span>最高:<span class="market_high">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最低:<span
                                    class="market_low">3051.87</span></span></li>
                            <li><span>涨停:<span class="market_limit_up">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跌停:<span
                                    class="market_limit_down">3051.87</span></span></li>
                            <li><span>成交量:<span class="volume">3090</span></span></li>
                        </ul>
                    </li>
                    <li class="each_market" id="stocks_chuangzhi">
                        <h4>创业指数</h4>
                        <h3>3083.51&nbsp;&nbsp;
                            <small>+22.01&nbsp;&nbsp;</small>
                            <small>+0.71%</small>
                        </h3>
                        <ul class="market-data-extra">
                            <li><span>最高:<span class="market_high">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最低:<span
                                    class="market_low">3051.87</span></span></li>
                            <li><span>涨停:<span class="market_limit_up">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跌停:<span
                                    class="market_limit_down">3051.87</span></span></li>
                            <li><span>成交量:<span class="volume">3090</span></span></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="table-responsive">
                    <table class="table table-hover table-condensed">
                        <caption class="text-center"><h3>市场行情</h3></caption>
                        <thead>
                        <tr>
                            <th>代码</th>
                            <th>名称</th>
                            <th>开盘价</th>
                            <th>收盘价</th>
                            <th>最高价</th>
                            <th>最低价</th>
                            <th>昨收</th>
                            <th>交易量</th>
                            <th>交易额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${stockList}" var="stock" varStatus="vs">
                            <tr>
                                <td>${stock.stockID.code}</td>
                                <td>${stock.name}</td>
                                <td>${stock.open}</td>
                                <td>${stock.close}</td>
                                <td class="stock_high">${stock.high}</td>
                                <td class="stock_low">${stock.low}</td>
                                <td>${stock.preClose}</td>
                                <td>${stock.volume}</td>
                                <td>${stock.transactionAmount}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pagination-wrapper text-right">
                    <ul class="pagination">
                        <li><a href="#">&laquo;</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><span>&middot;&middot;&middot;</span></li>
                        <li><a href="#">30</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="candlestick_chart" style="width:100%;height:600px"></div>

<script src="../js/stocks.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>
<script type="text/javascript">
    var candlestickChart = createCandlestickChart("candlestick_chart", ${candlestickData}, ${volumeData});
</script>
<footer>

</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
    $(".form_date").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,//日视图
        minView: 2,//日期时间选择器所能够提供的最精确的时间选择视图0
        pickerPosition: "bottom-left"
    });
    $('.form_date')
        .datetimepicker()
        .on('changeDate', function (ev) {
            alert("今天是" + ev.date.valueOf());
        });
</script>
</body>
</html>