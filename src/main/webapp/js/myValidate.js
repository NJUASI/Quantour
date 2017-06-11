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
        $(this).css("border","1px solid red");
        return false;
    }
    else if (!reg.test($(this).val())) {
        $('#holdingPeriodError').show();
        $(this).css("border","1px solid red");
        return false;
    }else{
        $('#holdingPeriodError').hide();
        $(this).css("border","1px solid #CCCCCC");
    }
});
