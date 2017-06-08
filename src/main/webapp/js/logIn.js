/**
 * Created by cuihua on 2017/5/12.
 */

$(document).ready(function () {
    //显示出白色下划线的效果


   //切换白色下划线
   $(".navbar-nav li a").on("click",function () {
       $(".navbar-nav li a[class~='act']").css("color","#BDBDBD");
       $(".navbar-nav li a[class~='act']").removeClass("act");
       $(this).addClass("act");
       $(this).css("color","#ffffff");
   });
});

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

            var array = result.split(";");

            if (array[0] == "1") {
                window.location.reload();
            } else if (array[0] == "-1") {
                $("#errorMessageField").html(array[1]);
            } else {
                alert("未知错误类型orz");
            }
        },
        error: function (result) {
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
            var array = result.split(";");

            if (array[0] == "1") {
                window.location.reload();
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