<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/14
  Time: 19:04
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
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/startLoader.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        footer {
            width: 100%;
            height: 100px;
            background-color: #444444;
        }
    </style>

    <![endif]-->
    <title>个股</title>

    <style rel="stylesheet" type="text/css">

        .stock {
            font-family: "Microsoft YaHei";
            font-size: large;
            font-weight: bold;
            padding-bottom: 30px;
            padding-top: 50px;
        }

        .userBlockLeft {
            margin-top: 60px;
        }
    </style>

</head>
<body class="loaded">
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
                <li><a id="stocks">大盘详情</a></li>
                <li><a href="/trace_back_home">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <c:choose>
                    <c:when test="${sessionScope.user!=null}">
                        <li><a href="/welcome">用户管理</a></li>
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

<div class="row userBlockLeft">
    <div class="col-md-2 col-lg-offset-6 input-group">
        <input type="text" id="stockText" class="form-control form-inline" >
        <span class="input-group-btn">
                                    <button class="btn btn-default form-control" id="searchButton" type="button">
                                        <span class="glyphicon glyphicon-search"> </span>
                                    </button>
    </span>
    </div>
</div>


<div class="row stock" style="margin-top: -60px">
    <span class="col-md-2 col-md-offset-2"><span id="stockName">${dataOfEndDay.name}</span>&nbsp;<i id="stockCode">${dataOfEndDay.stockID.code}</i></span>
    <span id="addBtn"><button class="btn"></button></span>
</div>


<div class="row">
    <ul id="stockDetail" class="col-md-6 col-md-offset-2 list-inline">
        <li>开盘 <span class=" font-green ">${dataOfEndDay.open}</span></li>
        <li>最高 <span class=" font-red ">${dataOfEndDay.high}</span></li>
        <li>最低 <span class=" font-green ">${dataOfEndDay.low}</span></li>
        <li>昨收 <span class=" font-black ">${dataOfEndDay.preClose}</span></li>
        <li>成交量 <span>${dataOfEndDay.volume}</span></li>
        <li>成交额 <span>${dataOfEndDay.transactionAmount}</span></li>
        <li>涨跌幅 <span>${dataOfEndDay.increaseMargin}</span></li>
        <li>涨跌额 <span>${dataOfEndDay.fluctuation}</span></li>
        <li>换手率 <span>${dataOfEndDay.turnoverRate}</span></li>
        <li>总市值 <span>${dataOfEndDay.totalValue}</span></li>
        <li>流通市值 <span>${dataOfEndDay.circulationMarketValue}</span></li>
    </ul>
</div>


<div class="row">

    <div class="col-lg-2 col-lg-offset-2 userBlockLeft">

        <label>开始日期：</label>
        <!--指定 date标记-->
        <div class='input-group date' id='datetimeStart'>
            <input type='text' class="form-control form_datetime"/>
            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
        </div>
    </div>
    <div class="col-lg-2 userBlockLeft">
        <label>结束日期：</label>
        <!--指定 date标记-->
        <div class='input-group date' id='datetimeEnd'>
            <input type='text' class="form-control form_datetime"/>
            <span class="input-group-addon">
                       <span class="glyphicon glyphicon-calendar"></span>
                </span>

        </div>
    </div>
    <div class="col-lg-2 col-lg-offset-1 userBlockLeft">
        <a class="btn btn-info" onclick="getSingleStockDetail()" style="margin-top: 15px;margin-left: -40px;">查看区间</a>
    </div>
</div>

<div class="row" style="margin-top: 60px">
    <div class="col-md-offset-1 col-md-10">
        <div id="candlestick_chart"class="col-md-12" style=" height: 600px"></div>
    </div>
</div>

<footer>

</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>


<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>
<script src="../js/startLoaded.js"></script>

<script type="text/javascript">

    $(document).ready(
        function() {
            $("#stockText").keydown(function(event) {
                if (event.keyCode == 13) {
//                  添加回测时间
                    alert(event.target.value);
                }
            });
            $("#searchButton").click(function(event) {
                alert( $("#stockText").val());
            });

            var isPrivate = ${isPrivate};
            if(isPrivate){
                alert("已收藏");
                $("#addBtn button").html("取消收藏");
                $("#addBtn").on("click","button", deletePrivateStock);
            }
            else {
                alert("未收藏");
                $("#addBtn button").addClass("btn-primary");
                $("#addBtn button").html("加入收藏");
                $("#addBtn").on("click","button", addPrivateStock);
            }
        }
    );

    $("#stocks").click(function () {
        $("body").removeClass("loaded");
        $.ajax({
            type: "post",
            async: true,
            url: "/stocks",

            success: function (result) {
                var array = result.split(";");

                if (array[0] == "1") {
                    window.location.href = "/stocks";
                } else if (array[0] == "-1") {
                    // 提示错误信息
                    alert(array[1]);
                } else {
                    alert("未知错误类型orz");
                }
            },
            error: function (result) {
                alert("错误" + result);
            }
        }) ;
    });

    function addPrivateStock () {
//        alert("添加方法执行了");
        $.ajax({
            type: "get",
            async: true,
            url: "/user/addPrivate/" + $("#stockCode").text(),
            success: function (result) {
                if (result == "1") {
                    alert("添加成功");
                    $("#addBtn button").html("取消收藏");
//                    $("#addBtn").unbind("click",addPrivateStock());
                    $("#addBtn button").removeClass("btn-primary");
                    $("#addBtn").off("click","button");
                    $("#addBtn").on("click","button",deletePrivateStock);
//                    $("#addBtn").bind("click",deletePrivateStock());
                }
                else {
                    alert("添加失败");
                }
            }
        });
    };

    function deletePrivateStock () {
//        alert("删除方法执行了");
        $.ajax({
            type: "get",
            async: true,
            url: "/user/deletePrivate/" + $("#stockCode").text(),
            success: function (result) {
                if (result == "1") {
                    alert("删除成功");
                    $("#addBtn button").html("加入收藏");
                    $("#addBtn button").addClass("btn-primary");
                    $("#addBtn").off("click","button");
                    $("#addBtn").on("click","button",addPrivateStock);
                }
                else {
                    alert("删除失败");
                }
            }
        });
    };

    $("#stockDetail > li").addClass("col-md-3");

    var startTime = ${dataOfStartDay.stockID.date.year} + "-" + ${dataOfStartDay.stockID.date.monthValue} + "-" + ${dataOfStartDay.stockID.date.dayOfMonth};
    var endTime = ${dataOfEndDay.stockID.date.year} + "-" + ${dataOfEndDay.stockID.date.monthValue} + "-" + ${dataOfEndDay.stockID.date.dayOfMonth};
    $("#datetimeStart>input").attr('value', startTime);
    $("#datetimeEnd>input").attr('value', endTime);

    $("#datetimeStart").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
        //数据从12年开始
        startDate: new Date(2012 - 01 - 01),
        endDate: new Date(),
        daysOfWeekDisabled: [0,6]
    });

    $("#datetimeEnd").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
        startDate: new Date(2012 - 01 - 01),
        endDate: new Date(),
        daysOfWeekDisabled: [0,6]
    });

    var data1 = ${candlestickData};
    var data2 = ${volumeData};

    createCandlestickChart('candlestick_chart',data1,data2);
    <%--var candlestickChart = createCandlestickChart("candlestick_chart",${candlestickData},${volumeData});--%>

    function getSingleStockDetail() {
        var wantedStockCode = $("#stockCode").text();
//        alert("查看股票：" + wantedStockCode);

        var start = $("#datetimeStart > input").val();
        var end = $("#datetimeEnd > input").val();

//        alert(start);

        $.ajax({
            type: "post",
            async: true,
            url: "/stocks/" + wantedStockCode,
            data:{
                "startDate": start,
                "endDate": end
            },

            success: function (result) {
                var array = result.split(";");

                if (array[0] == "1") {
                    window.location.href = "/stocks/" + wantedStockCode;
                } else if (array[0] == "-1") {
                    // 提示错误信息
                    alert(array[1]);
                } else {
                    alert("未知错误类型orz");
                }
            },
            error: function (result) {
                alert("错误" + result);
            }
        });
    }
</script>
</body>
</html>