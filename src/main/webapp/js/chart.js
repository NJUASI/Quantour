/**
 * Created by Byron Dong on 2017/5/13.
 */
function createCandlestickChart(id, candlestickData) {

    function splitCandlestickData(rawData) {
        var categoryData = [];
        var values = [];
        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
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
        for (var i = 0; i < length; i++) {
            //刚开始的day天没有均线数据
            if (i < day) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < day; j++) {
                sum = sum + Number(data0.values[i - j][1]);
            }
            var tempResult = sum / day;
            tempResult = tempResult.toFixed(2);
            result.push((tempResult));
        }
        return result;
    }

    var candlestickChart = echarts.init(document.getElementById(id));
    var data0 = splitCandlestickData(candlestickData);

    //K线图和均线图的配置
    var option = {
        title: {},
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis: {
            type: 'category',
            data: data0.categoryData,
            scale: true,
            axisLine: {onZero: false},
            splitLine: {show: false}
        },
        yAxis: {
            scale: true,
            boundaryGap: false,
            splitArea: {
                show: true
            }
        },
        dataZoom: [
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
        series: [
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
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: createMA(20),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA30',
                type: 'line',
                data: createMA(30),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            }
        ]
    };
    candlestickChart.setOption(option);
    return candlestickChart;
}

function createVolumeChart(id, barData, title,d1,d2) {
    function spliteBarData(rawData) {
        var categoryData = [];
        var values = [];

        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i].splice(0, 1)[0]);
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var barChart = echarts.init(document.getElementById(id));
    // var data0 = spliteBarData(barData);

    var option = {
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            data: '交易量'
        },
        xAxis: {
            type: 'category',
            data: d1,
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
            axisLabel: {
                formatter: '{value}万'
            }
        },
        dataZoom: [
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
        series: [{
            name: '交易量',
            type: 'bar',
            data: d2
        }]
    };
    barChart.setOption(option);
    return barChart;
}

function createLineChart(id, lineData, title, legend) {
    function splitLineData(rawData) {
        var categoryData = [];
        var values = [];

        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i].splice(0, 1)[0]);
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var dataAll = [];
    for (var i = 0; i < lineData.length; i++) {
        dataAll.push(splitLineData(lineData[i]));
    }
    var lineChart = echarts.init(document.getElementById(id));

    function initSeries() {
        var series = [];
        for (var i = 0; i < legend.length; i++) {
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
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            data: legend
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dataAll[0].categoryData,
            splitNumber: 20
        },
        yAxis: {
            type: 'value',
            scale: true
        },
        dataZoom: [
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

function createTraceBackChart(id, strategyData, baseData, legend, startX, endX) {
    function splitTraceBackData(rawData) {
        var categoryData = [];
        var values = [];

        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i].splice(0, 1)[0]);
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var strategy = splitTraceBackData(strategyData);
    var base = splitTraceBackData(baseData);
    var traceBackChart = echarts.init(document.getElementById(id));

    var option = {
        title: {},
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            data: legend
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: strategy.categoryData,
            splitNumber: 10
        },
        yAxis: {
            type: 'value',
            scale: true,
            axisLabel: {
                formatter: '{value}%'
            }
        },
        dataZoom: [
            {
                type: 'inside',
                start: 1,
                end: 100
            },
            {
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            },
            {
                type: 'slider',
                show: true,
                start: 1,
                end: 100
            }
        ],
        series: [
            {
                name: '策略',
                type: 'line',
                data: strategy.values,
                smooth: true,
            },
            {
                name: '基准',
                type: 'line',
                data: base.values,
                smooth: true
            }
        ]
    };

    traceBackChart.setOption(option);
    return traceBackChart;
}

function createAreaChart(id, areaData) {

    function splitAreaData(rawData) {
        var categoryData = [];
        var values = [];

        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i].splice(0, 1)[0]);
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var date = splitAreaData(areaData);
    var areaChart = echarts.init(document.getElementById(id));

    var option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '策略胜率',
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 10
        }, {
            start: 0,
            end: 10,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [
            {
                name: '数据',
                type: 'line',
                smooth: true,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: data
            }
        ]
    };

    areaChart.setOption(option);
    return areaChart;
}

function createHistogramChart(id, data1, data2, title, legend) {
    function spliteHistogramData(rawData) {
        var categoryData = [];
        var values = [];

        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i].splice(0, 1)[0]);
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var datas1 = spliteHistogramData(data1);
    var datas2 = spliteHistogramData(data2);
    var histogramChart = echarts.init(document.getElementById(id));

    var option = {
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            data: legend
        },
        xAxis: {
            type: 'category',
            data: datas1.categoryData,
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
            axisLabel: {
                // formatter: '{value}万'
            }
        },
        dataZoom: [
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
        series: [
            {
                name: '正收益周期',
                type: 'bar',
                data: datas1.values
            },
            {
                name: '负收益周期',
                type: 'bar',
                data: datas2.values
            }
        ]
    };
    histogramChart.setOption(option);
    return histogramChart;
}