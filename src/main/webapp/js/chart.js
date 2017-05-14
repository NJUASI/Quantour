/**
 * Created by Byron Dong on 2017/5/13.
 */
function createCandlestickChart(id,candlestickData) {
    
    function splitCandlestickData(rawData) {
        var categoryData = [];
        var values = [];
        for(var i = 0;i<rawData.length;i++){
            categoryData.push(rawData[i].splice(0,1)[0]);
            values.push(rawData[i]);
        }

        return {
            categoryData: categoryData,
            values: values
        };
    }
    
    function createMA(day) {
        var result = [];
        var length = data0.values.length;
        for(var i=0;i< length;i++){
            //刚开始的day天没有均线数据
            if(i<day){
                result.push('-');
                continue;
            }
            var sum = 0;
            for(var j = 0;j < day;j++){
                sum = sum +Number(data0.values[i-j][1]);
            }
            var tempResult = sum/day;
            tempResult = tempResult.toFixed(2);
            result.push((tempResult));
        }
        return result;
    }

    var candlestickChart = echarts.init(document.getElementById(id));
    var data0 = splitCandlestickData(candlestickData);

    //K线图和均线图的配置
    var option = {
        title:{},
        tooltip:{
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend:{
            data: ['日K','MA5','MA10','MA20','MA30']
        },
        grid:{
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis:{
            type: 'category',
            data: data0.categoryData,
            scale: true,
            axisLine: {onZero: false},
            splitLine: {show: false}
        },
        yAxis:{
            scale: true,
            boundaryGap: false,
            splitArea: {
                show: true
            }
        },
        dataZoom:[
            {
                type: 'inside',
                xAxisIndex: [0],
                start: 0,
                end: 100
            },
            {
                type: 'slider',
                show: true,
                xAxisIndex: [0],
                start: 0,
                end: 100
            }
        ],
        series:[
            {
                name: '日K',
                type: 'candlestick',
                data: data0.values
            },
            {
                name: 'MA5',
                type: 'line',
                data: createMA(5),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA10',
                type: 'line',
                data: createMA(10),
                smooth: true,
                lineStyle:{
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: createMA(20),
                smooth: true,
                lineStyle: {
                    normal:{opacity:0.5}
                }
            },
            {
                name: 'MA30',
                type: 'line',
                data: createMA(30),
                smooth: true,
                lineStyle:{
                    normal:{opacity: 0.5}
                }
            }
        ]
    };
    candlestickChart.setOption(option);
    return candlestickChart;
}

function createBarChart(id,barData,title,legend){
    function spliteBarData(rawData) {
        var categoryData = [];
        var values = [];

        for(var i = 0;i<rawData.length;i++){
            categoryData.push(rawData[i].splice(0,1)[0]);
            values.push(rawData[i].splice(0,1)[0]);
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var barChart = echarts.init(document.getElementById(id));
    var data0 = spliteBarData(barData);

    var option = {
        title:{
            text: title
        },
        tooltip:{
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            data: legend
        },
        xAxis:{
            type: 'category',
            data: data0.categoryData,
            scale: true,
            boundaryGap: false,
            axisLine: {onZero: false},
            splitLine: {show: false},
            splitNumber: 40
        },
        yAxis: {
            scale: true,
            boundaryGap: false,
            splitArea: {show: false},
            axisLabel:{
                formatter: '{value}万'
            }
        },
        dataZoom:[
            {
                type: 'inside',
                show: true,
                start: 50,
                end: 100
            },
            {
                type: 'slider',
                show: true,
                start: 50,
                end: 100
            }
        ],
        series:[{
            name: legend[0],
            type: 'bar',
            data: data0.values
        }]
    };
    barChart.setOption(option);
    return barChart;
}

function createLineChart(id,lineData,title,legend) {
    function splitLineData(rawData){
        var categoryData = [];
        var values = [];

        for(var i = 0;i<rawData.length;i++){
            categoryData.push(rawData[i].splice(0,1)[0]);
            values.push(rawData[i].splice(0,1)[0]);
        }
        return {
            category: categoryData,
            values: values
        };
    }

    var dataAll = [];
    for(var i = 0; i<lineData.length;i++){
        dataAll.push(splitLineData(lineData[i]));
    }
    var lineChart = echarts.init(document.getElementById(id));

    function initSeries(){
        var series = [];
        for(var i = 0; i<legend.length;i++){
            var item = {
                name: legend[i],
                type: 'line',
                data: dataAll[i].values
            };
            series.push(item);
        }
        return series;
    }

    var lineSeries = initSeries();

    var option = {
        title:{
            text: title
        },
        tooltip:{
            trigger: 'axis',
            axisPointer:{
                type: 'line'
            }
        },
        legend:{
            data: legend
        },
        grid:{
            left: '3%',
            right: '4%',
            bottom : '3%',
            containLabel: true
        },
        xAxis:{
            type: 'category',
            boundaryGap: false,
            data: dataAll[0].categoryData,
            splitNumber: 20
        },
        yAxis:{
            type: 'value',
            scale: true
        },
        dataZoom:[
            {
                type: 'inside',
                start: 50,
                end: 100
            },
            {
                type: 'slider',
                show: false,
                start: 50,
                end: 100
            }
        ],
        series: lineSeries
    };

    lineChart.setOption(option);
    return lineChart;
}