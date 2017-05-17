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
        "baseStockName": $("#baseStock").val(),
        "isCustomized": isCustomized,
        "formateAndPickCriteria": {
            "formateType": $("#formativeStrategy").val(),
            "pickType": $("#pickStrategy").val(),
            "rank": $("#rank").val(),
        }
    };

    //
    // alert($("#startDate").val() + "\n" + $("#endDate").val() + "\n" + $("#formativePeriod").val() + "\n" + $("#holdingPeriod").val()
    //     + "\n" + $("#stType").val() + "\n" +$("#blockTypes").val() + "\n" +$("#baseStock").val() + "\n" + isCustomized
    //     + "\n" +$("#formativeStrategy").val() + "\n" +$("#pickStrategy").val() + "\n"+$("#rank").val());

    $.ajax({
        type: "post",
        async: true,
        url: "/req_trace_back",
        contentType:'application/json;charset=UTF-8',
        data: JSON.stringify(jsonData),


        success: function (result) {
            alert(result);
            alert(JSON.stringify(jsonData));

            var array = result.split(";");
            if (array[0] == "1") {
                alert("666");

                // 处理网页上要显示的信息
                var numberValues = JSON.parse(array[1]);            // List<String>
                var abReturnPeriod = JSON.parse(array[2]);          // ReturnPeriod
                var reReturnPeriod = JSON.parse(array[3]);          // ReturnPeriod
                var holdingDetails = JSON.parse(array[4]);          // List<HoldingDetail>
                var certainFormates = JSON.parse(array[5]);         // List<ExcessAndWinRateDist>
                var certainHoldings = JSON.parse(array[6]);         // List<ExcessAndWinRateDist>


                // 回测的数值型数据
                $("#chartPanel").toggle("slow");
                $("#tb_chart").empty();
                $("#tb_chart").append("<tr>");
                for(var i = 0; i < 3; i++) {
                    switch (i) {
                        case 0:$("#tb_chart").append("<td>本策略</td>");
                            break;
                        case 1:$("#tb_chart").append("<td>基准股票</td>");
                            break;
                        case 2:$("#tb_chart").append("<td>相对收益</td>");
                            break;
                    }

                    for (var j = 0; j < 7; j++) {
                        $("#tb_chart").append("<td>" + numberValues.get(i * 7 + j) + "</td>");
                    }
                }
                $("#tb_chart").append("</tr>");

                // 股票周期的对比图
                $("#cyclePanel").toggle("slow");
                $("#tb_cycle_ab").empty();
                $("#tb_cycle_ab").append("<tr>");
                $("#tb_cycle_ab").append("<td>" + abReturnPeriod.positivePeriodsNum + "</td>");
                $("#tb_cycle_ab").append("<td>" + abReturnPeriod.negativePeriodNum + "</td>");
                $("#tb_cycle_ab").append("<td>" + abReturnPeriod.winRate + "</td>");
                $("#tb_cycle_ab").append("</tr>");

                $("#tb_cycle_re").empty();
                $("#tb_cycle_re").append("<tr>");
                $("#tb_cycle_re").append("<td>" + reReturnPeriod.positivePeriodsNum + "</td>");
                $("#tb_cycle_re").append("<td>" + reReturnPeriod.negativePeriodNum + "</td>");
                $("#tb_cycle_re").append("<td>" + reReturnPeriod.winRate + "</td>");
                $("#tb_cycle_re").append("</tr>");



                // 持有周期详情
                $("#holdingDetailPanel").toggle("slow");
                $("#tb_detail").empty();
                $("#tb_detail").append("<tr>");
                for(var i = 0; i < holdingDetails.size(); i++) {
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).periodSerial + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).startDate + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).endDate + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).holdingNum + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).strategyReturn + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).baseReturn + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).excessReturn + "</td>");
                    $("#tb_detail").append("<td>" + holdingDetails.get(i).remainInvestment + "</td>");
                }
                $("#tb_detail").append("</tr>");


                // 固定形成期的赢率分析
                $("#certainFormatePanel").toggle("slow");
                $("#tb_certain_formate").empty();
                $("#tb_certain_formate").append("<tr>");
                for(var i = 0; i < certainFormates.size(); i++) {
                    $("#tb_certain_formate").append("<td>" + certainFormates.get(i).relativeCycle + "</td>");
                    $("#tb_certain_formate").append("<td>" + certainFormates.get(i).excessRate + "</td>");
                    $("#tb_certain_formate").append("<td>" + certainFormates.get(i).winRate + "</td>");
                }
                $("#tb_certain_formate").append("</tr>");


                // 固定持有期的赢率分析
                $("#certainHoldingPanel").toggle("slow");
                $("#tb_certain_holding").empty();
                $("#tb_certain_holding").append("<tr>");
                for(var i = 0; i < certainHoldings.size(); i++) {
                    $("#tb_certain_holding").append("<td>" + certainHoldings.get(i).relativeCycle + "</td>");
                    $("#tb_certain_holding").append("<td>" + certainHoldings.get(i).excessRate + "</td>");
                    $("#tb_certain_holding").append("<td>" + certainHoldings.get(i).winRate + "</td>");
                }
                $("#tb_certain_holding").append("</tr>");


                // 处理图标的信息
                var strategyData = JSON.parse(array[7]);            //List<List<String>>
                var baseData = JSON.parse(array[8]);                //List<List<String>>
                var abReturnPeriod = JSON.parse(array[9]);






            } else if (array[0] == "-1") {
                // 提示错误信息
                alert(array[1]);
            } else {
                alert("未知错误类型orz");
            }
        },
        error: function (result) {
            alert(JSON.stringify(jsonData));
            alert("错误" + result);
        }
    });
}