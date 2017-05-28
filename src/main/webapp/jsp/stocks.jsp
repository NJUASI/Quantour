<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cuihua
  Date: 2017/5/13
  Time: 下午7:56
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
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="../css/stocks.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <title>股票市场</title>
</head>
<header>
    <nav class="navbar navbar-default nav-wrapper navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand brand" href="#">
                    <img alt="Quantour" src="">
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">首页</a></li>
                <li><a href="/stocks">大盘详情</a></li>
                <li><a href="/trace_back">量化社区</a></li>
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
</header>
<body class="loaded" style="margin-top: 60px;">
<div class="content">
    <div class="container">
        <div class="row panel_title_wrapper" style="z-index: 5;">
            <div class="col-md-offset-1 col-md-10">
                <div class="panel_title">
                    <div class="row">
                        <div class="col-md-2">
                            <ul class="nav">
                                <li class="curr text-center">沪深行情</li>
                            </ul>
                        </div>
                        <div class="col-md-3 col-md-offset-1">
                            <div class="input-group date form_date" data-date="" data-date-format="yyyy/mm/dd"
                                 data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input id="stocks_date" class="form-control" size="16" type="text"/>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                        <div class="col-md-3 col-md-offset-1">
                            <form role="form">
                                <div class="input-group" style="position: relative">
                                    <input type="text" id="search-input" class="form-control" placeholder="输入代码/简称/拼音">
                                    <span class="input-group-btn">
                                    <button class="btn btn-default form-control search-btn" type="button"
                                            onclick="getSingleStockDetail()">
                                        <span class="glyphicon glyphicon-search"> </span>
                                    </button>
                                    </span>


                                </div>
                                <div class="searchResults  pre-scrollable"
                                     style="position: absolute;display: none;max-height: 200px; background-color: whitesmoke;z-index: 20">
                                    <table class="table table-condensed table-bordered">
                                        <div class="search-table">
                                            <thead class="search-table-head">
                                            <tr>
                                                <th>代码</th>
                                                <th>名称</th>
                                                <th>简称</th>
                                                <th>类型</th>
                                            </tr>
                                            </thead>
                                            <tbody id="search-body">

                                            </tbody>
                                        </div>
                                    </table>
                                </div>
                            </form>
                        </div>

                        <div class="col-md-1">
                            <button type="button" class="btn btn-primary" onclick="directToCompare()">对比</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row markets_wrapper " style="z-index:3">
            <div class="col-md-offset-1 col-md-10">
                <ul class="market">
                    <li class="each_market" id="stocks_shangzheng">
                        <h4>上证指数</h4>
                        <h3>3083.51&nbsp;&nbsp;
                            <small>+22.01&nbsp;&nbsp;</small>
                            <small>+0.71%</small>
                        </h3>
                        <ul class="market-data-extra">
                            <li><span>最高:<span class="market_high">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最低:<span
                                    class="market_low">3051.87</span></span></li>
                            <li><span>涨停:<span class="market_limit_up">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跌停:<span
                                    class="market_limit_down">3051.87</span></span></li>
                            <li><span>成交量:<span class="volume">3090</span></span></li>
                        </ul>
                    </li>
                    <li class="each_market" id="stocks_shenzheng">
                        <h4>深证指数</h4>
                        <h3>3083.51&nbsp;&nbsp;
                            <small>+22.01&nbsp;&nbsp;</small>
                            <small>+0.71%</small>
                        </h3>
                        <ul class="market-data-extra">
                            <li><span>最高:<span class="market_high">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最低:<span
                                    class="market_low">3051.87</span></span></li>
                            <li><span>涨停:<span class="market_limit_up">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跌停:<span
                                    class="market_limit_down">3051.87</span></span></li>
                            <li><span>成交量:<span class="volume">3090</span></span></li>
                        </ul>
                    </li>
                    <li class="each_market" id="stocks_chuangzhi">
                        <h4>创业指数</h4>
                        <h3>3083.51&nbsp;&nbsp;
                            <small>+22.01&nbsp;&nbsp;</small>
                            <small>+0.71%</small>
                        </h3>
                        <ul class="market-data-extra">
                            <li><span>最高:<span class="market_high">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最低:<span
                                    class="market_low">3051.87</span></span></li>
                            <li><span>涨停:<span class="market_limit_up">3090</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跌停:<span
                                    class="market_limit_down">3051.87</span></span></li>
                            <li><span>成交量:<span class="volume">3090</span></span></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row" style="z-index:3">
            <div class="col-md-10 col-md-offset-1">
                <div class="table-responsive">
                    <table class="table table-hover table-condensed stocks-table">
                        <caption class="text-center"><h3>市场行情</h3></caption>
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
                        <tbody id="stocks_all">

                        <c:forEach items="${stock_list}" var="stock" varStatus="vs">
                            <tr>
                                <td>${stock.stockID.code}</td>
                                <td>${stock.name}</td>
                                <td>${stock.open}</td>
                                <td>${stock.close}</td>
                                <td class="stock_high">${stock.high}</td>
                                <td class="stock_low">${stock.low}</td>
                                <td>${stock.preClose}</td>
                                <td>${stock.volume}</td>
                                <td>${stock.transactionAmount}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="pagination-wrapper text-right">
                    <ul class="pagination">
                        <li><a href="#">&laquo;</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><span>&middot;&middot;&middot;</span></li>
                        <li><a href="#">30</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


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
                            <input type="checkbox" checked>记住密码
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

<footer>
</footer>

<script src="../js/chart.js"></script>
<script src="../js/echarts.min.js"></script>
<script src="../js/stocks.js"></script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../js/logIn.js"></script>
<script type="text/javascript">
    $(document).ready(
        function () {
            var date = ${date.year} +"-" + ${date.monthValue} +"-" + ${date.dayOfMonth};
            $(".form_date > input").attr('value', date);


            $('#search-input').bind('input propertychange', function () {

                var key = $('#search-input').val();
//                alert(key);
                $.ajax({
                    type: "get",
                    async: true,
                    url: "/stocks/search?key=" + key,

                    success: function (result) {
//                        alert(result);
                        if (result == "-1") {
                            // 调取失败处理

                        }

                        var obj = eval("(" + result + ")");
                        var len = obj.length;
                        $("#search-body").empty();
                        for (var i = 0; i < 10; i++) {
                            $("#search-body").append("<tr>");
                            $("#search-body").append("<td>" + obj[i]["searchID"]["code"] + "</td>");
                            $("#search-body").append("<td>" + obj[i]["searchID"]["name"] + "</td>");
                            $("#search-body").append("<td>" + obj[i]["firstLetters"] + "</td>");
                            $("#search-body").append("<td>" + obj[i]["searchID"]["market"] + "</td>");
                            $("#search-body").append("</tr>");
                        }
                        $("#search-body tr").on("click", function () {
                            var code = $(this).find("td:first").text();
                            $("#search-input").html(code);
                        });
                        $(".searchResults").show();
                    }
                })
            });


            $(".searchResults").click(function (e) {
                e.stopPropagation();
            });
            $(document).click(function () {
                $(".searchResults").hide();
            });

            $(".searchResults")
        }
    );

    $(".form_date").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,//日视图
        minView: 2,//日期时间选择器所能够提供的最精确的时间选择视图0
        pickerPosition: "bottom-left",
        startDate: new Date(2012 - 01 - 01),
        endDate: new Date(),
        daysOfWeekDisabled: [0, 6]
    });

    $('.form_date')
        .datetimepicker().on("changeDate", function () {
//        alert($(".form_date > input").val());
        $("body").removeClass("loaded");

        $.ajax({
            type: "post",
            async: true,
            url: "/stocks",
            data: {
                "date": $(".form_date > input").val()
                // TODO 高源 page信息传输，排序条件
            },

            success: function (result) {
//                alert(result);
                $("body").addClass("loaded");
                var array = result.split(";");

                if (array[0] == "1") {
                    // js修改jsp中数据
                    // TODO 高源 这里有个bug 就是我点了日期，修改页面之后，他自己又跳回原来的默认日期去了

                    var stock_list = eval("(" + array[1] + ")");
                    var nowDate = eval("(" + array[2] + ")");


                    $("#stocks_all").empty();
                    for (var i = 0; i < stock_list.length; i++) {
                        $("#stocks_all").append("<tr>");
                        $("#stocks_all").append("<td>" + stock_list[i]["stockID"]["code"] + "</td>");
                        $("#stocks_all").append("<td>" + stock_list[i]["name"] + "</td>");
                        $("#stocks_all").append("<td>" + stock_list[i]["open"] + "</td>");
                        $("#stocks_all").append("<td>" + stock_list[i]["close"] + "</td>");
                        $("#stocks_all").append("<td class=\"stock_high\">" + stock_list[i]["high"] + "</td>");
                        $("#stocks_all").append("<td class=\"stock_low\">" + stock_list[i]["low"] + "</td>");
                        $("#stocks_all").append("<td>" + stock_list[i]["preClose"] + "</td>");
                        $("#stocks_all").append("<td>" + stock_list[i]["volume"] + "</td>");
                        $("#stocks_all").append("<td>" + stock_list[i]["transactionAmount"] + "</td>");
                        $("#stocks_all").append("</tr>");
                    }


                    window.location.href = "/stocks";
                } else if (array[0] == "-1") {
                    // 提示错误信息
                    alert(array[1]);
                } else {
                    alert("未知错误类型orz");
                }
            },
            error: function (result) {
//                alert(JSON.stringify(result));
                alert("错误" + result);
            }
        });
    });
</script>
</body>
</html>