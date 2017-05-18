<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/15
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->


        <!-- Bootstrap -->
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">

        <link href="../css/index.css" rel="stylesheet">
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
                    <li><a href="/trace_back_home">量化社区</a></li>
                    <li><a href="#">帮助</a></li>
                    <c:choose>
                        <c:when test="${sessionScope.user!=null}">
                            <li><a href="/welcome">用户管理</a></li>
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
    <div class="form-group form">
        <div class="row inputBlock">
            <div class="col-md-2 col-md-offset-2">
                <label class="control-label" for="stock1">股票1：</label>
                <input type='text' id="stock1" class="form-control" placeholder="输入股票1名称/代码/拼音"/>
            </div>

            <div class="col-md-2 ">
                <label>股票2：</label>
                <input type='text' id="stock2" class="form-control" placeholder="输入股票2名称/代码/拼音"/>
            </div>

            <div class="col-md-2">

                <label>开始日期：</label>
                <!--指定 date标记-->
                <div class='input-group date' id="compare_startDate">
                    <input type='text' class="form-control form_datetime"/>
                    <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                </div>
            </div>
            <div class="col-md-2">
                <label>结束日期：</label>
                <!--指定 date标记-->
                <div class='input-group date' id="compare_endDate">
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
            <button id="compare-btn" type="button" class="btn btn-primary" onclick="compare()"/>
            开始对比</button>
        </div>

    </div>
    <div id="analysePanel" style="display: none">
        <div class="row">
            <div class="col-md-4 col-md-offset-4 table-responsive">
                <table class="table table-hover table-condensed">
                    <caption class="text-center"><h4>股票比较详情</h4></caption>
                    <thead>
                    <tr>
                        <th>股票名</th>
                        <th>最大值</th>
                        <th>最小值</th>
                        <th>涨跌幅</th>
                        <th>对数收益率方差</th>
                    </tr>
                    </thead>
                    <tbody id="compareChart">

                    </tbody>
                </table>
            </div>
        </div>
    </div>

        <div class="row col-md-8 col-md-offset-2">
            <div class="row">
                <div id="closesChart" class="col-md-12" style="height:400px"></div>
            </div>
            <div class="row">
                <div id="logarithmicYieldChart" class="col-md-12" style="height:400px"></div>
            </div>
        </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/jquery.validate.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.js"></script>

    <script src="../js/stockComparision.js"></script>
    <script src="../js/bootstrap-select.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script src="../js/chart.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {

            var today = new Date();

            var startTime = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + (today.getDate() - 1);
            var endTime = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
            $("#compare_startDate>input").attr('value', startTime);
            $("#compare_endDate>input").attr('value', endTime);


            $("#compare_startDate").datetimepicker({
                format: 'yyyy-mm-dd',
                minView: 'month',
                language: 'zh-CN',
                autoclose: true,
                startDate: new Date(2005 - 04 - 03),
                endDate: new Date()
            }).on("click", function () {
                $("#compare_startDate").datetimepicker('setEndDate', $("#compare_endDate>input").val())
            });
            $("#compare_endDate").datetimepicker({
                format: 'yyyy-mm-dd',
                minView: 'month',
                language: 'zh-CN',
                autoclose: true,
                endDate: new Date()
            }).on("click", function () {
                $("#compare_endDate").datetimepicker("setStartDate", $("#compare_startDate>input").val())
            });

//        $("#compareChart").append("1231231");
            <%--var ttt = <%=request.getAttribute("closesData")%>;--%>
            <%--if (ttt != null) {--%>
            <%--alert("hhh");--%>
            <%--alert(${closesData});--%>

            <%--var closesChart = createLineChart("closesChart", ${closesData}, '收盘价', ${comparisionName});--%>
            <%--var logarithmicYieldChart = createLineChart("logarithmicYieldChart", ${logarithmicYieldData}, '对数收益率方差', ${comparisionName});--%>
            <%--}else {--%>
            <%--alert("kkkkk");--%>
            <%--}--%>

        });

    </script>
    </body>
    </html>

