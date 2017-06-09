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
                $("#errorMessageField").html("您的用户名或密码错误");
            } else {
                $("#errorMessageField").html("您的用户名或密码错误");
            }
        },
        error: function (result) {
            alert("错误" + result);
        }
    });
}
$.validator.addMethod("checkQQ",function(value,element,params){
    var checkQQ = /^[a-zA-Z]+\w+$/;
    return this.optional(element)||(checkQQ.test(value));
},"请输入正确的QQ号码！");
$("#registerForm").validate({
    onsubmit :false,
    onkeyup :true,// 是否在敲击键盘时验证
    rules: {
        reg_username: {
            required: true,
            minlength: 5,
            maxlength: 15,
            checkQQ:true
        },
        reg_password: {
            required: true,
            minlength: 5,
            maxlength: 15
        },
        reg_password2: {
            equalTo: "#reg_password"
        },
        e_mail:{
            // required: true,
            email: true
        }
    },
    messages: {
        reg_username:{
            required:"用户名不能为空",
            minlength: "用户名不能少于5位",
            maxlength: "用户名不能高于15位"
        },
        reg_password: {
            required: "密码不能为空",
            minlength: "密码不能少于5位",
            maxlength: "密码不能高于15位"
        },
        reg_password2: {
            equalTo: "两次密码不一样"
        },
        e_mail:{
            required: "邮箱不能为空",
            email: "你输入的邮箱格式错误"
        }
    }
});
function register() {
    var userName = $("#reg_username").val();
    var password1 = $("#reg_password").val();
    var password2 = $("#reg_password2").val();
//TODO FJJ 对电子邮箱的处理
    var email= $("#e_mail").val();


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
                $("#errorMessageField2").html(array[1]);
            } else {
                $("#errorMessageField2").html("请再次确定输入信息");
            }
        },
        error: function (result) {
            alert("错误" + result);
        }
    });
}