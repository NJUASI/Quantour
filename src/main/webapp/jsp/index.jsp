<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Quantour</title>

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
                    <img alt="Quantour" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a id="homePage" href="/">首页</a></li>
                <li><a id="stocks" onclick="openStock()" style="cursor: pointer">大盘详情</a></li>
                <li class="dropdown">
                    <a href="##" class="dropdown-toggle" data-toggle="dropdown">量化社区<span class="caret"></span></a>
                    <ul class="dropdown-menu" style="left:15px;max-width: 100px">
                        <li><a href="/trace_back">创建策略</a></li>
                        <li><a href="/strategy">使用策略</a></li>
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
    <div class="banner-holder">
        <div class="banner-image-holder">
            <img alt="Quantour" src="../img/banner.jpg" draggable="false">
        </div>
        <div class="jumbotron banner-desc">
            <div class="container text-center">
                <h1>Quantour, 您的私人量化平台</h1>
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
                    <h4 class="media-heading">股票搜索</h4>
                    <p>高质量的海量金融数据支撑，轻松找到股市信息</p>
                    <div class="detail">
                        <ul>
                            <li> 实时更新当天全量数据；</li>
                            <li> 快速搜索；</li>
                            <li> 通过api接口可直接在平台调用，免费提供给用户使用；</li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="media feature-wrapper-mean">
                <a class="media-right pull-right">
                    <img class="media-object" src="../img/img02.png" alt="媒体对象">
                </a>
                <div class="media-body desc">
                    <h4 class="media-heading">实时掌控市场行情</h4>
                    <p>高质量的海量金融数据支撑，快速获取股票波动</p>
                    <div class="detail">
                        <ul>
                            <li> 依托通联数据团队，免费提供股票、基金、债券、研报、宏观、资讯、社交、电商等全量数据；</li>
                            <li> 专业团队负责数据的收集、清洗、加工及存储，来源权威，数据精准可靠，并定期推出特色数据；</li>
                            <li> 通过api接口可直接在平台调用，免费提供给用户使用；</li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="media feature-wrapper-odd">
                <a class="media-left">
                    <img class="media-object" src="../img/img03.png" alt="媒体对象">
                </a>
                <div class="media-body desc">
                    <h4 class="media-heading">优质回测系统</h4>
                    <p>高质量的海量金融数据支撑，轻松实现大数据时代的交易策略</p>
                    <div class="detail">
                        <ul>
                            <li> 依托通联数据团队，免费提供股票、基金、债券、研报、宏观、资讯、社交、电商等全量数据；</li>
                            <li> 专业团队负责数据的收集、清洗、加工及存储，来源权威，数据精准可靠，并定期推出特色数据；</li>
                            <li> 通过api接口可直接在平台调用，免费提供给用户使用；</li>
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
<%@ include file="logIn.jsp" %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/jquery.validate.js"></script>;
<script src="../js/logIn.js"></script>
<script src="../js/startLoaded.js"></script>
<script type="text/javascript">
    function openStock() {
        $("body").removeClass('loaded');
        window.location.href="/stocks"
    }
    $("#homePage").addClass("act");

</script>
</body>
</html>