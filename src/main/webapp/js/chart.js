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
                        color: '#ef232a',
                        color0: '#14b143',
                        borderColor: '#ef232a',
                        borderColor0: '#14b143'
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
                },
                itemStyle: {
                    normal: {
                        color: function (params) {
                            var colorList;
                            if (data.values[params.dataIndex][1] > data.values[params.dataIndex][0]) {
                                colorList = '#ef232a';
                            } else {
                                colorList = '#14b143';
                            }
                            return colorList;
                        }
                    }
                }
            }
        ]
    };
    chart.setOption(option, true);
    chart.hideLoading();
    return chart;
}

function createCandlestick(id, candlestickData) {
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
            borderWidth: 10,
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
        xAxis: [{
            type: 'category',
            data: data.categoryData,
            scale: true,
            axisLine: {onZero: false},
            splitLine: {show: false},
            axisLabel: {show: true},
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax',
            axisPointer: {
                z: 100
            }
        }],
        yAxis: [{
            scale: true,
            splitArea: {
                show: false
            },
            splitNumber: 10,
            boundaryGap: ['10%', '10%']
        }],
        dataZoom: [{
            type: 'inside',
            start: 10,
            end: 60
        }, {
            show: false,
            type: 'slider',
            top: '85%',
            start: 10,
            end: 60
        }],
        series: [{
            name: '日K',
            type: 'candlestick',
            data: data.values,
            itemStyle: {
                normal: {
                    color: '#ef232a',
                    color0: '#14b143',
                    borderColor: '#ef232a',
                    borderColor0: '#14b143'
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
        }, {
            name: 'MA5',
            type: 'line',
            data: createMA(5),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }, {
            name: 'MA10',
            type: 'line',
            data: createMA(10),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }, {
            name: 'MA20',
            type: 'line',
            data: createMA(20),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }, {
            name: 'MA30',
            type: 'line',
            data: createMA(30),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }]
    };
    chart.setOption(option);
    chart.hideLoading();
    return chart;
}

function createVolume(id, volumes) {

    function splitVolumeData(rawData) {
        var categoryData = [];
        var values = [];
        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i][0]);
            values.push(rawData[i][1]);
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    var data = splitVolumeData(volumes);
    var chart = echarts.init(document.getElementById(id));
    chart.showLoading();

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 10,
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
        xAxis: [{
            type: 'category',
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
        }],
        yAxis: [{
            scale: true,
            splitNumber: 2,
            axisLabel: {
                show: true
            },
            axisLine: {show: false},
            axisTick: {show: false}
        }],
        dataZoom: [{
            type: 'inside',
            start: 10,
            end: 60
        }, {
            show: true,
            type: 'slider',
            top: '85%',
            start: 10,
            end: 60
        }],
        series: [{
            name: 'volume',
            type: 'bar',
            data: data.values,
            tooltip: {
                formatter: function (param) {
                    param = param[0];
                    return [
                        'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
                        'Volume: ' + param.data[0] + '<br/>'
                    ].join('');
                }
            },
            itemStyle: {
                normal: {
                    color: function (params) {
                        var colorList;
                        if (data.values[params.dataIndex][1] > data.values[params.dataIndex][0]) {
                            colorList = '#ef232a';
                        } else {
                            colorList = '#14b143';
                        }
                        return colorList;
                    }
                }
            }
        }]
    };
    chart.setOption(option, true);
    chart.hideLoading();
    return chart;
}

function createMACD(id, datas) {
    function splitMACDData(rawData) {
        var macd = [];
        var dif = [];
        var dea = [];
        var category = [];
        for (var i = 0; i < rawData.length; i++) {
            category.push(rawData[i][0]);
            macd.push(rawData[i][1]);
            dif.push(rawData[i][2]);
            dea.push(rawData[i][3]);
        }
        return {
            categoryData: category,
            macdData: macd,
            difData: dif,
            deaData: dea
        };
    }

    var allData = splitMACDData(datas);
    var macdChart = echarts.init(document.getElementById(id));
    macdChart.showLoading();

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 10,
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
        xAxis: [{
            type: 'category',
            data: allData.categoryData,
            axisLabel: {show: true}
        }],
        yAxis: [{
            splitNumber: 4,
            axisLine: {onZero: false},
            axisTick: {show: false},
            splitLine: {show: true},
            axisLabel: {show: true}
        }],
        dataZoom: [{
            type: 'inside',
            start: 10,
            end: 60
        }, {
            type: 'slider',
            show: true,
            start: 10,
            end: 60
        }],
        series: [{
            name: 'MACD',
            type: 'bar',
            barWidth: 1,
            data: allData.macdData,
            itemStyle: {
                normal: {
                    color: function (params) {
                        var colorList;
                        if (params.data >= 0) {
                            colorList = '#ef232a';
                        } else {
                            colorList = '#14b143';
                        }
                        return colorList;
                    }
                }
            }
        }, {
            name: 'DIF',
            type: 'line',
            smooth: true,
            data: allData.difData
        }, {
            name: 'DEA',
            type: 'line',
            smooth: true,
            data: allData.deaData
        }]
    };
    macdChart.setOption(option);
    macdChart.hideLoading();
    return macdChart;
}

function createBULL(id, candlestickData, upData, midData, lowData) {
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

    var data = splitCandlestickData(candlestickData);
    var chart = echarts.init(document.getElementById(id));
    chart.showLoading();

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            backgroundColor: 'rgba(245, 245, 245, 0.8)',
            borderWidth: 10,
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
        xAxis: [{
            type: 'category',
            data: data.categoryData,
            scale: true,
            axisLine: {onZero: false},
            splitLine: {show: false},
            axisLabel: {show: true},
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax',
            axisPointer: {
                z: 100
            }
        }],
        yAxis: [{
            scale: true,
            splitArea: {
                show: false
            },
            splitNumber: 10,
            boundaryGap: ['10%', '10%']
        }],
        dataZoom: [{
            type: 'inside',
            start: 10,
            end: 60
        }, {
            show: false,
            type: 'slider',
            top: '85%',
            start: 10,
            end: 60
        }],
        series: [{
            name: '日K',
            type: 'candlestick',
            data: data.values,
            itemStyle: {
                normal: {
                    color: '#ef232a',
                    color0: '#14b143',
                    borderColor: '#ef232a',
                    borderColor0: '#14b143'
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
        }, {
            name: 'UPPER',
            type: 'line',
            data: upData,
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }, {
            name: 'MID',
            type: 'line',
            data: midData,
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }, {
            name: 'LOWER',
            type: 'line',
            data: lowData,
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }]
    };
    chart.setOption(option, true);
    chart.hideLoading();
    return chart;
}

function createMACDChart(id, datas) {
    function splitMACDData(rawData) {
        var macd = [];
        var dif = [];
        var dea = [];
        var category = [];
        for (var i = 0; i < rawData.length; i++) {
            category.push(rawData[i][0]);
            macd.push(rawData[i][1]);
            dif.push(rawData[i][2]);
            dea.push(rawData[i][3]);
        }
        return {
            categoryData: category,
            macdData: macd,
            difData: dif,
            deaData: dea
        };
    }

    var allData = splitMACDData(datas);
    var macdChart = echarts.init(document.getElementById(id));
    macdChart.showLoading();

    var option = {
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
        xAxis: [{
            type: 'category',
            data: allData.categoryData,
            axisLabel: {show: true}
        }],
        yAxis: [{
            splitNumber: 4,
            axisLine: {onZero: false},
            axisTick: {show: false},
            splitLine: {show: false},
            axisLabel: {show: true}
        }],
        dataZoom: [{
            type: 'inside',
            start: 1,
            end: 100
        }, {
            type: 'slider',
            show: true,
            start: 1,
            end: 100
        }],
        series: [{
            name: 'MACD',
            type: 'bar',
            barWidth: 1,
            data: allData.macdData,
            itemStyle: {
                normal: {
                    color: function (params) {
                        var colorList;
                        if (params.data >= 0) {
                            colorList = '#ef232a';
                        } else {
                            colorList = '#14b143';
                        }
                        return colorList;
                    }
                }
            }
        }, {
            name: 'DIF',
            type: 'line',
            smooth: true,
            data: allData.difData
        }, {
            name: 'DEA',
            type: 'line',
            smooth: true,
            data: allData.deaData
        }]
    };
    macdChart.setOption(option);
    macdChart.hideLoading();
    return macdChart;
}

function createBullChart(id, candlestickData, upData, midData, lowData) {
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

    var data = splitCandlestickData(candlestickData);
    var chart = echarts.init(document.getElementById(id));
    chart.showLoading();

    var option = {
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
        xAxis: [{
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
        }],
        yAxis: [{
            scale: true,
            splitArea: {
                show: true
            }
        }],
        dataZoom: [
            {
                type: 'inside',
                start: 1,
                end: 100
            },
            {
                show: true,
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
                        color: '#ef232a',
                        color0: '#14b143',
                        borderColor: '#ef232a',
                        borderColor0: '#14b143'
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
            }, {
                name: 'UPPER',
                type: 'line',
                data: upData,
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            }, {
                name: 'MID',
                type: 'line',
                data: midData,
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            }, {
                name: 'LOWER',
                type: 'line',
                data: lowData,
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            }]
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
        title: {
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
            data: ['正收益周期数', '负收益周期数']
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
                name: '正收益周期数',
                type: 'bar',
                data: datas1.values
            },
            {
                name: '负收益周期数',
                type: 'bar',
                data: datas2.values
            }
        ]
    };
    histogramChart.setOption(option);
    histogramChart.hideLoading();
    return histogramChart;
}

function createClickChart(id, data1, percentString) {

    var clickChart = echarts.init(document.getElementById(id));
    clickChart.showLoading();
    var data = [];
    data.push(data1);
    var strData = [];
    strData.push(percentString);


    var option = {
        series: [{
            animation: true,
            waveAnimation: true,
            type: 'liquidFill',
            data: data,
            color: ['rgb(20,142,222)'],
            center: ['75%', '50%'],
            radius: '70%',
            amplitude: 8,
            label: {
                normal: {
                    formatter: function () {
                        return '热搜率' + strData;
                    },
                    textStyle: {
                        fontSize: 17
                    },
                    position: ['50%', '30%']
                }
            },
            outline: {
                itemStyle: {
                    borderWidth: 5,
                    borderColor: ['rgb(20,142,222)']
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
    clickChart.hideLoading();
    return clickChart;
}

function createPieChart(id, data, seriesTitle) {

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
    pieChart.showLoading();

    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: 'center',
            y: 'bottom',
            data: pieData.categoryData
        },
        calculable: true,
        series: [{
            name: seriesTitle,
            type: 'pie',
            radius: [30, 110],
            center: ['50%', '50%'],
            roseType: 'area',
            data: pieData.values
        }]
    };

    pieChart.setOption(option);
    pieChart.hideLoading();
    return pieChart;
}

function createRadarChart(id, data, legend, paramter) {

    function spliteRadarData(rawData, category) {
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

    var radarData = spliteRadarData(data, legend);
    var radarChart = echarts.init(document.getElementById(id));
    radarChart.showLoading();

    var option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            x: 'center',
            data: legend,
            show: false
        },
        radar: [
            {
                indicator: (function () {
                    var res = [];
                    for (var i = 0; i < paramter.length; i++) {
                        res.push({text: paramter[i], max: 100});
                    }
                    return res;
                })(),
                center: ['50%', '50%'],
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
    radarChart.hideLoading();
    return radarChart;
}

//待定 TODO 董金玉
function createRelationChart(id, data) {

    function get_nodes(data) {
        var nodes = [];
        for (var nodes_i in data) {
            nodes.push(
                {
                    'name': data[nodes_i].node
                }
            );
        }
        return nodes;
    }

    function get_links(data) {
        var links = [];
        for (var nodes_i in data) {
            var node = data[nodes_i].node;
            var endpoint = data[nodes_i].endpoint;
            var service = data[nodes_i].service;
            for (var service_i in endpoint) {
                links.push({
                    'source': node,
                    'target': endpoint[service_i],
                    'label': {
                        'normal': {
                            'show': false,
                            'textStyle': {
                                'fontSize': 5
                            },
                            'formatter': service
                        }
                    },
                    'lineStyle': {
                        'normal': {
                            'curveness': 0.1
                        }
                    }
                })
            }
        }

        //调整线的格式
        for (var i = 0, len1 = links.length; i < len1; i++) {
            for (var j = i, len2 = len1 - 1; j < len2; j++) {
                if (links[i].source == links[j].target) {
                    links[j].lineStyle.normal.curveness = -0.1;
                }
            }
        }
        return links;
    }

    var option = {
        title: {
            text: '调用关系demo'
        },
        // tooltip: {
        //formatter: '调用方法'
        // },
        animationDurationUpdate: 1500,
        animationEasing: 'cubicOut',
        animationEasingUpdate: 'quinticInOut',
        series: [
            {
                type: 'graph',
                layout: 'circular',
                // layout:'none',
                focusNodeAdjacency: true,
                legendHoverLink: true,
                hoverAnimation: true,
                symbolSize: 50,
                //edgeSymbolSize: 50,
                roam: true,
                symbol: "roundRect",
                label: {
                    normal: {
                        show: true,
                    }
                },
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 15],
                edgeLabel: {
                    normal: {
                        textStyle: {
                            fontSize: 20
                        }
                    }
                },
                data: get_nodes(data),
                links: get_links(data),
                lineStyle: {
                    normal: {
                        opacity: 0.9,
                        width: 2,
                        curveness: 0,
                        type: 'dashed'
                    }
                }
            },
        ]
    };
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(option);

//添加点击事件
    myChart.on('click', function (params) {
        // 弹窗打印数据的名称
        console.log(params);
        if (params.dataType == "node") {
            alert("机器属性：" + params.name);
        } else if (params.dataType == "edge") {
            alert("调用方法：" + params.data.label.normal.formatter);
        }
    });
}

function createWithoutDecorationChart(id, data1, data2) {
    function splitSeriesData(rawData) {
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

    var series1 = splitSeriesData(data1);
    var series2 = splitSeriesData(data2);
    var chart = echarts.init(document.getElementById(id));
    chart.showLoading();

    var option = {
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: series1.categoryData,
            splitNumber: 10,
            show: false
        },
        yAxis: {
            type: 'value',
            scale: true,
            show: false
        },
        series: [
            {
                name: 'series1',
                type: 'line',
                data: series1.values,
                smooth: true
            },
            {
                name: 'series2',
                type: 'line',
                data: series2.values,
                smooth: true
            }
        ]
    };
    chart.setOption(option, true);
    chart.hideLoading();
    return chart;
}