package com.edu.nju.asi.utilities.util;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.StocksPage;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.NumberFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

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
        holder.append(convertBoll(stocks)).append(";");
        holder.append(convertVolume(stocks)).append(";");
        holder.append(convertMACD(stocks)).append(";");
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
     * 转换为布林线所需json data字符串格式
     */
    private static String convertBoll(List<Stock> stocks) throws JsonProcessingException {
        List<String> upper = new ArrayList<>();
        List<String> mid = new ArrayList<>();
        List<String> lower = new ArrayList<>();

        StandardDeviation stdev = new StandardDeviation();


        for (int i = 0; i < stocks.size(); i++) {
            // 默认为20日布林线，当开始没有数据就跳过
            if (i < 20) {
                upper.add("-");
                mid.add("-");
                lower.add("-");
            } else {
                double[] afterAdjCloses = new double[20];
                double sum = 0;
                for (int j = 0; j < 20; j++) {
                    double tempAfterAdjClose = stocks.get(i - j).getAfterAdjClose();
                    sum += tempAfterAdjClose;
                    afterAdjCloses[j] = tempAfterAdjClose;
                }
                double mean = sum / 20;
                double stdev_afterAdjCloses = stdev.evaluate(afterAdjCloses);

                // 因单位问题转化数据量大小
                upper.add(NumberFormat.decimaFormat((mean + 2 * stdev_afterAdjCloses) / 100, 2));
                mid.add(NumberFormat.decimaFormat(mean / 100, 2));
                lower.add(NumberFormat.decimaFormat((mean - 2 * stdev_afterAdjCloses) / 100, 2));
            }
        }

        List<List<String>> result = new ArrayList<>();
        result.add(upper);
        result.add(mid);
        result.add(lower);
        return jsonOfObject(result);
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

    /**
     * 转换为MACD所需json data字符串格式
     */
    private static String convertMACD(List<Stock> stocks) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        // dea的平滑指数, 一般取作2/(N+1) MID = 9
        double k = 2.0 / (9 + 1);

        for (int i = 0; i < stocks.size(); i++) {
            List<String> temp = new ArrayList<>();
            // 默认为26 + 9 = 35日后才开始能计算长线，当开始没有数据就跳过
            if (i < 35) {
                temp.add("-");
                temp.add("-");
                temp.add("-");
                temp.add("-");
            } else {
                double diff = computeDIF(stocks, i);

                //第一天的macd_dea以第一次计算的dif为准
                Double dea = computeDIF(stocks, i - 8);
                for (int j = 1; j < 9; j++) {
                    Double next = computeDIF(stocks, i - j);
                    dea = k*next + (1 - k) * dea;
                }

                double macd = 2 * (diff - dea);

                temp.add(stocks.get(i).getStockID().getDate().toString());
                temp.add(NumberFormat.decimaFormat(macd, 2));
                temp.add(NumberFormat.decimaFormat(diff, 2));
                temp.add(NumberFormat.decimaFormat(dea, 2));
            }
            result.add(temp);
        }

        return jsonOfObject(result);
    }

    public static String convertClickSearch(double nowClickNum) throws JsonProcessingException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(JSON.toJSONString(NumberFormat.decimaFormat(nowClickNum, 4))).append(";");
        buffer.append(JSON.toJSONString("\"" + NumberFormat.percentFormat(nowClickNum, 2) + "\""));
        return buffer.toString();
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

    private static String convertTopSearchedChart(List<StockSearch> topClicks) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        if (topClicks != null) {
            for (StockSearch stockSearch : topClicks) {
                List<String> temp = new ArrayList<>();
                temp.add(stockSearch.getSearchID().getName());
                temp.add(String.valueOf(stockSearch.getClickAmount()));
                result.add(temp);
            }
            return JsonConverter.jsonOfObject(result);
        } else {
            return null;
        }
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

        // holdingDetails, stageDetails
        holder.append(JSON.toJSONString(traceBackInfo.holdingDetails)).append(";");
        holder.append(JSON.toJSONString(traceBackInfo.stageDetails)).append(";");
        System.out.println("numbers3 over");

        // lastSoldStocks
        holder.append(JSON.toJSONString(traceBackInfo.transferDayDetails)).append(";");
        System.out.println("numbers4 over");

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

    public static ReturnPeriod convertReturnPeriod(ReturnPeriod returnPeriod) {
        returnPeriod.winRate = Double.parseDouble(NumberFormat.decimaFormat(returnPeriod.winRate, 4));
        return returnPeriod;
    }

    public static List<String> convertTraceBackNumVal(TraceBackInfo info) {
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
    public static String convertTraceBack(List<CumulativeReturn> list) throws JsonProcessingException {
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
    public static List<String> convertHistogram(ReturnPeriod returnPeriod) throws JsonProcessingException {
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


    /**
     * 计算某只股票的DIF值，短线的时间为12天，长线的时间为26天
     */
    private static double computeDIF(List<Stock> stocks, int endIndex) {
        // 短期——12天，长期——26天
        List<Stock> stockList_short = stocks.subList(endIndex - 11, endIndex + 1);
        List<Stock> stockList_long = stocks.subList(endIndex - 25, endIndex + 1);

        return EMA_AfterAdjClose(stockList_short) - EMA_AfterAdjClose(stockList_long);
    }


    /**
     * 计算某只股票N个交易日的eam
     *
     * @param stockList 需要计算的某只股票N个交易日的详情
     * @return
     */
    private static double EMA_AfterAdjClose(List<Stock> stockList) {
        //平滑指数, 一般取作2/(N+1)
        double k = 2.0 / (stockList.size() + 1);

        //第一天ema等于当天的收盘价
        double ema = stockList.get(0).getAfterAdjClose();
        for (int i = 1; i < stockList.size(); i++) {
            //第二天以后，当天收盘价 * 系数 加上昨天的ema*系数-1
            ema = k * stockList.get(i).getAfterAdjClose() + (1 - k) * ema;
        }

        return ema;
    }

}
