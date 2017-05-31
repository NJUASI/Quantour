package com.edu.nju.asi.utilities.util;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.StocksPage;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.NumberFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/5/13.
 */
public class JsonConverter {

    /**
     * 将数据集合变成json-String
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    public static String jsonOfObject(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }


    /**
     * 将数据集合变成json-String(用于K线图和交易量)
     *
     * @return String 转换后的json
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/5/30
     * @params object 需要转换的对象
     */
    public static String convertOneStock(List<Stock> stocks, boolean isPrivate, double nowClickNum) throws JsonProcessingException {
        StringBuffer holder = new StringBuffer();
        holder.append(convertCandlestick(stocks)).append(";");
        holder.append(convertVolume(stocks)).append(";");
        holder.append(JSON.toJSONString(stocks.get(stocks.size() - 1))).append(";");
        holder.append(JSON.toJSONString(stocks.get(0).getStockID().getDate())).append(";");
        holder.append(JSON.toJSONString(isPrivate)).append(";");
        holder.append(convertClickSearch(nowClickNum)).append(";");
        return holder.toString();
    }

    /**
     * 将数据集合变成json-String(用于K线图)
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    private static String convertCandlestick(List<Stock> stocks) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for (Stock stock : stocks) {
            List<String> temp = new ArrayList<>();
            temp.add(stock.getStockID().getDate().toString());
            temp.add(String.valueOf(stock.getOpen()));
            temp.add(String.valueOf(stock.getClose()));
            temp.add(String.valueOf(stock.getLow()));
            temp.add(String.valueOf(stock.getHigh()));
            result.add(temp);
        }
        return JsonConverter.jsonOfObject(result);
    }

    /**
     * 将数据集合变成json-String(用于交易量)
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    private static String convertVolume(List<Stock> stocks) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for (Stock stock : stocks) {
            List<String> temp = new ArrayList<>();
            temp.add(stock.getStockID().getDate().toString());
            temp.add(String.valueOf(Long.parseLong(stock.getVolume()) / 10000));
            result.add(temp);
        }
        return JsonConverter.jsonOfObject(result);
    }

    private static String convertClickSearch(double nowClickNum){
        // TODO 金玉 个股热搜比例水球 我觉得还需要一个总的点击量数据吧。。。。

        return null;
    }


    /**
     * 将数据集合变成json-String(用于比较的图)
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    public static String convertComparision(Map<LocalDate, Double> map) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for (LocalDate localDate : map.keySet()) {
            List<String> temp = new ArrayList<>();
            temp.add(localDate.toString());
            temp.add(String.valueOf(map.get(localDate)));
            result.add(temp);
        }
        return jsonOfObject(result);
    }

    /**
     * 将股票市场变为JSON字符串传输
     *
     * @param stocksPage 需要的一页股票数据
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/5/18
     */
    public static String convertStockMarket(StocksPage stocksPage) throws JsonProcessingException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(JSON.toJSONString(stocksPage)).append(";");
        buffer.append(convertTopSearchedChart(stocksPage.topClicks));
        return buffer.toString();
    }

    private static String convertTopSearchedChart(List<StockSearch> topClicks) {
        // TODO 金玉 南丁格尔的图需要的数据String

        return null;
    }


    /**
     * 将回测结果变为可读取的表格和图表集合String
     *
     * @param traceBackInfo 回测结果
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/5/18
     */
    public static String convertTraceBackInfo(TraceBackInfo traceBackInfo) throws JsonProcessingException {
        StringBuffer holder = new StringBuffer();

        // traceBackNums
        holder.append(jsonOfObject(convertTraceBackNumVal(traceBackInfo))).append(";");
        System.out.println("numbers1 over");

        // abReturnPeriod, reReturnPeriod, holdingDetails
        holder.append(jsonOfObject(convertReturnPeriod(traceBackInfo.absoluteReturnPeriod))).append(";");
        holder.append(jsonOfObject(convertReturnPeriod(traceBackInfo.relativeReturnPeriod))).append(";");
        System.out.println("numbers2 over");

        holder.append(JSON.toJSONString(traceBackInfo.holdingDetails)).append(";");
        System.out.println("numbers3 over");

        // certainFormates, certainHoldings
//        holder.append(jsonOfObject(traceBackInfo.certainFormates)).append(";");
//        holder.append(jsonOfObject(traceBackInfo.certainHoldings)).append(";");
        System.out.println("numbers4 over");

        System.out.println("numbers all over");

        /*
        加入画图所需的信息
        */
        // json_strategyData, json_baseData
        holder.append(convertTraceBack(traceBackInfo.strategyCumulativeReturn)).append(";");
        holder.append(convertTraceBack(traceBackInfo.baseCumulativeReturn)).append(";");
        System.out.println("charts1 over");

        // json_absoluteHistogramData, json_relativeHistogramData
        holder.append(convertHistogram(traceBackInfo.absoluteReturnPeriod)).append(";");
        holder.append(convertHistogram(traceBackInfo.relativeReturnPeriod)).append(";");
        System.out.println("charts2 over");

//        List<String> formate = JsonConverter.convertExcessAndWin_Chart(traceBackInfo.certainFormates);
//        List<String> holdings = JsonConverter.convertExcessAndWin_Chart(traceBackInfo.certainHoldings);
//        // json_certainFormatesExcessData, json_certainFormatesWinData
//        holder.append(formate.get(0)).append(";");
//        holder.append(formate.get(1)).append(";");
//        System.out.println("charts3 over");
//
//        // json_certainHoldingsExcessData, json_certainHoldingsWinData
//        holder.append(holdings.get(0)).append(";");
//        holder.append(holdings.get(1)).append(";");
//        System.out.println("charts4 over");

        System.out.println("charts all over");


        return holder.toString();
    }

    private static ReturnPeriod convertReturnPeriod(ReturnPeriod returnPeriod) {
        returnPeriod.winRate = Double.parseDouble(NumberFormat.decimaFormat(returnPeriod.winRate, 4));
        return returnPeriod;
    }

    private static List<String> convertTraceBackNumVal(TraceBackInfo info) {
        TraceBackNumVal val = info.traceBackNumVal;
        MaxTraceBack maxTraceBack = info.maxTraceBack;
        List<String> result = new LinkedList<>();

        // 策略部分
        result.add(NumberFormat.percentFormat(val.sumRate, 2));
        result.add(NumberFormat.percentFormat(val.annualizedRateOfReturn, 2));
        result.add(NumberFormat.decimaFormat(val.sharpeRatio, 4));
        result.add(NumberFormat.percentFormat(maxTraceBack.maxStrategyTraceBackRate, 2));
        result.add(NumberFormat.percentFormat(val.returnVolatility, 2));
        result.add(NumberFormat.decimaFormat(val.beta, 4));
        result.add(NumberFormat.decimaFormat(val.alpha, 4));

        // 基准部分
        result.add(NumberFormat.percentFormat(val.baseSumRate, 2));
        result.add(NumberFormat.percentFormat(val.baseAnnualizedRateOfReturn, 2));
        result.add(NumberFormat.decimaFormat(val.baseSharpeRatio, 4));
        result.add(NumberFormat.percentFormat(maxTraceBack.maxBaseTraceBackRate, 2));
        result.add(NumberFormat.percentFormat(val.baseReturnVolatility, 2));
        result.add("/");
        result.add("/");

        // 相对部分
        result.add(NumberFormat.percentFormat(val.sumRate - val.baseSumRate, 2));
        result.add(NumberFormat.percentFormat(val.annualizedRateOfReturn - val.baseAnnualizedRateOfReturn, 2));
        result.add(NumberFormat.decimaFormat(val.sharpeRatio - val.baseSharpeRatio, 4));
        result.add(NumberFormat.percentFormat(maxTraceBack.maxStrategyTraceBackRate - maxTraceBack.maxBaseTraceBackRate, 2));
        result.add(NumberFormat.percentFormat(val.returnVolatility - val.baseReturnVolatility, 2));
        result.add(NumberFormat.decimaFormat(val.beta - val.baseReturnVolatility, 4));
        result.add(NumberFormat.decimaFormat(val.alpha - val.baseReturnVolatility, 4));


        for (String tempStr : result) {
            System.out.println(tempStr);
        }


        return result;
    }

    /**
     * 将数据集合变成json-String(用于回测图)
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    private static String convertTraceBack(List<CumulativeReturn> list) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for (CumulativeReturn cumulativeReturn : list) {
            List<String> temp = new ArrayList<>();
            temp.add(cumulativeReturn.currentDate.toString());
            temp.add(String.valueOf(cumulativeReturn.cumulativeReturn * 100));
            result.add(temp);
        }
        return JsonConverter.jsonOfObject(result);
    }

    /**
     * 将数据集合变成json-String(赢率图)
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    private static List<String> convertExcessAndWin_Chart(List<ExcessAndWinRateDist> list) throws JsonProcessingException {
        List<String> result = new ArrayList<>();
        List<List<String>> excessRate = new ArrayList<>();
        List<List<String>> winRate = new ArrayList<>();
        for (ExcessAndWinRateDist excessAndWinRateDist : list) {
            String relativeCycle = String.valueOf(excessAndWinRateDist.relativeCycle);
            String excess = String.valueOf(excessAndWinRateDist.excessRate * 100);
            String win = String.valueOf(excessAndWinRateDist.winRate * 100);
            List<String> temp1 = new ArrayList<>();
            List<String> temp2 = new ArrayList<>();
            temp1.add(relativeCycle);
            temp1.add(excess);
            excessRate.add(temp1);
            temp2.add(relativeCycle);
            temp2.add(win);
            winRate.add(temp2);
        }
        result.add(JsonConverter.jsonOfObject(excessRate));
        result.add(JsonConverter.jsonOfObject(winRate));
        return result;
    }

    /**
     * 将数据集合变成json-String(用于正负周期的图)
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    private static List<String> convertHistogram(ReturnPeriod returnPeriod) throws JsonProcessingException {
        String[] categories = {"1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", ">12%"};
        Object[] datas1 = new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Object[] datas2 = new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        setData(returnPeriod.positiveNums, datas1);
        setData(returnPeriod.negativeNums, datas2);
        List<String> result = new ArrayList<>();
        List<List<String>> data1 = new ArrayList<>();
        List<List<String>> data2 = new ArrayList<>();

        for (int i = 0; i < categories.length; i++) {
            List<String> temp1 = new ArrayList<>();
            List<String> temp2 = new ArrayList<>();
            temp1.add(categories[i]);
            temp1.add(String.valueOf(datas1[i]));
            data1.add(temp1);
            temp2.add(categories[i]);
            temp2.add(String.valueOf(datas2[i]));
            data2.add(temp2);
        }
        result.add(JsonConverter.jsonOfObject(data1));
        result.add(JsonConverter.jsonOfObject(data2));
        return result;
    }

    /**
     * 数据填充
     *
     * @return String 转换后的json
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     */
    private static void setData(Map<Double, Integer> map, Object[] data) {
        for (Double key : map.keySet()) {
            int index = key.intValue() - 1;
            if (index >= 11) {
                int sum = (int) data[11];
                sum = sum + map.get(key);
                data[11] = sum;
            } else if (index >= 1) {
                data[index] = map.get(key);
            }
        }
    }

}
