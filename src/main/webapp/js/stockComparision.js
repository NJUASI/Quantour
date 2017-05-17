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

            var parts = result.split(";");

            var wanted01 = JSON.parse(parts[0]);
            var wanted02 = JSON.parse(parts[1]);

            alert(wanted01);
            alert(wanted02);



            // alert(JSON.stringify(jsonData));
            // var array = result.split(";");


            var data1 =[["2017-05-02","8.94"],["2017-05-03","8.91"],["2017-05-04","8.74"],["2017-05-05","8.63"],["2017-05-08","8.57"],["2017-05-09","8.64"],["2017-05-10","8.67"],["2017-05-11","8.7"]];
            var data2 =[["2017-05-02","8.94"],["2017-05-03","8.91"],["2017-05-04","8.74"],["2017-05-05","8.63"],["2017-05-08","8.57"],["2017-05-09","8.64"],["2017-05-10","8.67"],["2017-05-11","8.7"]];
            // var data =[ array[0],array[1] ];
            var data = [wanted01, wanted02];
            var wantedData = [data1, data2];
            alert(wanted01 + "\n" + data1);
            alert(wanted02 + "\n" + data2);

            alert(data + "\n" + wantedData);

            createLineChart("closesChart",data,'test13',['test1', 'test2']);

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