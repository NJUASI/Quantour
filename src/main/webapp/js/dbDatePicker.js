/**
 * Created by 61990 on 2017/6/9.
 */
var today = new Date();

today.setTime(today.getTime() - 24 * 60 * 60 * 1000);
var yesterday = new Date();
yesterday.setTime(today.getTime() - 29*24 * 60 * 60 * 1000);

var endTime = today.getFullYear() + "-";
var startTime = yesterday.getFullYear() + "-";

var month = today.getMonth() + 1;
var dayOfMonth = today.getDate();

if (month < 10) {
    endTime += "0" + month;
} else {
    endTime += month;
}
if (dayOfMonth < 10) {
    endTime += "-0" + dayOfMonth;
} else {
    endTime += "-" + dayOfMonth;
}

month = yesterday.getMonth() + 1;
dayOfMonth = yesterday.getDate();
if (month < 10) {
    startTime += "0" + month;
} else {
    startTime += month;
}
if (dayOfMonth < 10) {
    startTime += "-0" + dayOfMonth;
} else {
    startTime += "-" + dayOfMonth;
}

$("#datetimeStart>input").attr('value', startTime);
$("#datetimeEnd>input").attr('value', endTime);

$("#datetimeStart").datetimepicker({
    daysOfWeekDisabled: [0,6],
    format: 'yyyy-mm-dd',
    minView: 'month',
    language: 'zh-CN',
    autoclose: true,
    startDate: new Date(2014 - 01 - 01),
    endDate: new Date()
}).on("click", function () {
    $("#datetimeStart").datetimepicker('setEndDate', $("#datetimeEnd>input").val())
});
$("#datetimeEnd").datetimepicker({
    daysOfWeekDisabled: [0,6],
    format: 'yyyy-mm-dd',
    minView: 'month',
    language: 'zh-CN',
    autoclose: true,
    endDate: today
}).on("click", function () {
    $("#datetimeEnd").datetimepicker("setStartDate", $("#datetimeStart>input").val())
});
