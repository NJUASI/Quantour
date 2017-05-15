<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/15
  Time: 19:03
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
    <title>股票比较</title>

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

        .inputBlock {
            margin-top: 50px;
            margin-bottom: 40px;
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
                <li><a href="index.jsp">首页</a></li>
                <li><a href="/stocks">大盘详情</a></li>
                <li><a href="/traceback_home">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <li><a href="#" style="color: #4cae4c">用户</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>
<div class="form-group form">
    <div class="row inputBlock">
        <div class="col-md-2 col-md-offset-2">
            <label class="control-label" for="stock1">股票1：</label>
                <input type='text' id="stock1" class="form-control" placeholder="输入股票1名称/代码/拼音"/>

        </div>

        <div class="col-md-2 ">
            <label>股票2：</label>
            <input type='text' class="form-control" placeholder="输入股票2名称/代码/拼音"/>
        </div>

        <div class="col-md-2">

            <label>开始日期：</label>
            <!--指定 date标记-->
            <div class='input-group date' id='datetimeStart'>
                <input type='text' class="form-control form_datetime"/>
                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
            </div>
        </div>
        <div class="col-md-2">
            <label>结束日期：</label>
            <!--指定 date标记-->
            <div class='input-group date' id='datetimeEnd'>
                <input type='text' class="form-control form_datetime"/>
                <span class="input-group-addon">
                       <span class="glyphicon glyphicon-calendar"></span>
                </span>

            </div>
        </div>

    </div>
</div>


<div class="row">

    <div class="col-md-2 col-md-offset-8">
        <button type="button" class="btn btn-primary" onclick=""/>开始对比</button>
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
<script type="text/javascript">

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

</script>
</body>
</html>

