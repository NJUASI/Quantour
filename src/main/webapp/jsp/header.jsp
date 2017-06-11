
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
