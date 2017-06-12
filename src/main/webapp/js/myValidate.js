/**
 * Created by 61990 on 2017/6/10.
 */


$("#strategyName").bind('input propertychange', function () {

    if($(this).val().trim()==""){
        $('#nameErrorPanel').show();
        $('#nameError').html("策略名称必须填写");
        return false;
    }else{
        $('#nameErrorPanel').hide();
    }

});
$("#strategyDescription").bind('input propertychange', function () {

    if($(this).val().trim()==""){
        $('#descriptionError').show();
        return false;
    }else{
        $('#descriptionError').hide();
    }

});
var reg = /^\d{1,2}$/;
$("#maxHolding").bind('input propertychange', function () {

    if($(this).val()==""||$(this).val()==0||$(this).val()=="0"){
        $('#maxHoldingError').show();
        $(this).css("border","1px solid red");
        return false;
    }
    else if (!reg.test($(this).val())) {
        $('#maxHoldingError').show();
        $(this).css("border","1px solid red");
        return false;
    }else{
        $(this).css("border","1px solid #CCCCCC");
        $('#maxHoldingError').hide()
    }

});
$("#holdingPeriod").bind('input propertychange', function () {
    if($(this).val()==""||$(this).val()==0||$(this).val()=="0"){
        $('#holdingPeriodError').show();
        $(this).css("border","2px solid red");
        return false;
    }
    else if (!reg.test($(this).val())) {
        $('#holdingPeriodError').show();
        $(this).css("border","2px solid red");
        return false;
    }else{
        $('#holdingPeriodError').hide();
        $(this).css("border","1px solid #CCCCCC");
    }
});

var reg6 = /^\d{1,3}$/;
$("#timing_text1").bind('input propertychange', function () {
    if($(this).val()==""||$(this).val()==0||$(this).val()=="0"){
        $('#timingError3').show();
        $(this).css("border","2px solid red");
        return false;
    }
    else if (!reg6.test($(this).val())) {
        $('#timingError2').show();
        $(this).css("border","2px solid red");
        return false;
    }else{
        $('#timingError3').hide();
        $(this).css("border","1px solid #CCCCCC");
    }
});
$("#timing_text2").bind('input propertychange', function () {
    if($(this).val()==""||$(this).val()==0||$(this).val()=="0"){
        $('#timingError3').show();
        $(this).css("border","2px solid red");
        return false;
    }
    else if (!reg6.test($(this).val())) {
        $('#timingError3').show();
        $(this).css("border","2px solid red");
        return false;
    }else{
        $('#timingError3').hide();
        $(this).css("border","1px solid #CCCCCC");
    }
});
$("#position").bind('input propertychange', function () {

    if (!reg6.test($(this).val())) {
        $('#timingError3').show();
        $(this).css("border","2px solid red");
        return false;
    }
    if ($(this).val()>100) {
        $('#timingError3').show();
        $(this).css("border","2px solid red");
        return false;
    }
    else{
        $('#timingError3').hide();
        $(this).css("border","1px solid #CCCCCC");
    }
});