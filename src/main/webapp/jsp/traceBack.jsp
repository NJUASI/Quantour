<%@ page import="com.edu.nju.asi.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/13
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  response.setHeader("Pragma","No-cache");  response.setHeader("Cache-Control","no-cache");  response.setDateHeader("Expires", 0);  %>
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
    <link href="../css/bootstrap-slider.css" rel="stylesheet">
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
                <li><a id="homePage" href="/">首页</a></li>
                <li><a id="stocks" style="cursor: pointer">大盘详情</a></li>
                <li class="dropdown">
                    <a href="##" class="dropdown-toggle" id="community" data-toggle="dropdown">量化社区<span class="caret"></span></a>
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
                </div>

                <div class="row">
                    <div id="blockTypesError" class="row col-md-2 col-md-offset-2" hidden>
                        <strong style="color:indianred;margin-left: 33px"> <span class="glyphicon glyphicon glyphicon-remove-circle"></span> 板块不能为空</strong>
                    </div>
                </div>

                <div class="row">
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
                <div class="row">
                    <div class="row col-md-2 col-md-offset-2" >
                        <strong  id="holdingPeriodError" hidden style="color:indianred;margin-left: 33px"> <span class="glyphicon glyphicon glyphicon-remove-circle"></span> 请输入合法天数</strong>
                    </div>

                    <div class="row col-md-2 col-md-offset-2" >
                        <strong id="maxHoldingError" hidden style="color:indianred;margin-left: 4px"><span class="glyphicon glyphicon glyphicon-remove-circle"></span> 请输入合法股票数</strong>
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
                                                <a role="menuitem" class="quota" tabindex="-1">当日涨幅</a>
                                                <%--<span class="	glyphicon glyphicon-search"></span>--%>
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
                    <div class="row col-md-offset-9" id="wholeMessage" hidden>
                        <strong style="color:indianred;margin-left: 4px"><span class="glyphicon glyphicon glyphicon-remove-circle"></span><span id="wholeError"> 请输入合法股票数</span></strong>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form>

<div id="coverPanel" style="position: absolute;background: #EBEFF0;width: 100%;height: 750px;z-index: 20">

</div>

<div class="row" style="z-index: 2">

    <ul id="myTab" class="col-md-offset-1 col-md-10 nav nav-tabs" role="tablist">
        <li class="active"><a href="#chartPanel" role="tab" data-toggle="tab">收益曲线</a></li>
        <li><a href="#cyclePanel" role="tab" data-toggle="tab">收益周期统计</a></li>
        <li><a href="#holdingDetailPanel" role="tab" data-toggle="tab">交易详情</a></li>
        <li><a href="#recentlySoldPanel" role="tab" data-toggle="tab">卖出股票</a></li>
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
            <div id="trace_back_chart" style="margin:0px auto; width:1100px;height:500px"></div>
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
    <div class="tab-pane" id="recentlySoldPanel">
       <div class="row">
           <div class="col-md-12 table-responsive">
               <table class="table table-hover table-condensed">
                   <thead>
                   <tr>
                       <th>股票名</th>
                       <th>股票代码</th>
                       <th>买入日期</th>
                       <th>卖出日期</th>
                       <th>买入价格</th>
                       <th>卖出价格</th>
                       <th>涨幅</th>
                   </tr>
                   </thead>
                   <tbody id="sold_stock_detail">
                   </tbody>
               </table>
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
                            <input type="text" id="strategyName" class="form-control" style="margin-top: -17px;margin-left: -30px"
                                   placeholder="">
                        </div>
                        <div class="col-md-offset-2 col-md-5" id="nameErrorPanel" hidden>
                        <strong style="color:indianred;margin-left: 14px"><span class="glyphicon glyphicon glyphicon-remove-circle"></span><span id="nameError"></span></strong>
                        </div>
                    </div>
                </div>
                <div style="width:90%;margin:0px auto;margin-bottom:30px;border-top:1px solid #ddd"></div>

                <div class="row" style="margin-bottom: 10px">
                    <div class="col-md-offset-1 col-md-11">
                        <div class="col-md-10">
                            <div class="row">
                                <div class="col-md-4">
                                    <label class="control-label">板块：</label><span id="strategyBlock">主板，创业板，中小板</span>
                                </div>
                                <div class="col-md-3">
                                    <label class="control-label">ST：</label><span id="strategyST">仅为ST</span>
                                </div>
                                <div class="col-md-4">
                                    <label class="control-label">收益基准：</label><span id="strategyBace">沪深300</span>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 15px">
                                <div class="col-md-4 ">
                                    <label class="control-label">调仓周期：</label><span id="strategyPeriod"></span>
                                </div>
                                <div class="col-md-3">
                                    <label class="control-label">最大持股：</label><span id="strategyHolding"></span>
                                </div>

                            </div>
                            <div class="row" style="margin-top: 15px; margin-bottom: 15px">
                                <div class="col-md-2">
                                    <label class="control-label">筛选条件：</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="row " id="quotaCreate">

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
                                    <div class="row " id="rankCreate">

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
                                    <textarea rows="4" id="strategyDescription" style="width: 90%; margin-left: -20px"></textarea>
                                </div>
                            </div>
                            <div id="descriptionError"  class="row col-md-offset-2" hidden>
                                <strong style="color:indianred;margin-left: 14px"><span class="glyphicon glyphicon glyphicon-remove-circle"></span><span>输入策略描述吸引更多人订阅</span></strong>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class=" col-md-offset-8 col-md-1" style="padding-right: 0px">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="radios3" id="radios1" value="true" checked>
                                    私密
                                </label>
                            </div>
                        </div>
                        <div class="col-md-1" style="padding-left: 0px">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="radios3" id="radios2" value="false">
                                    公开
                                </label>
                            </div>
                        </div>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <c:choose>
                            <c:when test="${sessionScope.user==null}">
                                <button type="button" onclick='ensureCreate()' class="btn btn-primary">创建策略</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" onclick='ensureCreate("<%=((User)session.getAttribute("user")).getUserName()%>")' class="btn btn-primary">创建策略</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<%@ include file="logIn.jsp" %>


<footer>

</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<%--<script src="../js/traceBack.js"></script>--%>

<script src="../js/echarts.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/startLoaded.js"></script>
<script src="../js/logIn.js"></script>
<script src="../js/quotaSelect.js"></script>


<script src="../js/bootstrap-slider.js"></script>
<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../js/myValidate.js"></script>
<script src="../js/dbDatePicker.js"></script>
<script type="text/javascript">


    $(document).ready(function () {
        $("#modifyBT").click(function () {
            $("#passwordField").toggle("slow");
            $("#passwordModify").toggle("slow");
        });

        $("#stocks").click(function () {
            $("body").removeClass("loaded");
            window.location.href = "/stocks";
        });
        $("#community").addClass("act");
    });




    function getTraceBackCriteria() {
        var user = "<%= (User)session.getAttribute("user")%>";
        if(user=="null" ){
            $("#wholeMessage").show();
            $("#wholeError").html("请先登录");
            setTimeout("$('#wholeMessage').hide();",5000);
            return false;
        }
        // alert($("#startDate").val() + "\n" + $("#endDate").val() + "\n" + $("#formativePeriod").val() + "\n" + $("#holdingPeriod").val()
        //     + "\n" + $("#stType").val() + "\n" +$("#blockTypes").val() + "\n" +$("#baseStockEve").val() + "\n" + isCustomized
        //     + "\n" +$("#formativeStrategy").val() + "\n" +$("#pickStrategy").val() + "\n"+$("#rank").val());
        var isZero;
        if( $("#blockTypes").val()==" "||$("#blockTypes").val()==""||$("#blockTypes").val()==null){
            $('#blockTypesError').show();
            setTimeout("$('#blockTypesError').hide();",3000);
            window.location.href="#";
            isZero= false;
        }

        var reg = /^\d{1,2}$/;
        if (!reg.test($('#maxHolding').val())) {
            $('#maxHoldingError').show();
            $('#maxHolding').css("border","1px solid red");
            window.location.href="#";
            isZero= false;
        }
        if (!reg.test($("#holdingPeriod").val())) {
            $('#holdingPeriodError').show();
            $("#holdingPeriod").css("border","1px solid red");
            window.location.href="#";
            isZero= false;
        };

        var reg2 = /^[1-9]$/;
        $(".quotaWeight").each(function () {
            var num=$(this).val();
            if(num==0||num=="0"){
                $(this).css("border","2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html(" 你输入的排名条件错误");
                setTimeout("$('#wholeMessage').hide();",3000)
                isZero=false;
            }else if (!reg2.test($(this).val())) {
                $(this).css("border","2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html(" 你输入的排名条件错误");
                setTimeout("$('#wholeMessage').hide();",3000)
                isZero=false;
            }
        });
        var reg3 = /^\d{1,20}$/;
        var isZero;
        $(".quotaNum").each(function () {
            var num=$(this).val();
            if(num==0||num=="0"){
                $(this).css("border","2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html("你输入的筛选条件错误");
                setTimeout("$('#wholeMessage').hide();",3000)
                isZero=false;
            }else if (!reg3.test($(this).val())) {
                $(this).css("border","2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html(" 你输入的筛选条件错误");
                setTimeout("$('#wholeMessage').hide();",3000)
                isZero=false;
            }
        });
        var reg4 = /^\d{1,3}$/;
        $(".numOfN").each(function () {
            var temp= $(this).is(":hidden");
            if(!temp) {
                var num = $(this).val();
                if (num == 0 || num == "0") {
                    $(this).css("border", "2px solid red");
                    $("#wholeMessage").show();
                    $("#wholeError").html("你输入的指标错误");
                    setTimeout("$('#wholeMessage').hide();", 3000)
                    isZero = false;
                } else if (!reg4.test($(this).val())) {
                    $(this).css("border", "2px solid red");
                    $("#wholeMessage").show();
                    $("#wholeError").html(" 你输入的指标错误");
                    setTimeout("$('#wholeMessage').hide();", 3000)
                    isZero = false;
                } else {
                    $(this).css("border", "1px solid #CCCCCC");
                }
            }
        });
        if(isZero==false){
            return false;
        }
        // 添加选股条件
        var filterConditions = new Array();
            var filterNum = 0;
        $(".quotaRow").each(function () {
            var dateOfIndicator = $(this).find(".numOfN").val();
            var separatorResult = separateIndicator($(this).find(".quotaName").html().trim());
//            alert(separatorResult);

            if (dateOfIndicator == "") {
//                alert("无N日");
                // 非输入框输入
                filterConditions[filterNum] = {
                    "indicatorType": separatorResult[1],
                    "comparatorType": $(this).find(".quotaRank").val(),
                    "value": $(this).find(".quotaNum").val(),
                    "formativePeriod": separatorResult[0]
                };
            } else {
//                alert("有N日");
                // 输入框输入
                filterConditions[filterNum] = {
                    "indicatorType": separatorResult,
                    "comparatorType": $(this).find(".quotaRank").val(),
                    "value": $(this).find(".quotaNum").val(),
                    "formativePeriod": dateOfIndicator
                };
            }
            filterNum++;
        });
//        alert( $("#blockTypes").val());


        // 添加排序条件
        var rankConditions = new Array();
        var rankNum = 0;
        $(".rankRow").each(function () {
            var dateOfIndicator = $(this).find(".numOfN").val();
            var separatorResult = separateIndicator($(this).find(".quotaName").html().trim());
//            alert(separatorResult);

            if (dateOfIndicator == "") {
//                alert("无N日");
                // 非输入框输入
                rankConditions[rankNum] = {
                    "indicatorType": separatorResult[1],
                    "rankType": $(this).find(".rankOrder").val(),
                    "weight": $(this).find(".quotaWeight").val(),
                    "formativePeriod": separatorResult[0]
                };
            } else {
//                alert("有N日");
                // 输入框输入
                rankConditions[rankNum] = {
                    "indicatorType": separatorResult,
                    "rankType": $(this).find(".rankOrder").val(),
                    "weight": $(this).find(".quotaWeight").val(),
                    "formativePeriod": dateOfIndicator
                };
            }
            rankNum++;
        });
        if(rankNum+filterNum==0){
            $("#wholeMessage").show();
            $("#wholeError").html(" 请选择筛选条件或者排名条件");
            setTimeout("$('#wholeMessage').hide();",3000)
            return false;
        }

        // 添加择时条件
        var marketSelectingConditions = new Array();
        var marketSelectingNum = 0;

        // "formativePeriod": $("#formativePeriod").val();

//        alert("filterConditions: " +  filterConditions + "\n\n" + "rankConditions: " + rankConditions);
        var start=new Date( $("#startDate").val());
        var end=new Date($("#endDate").val());
        if(end-start<1000*60*60*24*$("#holdingPeriod").val()){
            $("#wholeMessage").show();
            $("#wholeError").html(" 你输入的日期少于持仓周期");
            setTimeout("$('#wholeMessage').hide();",3000)
            return false;
        }


        var criteriaData = {
            "startDate": $("#startDate").val(),
            "endDate": $("#endDate").val(),
            "holdingPeriod": $("#holdingPeriod").val(),
            "stockPoolCriteria": {
                "stType": $("#stType").val(),
                "blockTypes": $("#blockTypes").val()
            },
            "maxHoldingNum": $("#maxHolding").val(),
            "baseStockName": $("#baseStockEve").val(),
            // "isCustomized": isCustomized,
            "filterConditions": filterConditions,
            "rankConditions": rankConditions,
            "marketSelectingConditions": marketSelectingConditions,
            "adjustPositionPercent": null,
            "bearToBull_num": null,
            "bullToBear_num": null
        };


        return criteriaData;
    }

    /**
     * 回测
     */
    function traceback() {
        // var isCustomized = $(":radio[name='optionsRadios']:checked").val();

        var jsonData = getTraceBackCriteria();

        if(jsonData==false){
            return false;
        }

        $("body").removeClass("loaded");
        // alert(JSON.stringify(jsonData));

        $.ajax({
            type: "post",
            async: true,
            url: "/req_trace_back",
            data: {
                "criteriaData": JSON.stringify(jsonData)
            },


            success: function (result) {
                // $("#myTab").fadeIn("slow");
                // $("#chartPanel").fadeIn("slow");
                $("body").addClass("loaded");
                var array = result.split(";");
                if (array[0] == "1") {
                    $("#coverPanel").hide();
                    // 处理网页上要显示的表格信息
                    var numberValues = eval("(" + array[1] + ")");              // List<String>
                    var abReturnPeriod = eval("(" + array[2] + ")");            // ReturnPeriod
                    var reReturnPeriod = eval("(" + array[3] + ")");            // ReturnPeriod
                    var holdingDetails = eval("(" + array[4] + ")");            // List<HoldingDetail>
                    var transferDetails = eval("(" + array[5] + ")");           // List<TransferDetail>
                    // var certainFormates = eval("(" + array[5] + ")");           // List<ExcessAndWinRateDist>
                    // var certainHoldings = eval("(" + array[6] + ")");           // List<ExcessAndWinRateDist>

                    // 回测的数值型数据
                    $("#tb_chart").empty();
                    for (var i = 0; i < 3; i++) {
                        $("#tb_chart").append("<tr>");

                        switch (i) {
                            case 0:
                                $("#tb_chart").append("<td>本策略</td>");
                                break;
                            case 1:
                                $("#tb_chart").append("<td>基准股票</td>");
                                break;
                            case 2:
                                $("#tb_chart").append("<td>相对收益</td>");
                                break;
                        }

                        for (var j = 0; j < 7; j++) {
                            $("#tb_chart").append("<td>" + numberValues[i * 7 + j] + "</td>");
                        }
                        $("#tb_chart").append("</tr>");
                    }
                    // alert("--------------------1----------------");

                    // 股票周期的对比图
                    $("#tb_cycle_ab").empty();
                    $("#tb_cycle_ab").append("<tr>");
                    $("#tb_cycle_ab").append("<td>" + abReturnPeriod["positivePeriodsNum"] + "</td>");
                    $("#tb_cycle_ab").append("<td>" + abReturnPeriod["negativePeriodNum"] + "</td>");
                    $("#tb_cycle_ab").append("<td>" + abReturnPeriod["winRate"] + "</td>");
                    $("#tb_cycle_ab").append("</tr>");

                    $("#tb_cycle_re").empty();
                    $("#tb_cycle_re").append("<tr>");
                    $("#tb_cycle_re").append("<td>" + reReturnPeriod["positivePeriodsNum"] + "</td>");
                    $("#tb_cycle_re").append("<td>" + reReturnPeriod["negativePeriodNum"] + "</td>");
                    $("#tb_cycle_re").append("<td>" + reReturnPeriod["winRate"] + "</td>");
                    $("#tb_cycle_re").append("</tr>");
                    // alert("--------------------2----------------");


                    // 持有周期详情
                    $("#tb_detail").empty();
                    for (var i = 0; i < holdingDetails.length; i++) {
                        $("#tb_detail").append("<tr>"+
                        "<td><span class='circle' style='color:#7291CA'>" + holdingDetails[i]["periodSerial"] + "<spam></td>"+
                        "<td>" + holdingDetails[i]["startDate"] + "</td>"+
                        "<td>" + holdingDetails[i]["endDate"] + "</td>"+
                        "<td>" + holdingDetails[i]["holdingNum"] + "</td>"+
                        "<td>" + (holdingDetails[i]["strategyReturn"] * 100).toFixed(2) + "%" + "</td>"+
                        "<td>" + (holdingDetails[i]["baseReturn"] * 100).toFixed(2) + "%" + "</td>"+
                        "<td>" + (holdingDetails[i]["excessReturn"] * 100).toFixed(2) + "%" + "</td>"+
                        "<td>" + holdingDetails[i]["remainInvestment"].toFixed(2) + "</td>"+
                        "</tr>");
                    }

                    $(".circle").click(function () {

                    })

                    $(".circle").hover(function () {
                        $(this).css({"cursor": "pointer","text-decoration":" underline"});
                    }, function () {
                        $(this).css({"color":"#7291CA","text-decoration":" none"});
                    });

                    // alert("--------------------3----------------");

                    // 卖出的股票详情
                    $("#sold_stock_detail").empty();
                    for (var i = 0; i < transferDetails.length; i++) {
                        $("#sold_stock_detail").append("<tr>");
                        $("#sold_stock_detail").append("<td>" + transferDetails[i]["stockName"] + "</td>");
                        $("#sold_stock_detail").append("<td>" + transferDetails[i]["stockCode"] + "</td>");
                        $("#sold_stock_detail").append("<td>" + transferDetails[i]["buyDate"] + "</td>");
                        $("#sold_stock_detail").append("<td>" + transferDetails[i]["sellDate"] + "</td>");
                        $("#sold_stock_detail").append("<td>" + transferDetails[i]["buyPrice"]+ "</td>");
                        $("#sold_stock_detail").append("<td>" + transferDetails[i]["sellPrice"]+ "</td>");
                        $("#sold_stock_detail").append("<td>" + (transferDetails[i]["changeRate"] * 100).toFixed(2) + "%" + "</td>");
                        $("#sold_stock_detail").append("</tr>");
                    }
                    // alert("--------------------4----------------");



                    // // 固定形成期的赢率分析
                    // $("#tb_certain_formate").empty();
                    // for(var i = 0; i < certainFormates.length; i++) {
                    //     $("#tb_certain_formate").append("<tr>");
                    //     $("#tb_certain_formate").append("<td>" + certainFormates[i]["relativeCycle"] + "</td>");
                    //     $("#tb_certain_formate").append("<td>" + (certainFormates[i]["excessRate"]*100).toFixed(2) + "%" + "</td>");
                    //     $("#tb_certain_formate").append("<td>" + (certainFormates[i]["winRate"]*100).toFixed(2) + "%" + "</td>");
                    //     $("#tb_certain_formate").append("</tr>");
                    // }
                    // // alert("--------------------5----------------");
                    //
                    //
                    // // 固定持有期的赢率分析
                    // $("#tb_certain_holding").empty();
                    // for(var i = 0; i < certainHoldings.length; i++) {
                    //     $("#tb_certain_holding").append("<tr>");
                    //     $("#tb_certain_holding").append("<td>" + certainHoldings[i]["relativeCycle"] + "</td>");
                    //     $("#tb_certain_holding").append("<td>" + (certainHoldings[i]["excessRate"]*100).toFixed(2) + "%" + "</td>");
                    //     $("#tb_certain_holding").append("<td>" + (certainHoldings[i]["winRate"]*100).toFixed(2) + "%" + "</td>");
                    //     $("#tb_certain_holding").append("</tr>");
                    // }
                    // // alert("--------------------6----------------");


                    // 处理图表的信息
                    var strategyData = JSON.parse(array[6]);            //List<List<String>>
                    var baseData = JSON.parse(array[7]);                //List<List<String>>
                    var abHistogramData = JSON.parse(array[8]);
                    var reHistogramData = JSON.parse(array[9]);
                    // var formateExcessData = JSON.parse(array[11]);
                    // var formateWinData = JSON.parse(array[12]);
                    // var holdingExcessData = JSON.parse(array[13]);
                    // var holdingWinData = JSON.parse(array[14]);

                    // alert(strategyData + "\n\n" + baseData + "\n\n" + abHistogramData + "\n\n" + reHistogramData + "\n\n" + formateExcessData
                    //     + "\n\n" + formateExcessData + "\n\n" + holdingExcessData + "\n\n" + holdingWinData);


                    var trace_back_chart = createTraceBackChart("trace_back_chart", strategyData, baseData, ['策略', '基准'], '1', '1');
                    var absolute_histogram_chart = createHistogramChart("absolute_histogram_chart", abHistogramData, " ");
                    var relative_histogram_chart = createHistogramChart("relative_histogram_chart", reHistogramData, " ");
                    // var formates_excess_chart = createAreaChart("formates_excess_chart", formateExcessData, '胜率');
                    // var formates_win_chart = createAreaChart("formates_win_chart", formateWinData, '赢率');
                    // var holdings_excess_chart = createAreaChart("holdings_excess_chart", holdingExcessData, '胜率');
                    // var holdings_win_chart = createAreaChart("holdings_win_chart", holdingWinData, '赢率');


                } else if (array[0] == "-1") {
                    // 提示错误信息
                    $("#coverPanel").show();
                    $("#wholeMessage").show();
                    $("#wholeError").html("请再次确认您输入的内容是否合法");
                    setTimeout("$('#wholeMessage').hide();",3000)
                } else {
                    $("#coverPanel").show();
                    $("#wholeMessage").show();
                    $("#wholeError").html("请再次确认您输入的内容是否合法");
                    setTimeout("$('#wholeMessage').hide();",3000)
                }
            },

            error: function (result) {
                $("#coverPanel").show();
                $("#wholeMessage").show();
                $("#wholeError").html("请再次确认您输入的内容是否合法");
                setTimeout("$('#wholeMessage').hide();",3000)
            }
        });
    }



    /**
     * 用户确认保存策略
     */
    function ensureCreate(curUser){


            if($("#strategyName").val().trim()==""){
                $('#nameErrorPanel').show();
                $('#nameError').html("策略名称必须填写");
                return false;
            }else{
                $('#nameErrorPanel').hide();
            }


            if($("#strategyDescription").trim().val()==""){
                $('#descriptionError').show();
                return false;
            }else{
                $('#descriptionError').hide();
            }

        alert("--------ENTER--------");
        var strategyID = $("#strategyName").val();
        var description = $("#strategyDescription").val();
        var isPrivate = $('input[name="radios3"]:checked').val();

        var strategyData = {
            "strategyID": strategyID,
            "date": null,
            "creator": curUser,
            "isPrivate": isPrivate,
            "content": JSON.stringify(getTraceBackCriteria()),
            "description": description,
            "traceBackInfo": null,
            "users": null
        };

        alert("--------END--------");
        alert(JSON.stringify(strategyData));

        $.ajax({
            type: "post",
            async: true,
            url: "/strategy/save",
            data:{
                "strategy": JSON.stringify(strategyData)
            },

            success: function (result) {
                // $("body").addClass("loaded");
                var array = result.split(";");

                if (array[0] == "1") {


                } else if (array[0] == "-1") {
                    // 提示错误信息
                    $('#nameErrorPanel').show();
                    $('#nameError').html(array[1]);
                } else if  (array[0] == "-2"){
                    $('#nameErrorPanel').show();
                    $('#nameError').html("策略名称已被创建");
                }
            },
            error: function (result) {
                alert("错误" + result);
            }

        });


    }

    /**
     * 解析回测指标
     */
    function separateIndicator(indicatorType) {
        // 特殊情况，优先处理
        if (indicatorType == "日均成交价") return new Array(1, convertIndicator(indicatorType));
        if (indicatorType == "前日收盘价") return new Array(1, "PRE_CLOSE");
        if (indicatorType == "前日后复权收盘价") return new Array(1, "AFTER_ADJ_PRE_CLOSE");
        if (indicatorType == "1日5日量比") return new Array(1, "VOLUME_RATIO");


        var separator = indicatorType.indexOf("日");

        if (separator == -1) {
            // 指标中不含有日字
//            alert("指标中不含有日字\n" + convertIndicator(indicatorType));
            return new Array(1, convertIndicator(indicatorType));
        }
        if (separator == 0) {
            // 为 N日** 类型
            return convertIndicator(indicatorType.substr(1));
        } else {
            var reg = /^[0-9]*$/;
            if (reg.test(indicatorType.substr(0, separator))) {
                // 为 *日*** 类型
                return new Array(indicatorType.substr(0, separator), convertIndicator(indicatorType.substr(separator + 1, indicatorType.length)));
            } else {
                // 为 当日*** 类型
                return new Array(1, convertIndicator(indicatorType.substr(separator + 1, indicatorType.length)));
            }
        }
    }


    /**
     * 在JS中实现 IndicatorType.getEnum()方法，能够在JS中调用Java代码后修改
     */
    function convertIndicator(indicatorType) {
        switch (indicatorType) {
            case "开盘价":
                return "OPEN";
            case "收盘价":
                return "CLOSE";
            case "最高价":
                return "HIGH";
            case "最低价":
                return "LOW";
            case "日均成交价":
                return "DAILY_AVE_PRICE";
            case "后复权开盘价":
                return "AFTER_ADJ_OPEN";
            case "后复权收盘价":
                return "AFTER_ADJ_CLOSE";
            case "后复权最高价":
                return "AFTER_ADJ_HIGH";
            case "后复权最低价":
                return "AFTER_ADJ_LOW";
            case "后复权均价":
                return "AFTER_ADJ_DAILY_AVE_PRICE";

            case "成交额":
            case "平均成交额":
                return "TRANSACTION_AMOUNT";
            case "成交量":
            case "平均成交量":
                return "VOLUME";

            case "涨幅":
                return "INCREASE_MARGIN";
            case "换手率":
                return "TURNOVER_RATE";
            case "股价振幅":
                return "SWING_RATE";

            case "总股本":
                return "GENERAL_CAPITAL";
            case "流通股本":
                return "NEGOTIABLE_CAPITAL";
            case "总市值":
                return "TOTAL_VALUE";
            case "流通市值":
                return "CIRCULATION_MARKET_VALUE";

            case "乖离率":
                return "BIAS";
            case "波动率":
                return "RETURN_VOLATILITY";
            case "DIFF线":
                return "MACD_DIF";
            case "DEA线":
                return "MACD_DEA";
            case "MACD柱状值":
                return "MACD_COLUMN_VAL";
            case "多空指标":
                return "BBIC";
            case "多头排列标记":
                return "MULTIPLE_ARRANGEMENT_MARK";
            case "布林上线":
                return "BOLL_UP_BANDS";
            case "布林下线":
                return "BOLL_DOWN_BANDS";
            case "平均真实波动范围":
                return "AVE_TRUE_RANGE";

            case "市盈率":
                return "PE_TTM";
            case "市净率":
                return "PB";
            case "市销率":
                return "PS_TTM";
            case "静态市盈率":
                return "S_PE_TTM";
            case "动态市盈率":
                return "D_PE_TTM";
            case "市盈率相对于盈利增长比率":
                return "PEG";
            case "每股收益":
                return "EPS";
            case "净资产收益率":
                return "ROE";
            case "资产负债率":
                return "ASSET_LIABILITY_RATIO";
        }
        alert("No Match IndicatorType");
    }

    /**
     * 股票策略创建界面引出
     */
    $(function () {
        $("#createStrategy").click(function () {

            var jsonData = getTraceBackCriteria();
            if(jsonData==false){
                return false;
            }else {
                $("#mymodal").modal("toggle");
                var blockType = "";
                for (var i = 0; i < $("#blockTypes").val().length; i++) {
                    if ($("#blockTypes").val()[i] == "ZB") {
                        blockType += "主板 ";
                    } else if ($("#blockTypes").val()[i] == "CYB") {
                        blockType += "创业板 ";
                    } else {
                        blockType += "中小板 ";
                    }
                }
                $("#strategyBlock").html(blockType);
                var stType = document.getElementById("stType").options[document.getElementById("stType").selectedIndex].text;
                $("#strategyST").html(stType);
                $("#strategyBace").html($("#baseStockEve").val());
                $("#strategyPeriod").html($("#holdingPeriod").val());
                $("#strategyHolding").html($("#maxHolding").val());

                $("#quotaCreate").empty();
                $("#quotaCreate").append("<div class=' col-md-10'> <strong class='col-md-5'>指标</strong><strong class='col-md-4'>比较符</strong> <strong class='col-md-2'>值</strong> </div>")

                $(".quotaRow").each(function () {
                    var rank = "";
                    switch ($(this).find('.quotaRank').val()) {
                        case "RANK_MIN":
                            rank = "排名最小";
                            break;
                        case "RANK_MAX_PERCENT":
                            rank = "排名%最大";
                            break;
                        case "RANK_MAX":
                            rank = "排名最大";
                            break;
                        case  "RANK_MIN_PERCENT":
                            rank = "排名%最小";
                            break;
                        case  "RANK_GREATER":
                            rank = "大于";
                            break;
                        case  "RANK_LESS":
                            rank = "小于";
                            break;
                        case  " RANK_EQUAL":
                            rank = "等于";
                            break;
                    }

                    $("#quotaCreate").append("<div class=' col-md-10'> <span class='col-md-5'>" + $(this).find('.numOfN').val() + $(this).find('.quotaName').html() + "</span><span class='col-md-4'>" + rank + "</span> <span class='col-md-2'>" + $(this).find('.quotaNum').val() + "</span> </div>")
//

                });

                $("#rankCreate").empty();
                $("#rankCreate").append("<div class=' col-md-10'> <strong class='col-md-5'>指标</strong><strong class='col-md-4'>次序</strong> <strong class='col-md-2'>权重</strong> </div>")

                $(".rankRow").each(function () {
                    var rank = "";
                    switch ($(this).find('.rankOrder').val()) {
                        case "ASC_RANK":
                            rank = "由小到大";
                            break;
                        case "DESC_RANK":
                            rank = "由大到小";
                            break;
                    }
                    $("#rankCreate").append("<div class=' col-md-10'> <span class='col-md-5'>" + $(this).find('.numOfN').val() + $(this).find('.quotaName').html() + "</span><span class='col-md-4'>" + rank + "</span> <span class='col-md-2'>" + $(this).find('.quotaWeight').val() + "</span> </div>")

                });
            }
        });
    });
</script>

<%--<script src="../js/strategy.js"></script>--%>


</body>
</html>
