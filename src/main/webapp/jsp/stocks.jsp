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
    <link href="../css/bootstrap-select.css" rel="stylesheet">
    <%--<link href="../css/reset.css" rel="stylesheet">--%>
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/startLoader.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        .tabPanel {
            background-color: #f7f7f7;
            border: none;
            margin-bottom: 40px;
            border-radius: 10px;
        }
    </style>
    <title>股票市场</title>
</head>
<header>
    <%@ include file="header.jsp" %>
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
                            <form id="inputcode" role="form">
                                <div class="input-group" style="position: relative">
                                    <input type="text" id="search-input" name="search_input" class="form-control"
                                           placeholder="输入代码/简称/拼音">
                                    <span class="input-group-btn">
                                    <button class="btn btn-default form-control search-btn" type="button"
                                            onclick="getSingleStockDetail()">
                                        <span class="glyphicon glyphicon-search"> </span>
                                    </button>
                                    </span>

                                </div>
                                <strong id="inputMessage" style="color: indianred;"></strong>
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

        <div class="row" id="dateMessage" style="margin-top: 60px;margin-bottom: 200px;">
            <p style="text-align: center"><img src="../img/sad.png" style="margin-left: auto"
                                               class="picture"/></p>
            <div class="text-center" style="margin-top: 20px;margin-bottom: 40px">
                亲，由于当天停盘没有数据，请选择其他日期~~~
            </div>
        </div>
        <div id="analysePanel">
            <div class="row markets_wrapper " style="z-index:3">
                <div class="col-md-offset-3 col-md-6">
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
                                <div class="item  col-md-12">
                                    <div class="row">
                                        <h4 class="stockName">${base_stock.name}</h4>
                                    </div>
                                    <div class="row">
                                        <h3 class="col-md-5" style="margin-top: 0px"><span
                                                class="volume">${base_stock.volume}</span></h3>

                                        <h4 class="col-md-4" style="margin-top: 5px"><span
                                                class="transactionAmount">${base_stock.transactionAmount}</span></h4>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">开盘:<span class="market_open">${base_stock.open}</span>
                                        </div>
                                        <div class="col-md-4">最高:<span class="market_high">${base_stock.high}</span>
                                        </div>
                                        <div class="col-md-4">涨跌幅:<span
                                                class="increaseMargin">${base_stock.increaseMargin}</span></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">收盘:<span class="market_close">${base_stock.close}</span>
                                        </div>
                                        <div class="col-md-4">最低:<span class="market_low">${base_stock.low}</span></div>
                                        <div class="col-md-4">涨跌额:<span
                                                class="fluctuation">${base_stock.fluctuation}</span></div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- 轮播（Carousel）导航 -->
                        <%--<a class="carousel-control left" href="#myCarousel"--%>
                        <%--data-slide="prev">--%>
                        <%--</a>--%>
                        <%--<a class="carousel-control right" href="#myCarousel"--%>
                        <%--data-slide="next">--%>
                        <%--</a>--%>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-md-4 col-md-offset-1">
                    <table class="table table-hover" style="text-align: center">
                        <caption><h4 style="text-align: center">热搜榜</h4></caption>
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
                        <c:set var="index" value="1"/>
                        <c:forEach items="${topClicks}" var="topStock" varStatus="vs">
                            <tr>
                                <td>${index}</td>
                                <td>${topStock.searchID.code} ${topStock.searchID.name}</td>
                                <td>${topStock.clickAmount}</td>
                            </tr>
                            <c:set var="index" value="${index+1}"/>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="col-md-6 col-md-offset-1" id="hot_search_chart" style="width: 40%;height: 420px">

                </div>
            </div>


            <div class="text-center" id="headTitle"><h3>市场行情</h3></div>
            <ul class="nav nav-pills  col-md-offset-1  col-md-10" role="tablist" style="margin-bottom: 10px">
                <li class="active"><a href="#bulletin" onclick="stocks_on_all()" role="tab" data-toggle="pill">全部股票</a>
                </li>
                <li><a href="#rule" role="tab" data-toggle="pill">按行业板块</a></li>
                <li><a href="#forum" role="tab" data-toggle="pill">按地域板块</a></li>
            </ul>
            <!-- 选项卡面板 -->
            <div class="tab-content col-md-offset-1 col-md-10">
                <div class="tab-pane fade in active" id="bulletin"></div>
                <div class="tab-pane fade" id="rule">
                    <div class="tabPanel">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1" id="industryTab">
                                <div><a class="industryOfStock">全部</a></div>
                                <div><a class="industryOfStock">金融行业</a></div>
                                <div><a class="industryOfStock">房地产</a></div>
                                <div><a class="industryOfStock">综合行业</a></div>
                                <div><a class="industryOfStock">建筑建材</a></div>
                                <div><a class="industryOfStock">玻璃行业</a></div>
                                <div><a class="industryOfStock">家电行业</a></div>
                                <div><a class="industryOfStock">纺织行业</a></div>
                                <div><a class="industryOfStock">食品行业</a></div>
                                <div><a class="industryOfStock">电子信息</a></div>
                                <div><a class="industryOfStock">交通运输</a></div>
                                <div><a class="industryOfStock">汽车制造</a></div>
                                <div><a class="industryOfStock">商业百货</a></div>
                                <div><a class="industryOfStock">电力行业</a></div>
                                <div><a class="industryOfStock">酒店旅游</a></div>
                                <div><a class="industryOfStock">机械行业</a></div>
                                <div><a class="industryOfStock">农林牧渔</a></div>
                                <div><a class="industryOfStock">电器行业</a></div>
                                <div><a class="industryOfStock">电子器件</a></div>
                                <div><a class="industryOfStock">石油行业</a></div>
                                <div><a class="industryOfStock">有色金属</a></div>
                                <div><a class="industryOfStock">生物制药</a></div>
                                <div><a class="industryOfStock">医疗器械</a></div>
                                <div><a class="industryOfStock">物资外贸</a></div>
                                <div><a class="industryOfStock">传媒娱乐</a></div>
                                <div><a class="industryOfStock">发电设备</a></div>
                                <div><a class="industryOfStock">水泥行业</a></div>
                                <div><a class="industryOfStock">塑料制品</a></div>
                                <div><a class="industryOfStock">钢铁行业</a></div>
                                <div><a class="industryOfStock">化纤行业</a></div>
                                <div><a class="industryOfStock">农药化肥</a></div>
                                <div><a class="industryOfStock">公路桥梁</a></div>
                                <div><a class="industryOfStock">造纸行业</a></div>
                                <div><a class="industryOfStock">化工行业</a></div>
                                <div><a class="industryOfStock">环保行业</a></div>
                                <div><a class="industryOfStock">煤炭行业</a></div>
                                <div><a class="industryOfStock">酿酒行业</a></div>
                                <div><a class="industryOfStock">供水供气</a></div>
                                <div><a class="industryOfStock">开发区</a></div>
                                <div><a class="industryOfStock">印刷包装</a></div>
                                <div><a class="industryOfStock">纺织机械</a></div>
                                <div><a class="industryOfStock">仪器仪表</a></div>
                                <div><a class="industryOfStock">飞机制造</a></div>
                                <div><a class="industryOfStock">其它行业</a></div>
                                <div><a class="industryOfStock">家具行业</a></div>
                                <div><a class="industryOfStock">摩托车</a></div>
                                <div><a class="industryOfStock">服装鞋类</a></div>
                                <div><a class="industryOfStock">陶瓷行业</a></div>
                                <div><a class="industryOfStock">船舶制造</a></div>
                                <div><a class="industryOfStock">次新股</a></div>
                                <div><a class="industryOfStock">其他</a></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="forum">
                    <div class="tabPanel">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1" id="provinceTab">
                                <div><a class="provinceOfStock">全部</a></div>
                                <div><a class="provinceOfStock">深圳</a></div>
                                <div><a class="provinceOfStock">北京</a></div>
                                <div><a class="provinceOfStock">吉林</a></div>
                                <div><a class="provinceOfStock">江苏</a></div>
                                <div><a class="provinceOfStock">辽宁</a></div>
                                <div><a class="provinceOfStock">广东</a></div>
                                <div><a class="provinceOfStock">浙江</a></div>
                                <div><a class="provinceOfStock">湖南</a></div>
                                <div><a class="provinceOfStock">河北</a></div>
                                <div><a class="provinceOfStock">新疆</a></div>
                                <div><a class="provinceOfStock">山东</a></div>
                                <div><a class="provinceOfStock">河南</a></div>
                                <div><a class="provinceOfStock">山西</a></div>
                                <div><a class="provinceOfStock">江西</a></div>
                                <div><a class="provinceOfStock">安徽</a></div>
                                <div><a class="provinceOfStock">湖北</a></div>
                                <div><a class="provinceOfStock">内蒙</a></div>
                                <div><a class="provinceOfStock">海南</a></div>
                                <div><a class="provinceOfStock">四川</a></div>
                                <div><a class="provinceOfStock">重庆</a></div>
                                <div><a class="provinceOfStock">陕西</a></div>
                                <div><a class="provinceOfStock">广西</a></div>
                                <div><a class="provinceOfStock">福建</a></div>
                                <div><a class="provinceOfStock">天津</a></div>
                                <div><a class="provinceOfStock">云南</a></div>
                                <div><a class="provinceOfStock">贵州</a></div>
                                <div><a class="provinceOfStock">甘肃</a></div>
                                <div><a class="provinceOfStock">黑龙江</a></div>
                                <div><a class="provinceOfStock">宁夏</a></div>
                                <div><a class="provinceOfStock">青海</a></div>
                                <div><a class="provinceOfStock">上海</a></div>
                                <div><a class="provinceOfStock">西藏</a></div>
                                <div><a class="provinceOfStock">其他</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="z-index:3">
                <div class="col-md-10 col-md-offset-1">
                    <div class="table-responsive">
                        <table class="table table-hover table-condensed stocks-table">
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
                                    <td> <span class="code" style="color:#7291CA">${stock.stockID.code}</span></td>
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
                    <input type="text" id="goPage"
                           style="max-width:20%;padding-left:4px;margin-right:10px;padding-right: 4px ;text-align: center"
                           value=1 class="form-control col-md-2"/>
                    <button class="btn btn-default col-md-3" id="pageChoose" type="button">
                        <a href="#headTitle" style='text-decoration:none;'><span style="color:blueviolet">跳转</span></a>
                    </button>
                </div>


            </div>
        </div>
    </div>
</div>


<!-- 登录模态框（Modal） -->

<%@ include file="logIn.jsp" %>

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
<script src="../js/bootstrap-select.js"></script>
<script src="../js/logIn.js"></script>
<script src="../js/jquery.validate.js"></script>
<script type="text/javascript">

   if(${base_stock_list == null}){
       $("#analysePanel").hide();
   }else{
       $("#dateMessage").hide();
   }
    // 画出热搜榜的图
    createPieChart("hot_search_chart", ${topClicksChartData});

    <%--alert(${topClicks.});--%>
    var pagetol =${totalPageNum};
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
            $("body").removeClass('loaded');

            var date = ${date.year}+"-";
            var month =${date.monthValue};
            var dayOfMonth =${date.dayOfMonth};
            if (month < 10) {
                date += "0" + month;
            } else {
                date += month;
            }
            if (dayOfMonth < 10) {
                date += "-0" + dayOfMonth;
            } else {
                date += "-" + dayOfMonth;
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
                        if (len > 10) {
                            len = 10;
                        }
                        for (var i = 0; i < len; i++) {
                            $("#search-body").append("<tr class='colomnsOfTable' style='cursor: default'><td>" + obj[i]["searchID"]["code"] + "</td><td style='font-size: 14px;'>" + obj[i]["searchID"]["name"] + "</td>" +
                                "<td>" + obj[i]["firstLetters"] + "</td><td>" + obj[i]["searchID"]["market"] + "</td></tr>");
                        }

                        $("#search-body").find(".colomnsOfTable").click(function () {
                            var num = ($(".colomnsOfTable").index($(this)));
                            var code = $("#search-body").find(".colomnsOfTable").eq(num).find("td").eq(0).html();
                            var name = $("#search-body").find(".colomnsOfTable").eq(num).find("td").eq(1).html();

                            $("#search-input").val(code + " " + name);
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
            $("body").addClass('loaded');
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

    var areaType = '全部';
    var industryType = '全部';


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


        updatePanel();
    });

    //设置第一个显示
    $(".item").eq(0).addClass("active");
    //开始轮播
    $('.carousel').carousel();

    $("#pageChoose").click(function () {
        var pageNumber = $("#goPage").val();
        nowPage = pageNumber;
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

    $('.form_date').datetimepicker().on("changeDate", function () {
//        alert($(".form_date > input").val());
        $("body").removeClass("loaded");
        nowPage = 1;
        sortNum = 0;
        updatePanel();
    });

    //通过界面的数据post更改界面内容
    function updatePanel() {
        $("body").addClass('loaded');
        $.ajax({
            type: "post",
            async: true,
            url: "/stocks",
            data: {
                "date": $(".form_date > input").val(),
                "sortCriteria": sortNum,
                "wantedPage": nowPage,
                "industryType": industryType,
                "areaType": areaType
            },

            success: function (result) {
//                alert(result);
                $("body").addClass("loaded");
                var array = result.split(";");

                if (array[0] == "1") {
                    // js修改jsp中数据

                    // 处理表格数据
                    var stock_page = eval("(" + array[1] + ")");

                    var numOfEachPage = stock_page["numOfEachPage"];
                    var curPageNum = stock_page["curPageNum"];
                    var totalPageNum = stock_page["totalPageNum"];
                    var totalRecordNum = stock_page["totalRecordNum"];

                    var baseStocks = stock_page["baseStocks"];
                    for (var j = 0; j < baseStocks.length; j++) {
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
                        $("#stocks_all").append("<tr><td><span class=\"code\" style=\"color:#7291CA\">" + stocks[i]["stockID"]["code"] + "</span>" +
                            "</td>" +
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
                        var code = $(this).find("td").eq(0).html();
                        window.location.href = "/stocks/" + code;
                    });

                    $(".code").click(function () {
                        window.location.href = "/stocks/" + $(this).html();
                    })

                    $(".code").hover(function () {
                        $(this).css({"cursor": "pointer","text-decoration":" underline"});
                    }, function () {
                        $(this).css({"color":"#7291CA","text-decoration":" none"});
                    });

                    // 处理热搜榜图表数据
                    var top_search_chart_data = array[2];
                    createPieChart("hot_search_chart", top_search_chart_data);

                    $("#analysePanel").show();
                    $("#dateMessage").hide();
                } else if (array[0] == "-1") {
                    $("#analysePanel").hide();
                    $("#dateMessage").show();
                } else if (array[0] == "-2") {
                    $("#analysePanel").hide();
                    $("#dateMessage").show();
                } else {
                    $("#analysePanel").hide();
                    $("#dateMessage").show();
                }
            },
            error: function (result) {
//                alert(JSON.stringify(result));
                alert("错误" + result);
            }
        });
        $("body").removeClass('loaded');
    }
    //查看股票详情
    function getSingleStockDetail() {
        var str = $("#search-input").val();
        var wantedStockCode = str.split(" ")[0];

        var reg = /^\d{6}.*$/;
        if (wantedStockCode == "") {
            $("#inputMessage").html("<span class=\"glyphicon glyphicon glyphicon-remove-circle\"></span>代码不能为空！");
            setTimeout("$('#inputMessage').html('');", 2000);
            return false;
        } else if (!reg.test(wantedStockCode)) {
            $("#inputMessage").html("<span class=\"glyphicon glyphicon glyphicon-remove-circle\"></span>代码输入不正确！");
            setTimeout("$('#inputMessage').html('');", 2000);
            return false;
        }

        // alert(wantedStockCode);
        window.location.href = "/stocks/" + wantedStockCode;
    }

    $("#search-input").keyup(function (event) {
        if (event.keyCode == 13) {

            var str = $("#search-input").val();
            var wantedStockCode = str.split(" ")[0];
            var reg = /^\d{0,6}.*$/;
            if (wantedStockCode == "") {
                $("#inputMessage").html("代码不能为空！");
                setTimeout("$('#inputMessage').html('');", 1000);
                return false;
            } else if (!reg.test(wantedStockCode)) {
                $("#inputMessage").html("代码输入不正确！");
                setTimeout("$('#inputMessage').html('');", 1000);
                return false;
            } else {
                window.location.href = "/stocks/" + wantedStockCode;
            }
        }else{

        }
    });
    //双击后查看详情
    $("#rank-list").find("tr").dblclick(function () {
        var code = $(this).find("td").eq(1).html().split(" ")[0];
        window.location.href = "/stocks/" + code;
    });

    $("#stocks_all").find("tr").dblclick(function () {
        var code = $(this).find("td").eq(0).html();
        window.location.href = "/stocks/" + code;
    });

   $(".code").click(function () {
       window.location.href = "/stocks/" + $(this).html();
   })

   $(".code").hover(function () {
       $(this).css({"cursor": "pointer","text-decoration":" underline"});
   }, function () {
       $(this).css({"color":"#7291CA","text-decoration":" none"});
   });
//    $("td").hover(function () {
//        $(this).css({"cursor": "default"});
//    }, function () {
//        $(this).css({"margin-top": "0px", "margin-bottom": "40px", "border": "none"});
//    });

    $("#industryTab").find("div").addClass("col-md-2");
    $("#industryTab").find("div").css({"padding": "1px", "margin-top": "3px"});
    $("#industryTab").find("a").css({
        "color": "currentColor",
        "text-decoration": "none",
        "margin": "1px",
        "padding": "1px"
    });
    $(".industryOfStock").eq(0).css({"background-color": "#337ab7", "color": "white"});

    $("#provinceTab").find("div").addClass("col-md-2");
    $("#provinceTab").find("div").css({"padding": "1px", "margin-top": "3px"});
    $("#provinceTab").find("a").css({
        "color": "currentColor",
        "text-decoration": "none",
        "margin": "1px",
        "padding": "1px"
    });
    $(".provinceOfStock").eq(0).css({"background-color": "#337ab7", "color": "white"});

    $(".industryOfStock").css("border", "1px solid #F7F7F7");
    $(".industryOfStock").hover(function () {
        $(this).css({"cursor": "pointer", "border": "1px solid red"});
    }, function () {
        $(this).css({"border": "1px solid #F7F7F7"});
    });
    $(".provinceOfStock").css("border", "1px solid #F7F7F7");
    $(".provinceOfStock").hover(function () {
        $(this).css({"cursor": "pointer", "border": "1px solid red"});
    }, function () {
        $(this).css({"border": "1px solid #F7F7F7"});
    });

    $(".industryOfStock").click(function () {
        $(".provinceOfStock").css({"background-color": "#F7F7F7", "color": "currentColor"});
        $(".provinceOfStock").eq(0).css({"background-color": "#337ab7", "color": "white"});
        $(".industryOfStock").css({"background-color": "#F7F7F7", "color": "currentColor"});
        $(this).css({"background-color": "#337ab7", "color": "white"});

        industryType = $(this).html();
        areaType = '全部';
        updatePanel();
    });

    $(".provinceOfStock").click(function () {
        $(".industryOfStock").css({"background-color": "#F7F7F7", "color": "currentColor"});
        $(".industryOfStock").eq(0).css({"background-color": "#337ab7", "color": "white"});
        $(".provinceOfStock").css({"background-color": "#F7F7F7", "color": "currentColor"});
        $(this).css({"background-color": "#337ab7", "color": "white"});

        areaType = $(this).html();
        industryType = '全部';
        updatePanel();
    });


    function stocks_on_all() {
        $(".industryOfStock").css({"background-color": "#F7F7F7", "color": "currentColor"});
        $(".industryOfStock").eq(0).css({"background-color": "#337ab7", "color": "white"});

        $(".provinceOfStock").css({"background-color": "#F7F7F7", "color": "currentColor"});
        $(".provinceOfStock").eq(0).css({"background-color": "#337ab7", "color": "white"});
        industryType = '全部';
        areaType = '全部';
        updatePanel();
    }

    function openStock() {
        $("body").removeClass('loaded');
        window.location.href = "/stocks"
    }
    $("#stocks").addClass("act");

</script>
</body>
</html>