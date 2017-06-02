/**
 * Created by cuihua on 2017/5/15.
 */


function compare() {
    var stockCode1 = $("#search-input1").val();
    var stockCode2 = $("#search-input2").val();
    var startDate = $("#compare_startDate>input").val();
    var endDate = $("#compare_endDate>input").val();

    var jsonData = {
        "stockCode1": stockCode1,
        "stockCode2": stockCode2,
        "start": startDate,
        "end": endDate
    };

    alert(JSON.stringify(jsonData));

    $.ajax({
        type: "post",
        async: true,
        url: "/stocks/req_compare",
        data: {
            comparisionCriteria: JSON.stringify(jsonData)
        },

        success: function (result) {
            var parts = result.split(";");
            var closes01 = JSON.parse(parts[0]);
            var closes02 = JSON.parse(parts[1]);
            var logarithmicYield01 = JSON.parse(parts[2]);
            var logarithmicYield02 = JSON.parse(parts[3]);
            var comparisionName = JSON.parse(parts[4]);

            var closesData = [closes01, closes02];
            var logarithmicYield = [logarithmicYield01, logarithmicYield02];

            var numVals = JSON.parse(parts[5]); //二维数组

            createLineChart("closesChart", closesData, '收盘价', comparisionName);
            createLineChart("logarithmicYieldChart", logarithmicYield, "对数收益率方差", comparisionName);

            $("#analysePanel").fadeIn("slow");

            $("#compareChart").empty();
            for (var i = 0; i < 2; i++) {
                $("#compareChart").append("<tr>");
                for (var j = 0; j < 5; j++) {
                    $("#compareChart").append("<td>" + numVals[i][j] + "</td>");
                }
                $("#compareChart").append("</tr>");
            }

        },

        error: function (result) {
            alert("错误" + result);
        }
    });

}