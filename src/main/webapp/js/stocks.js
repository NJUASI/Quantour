/**
 * Created by cuihua on 2017/5/15.
 */

function getSingleStockDetail() {
        var wantedStockCode = $("#search-input").val();
        $.ajax({
            type: "post",
            async: true,
            url: "/stocks/" + wantedStockCode,

            success: function (result) {
                var array = result.split(";");

                if (array[0] == "1") {
                    window.location.href = "/stocks/" + wantedStockCode;
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