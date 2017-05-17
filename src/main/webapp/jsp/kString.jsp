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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>个股</title>

    <style rel="stylesheet" type="text/css">


        .stock {
            font-family: "Microsoft YaHei";
            font-size: large;
            font-weight: bold;
            padding-bottom: 30px;
            padding-top: 50px;
        }

        .messageBlock {
            margin-top: 10px;
            padding-right: 0;
            padding-left: 0;
        }

        .userBlockLeft {
            margin-top: 50px;
        }

        #passwordModify {
            display: none;
        }
    </style>

</head>


<header>
    <nav class="navbar navbar-inverse    nav-wrapper" style="margin-bottom: 0px">
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
                <li><a href="#">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <li><a href="#" style="color: #4cae4c">用户</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>
<div class="row userBlockLeft">
    <div class="col-md-2 col-lg-offset-6 input-group">
        <input type="text" id="stockText" class="form-control form-inline" >
        <span class="input-group-btn">
                                    <button class="btn btn-default form-control" id="searchButton" type="button">
                                        <span class="glyphicon glyphicon-search"> </span>
                                    </button>
    </span>
    </div>
</div>


<div class="row stock" style="margin-top: -60px">
    <span class="col-md-2 col-md-offset-2"><span id="stockName">艾派克</span>&nbsp;<i id="stockCode">002180</i></span>
    <button class="btn btn-primary ">+自选</button>
</div>


<div class="row">
    <ul id="stockDetail" class="col-md-6 col-md-offset-2 list-inline">
        <li>开盘 <span class=" font-green ">25.62</span></li>
        <li>最高 <span class=" font-red ">26.45</span></li>
        <li>最低 <span class=" font-green ">25.62</span></li>
        <li>涨停 <span class=" font-red ">28.25</span></li>
        <li>跌停 <span class=" font-green ">23.11</span></li>
        <li>昨收 <span class=" font-black ">25.68</span></li>
        <li>成交量 <span>67.03万</span></li>
        <li>成交额 <span>1747.02万</span></li>
        <li>总手 <span>6703.43</span></li>
        <li>振幅 <span>3.23%</span></li>
    </ul>
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
    <div class="col-lg-2 col-lg-offset-1 userBlockLeft">

        <input type="submit" class="btn btn-info"
               style="margin-top: 15px;margin-left: -40px;" value="查看区间"/>
        </button>
    </div>
</div>

<div class="row">
    <div class="col-md-offset-1 col-md-10">
        <div id="candlestick_chart"class="col-md-12" style="height: 600px"></div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>


<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>

<script type="text/javascript">

    $(document).ready(
        function() {
            $("#stockText").keydown(function(event) {
                if (event.keyCode == 13) {
//                  添加回车时间
                    alert(event.target.value);

                }
            })
            $("#searchButton").click(function(event) {
                alert( $("#stockText").val());

            })
        }
    );
    $("#stockDetail>li").addClass("col-md-3");
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


    var data1 =[['2016-6-22','4471.61','4576.49','4264.77','4577.94'],['2016/6/21','4471.61','4576.49','4264.77','4577.94'],['2016/6/20','4689.93','4478.36','4476.5','4744.08'],['2016/6/17','4942.52','4785.36','4780.87','4966.77'],['2016/6/16','4890.55','4967.9','4767.22','4983.66'],['2016/6/15','5004.41','4887.43','4842.1','5029.68'],['2016/6/14','5174.42','5062.99','5048.74','5176.79'],['2016/6/13','5143.34','5166.35','5103.4','5178.19'],['2016/6/10','5101.44','5121.59','5050.76','5122.46'],['2016/6/9','5049.2','5106.04','5001.49','5164.16'],['2016/6/8','5145.98','5113.53','5042.96','5147.45'],['2016/6/7','5045.69','5131.88','4997.48','5146.95'],['2016/6/6','5016.09','5023.1','4898.07','5051.63'],['2016/6/3','4912.95','4947.1','4647.41','4947.96'],['2016/6/2','4924.38','4909.98','4822.44','4942.06'],['2016/6/1','4844.7','4910.53','4797.55','4911.57'],['2016/5/31','4633.1','4828.74','4615.23','4829.5'],['2016/5/27','4603.47','4611.74','4431.56','4698.19'],['2016/5/26','4943.74','4620.27','4614.24','4986.5'],['2016/5/25','4932.85','4941.71','4857.06','4958.16'],['2016/5/24','4854.85','4910.9','4779.08','4911.69'],['2016/5/23','4660.08','4813.8','4656.83','4814.67'],['2016/5/20','4584.98','4657.6','4562.99','4658.27'],['2016/5/19','4456.44','4529.42','4438.26','4530.48'],['2016/5/18','4434.98','4446.29','4432.28','4520.54'],['2016/5/17','4285.78','4417.55','4285.78','4418.4'],['2016/5/16','4277.9','4283.49','4260.51','4324.83'],['2016/5/13','4366.82','4308.69','4278.55','4366.82'],['2016/5/12','4372.82','4378.31','4329.04','4397.75'],['2016/5/11','4402.38','4375.76','4342.48','4415.63'],['2016/5/10','4342.37','4401.22','4317.98','4402.31'],['2016/5/9','4231.27','4333.58','4187.82','4334.88'],['2016/5/6','4152.98','4205.92','4099.04','4206.86'],['2016/5/5','4197.9','4112.21','4108.01','4213.76'],['2016/5/4','4311.64','4229.27','4187.37','4376.35'],['2016/5/3','4479.85','4298.71','4282.24','4488.87'],['2016/5/2','4441.34','4480.46','4387.43','4487.57'],['2016/4/29','4483.01','4441.65','4441.05','4507.34'],['2016/4/28','4446.12','4476.62','4398.64','4499.94'],['2016/4/26','4527.63','4476.21','4432.9','4572.39'],['2016/4/26','4441.93','4527.4','4441.93','4529.73'],['2016/4/25','4355.95','4393.69','4318.12','4416.38'],['2016/4/22','4414.48','4414.51','4358.84','4444.41'],['2016/4/21','4304.6','4398.49','4297.95','4400.19'],['2016/4/20','4212.19','4293.62','4188.57','4294.38'],['2016/4/19','4301.35','4217.08','4190.68','4356'],['2016/4/18','4254.72','4287.3','4238.91','4317.22'],['2016/4/15','4055.92','4194.82','4031.24','4195.31'],['2016/4/14','4135.65','4084.16','4069.01','4175.49'],['2016/4/13','4125.78','4135.56','4091.26','4168.35'],['2016/4/12','4072.72','4121.71','4057.29','4128.07'],['2016/4/11','3947.49','4034.31','3929.32','4040.35'],['2016/4/8','4006.13','3957.53','3900.03','4016.4'],['2016/4/7','3976.53','3994.81','3903.65','4000.22'],['2016/4/6','3899.42','3961.38','3891.73','3961.67'],['2016/4/5','3803.38','3863.93','3792.21','3864.41'],['2016/4/4','3827.69','3825.78','3775.89','3835.45'],['2016/4/1','3748.34','3810.29','3742.21','3817.08'],['2016/3/31','3822.99','3747.9','3737.04','3835.57'],['2016-3-30','3822.99','3747.9','3737.04','3835.57'],['2016-3-29','3710.61','3786.57','3710.61','3795.94']];
    data1.reverse();

    var data2 =['86160000','79330000','102600000','104890000','85230000','115230000','99410000','90120000','79990000', '107100000','81020000','91710000','84510000','118160000','89390000','89820000','100210000','102720000','134120000', '83770000','92570000','109090000','100920000','136670000','80100000','97060000','95020000','81530000','80020000','85590000','75790000','87390000','88560000','86640000','88440000','103260000','79120000','95530000','111990000','87790000','86480000','79180000','68940000','73190000','147390000','78530000','75560000','82270000','71870000','78750000','71260000','69690000','90540000','101690000','93740000','94130000','91950000','248680000','99380000','85130000','89440000'];
    var data3 = ['2016-03-29', '2016-03-30', '2016-03-31', '2016-04-01', '2016-04-04', '2016-04-05', '2016-04-06', '2016-04-07', '2016-04-08', '2016-04-11', '2016-04-12', '2016-04-13', '2016-04-14', '2016-04-15', '2016-04-18', '2016-04-19', '2016-04-20', '2016-04-21', '2016-04-22', '2016-04-25', '2016-04-26', '2016-04-27', '2016-04-28', '2016-04-29', '2016-05-02', '2016-05-03', '2016-05-04', '2016-05-05', '2016-05-06', '2016-05-09', '2016-05-10', '2016-05-11', '2016-05-12', '2016-05-13', '2016-05-16', '2016-05-17', '2016-05-18', '2016-05-19', '2016-05-20', '2016-05-23', '2016-05-24', '2016-05-25', '2016-05-26', '2016-05-27', '2016-05-31', '2016-06-01', '2016-06-02', '2016-06-03', '2016-06-06', '2016-06-07', '2016-06-08', '2016-06-09', '2016-06-10', '2016-06-13', '2016-06-14', '2016-06-15', '2016-06-16', '2016-06-17', '2016-06-20', '2016-06-21', '2016-06-22'];

    createCandlestickChart('candlestick_chart',data1,data2);
    <%--var candlestickChart = createCandlestickChart("candlestick_chart",${candlestickData},${volumeData});--%>


</script>
</body>
</html>