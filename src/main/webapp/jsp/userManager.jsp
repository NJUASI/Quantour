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
    <title>Bootstrap 101 Template</title>

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

        header {
            width: 100%;
            background-color: #444444;
            overflow: hidden;
        }

        .nav-wrapper {
            background-color: transparent;
            border-color: transparent;
        }


        .nav-wrapper .container {
            margin-top: 10px;
        }

    </style>

</head>
<body>

<header>
    <nav class="navbar navbar-default nav-wrapper">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand brand" href="#">
                    <!-- TODO -->
                    <img alt="Brand" src="../img/order.jpg">
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
    <div class="col-lg-8 col-lg-offset-2">
        <div class="row">
            <div class="col-lg-4"><img src="../img/sad.png" /></div>
            <div class="col-lg-8" id="modify">
                <div>
                    <form>
                         <table>
                             <div class="form-group">
                                 <label for="userID">账户：</label>
                                 <input type="text" style="width:200px;" class="form-control" id="userID" placeholder="请输入您的账户">
                             </div>
                             <div class="form-group">
                                 <label for="exampleInputPassword1" >密码：</label>
                                 <input type="password"  style="width:200px;" class="form-control" id="exampleInputPassword1" placeholder="请输入您的密码">
                             </div>
                             <div class="form-group">
                                 <label for="password2" >确认密码：</label>
                                 <input type="password"  style="width:200px;" class="form-control" id="password2" placeholder="再次输入您的密码">
                             </div>
                        </table>
                        <button type="submit" class="btn btn-default">确认修改</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
