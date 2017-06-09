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
    <nav class="navbar navbar-default nav-wrapper navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand brand" href="#">
                    <img alt="Quantour" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a id="homePage" href="/">首页</a></li>
                <li><a id="stocks" onclick="openStock()" style="cursor: pointer">大盘详情</a></li>
                <li class="dropdown">
                    <a href="##" class="dropdown-toggle" id="community" data-toggle="dropdown">量化社区<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" style="left:15px;max-width: 100px">
                        <li><a href="/trace_back">创建策略</a></li>
                        <li><a href="/jsp/generalStrategy.jsp">使用策略</a></li>
                    </ul>
                </li>
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

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align:center">
        <h3>热门策略</h3>
    </div>
</div>
<div style="width:85%;margin:0 auto;2px;margin-bottom: 20px;border-top:1px solid #ddd"></div>

<div class="row">
    <div class="col-md-offset-1 col-md-10">
        <div class="col-md-4">
            <div class="strategyPanel">
                <div class="row">
                    <h5 id="strategyName" class="col-md-offset-1 col-md-11" style="font-size: 130%">中小股策略</h5>
                    <ul class="col-md-7 col-md-offset-2" style="z-index: 5">
                        <li class="" style="font-size: 90%; color:red">
                            沪深300
                        </li>
                        <li class="" style="font-size: 90%; color:blueviolet">
                            策略收益率
                        </li>
                    </ul>
                    <figure class="col-md-12" style="width: 100%;margin-top: -30px;z-index: 3">
                        <img class="img-responsive " src="../img/traceback3.png">
                    </figure>
                    <span class="col-md-3 col-md-offset-4 small">创建日期</span><span
                        class="col-md-5 small">2017-01-31</span>
                    <span class="col-md-3 col-md-offset-4 small">创建者</span><span
                        class="col-md-5 small">qingqing123</span>
                    <span class="col-md-3 col-md-offset-4 small">订阅人数</span><span
                        class="col-md-4 col-md-offset-1 small">123</span>
                    <button class="searchBt col-md-offset-8 btn btn-sm btn-info">查看详情</button>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="strategyPanel">
                <div class="row">
                    <h5 class="col-md-offset-1 col-md-11" style="font-size: 130%">中小股策略</h5>
                    <ul class="col-md-7 col-md-offset-2" style="z-index: 5">
                        <li class="" style="font-size: 70%; color:red">
                            沪深300
                        </li>
                        <li class="" style="font-size: 70%; color:blueviolet">
                            策略收益率
                        </li>
                    </ul>
                    <figure class="col-md-12" style="width: 100%;margin-top: -30px;z-index: 3">
                        <img class="img-responsive " src="../img/traceback3.png">
                    </figure>
                    <span class="col-md-3 col-md-offset-4 small">创建日期</span><span
                        class="col-md-5 small">2017-01-31</span>
                    <span class="col-md-3 col-md-offset-4 small">创建者</span><span
                        class="col-md-5 small">qingqing123</span>
                    <span class="col-md-3 col-md-offset-4 small">订阅人数</span><span
                        class="col-md-4 col-md-offset-1 small">123</span>
                    <button class="searchBt col-md-offset-8 btn btn-sm btn-info">查看详情</button>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="strategyPanel">
                <div class="row">
                    <h5 class="col-md-offset-1 col-md-11" style="font-size: 130%">中小股策略</h5>
                    <ul class="col-md-7 col-md-offset-2" style="z-index: 5">
                        <li class="" style="font-size: 70%; color:red">
                            沪深300
                        </li>
                        <li class="" style="font-size: 70%; color:blueviolet">
                            策略收益率
                        </li>
                    </ul>
                    <figure class="col-md-12" style="width: 100%;margin-top: -30px;z-index: 3">
                        <img class="img-responsive " src="../img/traceback3.png">
                    </figure>
                    <span class="col-md-3 col-md-offset-4 small">创建日期</span><span
                        class="col-md-5 small">2017-01-31</span>
                    <span class="col-md-3 col-md-offset-4 small">创建者</span><span
                        class="col-md-5 small">qingqing123</span>
                    <span class="col-md-3 col-md-offset-4 small">订阅人数</span><span
                        class="col-md-4 col-md-offset-1 small">123</span>
                    <button class="searchBt col-md-offset-8 btn btn-sm btn-info">查看详情</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align:center">
        <h3>全部策略</h3>
    </div>
</div>
<div style="width:85%;margin:0 auto;2px;margin-bottom: 20px;border-top:1px solid #ddd"></div>

<div class="row">
    <div class="col-md-offset-1 col-md-10">
<%--TODO fjj debug--%>
    <c:forEach items="${generalStrategies}" var="strategy" varStatus="vs">
        <div class="col-md-3">
            <div class="strategyPanel">
                <div class="row">
                    <h5 class="col-md-offset-1 col-md-11" style="font-size: 130%">${strategy.strategyID}</h5>
                    <ul class="col-md-7 col-md-offset-2" style="z-index: 5">
                        <li class="" style="font-size: 90%; color:red">
                            沪深300
                        </li>
                        <li class="" style="font-size: 90%; color:blueviolet">
                            策略收益率
                        </li>
                    </ul>
                    <figure class="col-md-12" style="width: 100%;margin-top: -30px;z-index: 3">
                        <img class="img-responsive " src="../img/traceback4.png">
                    </figure>
                    <span class="col-md-4 col-md-offset-1 font-size: 85%;">创建日期</span><span class="col-md-6 small">2017-01-31</span>
                    <span class="col-md-4 col-md-offset-1 font-size: 85%;">创建者</span><span class="col-md-6 small">qingqing123</span>
                    <span class="col-md-4 col-md-offset-1 font-size: 85%;">订阅人数</span><span
                        class="col-md-5 col-md-offset-1 small">123</span>
                    <button class="searchBt col-md-offset-6 btn btn-sm btn-info">查看详情</button>
                </div>
            </div>
        </div>
    </c:forEach>
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
        //TODO  此处需要跳转
        window.location.href = "/jsp/searchStrategy.jsp";
    })
    function openStock() {
        $("body").removeClass('loaded');
        window.location.href = "/stocks"
    }
    $("#community").addClass("act");
</script>

</body>
</html>

