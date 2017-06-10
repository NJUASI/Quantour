/**
 * Created by cuihua on 2017/5/14.
 */
/**
 * 获取股票策略的条件
 */
function getTraceBackCriteria() {

    // alert($("#startDate").val() + "\n" + $("#endDate").val() + "\n" + $("#formativePeriod").val() + "\n" + $("#holdingPeriod").val()
    //     + "\n" + $("#stType").val() + "\n" +$("#blockTypes").val() + "\n" +$("#baseStockEve").val() + "\n" + isCustomized
    //     + "\n" +$("#formativeStrategy").val() + "\n" +$("#pickStrategy").val() + "\n"+$("#rank").val());


    // 添加选股条件
    var filterConditions = new Array();
    var filterNum = 0;
    $(".quotaRow").each(function () {
        var dateOfIndicator = $(this).find(".numOfN").val();
        var separatorResult = separateIndicator($(this).find(".quotaName").html().trim());
        alert(separatorResult);

        if (dateOfIndicator == "") {
            alert("无N日");
            // 非输入框输入
            filterConditions[filterNum] = {
                "indicatorType": separatorResult[1],
                "comparatorType": $(this).find(".quotaRank").val(),
                "value": $(this).find(".quotaNum").val(),
                "formativePeriod": separatorResult[0]
            };
        } else {
            alert("有N日");
            // 输入框输入
            filterConditions[filterNum] = {
                "indicatorType": separatorResult,
                "comparatorType": $(this).find(".quotaRank").val(),
                "value": $(this).find(".quotaNum").val(),
                "formativePeriod": dateOfIndicator
            };
        }
        filterNum++;
    });

    // 添加排序条件
    var rankConditions = new Array();
    var rankNum = 0;
    $(".rankRow").each(function () {
        var dateOfIndicator = $(this).find(".numOfN").val();
        var separatorResult = separateIndicator($(this).find(".quotaName").html().trim());
        alert(separatorResult);

        if (dateOfIndicator == "") {
            alert("无N日");
            // 非输入框输入
            rankConditions[rankNum] = {
                "indicatorType": separatorResult[1],
                "rankType": $(this).find(".rankOrder").val(),
                "weight": $(this).find(".quotaWeight").val(),
                "formativePeriod": separatorResult[0]
            };
        } else {
            alert("有N日");
            // 输入框输入
            rankConditions[rankNum] = {
                "indicatorType": separatorResult,
                "rankType": $(this).find(".rankOrder").val(),
                "weight": $(this).find(".quotaWeight").val(),
                "formativePeriod": dateOfIndicator
            };
        }
        rankNum++;
    });

    // "formativePeriod": $("#formativePeriod").val();

    alert("filterConditions: " + filterConditions + "\n\n" + "rankConditions: " + rankConditions);
    var criteriaData = {
        "startDate": $("#startDate").val(),
        "endDate": $("#endDate").val(),
        "holdingPeriod": $("#holdingPeriod").val(),
        "stockPoolCriteria": {
            "stType": $("#stType").val(),
            "blockTypes": $("#blockTypes").val()
        },
        "maxHoldingNum": $("#maxHolding").val(),
        "baseStockName": $("#baseStockEve").val(),
        // "isCustomized": isCustomized,
        "filterConditions": filterConditions,
        "rankConditions": rankConditions
    };
    return criteriaData;
}

/**
 * 回测
 */
function traceback() {
    // var isCustomized = $(":radio[name='optionsRadios']:checked").val();
    $("body").removeClass("loaded");

    var jsonData = getTraceBackCriteria();
    alert(JSON.stringify(jsonData));

    $.ajax({
        type: "post",
        async: true,
        url: "/req_trace_back",
        data: {
            "criteriaData": JSON.stringify(jsonData)
        },


        success: function (result) {
            // $("#myTab").fadeIn("slow");
            // $("#chartPanel").fadeIn("slow");
            $("#coverPanel").hide();
            $("body").addClass("loaded");
            var array = result.split(";");
            if (array[0] == "1") {

                // 处理网页上要显示的表格信息
                var numberValues = eval("(" + array[1] + ")");              // List<String>
                var abReturnPeriod = eval("(" + array[2] + ")");            // ReturnPeriod
                var reReturnPeriod = eval("(" + array[3] + ")");            // ReturnPeriod
                var holdingDetails = eval("(" + array[4] + ")");            // List<HoldingDetail>
                var transferDetails = eval("(" + array[5] + ")");           // List<TransferDetail>
                // var certainFormates = eval("(" + array[5] + ")");           // List<ExcessAndWinRateDist>
                // var certainHoldings = eval("(" + array[6] + ")");           // List<ExcessAndWinRateDist>

                // 回测的数值型数据
                $("#tb_chart").empty();
                for (var i = 0; i < 3; i++) {
                    $("#tb_chart").append("<tr>");

                    switch (i) {
                        case 0:
                            $("#tb_chart").append("<td>本策略</td>");
                            break;
                        case 1:
                            $("#tb_chart").append("<td>基准股票</td>");
                            break;
                        case 2:
                            $("#tb_chart").append("<td>相对收益</td>");
                            break;
                    }

                    for (var j = 0; j < 7; j++) {
                        $("#tb_chart").append("<td>" + numberValues[i * 7 + j] + "</td>");
                    }
                    $("#tb_chart").append("</tr>");
                }
                // alert("--------------------1----------------");

                // 股票周期的对比图
                $("#tb_cycle_ab").empty();
                $("#tb_cycle_ab").append("<tr>");
                $("#tb_cycle_ab").append("<td>" + abReturnPeriod["positivePeriodsNum"] + "</td>");
                $("#tb_cycle_ab").append("<td>" + abReturnPeriod["negativePeriodNum"] + "</td>");
                $("#tb_cycle_ab").append("<td>" + abReturnPeriod["winRate"] + "</td>");
                $("#tb_cycle_ab").append("</tr>");

                $("#tb_cycle_re").empty();
                $("#tb_cycle_re").append("<tr>");
                $("#tb_cycle_re").append("<td>" + reReturnPeriod["positivePeriodsNum"] + "</td>");
                $("#tb_cycle_re").append("<td>" + reReturnPeriod["negativePeriodNum"] + "</td>");
                $("#tb_cycle_re").append("<td>" + reReturnPeriod["winRate"] + "</td>");
                $("#tb_cycle_re").append("</tr>");
                // alert("--------------------2----------------");


                // 持有周期详情
                $("#tb_detail").empty();
                for (var i = 0; i < holdingDetails.length; i++) {
                    $("#tb_detail").append("<tr>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["periodSerial"] + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["startDate"] + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["endDate"] + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["holdingNum"] + "</td>");
                    $("#tb_detail").append("<td>" + (holdingDetails[i]["strategyReturn"] * 100).toFixed(2) + "%" + "</td>");
                    $("#tb_detail").append("<td>" + (holdingDetails[i]["baseReturn"] * 100).toFixed(2) + "%" + "</td>");
                    $("#tb_detail").append("<td>" + (holdingDetails[i]["excessReturn"] * 100).toFixed(2) + "%" + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["remainInvestment"].toFixed(2) + "</td>");
                    $("#tb_detail").append("</tr>");
                }
                // alert("--------------------3----------------");

                // 卖出的股票详情
                $("#sold_stock_detail").empty();
                for (var i = 0; i < transferDetails.length; i++) {
                    $("#sold_stock_detail").append("<tr>");
                    $("#sold_stock_detail").append("<td>" + transferDetails[i]["stockName"] + "</td>");
                    $("#sold_stock_detail").append("<td>" + transferDetails[i]["stockCode"] + "</td>");
                    $("#sold_stock_detail").append("<td>" + transferDetails[i]["buyDate"] + "</td>");
                    $("#sold_stock_detail").append("<td>" + transferDetails[i]["sellDate"] + "</td>");
                    $("#sold_stock_detail").append("<td>" + transferDetails[i]["buyPrice"] + "</td>");
                    $("#sold_stock_detail").append("<td>" + transferDetails[i]["sellPrice"] + "</td>");
                    $("#sold_stock_detail").append("<td>" + (transferDetails[i]["changeRate"] * 100).toFixed(2) + "%" + "</td>");
                    $("#sold_stock_detail").append("</tr>");
                }
                // alert("--------------------4----------------");


                // // 固定形成期的赢率分析
                // $("#tb_certain_formate").empty();
                // for(var i = 0; i < certainFormates.length; i++) {
                //     $("#tb_certain_formate").append("<tr>");
                //     $("#tb_certain_formate").append("<td>" + certainFormates[i]["relativeCycle"] + "</td>");
                //     $("#tb_certain_formate").append("<td>" + (certainFormates[i]["excessRate"]*100).toFixed(2) + "%" + "</td>");
                //     $("#tb_certain_formate").append("<td>" + (certainFormates[i]["winRate"]*100).toFixed(2) + "%" + "</td>");
                //     $("#tb_certain_formate").append("</tr>");
                // }
                // // alert("--------------------5----------------");
                //
                //
                // // 固定持有期的赢率分析
                // $("#tb_certain_holding").empty();
                // for(var i = 0; i < certainHoldings.length; i++) {
                //     $("#tb_certain_holding").append("<tr>");
                //     $("#tb_certain_holding").append("<td>" + certainHoldings[i]["relativeCycle"] + "</td>");
                //     $("#tb_certain_holding").append("<td>" + (certainHoldings[i]["excessRate"]*100).toFixed(2) + "%" + "</td>");
                //     $("#tb_certain_holding").append("<td>" + (certainHoldings[i]["winRate"]*100).toFixed(2) + "%" + "</td>");
                //     $("#tb_certain_holding").append("</tr>");
                // }
                // // alert("--------------------6----------------");


                // 处理图表的信息
                var strategyData = JSON.parse(array[6]);            //List<List<String>>
                var baseData = JSON.parse(array[7]);                //List<List<String>>
                var abHistogramData = JSON.parse(array[8]);
                var reHistogramData = JSON.parse(array[9]);
                // var formateExcessData = JSON.parse(array[11]);
                // var formateWinData = JSON.parse(array[12]);
                // var holdingExcessData = JSON.parse(array[13]);
                // var holdingWinData = JSON.parse(array[14]);

                // alert(strategyData + "\n\n" + baseData + "\n\n" + abHistogramData + "\n\n" + reHistogramData + "\n\n" + formateExcessData
                //     + "\n\n" + formateExcessData + "\n\n" + holdingExcessData + "\n\n" + holdingWinData);


                var trace_back_chart = createTraceBackChart("trace_back_chart", strategyData, baseData, ['策略', '基准'], '1', '1');
                var absolute_histogram_chart = createHistogramChart("absolute_histogram_chart", abHistogramData, " ");
                var relative_histogram_chart = createHistogramChart("relative_histogram_chart", reHistogramData, " ");
                // var formates_excess_chart = createAreaChart("formates_excess_chart", formateExcessData, '胜率');
                // var formates_win_chart = createAreaChart("formates_win_chart", formateWinData, '赢率');
                // var holdings_excess_chart = createAreaChart("holdings_excess_chart", holdingExcessData, '胜率');
                // var holdings_win_chart = createAreaChart("holdings_win_chart", holdingWinData, '赢率');


            } else if (array[0] == "-1") {
                // 提示错误信息
                alert(array[1]);
            } else {
                alert("未知错误类型orz");
            }
        },

        error: function (result) {
            alert("错误" + result);
        }
    });
}


/**
 * 用户确认保存策略
 */
function ensureCreate(curUser) {
    alert("--------ENTER--------");
    var strategyID = $("#strategyName").val();
    var description = $("#strategyDescription").val();
    var isPrivate = $('input[name="radios3"]:checked').val();

    var strategyData = {
        "strategyID": strategyID,
        "date": null,
        "creater": curUser,
        "isPrivate": isPrivate,
        "content": JSON.stringify(getTraceBackCriteria()),
        "description": description,
        "traceBackInfo": null,
        "users": null
    };

    alert("--------END--------");
    alert(JSON.stringify(strategyData));

    $.ajax({
        type: "post",
        async: true,
        url: "/strategy/save",
        data: {
            "strategy": JSON.stringify(strategyData)
        },

        success: function (result) {
            // $("body").addClass("loaded");
            var array = result.split(";");

            if (array[0] == "1") {


            } else if (array[0] == "-1") {
                // 提示错误信息
                alert(array[1]);
            } else {
                alert("未知错误类型orz");
            }
        },
        error: function (result) {
            alert("错误" + result);
        }

    });


}

/**
 * 解析回测指标
 */
function separateIndicator(indicatorType) {
    // 特殊情况，优先处理
    if (indicatorType == "日均成交价") return new Array(1, convertIndicator(indicatorType));
    if (indicatorType == "前日收盘价") return new Array(1, "PRE_CLOSE");
    if (indicatorType == "前日后复权收盘价") return new Array(1, "AFTER_ADJ_PRE_CLOSE");
    if (indicatorType == "1日5日量比") return new Array(1, "VOLUME_RATIO");


    var separator = indicatorType.indexOf("日");

    if (separator == -1) {
        // 指标中不含有日字
        alert("指标中不含有日字\n" + convertIndicator(indicatorType));
        return new Array(1, convertIndicator(indicatorType));
    }
    if (separator == 0) {
        // 为 N日** 类型
        return convertIndicator(indicatorType.substr(1));
    } else {
        var reg = /^[0-9]*$/;
        if (reg.test(indicatorType.substr(0, separator))) {
            // 为 *日*** 类型
            return new Array(indicatorType.substr(0, separator), convertIndicator(indicatorType.substr(separator + 1, indicatorType.length)));
        } else {
            // 为 当日*** 类型
            return new Array(1, convertIndicator(indicatorType.substr(separator + 1, indicatorType.length)));
        }
    }
}


/**
 * 在JS中实现 IndicatorType.getEnum()方法，能够在JS中调用Java代码后修改
 */
function convertIndicator(indicatorType) {
    switch (indicatorType) {
        case "开盘价":
            return "OPEN";
        case "收盘价":
            return "CLOSE";
        case "最高价":
            return "HIGH";
        case "最低价":
            return "LOW";
        case "日均成交价":
            return "DAILY_AVE_PRICE";
        case "后复权开盘价":
            return "AFTER_ADJ_OPEN";
        case "后复权收盘价":
            return "AFTER_ADJ_CLOSE";
        case "后复权最高价":
            return "AFTER_ADJ_HIGH";
        case "后复权最低价":
            return "AFTER_ADJ_LOW";
        case "后复权均价":
            return "AFTER_ADJ_DAILY_AVE_PRICE";

        case "成交额":
        case "平均成交额":
            return "TRANSACTION_AMOUNT";
        case "成交量":
        case "平均成交量":
            return "VOLUME";

        case "涨幅":
            return "INCREASE_MARGIN";
        case "换手率":
            return "TURNOVER_RATE";
        case "股价振幅":
            return "SWING_RATE";

        case "总股本":
            return "GENERAL_CAPITAL";
        case "流通股本":
            return "NEGOTIABLE_CAPITAL";
        case "总市值":
            return "TOTAL_VALUE";
        case "流通市值":
            return "CIRCULATION_MARKET_VALUE";

        case "乖离率":
            return "BIAS";
        case "波动率":
            return "RETURN_VOLATILITY";
        case "DIFF线":
            return "MACD_DIF";
        case "DEA线":
            return "MACD_DEA";
        case "MACD柱状值":
            return "MACD_COLUMN_VAL";
        case "多空指标":
            return "BBIC";
        case "多头排列标记":
            return "MULTIPLE_ARRANGEMENT_MARK";
        case "布林上线":
            return "BOLL_UP_BANDS";
        case "布林下线":
            return "BOLL_DOWN_BANDS";
        case "平均真实波动范围":
            return "AVE_TRUE_RANGE";

        case "市盈率":
            return "PE_TTM";
        case "市净率":
            return "PB";
        case "市销率":
            return "PS_TTM";
        case "静态市盈率":
            return "S_PE_TTM";
        case "动态市盈率":
            return "D_PE_TTM";
        case "市盈率相对于盈利增长比率":
            return "PEG";
        case "每股收益":
            return "EPS";
        case "净资产收益率":
            return "ROE";
        case "资产负债率":
            return "ASSET_LIABILITY_RATIO";
    }
    alert("No Match IndicatorType");
}