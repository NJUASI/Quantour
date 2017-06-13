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
    <link href="../css/flat/red.css" rel="stylesheet">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/startLoader.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        footer {
            width: 100%;
            height: 100px;
            background-color: #444444;
        }
    </style>

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
        .font-green{
            color:green;
        }
        .font-red{
            color:red;
        }

    </style>

</head>
<body class="loaded">
<header>
    <%@ include file="header.jsp" %>
</header>

<%--<div class="row userBlockLeft">--%>
    <%--<div class="col-md-2 col-lg-offset-6 input-group">--%>
        <%--<input type="text" id="stockText" class="form-control form-inline" >--%>
        <%--<span class="input-group-btn">--%>
                                    <%--<button class="btn btn-default form-control" id="searchButton" type="button">--%>
                                        <%--<span class="glyphicon glyphicon-search"> </span>--%>
                                    <%--</button>--%>
    <%--</span>--%>
    <%--</div>--%>
<%--</div>--%>


<div class="row stock" style="margin-top: 70px">
    <span class="col-md-4 col-md-offset-2"><span id="stockName" style="font-size: 200%">${stockOfEndDay.name}</span>&nbsp;<i style="font-size: 120%" id="stockCode">${stockOfEndDay.stockID.code}</i></span>
    <c:if test="${sessionScope.user!=null}">
        <span id="addBtn" ><button class="btn" style="margin-top:10px "></button></span>
    </c:if>

</div>


<div class="row">
    <ul id="stockDetail" class="col-md-6 col-md-offset-2 list-inline">
        <li><span class="col-md-3" style="font-weight:900">开盘</span> <span class="col-md-offset-2 font-green ">${stockOfEndDay.open}</span></li>
        <li><span class="col-md-3" style="font-weight:900">收盘</span> <span class="col-md-offset-2 font-red ">${stockOfEndDay.close}</span></li>
        <li><span class="col-md-3" style="font-weight:900">最高</span> <span class="col-md-offset-2 font-red ">${stockOfEndDay.high}</span></li>
        <li><span class="col-md-3" style="font-weight:900">最低</span> <span class="col-md-offset-2 font-green ">${stockOfEndDay.low}</span></li>
        <li><span class="col-md-3" style="font-weight:900">昨收 </span><span class="col-md-offset-2 font-black ">${stockOfEndDay.preClose}</span></li>
        <li><span class="col-md-3" style="font-weight:900">成交量</span> <span class="col-md-offset-2 ">${stockOfEndDay.volume}</span></li>
        <li><span class="col-md-3" style="font-weight:900">成交额</span> <span class="col-md-offset-2 ">${stockOfEndDay.transactionAmount}</span></li>
        <li><span class="col-md-3" style="font-weight:900">涨跌幅</span> <span class="col-md-offset-2 ">${stockOfEndDay.increaseMargin}</span></li>
        <li><span class="col-md-3" style="font-weight:900">涨跌额 </span><span class="col-md-offset-2 ">${stockOfEndDay.fluctuation}</span></li>
        <li><span class="col-md-3" style="font-weight:900">换手率</span> <span class="col-md-offset-2 ">${stockOfEndDay.turnoverRate}</span></li>
        <li><span class="col-md-3" style="font-weight:900">总市值</span> <span class="col-md-offset-2 ">${stockOfEndDay.totalValue}</span></li>
        <li><span class="col-md-3" style="font-weight:900">流通值 </span><span class="col-md-offset-2 " >${stockOfEndDay.circulationMarketValue}</span></li>
    </ul>
    <div class="col-md-4" style="margin-top: -70px;margin-left: -80px">
        <div id="one_stock_click_chart" style=" width:300px;height: 200px"></div>
    </div>
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
    <div class="col-lg-1 col-lg-offset-1 userBlockLeft">
        <a class="btn btn-info" onclick="changeSingleStockDetail()" style="margin-top: 25px;margin-left: -40px;">查看区间</a>
    </div>
    <div id="dateError"  class=" row col-md-offset-6 col-md-3" hidden>
        <strong  style="margin-top: 20px;color:red">选择区间没有数据，请重新选择~~~</strong>
    </div>

</div>
<div class="row " style="margin-top: 30px">
    <div class="col-md-offset-2 col-md-1">
        <div>
            <label class="radio1">
                <input class="radio_group1" type="radio" name="upPanel"  value="MA" checked>MA
            </label>
        </div>
    </div>
    <div class="col-md-1">
        <div>
            <label class="radio1">
                <input class="radio_group1" type="radio" name="upPanel" value="BOLL">BOLL
            </label>
        </div>
    </div>
</div>


<div class="row mychart">
    <div id="candlestick_chart" style="margin:0px auto; width:1300px;height:400px" ></div>
</div>

<div class="row mychart" hidden>
    <div id="boll_chart" style="margin:0px auto; width:1300px;height:400px" ></div>
</div>

<div class="row ">
    <div class=" col-md-offset-2 col-md-1">
        <div>
            <label class="radio2">
                <input class="radio_group2" type="radio" name="downPanel"  value="VOLUME" checked>VOLUME
            </label>
        </div>
    </div>
    <div class="col-md-1">
        <div>
            <label class="radio2">
                <input class="radio_group2" type="radio" name="downPanel" value="MACD">MACD
            </label>
        </div>

    </div>
</div>

<div class="row mychart" style="margin-bottom: 50px">
    <div id="volume_chart" style="margin:0px auto; width:1300px;height:200px" ></div>
</div>

<div class="row mychart" hidden  style="margin-bottom: 50px">
    <div id="MACD_chart" style="margin:0px auto; width:1300px;height:200px" ></div>
</div>


<!-- 登录模态框（Modal） -->
<%@ include file="logIn.jsp" %>

<footer>
    <div class="col-md-offset-5">
        <figure  style="width: 200px;height:60px;z-index: 3">
            <img class="img-responsive " src="../img/web_logo.png">
        </figure>
    </div>
</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>


<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="../js/icheck.js"></script>

<script src="../js/dbDatePicker.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>
<script src="../js/echarts-liquidfill.js"></script>

<script src="../js/startLoaded.js"></script>
<script src="../js/logIn.js"></script>
<script src="../js/stocks.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.radio_group1').iCheck({
            checkboxClass: 'icheckbox_flat-red',
            radioClass: 'iradio_flat-red'
        });
        $('.radio_group2').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
//
//        $('.radio1').click(function () {
//            changePanel();
//        })
//
//        $('.radio2').click(function () {
//            changePanel();
//        })
        $('input').on('ifChecked', function(event){
            changePanel();
        });

        function changePanel() {
            var up=$("input[name='upPanel']:checked").val();
            var down=$("input[name='downPanel']:checked").val();
            if(up=="MA"){
                $(".mychart").eq(0).show();
                $(".mychart").eq(1).hide();
            }else{
                $(".mychart").eq(1).show();
                $(".mychart").eq(0).hide();
            }
            if(down=="VOLUME"){
                $(".mychart").eq(2).show();
                $(".mychart").eq(3).hide();
            }else{
                $(".mychart").eq(3).show();
                $(".mychart").eq(2).hide();
            }
        }

        function connect(chart1,chart2){
            echarts.connect([chart1,chart2]);
        }



        var endTime =  ${stockOfEndDay.stockID.date.year} + "-";
        var startTime = ${startDate.year} + "-";

        var month = ${startDate.monthValue};
        var dayOfMonth = ${startDate.dayOfMonth};


        if (month < 10) {
            startTime += "0" + month;
        } else {
            startTime += month;
        }
        if (dayOfMonth < 10) {
            startTime += "-0" + dayOfMonth;
        } else {
            startTime += "-" + dayOfMonth;
        }

        month = ${stockOfEndDay.stockID.date.monthValue};
        dayOfMonth =  ${stockOfEndDay.stockID.date.dayOfMonth};

        if (month < 10) {
            endTime += "0" + month;
        } else {
            endTime += month;
        }
        if (dayOfMonth < 10) {
            endTime += "-0" + dayOfMonth;
        } else {
            endTime += "-" + dayOfMonth;
        }




        $("#datetimeStart>input").attr('value', startTime);
        $("#datetimeEnd>input").attr('value', endTime);

        // 画图
        var bollData = ${bollData};

        var c1 = createCandlestick('candlestick_chart', ${candlestickData});
        var c2 = createVolume('volume_chart', ${volumeData});
        var c3 = createMACD('MACD_chart', ${macdData});
        var c4 = createBULL('boll_chart', ${candlestickData}, bollData[0], bollData[1], bollData[2]);
        connect(c1, c3);
        connect(c2, c3);
        connect(c1, c4);
        connect(c2, c4);
    });
</script>
<script type="text/javascript">


    createClickChart("one_stock_click_chart", ${clickedData}, ${clickedDataStringRepre});
    $(document).ready(
        function() {
            $("#stockText").keydown(function(event) {
                if (event.keyCode == 13) {
//                  添加回测时间
                   //alert(event.target.value);
                }
            });
            $("#searchButton").click(function(event) {
                alert( $("#stockText").val());
            });

            var isPrivate = ${isPrivate};
            if(isPrivate){
                $("#addBtn button").html("取消收藏");
                $("#addBtn").on("click","button", deletePrivateStock);
            }
            else {
                $("#addBtn button").addClass("btn-primary");
                $("#addBtn button").html("加入收藏");
                $("#addBtn").on("click","button", addPrivateStock);
            }
        }
    );

    function addPrivateStock () {
//        alert("添加方法执行了");
        $.ajax({
            type: "get",
            async: true,
            url: "/user/addPrivate/" + $("#stockCode").text(),
            success: function (result) {
                if (result == "1") {
//                    alert("添加成功");
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
//                    alert("删除成功");
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

    $("#stockDetail > li").addClass("col-md-5");


    $("input[name='upPanel']:eq(0)").attr("checked",'checked');
    $("input[name='downPanel']:eq(0)").attr("checked",'checked');

    <%--var candlestickChart = createCandlestickChart("candlestick_chart",${candlestickData},${volumeData});--%>
    function openStock() {
        window.history.go(-1);
    }
    $("#stocks").addClass("act");
</script>
</body>
</html>