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

    <style rel="stylesheet" type="text/css">

        body {
            background-color: #f9f9f9;
        }

        header {
            width: 100%;
            height: 500px;
            background-color: #444444;
            overflow: hidden;
        }

        .nav-wrapper {
            background-color: transparent;
            border-color: transparent;
        }

        .nav-wrapper .container {
            margin-top: 20px;
        }

        .media-list {
            margin: 0px 100px;
        }

        .media {
            margin-top: 0px;
            padding: 100px;
        }

        .modal-dialog {
            width: 400px;
        }

        #loginLabel {
            margin-left: 22px;
        }

        .modal-body {
            margin-top: 30px;
        }

        .modal-body .form-group {
            margin-bottom: 25px;
        }

        #rem-password {
            padding-right: 66px;
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
                    <img alt="Brand" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">首页</a></li>
                <li><a href="#">大盘详情</a></li>
                <li><a href="#">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <li><a href="#" data-toggle="modal" data-target="#login">登录</a></li>
                <li><a href="#">注册</a></li>
            </ul>
        </div><!-- /.container-fluid -->
    </nav>
</header>
<div class="content">
    <div class="container-fluid">
        <ul class="media-list">
            <li class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" src=""
                         alt="媒体对象">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">媒体标题</h4>
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                </div>
            </li>
            <li class="media">
                <a class="pull-right" href="#">
                    <img class="media-object" src=""
                         alt="媒体对象">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">媒体标题</h4>
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                </div>
            </li>
            <li class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" src=""
                         alt="媒体对象">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">媒体标题</h4>
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                </div>
            </li>
        </ul>
    </div>
</div>
<footer>

</footer>

<!-- 登录模态框（Modal） -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="loginLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="username">用户名:</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="username" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="password">密码:</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="checkbox text-right">
                        <label id="rem-password">
                            <input type="checkbox">记住密码
                        </label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="login-btn-group">
                    <button type="button" class="btn btn-primary" onclick="login()">登录</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>

                <a href="/test2">test2</a><br>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script type="text/javascript">
    function login() {
        alert("大风过后");
        var username = $("#username").val();
        var password = $("#password").val();

        $.post("/view/template.html", {
                name: "菜鸟教程",
                url: "http://www.runoob.com"
            },
            function (data, status) {

                alert("数据: \n" + data + "\n状态: " + status);
            });
//        var obj = $.ajax({
//            type: "post",
//            url: "../template.html",
//            async: false,
//            data: {
//                "username": username,
//                "password": password,
//            },
//
//            success: function (result) {
////                if (result == true) {
////                    window.location.href = "welcome?id=" + username;
////                    alert(obj.responseText);
////                } else {
////                    alert("qwertyuioiuytrewertyui");
////                }
//                alert("qwertyuiop");
//            },
//            error: function(){
//                alert("错误");
//            }
//        })
//        ;
    }
</script>
</body>
</html>