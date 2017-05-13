/**
 * Created by cuihua on 2017/5/12.
 */
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    $("#")

    var obj = $.ajax({
            type: "post",
            async: true,
            url: "/log_in",
            data: {
                "username": username,
                "password": password,
            },

        success: function (result) {
            var array = result.split(";")

            if (array[0] == "1") {
                alert("666")
                window.location.href = "/welcome?id=" + username;
            } else {
                alert("qwertyuioiuytrewertyui");
            }
        },
        error: function () {
            alert("错误");
        }
    });
}