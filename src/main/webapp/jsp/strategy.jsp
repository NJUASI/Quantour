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
    <link rel="stylesheet" href="../css/bootstrap-datepicker.css">
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

        .userBlockLeft {
            margin-top: 20px;
            margin-bottom: 10px;
        }

        #passwordModify {
            display: none;
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
                <h3 class="panel-title">
                    选股票池
                </h3>
            </div>
            <div class="panel-body">
                <div class="row">

                    <div class="col-lg-2 col-lg-offset-2 userBlockLeft">
                        <div class="radio">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios1" value="byBlock" checked>
                                按板块选
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios2" value="byChoice">
                                自选股池
                            </label>
                        </div>
                    </div>

                    <div class="col-lg-3 userBlockLeft">
                        <div class="form-group">
                            <label for="blocks" class="col-sm-4 control-label">板块：</label>
                            <div class="col-sm-8">
                                <select id="blocks" name="blocks" class="selectpicker show-tick form-control"
                                        multiple data-live-search="false" placeholder="请选择板块">
                                    <option value="1" selected>主板</option>
                                    <option value="2" selected>中小板</option>
                                    <option value="3" selected>创业板</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3 userBlockLeft">
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">ST：</label>
                        <div class="col-sm-8">
                            <select  class="selectpicker show-tick form-control" >
                                <option value="0" selected>包含ST</option>
                                <option value="1">排除ST</option>
                                <option value="2">仅有ST</option>
                            </select>
                        </div>
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
                <h3 class="panel-title">
                    回测条件
                </h3>
            </div>
            <div class="panel-body">
                <div class="row">

                    <div class="col-lg-7 col-lg-offset-1 userBlockLeft" >
                        <input type="text" id="sandbox-container" class="form-control" value="02-16-2012">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="panel panel-default col-lg-10 col-lg-offset-1 userBlock">
            <div class="panel-heading">
                <h3 class="panel-title">
                    选择策略
                </h3>
            </div>
            <div class="panel-body">
                <div class="row">

                    <div class="col-lg-7 col-lg-offset-1 userBlockLeft" id="modify">


                        <div id="passwordField">
                            <div class="form-group">
                                <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                                <label style="margin-left: 20px">*******</label>
                            </div>

                            <button type="button" id="modifyBT" class="btn btn-primary">修改密码</button>
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


<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-datepicker.js"></script>

<script type="text/javascript">

    $('#sandbox-container).datepicker({
        language: "zh-CN",
        orientation: "top auto",
        daysOfWeekDisabled: "0,4,6",
        daysOfWeekHighlighted: "1,2,3,4,5"
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
