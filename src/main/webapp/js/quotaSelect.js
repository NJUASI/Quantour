/**
 * Created by 61990 on 2017/6/8.
 */
$(".quota").hover(function () {
    $(this).css({"cursor": "pointer"});
});



function convert(indicatorType) {
    indicatorType = indicatorType.trim();
    switch (indicatorType) {
        case "开盘价":
            return " ";
        case "收盘价":
            return " ";
        case "最高价":
            return " ";
        case "最低价":
            return " ";
        case "前日收盘价":
            return " ";
        case "日均成交价":
            return " ";
        case "后复权开盘价":
            return " ";
        case "后复权收盘价":
            return " ";
        case "后复权最高价":
            return " ";
        case "后复权最低价":
            return " ";
        case "前日后复权收盘价":
            return " ";
        case "后复权均价":
            return " ";
        case "总股本":
            return "亿";
        case "流通股本":
            return "亿";
        case "总市值":
            return "亿";
        case "流通市值":
            return "亿";
        case "股价振幅":
            return "%";
        case "市盈率":
            return " ";
        case "市净率":
            return " ";
        case "市销率":
            return " ";
        case "静态市盈率":
            return " ";
        case "动态市盈率":
            return " ";
        default:
            switch (indicatorType.substring(indicatorType.length - 2)) {
                case "交额":
                    return "亿";
                case "交量":
                    return "万";
                case "离率":
                    return "%";
                case "动率":
                    return "%";
                case "涨幅":
                    return "%";
                case "手率":
                    return "%";
            }

    }

}


function quotaChange() {
    var quotaName;
//       alert($(this).html())
    if ($('#searchQuota').val().substring(0, 1) == "N") {
        quotaName = "<div class='col-md-5'><input class='numOfN form-control' value='10'></div><div style='margin-top: 6px' class='quotaName'>" + $('#searchQuota').val().substring(1) + "</div>"
    } else {
        quotaName = "<div class='col-md-4' hidden><input class='numOfN form-control'></div><div style='margin-top: 6px' class='col-md-12 quotaName'>" + $('#searchQuota').val() + "</div>"
    }

    var rankType;
    var whichTab;
    var whichValue;
    var whichButton;
    var whichType;
    if($("#choose").hasClass("active")){
        rankType = "<select class=\"form-control col-md-12 quotaRank\" style=\"padding-left: 5px;padding-right: 5px\">" +
            "<option value='RANK_MAX'>排名最大</option>" +
            "<option value='RANK_MIN'>排名最小</option>" +
            "<option value='RANK_MAX_PERCENT'>排名%最大</option>" +
            "<option value='RANK_MIN_PERCENT'>排名%最小</option>" +
            "<option value='RANK_GREATER'>大于</option>" +
            "<option value='RANK_LESS'>小于</option>" +
            "<option value='RANK_EQUAL'>等于</option>" +
            "</select>" ;
        whichTab =$("#quotaList");

        whichValue=  "<div class=\"row\">" +
            "<div class=\"col-md-10\">" +
            "<input type=\"text\" class=\"form-control quotaNum\" value=\"10\"/>" +
            "</div>" +
            "<div class='percent' style=\"margin-left: -10px;display: inline-block;visibility: hidden;margin-top: 5px\" >%</div>" +
            "</div>" ;
        whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary quotaBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
        whichType= 'quotaRow';
    }else{
        rankType="<select class=\"form-control col-md-12 rankOrder\" style=\"padding-left: 5px;padding-right: 5px\">" +
            "<option value='ASC_RANK'>由小到大</option>" +
            "<option value='DESC_RANK'>由大到小</option>" +
            "</select>" ;
        whichTab= $("#rankList");
        whichValue=  "<div class=\"row\">" +
            "<div class=\"col-md-9\">" +
            "<input type=\"text\" class=\"form-control quotaWeight\" value=\"1\"/>" +
//                    "<input id='ex1' data-slider-id='ex1Slider' type='text' data-slider-min='0' data-slider-max='20' data-slider-step='1' data-slider-value='14'/>"+
            "</div>" +
            "</div>" ;
        whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary rankBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
        whichType= 'rankRow';

    }
    whichTab.append("<tr class="+whichType+">" +
        "<td class=\"col-md-4\"><div class='row'>" + quotaName + "</div></td>" +
        "<td class=\"col-md-2\">" +
        "<div class=\"row\">" +
        rankType+
        "</div>" +
        "</td>" +
        "<td class=\"col-md-2\">" +
        whichValue+
        "</td>" +
        whichButton+
        "</tr>");

    var reg2 = /^[1-9]$/;
    $(".quotaWeight").bind('input propertychange', function () {
        if($(this).val()==""){
            $(this).css("border","2px solid red");
            return false;
        }
        else if (!reg2.test($(this).val())) {
            $(this).css("border","2px solid red");
            return false;
        }else{
            $(this).css("border","1px solid #CCCCCC");
        }
    });
    var reg3 = /^\d{1,20}$/;
    $(".quotaNum").bind('input propertychange', function () {
        alert("213");
        if($(this).val()==""){
            $(this).css("border","2px solid red");
            return false;
        }
        else if (!reg3.test($(this).val())) {
            $(this).css("border","2px solid red");
            return false;
        }else{
            $(this).css("border","1px solid #CCCCCC");
        }
    });

    $(".quotaBt").unbind("click");
    $(".quotaBt").click(function () {
        $("#quotaList").find("tr").eq($(".quotaBt").index($(this))).remove();
    });

    $(".quotaRank").unbind("input propertychange");
    $(".quotaRank").bind('input propertychange', function () {
        if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "hidden");
        } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html("%");
        } else {
            var name = $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".quotaName").html();
            var result = convert(name);
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html(result);
        }
    });

    $(".rankBt").unbind("click");
    $(".rankBt").click(function () {
        $("#rankList").find("tr").eq($(".rankBt").index($(this))).remove();
    });
    $(".rankOrder").unbind("input propertychange");
    $(".rankOrder").bind('input propertychange', function () {
        if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "hidden");
        } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html("%");
        } else {
            var name = $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".quotaName").html();
            var result = convert(name);
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html(result);
        }
    })
}

$(".quota").click(function () {
    var quotaName;

    if ($(this).html().substring(0, 1) == "N") {
        quotaName = "<div class='col-md-4'><input class='numOfN form-control' value='10'></div><div style='margin-top: 6px' class='quotaName'>" + $(this).html().substring(1) + "</div>"
    } else {
        quotaName = "<div class='col-md-4' hidden><input class='numOfN form-control'></div><div style='margin-top: 6px' class='col-md-12 quotaName'>" + $(this).html() + "</div>"
    }

    var rankType;
    var whichTab;
    var whichValue;
    var whichButton;
    var whichType;
    if($("#choose").hasClass("active")){
        rankType = "<select class=\"form-control col-md-12 quotaRank\" style=\"padding-left: 5px;padding-right: 5px\">" +
            "<option value='RANK_MAX'>排名最大</option>" +
            "<option value='RANK_MIN'>排名最小</option>" +
            "<option value='RANK_MAX_PERCENT'>排名%最大</option>" +
            "<option value='RANK_MIN_PERCENT'>排名%最小</option>" +
            "<option value='RANK_GREATER'>大于</option>" +
            "<option value='RANK_LESS'>小于</option>" +
            "<option value='RANK_EQUAL'>等于</option>" +
            "</select>" ;
        whichTab =$("#quotaList");

        whichValue=  "<div class=\"row\">" +
            "<div class=\"col-md-10\">" +
            "<input type=\"text\" class=\"form-control quotaNum\" value=\"10\"/>" +
            "</div>" +
            "<div class='percent' style=\"margin-left: -10px;display: inline-block;visibility: hidden;margin-top: 5px\" >%</div>" +
            "</div>" ;
        whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary quotaBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
        whichType='quotaRow';

    }else{
        rankType="<select class=\"form-control col-md-12 rankOrder\" style=\"padding-left: 5px;padding-right: 5px\">" +
            "<option value='ASC_RANK'>由小到大</option>" +
            "<option value='DESC_RANK'>由大到小</option>" +
            "</select>" ;
        whichTab= $("#rankList");
        whichValue=  "<div class=\"row\">" +
            "<div class=\"col-md-9\">" +
            "<input type=\"text\" class=\"form-control quotaWeight\" value=\"1\"/>" +
//                "<input id='ex1' data-slider-id='ex1Slider' type='text' data-slider-min='0' data-slider-max='20' data-slider-step='1' data-slider-value='14'/>"+
            "</div>" +
            "</div>" ;
        whichButton= "<td class=\"col-md-1\"><button class=\"btn  btn-primary rankBt\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
        whichType='rankRow';

    }
    whichTab.append("<tr class="+whichType+">" +
        "<td class=\"col-md-4\"><div class='row'>" + quotaName + "</div></td>" +
        "<td class=\"col-md-2\">" +
        "<div class=\"row\">" +
        rankType+
        "</div>" +
        "</td>" +
        "<td class=\"col-md-2\">" +
        whichValue+
        "</td>" +
        whichButton+
        "</tr>");

    var reg2 = /^[1-9]$/;
    $(".quotaWeight").bind('input propertychange', function () {
        if($(this).val()==""){
            $(this).css("border","2px solid red");
            return false;
        }
        else if (!reg2.test($(this).val())) {
            $(this).css("border","2px solid red");
            return false;
        }else{
            $(this).css("border","1px solid #CCCCCC");
        }
    });
    var reg3 = /^\d{1,20}$/;
    $(".quotaNum").bind('input propertychange', function () {
        if($(this).val()==""){
            $(this).css("border","2px solid red");
            return false;
        }
        else if (!reg3.test($(this).val())) {
            $(this).css("border","2px solid red");
            return false;
        }else{
            $(this).css("border","1px solid #CCCCCC");
        }
    });

    $(".quotaBt").unbind("click");
    $(".quotaBt").click(function () {
        $("#quotaList").find("tr").eq($(".quotaBt").index($(this))).remove();
    });
    $(".quotaRank").unbind("input propertychange");
    $(".quotaRank").bind('input propertychange', function () {
        if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "hidden");
        } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html("%");
        } else {
            var name = $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".quotaName").html();
            var result = convert(name);
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").css("visibility", "visible");
            $("#quotaList").find("tr").eq($(".quotaRank").index($(this))).find(".percent").html(result);
        }
    });

    $(".rankBt").unbind("click");
    $(".rankBt").click(function () {
        $("#rankList").find("tr").eq($(".rankBt").index($(this))).remove();
    });
    $(".rankOrder").unbind("input propertychange");
    $(".rankOrder").bind('input propertychange', function () {
        if ($(this).val() == "RANK_MAX" || $(this).val() == "RANK_MIN") {
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "hidden");
        } else if ($(this).val() == "RANK_MAX_PERCENT" || $(this).val() == "RANK_MIN_PERCENT") {
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html("%");
        } else {
            var name = $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".quotaName").html();
            var result = convert(name);
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").css("visibility", "visible");
            $("#rankList").find("tr").eq($(".rankOrder").index($(this))).find(".percent").html(result);
        }
    });
});
