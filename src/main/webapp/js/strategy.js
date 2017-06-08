/**
 * Created by cuihua on 2017/6/8.
 */

/**
 * 直接跳转至要查看的策略界面
 */
function directToGeneralStrategy(strategy_id) {
    window.location.href = "/strategy/" + strategy_id;
}

/**
 * 股票策略创建界面引出
 */
$(function () {
    $("#createStrategy").click(function () {
        $("#mymodal").modal("toggle");
        var blockType="";
        for( var i = 0; i< $("#blockTypes").val().length;i++)   {
            if($("#blockTypes").val()[i]=="ZB"){
                blockType+="主板 ";
            }else if($("#blockTypes").val()[i]=="CYB"){
                blockType+="创业板 ";
            }else{
                blockType+="中小板 ";
            }
        }
        $("#strategyBlock").html(blockType);
        var stType=document.getElementById("stType").options[document.getElementById("stType").selectedIndex].text;
        $("#strategyST").html(stType);
        $("#strategyBace").html($("#baseStockEve").val());
        $("#strategyPeriod").html($("#holdingPeriod").val());
        $("#strategyHolding").html($("#maxHolding").val());

        $("#quotaCreate").empty();
        $("#quotaCreate").append( "<div class=' col-md-10'> <strong class='col-md-5'>指标</strong><strong class='col-md-4'>比较符</strong> <strong class='col-md-2'>值</strong> </div>")

        $(".quotaRow").each(function () {
            var rank="";
            switch ($(this).find('.quotaRank').val()){
                case "RANK_MIN":
                    rank="排名最小";
                    break;
                case "RANK_MAX_PERCENT":
                    rank="排名%最大";
                    break;
                case "RANK_MAX":
                    rank="排名最大";
                    break;
                case  "RANK_MIN_PERCENT":
                    rank="排名%最小";
                    break;
                case  "RANK_GREATER":
                    rank="大于";
                    break;
                case  "RANK_LESS":
                    rank="小于";
                    break;
                case  " RANK_EQUAL":
                    rank="等于";
                    break;
            }

            $("#quotaCreate").append( "<div class=' col-md-10'> <span class='col-md-5'>"+$(this).find('.numOfN').val() + $(this).find('.quotaName').html()+"</span><span class='col-md-4'>"+rank+"</span> <span class='col-md-2'>"+$(this).find('.quotaNum').val()+"</span> </div>")
//

        });

        $("#rankCreate").empty();
        $("#rankCreate").append( "<div class=' col-md-10'> <strong class='col-md-5'>指标</strong><strong class='col-md-4'>次序</strong> <strong class='col-md-2'>权重</strong> </div>")

        $(".rankRow").each(function (){
            var rank="";
            switch ($(this).find('.rankOrder').val()){
                case "ASC_RANK":
                    rank="由小到大";
                    break;
                case "DESC_RANK":
                    rank="由大到小";
                    break;
            }
            $("#rankCreate").append( "<div class=' col-md-10'> <span class='col-md-5'>"+$(this).find('.numOfN').val() + $(this).find('.quotaName').html()+"</span><span class='col-md-4'>"+rank+"</span> <span class='col-md-2'>"+$(this).find('.quotaWeight').val()+"</span> </div>")

        });

    });
});

/**
 * 创建者修改策略
 */
function strategy_modify(curUser) {

}

/**
 * 创建者删除策略
 */
function strategy_delete() {
    var strategyID = $("#strategyName").val();

    $.ajax({
        type: "post",
        async: true,
        url: "/strategy/delete",
        data:{
            "deleteStrategyID": strategyID
        },

        success: function (result) {
            // $("body").addClass("loaded");
            var array = result.split(";");

            if (array[0] == "1") {
                alert(666);

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

/**
 * 非创建者订阅策略
 */
function strategy_subscribr() {
    var strategyID = $("#strategyName").val();

    $.ajax({
        type: "post",
        async: true,
        url: "/strategy/" + strategyID + "/subscribe",
        data:{
            "id": strategyID
        },

        success: function (result) {
            // $("body").addClass("loaded");
            var array = result.split(";");

            if (array[0] == "1") {
                alert(666);

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

/**
 * 非创建者取消订阅策略
 */
function strategy_revoke_subscribr() {
    var strategyID = $("#strategyName").val();

    $.ajax({
        type: "post",
        async: true,
        url: "/strategy/" + strategyID + "/revoke_subscribe",
        data:{
            "id": strategyID
        },

        success: function (result) {
            // $("body").addClass("loaded");
            var array = result.split(";");

            if (array[0] == "1") {
                alert(666);

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

