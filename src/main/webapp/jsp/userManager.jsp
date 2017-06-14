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

    <link href="../css/startLoader.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/stocks.css" rel="stylesheet">

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
    <%@ include file="header.jsp" %>
</header>


<div class="row">
    <div class="col-md-2 affix" id="modifyInfo" style="margin-top: 40px">
        <p style="text-align: center"><img src="../img/happy.png" style="margin-left: auto" class="picture"/></p>
            <div class="userID text-center"   style="margin-top: 10px"></div>
    </div>


    <div class="panel panel-default col-md-9 col-md-offset-2 userBlock">
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
                                                <td class="stock_no_value"><span class="code" style="color:#7291CA">${stock.stockID.code}</span></td>
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
                                                <td><span class="code" style="color:7291CA">${stock.stockID.code}</span></td>
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
                <div class="panel-body">
                    <div class="row" style="margin-top: 20px;">
                        <p style="text-align: center"><img src="../img/sad.png" style="margin-left: auto"
                                                           class="picture"/></p>
                        <div class="text-center" style="margin-top: 20px;margin-bottom: 40px">
                            亲，去大盘详情个股界面选两只股票收藏，就可以在这了方便地看到了哦~~~
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="panel panel-default col-md-9 col-md-offset-2 userBlock">
        <div class="panel-heading">
            <h3 class="panel_title">
                我的策略
            </h3>
        </div>
        <div class="panel-body">

            <ul class="nav nav-pills  col-md-offset-1  col-md-10" role="tablist" style="margin-bottom: 10px">
                <li class="active"><a href="#bulletin" role="tab" data-toggle="pill">我的创建</a></li>
                <li><a href="#rule" role="tab" data-toggle="pill">我的收藏</a></li>
            </ul>
            <!-- 选项卡面板 -->
            <div class="tab-content col-md-offset-1 col-md-10">
                <div class="tab-pane fade in active" id="bulletin">
                    <c:choose>
                        <c:when test="${myOwn.size()!=0}">
                                    <c:forEach items="${myOwn}" var="strategy" varStatus="vs">
                                        <div class="col-md-4">
                                            <div class="strategyPanel">
                                                <div class="row">
                                                    <h5 class="col-md-offset-1 col-md-11 strategyID" style="font-size: 130%">${strategy.strategyID}</h5>
                                                    <div class="col-md-12" class="" style="width: 100%;height:20px;z-index: 3">

                                                    </div>
                                                    <span class="col-md-4 col-md-offset-2"  style="font-size: 85%;font-weight: bold">创建日期</span><span class="col-md-4 small" style="text-align: right">${strategy.createDate}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">创建者</span><span class="col-md-4 small" style="text-align: right">${strategy.creator}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">年化收益率</span><span class="col-md-4 small" style="text-align: right">${strategy.annualizedRateOfReturn}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">最大回撤率</span><span class="col-md-4 small" style="text-align: right">${strategy.maxStrategyTraceBackRate}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">订阅人数</span><span  class="col-md-4 small" style="text-align: right">${strategy.subscribeNum}</span>
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
                                    亲，量化社区创建策略，就可以在这了方便地查看管理了哦~~~
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="tab-pane fade" id="rule">
                    <c:choose>
                        <c:when test="${mySubscribe.size()!=0}">
                                    <c:forEach items="${mySubscribe}" var="strategy" varStatus="vs">
                                        <div class="col-md-4">
                                            <div class="strategyPanel">
                                                <div class="row">
                                                    <h5 class="col-md-offset-1 col-md-11 strategyID" style="font-size: 130%">${strategy.strategyID}</h5>

                                                    <div class="col-md-12" class="" style="width: 100%;height:20px;z-index: 3">

                                                    </div>
                                                    <span class="col-md-4 col-md-offset-2"  style="font-size: 85%;font-weight: bold">创建日期</span><span class="col-md-4 small" style="text-align: right">${strategy.createDate}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">创建者</span><span class="col-md-4 small" style="text-align: right">${strategy.creator}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">年化收益率</span><span class="col-md-4 small" style="text-align: right">${strategy.annualizedRateOfReturn}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">最大回撤率</span><span class="col-md-4 small" style="text-align: right">${strategy.maxStrategyTraceBackRate}</span>
                                                    <span class="col-md-4 col-md-offset-2" style="font-size: 85%;font-weight: bold">订阅人数</span><span  class="col-md-4 small" style="text-align: right">${strategy.subscribeNum}</span>
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
                                    亲，量化社区订阅策略，就可以在这了方便地查看使用了哦~~~
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>












        </div>
    </div>
</div>
</div>


</div>

<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 30%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="registerLabel">修改密码</h4>
            </div>
            <div class="modal-body" style="padding-left: 80px">
                <form id="modifyForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="password1" style="padding-left: 0">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：</label>
                        <div class="col-md-7">
                            <label class="userID" style="margin-left: 20px">12345</label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-md-3 control-label" for="password1" style="padding-left: 0">用户密码：</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="password1" name="password1" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="password2" style="padding-left: 0">确认密码：</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="password2" name="reg_password2" placeholder="请再次输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="e_mail" style="padding-left: 0">电子邮箱：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="e_mail" name="e_mail" placeholder="请输入邮箱">
                        </div>
                    </div>

                    <input type="submit" class="btn btn-info" onclick="user_modify()"
                           style="margin-top: 15px;margin-left: 80px;" value="确认修改"/>
                </form>
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

<script src="../js/startLoaded.js"></script>
<script src="../js/strategy.js"></script>

<script type="text/javascript">

    $("#modifyInfo").click(function () {
        $("#changePassword").modal("toggle");
    });
    $("#modifyInfo").hover(function () {
        $(this).css({"cursor": "pointer"});
    }, function () {
    });
    $(document).ready(function () {
        var user = "<%= ((User)session.getAttribute("user")).getUserName()%>";
        $(".userID").html(user);
        $(".userID").click(function () {
            var password = "<%= ((User)session.getAttribute("user")).getPassword()%>";
            $("#password1").val(password);
            $("#password2").val(password);
            var email="<%= ((User)session.getAttribute("user")).getEmail()%>";
            $("#email").val(email);
        });
        $("#password1").click(function () {
            $("#password1").val("");
        });
        $("#password2").click(function () {
            $("#password2").val("");
        });
    });

    $(".strategyPanel").hover(function () {
        $(this).css({"margin-top": "-10px", "margin-bottom": "50px", "cursor": "pointer"});
        $(this).addClass("box-shadow");
    }, function () {
        $(this).css({"margin-top": "0px", "margin-bottom": "40px", "border": "none"});
        $(this).remove("box-shadow");
    });

    $(".strategyPanel").click(function () {
//        alert($(this).find(".strategyID").eq(0).html());
        var strategyID=$(this).find(".strategyID").eq(0).html();
        window.location.href = "/strategy/" + strategyID;
    });

    function user_modify() {
        $("#passwordField").toggle("slow");
        $("#passwordModify").toggle("slow");

//        TODO 冯俊杰 用户修改时好像调用有点问题  明明成功了却返回error。。。
        $("#password1").val();
        $("#e_mail").val();

        var modified_user = {
            "userName": "<%=((User) session.getAttribute("user")).getUserName()%>",
            "password": $("#password1").val(),
            "email": $("#e_mail").val()
        };


        $.ajax({
            type: "post",
            async: true,
            url: "/user/modify",
            data: {
                "user":JSON.stringify(modified_user)
            },

            success: function (result) {
                var array = result.split(";");

                if (array[0] == "1") {
                    // TODO gaoyuan 登录成功用我说的那个小动画
                    alert("66666666");
                    window.location.reload();
                } else if (array[0] == "-1") {
                    // 提示错误信息
                    $("#errorMessageField2").html(array[1]);
                } else {
                    $("#errorMessageField2").html("请再次确定输入信息");
                }
            },
            error: function (result) {
                alert("----错误" + result);
            }
        });

    }

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
            e_mail: {
                email: true
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
            },
            e_mail: {
                email: "邮箱错误"
            }

        }
    });





    //双击后查看详情

    $("#myStocks").find("tr").dblclick(function () {
        var code = $(this).find(".code").eq(0).html();
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
    $("#stocks").click(function() {
        $("body").removeClass('loaded');
        window.location.href = "/stocks"
        $("#stocks").unbind("click");
    });

    $("#userManager").addClass("act");
</script>

</body>
</html>
