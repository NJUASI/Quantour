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
    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="../css/stocks.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/startLoader.css" rel="stylesheet">
    <title>股票市场</title>
</head>
<header>
    <nav class="navbar navbar-default nav-wrapper navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand brand" href="#">
                    <img alt="Quantour" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">首页</a></li>
                <li><a href="/stocks">大盘详情</a></li>
                <li><a href="/trace_back">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <c:choose>
                    <c:when test="${sessionScope.user!=null}">
                        <li><a href="/user/welcome">用户管理</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#" data-toggle="modal" data-target="#login">登录</a></li>
                        <li><a href="#" data-toggle="modal" data-target="#register">注册</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>
<body class="loaded" style="margin-top: 60px;">
<div class="content">
    <div class="container">
        <div class="row panel_title_wrapper" style="z-index: 5;">
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
                                <input id="stocks_date" class="form-control" size="16" type="text"/>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                        <div class="col-md-3 col-md-offset-1">
                            <form role="form">
                                <div class="input-group" style="position: relative">
                                    <input type="text" id="search-input" class="form-control" placeholder="输入代码/简称/拼音">
                                    <span class="input-group-btn">
                                    <button class="btn btn-default form-control search-btn" type="button"
                                            onclick="getSingleStockDetail()">
                                        <span class="glyphicon glyphicon-search"> </span>
                                    </button>
                                    </span>


                                </div>
                                <div class="searchResults  pre-scrollable"
                                     style="position: absolute;display: none;width: 300px;max-height: 200px; background-color: whitesmoke;z-index: 20">
                                    <table class="table table-hover table-bordered search-table">
                                        <thead class="search-table-head">
                                        <tr>
                                            <th width="60px">代码</th>
                                            <th width="70px">名称</th>
                                            <th>简称</th>
                                            <th>类型</th>
                                        </tr>
                                        </thead>
                                        <tbody id="search-body">
                                        <tr class="colomnsOfTable">
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>

                        <div class="col-md-1">
                            <button type="button" class="btn btn-primary" onclick="directToCompare()">对比</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row markets_wrapper " style="z-index:3">
            <div class="col-md-offset-1 col-md-10">
                <div id="myCarousel" class="carousel slide" style="padding-top: 15px;padding-bottom: 15px">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators" style="top:-15px">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <li data-target="#myCarousel" data-slide-to="3"></li>
                        <li data-target="#myCarousel" data-slide-to="4"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->

                    <%--TODO 高源 没数据的话怎么显示--%>
                    <div class="carousel-inner row">
                        <c:forEach items="${base_stock_list}" var="base_stock" varStatus="vs">
                            <div class="item col-md-offset-4 col-md-6" >
                                <div class="row">
                                    <h4 class="stockName">${base_stock.name}</h4>
                                </div>
                                <div class="row">
                                    <h3 class="col-md-5" style="margin-top: 0px"><span class="volume">${base_stock.volume}</span></h3>

                                    <h4 class="col-md-4" style="margin-top: 5px"><span class="transactionAmount">${base_stock.transactionAmount}</span></h4>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">开盘:<span class="market_open">${base_stock.open}</span></div>
                                    <div class="col-md-4">最高:<span class="market_high">${base_stock.high}</span></div>
                                    <div class="col-md-4">涨跌幅:<span class="increaseMargin">${base_stock.increaseMargin}</span></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">收盘:<span class="market_close">${base_stock.close}</span></div>
                                    <div class="col-md-4">最低:<span class="market_low">${base_stock.low}</span></div>
                                    <div class="col-md-4">涨跌额:<span class="fluctuation">${base_stock.fluctuation}</span></div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="carousel-control left" href="#myCarousel"
                       data-slide="prev">
                    </a>
                    <a class="carousel-control right" href="#myCarousel"
                       data-slide="next">
                    </a>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-1">
                <table class="table table-hover" style="text-align: center">
                    <caption><h4  style="text-align: center">热搜榜</h4></caption>
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>股票</th>
                        <th>点击量</th>
                    </tr>
                    </thead>
                    <tbody id="rank-list">
                    <%--<c:foreach items="${topClicksChartData}" var="topClickDate" varStatus="vs">--%>
                        <%--<tr>--%>
                            <%--<td>1</td>--%>
                            <%--<td>${topClickDate[0]}</td>--%>
                            <%--<td>${topClickDate[1]}</td>--%>
                        <%--</tr>--%>
                    <%--</c:foreach>--%>
                    <c:set var="index" value="1" />
                    <c:forEach items="${topClicks}" var="topStock"  varStatus="vs">
                        <tr>
                            <td>${index}</td>
                            <td>${topStock.searchID.code} ${topStock.searchID.name}</td>
                            <td>${topStock.clickAmount}</td>
                        </tr>
                        <c:set var="index" value="${index+1}" />
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="col-md-6 col-md-offset-1" id="hot_search_chart" style="width: 40%;height: 420px">

            </div>
        </div>

        <%--TODO 高源 没数据的话怎么显示--%>
        <div class="row" style="z-index:3">
            <div class="col-md-10 col-md-offset-1">
                <div class="table-responsive">
                    <table class="table table-hover table-condensed stocks-table">
                        <caption class="text-center" id="headTitle"><h3>市场行情</h3></caption>
                        <thead>
                        <tr>
                            <th width="10%"><span class="cTable">代码</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">名称</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">开盘价</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">收盘价</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">最高价</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">最低价</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">昨收</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">交易量</span><span class="tLabel"></span></th>
                            <th width="10%"><span class="cTable">交易额</span><span class="tLabel"></span></th>
                        </tr>
                        </thead>
                        <tbody id="stocks_all">

                        <c:forEach items="${stock_list}" var="stock" varStatus="vs">
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
            </div>
        </div>
        <div class="row">
            <div class="col-md-5 col-md-offset-4">
                <ul class="pagination">
                    <li class="active"><a>1</a></li>
                    <%--<c:if test="${totalPageNum}>9">--%>

                    <%--</c:if>--%>

                </ul>
            </div>
            <div class=" row col-md-3" style="margin-top: 21px">
                <input type="text" id="goPage"  style="max-width:20%;padding-left:4px;margin-right:10px;padding-right: 4px ;text-align: center"
                       value=1 class="form-control col-md-2"/>
                <button class="btn btn-default col-md-3" id="pageChoose" type="button">
                    <a href="#headTitle" style='text-decoration:none;'><span style="color:blueviolet">跳转</span></a>
                </button>
            </div>


        </div>
    </div>
</div>


<!-- 登录模态框（Modal） -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="loginLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="login_username">用户名：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="login_username" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="login_password">密码：</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="login_password" placeholder="请输入密码">
                        </div>
                    </div>
                    <p class="col-md-offset-3" id="errorMessageField"></p>
                    <div class="checkbox text-right">
                        <label id="rem-password">
                            <input type="checkbox" checked>记住密码
                        </label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="login-btn-group">
                    <button type="button" class="btn btn-primary" onclick="login()">登录</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="registerLabel">注册</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="reg_username">用户名：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="reg_username" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="reg_password">密码：</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="reg_password2" style="padding-left: 0">确认密码：</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password2" placeholder="请再次输入密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="login-btn-group">
                    <button type="button" class="btn btn-primary" onclick="register()">注册</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
</div>

<footer>
</footer>

<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>
<script src="../js/stocks.js"></script>
<script src="../js/echarts-liquidfill.js"></script>
<script src="../js/startLoaded.js"></script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../js/logIn.js"></script>
<script type="text/javascript">

    // 画出热搜榜的图
    createPieChart("hot_search_chart", ${topClicksChartData});

    <%--alert(${topClicks.});--%>
    var pagetol=${totalPageNum};
    if (pagetol <= 9) {
        for (var i = 2; i <= pagetol; i++) {
            $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + i + "</a></li>");
        }
    } else {
        for (var i = 2; i <= 8; i++) {
            $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + i + "</a></li>");
        }
        $(".pagination").append("<li><a class=\"\" href=\"#headTitle\">&middot;&middot;&middot;</a></li>");
        $(".pagination").append(" <li><a   href=\"#headTitle\" class=\"\">" + pagetol + "</a></li>");
    }


    $(document).ready(
        function () {
            var date = ${date.year}+"-";
            var month=${date.monthValue};
            var dayOfMonth=${date.dayOfMonth};
            if( month<10){
                date+="0"+month;
            }else{
                date+=month;
            }
            if(dayOfMonth<10){
                date+="-0"+dayOfMonth;
            }else{
                date+="-"+dayOfMonth;
            }
            $(".form_date > input").attr('value', date);


            $('#search-input').bind('input propertychange', function () {

                var key = $('#search-input').val();

                $.ajax({
                    type: "get",
                    async: true,
                    url: "/stocks/search?key=" + key,

                    success: function (result) {
//                        alert(result);
                        if (result == "-1") {
                            // 调取失败处理

                        }

                        var obj = eval("(" + result + ")");
                        var len = obj.length;
                        $("#search-body").empty();
                        if(len>10){
                            len=10;
                        }
                        for (var i = 0; i < len; i++) {
                            $("#search-body").append("<tr class='colomnsOfTable' style='cursor: default'><td>" + obj[i]["searchID"]["code"] + "</td><td style='font-size: 14px;'>" + obj[i]["searchID"]["name"] + "</td>" +
                                "<td>" + obj[i]["firstLetters"] + "</td><td>" + obj[i]["searchID"]["market"] + "</td></tr>");
                        }

                        $("#search-body").find(".colomnsOfTable").click(function () {
                            var num = ($(".colomnsOfTable").index($(this)));
                            var code = $("#search-body").find(".colomnsOfTable").eq(num).find("td").eq(0).html();
                            var name = $("#search-body").find(".colomnsOfTable").eq(num).find("td").eq(1).html();

                            $("#search-input").val(code+" "+name);
                            $(".searchResults").hide();
                            $("#search-input").focus();
                        });

                        $("#search-body").find("tr").hover(function () {
                            $(this).css("background-color", "#A3B1D1");
                        }, function () {
                            $(this).css("background-color", "#F5F5F5");
                        });

                        $(".searchResults").show();
                    }
                })
            });

//            $("tbody").click(function () {
//
//                      var code = $(this).find("td:first").text();
//                            $("#search-input").html(code);
//            });


            $(".searchResults").click(function (e) {
                e.stopPropagation();
            });

            $(document).click(function () {
                $(".searchResults").hide();
            });

        }
    );

    $(".form_date").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,//日视图
        minView: 2,//日期时间选择器所能够提供的最精确的时间选择视图0
        pickerPosition: "bottom-left",
        startDate: new Date(2012 - 01 - 01),
        endDate: new Date(),
        daysOfWeekDisabled: [0, 6]
    });


    $("th").find("span").css("cursor", "pointer");
    var nowPage = 1;
    var numOfColumn = 0;
    var numOfClick = 1;
    var sortNum = 0;
    $("th").find("span").click(function () {
        if (($(".cTable").index($(this))) != numOfColumn) {
            numOfColumn = ($(".cTable").index($(this)));
            numOfClick = 1;
        } else if (numOfClick == 1) {
            numOfClick = 2;
        } else if (numOfClick == 2) {
            numOfClick = 1;
        }
        for (var i = 0; i < 16; i++) {
            $("thead>tr>th").eq(i).find(".tlabel").removeClass("glyphicon glyphicon-chevron-up");
            $("thead>tr>th").eq(i).find(".tlabel").removeClass("glyphicon glyphicon-chevron-down");
        }

        if (numOfClick == 1) {
            $("thead>tr>th").eq(numOfColumn + 7).find(".tlabel").addClass("glyphicon glyphicon-chevron-up");
        } else if (numOfClick == 2) {
            $("thead>tr>th").eq(numOfColumn + 7).find(".tlabel").addClass("glyphicon glyphicon-chevron-down");
        }

        sortNum = numOfColumn * 2 + numOfClick - 1;

//        TODO fjj 当sortNum等于16或者17的时候不能排序，就是最后一列
//        alert(sortNum);
//        alert("你第"+numOfClick+"次点了第"+numOfColumn+"列"+nowPage+"页");
//        alert(sortNum);
        updatePanel();
    });

    //设置第一个显示
    $(".item").eq(0).addClass("active");
    //开始轮播
    $('.carousel').carousel();

    $("#pageChoose").click(function () {
        var pageNumber=$("#goPage").val();
        nowPage=pageNumber;
        updatePanel();
    });

    $(".pagination").find("li").click(function () {
        if ($(this).find("a").html() == "···") {
//            alert($(this).next("li").find("a").html());
            if (($(this).next("li").find("a").html()) == totalPageNum) {
                nowPage = curPageNum + 4;
            } else {
                nowPage = curPageNum - 4;
            }
        } else {
            nowPage = $(this).find("a").html();
        }
        updatePanel();
    });

    $('.form_date')
        .datetimepicker().on("changeDate", function () {
//        alert($(".form_date > input").val());
        $("body").removeClass("loaded");
        nowPage = 1;
        sortNum=0;
        updatePanel();
    });
//通过界面的数据post更改界面内容
    function updatePanel() {
        $.ajax({
            type: "post",
            async: true,
            url: "/stocks",
            data: {
                "date": $(".form_date > input").val(),
                "sortCriteria": sortNum,
                "wantedPage": nowPage
            },

            success: function (result) {
//                alert(result);
                $("body").addClass("loaded");
                var array = result.split(";");

                if (array[0] == "1") {
                    // js修改jsp中数据

                    // 处理图表数据
                    var stock_page = eval("(" + array[1] + ")");

                    var numOfEachPage = stock_page["numOfEachPage"];
                    var curPageNum = stock_page["curPageNum"];
                    var totalPageNum = stock_page["totalPageNum"];
                    var totalRecordNum = stock_page["totalRecordNum"];

                    var baseStocks = stock_page["baseStocks"];
                    for(var j=0;j<baseStocks.length;j++){
                        $(".stockName").eq(j).html(baseStocks[j]["name"]);
                        $(".volume").eq(j).html(baseStocks[j]["volume"]);
                        $(".transactionAmount").eq(j).html(baseStocks[j]["transactionAmount"]);
                        $(".market_open").eq(j).html(baseStocks[j]["open"]);
                        $(".market_close").eq(j).html(baseStocks[j]["close"]);
                        $(".market_high").eq(j).html(baseStocks[j]["high"]);
                        $(".market_low").eq(j).html(baseStocks[j]["low"]);
                        $(".increaseMargin").eq(j).html(baseStocks[j]["increaseMargin"]);
                        $(".fluctuation").eq(j).html(baseStocks[j]["fluctuation"]);
                    }
//                <h4>上证指数</h4>
//                    <h3>3083.51&nbsp;&nbsp;
//                <small>+22.01&nbsp;&nbsp;</small>
//                    <small>+0.71%</small>
//                    </h3>
//                    <ul class="market-data-extra">
//
//                        <li><span>涨停:<span class="market_limit_up">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跌停:<span
//                class="market_limit_down">3051.87</span></span></li>
//                        <li><span>成交量:<span class="volume">3090</span></span></li>
//                        </ul>
                    var stocks = stock_page["stocks"];
                    $(".pagination").empty();
                    if (totalPageNum <= 9) {
                        for (var i = 1; i <= totalPageNum; i++) {
                            if (curPageNum == i) {
                                $(".pagination").append(" <li class=\"active\"><a  href=\"#headTitle\" >" + i + "</a></li>");
                            } else {
                                $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + i + "</a></li>");
                            }
                        }
                    } else {
//                        alert(curPageNum);
                        if (curPageNum <= 5) {
                            for (var i = 1; i <= 9; i++) {
                                if (curPageNum == i) {
                                    $(".pagination").append(" <li class=\" active\"><a  href=\"#headTitle\" >" + i + "</a></li>");
                                } else {
                                    $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + i + "</a></li>");
                                }
                            }
                            $(".pagination").append("<li value=\"right\"><a class=\"\" href=\"#headTitle\">&middot;&middot;&middot;</a></li>");
                            $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + totalPageNum + "</a></li>");
                        } else if (curPageNum >= totalPageNum - 4) {
                            $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + 1 + "</a></li>");
                            $(".pagination").append("<li value=\"left\"><a class=\"\" href=\"#headTitle\">&middot;&middot;&middot;</a></li>");
                            for (var i = totalPageNum - 7; i <= totalPageNum; i++) {
                                if (curPageNum == i) {
                                    $(".pagination").append(" <li class=\" active\"><a  href=\"#headTitle\" >" + i + "</a></li>");
                                } else {
                                    $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + i + "</a></li>");
                                }
                            }
                        } else {
                            $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + 1 + "</a></li>");
                            $(".pagination").append("<li value=\"left\"><a class=\"\" href=\"#headTitle\">&middot;&middot;&middot;</a></li>");

                            for (var i = curPageNum - 3; i <= curPageNum + 3; i++) {
                                if (curPageNum == i) {
                                    $(".pagination").append(" <li class=\"active\"><a  href=\"#headTitle\" >" + i + "</a></li>");
                                } else {
                                    $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + i + "</a></li>");
                                }
                            }

                            $(".pagination").append("<li><a class=\"\" href=\"#headTitle\">&middot;&middot;&middot;</a></li>");
                            $(".pagination").append(" <li><a  href=\"#headTitle\" class=\"\">" + totalPageNum + "</a></li>");
                        }

                    }
                    $("#goPage").val(curPageNum);
                    $(".pagination").find("li").click(function () {

                        if ($(this).find("a").html() == "···") {

                            if (($(this).next("li").find("a").html()) == totalPageNum) {
                                nowPage = curPageNum + 4;
                            } else {
                                nowPage = curPageNum - 4;
                            }
                        } else {
                            nowPage = $(this).find("a").html();
                        }
                        updatePanel();
                    });
                    $("#stocks_all").empty();

                    for (var i = 0; i < stocks.length; i++) {
                        $("#stocks_all").append("<tr><td>" + stocks[i]["stockID"]["code"] + "</td>" +
                            "<td>" + stocks[i]["name"] + "</td>" +
                            "<td>" + stocks[i]["open"] + "</td>" +
                            "<td>" + stocks[i]["close"] + "</td>" +
                            "<td class='stock_high'>" + stocks[i]["high"] + "</td>" +
                            "<td class='stock_low'>" + stocks[i]["low"] + "</td>" +
                            "<td>" + stocks[i]["preClose"] + "</td>" +
                            "<td>" + stocks[i]["volume"] + "</td>" +
                            "<td>" + stocks[i]["transactionAmount"] + "</td></tr>");
                    }
                    $("#stocks_all").find("tr").dblclick(function () {
                        var code=$(this).find("td").eq(0).html();
                        window.location.href = "/stocks/" + code;
                    });


                    // 处理表格数据
                    var top_search_chart_data = array[2];
                    createPieChart("hot_search_chart", top_search_chart_data);

                } else if (array[0] == "-1") {
                    // 提示错误信息
                    alert(array[1]);
                } else {
                    alert("未知错误类型orz");
                }
            },
            error: function (result) {
//                alert(JSON.stringify(result));
                alert("错误" + result);
            }
        });
    }
//查看股票详情
    function getSingleStockDetail() {
        var str = $("#search-input").val();
        var wantedStockCode=str.split(" ")[0];
       // alert(wantedStockCode);
        window.location.href = "/stocks/" + wantedStockCode;
    }

    $("#search-input").keyup(function(event) {
        if (event.keyCode == 13) {
            var str = $("#search-input").val();
            var wantedStockCode=str.split(" ")[0];
            window.location.href = "/stocks/" + wantedStockCode;
        }
    });
//双击后查看详情
    $("#rank-list").find("tr").dblclick(function () {
        var code=$(this).find("td").eq(1).html().split(" ")[0];
        window.location.href = "/stocks/" + code;
    });

    $("#stocks_all").find("tr").dblclick(function () {
        var code=$(this).find("td").eq(0).html();
        window.location.href = "/stocks/" + code;
    });
    $("td").hover(function () {
        $(this).css({"cursor": "default"});
    }, function () {
        $(this).css({"margin-top": "0px", "margin-bottom": "40px","border": "none"});
    });
</script>
</body>
</html>