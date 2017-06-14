/**
 * Created by 61990 on 2017/6/13.
 */


$(".num").css({"margin-top": "-7px","margin-bottom": "15px"});
$(".num").bind('input propertychange', function () {
    var isValid=true;
    $(".num").each(function () {
        isValid=validate($(this).val());
        if(isValid==false) {
            return false;
        }
    });
    if(isValid==false){
        return false
    }else{
        calculateNum();
    }
});
function calculateNum() {
    var num=1;
    $(".chooseRow").each(function () {
        var thisValue=parseInt($(this).find(".value").eq(0).html());
        var max=parseInt($(this).find(".max_num").eq(0).val());
        var min=parseInt($(this).find(".min_num").eq(0).val());
        var length=parseInt($(this).find(".length_num").eq(0).val());
            if(thisValue<min||thisValue>max){
                $("#inputError").html(' <strong style="color:indianred">参数原值不得超出最大最小值范围</strong>');
                $("#inputError").show();
                setTimeout('$("#inputError").hide();',2000);
            }
        num *= parseInt((max - min) / length) + 1;
    });
    $(".rankRow").each(function () {
        var thisValue=parseInt($(this).find(".weight").eq(0).html());
        var max=parseInt($(this).find(".max_num").eq(0).val());
        var min=parseInt($(this).find(".min_num").eq(0).val());
        var length=parseInt($(this).find(".length_num").eq(0).val());
            if(thisValue<min||thisValue>max){
                $("#inputError").html(' <strong style="color:indianred">参数原值不得超出最大最小值范围</strong>');
                $("#inputError").show();
                setTimeout('$("#inputError").hide();', 2000);
            }
        num *= parseInt((max - min) / length) + 1;
    });
    $("#resultNum").html(num);
}
function validate(num){
    var reg = /^\d{1,3}$/;
    if(!reg.test(num)){
        return false;
    }
}