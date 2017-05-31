/**
 * Created by Byron Dong on 2017/5/13.
 */
function createCandlestickChart(id, candlestickData, volumes) {

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
        var length = data.values.length;
        for (var i = 0; i < length; i++) {
            //刚开始的day天没有均线数据
            if (i < day) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < day; j++) {
                sum = sum + Number(data.values[i - j][1]);
            }
            var tempResult = sum / day;
            tempResult = tempResult.toFixed(2);
            result.push((tempResult));
        }
        return result;
    }

    var data = splitCandlestickData(candlestickData);
    var chart = echarts.init(document.getElementById(id));
    chart.showLoading();

    var option = {
        // backgroundColor: '#eee',
        legend: {
            left: 'center',
            data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
                color: '#000'
            },
            position: function (pos, params, el, elRect, size) {
                var obj = {top: 10};
                obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
                return obj;
            },
            extraCssText: 'width: 170px'
        },
        axisPointer: {
            link: {xAxisIndex: 'all'},
            label: {
                backgroundColor: '#777'
            }
        },
        grid: [
            {
                left: '10%',
                right: '8%',
                height: '50%'
            },
            {
                left: '10%',
                right: '8%',
                top: '63%',
                height: '16%'
            }
        ],
        xAxis: [
            {
                type: 'category',
                data: data.categoryData,
                scale: true,
                axisLine: {onZero: false},
                splitLine: {show: false},
                axisLabel: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax',
                axisPointer: {
                    z: 100
                }
            },
            {
                type: 'category',
                gridIndex: 1,
                data: data.categoryData,
                scale: true,
                axisLine: {onZero: false},
                axisTick: {show: false},
                splitLine: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax',
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            var seriesValue = (params.seriesData[0] || {}).value;
                            return params.value
                                + (seriesValue !== null
                                        ? '\n' + echarts.format.addCommas(seriesValue) : ''
                                );
                        }
                    }
                }
            }
        ],
        yAxis: [
            {
                scale: true,
                splitArea: {
                    show: true
                }
            },
            {
                scale: true,
                gridIndex: 1,
                splitNumber: 2,
                axisLabel: {
                    show: false,
                    formatter: '{value}%'
                },
                axisLine: {show: false},
                axisTick: {show: false}
            }
        ],
        dataZoom: [
            {
                type: 'inside',
                xAxisIndex: [0, 1],
                start: 1,
                end: 100
            },
            {
                show: true,
                xAxisIndex: [0, 1],
                type: 'slider',
                top: '85%',
                start: 1,
                end: 100
            }
        ],
        series: [
            {
                name: '日K',
                type: 'candlestick',
                data: data.values,
                itemStyle: {
                    normal: {
                        borderColor: null,
                        borderColor0: null
                    }
                },
                tooltip: {
                    formatter: function (param) {
                        param = param[0];
                        return [
                            'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
                            'Open: ' + param.data[0] + '<br/>',
                            'Close: ' + param.data[1] + '<br/>',
                            'Lowest: ' + param.data[2] + '<br/>',
                            'Highest: ' + param.data[3] + '<br/>'
                        ].join('');
                    }
                }
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
            },
            {
                name: 'volume',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: volumes,
                tooltip: {
                    formatter: function (param) {
                        param = param[0];
                        return [
                            'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
                            'Volume: ' + param.data[0] + '<br/>'
                        ].join('');
                    }
                }
            }
        ]
    };
    chart.setOption(option, true);
    chart.hideLoading();
    return chart;
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
    lineChart.showLoading();

    function initSeries() {
        var series = [];
        for (var i = 0; i < legend.length; i++) {
            var item = {
                name: legend[i],
                type: 'line',
                smooth: true,
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
                start: 1,
                end: 100
            },
            {
                type: 'slider',
                show: true,
                start: 1,
                end: 100
            }
        ],
        series: lineSeries
    };

    lineChart.setOption(option);
    lineChart.hideLoading();
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
    traceBackChart.showLoading();

    var option = {
        title: {},
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
                color: '#000'
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
                start: 0,
                end: 100
            }, {
                type: 'slider',
                show: true,
                start: 0,
                end: 100,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }
        ],
        series: [
            {
                name: '策略',
                type: 'line',
                data: strategy.values,
                smooth: true
            },
            {
                name: '基准',
                type: 'line',
                data: base.values,
                smooth: true
            }
        ]
    };
    traceBackChart.setOption(option, true);
    traceBackChart.hideLoading();
    return traceBackChart;
}

function createAreaChart(id, areaData, title) {

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

    var data = splitAreaData(areaData);
    var areaChart = echarts.init(document.getElementById(id));
    areaChart.showLoading();

    var option = {
        title: {
            left: 'center',
            text: title
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
                color: '#000'
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: data.categoryData
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '50%'],
            axisLabel: {
                formatter: '{value}%'
            }
        },
        dataZoom: [{
            type: 'inside',
            start: 1,
            end: 100
        }, {
            type: 'slider',
            show: true,
            start: 1,
            end: 100,
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
                name: title,
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
                data: data.values
            }
        ]
    };
    areaChart.setOption(option);
    areaChart.hideLoading();
    return areaChart;
}

function createHistogramChart(id, data, title) {
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

    var datas1 = spliteHistogramData(data[0]);
    var datas2 = spliteHistogramData(data[1]);
    var histogramChart = echarts.init(document.getElementById(id));
    histogramChart.showLoading();

    var option = {
        title:{
            text: title
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
                color: '#000'
            }
        },
        legend: {
            data:['正收益周期数', '负收益周期数']
        },
        xAxis: [
            {
                type: 'category',
                data: datas1.categoryData,
                scale: true,
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: '周期数'
            }
        ],
        dataZoom: [
            {
                type: 'inside',
                show: true,
                start: 0,
                end: 100
            },
            {
                type: 'slider',
                show: true,
                start: 0,
                end: 100
            }
        ],
        series: [
            {
                name:'正收益周期数',
                type:'bar',
                data:datas1.values
            },
            {
                name:'负收益周期数',
                type:'bar',
                data:datas2.values
            }
        ]
    };
    histogramChart.setOption(option);
    histogramChart.hideLoading();
    return histogramChart;
}

function createClickChart(id,data){

    var strData = data.splice(1,2)[0];

    var clickChart = echarts.init(document.getElementById(id));

    var option = {
        series: [{
            animation: true,
            waveAnimation: true,
            type: 'liquidFill',
            data: data,
            color: [ 'rgb(20,142,222)'],
            center: ['75%', '50%'],
            radius: '70%',
            amplitude: 8,
            label: {
                normal: {
                    formatter: function() {
                        return '热搜率'+strData;
                    },
                    textStyle: {
                        fontSize: 22
                    },
                    position: ['50%', '30%']
                }
            },
            outline: {
                itemStyle: {
                    borderWidth: 5,
                    borderColor: [ 'rgb(20,142,222)']
                },
                borderDistance: 0
            },
            itemStyle: {
                normal: {
                    backgroundColor: '#fff'
                }
            }
        }]
    };
    clickChart.setOption(option);
    return clickChart;
}

function createPieChart(id,data,seriesTitle){

    function splitePieData(rawData) {
        var categoryData = [];
        var values = [];

        for (var i = 0; i < rawData.length; i++) {
            var name = rawData[i].splice(0, 1)[0];
            var value = rawData[i].splice(0, 1)[0];
            categoryData.push(name);
            values.push({
                value: value,
                name: name
            });
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var pieData = splitePieData(data);
    var pieChart = echarts.init(document.getElementById(id));

    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:pieData.categoryData
        },
        calculable : true,
        series : [{
                name: seriesTitle,
                type:'pie',
                radius : [30, 110],
                center : ['50%', '50%'],
                roseType : 'area',
                data:pieData.values
            }]
    };

    pieChart.setOption(option);
    return pieChart;
}

function createRadarChart(id,data,legend,paramter){

    function spliteRadarData(rawData,category) {
        var values = [];

        for (var i = 0; i < category.length; i++) {
            values.push({
                name: category[i],
                value: rawData[i]
            });
        }
        return {
            values: values
        };
    }

    var radarData = spliteRadarData(data,legend);
    var radarChart = echarts.init(document.getElementById(id));

    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            x: 'center',
            data:legend
        },
        radar: [
            {
                indicator: (function (){
                    var res = [];
                    for (var i = 0; i <paramter.length; i++) {
                        res.push({text:paramter[i],max:100});
                    }
                    return res;
                })(),
                center: ['50%','50%'],
                radius: 80
            }
        ],
        series: [
            {
                type: 'radar',
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: radarData.values
            }
        ]
    };

    radarChart.setOption(option);
    return radarChart;
}