<%@ page import="com.edu.nju.asi.model.User" %><%--
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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>用户管理</title>

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
            margin-top: 50px;
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
                <li><a href="#">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <li><a href="#" style="color: #4cae4c">用户</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>
<div class="row">
    <div class="panel panel-default col-md-8 col-md-offset-2 userBlock">
        <div class="panel-heading">
            <h3 class="panel_title">
                登录信息
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="panel-body col-md-3 col-md-offset-1"><img src="../img/sad.png" class="picture"/></div>
                <div class="col-md-7 col-md-offset-1 userBlockLeft" id="modify">

                    <div class="form-group">
                        <label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：</label>
                        <label id="userID" style="margin-left: 20px">12345</label>
                    </div>

                    <div id="passwordField">
                        <div class="form-group">
                            <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                            <label style="margin-left: 20px">*******</label>
                        </div>

                        <button type="button" id="modifyBT" class="btn btn-primary" onclick="modify()">修改密码</button>
                    </div>

                    <div id="passwordModify">
                        <form id="modifyForm">

                            <div class="form-group form-inline">
                                <label for="password1">输入密码：</label>
                                <input type="password" style="width:200px;" class="form-control" id="password1"
                                       name="password1" placeholder="请输入您的密码">
                            </div>
                            <div class="form-group form-inline">
                                <label for="password2">确认密码：</label>
                                <input type="password" style="width:200px;" class="form-control" id="password2"
                                       name="password2" placeholder="再次输入您的密码">
                            </div>

                            <input type="submit" class="btn btn-info" onclick="modify()"
                                   style="margin-top: 15px;margin-left: 80px;" value="确认修改"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="panel panel-default col-md-8 col-md-offset-2 messageBlock">
        <div class="panel-heading">
            <h3 class="panel_title">
                基本信息
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <form id="normalForm">
                    <div class="col-md-4 col-md-offset-2">
                        <div class="form-group form-inline">
                            <label for="nickName" class="">昵称：</label>
                            <input type="text" class="form-control" id="nickName"
                                   name="nickName" placeholder="请输入昵称">
                        </div>

                        <div class="form-group form-inline">
                            <label for="nickName" class="">QQ ：</label>
                            <input type="text" class="form-control" id="qq"
                                   name="qq" placeholder="请输入QQ号码">
                        </div>

                    </div>
                    <div class="col-md-5">

                        <div>


                            <div class="form-group form-inline">
                                <label for="phone">手机号码：</label>
                                <input type="text" style="width:200px;" class="form-control" id="phone"
                                       name="phone" placeholder="请输入手机号码">
                            </div>

                            <div class="form-group form-inline">
                                <label for="email">电子邮箱：</label>
                                <input type="text" style="width:200px;" class="form-control" id="email"
                                       name="email" placeholder="请输入电子邮箱">
                            </div>

                            <input type="submit" class="btn btn-info"
                                   style="margin-top: 15px;margin-left: -40px;" value="保存信息"/>
                            </button>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="panel panel-default col-md-8 col-md-offset-2 userBlock">
        <div class="panel-heading">
            <h3 class="panel_title">
                自选股票
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="table-responsive col-md-12">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>代码</th>
                            <th>名称</th>
                            <th>开盘价</th>
                            <th>收盘价</th>
                            <th>最高价</th>
                            <th>最低价</th>
                            <th>昨收</th>
                            <th>交易量</th>
                            <th>交易额</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%--<c:forEach items="${stockList}" var="stock" varStatus="vs">--%>
                                <%--<tr>--%>
                                    <%--<td>${stock.stockID.code}</td>--%>
                                    <%--<td>${stock.name}</td>--%>
                                    <%--<td>${stock.open}</td>--%>
                                    <%--<td>${stock.close}</td>--%>
                                    <%--<td class="stock_high">${stock.high}</td>--%>
                                    <%--<td class="stock_low">${stock.low}</td>--%>
                                    <%--<td>${stock.preClose}</td>--%>
                                    <%--<td>${stock.volume}</td>--%>
                                    <%--<td>${stock.transactionAmount}</td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/jquery.validate.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.js"></script>

    <script type="text/javascript">



        $(document).ready(function () {
            var user="<%= ((User)session.getAttribute("user")).getUserName()%>";
            $("#userID").html(user);
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
