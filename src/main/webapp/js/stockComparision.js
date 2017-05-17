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



            // alert(JSON.stringify(jsonData));
            // var array = result.split(";");


            var data1 =[["2017-05-02","8.94"],["2017-05-03","8.91"],["2017-05-04","8.74"],["2017-05-05","8.63"],["2017-05-08","8.57"],["2017-05-09","8.64"],["2017-05-10","8.67"],["2017-05-11","8.7"]];
            var data2 =[["2017-05-02","8.94"],["2017-05-03","8.91"],["2017-05-04","8.74"],["2017-05-05","8.63"],["2017-05-08","8.57"],["2017-05-09","8.64"],["2017-05-10","8.67"],["2017-05-11","8.7"]];
            alert( result);
            // var data =[ array[0],array[1] ];
            var data =[ result ];
            createLineChart("closesChart",data,'test13',['test1']);

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