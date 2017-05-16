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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>股票策略</title>

    <style rel="stylesheet" type="text/css">


        .picture {
            height: 200px;
            width: 200px;
            margin: 10px auto;
        }

        .userBlock {
            margin-top: 20px;
            padding-right: 0;
            padding-left: 0;

        }

        .messageBlock {
            margin-top: 10px;
            padding-right: 0;
            padding-left: 0;
        }

        .inputBlock {
            margin-top: 20px;
            margin-bottom: 10px;
        }

        .panel-title{
            margin-bottom: 0;
            margin-top: 0;
        }
    </style>


</head>
<body>

<header>
    <nav class="navbar navbar-inverse    nav-wrapper">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand brand" href="#">
                    <!-- TODO -->
                    <img alt="Brand" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">首页</a></li>
                <li><a href="#">大盘详情</a></li>
                <li><a href="#" style="color: #4cae4c">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <li><a href="#">用户</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>

<form role="form">
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


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/traceBack.js"></script>

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

</script>

</body>
</html>
