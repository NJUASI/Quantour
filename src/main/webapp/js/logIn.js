/**
 * Created by cuihua on 2017/5/12.
 */
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    alert(username);

    var obj = $.ajax({
            type: "post",
            async: true,
            url: "/log_in",
            data: {
                "username": username,
                "password": password,
            },

        success: function (result) {
            if (result == "1") {
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