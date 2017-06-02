<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/5/15
  Time: 19:03
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
    <div class="form-group form" style="margin-top: 80px;">
        <div class="row inputBlock">
            <div class="col-md-2 col-md-offset-2"  >
                <label class="control-label" for="search-input1">股票1：</label>
                <input type='text' id="search-input1" style="position: relative" class="form-control" placeholder="输入股票1名称/代码/拼音"/>
                <div class="searchResults1  pre-scrollable"
                     style="position: absolute;display: none;width: 300px;max-height: 200px; background-color: whitesmoke;z-index: 20">
                    <table class="table table-hover table-bordered search-table">
                        <thead class="search-table-head">
                        <tr>
                            <th width="60px">代码</th>
                            <th width="70px">名称</th>
                            <th>简称</th>
                            <th>类型</th>
                        </tr>
                        </thead>
                        <tbody id="search-body1">
                        <tr class="colomnsOfTable1">
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>


            <div class="col-md-2 ">
                <label class="control-label" for="search-input2">股票2：</label>
                <input type='text' id="search-input2" style="position: relative" class="form-control" placeholder="输入股票2名称/代码/拼音"/>
                <div class="searchResults2  pre-scrollable"
                     style="position: absolute;display: none;width: 300px;max-height: 200px; background-color: whitesmoke;z-index: 21">
                    <table class="table table-hover table-bordered search-table">
                        <thead class="search-table-head">
                        <tr>
                            <th width="60px">代码</th>
                            <th width="70px">名称</th>
                            <th>简称</th>
                            <th>类型</th>
                        </tr>
                        </thead>
                        <tbody id="search-body2">
                        <tr class="colomnsOfTable2">
                        </tr>
                        </tbody>
                    </table>
                </div>
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
                                <input type="password" class="form-control" id="reg_password" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="reg_password2" style="padding-left: 0">确认密码：</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" id="reg_password2" placeholder="请再次输入密码">
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
    <script src="../js/logIn.js"></script>

    <script type="text/javascript">

        $(document).ready(function () {

            var today = new Date();

            var today = new Date();
            var yesterday=new Date();
            yesterday.setTime(today.getTime()-24*60*60*1000);

            var endTime = today.getFullYear() + "-";
            var startTime = yesterday.getFullYear()+"-";

            var month=today.getMonth() + 1;
            var dayOfMonth=today.getDate();
            if( month<10){
                endTime+="0"+month;
            }else{
                endTime+=month;
            }
            if(dayOfMonth<10){
                endTime+="-0"+dayOfMonth;
            }else{
                endTime+="-"+dayOfMonth;
            }

            month=yesterday.getMonth() + 1;
            dayOfMonth=yesterday.getDate();
            if( month<10){
                startTime+="0"+month;
            }else{
                startTime+=month;
            }
            if(dayOfMonth<10){
                startTime+="-0"+dayOfMonth;
            }else{
                startTime+="-"+dayOfMonth;
            }
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

            $('#search-input1').bind('input propertychange', function () {
                $("#search-body1").show();
                var key = $('#search-input1').val();
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
                        if(len>10){
                            len=10;
                        }
                        $("#search-body1").empty();
                        for (var i = 0; i < len; i++) {
                            $("#search-body1").append("<tr class='colomnsOfTable1' style='cursor: default'><td>" + obj[i]["searchID"]["code"] + "</td><td style='font-size: 14px;'>&nbsp;" + obj[i]["searchID"]["name"] + "</td>" +
                                "<td>" + obj[i]["firstLetters"] + "</td><td>" + obj[i]["searchID"]["market"] + "</td></tr>");
                        }

                        $("#search-body1").find(".colomnsOfTable1").click(function() {
                            var num=($(".colomnsOfTable1").index($(this)));
                            var code=$("#search-body1").find(".colomnsOfTable1").eq(num).find("td").eq(0).html();

                            $("#search-input1").val(code);
                            $(".searchResults1").hide();
                        });

                        $("#search-body1").find("tr").hover(function(){
                            $(this).css("background-color","#A3B1D1");
                        },function(){
                            $(this).css("background-color","#F5F5F5");
                        });

                        $(".searchResults1").show();
                    }
                })
            });

            $('#search-input2').focus(function(){
                $(".searchResults1").hide();
            })
            $('#compare_startDate').focus(function(){
                $(".searchResults2").hide();
            })

            $('#search-input2').bind('input propertychange', function () {
                $("#search-body2").show();
                var key = $('#search-input2').val();
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
                        if(len>10){
                            len=10;
                        }
                        $("#search-body2").empty();
                        for (var i = 0; i < len; i++) {
                            $("#search-body2").append("<tr class='colomnsOfTable2' style='cursor: default'><td>" + obj[i]["searchID"]["code"] + "</td><td style='font-size: 14px;'>&nbsp;" + obj[i]["searchID"]["name"] + "</td>" +
                                "<td>" + obj[i]["firstLetters"] + "</td><td>" + obj[i]["searchID"]["market"] + "</td></tr>");
                        }

                        $("#search-body2").find(".colomnsOfTable2").click(function() {
                            var num=($(".colomnsOfTable2").index($(this)));
                            var code=$("#search-body2").find(".colomnsOfTable2").eq(num).find("td").eq(0).html();

                            $("#search-input2").val(code);
                            $(".searchResults2").hide();
                        });

                        $("#search-body2").find("tr").hover(function(){
                            $(this).css("background-color","#A3B1D1");
                        },function(){
                            $(this).css("background-color","#F5F5F5");
                        });

                        $(".searchResults2").show();
                    }
                })
            });
//            $("tbody").click(function () {
//
//                      var code = $(this).find("td:first").text();
//                            $("#search-input1").html(code);
//            });


            $(".searchResults1").click(function (e) {
                e.stopPropagation();
            });
            $(".searchResults2").click(function (e) {
                e.stopPropagation();
            });

            $(document).click(function () {
                $(".searchResults1").hide();

                $(".searchResults2").hide();
            });
        });

    </script>
    </body>
    </html>

