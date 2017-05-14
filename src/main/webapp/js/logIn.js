/**
 * Created by cuihua on 2017/5/12.
 */
function login() {
    var jsonData = {
        "userName": $("#username").val(),
        "password": $("#password").val()
    };

    var obj = $.ajax({
        type: "post",
        async: true,
        url: "/log_in",
        contentType:'application/json;charset=UTF-8',
        data: JSON.stringify(jsonData),

        success: function (result) {
            alert(result);
            alert(JSON.stringify(jsonData));
            var array = result.split(";");

            if (array[0] == "1") {
                alert("666");
                window.location.href = "/welcome?id=" + username;
            } else if (array[0] == "-1"){
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