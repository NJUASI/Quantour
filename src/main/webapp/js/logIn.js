/**
 * Created by cuihua on 2017/5/12.
 */
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    alert(username);

    $.ajax({
        type: "post",
        async: true,
        url: "/logIn",
        data: {
            "username": username,
            "password": password,
        },

        success: function (result) {
            if (result == "1") {
                window.location.href = "/welcome?id=Guest";
            } else {
                alert("qwertyuioiuytrewertyui");
            }
            alert("qwertyuiop");
        },
        error: function () {
            alert("错误");
        }
    });
}