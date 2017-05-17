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
        contentType: 'application/json;charset=UTF-8',
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

                // 处理图标的信息
                var strategyData = JSON.parse(array[7]);            //List<List<String>>
                var baseData = JSON.parse(array[8]);                //List<List<String>>
                var abHistogramData = JSON.parse(array[9]);
                var reHistogramData = JSON.parse(array[10]);
                var formateExcessData = JSON.parse(array[11]);
                var formateWinData = JSON.parse(array[12]);
                var holdingExcessData = JSON.parse(array[13]);
                var holdingWinData = JSON.parse(array[14]);


                var trace_back_chart = createTraceBackChart("trace_back_chart", strategyData, baseData, ['策略', '基准'], '1', '1');
                var absolute_histogram_chart = createHistogramChart("absolute_histogram_chart", abHistogramData, "绝对收益直方图");
                var relative_histogram_chart = createHistogramChart("relative_histogram_chart", reHistogramData, "相对收益直方图");
                var formates_excess_chart = createAreaChart("formates_excess_chart", formateExcessData, '胜率');
                var formates_win_chart = createAreaChart("formates_win_chart", formateWinData, '赢率');
                var holdings_excess_chart = createAreaChart("holdings_excess_chart", holdingExcessData, '胜率');
                var holdings_win_chart = createAreaChart("holdings_win_chart", holdingWinData, '赢率');


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