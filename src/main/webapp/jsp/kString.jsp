<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/14
  Time: 19:04
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
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="../css/flat/red.css" rel="stylesheet">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/startLoader.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        footer {
            width: 100%;
            height: 100px;
            background-color: #444444;
        }
    </style>

    <title>个股</title>

    <style rel="stylesheet" type="text/css">

        .stock {
            font-family: "Microsoft YaHei";
            font-size: large;
            font-weight: bold;
            padding-bottom: 30px;
            padding-top: 50px;
        }

        .userBlockLeft {
            margin-top: 60px;
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
                <li><a id="stocks" onClick="JavaScript :history.back(1)" style="cursor: pointer">大盘详情</a></li>
                <li class="dropdown">
                    <a href="##" class="dropdown-toggle" data-toggle="dropdown">量化社区<span class="caret"></span></a>
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

<%--<div class="row userBlockLeft">--%>
    <%--<div class="col-md-2 col-lg-offset-6 input-group">--%>
        <%--<input type="text" id="stockText" class="form-control form-inline" >--%>
        <%--<span class="input-group-btn">--%>
                                    <%--<button class="btn btn-default form-control" id="searchButton" type="button">--%>
                                        <%--<span class="glyphicon glyphicon-search"> </span>--%>
                                    <%--</button>--%>
    <%--</span>--%>
    <%--</div>--%>
<%--</div>--%>


<div class="row stock" style="margin-top: 70px">
    <span class="col-md-2 col-md-offset-2"><span id="stockName">${stockOfEndDay.name}</span>&nbsp;<i id="stockCode">${stockOfEndDay.stockID.code}</i></span>
    <c:if test="${sessionScope.user!=null}">
        <span id="addBtn"><button class="btn"></button></span>
    </c:if>

</div>


<div class="row">
    <ul id="stockDetail" class="col-md-5 col-md-offset-2 list-inline">
        <li>开盘 <span class=" font-green ">${stockOfEndDay.open}</span></li>
        <li>最高 <span class=" font-red ">${stockOfEndDay.high}</span></li>
        <li>最低 <span class=" font-green ">${stockOfEndDay.low}</span></li>
        <li>昨收 <span class=" font-black ">${stockOfEndDay.preClose}</span></li>
        <li>成交量 <span>${stockOfEndDay.volume}</span></li>
        <li>成交额 <span>${stockOfEndDay.transactionAmount}</span></li>
        <li>涨跌幅 <span>${stockOfEndDay.increaseMargin}</span></li>
        <li>涨跌额 <span>${stockOfEndDay.fluctuation}</span></li>
        <li>换手率 <span>${stockOfEndDay.turnoverRate}</span></li>
        <li>总市值 <span>${stockOfEndDay.totalValue}</span></li>
        <li>流通市值 <span>${stockOfEndDay.circulationMarketValue}</span></li>
    </ul>
    <div class="col-md-4" style="margin-top: -70px;margin-left: -80px">
        <div id="one_stock_click_chart" style=" width:300px;height: 200px"></div>
    </div>
</div>





<div class="row">

    <div class="col-lg-2 col-lg-offset-2 userBlockLeft">

        <label>开始日期：</label>
        <!--指定 date标记-->
        <div class='input-group date' id='datetimeStart'>
            <input type='text' class="form-control form_datetime"/>
            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
        </div>
    </div>
    <div class="col-lg-2 userBlockLeft">
        <label>结束日期：</label>
        <!--指定 date标记-->
        <div class='input-group date' id='datetimeEnd'>
            <input type='text' class="form-control form_datetime"/>
            <span class="input-group-addon">
                       <span class="glyphicon glyphicon-calendar"></span>
                </span>

        </div>
    </div>
    <div class="col-lg-1 col-lg-offset-1 userBlockLeft">
        <a class="btn btn-info" onclick="changeSingleStockDetail()" style="margin-top: 15px;margin-left: -40px;">查看区间</a>
    </div>
    <div id="dateError"  class=" row col-md-offset-6 col-md-3" hidden>
        <strong  style="margin-top: 20px;color:red">选择区间没有数据，请重新选择~~~</strong>
    </div>

</div>
<div class="row " style="margin-top: 30px">
    <div class="col-md-offset-2 col-md-1">
        <div>
            <label class="radio1">
                <input class="radio_group1" type="radio" name="upPanel"  value="MA" checked>MA
            </label>
        </div>
    </div>
    <div class="col-md-1">
        <div>
            <label class="radio1">
                <input class="radio_group1" type="radio" name="upPanel" value="BOLL">BOLL
            </label>
        </div>
    </div>
</div>


<div class="row mychart">
    <div id="candlestick_chart" style="margin:0px auto; width:1300px;height:400px" ></div>
</div>

<div class="row mychart" hidden>
    <div id="boll_chart" style="margin:0px auto; width:1300px;height:400px" ></div>
</div>

<div class="row ">
    <div class=" col-md-offset-2 col-md-1">
        <div>
            <label class="radio2">
                <input class="radio_group2" type="radio" name="downPanel"  value="VOLUME" checked>VOLUME
            </label>
        </div>
    </div>
    <div class="col-md-1">
        <div>
            <label class="radio2">
                <input class="radio_group2" type="radio" name="downPanel" value="MACD">MACD
            </label>
        </div>

    </div>
</div>

<div class="row mychart" style="margin-bottom: 50px">
    <div id="volume_chart" style="margin:0px auto; width:1300px;height:200px" ></div>
</div>

<div class="row mychart" hidden  style="margin-bottom: 50px">
    <div id="MACD_chart" style="margin:0px auto; width:1300px;height:200px" ></div>
</div>


<!-- 登录模态框（Modal） -->
<%@ include file="logIn.jsp" %>

<footer>

</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>


<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="../js/icheck.js"></script>

<script src="../js/dbDatePicker.js"></script>
<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>
<script src="../js/echarts-liquidfill.js"></script>

<script src="../js/startLoaded.js"></script>
<script src="../js/logIn.js"></script>
<script src="../js/stocks.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.radio_group1').iCheck({
            checkboxClass: 'icheckbox_flat-red',
            radioClass: 'iradio_flat-red'
        });
        $('.radio_group2').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
//
//        $('.radio1').click(function () {
//            changePanel();
//        })
//
//        $('.radio2').click(function () {
//            changePanel();
//        })
        $('input').on('ifChecked', function(event){
            changePanel();
        });

        function changePanel() {
            var up=$("input[name='upPanel']:checked").val();
            var down=$("input[name='downPanel']:checked").val();
            if(up=="MA"){
                $(".mychart").eq(0).show();
                $(".mychart").eq(1).hide();
            }else{
                $(".mychart").eq(1).show();
                $(".mychart").eq(0).hide();
            }
            if(down=="VOLUME"){
                $(".mychart").eq(2).show();
                $(".mychart").eq(3).hide();
            }else{
                $(".mychart").eq(3).show();
                $(".mychart").eq(2).hide();
            }
        }

        //需要删除

        var candlestick =[['2016-6-22','4471.61','4576.49','4264.77','4577.94'],['2016/6/21','4471.61','4576.49','4264.77','4577.94'],['2016/6/20','4689.93','4478.36','4476.5','4744.08'],['2016/6/17','4942.52','4785.36','4780.87','4966.77'],['2016/6/16','4890.55','4967.9','4767.22','4983.66'],['2016/6/15','5004.41','4887.43','4842.1','5029.68'],['2016/6/14','5174.42','5062.99','5048.74','5176.79'],['2016/6/13','5143.34','5166.35','5103.4','5178.19'],['2016/6/10','5101.44','5121.59','5050.76','5122.46'],['2016/6/9','5049.2','5106.04','5001.49','5164.16'],['2016/6/8','5145.98','5113.53','5042.96','5147.45'],['2016/6/7','5045.69','5131.88','4997.48','5146.95'],['2016/6/6','5016.09','5023.1','4898.07','5051.63'],['2016/6/3','4912.95','4947.1','4647.41','4947.96'],['2016/6/2','4924.38','4909.98','4822.44','4942.06'],['2016/6/1','4844.7','4910.53','4797.55','4911.57'],['2016/5/31','4633.1','4828.74','4615.23','4829.5'],['2016/5/27','4603.47','4611.74','4431.56','4698.19'],['2016/5/26','4943.74','4620.27','4614.24','4986.5'],['2016/5/25','4932.85','4941.71','4857.06','4958.16'],['2016/5/24','4854.85','4910.9','4779.08','4911.69'],['2016/5/23','4660.08','4813.8','4656.83','4814.67'],['2016/5/20','4584.98','4657.6','4562.99','4658.27'],['2016/5/19','4456.44','4529.42','4438.26','4530.48'],['2016/5/18','4434.98','4446.29','4432.28','4520.54'],['2016/5/17','4285.78','4417.55','4285.78','4418.4'],['2016/5/16','4277.9','4283.49','4260.51','4324.83'],['2016/5/13','4366.82','4308.69','4278.55','4366.82'],['2016/5/12','4372.82','4378.31','4329.04','4397.75'],['2016/5/11','4402.38','4375.76','4342.48','4415.63'],['2016/5/10','4342.37','4401.22','4317.98','4402.31'],['2016/5/9','4231.27','4333.58','4187.82','4334.88'],['2016/5/6','4152.98','4205.92','4099.04','4206.86'],['2016/5/5','4197.9','4112.21','4108.01','4213.76'],['2016/5/4','4311.64','4229.27','4187.37','4376.35'],['2016/5/3','4479.85','4298.71','4282.24','4488.87'],['2016/5/2','4441.34','4480.46','4387.43','4487.57'],['2016/4/29','4483.01','4441.65','4441.05','4507.34'],['2016/4/28','4446.12','4476.62','4398.64','4499.94'],['2016/4/26','4527.63','4476.21','4432.9','4572.39'],['2016/4/26','4441.93','4527.4','4441.93','4529.73'],['2016/4/25','4355.95','4393.69','4318.12','4416.38'],['2016/4/22','4414.48','4414.51','4358.84','4444.41'],['2016/4/21','4304.6','4398.49','4297.95','4400.19'],['2016/4/20','4212.19','4293.62','4188.57','4294.38'],['2016/4/19','4301.35','4217.08','4190.68','4356'],['2016/4/18','4254.72','4287.3','4238.91','4317.22'],['2016/4/15','4055.92','4194.82','4031.24','4195.31'],['2016/4/14','4135.65','4084.16','4069.01','4175.49'],['2016/4/13','4125.78','4135.56','4091.26','4168.35'],['2016/4/12','4072.72','4121.71','4057.29','4128.07'],['2016/4/11','3947.49','4034.31','3929.32','4040.35'],['2016/4/8','4006.13','3957.53','3900.03','4016.4'],['2016/4/7','3976.53','3994.81','3903.65','4000.22'],['2016/4/6','3899.42','3961.38','3891.73','3961.67'],['2016/4/5','3803.38','3863.93','3792.21','3864.41'],['2016/4/4','3827.69','3825.78','3775.89','3835.45'],['2016/4/1','3748.34','3810.29','3742.21','3817.08'],['2016/3/31','3822.99','3747.9','3737.04','3835.57'],['2016-3-30','3822.99','3747.9','3737.04','3835.57'],['2016-3-29','3710.61','3786.57','3710.61','3795.94']];
        candlestick.reverse();

        var candlestick2 =[['2016-6-22','4471.61','4576.49','4264.77','4577.94'],['2016/6/21','4471.61','4576.49','4264.77','4577.94'],['2016/6/20','4689.93','4478.36','4476.5','4744.08'],['2016/6/17','4942.52','4785.36','4780.87','4966.77'],['2016/6/16','4890.55','4967.9','4767.22','4983.66'],['2016/6/15','5004.41','4887.43','4842.1','5029.68'],['2016/6/14','5174.42','5062.99','5048.74','5176.79'],['2016/6/13','5143.34','5166.35','5103.4','5178.19'],['2016/6/10','5101.44','5121.59','5050.76','5122.46'],['2016/6/9','5049.2','5106.04','5001.49','5164.16'],['2016/6/8','5145.98','5113.53','5042.96','5147.45'],['2016/6/7','5045.69','5131.88','4997.48','5146.95'],['2016/6/6','5016.09','5023.1','4898.07','5051.63'],['2016/6/3','4912.95','4947.1','4647.41','4947.96'],['2016/6/2','4924.38','4909.98','4822.44','4942.06'],['2016/6/1','4844.7','4910.53','4797.55','4911.57'],['2016/5/31','4633.1','4828.74','4615.23','4829.5'],['2016/5/27','4603.47','4611.74','4431.56','4698.19'],['2016/5/26','4943.74','4620.27','4614.24','4986.5'],['2016/5/25','4932.85','4941.71','4857.06','4958.16'],['2016/5/24','4854.85','4910.9','4779.08','4911.69'],['2016/5/23','4660.08','4813.8','4656.83','4814.67'],['2016/5/20','4584.98','4657.6','4562.99','4658.27'],['2016/5/19','4456.44','4529.42','4438.26','4530.48'],['2016/5/18','4434.98','4446.29','4432.28','4520.54'],['2016/5/17','4285.78','4417.55','4285.78','4418.4'],['2016/5/16','4277.9','4283.49','4260.51','4324.83'],['2016/5/13','4366.82','4308.69','4278.55','4366.82'],['2016/5/12','4372.82','4378.31','4329.04','4397.75'],['2016/5/11','4402.38','4375.76','4342.48','4415.63'],['2016/5/10','4342.37','4401.22','4317.98','4402.31'],['2016/5/9','4231.27','4333.58','4187.82','4334.88'],['2016/5/6','4152.98','4205.92','4099.04','4206.86'],['2016/5/5','4197.9','4112.21','4108.01','4213.76'],['2016/5/4','4311.64','4229.27','4187.37','4376.35'],['2016/5/3','4479.85','4298.71','4282.24','4488.87'],['2016/5/2','4441.34','4480.46','4387.43','4487.57'],['2016/4/29','4483.01','4441.65','4441.05','4507.34'],['2016/4/28','4446.12','4476.62','4398.64','4499.94'],['2016/4/26','4527.63','4476.21','4432.9','4572.39'],['2016/4/26','4441.93','4527.4','4441.93','4529.73'],['2016/4/25','4355.95','4393.69','4318.12','4416.38'],['2016/4/22','4414.48','4414.51','4358.84','4444.41'],['2016/4/21','4304.6','4398.49','4297.95','4400.19'],['2016/4/20','4212.19','4293.62','4188.57','4294.38'],['2016/4/19','4301.35','4217.08','4190.68','4356'],['2016/4/18','4254.72','4287.3','4238.91','4317.22'],['2016/4/15','4055.92','4194.82','4031.24','4195.31'],['2016/4/14','4135.65','4084.16','4069.01','4175.49'],['2016/4/13','4125.78','4135.56','4091.26','4168.35'],['2016/4/12','4072.72','4121.71','4057.29','4128.07'],['2016/4/11','3947.49','4034.31','3929.32','4040.35'],['2016/4/8','4006.13','3957.53','3900.03','4016.4'],['2016/4/7','3976.53','3994.81','3903.65','4000.22'],['2016/4/6','3899.42','3961.38','3891.73','3961.67'],['2016/4/5','3803.38','3863.93','3792.21','3864.41'],['2016/4/4','3827.69','3825.78','3775.89','3835.45'],['2016/4/1','3748.34','3810.29','3742.21','3817.08'],['2016/3/31','3822.99','3747.9','3737.04','3835.57'],['2016-3-30','3822.99','3747.9','3737.04','3835.57'],['2016-3-29','3710.61','3786.57','3710.61','3795.94']];
        candlestick2.reverse();

        var volume =['86160000','79330000','102600000','104890000','85230000','115230000','99410000','90120000','79990000', '107100000','81020000','91710000','84510000','118160000','89390000','89820000','100210000','102720000','134120000', '83770000','92570000','109090000','100920000','136670000','80100000','97060000','95020000','81530000','80020000','85590000','75790000','87390000','88560000','86640000','88440000','103260000','79120000','95530000','111990000','87790000','86480000','79180000','68940000','73190000','147390000','78530000','75560000','82270000','71870000','78750000','71260000','69690000','90540000','101690000','93740000','94130000','91950000','248680000','99380000','85130000','89440000'];
        var date = ['2016-03-29', '2016-03-30', '2016-03-31', '2016-04-01', '2016-04-04', '2016-04-05', '2016-04-06', '2016-04-07', '2016-04-08', '2016-04-11', '2016-04-12', '2016-04-13', '2016-04-14', '2016-04-15', '2016-04-18', '2016-04-19', '2016-04-20', '2016-04-21', '2016-04-22', '2016-04-25', '2016-04-26', '2016-04-27', '2016-04-28', '2016-04-29', '2016-05-02', '2016-05-03', '2016-05-04', '2016-05-05', '2016-05-06', '2016-05-09', '2016-05-10', '2016-05-11', '2016-05-12', '2016-05-13', '2016-05-16', '2016-05-17', '2016-05-18', '2016-05-19', '2016-05-20', '2016-05-23', '2016-05-24', '2016-05-25', '2016-05-26', '2016-05-27', '2016-05-31', '2016-06-01', '2016-06-02', '2016-06-03', '2016-06-06', '2016-06-07', '2016-06-08', '2016-06-09', '2016-06-10', '2016-06-13', '2016-06-14', '2016-06-15', '2016-06-16', '2016-06-17', '2016-06-20', '2016-06-21', '2016-06-22'];
        var volumeData = [];

        for(var i=0;i<volume.length;i++){
            var day = [];
            day.push(date[i]);
            day.push(volume[i]);
            volumeData.push(day);
        }

        var macd = [0.04,-0.01,0.01,0.03,-0.06,-0.10,0.07,0.12,0.16,0.09,
            0.04,-0.01,0.01,0.03,-0.06,-0.10,0.07,0.12,0.16,0.09,
            0.04,-0.01,0.01,0.03,-0.06,-0.10,0.07,0.12,0.16,0.09,
            0.04,-0.01,0.01,0.03,-0.06,-0.10,0.07,0.12,0.16,0.09,
            0.04,-0.01,0.01,0.03,-0.06,-0.10,0.07,0.12,0.16,0.09,
            0.04,-0.01,0.01,0.03,-0.06,-0.10,0.07,0.12,0.16,0.09,0.11];
        var dif = [0.11,0.08,0.09,0.05,0.03,0.06,0.15,0.22,0.25,0.27,
            0.11,0.08,0.09,0.05,0.03,0.06,0.15,0.22,0.25,0.27,
            0.11,0.08,0.09,0.05,0.03,0.06,0.15,0.22,0.25,0.27,
            0.11,0.08,0.09,0.05,0.03,0.06,0.15,0.22,0.25,0.27,
            0.11,0.08,0.09,0.05,0.03,0.06,0.15,0.22,0.25,0.27,
            0.11,0.08,0.09,0.05,0.03,0.06,0.15,0.22,0.25,0.27,0.35];
        var dea = [0.09,0.07,0.09,0.15,0.18,0.08,0.12,0.30,0.29,0.23,
            0.09,0.07,0.09,0.15,0.18,0.08,0.12,0.30,0.29,0.23,
            0.09,0.07,0.09,0.15,0.18,0.08,0.12,0.30,0.29,0.23,
            0.09,0.07,0.09,0.15,0.18,0.08,0.12,0.30,0.29,0.23,
            0.09,0.07,0.09,0.15,0.18,0.08,0.12,0.30,0.29,0.23,
            0.09,0.07,0.09,0.15,0.18,0.08,0.12,0.30,0.29,0.23,0.18];
        var macdData = [];

        for(var i=0;i<macd.length;i++){
            var day = [];
            day.push(date[i]);
            day.push(macd[i]);
            day.push(dif[i]);
            day.push(dea[i]);
            macdData.push(day);
        }



        var up = [4471.61,4576.49,4264.77,4577.94,4433.22,4141.2,4021.12,3999.13,3988.29,4000.13,
            4471.61,4576.49,4264.77,4577.94,4433.22,4141.2,4021.12,3999.13,3988.29,4000.13,
            4471.61,4576.49,4264.77,4577.94,4433.22,4141.2,4021.12,3999.13,3988.29,4000.13,
            4471.61,4576.49,4264.77,4577.94,4433.22,4141.2,4021.12,3999.13,3988.29,4000.13,
            4471.61,4576.49,4264.77,4577.94,4433.22,4141.2,4021.12,3999.13,3988.29,4000.13,
            4471.61,4576.49,4264.77,4577.94,4433.22,4141.2,4021.12,3999.13,3988.29,4000.13,4398,12];
        var mid = [];
        var low = [];

        for(var i=0;i<up.length;i++){
            mid.push(up[i]-200);
            low.push(up[i]-500);
        }

        function connect(chart1,chart2){
            echarts.connect([chart1,chart2]);
        }
    //TODO fjj 画真图
        <%--createCandlestickChart('candlestick_chart', ${candlestickData}, ${volumeData});--%>

        var c1= createCandlestick('candlestick_chart',candlestick);
        var c2= createVolume('volume_chart',volumeData);
        var c3=createMACD('MACD_chart',macdData);
        var c4=createBULL('boll_chart',candlestick2,up,mid,low);
        connect(c1,c3);
        connect(c2,c3);
        connect(c1,c4);
        connect(c2,c4);

        //todo fjj 当前日期
        $("#datetimeStart>input").attr('value', "2003-01-02");
        $("#datetimeEnd>input").attr('value', endTime);
    });
</script>
<script type="text/javascript">


    createClickChart("one_stock_click_chart", ${clickedData}, ${clickedDataStringRepre});
    $(document).ready(
        function() {
            $("#stockText").keydown(function(event) {
                if (event.keyCode == 13) {
//                  添加回测时间
                   //alert(event.target.value);
                }
            });
            $("#searchButton").click(function(event) {
                alert( $("#stockText").val());
            });

            var isPrivate = ${isPrivate};
            if(isPrivate){
                $("#addBtn button").html("取消收藏");
                $("#addBtn").on("click","button", deletePrivateStock);
            }
            else {
                $("#addBtn button").addClass("btn-primary");
                $("#addBtn button").html("加入收藏");
                $("#addBtn").on("click","button", addPrivateStock);
            }
        }
    );

    function addPrivateStock () {
//        alert("添加方法执行了");
        $.ajax({
            type: "get",
            async: true,
            url: "/user/addPrivate/" + $("#stockCode").text(),
            success: function (result) {
                if (result == "1") {
//                    alert("添加成功");
                    $("#addBtn button").html("取消收藏");
//                    $("#addBtn").unbind("click",addPrivateStock());
                    $("#addBtn button").removeClass("btn-primary");
                    $("#addBtn").off("click","button");
                    $("#addBtn").on("click","button",deletePrivateStock);
//                    $("#addBtn").bind("click",deletePrivateStock());
                }
                else {
                    alert("添加失败");
                }
            }
        });
    };

    function deletePrivateStock () {
//        alert("删除方法执行了");
        $.ajax({
            type: "get",
            async: true,
            url: "/user/deletePrivate/" + $("#stockCode").text(),
            success: function (result) {
                if (result == "1") {
//                    alert("删除成功");
                    $("#addBtn button").html("加入收藏");
                    $("#addBtn button").addClass("btn-primary");
                    $("#addBtn").off("click","button");
                    $("#addBtn").on("click","button",addPrivateStock);
                }
                else {
                    alert("删除失败");
                }
            }
        });
    };

    $("#stockDetail > li").addClass("col-md-5");





    <%--var candlestickChart = createCandlestickChart("candlestick_chart",${candlestickData},${volumeData});--%>
    function openStock() {
        $("body").removeClass('loaded');
        window.location.href="/stocks"
    }
    $("#stocks").addClass("act");
</script>
</body>
</html>