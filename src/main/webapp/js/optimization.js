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
                $("#inputError").show();
                setTimeout('$("#inputError").hide();',2000);
            }
        num*=(max-min)/length-1;
    });
    $(".rankRow").each(function () {
        var thisValue=parseInt($(this).find(".weight").eq(0).html());
        var max=parseInt($(this).find(".max_num").eq(0).val());
        var min=parseInt($(this).find(".min_num").eq(0).val());
        var length=parseInt($(this).find(".length_num").eq(0).val());
            if(thisValue<min||thisValue>max){
                $("#inputError").show();
                setTimeout('$("#inputError").hide();',2000);
            }
        num*=(max-min)/length-1;
    });
    $("#resultNum").html(num);
}
function validate(num){
    var reg = /^\d{1,3}$/;
    if(!reg.test(num)){
        return false;
    }
}
$("#submitBt").click(function () {
    var isValid=true;
    $(".chooseRow").each(function () {
        var thisValue=parseInt($(this).find(".value").eq(0).html());
        var max=parseInt($(this).find(".max_num").eq(0).val());
        var min=parseInt($(this).find(".min_num").eq(0).val());
           if(thisValue < min || thisValue > max){
               alert(thisValue);
               alert(max);
               alert(min);
               $("#inputError").show();
               setTimeout('$("#inputError").hide();',2000);
               isValid=false;
               return false;
           }
    });
    $(".rankRow").each(function () {
        var thisValue=parseInt($(this).find(".weight").eq(0).html());
        var max=parseInt($(this).find(".max_num").eq(0).val());
        var min=parseInt($(this).find(".min_num").eq(0).val());
           if(thisValue<min||thisValue>max){
               $("#inputError").show();
               setTimeout('$("#inputError").hide();',2000);
               isValid=false;
               return false;
           }
    });
    $(".num").each(function () {
        isValid=validate($(this).val());
        if(isValid==false) {
            return false;
        }
    });
    if(isValid==false){
           return false;
    }

    alert($("#mainFunc").val());
    alert($("#resultNum").html());
    //TODO fjj 传递优化数据！
    $(".chooseRow").each(function () {
        alert($(this).find(".max_num").eq(0).val());
        alert($(this).find(".min_num").eq(0).val());
        alert($(this).find(".length_num").eq(0).val());
    });
    $(".rankRow").each(function (){
        alert($(this).find(".max_num").eq(0).val());
        alert($(this).find(".min_num").eq(0).val());
        alert($(this).find(".length_num").eq(0).val());
    });




        //TODO fjj 初始化表头
        $("#optimizationHead").empty()
        $("#optimizationHead").append("<th>策略定义</th>"+
            "<th>年化收益</th>"+
            "<th>夏普比率</th>"+
            "<th>最大回撤率</th>"+
            "<th>收益波动率</th>"+//TODO 加你传下来自己的名称的权重
            "<th>收盘价_权重</th>"+
            "<th>总市值_权重</th>"
        );
        $("#optimizationList").empty();
        //TODO 遍历添加你自己的数据
//        for (var i = 0; i < transferDetails.length; i++) {
        $("#optimizationList").append("<tr>" +
//                "<td>" + transferDetails[i]["stockName"] + "</td>" +
//                "<td>" + transferDetails[i]["stockCode"] + "</td>" +
//                "<td>" + transferDetails[i]["buyDate"] + "</td>" +
//                "<td>" + transferDetails[i]["sellDate"] + "</td>" +
//                "<td>" + transferDetails[i]["buyPrice"] + "</td>" +
//                "<td>" + transferDetails[i]["sellPrice"] + "</td>" +
//                "<td>" + (transferDetails[i]["changeRate"] * 100).toFixed(2) + "%" + "</td>" +
            "</tr>");


});