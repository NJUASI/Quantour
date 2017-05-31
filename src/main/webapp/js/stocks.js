/**
 * Created by cuihua on 2017/5/15.
 */

// 行情界面跳转至个股界面

// 在个股界面修改查看条件后修改界面数据
function changeSingleStockDetail() {
    var wantedStockCode = $("#stockCode").text();
//        alert("查看股票：" + wantedStockCode);

    var start = $("#datetimeStart > input").val();
    var end = $("#datetimeEnd > input").val();

    $("body").removeClass("loaded");

//        alert(start);

    $.ajax({
        type: "post",
        async: true,
        url: "/stocks/" + wantedStockCode,
        data:{
            "startDate": start,
            "endDate": end
        },

        success: function (result) {
            $("body").addClass("loaded");
            var array = result.split(";");

            if (array[0] == "1") {

                var candlestickData = array[1];
                var volumeData = array[2];
                var stockOfEndDay = eval("(" + array[3] + ")");
                var startDate = eval("(" + array[4] + ")");
                var isPrivate = eval("(" + array[5] + ")");


                // TODO 高源 前端修改数据，画图


                $("#stockDetail").empty();



                createCandlestickChart('candlestick_chart', candlestickData, volumeData);

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


function directToCompare() {
    window.location.href = "/stocks/compare";
}