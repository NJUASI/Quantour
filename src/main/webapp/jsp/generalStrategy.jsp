<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/6/3
  Time: 15:31
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
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        footer {
            margin-top: 100px;
            width: 100%;
            height: 100px;
            background-color: #444444;
        }

        body {
            margin-top: 80px;
        }

        .strategyPanel {
            background-color: white;
            border: none;
            margin-bottom: 40px;
            border-radius: 10px;
        }

        .searchBt {
            margin-top: 15px;
            margin-bottom: 15px;
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
    <title>股票策略</title>
</head>

<body>
<header>
    <%@ include file="header.jsp" %>
</header>

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align:center">
        <h3>全部策略</h3>
    </div>
</div>
<div style="width:85%;margin:0 auto;2px;margin-bottom: 20px;border-top:1px solid #ddd"></div>

<div class="row" style="margin-bottom: 500px">
    <div class="col-md-offset-1 col-md-10">

        <c:choose>
            <c:when test="${generalStrategies!=null && generalStrategies.size() != 0}">

                <c:forEach items="${generalStrategies}" var="strategy" varStatus="vs">
                    <div class="col-md-3">
                        <div class="strategyPanel">
                            <div class="row">
                                <h5 class="col-md-offset-1 col-md-11 strategyID" style="font-size: 130%">${strategy.strategyID}</h5>
                                <%--<ul class="col-md-7 col-md-offset-2" style="z-index: 5">--%>
                                    <%--<li class="baseStock" style="font-size: 90%; color:red">--%>
                                            <%--${strategy.baseStockName}--%>
                                    <%--</li>--%>
                                    <%--<li class="" style="font-size: 90%; color:blueviolet">--%>
                                        <%--策略收益率--%>
<%--/                                        TODO gaoyuan 小图--%>
                                        <%--${strategy.baseCumulativeReturnChart}--%>
                                        <%--${strategy.strategyCumulativeReturnChart}--%>
                                    <%--</li>--%>
                                <%--</ul>--%>

                                <c:choose>
                                    <c:when test="${strategy.annualizedRateOfReturn=='-1'}">
                                        <div class="col-md-12" class="" style="width: 100%;height:20px;z-index: 3">
                                            <p class='col-md-offset-2' style="color:red">正在创建策略</p>
                                        </div>
                                        <span class="col-md-4 col-md-offset-2"  style="font-size: 85%;font-weight: bold">创建日期</span><span class="col-md-4 small" style="text-align: right">${strategy.createDate}</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">创建者</span><span class="col-md-4 small" style="text-align: right">${strategy.creator}</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">年化收益率</span><span class="col-md-4 small" style="text-align: right">......</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">最大回撤率</span><span class="col-md-4 small" style="text-align: right">......</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">订阅人数</span><span  class="col-md-4 small" style="text-align: right">......</span>

                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-12" class="" style="width: 100%;height:20px;z-index: 3">

                                        </div>
                                        <span class="col-md-4 col-md-offset-2"  style="font-size: 85%;font-weight: bold">创建日期</span><span class="col-md-4 small" style="text-align: right">${strategy.createDate}</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">创建者</span><span class="col-md-4 small" style="text-align: right">${strategy.creator}</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">年化收益率</span><span class="col-md-4 small" style="text-align: right">${strategy.annualizedRateOfReturn}</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">最大回撤率</span><span class="col-md-4 small" style="text-align: right">${strategy.maxStrategyTraceBackRate}</span>
                                        <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">订阅人数</span><span  class="col-md-4 small" style="text-align: right">${strategy.subscribeNum}</span>

                                    </c:otherwise>
                                </c:choose>

                                <button class="searchBt col-md-offset-7 btn btn-sm btn-info">查看详情</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>

                <div class="row" style="margin-top: 20px;">
                    <p style="text-align: center"><img src="../img/sad.png" style="margin-left: auto"
                                                       class="picture"/></p>
                    <div class="text-center" style="margin-top: 20px;margin-bottom: 40px">
                        亲，网站还没有人上传策略，你可以自己创建一支哦~~~
                    </div>
                </div>
            </c:otherwise>
        </c:choose>




    </div>
</div>

<%@ include file="logIn.jsp" %>

<footer class="">
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


<script src="../js/echarts.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/logIn.js"></script>
<script src="../js/startLoaded.js"></script>


<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

    $(".strategyPanel").hover(function () {
        $(this).css({"margin-top": "-10px", "margin-bottom": "50px", "cursor": "pointer"});
    }, function () {
        $(this).css({"margin-top": "0px", "margin-bottom": "40px"});
    });

    $(".strategyPanel").click(function () {
//        alert($(this).find(".strategyID").eq(0).html());
        var strategyID=$(this).find(".strategyID").eq(0).html();
        window.location.href = "/strategy/" + strategyID;
    });

    $("#stocks").click(function() {
        $("body").removeClass('loaded');
        window.location.href = "/stocks"
        $("#stocks").unbind("click");
    });

    $("#community").addClass("act");
</script>

</body>
</html>

