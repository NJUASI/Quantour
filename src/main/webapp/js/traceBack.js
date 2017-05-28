/**
 * Created by cuihua on 2017/5/14.
 */
function traceback() {
    var isCustomized = $(":radio[name='optionsRadios']:checked").val();
    var jsonData = {
        "startDate": $("#startDate").val(),
        "endDate": $("#endDate").val(),
        "formativePeriod": $("#formativePeriod").val(),
        "holdingPeriod": $("#holdingPeriod").val(),
        "stockPoolCriteria": {
            "stType": $("#stType").val(),
            "blockTypes": $("#blockTypes").val()
        },
        "baseStockName": $("#baseStockEve").val(),
        "isCustomized": isCustomized,
        "formateAndPickCriteria": {
            "formateType": $("#formativeStrategy").val(),
            "pickType": $("#pickStrategy").val(),
            "rank": $("#rank").val(),
        }
    };

    $("body").removeClass("loaded");

    alert(JSON.stringify(jsonData));

    //
    // alert($("#startDate").val() + "\n" + $("#endDate").val() + "\n" + $("#formativePeriod").val() + "\n" + $("#holdingPeriod").val()
    //     + "\n" + $("#stType").val() + "\n" +$("#blockTypes").val() + "\n" +$("#baseStockEve").val() + "\n" + isCustomized
    //     + "\n" +$("#formativeStrategy").val() + "\n" +$("#pickStrategy").val() + "\n"+$("#rank").val());

    $.ajax({
        type: "post",
        async: true,
        url: "/req_trace_back",
        data: {
            criteriaData: JSON.stringify(jsonData)
        },


        success: function (result) {
            alert(result);
            $("body").addClass("loaded");
            var array = result.split(";");
            if (array[0] == "1") {

                // 处理网页上要显示的信息
                var numberValues = eval("(" + array[1] + ")");              // List<String>
                var abReturnPeriod = eval("(" + array[2] + ")");            // ReturnPeriod
                var reReturnPeriod = eval("(" + array[3] + ")");            // ReturnPeriod
                var holdingDetails = eval("(" + array[4] + ")");            // List<HoldingDetail>
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
                    $("#tb_detail").append("<td>" + holdingDetails[i]["strategyReturn"] + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["baseReturn"] + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["excessReturn"] + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails[i]["remainInvestment"] + "</td>");
                    $("#tb_detail").append("</tr>");
                }
                // alert("--------------------3----------------");


                // 固定形成期的赢率分析
                // $("#tb_certain_formate").empty();
                // for(var i = 0; i < certainFormates.length; i++) {
                //     $("#tb_certain_formate").append("<tr>");
                //     $("#tb_certain_formate").append("<td>" + certainFormates[i]["relativeCycle"] + "</td>");
                //     $("#tb_certain_formate").append("<td>" + (certainFormates[i]["excessRate"]*100) + "%" + "</td>");
                //     $("#tb_certain_formate").append("<td>" + (certainFormates[i]["winRate"]*100) + "%" + "</td>");
                //     $("#tb_certain_formate").append("</tr>");
                // }
                // // alert("--------------------4----------------");
                //
                //
                // // 固定持有期的赢率分析
                // $("#tb_certain_holding").empty();
                // for(var i = 0; i < certainHoldings.length; i++) {
                //     $("#tb_certain_holding").append("<tr>");
                //     $("#tb_certain_holding").append("<td>" + certainHoldings[i]["relativeCycle"] + "</td>");
                //     $("#tb_certain_holding").append("<td>" + (certainHoldings[i]["excessRate"]*100) + "%" + "</td>");
                //     $("#tb_certain_holding").append("<td>" + (certainHoldings[i]["winRate"]*100) + "%" + "</td>");
                //     $("#tb_certain_holding").append("</tr>");
                // }
                // // alert("--------------------5----------------");


                // 处理图标的信息
                var strategyData = JSON.parse(array[5]);            //List<List<String>>
                var baseData = JSON.parse(array[6]);                //List<List<String>>
                var abHistogramData = JSON.parse(array[7]);
                var reHistogramData = JSON.parse(array[8]);
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