/**
 * Created by cuihua on 2017/5/15.
 */
function compare() {
    var stockCode1 = $("#stock1").val();
    var stockCode2 = $("#stock2").val();
    var startDate = $("#compare_startDate>input").val();
    var endDate = $("#compare_endDate>input").val();

    var jsonData = {
        "stockCode1": stockCode1,
        "stockCode2": stockCode2,
        "startDate": startDate,
        "endDate": endDate
    };

    $.ajax({
        type: "post",
        async: true,
        url: "/stocks/req_compare",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(jsonData),

        success: function (result) {

            var parts = result.split(";");
            var closes01 = JSON.parse(parts[0]);
            var closes02 = JSON.parse(parts[1]);
            var logarithmicYield01 = JSON.parse(parts[2]);
            var logarithmicYield02 = JSON.parse(parts[3]);
            var comparisionName = JSON.parse(parts[4]);

            var closesData = [closes01, closes02];
            var logarithmicYield = [logarithmicYield01, logarithmicYield02];

            createLineChart("closesChart", closesData, '收盘价', comparisionName);
            createLineChart("logarithmicYieldChart", logarithmicYield, "对数收益率方差", comparisionName);
        },
        error: function (result) {
            alert(JSON.stringify(jsonData));
            alert("错误" + result);
        }

    });

}