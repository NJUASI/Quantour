/**
 * Created by cuihua on 2017/5/15.
 */
function getOneDate() {
    var wantedDate = $("#stocks_date").val();
    alert("查看日期：" + wantedDate);


    $.ajax({
        type: "post",
        async: true,
        url: "/stocks?" + wantedDate,

        success: function (result) {
            alert(result);
            var array = result.split(";");

            if (array[0] == "1") {
                alert("666");
                // TODO 请求成功并处理
                window.location.href = "/stocks?" + wantedDate;
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


function getOneStock() {
    var wantedStockCode = $("#stocks_id").val();
    alert("查看股票：" + wantedStockCode);

    $.ajax({
        type: "post",
        async: true,
        url: "/stocks/" + wantedStockCode,

        success: function (result) {
            alert(result);
            var array = result.split(";");

            if (array[0] == "1") {
                alert("666");
                window.location.href = "/stocks/" + wantedStockCode;
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