/**
 * Created by cuihua on 2017/5/14.
 */
function traceback() {
    var isCustomized = $(":radio[name='optionsRadios']:checked").val();
    var jsonData = {
        "startDate": $("#startDate").val(),
        "endDate": $("#endDate").val(),
        "formativePeriod": $("#formativePeriod").val(),
        "holdingPeriod": $("#holdingPeriod").val(),
        "stockPoolCriteria": {
            "stType": $("#stType").val(),
            "blockTypes": $("#blockTypes").val()
        },
        "baseStockName": $("#baseStock").val(),
        "isCustomized": isCustomized,
        "formateAndPickCriteria": {
            "formateType": $("#formativeStrategy").val(),
            "pickType": $("#pickStrategy").val(),
            "rank": $("#rank").val(),
        }
    };

    //
    // alert($("#startDate").val() + "\n" + $("#endDate").val() + "\n" + $("#formativePeriod").val() + "\n" + $("#holdingPeriod").val()
    //     + "\n" + $("#stType").val() + "\n" +$("#blockTypes").val() + "\n" +$("#baseStock").val() + "\n" + isCustomized
    //     + "\n" +$("#formativeStrategy").val() + "\n" +$("#pickStrategy").val() + "\n"+$("#rank").val());

    $.ajax({
        type: "post",
        async: true,
        url: "/traceback",
        contentType:'application/json;charset=UTF-8',
        data: JSON.stringify(jsonData)
    });
}