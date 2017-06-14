/**
 * Created by cuihua on 2017/5/15.
 */

// 行情界面跳转至个股界面

/**
 * 在个股界面修改查看条件后修改界面数据
 */
function changeSingleStockDetail() {
    var wantedStockCode = $("#stockCode").text();
//        alert("查看股票：" + wantedStockCode);

    var start = $("#datetimeStart > input").val();
    var end = $("#datetimeEnd > input").val();
    // alert(start);
    // alert(end);
    // $("body").removeClass("loaded");

    $.ajax({
        type: "post",
        async: true,
        url: "/stocks/" + wantedStockCode,
        data:{
            "startDate": start,
            "endDate": end
        },

        success: function (result) {
            // $("body").addClass("loaded");
            var array = result.split(";");

            if (array[0] == "1") {

                var candlestickData = eval("(" + array[1] + ")");
                var candlestickData2 = eval("(" + array[1] + ")");
                var bollData = eval("(" + array[2] + ")");
                var volumeData = eval("(" + array[3] + ")");
                var macdData = eval("(" + array[4] + ")");
                var stockOfEndDay = eval("(" + array[5] + ")");
                var startDate = eval("(" + array[6] + ")");
                var isPrivate = eval("(" + array[7] + ")");


                $("#stockDetail").empty();
                $("#stockDetail").append(
                    '<li><span class="col-md-3" style="font-weight:900">开盘</span> <span class="col-md-offset-2 font-green ">'+stockOfEndDay["open"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">收盘</span> <span class="col-md-offset-2 font-red ">'+stockOfEndDay["high"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">最高</span> <span class="col-md-offset-2 font-red ">'+stockOfEndDay["close"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">最低</span> <span class="col-md-offset-2 font-green ">'+stockOfEndDay["low"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">昨收 </span><span class="col-md-offset-2 font-black ">'+stockOfEndDay["preClose"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">成交量</span> <span class="col-md-offset-2 ">'+stockOfEndDay["volume"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">成交额</span> <span class="col-md-offset-2 ">'+stockOfEndDay["transactionAmount"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">涨跌幅</span> <span class="col-md-offset-2 ">'+stockOfEndDay["increaseMargin"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">涨跌额 </span><span class="col-md-offset-2 ">'+stockOfEndDay["fluctuation"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">换手率</span> <span class="col-md-offset-2 ">'+stockOfEndDay["turnoverRate"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">总市值</span> <span class="col-md-offset-2 ">'+stockOfEndDay["totalValue"]+'</span></li>'+
                    '<li><span class="col-md-3" style="font-weight:900">流通值 </span><span class="col-md-offset-2 " >'+stockOfEndDay["circulationMarketValue"]+'</span></li>'
                );
                $("#stockDetail > li").addClass("col-md-5");

                $("#datetimeStart>input").attr('value', startDate);
                $("#datetimeEnd>input").attr('value', stockOfEndDay["stockID"]["date"]);

                var c1 = createCandlestick('candlestick_chart', candlestickData);
                var c2 = createVolume('volume_chart', volumeData);
                var c3 = createMACD('MACD_chart', macdData);
                var c4 = createBULL('boll_chart', candlestickData2, bollData[0], bollData[1], bollData[2]);
                connect(c1, c3);
                connect(c2, c3);
                connect(c1, c4);
                connect(c2, c4);

            } else if (array[0] == "-1") {
                // 提示错误信息
                $("#dateError").show();
                setTimeout("$('#dateError').hide();", 4000);

            } else {
                $("#dateError").show();
                setTimeout("$('#dateError').hide();", 4000);
            }
        },
        error: function (result) {
            $("#dateError").show();
            setTimeout("$('#dateError').hide();", 4000);
        }

    });
}

function connect(chart1,chart2){
    echarts.connect([chart1,chart2]);
}

/**
 * 直接查看比较界面
 */
function directToCompare() {
    window.location.href = "/stocks/compare";
}