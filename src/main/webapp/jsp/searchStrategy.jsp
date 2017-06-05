<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/6/3
  Time: 15:19
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

        body {
            margin-top: 80px;
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
    <title>策略详情</title>
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
                <li><a id="stocks" style="cursor:
                 pointer">大盘详情</a></li>
                <li><a href="/trace_back">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <li><a href="/user/welcome">用户管理</a></li>

            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>


<div class="row ">
    <div class="col-md-8 col-md-offset-2">
        <div class="row">
            <h3 class="col-md-2" style="color:#4890c8">中小板股指</h3>
            <button class="btn btn-primary btn-sm col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="glyphicon glyphicon-heart"></span>
                <span class="txt">收藏</span>
            </button>
            <button id="modifyBt" class="btn btn-primary btn-sm col-md-1 col-md-offset-1" style="margin-top: 20px">
                <span class="txt">编辑</span>
            </button>
        </div>


    </div>
</div>

<div class="row">
    <div class="panel panel-default col-md-8 col-md-offset-2">
        <div class="panel-body">
            <div class="row" style="margin-top: 20px">
                <div class="col-md-5 row ">
                    <div class="row col-md-12">
                    <ul id="strategyDetail" class="list-inline">
                        <li>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:&nbsp; <span>2017-01-12</span></li>
                        <li>板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块:&nbsp; <span>1231</span></li>
                        <li>创建日期:&nbsp; <span>2017-01-12</span></li>
                        <li>ST&nbsp;&nbsp;&nbsp;信息:&nbsp; <span>123333</span></li>
                        <li>开始日期:&nbsp; <span>13</span></li>
                        <li>结束日期:&nbsp; <span>14</span></li>
                        <li>收益基准:&nbsp; <span>1233</span></li>
                    </ul>
                    </div>
                    <div class="row">
                        <div class="col-md-3 small"><p>筛选条件</p></div>
                        <div class="col-md-9">
                        <div class="row ">
                            <div class=" col-md-12 small">
                                <span class="col-md-4">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">20</span> <span class="col-md-1">1</span>
                            </div>
                            <div class=" col-md-12 small">
                                <span class="col-md-4">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">20</span> <span class="col-md-1">1</span>
                            </div>
                            <div class=" col-md-12 small">
                                <span class="col-md-4">10日平均成交量</span><span class="col-md-4">排名最大</span> <span class="col-md-1">20</span> <span class="col-md-1">1</span>
                            </div>
                        </div>
                        </div>

                    </div>
                </div>
                <div class="col-md-3" style="border-right: 1px solid slategray">
                    <div class="col-md-12 small">
                        策略描述:
                    </div>
                    <p class="col-md-12 small" style="color:#9e9e9e">
                        不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的 </p>
                </div>
                <div class="col-md-4">
                    <div id="candlestick" style=" width:100%;height:220px"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="coverPanel" style="position: absolute;background: #EBEFF0;width: 100%;height: 90px;z-index: 20">

</div>

<div class="row" style="z-index: 2">

    <ul id="myTab" class="col-md-offset-1 col-md-10 nav nav-tabs" role="tablist">
        <li class="active"><a href="#chartPanel" role="tab" data-toggle="tab">收益曲线</a></li>
        <li><a href="#cyclePanel" role="tab" data-toggle="tab">收益周期统计</a></li>
        <li><a href="#holdingDetailPanel" role="tab" data-toggle="tab">交易详情</a></li>

    </ul>
</div>
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


<div class="modal fade" id="modifyPanel" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">修改策略</h4>
            </div>
            <div class="modal-body" style="margin-top:0px ">

                <div class="row">
                    <div class="form-group col-md-offset-1 col-md-3" style="margin-bottom:0px">
                        <label class="col-md-4 control-label" style="margin-top:10px">策略名称</label>
                        <div class="col-md-7">
                            <h4>中小板股指</h4>
                        </div>
                    </div>

                    <div class="form-group col-md-5" style="margin-top: 5px">
                        <label for="baseStockEve">收益基准：</label>

                        <select id="baseStockEve" name="baseStockEve"
                                class=" col-md-5  selectpicker show-tick form-inline">
                            <option value="沪深300" selected>沪深300</option>
                            <option value="创业板指">创业板指</option>
                            <option value="中小板指">中小板指</option>
                        </select>
                    </div>

                </div>


                <div style="width:90%;margin:0 auto;2px;margin-bottom: 30px;border-top:1px solid #ddd"></div>
                <form>
                    <div class="row" style="margin-bottom: 10px">
                        <div class="col-md-offset-1 col-md-11">
                            <div class="col-md-11">
                                <div class="row">
                                    <div class="form-group row col-md-4">
                                        <label class="col-md-4" style="margin-top: 7px">选择板块：</label>
                                        <div class='col-md-7'>
                                            <select id="blockTypes" name="blockTypes"
                                                    class=" selectpicker show-tick form-control"
                                                    style="padding-left: 0px"
                                                    multiple data-live-search="false" placeholder="请选择板块">
                                                <option value="ZB" selected>主板</option>
                                                <option value="ZXB" selected>中小板</option>
                                                <option value="CYB" selected>创业板</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group  col-md-6 ">
                                        <label class="col-md-2" style="margin-top: 7px">ST：</label>
                                        <div class=' col-md-6 '>
                                            <select id="stType" class="col-md-10 selectpicker show-tick form-inline">
                                                <option value="INCLUDE" selected>包含ST</option>
                                                <option value="EXCLUDE">排除ST</option>
                                                <option value="ONLY">仅有ST</option>
                                            </select>
                                        </div>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group row col-md-4 ">
                                        <label class="col-md-4 " style="margin-top: 7px">开始日期：</label>
                                        <div class='col-md-6 input-group date' id='datetimeStart'>
                                            <input id="startDate" type='text' class="form-control form_datetime"/>
                                            <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>

                                        </div>
                                    </div>
                                    <div class="form-group col-md-5 ">
                                        <label class="col-md-3" style="margin-top: 7px">结束日期：</label>
                                        <div class=' col-md-5 input-group date' id='datetimeEnd'>
                                            <input id="endDate" type='text' class="form-control form_datetime"/>
                                            <span class="input-group-addon">
                                              <span class="glyphicon glyphicon-calendar"></span>
                                         </span>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="row" style="margin-top: 20px">
                                            <!--左边选择栏  -->
                                            <div class="col-md-5 col-xs-12">
                                                <label class="">
                                                    选股指标:
                                                </label>

                                                <select id="searchQuota" class="selectpicker col-md-6"
                                                        onchange=quotaChange(); data-live-search="true" data-size="10"
                                                        data-live-search-placeholder="Search" data-actions-box="true"
                                                        title="搜索指标">
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
                                                <ul id="myTab" class="nav nav-tabs col-md-12 col-xs-12"
                                                    style="margin-top: 20px" role="tablist">
                                                    <li class="active"><a href="#bulletin" role="tab" data-toggle="tab">行情</a>
                                                    </li>
                                                    <li><a href="#rule" role="tab" data-toggle="tab">技术指标</a></li>
                                                    <li><a href="#forum" role="tab" data-toggle="tab">股指</a></li>
                                                    <li><a href="#security" role="tab" data-toggle="tab">其他</a></li>
                                                </ul>
                                                <!-- 选项卡面板 -->
                                                <div id="myTabContent" class="tab-content">
                                                    <div class="tab-pane fade in active" id="bulletin">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">股票价格
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
                                                                    <li role="presentation" class="dropdown-header">
                                                                        当日价格
                                                                    </li>
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
                                                                    <li role="presentation" class="dropdown-header">
                                                                        其他价格
                                                                    </li>
                                                                    <li role="presentation">
                                                                        <a role="menuitem" class="quota" tabindex="-1">前日收盘价</a>
                                                                    </li>
                                                                    <li role="presentation">
                                                                        <a role="menuitem" class="quota" tabindex="-1">日均成交价</a>
                                                                    </li>
                                                                    <li role="presentation">
                                                                        <a role="menuitem" class="quota" tabindex="-1">后复权收盘价</a>
                                                                    </li>
                                                                    <li role="presentation">
                                                                        <a role="menuitem" class="quota" tabindex="-1">后复权均价</a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">成交额
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
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
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">成交量
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
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
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">股价涨幅
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
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
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">累计换手率
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
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
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">股本和市值
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
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
                                                                        style="border: 0px solid white">股价涨幅
                                                                </button>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="rule">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">乖离率
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
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
                                                                <button type="button"
                                                                        class="btn btn-default dropdown-toggle"
                                                                        style="border: 0px solid white"
                                                                        data-toggle="dropdown">波动率
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu"
                                                                    aria-labelledby="">
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
                                                            <button type="button" class="btn btn-default quota"
                                                                    style="border: 0px solid white">市盈率
                                                            </button>

                                                        </div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-default quota"
                                                                    style="border: 0px solid white">市净率
                                                            </button>

                                                        </div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-default quota"
                                                                    style="border: 0px solid white">市销率
                                                            </button>

                                                        </div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-default quota"
                                                                    style="border: 0px solid white">静态市盈率
                                                            </button>

                                                        </div>
                                                        <div class="col-md-4">
                                                            <button type="button" class="btn btn-default quota"
                                                                    style="border: 0px solid white">动态市盈率
                                                            </button>

                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="security"></div>

                                                </div>
                                            </div>


                                            <!--右边数据框-->
                                            <div class="col-md-7 col-xs-12 pre-scrollable"
                                                 style="height:200px;border-left: 1px solid slategray;max-height: 200px;">
                                                <label class="">
                                                    选股条件:
                                                </label>
                                                <table class="table table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>指标</th>
                                                        <th>比较符</th>
                                                        <th>值</th>
                                                        <th>权重</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="quotaList">

                                                    </tbody>
                                                </table>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 15px; margin-bottom: 15px">
                                    <div class="col-md-2">
                                        <label class="control-label">策略描述：</label>
                                    </div>
                                    <div class="col-md-10">
                                        <textarea rows="4" style="width: 90%; margin-left: -20px">不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的不瞒你说，我们的策略真的赚不了钱的</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="row" style="margin-right:0px">
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
                    <button type="button" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<footer>

</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>


<script src="../js/echarts.min.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/startLoaded.js"></script>


<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

    $("#strategyDetail").find("li").addClass("col-md-6 small");
    $("#strategyDetail").find("li").find("span").css("color", "#9e9e9e");
    $("#strategyDetail").find("li").css("margin-bottom", "10px")
    var paramter = ["我去", 2, 3, 4, 5];
    var data1 = [[2.6, 5.9, 9.0, 26.4, 28.7]];
    createRadarChart('candlestick', data1, ['1'], paramter);
    $(function () {
        $("#modifyBt").click(function () {
            $("#modifyPanel").modal("toggle");
        });
    });

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

</script>
<script type="text/javascript">

    $(".quota").hover(function () {
        $(this).css({"cursor": "pointer"});
    });

    $(".quota").click(function () {
        var quotaName;
//       alert($(this).html())
        if ($(this).html().substring(0, 1) == "N") {
            quotaName = "<div class='col-md-5'><input class='numOfN form-control' value='10'></div><div style='margin-top: 6px' class='quotaName'>" + $(this).html().substring(1,) + "</div>"
        } else {
            quotaName = "<div class='col-md-4' hidden><input class='numOfN form-control'></div><div style='margin-top: 6px' class='col-md-12 quotaName'>" + $(this).html() + "</div>"
        }

        $("#quotaList").append("<tr class='quotaRow'>" +
            "<td class=\"col-md-4\"><div class='row'>" + quotaName + "</div></td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            "<select class=\"form-control col-md-12 quotaRank\" style=\"padding-left: 5px;padding-right: 5px\">" +
            "<option>排名最大</option>" +
            "<option>排名最小</option>" +
            "<option>排名%最大</option>" +
            "<option>排名%最小</option>" +
            "</select>" +
            "</div>" +
            "</td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            "<div class=\"col-md-10\">" +
            "<input type=\"text\" class=\"form-control quotaNum\" value=\"10\"/>" +
            "</div>" +
            "<div class='percent' style=\"margin-left: -10px;display: inline-block;visibility: hidden;margin-top: 5px\" >%</div>" +
            "</div>" +
            "</td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            "<div class=\"col-md-9\">" +
            "<input type=\"text\" class=\"form-control quotaWeight\" value=\"1\"/>" +
            "</div>" +
            "</div>" +
            "</td>" +
            "<td class=\"col-md-1\"><button class=\"btn  btn-primary quotaBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>" +
            "</tr>");

        $(".quotaBt").unbind("click");
        $(".quotaBt").click(function () {
            $("#quotaList").find("tr").eq($(".quotaBt").index($(this))).remove();
            commit();
        });
        $(".quotaRank").unbind("input propertychange");
        $(".quotaRank").bind('input propertychange', function () {
            if ($(this).val() == "排名最大" || $(this).val() == "排名最小") {
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "hidden");
            } else {
                $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
            }
        })

    });

    function commit() {
        $(".quotaRow").each(function () {
//           分别对应指标名称 指标排名方式 选股的多少 权重
            alert($(this).find(".numOfN").val() + $(this).find(".quotaName").html());
            alert($(this).find(".quotaRank").val());
            alert($(this).find(".quotaNum").val());
            alert($(this).find(".quotaWeight").val());
        });
    }
    $('#searchQuota').bind('input propertychange', function () {
        alert("11");
    });
    function quotaChange() {
        var quotaName;
//       alert($(this).html())
        if ($('#searchQuota').val().substring(0, 1) == "N") {
            quotaName = "<div class='col-md-5'><input class='numOfN form-control' value='10'></div><div style='margin-top: 6px' class='quotaName'>" + $('#searchQuota').val().substring(1,) + "</div>"
        } else {
            quotaName = "<div class='col-md-4' hidden><input class='numOfN form-control'></div><div style='margin-top: 6px' class='col-md-12 quotaName'>" + $('#searchQuota').val() + "</div>"
        }

        $("#quotaList").append("<tr class='quotaRow'>" +
            "<td class=\"col-md-4\"><div class='row'>" + quotaName + "</div></td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            "<select class=\"form-control col-md-12 quotaRank\" style=\"padding-left: 5px;padding-right: 5px\">" +
            "<option>排名最大</option>" +
            "<option>排名最小</option>" +
            "<option>排名%最大</option>" +
            "<option>排名%最小</option>" +
            "</select>" +
            "</div>" +
            "</td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            "<div class=\"col-md-10\">" +
            "<input type=\"text\" class=\"form-control quotaNum\" value=\"10\"/>" +
            "</div>" +
            "<div class='percent' style=\"margin-left: -10px;display: inline-block;visibility: hidden;margin-top: 5px\" >%</div>" +
            "</div>" +
            "</td>" +
            "<td class=\"col-md-2\">" +
            "<div class=\"row\">" +
            "<div class=\"col-md-9\">" +
            "<input type=\"text\" class=\"form-control quotaWeight\" value=\"1\"/>" +
            "</div>" +
            "</div>" +
            "</td>" +
            "<td class=\"col-md-1\"><button class=\"btn  btn-primary quotaBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>" +
            "</tr>");

        $(".quotaBt").unbind("click");
        $(".quotaBt").click(function () {
            $("#quotaList").find("tr").eq($(".quotaBt").index($(this))).remove();
            commit();
        });

    }
</script>
</body>
</html>
