<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.edu.nju.asi.model.User" %><%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/13
  Time: 11:25
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

    <link href="../css/index.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>用户管理</title>

    <style rel="stylesheet" type="text/css">


        .table th, .table td {
            text-align: center;
            vertical-align: middle !important;
        }

        .userBlock {
            margin-top: 20px;
            padding-right: 0;
            padding-left: 0;

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

        .panel-body {
            background-color: #F3F3F3;
        }

        .panel-default > .panel-heading {
            background-color: #FFFFF0;
        }

        body {
            margin-top: 60px;
        }

        footer {
            width: 100%;
            height: 70px;
            background-color: #444444;
            margin-top: 70px;
        }

    </style>


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


<div class="row">
    <div class="col-md-2 affix" style="margin-top: 40px">
        <p style="text-align: center"><img src="../img/happy.png" style="margin-left: auto" class="picture"/></p>
        <a href="#" data-toggle="modal" style="text-decoration: none" data-target="#changePassword">
            <div class="userID text-center" style="margin-top: 10px"></div>
        </a>
    </div>


    <div class="panel panel-default col-md-8 col-md-offset-2 userBlock">
        <div class="panel-heading">
            <h3 class="panel_title">
                自选股票
            </h3>
        </div>
        <c:choose>
            <c:when test="${ps_list.size() != 0 && ps_list != null}">
                <%--有自选股数据--%>
                <div class="panel-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <table class="table table-hover table-condensed">
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
                                <tbody id="myStocks"
                                <c:forEach items="${ps_list}" var="stock" varStatus="vs">
                                    <c:choose>
                                        <c:when test="${stock.volume == null}">
                                            <%--此只股票没有当日信息，则仍需显示，只是显示为／--%>
                                            <tr>
                                                <td class="stock_no_value">${stock.stockID.code}</td>
                                                <td class="stock_no_value">${stock.name}</td>
                                                <td class="stock_no_value">/</td>
                                                <td class="stock_no_value">/</td>
                                                <td class="stock_no_value">/</td>
                                                <td class="stock_no_value">/</td>
                                                <td class="stock_no_value">/</td>
                                                <td class="stock_no_value">/</td>
                                                <td class="stock_no_value">/</td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <%--正常显示--%>
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
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <%--没有自选股数据，提示用户选自选股--%>
                <%--TODO 高源 美化一下没有自选股的情况--%>
                <div>亲，去个股界面选两只股票收藏，就可以在这了方便地看到了哦</div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="panel panel-default col-md-8 col-md-offset-2 userBlock">
        <div class="panel-heading">
            <h3 class="panel_title">
                我的策略
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-2 col-md-offset-1">
                    <h5>我的创建</h5>
                </div>
            </div>
            <div style="width:85%;margin:0 auto;2px;margin-bottom: 20px;border-top:1px solid #ddd"></div>

            <div class="row">
                <div class="col-md-offset-1 col-md-10">

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
                                    <img class="img-responsive " src="../img/traceback4.png">
                                </figure>
                                <span class="col-md-4 col-md-offset-1 small">创建日期</span><span class="col-md-6 small">2017-01-31</span>
                                <span class="col-md-4 col-md-offset-1 small">创建者</span><span class="col-md-6 small">qingqing123</span>
                                <span class="col-md-4 col-md-offset-1 small">订阅人数</span><span
                                    class="col-md-5 col-md-offset-1 small">123</span>
                                <button class="searchBt col-md-offset-6 btn btn-sm btn-info">查看详情</button>
                            </div>
                        </div>
                    </div>


                </div>
            </div>

            <div style="width:96%;margin:10px auto;2px;margin-bottom: 30px;border-top:2px solid #ddd"></div>
            <div class="row">
                <div class="col-md-2 col-md-offset-1">
                    <h5>我的收藏</h5>
                </div>
            </div>
            <div style="width:85%;margin:0 auto;2px;margin-bottom: 20px;border-top:1px solid #ddd"></div>

            <div class="row">
                <div class="col-md-offset-1 col-md-10">
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
                                    <img class="img-responsive " src="../img/traceback4.png">
                                </figure>
                                <span class="col-md-4 col-md-offset-1 small">创建日期</span><span class="col-md-6 small">2017-01-31</span>
                                <span class="col-md-4 col-md-offset-1 small">创建者</span><span class="col-md-6 small">qingqing123</span>
                                <span class="col-md-4 col-md-offset-1 small">订阅人数</span><span
                                    class="col-md-5 col-md-offset-1 small">123</span>
                                <button class="searchBt col-md-offset-6 btn btn-sm btn-info">查看详情</button>
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
                                    <img class="img-responsive " src="../img/traceback4.png">
                                </figure>
                                <span class="col-md-4 col-md-offset-1 small">创建日期</span><span class="col-md-6 small">2017-01-31</span>
                                <span class="col-md-4 col-md-offset-1 small">创建者</span><span class="col-md-6 small">qingqing123</span>
                                <span class="col-md-4 col-md-offset-1 small">订阅人数</span><span
                                    class="col-md-5 col-md-offset-1 small">123</span>
                                <button class="searchBt col-md-offset-6 btn btn-sm btn-info">查看详情</button>
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
                                    <img class="img-responsive " src="../img/traceback4.png">
                                </figure>
                                <span class="col-md-4 col-md-offset-1 small">创建日期</span><span class="col-md-6 small">2017-01-31</span>
                                <span class="col-md-4 col-md-offset-1 small">创建者</span><span class="col-md-6 small">qingqing123</span>
                                <span class="col-md-4 col-md-offset-1 small">订阅人数</span><span
                                    class="col-md-5 col-md-offset-1 small">123</span>
                                <button class="searchBt col-md-offset-6 btn btn-sm btn-info">查看详情</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-1 col-md-10">
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
                                    <img class="img-responsive " src="../img/traceback2.png">
                                </figure>
                                <span class="col-md-4 col-md-offset-1 small">创建日期</span><span
                                    class="col-md-6 small">2017-01-31</span>
                                <span class="col-md-4 col-md-offset-1 small">创建者</span><span
                                    class="col-md-6 small">qingqing123</span>
                                <button class="searchBt col-md-offset-6 btn btn-sm btn-info">查看详情</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
</div>


</div>

<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="registerLabel">修改密码</h4>
            </div>
            <div class="modal-body" style="padding-left: 80px">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：</label>
                        <label class="userID" style="margin-left: 20px">12345</label>
                    </div>
                    <div class="form-group form-inline">
                        <label for="password1">输入密码：</label>
                        <input type="password" style="width:200px;" class="form-control" id="password1"
                               name="password1" placeholder="请输入您的密码">
                    </div>
                    <div class="form-group form-inline">
                        <label for="password2">确认密码：</label>
                        <input type="password" style="width:200px;" class="form-control" id="password2"
                               name="password2" placeholder="再次输入您的密码">
                    </div>

                    <input type="submit" class="btn btn-info" onclick="user_modify()"
                           style="margin-top: 15px;margin-left: 80px;" value="确认修改"/>
                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<footer/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>



<script src="../js/strategy.js"></script>

<script type="text/javascript">


    $(document).ready(function () {
        var user = "<%= ((User)session.getAttribute("user")).getUserName()%>";
        $(".userID").html(user);
    });

    $(".strategyPanel").hover(function () {
        $(this).css({"margin-top": "-10px", "margin-bottom": "48px", "cursor": "pointer", "border": "1px solid red"});
    }, function () {
        $(this).css({"margin-top": "0px", "margin-bottom": "40px", "border": "none"});
    });

    $(".strategyPanel").click(function () {
        //TODO  此处需要跳转
        window.location.href = "/jsp/searchStrategy.jsp";
    })

    $("#modifyForm").validate({
        rules: {
            password1: {
                required: true,
                minlength: 5,
                maxlength: 15
            },
            password2: {
                equalTo: "#password1"
            },
        },
        messages: {

            password1: {
                required: "密码不能为空",
                minlength: "密码不能少于5位",
                maxlength: "密码不能高于15位"
            },
            password2: {
                equalTo: "两次密码不一样"
            }
        }
    });
    $("#normalForm").validate({
        rules: {
            phone: {
                minlength: 11,
                maxlength: 11
            },
            email: {
                email: true
            },
        },
        messages: {

            phone: {
                minlength: "手机错误",
                maxlength: "手机错误"

            },
            email: {
                email: "邮箱错误"
            }
        }
    });

    //双击后查看详情

    $("#myStocks").find("tr").dblclick(function () {
        var code = $(this).find("td").eq(0).html();
        window.location.href = "/stocks/" + code;
    });
    $("td").hover(function () {
        $(this).css({"cursor": "default"});
    }, function () {
        $(this).css({"margin-top": "0px", "margin-bottom": "40px", "border": "none"});
    });

</script>

</body>
</html>
