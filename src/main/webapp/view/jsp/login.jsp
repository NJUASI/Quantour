<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/12
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!--reset -->
    <link href="css/normalize.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<form role="form" action="dologin.jsp" method="post">
    <div class="form-group">
        <label for="userID">账户：</label>
        <input type="text" style="width:200px;" class="form-control"   name="userID" id="userID" placeholder="请输入您的账户">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1" >密码：</label>
        <input type="password"  style="width:200px;" class="form-control"  name="password" id="exampleInputPassword1" placeholder="请输入您的密码">
    </div>
    <div class="checkbox">
        <label>
            <input type="checkbox"> 记住密码
        </label>
    </div>
    <button type="submit" class="btn btn-default">登录</button>
</form>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>