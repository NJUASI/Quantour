<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
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
    <link rel="stylesheet" href="../css/bootstrap-select.css">
    <link rel="stylesheet" href="../css/bootstrap-datetimepicker.css">
    <link href="../css/startLoader.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        footer {
            width: 100%;
            height: 100px;
            background-color: #444444;
        }
    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>股票策略</title>

    <style rel="stylesheet" type="text/css">


        .userBlock {
            margin-top: 20px;
            padding-right: 0;
            padding-left: 0;

        }

        .inputBlock {
            margin-top: 20px;
            margin-bottom: 10px;
        }

        .panel-title {
            margin-bottom: 0;
            margin-top: 0;
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
                <li><a id="stocks" style="cursor:
                 pointer">大盘详情</a></li>
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

<form role="form" style="margin-top: 60px;">
    <div class="row">
        <div class="panel panel-default col-lg-10 col-lg-offset-1 userBlock">
            <div class="panel-heading">
                <h4 class="panel-title">
                    选股票池
                </h4>
            </div>
            <div class="panel-body">
                <div class="row">

                    <div class="col-lg-2 col-lg-offset-1 inputBlock">
                        <div class="radio">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios1" value="false" checked>
                                按板块选
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios2" value="true">
                                自选股池
                            </label>
                        </div>
                    </div>

                    <div class="col-md-2 col-lg-offset-1 inputBlock">

                        <label for="blockTypes">板块：</label>
                        <select id="blockTypes" name="blockTypes" class="selectpicker show-tick form-control"
                                multiple data-live-search="false" placeholder="请选择板块">
                            <option value="ZB" selected>主板</option>
                            <option value="ZXB" selected>中小板</option>
                            <option value="CYB" selected>创业板</option>
                        </select>

                    </div>

                    <div class="col-lg-2 col-lg-offset-1 inputBlock">

                        <label>ST：</label>
                        <div style="display: inline">
                            <select id="stType" class="selectpicker show-tick form-control">
                                <option value="INCLUDE" selected>包含ST</option>
                                <option value="EXCLUDE">排除ST</option>
                                <option value="ONLY">仅有ST</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>


    <div class="row">
        <div class="panel panel-default col-lg-10 col-lg-offset-1 userBlock">
            <div class="panel-heading">
                <h4 class="panel-title">
                    回测条件
                </h4>
            </div>
            <div class="panel-body">
                <div class="row">

                    <div class="col-md-2 col-md-offset-1 inputBlock">

                        <label>开始日期：</label>
                        <!--指定 date标记-->
                        <div class='input-group date' id='datetimeStart'>
                            <input id="startDate" type='text' class="form-control form_datetime"/>
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>

                        </div>
                    </div>
                    <div class="col-md-2 col-md-offset-1 inputBlock">
                        <label>结束日期：</label>
                        <!--指定 date标记-->
                        <div class='input-group date' id='datetimeEnd'>
                            <input id="endDate" type='text' class="form-control form_datetime"/>
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>

                        </div>
                    </div>

                    <div class="col-md-2 col-md-offset-1 inputBlock">

                        <label>收益基准：</label>

                        <select id="baseStockEve" name="baseStockEve" class="selectpicker show-tick form-control">
                            <option value="沪深300" selected>沪深300</option>
                            <option value="创业板指">创业板指</option>
                            <option value="中小板指">中小板指</option>
                        </select>
                    </div>

                    <div class="col-md-2 col-md-offset-1 inputBlock">

                        <button type="button" class="btn btn-info" onclick="traceback()"
                                style="margin-top: 15px;margin-left: -40px;">开始回测
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="panel panel-default col-md-10 col-md-offset-1 userBlock">
            <div class="panel-heading">
                <h4 class="panel-title">
                    选择策略
                </h4>
            </div>
            <div class="panel-body inputBlock">
                <div class="row">
                    <div class="col-md-4 col-md-offset-1">
                        <div class="row">
                            <div class="col-md-5"><label>形成指标</label></div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">形成期</div>
                            <div class="col-md-3">
                                <select id="formativePeriod" name="" class="selectpicker show-tick form-control">
                                    <option value="5" selected>5</option>
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="30">30</option>
                                    <option value="60">60</option>
                                </select>
                            </div>
                            <div class="col-md-1">日</div>
                            <div class="col-md-4">
                                <select id="formativeStrategy" name="" class="selectpicker show-tick form-control">
                                    <option value="INCEREASE_AMOUNT" selected>涨幅</option>
                                    <option value="BIAS">乖离率</option>
                                    <option value="VOLUME">成交量</option>

                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-md-5"><label>筛选条件</label></div>
                        </div>
                        <div class="row">

                            <div class="col-md-4">
                                <select id="pickStrategy" name="" class="selectpicker show-tick form-control">
                                    <option value="RANK_MAX_PERCENT" selected>排名最大</option>
                                    <option value="RANK_MIN_PERCENT">排名最小</option>
                                    <option value="RANK_MAX">排名%最大</option>
                                    <option value="RANK_MIN">排名%最小</option>
                                </select>
                            </div>
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-7">
                                        <input id="rank" type="text" class="form-control col-md-1"
                                               name="rank" placeholder="请输入排名条件">
                                    </div>
                                    <div class="col-md-5">
                                        <label for="rank" class="col-md-2">只/%</label>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="row">
                            <div class="col-md-5"><label>调仓周期</label></div>
                        </div>
                        <div class="row">

                            <div class="col-md-9">
                                <div class="row">
                                    <div class="col-md-8">
                                        <input id="holdingPeriod" type="text" class="form-control col-md-1"
                                               name="holdingPeriod" placeholder="请输入天数">
                                    </div>
                                    <div class="col-md-4">
                                        <label for="holdingPeriod" class="col-md-2">天</label>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>

</form>
<div class="row">

    <ul id="myTab" class="col-md-offset-1 col-md-10 nav nav-tabs" role="tablist">
        <li class="active"><a href="#chartPanel" role="tab" data-toggle="tab">收益曲线</a></li>
        <li><a href="#cyclePanel" role="tab" data-toggle="tab">收益周期统计</a></li>
        <li><a href="#holdingDetailPanel" role="tab" data-toggle="tab">交易详情</a></li>
        <li><a href="#certainFormatePanel" role="tab" data-toggle="tab">固定形成期的赢率分析</a></li>
        <li><a href="#certainHoldingPanel" role="tab" data-toggle="tab">固定持有期的赢率分析</a></li>
    </ul>
</div>

<%--<c:choose>--%>
<%--<c:when test="${traceBackNums != null}">--%>
<!-- 选项卡面板 -->
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
                </tbody>
            </table>
        </div>
        <div class="row">
            <div id="trace_back_chart" style="width:1100px;height:500px"></div>
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
            <div class="col-md-12 table-responsive">
                <table class="table table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>周期序号</th>
                        <th>开始日期</th>
                        <th>结束日期</th>
                        <th>股票持有只数</th>
                        <th>策略收益</th>
                        <th>基准收益</th>
                        <th>超额收益</th>
                        <th>模拟投资</th>
                    </tr>
                    </thead>
                    <tbody id="tb_detail">
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <div class="tab-pane" id="certainFormatePanel">

        <div class="row">
            <div class="col-md-4 col-md-offset-1 table-responsive">
                <table class="table table-hover table-condensed">
                    <caption class="text-center"><h3>固定形成期的赢率分析</h3></caption>
                    <thead>
                    <tr>
                        <th>周期</th>
                        <th>超额收益</th>
                        <th>1年内胜率</th>
                    </tr>
                    </thead>
                    <tbody id="tb_certain_formate">
                    </tbody>
                </table>
            </div>
            <div class="col-md-6 col-md-offset-1">
                <div class="row">
                    <div id="formates_excess_chart" style="width:500px;height:400px"></div>
                    <div id="formates_win_chart" style="width:500px;height:400px"></div>

                </div>
            </div>
        </div>

    </div>
    <div class="tab-pane" id="certainHoldingPanel">

        <div class="row">
            <div class="col-md-4 col-md-offset-1 table-responsive">
                <table class="table table-hover table-condensed">
                    <caption class="text-center"><h3>固定持有期的赢率分析</h3></caption>
                    <thead>
                    <tr>
                        <th>周期</th>
                        <th>超额收益</th>
                        <th>1年内胜率</th>
                    </tr>
                    </thead>
                    <tbody id="tb_certain_holding">
                    </tbody>
                </table>
            </div>
            <div class="col-md-6 col-md-offset-1">
                <div class="row">
                    <div id="holdings_excess_chart" style=" width:500px;height:400px"></div>
                    <div id="holdings_win_chart" style=" width:500px;height:400px"></div>
                </div>
            </div>
        </div>

    </div>


</div>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--显示一张图片好了。。--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>

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
                            <input type="text" class="form-control" id="reg_password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="reg_password2" style="padding-left: 0">确认密码：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="reg_password2" placeholder="请再次输入密码">
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


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/traceBack.js"></script>

<script src="../js/echarts.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/startLoaded.js"></script>
<script src="../js/logIn.js"></script>

<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

    var today = new Date();

    var startTime = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + (today.getDate() - 1);
    var endTime = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
    $("#datetimeStart>input").attr('value', startTime);
    $("#datetimeEnd>input").attr('value', endTime);


    $("#datetimeStart").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
        startDate: new Date(2005 - 04 - 03),
        endDate: new Date()
    }).on("click", function () {
        $("#datetimeStart").datetimepicker('setEndDate', $("#datetimeEnd>input").val())
    });
    $("#datetimeEnd").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 'month',
        language: 'zh-CN',
        autoclose: true,
        endDate: new Date()
    }).on("click", function () {
        $("#datetimeEnd").datetimepicker("setStartDate", $("#datetimeStart>input").val())
    });

        $(document).ready(function () {
            $("#modifyBT").click(function () {
            $("#passwordField").toggle("slow");
            $("#passwordModify").toggle("slow");
        });

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

    });


    function modify() {
        $("#passwordField").toggle("slow");
        $("#passwordModify").toggle("slow");
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

//
//    var strategyData = [['2015/7/1', '0'],
//        ['2015/7/2', '0.005'],
//        ['2015/7/3', '0.001'],
//        ['2015/7/4', '-0.002'],
//        ['2015/7/5', '-0.03'],
//        ['2015/7/6', '-0.12'],
//        ['2015/7/7', '-0.05'],
//        ['2015/7/8', '-0.01'],
//        ['2015/7/9', '0.1'],
//        ['2015/7/10', '0.15'],
//        ['2015/7/11', '-0.03'],
//        ['2015/7/12', '-0.09'],
//        ['2015/7/13', '-0.12'],
//        ['2015/7/14', '-0.15'],
//        ['2015/7/15', '0.13'],
//        ['2015/7/16', '0.23'],
//        ['2015/7/17', '0.29'],
//        ['2015/7/18', '0.31'],
//        ['2015/7/19', '0.29'],
//        ['2015/7/20', '0.3'],
//        ['2015/7/21', '0.25'],
//        ['2015/7/22', '0.23'],
//        ['2015/7/23', '0.2'],
//        ['2015/7/24', '0.24'],
//        ['2015/7/25', '0.26'],
//        ['2015/7/26', '0.29'],
//        ['2015/7/27', '0.34'],
//        ['2015/7/28', '0.39'],
//        ['2015/7/29', '0.4'],
//        ['2015/7/30', '0.38'],
//        ['2015/7/31', '0.36']];
//    var baseData = [['2015/7/1', '0'],
//        ['2015/7/2', '0.001'],
//        ['2015/7/3', '0.007'],
//        ['2015/7/4', '-0.012'],
//        ['2015/7/5', '-0.0312'],
//        ['2015/7/6', '-0.23'],
//        ['2015/7/7', '-0.12'],
//        ['2015/7/8', '-0.06'],
//        ['2015/7/9', '0.1'],
//        ['2015/7/10', '0.19'],
//        ['2015/7/11', '0.13'],
//        ['2015/7/12', '0.1'],
//        ['2015/7/13', '0.12'],
//        ['2015/7/14', '0.19'],
//        ['2015/7/15', '0.21'],
//        ['2015/7/16', '0.23'],
//        ['2015/7/17', '0.25'],
//        ['2015/7/18', '0.11'],
//        ['2015/7/19', '0.2'],
//        ['2015/7/20', '0.23'],
//        ['2015/7/21', '0.28'],
//        ['2015/7/22', '0.25'],
//        ['2015/7/23', '0.29'],
//        ['2015/7/24', '0.3'],
//        ['2015/7/25', '0.31'],
//        ['2015/7/26', '0.34'],
//        ['2015/7/27', '0.37'],
//        ['2015/7/28', '0.39'],
//        ['2015/7/29', '0.4'],
//        ['2015/7/30', '0.41'],
//        ['2015/7/31', '0.42']];
//
//    var startX = '2015/7/10';
//    var endX = '2015/7/14';
//    var legend = ['策略', '基准'];
//    var chart = createTraceBackChart('main', strategyData, baseData, legend, startX, endX);



</script>

</body>
</html>
