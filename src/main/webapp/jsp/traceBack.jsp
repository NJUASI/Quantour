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
            margin-top: 700px;
            width: 100%;
            height: 100px;
            background-color: #444444;
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
                <li><a href="/stocks" style="cursor:
                 pointer">大盘详情</a></li>
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

<form role="form" style="position:relative;margin-top: 60px;">
    <div class="row">
        <div class="panel panel-default col-lg-10 col-lg-offset-1 userBlock">
            <div class="panel-heading">
                <h4 class="panel-title">
                    选股票池
                </h4>
            </div>
            <div class="panel-body">
                <div class="row">

                    <div class="col-lg-2 col-lg-offset-1 inputBlock" style="display: none">
                        <div class="radio">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios1" value="false" checked>
                                按板块选
                            </label>
                        </div>
                    </div>

                    <div class="form-group row col-md-4 col-md-offset-1 inputBlock">
                        <label class="col-md-4" style="margin-top: 7px">选择板块：</label>
                        <div class='col-md-7' style="margin-left: -10px">
                            <select id="blockTypes" name="blockTypes"
                                    class=" selectpicker show-tick form-control"
                                    multiple data-live-search="false" placeholder="请选择板块">
                                <option value="ZB" selected>主板</option>
                                <option value="ZXB" selected>中小板</option>
                                <option value="CYB" selected>创业板</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-md-3 inputBlock">
                        <label class="col-md-3" style="margin-top: 7px">ST：</label>
                        <div class=' col-md-8'>
                            <select id="stType" class="col-md-12 selectpicker show-tick form-inline">
                                <option value="INCLUDE" selected>包含ST</option>
                                <option value="EXCLUDE">排除ST</option>
                                <option value="ONLY">仅有ST</option>
                            </select>
                        </div>

                    </div>

                    <div class="form-group col-md-3 inputBlock">
                        <label class="col-md-5" style="margin-top: 7px">收益基准：</label>
                        <div class='col-md-7' style="margin-left: -14px">
                            <select id="baseStockEve" name="baseStockEve"
                                    class="selectpicker show-tick form-control col-md-7">
                                <option value="沪深300" selected>沪深300</option>
                                <option value="创业板指">创业板指</option>
                                <option value="中小板指">中小板指</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group col-md-offset-1 col-md-4 inputBlock">
                        <label class="col-md-4" style="margin-top: 7px">调仓周期：</label>
                        <div class=' col-md-4 ' style="margin-left: -10px">
                            <input id="holdingPeriod" type="text" class="form-control col-md-1"
                                   name="holdingPeriod" placeholder="请输入天数">
                        </div>
                        <label class="col-md-1" style="margin-top: 7px">天</label>
                    </div>

                    <div class="form-group  col-md-3 inputBlock" style="margin-left: -17px">
                        <label class="col-md-5" style="margin-top: 7px">最大持仓：</label>
                        <div class=' col-md-6 ' style="margin-left: -27px">
                            <input id="maxHolding" type="text" class="form-control col-md-1"
                                   name="maxHolding" placeholder="请输入股票数">
                        </div>
                        <label class="col-md-1" style="margin-top: 7px">支</label>
                    </div>


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
            <div class="panel-body">
                <div class="row" style="margin-top: 20px">
                    <!--左边选择栏  -->
                    <div class="col-md-4 col-md-offset-1">
                        <label class="">
                            选股指标:
                        </label>

                        <select id="searchQuota" class="selectpicker col-md-6" onchange=quotaChange();
                                data-live-search="true" data-size="10" data-live-search-placeholder="Search"
                                data-actions-box="true" title="搜索指标">
                            <option><span class="quota">N日平均成交额</span></option>
                            <option class="quota">N日平均成交量</option>
                            <option class="quota">N日乖离率</option>
                            <option class="quota">N日波动率</option>
                            <option class="quota">N日涨幅</option>
                            <option class="quota">N日换手率</option>
                            <optgroup label="价格">
                                <option class="quota">开盘价</option>
                                <option class="quota">收盘价</option>
                                <option class="quota">最高价</option>
                                <option class="quota">最低价</option>
                                <option class="quota">前日收盘价</option>
                                <option class="quota">后复权收盘价</option>
                                <option class="quota">后复权均价</option>
                            </optgroup>
                            <optgroup label="股本">
                                <option class="quota">总股本</option>
                                <option class="quota">流通股本</option>
                                <option class="quota">总市值</option>
                                <option class="quota">流通市值</option>
                                <option class="quota">股价振幅</option>
                            </optgroup>
                            <optgroup label="估值">
                                <option class="quota">市盈率</option>
                                <option class="quota">市销率</option>
                                <option class="quota">市净率</option>
                                <option class="quota">静态市盈率</option>
                                <option class="quota">动态市盈率</option>
                            </optgroup>

                        </select>

                        <!-- 选项卡组件（菜单项nav-tabs）-->
                        <ul id="myTab1" class="nav nav-tabs col-md-12 col-xs-12" style="margin-top: 20px"
                            role="tablist">
                            <li class="active"><a href="#bulletin" role="tab" data-toggle="tab">行情</a></li>
                            <li><a href="#rule" role="tab" data-toggle="tab">技术指标</a></li>
                            <li><a href="#forum" role="tab" data-toggle="tab">股指</a></li>
                            <li><a href="#security" role="tab" data-toggle="tab">其他</a></li>
                        </ul>
                        <!-- 选项卡面板 -->
                        <div id="myTabContent1" class="tab-content">
                            <div class="tab-pane fade in active" id="bulletin">
                                <div class="row">
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">股票价格
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation" class="dropdown-header">当日价格</li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">开盘价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">收盘价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">最高价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">最低价</a>
                                            </li>
                                            <li role="presentation" class="dropdown-header">其他价格</li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">前日收盘价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">日均成交价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">后复权开盘价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">后复权收盘价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">后复权最高价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">后复权最低价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">前日后复权收盘价</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">后复权均价</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">成交额
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">当日成交额</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">5日平均成交额</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">20日平均成交额</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">60日平均成交额</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">N日平均成交额</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">成交量
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">当日成交量</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">5日平均成交量</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">20日平均成交量</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">60日平均成交量</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">N日平均成交量</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">股价涨幅
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">1日涨幅</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">5日涨幅</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">20日涨幅</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">60日涨幅</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">N日涨幅</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">累计换手率
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">当日换手率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">5日换手率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">20日换手率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">60日换手率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">N日换手率</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">股本和市值
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">总股本</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">流通股本</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">总市值</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">流通市值</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default quota"
                                                style="border: 0px solid white">股价振幅
                                        </button>

                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="rule">
                                <div class="row">
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">乖离率
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">5日乖离率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">10日乖离率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">20日乖离率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">30日乖离率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">N日乖离率</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button" class="btn btn-default dropdown-toggle"
                                                style="border: 0px solid white" data-toggle="dropdown">波动率
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">5日波动率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">10日波动率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">20日波动率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">30日波动率</a>
                                            </li>
                                            <li role="presentation">
                                                <a role="menuitem" class="quota" tabindex="-1">N日波动率</a>
                                            </li>

                                        </ul>
                                    </div>

                                </div>
                            </div>
                            <div class="tab-pane fade" id="forum">
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                                        市盈率
                                    </button>

                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                                        市净率
                                    </button>

                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                                        市销率
                                    </button>

                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                                        静态市盈率
                                    </button>

                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                                        动态市盈率
                                    </button>

                                </div>
                            </div>
                            <div class="tab-pane fade" id="security"></div>

                        </div>
                    </div>


                    <!--右边数据框-->


                    <div class="col-md-6" style=" border-left: 1px solid slategray;">
                    <label class="row col-md-3" style="margin-top:5px">
                        选股条件:
                    </label>
                        <!-- 选项卡组件（菜单项nav-tabs）-->

                    <ul id="myTab2" class="nav nav-tabs col-md-12 col-xs-12" style="margin-top: 5px"role="tablist">
                    <li class="active"><a href="#choose" role="tab" data-toggle="tab">筛选条件</a></li>
                    <li><a href="#rank" role="tab" data-toggle="tab">排名条件</a></li>
                    </ul>
                    <!-- 选项卡面板 -->
                    <div id="myTabContent2" class="tab-content">
                        <div class="tab-pane fade in active" id="choose">

                            <div class="col-md-12 col-xs-12"
                                 style="height:200px;max-height: 240px;overflow-y: auto">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>指标</th>
                                        <th>比较符</th>
                                        <th>值</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="quotaList">

                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="tab-pane fade in" id="rank">

                            <div class="col-md-12 col-xs-12"
                                 style="height:200px;max-height:  240px;overflow-y: auto">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>指标</th>
                                        <th>次序</th>
                                        <th>权重</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="rankList">

                                    </tbody>
                                </table>
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

                        <div class="col-md-3 col-md-offset-1 inputBlock">

                            <label class="col-md-5" style="margin-top: 7px">开始日期：</label>
                            <!--指定 date标记-->
                            <div class='input-group date col-md-6' id='datetimeStart'>
                                <input id="startDate" type='text' class="form-control form_datetime"/>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>

                            </div>
                        </div>
                        <div class="col-md-3 inputBlock">

                            <label class="col-md-5" style="margin-top: 7px">结束日期：</label>
                            <!--指定 date标记-->
                            <div class='input-group date col-md-6' id='datetimeEnd'>
                                <input id="endDate" type='text' class="form-control form_datetime"/>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>

                            </div>
                        </div>

                        <div class="col-md-1 col-md-offset-2 inputBlock">

                            <button type="button" class="btn btn-info" onclick="traceback()"
                                    style="margin-left: -70px;">开始回测
                            </button>
                        </div>
                        <div class="col-md-1 inputBlock">

                            <button type="button" class="btn btn-info" id="createStrategy"
                                    style="margin-left: -40px;margin-bottom: 20px">创建策略
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>


</form>

<div id="coverPanel" style="position: absolute;background: #EBEFF0;width: 100%;height: 90px;z-index: 20">

</div>

<div class="row" style="z-index: 2">

    <ul id="myTab" class="col-md-offset-1 col-md-10 nav nav-tabs" role="tablist">
        <li class="active"><a href="#chartPanel" role="tab" data-toggle="tab">收益曲线</a></li>
        <li><a href="#cyclePanel" role="tab" data-toggle="tab">收益周期统计</a></li>
        <li><a href="#holdingDetailPanel" role="tab" data-toggle="tab">交易详情</a></li>

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
            <div class="col-md-12 table-responsive pre-scrollable" style="max-height: 640px">
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
</div>
<footer/>
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

    <div class="modal-dialog modal-lg">
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
<div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:70%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">创建策略</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="form-group col-md-offset-1 col-md-6">
                        <label class="col-md-3 control-label" style="margin-top: -10px">策略名称：</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" style="margin-top: -17px;margin-left: -30px"
                                   placeholder="">
                        </div>
                    </div>
                </div>

                <div style="width:90%;margin:0 auto;2px;margin-bottom: 30px;border-top:1px solid #ddd"></div>

                <div class="row" style="margin-bottom: 10px">
                    <div class="col-md-offset-1 col-md-11">
                        <div class="col-md-10">
                            <div class="row">
                                <div class="col-md-4">
                                    <label class="control-label">板块：</label><span>主板，创业板，中小板</span>
                                </div>
                                <div class="col-md-3">
                                    <label class="control-label">ST：</label><span>仅为ST</span>
                                </div>
                                <div class="col-md-4">
                                    <label class="control-label">收益基准：</label><span>沪深300</span>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 15px">
                                <div class="col-md-4 ">
                                    <label class="control-label">调仓周期：</label><span>10</span>
                                </div>
                                <div class="col-md-3">
                                    <label class="control-label">最大持股：</label><span>50</span>
                                </div>

                            </div>
                            <div class="row" style="margin-top: 15px; margin-bottom: 15px">
                                <div class="col-md-2">
                                    <label class="control-label">筛选条件：</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="row " style="border: 1px solid darkslategrey">

                                        <div class=" col-md-10">
                                            <strong class="col-md-5">指标</strong><strong class="col-md-4">比较符</strong>
                                            <strong class="col-md-2">值</strong>
                                        </div>
                                        <div class=" col-md-10">
                                            <span class="col-md-5">10日平均成交量</span><span class="col-md-4">排名最大</span>
                                            <span class="col-md-2">20</span>
                                        </div>
                                        <div class=" col-md-10">
                                            <span class="col-md-5">10日平均成交量</span><span class="col-md-4">排名最大</span>
                                            <span class="col-md-2">20</span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 15px; margin-bottom: 15px">
                                <div class="col-md-2">
                                    <label class="control-label">排名条件：</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="row " style="border: 1px solid darkslategrey">

                                        <div class=" col-md-10">
                                            <strong class="col-md-5">指标</strong><strong class="col-md-4">次序</strong>
                                            <strong class="col-md-2">权重</strong>
                                        </div>
                                        <div class=" col-md-10">
                                            <span class="col-md-5">10日平均成交量</span><span class="col-md-4">排名最大</span>
                                            <span class="col-md-2">20</span>
                                        </div>
                                        <div class=" col-md-10">
                                            <span class="col-md-5">10日平均成交量</span><span class="col-md-4">排名最大</span>
                                            <span class="col-md-2">20</span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 15px; margin-bottom: 15px">
                                <div class="col-md-2">
                                    <label class="control-label">策略描述：</label>
                                </div>
                                <div class="col-md-10">
                                    <textarea rows="4" style="width: 90%; margin-left: -20px"></textarea>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class=" col-md-offset-8 col-md-1" style="padding-right: 0px">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="radios" id="radios1" value="false" checked>
                                    私密
                                </label>
                            </div>
                        </div>
                        <div class="col-md-1" style="padding-left: 0px">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="radios" id="radios2" value="false">
                                    公开
                                </label>
                            </div>
                        </div>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary">创建策略</button>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
</div>

<footer>

</footer>

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


    $(function () {
        $("#createStrategy").click(function () {
            $("#mymodal").modal("toggle");
        });
    });

    var today = new Date();
    var yesterday = new Date();
    yesterday.setTime(today.getTime() - 24 * 60 * 60 * 1000);

    var endTime = today.getFullYear() + "-";
    var startTime = yesterday.getFullYear() + "-";

    var month = today.getMonth() + 1;
    var dayOfMonth = today.getDate();
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

    month = yesterday.getMonth() + 1;
    dayOfMonth = yesterday.getDate();
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
            });
        });

    });


    function user_modify() {
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

<script type="text/javascript">

    $(".quota").hover(function () {
        $(this).css({"cursor": "pointer"});
    });

 

    function convert(indicatorType) {
        indicatorType = indicatorType.trim();
        switch (indicatorType) {
            case "开盘价":
                return " ";
            case "收盘价":
                return " ";
            case "最高价":
                return " ";
            case "最低价":
                return " ";
            case "前日收盘价":
                return " ";
            case "日均成交价":
                return " ";
            case "后复权开盘价":
                return " ";
            case "后复权收盘价":
                return " ";
            case "后复权最高价":
                return " ";
            case "后复权最低价":
                return " ";
            case "前日后复权收盘价":
                return " ";
            case "后复权均价":
                return " ";
            case "总股本":
                return "亿";
            case "流通股本":
                return "亿";
            case "总市值":
                return "亿";
            case "流通市值":
                return "亿";
            case "股价振幅":
                return "%";
            case "市盈率":
                return " ";
            case "市净率":
                return " ";
            case "市销率":
                return " ";
            case "静态市盈率":
                return " ";
            case "动态市盈率":
                return " ";
            default:
                switch (indicatorType.substring(indicatorType.length - 2)) {
                    case "交额":
                        return "亿";
                    case "交量":
                        return "万";
                    case "离率":
                        return "%";
                    case "动率":
                        return "%";
                    case "涨幅":
                        return "%";
                    case "手率":
                        return "%";
                }

        }

    }


    $('#searchQuota').bind('input propertychange', function () {
        alert("11");
    });
    function quotaChange() {
        var quotaName;
//       alert($(this).html())
        if ($('#searchQuota').val().substring(0, 1) == "N") {
            quotaName = "<div class='col-md-5'><input class='numOfN form-control' value='10'></div><div style='margin-top: 6px' class='quotaName'>" + $('#searchQuota').val().substring(1) + "</div>"
        } else {
            quotaName = "<div class='col-md-4' hidden><input class='numOfN form-control'></div><div style='margin-top: 6px' class='col-md-12 quotaName'>" + $('#searchQuota').val() + "</div>"
        }

        var rankType;
        var whichTab;
        var whichValue;
        var whichButton;
        if($("#choose").hasClass("active")){
            rankType = "<select class=\"form-control col-md-12 quotaRank\" style=\"padding-left: 5px;padding-right: 5px\">" +
                "<option value='RANK_MAX'>排名最大</option>" +
                "<option value='RANK_MIN'>排名最小</option>" +
                "<option value='RANK_MAX_PERCENT'>排名%最大</option>" +
                "<option value='RANK_MIN_PERCENT'>排名%最小</option>" +
                "<option value='RANK_GREATER'>大于</option>" +
                "<option value='RANK_LESS'>小于</option>" +
                "<option value='RANK_EQUAL'>等于</option>" +
                "</select>" ;
            whichTab =$("#quotaList");

            whichValue=  "<div class=\"row\">" +
                "<div class=\"col-md-10\">" +
                "<input type=\"text\" class=\"form-control quotaNum\" value=\"10\"/>" +
                "</div>" +
                "<div class='percent' style=\"margin-left: -10px;display: inline-block;visibility: hidden;margin-top: 5px\" >%</div>" +
                "</div>" ;
            whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary quotaBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
        }else{
            rankType="<select class=\"form-control col-md-12 rankOrder\" style=\"padding-left: 5px;padding-right: 5px\">" +
                "<option value='ASC_RANK'>由小到大</option>" +
                "<option value='DESC_RANK'>由大到小</option>" +
                "</select>" ;
            whichTab= $("#rankList");
            whichValue=  "<div class=\"row\">" +
                "<div class=\"col-md-9\">" +
                "<input type=\"text\" class=\"form-control quotaWeight\" value=\"1\"/>" +
                "</div>" +
                "</div>" ;
            whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary rankBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
        }
        whichTab.append("<tr class='quotaRow'>" +
            "<td class=\"col-md-4\"><div class='row'>" + quotaName + "</div></td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            rankType+
            "</div>" +
            "</td>" +
            "<td class=\"col-md-2\">" +
            whichValue+
            "</td>" +
            whichButton+
            "</tr>");

        $(".quotaBt").unbind("click");
        $(".quotaBt").click(function () {
            $("#quotaList").find("tr").eq($(".quotaBt").index($(this))).remove();
        });

        $(".quotaRank").unbind("input propertychange");
        $(".quotaRank").bind('input propertychange', function () {
            if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "hidden");
            } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html("%");
            } else {
                var name = $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".quotaName").html();
                var result = convert(name);
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html(result);
            }
        });

        $(".rankBt").unbind("click");
        $(".rankBt").click(function () {
            $("#rankList").find("tr").eq($(".rankBt").index($(this))).remove();
        });
        $(".rankOrder").unbind("input propertychange");
        $(".rankOrder").bind('input propertychange', function () {
            if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "hidden");
            } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html("%");
            } else {
                var name = $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".quotaName").html();
                var result = convert(name);
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html(result);
            }
        })
    }

    $(".quota").click(function () {
        var quotaName;
//       alert($(this).html())
        if ($(this).html().substring(0, 1) == "N") {
            quotaName = "<div class='col-md-4'><input class='numOfN form-control' value='10'></div><div style='margin-top: 6px' class='quotaName'>" + $(this).html().substring(1) + "</div>"
        } else {
            quotaName = "<div class='col-md-4' hidden><input class='numOfN form-control'></div><div style='margin-top: 6px' class='col-md-12 quotaName'>" + $(this).html() + "</div>"
        }

        var rankType;
        var whichTab;
        var whichValue;
        var whichButton;
        var whichType;
        if($("#choose").hasClass("active")){
            rankType = "<select class=\"form-control col-md-12 quotaRank\" style=\"padding-left: 5px;padding-right: 5px\">" +
                "<option value='RANK_MAX'>排名最大</option>" +
                "<option value='RANK_MIN'>排名最小</option>" +
                "<option value='RANK_MAX_PERCENT'>排名%最大</option>" +
                "<option value='RANK_MIN_PERCENT'>排名%最小</option>" +
                "<option value='RANK_GREATER'>大于</option>" +
                "<option value='RANK_LESS'>小于</option>" +
                "<option value='RANK_EQUAL'>等于</option>" +
                "</select>" ;
            whichTab =$("#quotaList");

            whichValue=  "<div class=\"row\">" +
                "<div class=\"col-md-10\">" +
                "<input type=\"text\" class=\"form-control quotaNum\" value=\"10\"/>" +
                "</div>" +
                "<div class='percent' style=\"margin-left: -10px;display: inline-block;visibility: hidden;margin-top: 5px\" >%</div>" +
                "</div>" ;
            whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary quotaBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
            whichType='quotaRow';
        }else{
            rankType="<select class=\"form-control col-md-12 rankOrder\" style=\"padding-left: 5px;padding-right: 5px\">" +
                "<option value='ASC_RANK'>由小到大</option>" +
                "<option value='DESC_RANK'>由大到小</option>" +
                "</select>" ;
            whichTab= $("#rankList");
            whichValue=  "<div class=\"row\">" +
                "<div class=\"col-md-9\">" +
                "<input type=\"text\" class=\"form-control quotaWeight\" value=\"1\"/>" +
                "</div>" +
                "</div>" ;
            whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary rankBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
            whichType='rankRow';
        }
        whichTab.append("<tr class="+whichType+">" +
            "<td class=\"col-md-4\"><div class='row'>" + quotaName + "</div></td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            rankType+
            "</div>" +
            "</td>" +
            "<td class=\"col-md-2\">" +
            whichValue+
            "</td>" +
            whichButton+
            "</tr>");

        $(".quotaBt").unbind("click");
        $(".quotaBt").click(function () {
            $("#quotaList").find("tr").eq($(".quotaBt").index($(this))).remove();
        });
        $(".quotaRank").unbind("input propertychange");
        $(".quotaRank").bind('input propertychange', function () {
            if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "hidden");
            } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html("%");
            } else {
                var name = $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".quotaName").html();
                var result = convert(name);
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html(result);
            }
        });

        $(".rankBt").unbind("click");
        $(".rankBt").click(function () {
            $("#rankList").find("tr").eq($(".rankBt").index($(this))).remove();
        });
        $(".rankOrder").unbind("input propertychange");
        $(".rankOrder").bind('input propertychange', function () {
            if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "hidden");
            } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html("%");
            } else {
                var name = $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".quotaName").html();
                var result = convert(name);
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
                $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html(result);
            }
        })
    });
</script>

</body>
</html>
