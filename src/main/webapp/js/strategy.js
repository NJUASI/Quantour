/**
 * Created by cuihua on 2017/6/8.
 */
// 导入本js需要用的js
document.write("<script src='traceBack.js'></script>");

/**
 * 直接跳转至要查看的策略界面
 */
function directToGeneralStrategy(strategy_id) {
    window.location.href = "/strategy/" + strategy_id;
}



/**
 * 创建者修改策略
 */
function strategy_modify(curUser) {

    // TODO 传数据
    var strategyData = {
        "strategyID": strategyID,
        "date": null,
        "creater": curUser,
        "isPrivate": isPrivate,
        "content": JSON.stringify(getTraceBackCriteria()),
        "description": description,
        "traceBackInfo": null,
        "users": null
    };

    $.ajax({
        type: "post",
        async: true,
        url: "/strategy/modify",
        data:{
            "strategy": JSON.stringify(strategyData)
        },

        success: function (result) {
            // $("body").addClass("loaded");
            var array = result.split(";");

            if (array[0] == "1") {
                alert("666\n修改成功");
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
                alert("666\n删除成功");
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
function strategy_subscribe() {
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
                alert("666\n订阅成功");
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
function strategy_revoke_subscribe() {
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
                alert("666\n取消订阅成功");
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

