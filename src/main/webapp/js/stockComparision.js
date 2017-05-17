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
            // var aa = JSON.parse(result);
            // alert(aa);
            alert(result);
            alert(result[0]);
            createLineChart('closesChart',result[0],'wfsd','1231231');
            // alert(JSON.stringify(jsonData));
            // var array = result.split(";");

            if (true) {
                alert("666");
                // createLineChart("closesChart", }, '收盘价', ${comparisionName});
            } else if (false) {
                // 提示错误信息
                // alert(array[1]);
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