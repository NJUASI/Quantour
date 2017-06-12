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
<% response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0); %>
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
    <link href="../css/flat/green.css" rel="stylesheet">
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
    <%@ include file="header.jsp" %>
</header>

<form role="form" style="position:relative;margin-top: 60px;">
    <div class="row">
        <div class="panel panel-default col-lg-10 col-lg-offset-1 userBlock">
            <div class="panel-heading">
                <h4 class="panel-title">
                    选择条件
                </h4>
            </div>
            <div class="panel-body">
                <div class="row">

                    <div class="form-group col-md-3 col-md-offset-1 inputBlock">
                        <label class="col-md-5" style="margin-top: 7px">收益基准：</label>
                        <div class='col-md-7' style="margin-left: 0px">
                            <select id="baseStockEve" name="baseStockEve"
                                    class="selectpicker show-tick form-control col-md-7">
                                <option value="沪深300" selected>沪深300</option>
                                <option value="创业板指">创业板指</option>
                                <option value="中小板指">中小板指</option>
                                <option value="上证指数">上证指数</option>
                                <option value="深证指数">深证指数</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group col-md-4 inputBlock">
                        <label class="col-md-4" style="margin-top: 7px;margin-left: 40px">调仓周期：</label>
                        <div class=' col-md-4 ' style="margin-left: -30px">
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
                    <div class="row col-md-2 col-md-offset-5">
                        <strong id="holdingPeriodError" hidden style="color:indianred;margin-left: 50px"> <span
                                class="glyphicon glyphicon glyphicon-remove-circle"></span> 请输入合法天数</strong>
                    </div>

                    <div class="row col-md-2 col-md-offset-2">
                        <strong id="maxHoldingError" hidden style="color:indianred;margin-left: 4px"><span
                                class="glyphicon glyphicon glyphicon-remove-circle"></span> 请输入合法股票数</strong>
                    </div>

                </div>


                <div class="row " style="margin-top: 15px">
                    <div class="col-md-offset-1 col-md-1">
                        <div>
                            <label class="radio1">
                                <input class="radio_group" type="radio" name="pool" value="blockPool" checked>全部股票
                            </label>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div>
                            <label class="radio1">
                                <input class="radio_group" type="radio" name="pool" value="myPool">我的股票
                            </label>
                        </div>
                    </div>
                </div>


                <div id="blockStock">
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

                        <div class="form-group col-md-3 inputBlock">
                            <label class="col-md-5" style="margin-top: 7px;margin-left: -50px">行业板块：</label>
                            <div class='col-md-7' style="margin-left: -30px">
                                <select id="industryBlock" name="industryBlock" multiple data-live-search="true"
                                        data-live-search-placeholder="请选择行业" data-selected-text-format="count > 2"
                                        data-actions-box="true" rows="10" style="width:200px"
                                        class="selectpicker show-tick form-control col-md-7">

                                    <option value="金融行业" selected>金融行业</option>
                                    <option value="房地产" selected>房地产</option>
                                    <option value="综合行业" selected>综合行业</option>
                                    <option value="建筑建材" selected>建筑建材</option>
                                    <option value="玻璃行业" selected>玻璃行业</option>
                                    <option value="家电行业" selected>家电行业</option>
                                    <option value="纺织行业" selected>纺织行业</option>
                                    <option value="食品行业" selected>食品行业</option>
                                    <option value="电子信息" selected>电子信息</option>
                                    <option value="交通运输" selected>交通运输</option>
                                    <option value="汽车制造" selected>汽车制造</option>
                                    <option value="商业百货" selected>商业百货</option>
                                    <option value="电力行业" selected>电力行业</option>
                                    <option value="酒店旅游" selected>酒店旅游</option>
                                    <option value="机械行业" selected>机械行业</option>
                                    <option value="农林牧渔" selected>农林牧渔</option>
                                    <option value="电器行业" selected>电器行业</option>
                                    <option value="电子器件" selected>电子器件</option>
                                    <option value="石油行业" selected>石油行业</option>
                                    <option value="有色金属" selected>有色金属</option>
                                    <option value="生物制药" selected>生物制药</option>
                                    <option value="医疗器械" selected>医疗器械</option>
                                    <option value="物资外贸" selected>物资外贸</option>
                                    <option value="传媒娱乐" selected>传媒娱乐</option>
                                    <option value="发电设备" selected>发电设备</option>
                                    <option value="水泥行业" selected>水泥行业</option>
                                    <option value="塑料制品" selected>塑料制品</option>
                                    <option value="钢铁行业" selected>钢铁行业</option>
                                    <option value="化纤行业" selected>化纤行业</option>
                                    <option value="农药化肥" selected>农药化肥</option>
                                    <option value="公路桥梁" selected>公路桥梁</option>
                                    <option value="造纸行业" selected>造纸行业</option>
                                    <option value="化工行业" selected>化工行业</option>
                                    <option value="环保行业" selected>环保行业</option>
                                    <option value="煤炭行业" selected>煤炭行业</option>
                                    <option value="酿酒行业" selected>酿酒行业</option>
                                    <option value="供水供气" selected>供水供气</option>
                                    <option value="开发区" selected>开发区</option>
                                    <option value="印刷包装" selected>印刷包装</option>
                                    <option value="纺织机械" selected>纺织机械</option>
                                    <option value="仪器仪表" selected>仪器仪表</option>
                                    <option value="飞机制造" selected>飞机制造</option>
                                    <option value="其它行业" selected>其它行业</option>
                                    <option value="家具行业" selected>家具行业</option>
                                    <option value="摩托车" selected>摩托车</option>
                                    <option value="服装鞋类" selected>服装鞋类</option>
                                    <option value="陶瓷行业" selected>陶瓷行业</option>
                                    <option value="船舶制造" selected>船舶制造</option>
                                    <option value="次新股" selected>次新股</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="row col-md-2 col-md-offset-2">
                            <strong id="blockTypesError" style="color:indianred;margin-left: 33px" hidden> <span
                                    class="glyphicon glyphicon glyphicon-remove-circle"></span>板块不能为空</strong>
                        </div>

                        <div class="row col-md-2 col-md-offset-2">
                            <strong id="industryError" style=" color:indianred;margin-left: -45px" hidden> <span
                                    class="glyphicon glyphicon glyphicon-remove-circle"></span> 行业不能为空</strong>
                        </div>


                    </div>
                    <div class="row">
                        <div class="form-group row col-md-4 col-md-offset-1 inputBlock">
                            <label class="col-md-4" style="margin-top: 7px">ST信息：</label>
                            <div class=' col-md-7' style="margin-left: -25px">
                                <select id="stType" class="col-md-10 selectpicker show-tick form-inline">
                                    <option value="INCLUDE" selected>包含ST</option>
                                    <option value="EXCLUDE">排除ST</option>
                                    <option value="ONLY">仅有ST</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group col-md-3 inputBlock">
                            <label class="col-md-5" style="margin-top: 7px;margin-left: -50px">地域板块：</label>
                            <div class='col-md-7' style="margin-left: -30px">
                                <select id="provinceBlock" name="provinceBlock" multiple data-live-search="true"
                                        data-live-search-placeholder="请选择地域" data-selected-text-format="count > 2"
                                        data-actions-box="true" data-size="10"
                                        class="selectpicker show-tick form-control col-md-7">
                                    <option value="深圳" selected>深圳</option>
                                    <option value="北京" selected>北京</option>
                                    <option value="吉林" selected>吉林</option>
                                    <option value="江苏" selected>江苏</option>
                                    <option value="辽宁" selected>辽宁</option>
                                    <option value="广东" selected>广东</option>
                                    <option value="浙江" selected>浙江</option>
                                    <option value="湖南" selected>湖南</option>
                                    <option value="河北" selected>河北</option>
                                    <option value="新疆" selected>新疆</option>
                                    <option value="山东" selected>山东</option>
                                    <option value="河南" selected>河南</option>
                                    <option value="山西" selected>山西</option>
                                    <option value="江西" selected>江西</option>
                                    <option value=" 安徽" selected>安徽</option>
                                    <option value=" 湖北" selected>湖北</option>
                                    <option value=" 内蒙" selected>内蒙</option>
                                    <option value="海南" selected>海南</option>
                                    <option value="四川" selected>四川</option>
                                    <option value="重庆" selected>重庆</option>
                                    <option value="陕西" selected>陕西</option>
                                    <option value="广西" selected>广西</option>
                                    <option value="福建" selected>福建</option>
                                    <option value="天津" selected>天津</option>
                                    <option value="云南" selected>云南</option>
                                    <option value="贵州" selected>贵州</option>
                                    <option value="甘肃" selected>甘肃</option>
                                    <option value="黑龙江" selected>黑龙江</option>
                                    <option value="宁夏" selected>宁夏</option>
                                    <option value="青海" selected>青海</option>
                                    <option value="上海" selected>上海</option>
                                    <option value="西藏" selected>西藏</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="row col-md-2 col-md-offset-6">
                            <strong id="provinceError" style="color:indianred;margin-left: -60px" hidden> <span
                                    class="glyphicon glyphicon glyphicon-remove-circle"></span> 地域不能为空</strong>
                        </div>
                    </div>

                </div>

                <div id="myStockPool" hidden style="margin-top: 20px;margin-bottom: 20px">
                    <div class="row col-md-offset-2">
                        <button type="button" id="modifyPool" class="btn btn-primary">编辑我的股票池</button>
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

            <%@ include file="quotaSelect.jsp" %>
        </div>

        <div class="panel panel-default col-md-10 col-md-offset-1 userBlock">
            <div class="panel-heading">
                <h4 class="panel-title">
                    大盘择时
                </h4>
            </div>
            <div class="panel-body">

                <div class="row " style="margin-top: 15px">
                    <div class="col-md-offset-1 col-md-1">
                        <div>
                            <label class="radio2">
                                <input class="market_timing" type="radio" name="market_timing" value="no" checked>不择时
                            </label>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div>
                            <label class="radio2">
                                <input class="market_timing" type="radio" name="market_timing" value="yes">使用择时
                            </label>
                        </div>
                    </div>
                </div>
                <div id="timingPanel">
                    <div style="border-bottom: 1px solid #CCCCCC;width: 88%;margin:20px auto 20px"></div>
                    <div class="row">
                        <label class="col-md-1 col-md-offset-1" style="margin-top: 7px">同时满足</label>
                        <div class=' col-md-1 ' style="margin-left: -27px">
                            <input id="timing_text1" type="text" class="form-control col-md-1" placeholder="条件数">
                        </div>
                        <label class="col-md-2" style="margin-top: 7px">个择时条件由熊变牛</label>

                        <label class="col-md-1" style="margin-top: 7px">同时满足</label>
                        <div class=' col-md-1 ' style="margin-left: -27px">
                            <input id="timing_text2" type="text" class="form-control col-md-1" placeholder="条件数">
                        </div>
                        <label class="col-md-3" style="margin-top: 7px">个择时条件由牛变熊</label>

                    </div>
                    <div class="row" style="margin-top: 20px">
                        <!--左边选择栏  -->
                        <div class="col-md-4 col-md-offset-1">
                            <label class="" style="margin-bottom: 30px">
                                择时指标:
                            </label>


                            <!-- 选项卡面板 -->
                            <div class="row">
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default timing"
                                            style="border: 0px solid white">MA
                                    </button>
                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default timing"
                                            style="border: 0px solid white">MACD
                                    </button>
                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default timing"
                                            style="border: 0px solid white">DMA
                                    </button>
                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default timing"
                                            style="border: 0px solid white">TRIX
                                    </button>
                                </div>
                                <div class="col-md-4">
                                    <button type="button" class="btn btn-default timing"
                                            style="border: 0px solid white">MAVOL
                                    </button>
                                </div>
                            </div>
                            <strong class="row col-md-offset-4" id="timingError"
                                    style="color:indianred;margin-left: 4px" hidden><span
                                    class="col-md-offset-2 glyphicon glyphicon glyphicon-remove-circle"></span><span> 你已经选择过此条件</span></strong>


                        </div>
                        <!--右边数据框-->
                        <div class="col-md-6" style=" border-left: 1px solid slategray;">
                            <label class="row col-md-3" style="margin-top:5px">
                                择时条件:
                            </label>
                            <!-- 选项卡组件（菜单项nav-tabs）-->


                            <div class="row">
                                <div class="col-md-12 col-xs-12"
                                     style="height:200px;max-height: 240px;overflow-y: auto">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>择时条件</th>
                                            <th>择时参数</th>
                                            <th>编辑</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="timingList">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

        </div>


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
                    <strong style="color:indianred;margin-left: 4px"><span
                            class="glyphicon glyphicon glyphicon-remove-circle"></span><span
                            id="wholeError"> 请输入合法股票数</span></strong>
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
                            <input type="text" id="strategyName" class="form-control"
                                   style="margin-top: -17px;margin-left: -30px"
                                   placeholder="">
                        </div>
                        <div class="col-md-offset-2 col-md-5" id="nameErrorPanel" hidden>
                            <strong style="color:indianred;margin-left: 14px"><span
                                    class="glyphicon glyphicon glyphicon-remove-circle"></span><span
                                    id="nameError"></span></strong>
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
                                    <textarea rows="4" id="strategyDescription"
                                              style="width: 90%; margin-left: -20px"></textarea>
                                </div>
                            </div>
                            <div id="descriptionError" class="row col-md-offset-2" hidden>
                                <strong style="color:indianred;margin-left: 14px"><span
                                        class="glyphicon glyphicon glyphicon-remove-circle"></span><span>输入策略描述吸引更多人订阅</span></strong>
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
                                <button type="button"
                                        onclick='ensureCreate("<%=((User)session.getAttribute("user")).getUserName()%>")'
                                        class="btn btn-primary">创建策略
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="strategyPool" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 40%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">编辑股票池</h4>
            </div>
            <div class="modal-body">
                <p class="static" style="margin-top: 5px;">股票列表:</p>
                <textarea type="text" id="poolCode" class="form-control" rows="10"
                          placeholder="请输入股票代码 以空格隔开"></textarea>

                <strong id="poolCodeError" style="color:indianred;margin-left: 0px"></strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="savePool" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="circleModal" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:70%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">周期买卖详情</h4>
            </div>
            <div class="modal-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>股票代码</th>
                        <th>股票名</th>
                        <th>行业分类</th>
                        <th>开始价格</th>
                        <th>结束价格</th>
                        <th>涨幅</th>
                        <th>本期起始仓</th>
                        <th>当日成交额</th>
                        <th>股价振幅</th>
                    </tr>
                    </thead>
                    <tbody id="circleList">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">

            </div>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="modifyTimingModal" tabindex="-1" role="dialog" aria-labelledby="loginLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:40%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">择时条件设定</h4>
            </div>
            <div class="modal-body">
                <p>择时条件:<span id="theCondition">MA</span></p>
                <p class="col-md-offset-1 ma indicator" hidden>以移动平均线(MA)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="col-md-offset-1 macd indicator" hidden>
                    以指数平滑异同移动平均线(MACD)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="col-md-offset-1 dma indicator" hidden>以平均线差指标(DMA)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="col-md-offset-1 trix indicator" hidden>
                    以三重指数平滑移动平均指标(TRIX)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="col-md-offset-1 mavol indicator" hidden>
                    以指数成交量移动平均线(MAVOL)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p>择时参数:</p>
                <div class="row inputBlock">
                    <div class="col-md-offset-1 col-md-3" style="margin-top: 6px"><span>指数选择：</span>
                    </div>
                    <div class="col-md-3">
                        <select id="timingBlock" class="form-control">
                            <option value="沪深300">沪深300</option>
                            <option value="创业板指">创业板指</option>
                            <option value="中小板指">中小板指</option>
                            <option value="上证指数">上证指数</option>
                            <option value="深证指数">深证指数</option>
                        </select>
                    </div>
                </div>
                <div class="row inputBlock">
                    <div class="col-md-offset-1 col-md-3" style="margin-top: 6px"><span>周期选择：</span>
                    </div>
                    <div class="col-md-3">
                        <select id="timingType" class="form-control">
                            <option value="日">日</option>
                            <option value="周">周</option>
                            <option value="月">月</option>
                        </select>
                    </div>
                </div>
                <div id="textArea">

                </div>

                <%--<div class="range-box" style="display: none;"><span class="leftName">板块选择：</span><select class="form-control">--%>
                <%--<option value="0">中小板</option><option value="1">主板</option><option value="2">创业板</option><option value="4">全市场</option><option value="1000001">上证指数</option><option value="1399905">中证500</option><option value="1000300">沪深300</option><option value="1399006">创业板指数</option><option value="1399005">中小板指数</option><option value="1399001">深证成指</option><option value="1000902">中证流通</option><option value="1000852">中证1000</option><option value="1399303">国证2000</option><option value="1000818">细分金融</option><option value="1000816">细分地产</option><option value="1000815">细分食品</option><option value="1000814">细分医药</option><option value="1000813">细分化工</option><option value="1000812">细分机械</option><option value="1000811">细分有色</option><option value="1000810">细分能源</option><option value="1000809">细分农业</option><option value="1801010">申万农林牧渔</option><option value="1801020">申万采掘</option><option value="1801030">申万化工</option><option value="1801040">申万钢铁</option><option value="1801050">申万有色金属</option><option value="1801080">申万电子</option><option value="1801110">申万家用电器</option><option value="1801120">申万食品饮料</option><option value="1801130">申万纺织服装</option><option value="1801140">申万轻工制造</option><option value="1801150">申万医药生物</option><option value="1801160">申万公用事业</option><option value="1801170">申万交通运输</option><option value="1801180">申万房地产</option><option value="1801200">申万商业贸易</option><option value="1801210">申万休闲服务</option><option value="1801230">申万综合</option><option value="1801710">申万建筑材料</option><option value="1801720">申万建筑装饰</option><option value="1801730">申万电气设备</option><option value="1801740">申万国防军工</option><option value="1801750">申万计算机</option><option value="1801760">申万传媒</option><option value="1801770">申万通信</option><option value="1801780">申万银行</option><option value="1801790">申万非银金融</option><option value="1801880">申万汽车</option><option value="1801890">申万机械设备</option></select></div>--%>
                <%--<div class="clear"></div>--%>
                <%--<div class="period-box"><span class="leftName">周期选择：</span><select class="form-control">--%>
                <%--<option value="day">日</option>--%>
                <%--<option value="week">周</option>--%>
                <%--<option value="month">月</option>--%>
                <%--</select></div>--%>
                <%--<div class="clear"></div>--%>
                <%--<div class="ma para-box" style="display: none;"><div class="short"><span class="leftName">MA短线：</span><input type="text" class="short text" value="5"><span class="unit">日</span></div><div class="long"><span class="leftName">MA长线：</span><input type="text" class="long text" value="60"><span class="unit">日</span></div><div class="zero"><input type="checkbox"><span class="txt">金叉必须短线上行， 死叉必须短线下行</span></div></div>--%>
                <%--<div class="ema para-box" style="display: none;"><div class="short"><span class="leftName">EMA短线：</span><input type="text" class="short text" value="5"><span class="unit">日</span></div><div class="long"><span class="leftName">EMA长线：</span><input type="text" class="long text" value="60"><span class="unit">日</span></div><div class="zero"><input type="checkbox"><span class="txt">金叉必须短线上行， 死叉必须短线下行</span></div></div>--%>
                <%--<div class="macd para-box" style="display: block;"><div class="short"><span class="leftName">DIF短线：</span><input type="text" class="short text" value="12"><span class="unit">日</span></div><div class="long"><span class="leftName">DIF长线：</span><input type="text" class="long text" value="26"><span class="unit">日</span></div><div class="long"><span class="leftName">DEA：</span><input type="text" class="m text" value="9"><span class="unit">日</span></div><div class="zero"><input type="checkbox" value="0"><span class="txt">金叉只出现在0轴上方，死叉只出现在0轴下方</span></div></div>--%>
                <%--<div class="dma para-box" style="display: none;"><div class="short"><span class="leftName">MA短线：</span><input type="text" class="short text" value="5"><span class="unit">日</span></div><div class="long"><span class="leftName">MA长线：</span><input type="text" class="long text" value="60"><span class="unit">日</span></div><div class="long"><span class="leftName">AMA：</span><input type="text" class="m text" value="20"><span class="unit">日</span></div></div>--%>
                <%--<div class="trix para-box" style="display: none;"><div class="short"><span class="leftName">TRIX：</span><input type="text" class="n text" value="120"><span class="unit">日</span></div><div class="long"><span class="leftName">MATRIX ：</span><input type="text" class="m text" value="5"><span class="unit">日</span></div></div>--%>
                <%--<div class="mavol para-box" style="display: none;"><div class="short"><span class="leftName">MA短线：</span><input type="text" class="short text" value="5"><span class="unit">日</span></div><div class="long"><span class="leftName">MA长线：</span><input type="text" class="long text" value="60"><span class="unit">日</span></div><div class="zero"><input type="checkbox"><span class="txt">金叉必须短线上行， 死叉必须短线下行</span></div></div>--%>
                <%--<div class="bias para-box" style="display: none;"><div class="short"><span class="leftName">MA短线：</span><input type="text" class="short text" value="1"><span class="unit">日</span></div><div class="long"><span class="leftName">MA长线：</span><input type="text" class="long text" value="20"><span class="unit">日</span></div><div class="low"><span class="leftName">下限：</span><input type="text" class="low text" value="-10"><span>%</span></div><div class="high"><span class="leftName">上限：</span><input type="text" class="high text" value="10"><span>%</span></div></div>--%>
                <%--<div class="pe para-box" style="display: none;"><div class="low"><span class="leftName">下限：</span><input type="text" class="low text" value="15"></div><div class="high"><span class="leftName">上限：</span><input type="text" class="high text" value="30"></div></div>--%>
                <%--<div class="pb para-box" style="display: none;"><div class="low"><span class="leftName">下限：</span><input type="text" class="low text" value="2"></div><div class="high"><span class="leftName">上限：</span><input type="text" class="high text" value="4"></div></div>--%>
                <%--<div class="pe2 para-box" style="display: none;"><div class="low"><span class="leftName">下限：</span><input type="text" class="low text" value="40"></div><div class="high"><span class="leftName">上限：</span><input type="text" class="high text" value="60"></div></div>--%>
                <%--<div class="pb2 para-box" style="display: none;"><div class="low"><span class="leftName">下限：</span><input type="text" class="low text" value="2"></div><div class="high"><span class="leftName">上限：</span><input type="text" class="high text" value="4"></div></div>--%>
                <%--<p class="error"></p>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary save">确定</button>
            </div>
        </div>
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
<%--<script src="../js/timingSelect.js"></script>--%>

<script src="../js/icheck.js"></script>

<script src="../js/bootstrap-slider.js"></script>
<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../js/myValidate.js"></script>
<script src="../js/dbDatePicker.js"></script>
<script type="text/javascript">


    $(document).ready(function () {

        $("#stocks").click(function () {
            $("body").removeClass("loaded");
            window.location.href = "/stocks";
        });

        $('.radio_group').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });

        $('.radio_group').on('ifChecked', function () {

            $("#blockStock").slideToggle("slow");
            $("#myStockPool").slideToggle("slow");

        });

        $('#modifyPool').click(function () {
            $("#strategyPool").modal("toggle");
            //TODO FJJ 从我的底层股票初始化编辑页面
            $("#poolCode").val("123456 000001 000002");
        })

        $('#savePool').click(function () {
            //TODO FJJ 从我的底层股票初始化编辑页面
            //TODO FJJ  存数据库 需要 返回成功 或者哪个代码不存在
            var codes = $("#poolCode").val().split(" ");
            alert($("#poolCode").val());
            var reg = /^\d{6}$/;
            for (var i = 0; i < codes.length; i++) {
                var code = codes[i];
                if (!reg.test(code)) {
                    $("#poolCodeError").html("你输入的代码" + code + "格式不正确");
                    setTimeout("$(\"#poolCodeError\").html('')", 3000);
                    return false;
                }
            }

            //TODO fjj codes 是保存的代码数组

        });


        $("#community").addClass("act");

        //下面是择时js
        $('.market_timing').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });

        $('.market_timing').on('ifChecked', function () {
            $("#timingPanel").slideToggle("slow");
        });

        $(".timing").click(function () {
            var thisName = $(this).html().trim();
            var isExist;
            $(".timingName").each(function () {
                if (thisName == $(this).html().trim()) {
                    isExist = true;
                    $("#timingError").show();
                    setTimeout("$(\"#timingError\").hide()", 1000)
                    return true;
                }
            });
            if (isExist) {
                return false;
            }
            var timingName = "<div class='timingName'>" + $(this).html() + "</div>";
            var timingParam;
            var name = $(this).html().trim();
            switch (name) {
                case "MA":
                    timingParam = "<div class='timingParam'>上证指数,日线,5,60</div>";
                    break;
                case "DMA":
                    timingParam = "<div class='timingParam'>上证指数,日线,5,60,20</div>";
                    break;
                case "TRIX":
                    timingParam = "<div class='timingParam'>上证指数,日线,120,5</div>";
                    break;
                case "MAVOL":
                    timingParam = "<div class='timingParam'>上证指数,日线,5,60</div>";
                    break;
                case "MACD":
                    timingParam = "<div class='timingParam'>上证指数,日线,12,26,9</div>"
                    break;
            }


            $("#timingList").append("<tr>" +
                "<td class=\"col-md-2\">" + timingName + "</td>" +
                "<td class=\"col-md-3\">" + timingParam + "</td>" +
                "<td class=\"col-md-1\"><button class=\"btn  btn-primary modifyTimingBT\"><span class=\"glyphicon glyphicon-pencil\"></span></button></td>" +
                "<td class=\"col-md-1\"><button class=\"btn  btn-primary removeTimingBT\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>" +
                "</tr>");


            $(".modifyTimingBT").unbind("click");
            $(".modifyTimingBT").click(function (e) {
                $("#modifyTimingModal").modal("toggle");
                var clickName = $("#timingList").find("tr").eq($(".modifyTimingBT").index($(this))).find(".timingName").eq(0).html().trim();
                var clickParam = $("#timingList").find("tr").eq($(".modifyTimingBT").index($(this))).find(".timingParam").eq(0).html().trim();
                var myParam=clickParam.split(",");
                $("#timingBlock").val(myParam[0]);
                $("#timingType").val(myParam[1]);
                $(".indicator").hide()
                switch (clickName) {
                    case "MA":
                        $(".ma").show();
                       $("#textArea").empty();
                        $("#textArea").append()
//                    <div class="row inputBlock">
//                        <div class="col-md-offset-1 col-md-3" style="margin-top: 6px"><span
//                    class="leftName">周期选择：</span>
//                    </div>
//                    <div class="col-md-2">
//                        <input type="text" class="form-control rightNum">
//                        </div>
//                        <div class="col-md-3" style="margin-top: 6px"><span
//                    class="rightName">日</span>
//                    </div>
//                    </div>
                        break;
                    case "DMA":
                        $(".dma").show();
                        break;
                    case "TRIX":
                        $(".trix").show();
                        break;
                    case "MAVOL":
                        $(".mavol").show();
                        break;
                    case "MACD":
                        $(".macd").show();
                        break;
                }
                e.stopPropagation();
                e.preventDefault()
            });
            function addText() {
                
            }
            $(".removeTimingBT").unbind("click");
            $(".removeTimingBT").click(function () {
                $("#timingList").find("tr").eq($(".removeTimingBT").index($(this))).remove();
            });

        });


    });


    function getTraceBackCriteria() {
        var user = "<%= (User)session.getAttribute("user")%>";
        if (user == "null") {
            $("#login").modal("toggle");
            $("#wholeMessage").show();
            $("#wholeError").html("请先登录");
            setTimeout("$('#wholeMessage').hide();", 5000);
            return false;
        }
        // alert($("#startDate").val() + "\n" + $("#endDate").val() + "\n" + $("#formativePeriod").val() + "\n" + $("#holdingPeriod").val()
        //     + "\n" + $("#stType").val() + "\n" +$("#blockTypes").val() + "\n" +$("#baseStockEve").val() + "\n" + isCustomized
        //     + "\n" +$("#formativeStrategy").val() + "\n" +$("#pickStrategy").val() + "\n"+$("#rank").val());
        var isZero;


        var reg = /^\d{1,2}$/;
        if (!reg.test($('#maxHolding').val())) {
            $('#maxHoldingError').show();
            $('#maxHolding').css("border", "1px solid red");
            window.location.href = "#";
            isZero = false;
        }
        if (!reg.test($("#holdingPeriod").val())) {
            $('#holdingPeriodError').show();
            $("#holdingPeriod").css("border", "1px solid red");
            window.location.href = "#";
            isZero = false;
        }
        ;

        var reg2 = /^[1-9]$/;
        $(".quotaWeight").each(function () {
            var num = $(this).val();
            if (num == 0 || num == "0") {
                $(this).css("border", "2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html(" 你输入的排名条件错误");
                setTimeout("$('#wholeMessage').hide();", 3000)
                isZero = false;
            } else if (!reg2.test($(this).val())) {
                $(this).css("border", "2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html(" 你输入的排名条件错误");
                setTimeout("$('#wholeMessage').hide();", 3000)
                isZero = false;
            }
        });
        var reg3 = /^\d{1,20}$/;
        var isZero;
        $(".quotaNum").each(function () {
            var num = $(this).val();
            if (num == 0 || num == "0") {
                $(this).css("border", "2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html("你输入的筛选条件错误");
                setTimeout("$('#wholeMessage').hide();", 3000)
                isZero = false;
            } else if (!reg3.test($(this).val())) {
                $(this).css("border", "2px solid red");
                $("#wholeMessage").show();
                $("#wholeError").html(" 你输入的筛选条件错误");
                setTimeout("$('#wholeMessage').hide();", 3000)
                isZero = false;
            }
        });
        var reg4 = /^\d{1,3}$/;
        $(".numOfN").each(function () {
            var temp = $(this).is(":hidden");
            if (!temp) {
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

        //自选股票池
        var pool = $("input[name='pool']:checked").val();
        if (pool == "myPool") {
        } else {
            if ($("#blockTypes").val() == " " || $("#blockTypes").val() == "" || $("#blockTypes").val() == null) {
                $('#blockTypesError').show();
                setTimeout("$('#blockTypesError').hide();", 3000);
                window.location.href = "#";
                isZero = false;
            }

            if ($("#industryBlock").val() == " " || $("#industryBlock").val() == "" || $("#industryBlock").val() == null) {
                $('#industryError').show();
                setTimeout("$('#industryError').hide();", 3000);
                window.location.href = "#";
                isZero = false;
            }
            if ($("#provinceBlock").val()[0] == "" || $("#provinceBlock").val()[0] == null) {
                $('#provinceError').show();
                setTimeout("$('#provinceError').hide();", 3000);
                window.location.href = "#";
                isZero = false;
            }
        }

        if (isZero == false) {
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
        if (rankNum + filterNum == 0) {
            $("#wholeMessage").show();
            $("#wholeError").html(" 请选择筛选条件或者排名条件");
            setTimeout("$('#wholeMessage').hide();", 3000)
            return false;
        }


        // 添加择时条件
        var marketSelectingConditions = new Array();
        var marketSelectingNum = 0;

        // "formativePeriod": $("#formativePeriod").val();

//        alert("filterConditions: " +  filterConditions + "\n\n" + "rankConditions: " + rankConditions);
        var start = new Date($("#startDate").val());
        var end = new Date($("#endDate").val());
        if (end - start < 1000 * 60 * 60 * 24 * $("#holdingPeriod").val()) {
            $("#wholeMessage").show();
            $("#wholeError").html(" 你输入的日期少于持仓周期");
            setTimeout("$('#wholeMessage').hide();", 3000)
            return false;
        }

        if (pool == "myPool") {
            //TODO fjj 如果是自己的股票池从数据库得到股票池
        } else {
            //TODO 从界面拿各种板块信息
        }

//        TODO fjj  行业和地狱的获得
        $("#provinceBlock").val();
        $("#industryBlock").val();

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

        if (jsonData == false) {
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
                    $("#tb_cycle_ab").append("<tr>" +
                        "<td>" + abReturnPeriod["positivePeriodsNum"] + "</td>" +
                        "<td>" + abReturnPeriod["negativePeriodNum"] + "</td>" +
                        "<td>" + abReturnPeriod["winRate"] + "</td>" +
                        "</tr>");

                    $("#tb_cycle_re").empty();
                    $("#tb_cycle_re").append("<tr>" +
                        "<td>" + reReturnPeriod["positivePeriodsNum"] + "</td>" +
                        "<td>" + reReturnPeriod["negativePeriodNum"] + "</td>" +
                        "<td>" + reReturnPeriod["winRate"] + "</td>" +
                        "</tr>");
                    // alert("--------------------2----------------");


                    // 持有周期详情
                    $("#tb_detail").empty();
                    for (var i = 0; i < holdingDetails.length; i++) {
                        $("#tb_detail").append("<tr>" +
                            "<td style='text-align: center'><span class='circle' style='color:#7291CA'>" + holdingDetails[i]["periodSerial"] + "<span></td>" +
                            "<td>" + holdingDetails[i]["startDate"] + "</td>" +
                            "<td>" + holdingDetails[i]["endDate"] + "</td>" +
                            "<td>" + holdingDetails[i]["holdingNum"] + "</td>" +
                            "<td>" + (holdingDetails[i]["strategyReturn"] * 100).toFixed(2) + "%" + "</td>" +
                            "<td>" + (holdingDetails[i]["baseReturn"] * 100).toFixed(2) + "%" + "</td>" +
                            "<td>" + (holdingDetails[i]["excessReturn"] * 100).toFixed(2) + "%" + "</td>" +
                            "<td>" + holdingDetails[i]["remainInvestment"].toFixed(2) + "</td>" +
                            "</tr>");
                    }

                    $(".circle").click(function () {
                        alert($(this).html());

                        //TODO $(this).html() 为当前需要看的周期，加一下此周期持有股票信息
//                        $("#circleList").empty();
//                        for (var i = 0; i < circleList.length; i++) {
//                            $("#circleList").append("<tr>"+
//                                "<td>" + 股票代码+ "</td>"+
//                                "<td>" + 股票名" + "</td>"+
//                                "<td>" + 行业分类+ "</td>"+
//                                "<td>" + 开始价格+ "</td>"+
//                                "<td>" + 结束价格 + "%" + "</td>"+
//                                "<td>" + 涨幅 + "</td>"+
//                                "<td>" + 本期起始仓 + "</td>"+
//                                "<td>" + 当日成交额 + "</td>"+
//                                "<td>" + 股价振幅 + "</td>"+
//                                "</tr>");
//                        }
                        $("#circleModal").modal("toggle");
                    })

                    $(".circle").hover(function () {
                        $(this).css({"cursor": "pointer", "text-decoration": " underline"});
                    }, function () {
                        $(this).css({"color": "#7291CA", "text-decoration": " none"});
                    });

                    // alert("--------------------3----------------");

                    // 卖出的股票详情
                    $("#sold_stock_detail").empty();
                    for (var i = 0; i < transferDetails.length; i++) {
                        $("#sold_stock_detail").append("<tr>" +
                            "<td>" + transferDetails[i]["stockName"] + "</td>" +
                            "<td>" + transferDetails[i]["stockCode"] + "</td>" +
                            "<td>" + transferDetails[i]["buyDate"] + "</td>" +
                            "<td>" + transferDetails[i]["sellDate"] + "</td>" +
                            "<td>" + transferDetails[i]["buyPrice"] + "</td>" +
                            "<td>" + transferDetails[i]["sellPrice"] + "</td>" +
                            "<td>" + (transferDetails[i]["changeRate"] * 100).toFixed(2) + "%" + "</td>" +
                            "</tr>");
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
                    setTimeout("$('#wholeMessage').hide();", 3000)
                } else {
                    $("#coverPanel").show();
                    $("#wholeMessage").show();
                    $("#wholeError").html("请再次确认您输入的内容是否合法");
                    setTimeout("$('#wholeMessage').hide();", 3000)
                }
            },

            error: function (result) {
                $("#coverPanel").show();
                $("#wholeMessage").show();
                $("#wholeError").html("请再次确认您输入的内容是否合法");
                setTimeout("$('#wholeMessage').hide();", 3000)
            }
        });
    }


    /**
     * 用户确认保存策略
     */
    function ensureCreate(curUser) {

        if ($("#strategyName").val().trim() == "") {
            $('#nameErrorPanel').show();
            $('#nameError').html("策略名称必须填写");
            return false;
        } else {
            $('#nameErrorPanel').hide();
        }


        if ($("#strategyDescription").val().trim() == "") {
            $('#descriptionError').show();
            return false;
        } else {
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
            data: {
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
                } else if (array[0] == "-2") {
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
            if (jsonData == false) {
                return false;
            } else {
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
