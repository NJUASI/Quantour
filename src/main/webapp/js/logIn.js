/**
 * Created by cuihua on 2017/5/12.
 */
function login() {
    var userName = $("#login_username").val();
    var password = $("#login_password").val();

    var jsonData = {
        "userName": userName,
        "password": password
    };

    $.ajax({
        type: "post",
        async: true,
        url: "/req_log_in",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(jsonData),

        success: function (result) {
            alert(result);
            alert(JSON.stringify(jsonData));
            var array = result.split(";");

            if (array[0] == "1") {
                alert("666");
                window.location.href = "/welcome?id=" + userName;
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

function register() {
    var userName = $("#reg_username").val();
    var password1 = $("#reg_password").val();
    var password2 = $("#reg_password2").val();

    $.ajax({
        type: "post",
        async: true,
        url: "/req_register",
        data: {
            "userName":userName,
            "password": password1,
            "password2": password2
        },

        success: function (result) {
            alert(result);
            var array = result.split(";");

            if (array[0] == "1") {
                alert("666");
                window.location.href = "/welcome?id=" + userName;
            } else if (array[0] == "-1") {
                // 提示错误信息
                alert(array[1]);
            } else {
                alert("未知错误类型orz");
            }
        },
        error: function (result) {
            alert(data);
            alert("错误" + result);
        }
    });
}