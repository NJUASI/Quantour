/**
 * Created by cuihua on 2017/5/15.
 */
function compare() {
    var stockCode1 = $("#stock1").val();
    var stockCode2 = $("#stock2").val();
    var startDate = $("#compare_startDate").val();
    var endDate = $("#compare_endDate").val();

    var jsonData = {
        "stockCode1": stockCode1,
        "stockCode2": stockCode2,
        "startDate": startDate,
        "endDate": endDate
    };

    $.ajax({
        type: "post",
        async: true,
        url: "/req_compare",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(jsonData),

        success: function (result) {
            alert(result);
            alert(JSON.stringify(jsonData));
            var array = result.split(";");

            if (array[0] == "1") {
                alert("666");
                window.location.href = "/compare";
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