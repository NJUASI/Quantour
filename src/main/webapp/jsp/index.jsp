<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/startLoader.css" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-default nav-wrapper navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand brand" href="#">
                    <img alt="Brand" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">首页</a></li>
                <li><a href="/stocks">大盘详情</a></li>
                <li><a href="/trace_back_home">量化社区</a></li>
                <li><a href="#">帮助</a></li>
                <c:choose>
                    <c:when test="${sessionScope.user!=null}">
                        <li><a href="/stocks">用戶管理</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#" data-toggle="modal" data-target="#login">登录</a></li>
                        <li><a href="#" data-toggle="modal" data-target="#register">注册</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div><!-- /.container-fluid -->
    </nav>
    <div class="banner-holder">
        <div class="banner-image-holder">
            <img alt="Quantour" src="../img/banner.jpg" draggable="false">
        </div>
        <div class="jumbotron banner-desc">
            <div class="container text-center">
                <h1>Quantour,您的私人量化平台</h1>
                <br/>
                <br/>
                <a class="btn btn-primary btn-lg" role="button">
                    学习更多</a>
            </div>
        </div>
    </div>
</header>
<div class="content">
    <div class="container-fluid">
        <ul class="media-list features">
            <li class="media feature-wrapper-odd">
                <a class="media-left">
                    <img class="media-object" src="../img/img01.png" alt="媒体对象">
                </a>
                <div class="media-body desc">
                    <h4 class="media-heading">媒体标题</h4>
                    <p>高质量的海量金融数据支撑，轻松实现大数据时代的交易策略</p>
                    <div class="detail">
                        <ul>
                            <li> 依托通联数据团队，免费提供股票、基金、债券、研报、宏观、资讯、社交、电商等全量数据；</li>
                            <li> 专业团队负责数据的收集、清洗、加工及存储，来源权威，数据精准可靠，并定期推出特色数据；</li>
                            <li> 通过api接口可直接在平台调用，免费提供给优矿用户使用；</li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="media feature-wrapper-mean">
                <a class="media-right pull-right">
                    <img class="media-object" src="../img/img01.png" alt="媒体对象">
                </a>
                <div class="media-body desc">
                    <h4 class="media-heading">媒体标题</h4>
                    <p>高质量的海量金融数据支撑，轻松实现大数据时代的交易策略</p>
                    <div class="detail">
                        <ul>
                            <li> 依托通联数据团队，免费提供股票、基金、债券、研报、宏观、资讯、社交、电商等全量数据；</li>
                            <li> 专业团队负责数据的收集、清洗、加工及存储，来源权威，数据精准可靠，并定期推出特色数据；</li>
                            <li> 通过api接口可直接在平台调用，免费提供给优矿用户使用；</li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="media feature-wrapper-odd">
                <a class="media-left">
                    <img class="media-object" src="../img/img01.png" alt="媒体对象">
                </a>
                <div class="media-body desc">
                    <h4 class="media-heading">媒体标题</h4>
                    <p>高质量的海量金融数据支撑，轻松实现大数据时代的交易策略</p>
                    <div class="detail">
                        <ul>
                            <li> 依托通联数据团队，免费提供股票、基金、债券、研报、宏观、资讯、社交、电商等全量数据；</li>
                            <li> 专业团队负责数据的收集、清洗、加工及存储，来源权威，数据精准可靠，并定期推出特色数据；</li>
                            <li> 通过api接口可直接在平台调用，免费提供给优矿用户使用；</li>
                        </ul>
                    </div>
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
                        <label class="col-md-3 control-label" for="login_username">用户名：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="login_username" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="login_password">密码：</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="login_password" placeholder="请输入密码">
                        </div>
                    </div>
                    <p class="col-md-offset-3" id="errorMessageField"></p>
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
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="registerLabel">注册</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="reg_username">用户名：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="reg_username" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="reg_password">密码：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="reg_password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="reg_password2" style="padding-left: 0">确认密码：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="reg_password2" placeholder="请再次输入密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="login-btn-group">
                    <button type="button" class="btn btn-primary" onclick="register()">注册</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/logIn.js"></script>
<script src="../js/startLoaded.js"></script>
</body>
</html>