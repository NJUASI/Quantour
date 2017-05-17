package com.edu.nju.asi.utilities.util;

import com.edu.nju.asi.infoCarrier.traceBack.CumulativeReturn;
import com.edu.nju.asi.infoCarrier.traceBack.ExcessAndWinRateDist;
import com.edu.nju.asi.infoCarrier.traceBack.ReturnPeriod;
import com.edu.nju.asi.model.Stock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/5/13.
 */
public class JsonConverter {

    /**
     *
     * 将数据集合变成json-String
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    public static String jsonOfObject(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     *
     * 将数据集合变成json-String(用于K线图)
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    public static String convertCandlestick(List<Stock> stocks) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for(Stock stock : stocks){
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
     *
     * 将数据集合变成json-String(用于交易量)
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    public static String convertVolume(List<Stock> stocks) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for(Stock stock : stocks){
            List<String> temp = new ArrayList<>();
            temp.add(stock.getStockID().getDate().toString());
            temp.add(String.valueOf(Long.parseLong(stock.getVolume())/10000));
            result.add(temp);
        }
        return JsonConverter.jsonOfObject(result);
    }

    /**
     *
     * 将数据集合变成json-String(用于回测图)
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    public static String convertTraceBack(List<CumulativeReturn> list) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for(CumulativeReturn cumulativeReturn : list){
            List<String> temp =  new ArrayList<>();
            temp.add(cumulativeReturn.currentDate.toString());
            temp.add(String.valueOf(cumulativeReturn.cumulativeReturn*100));
            result.add(temp);
        }

        return JsonConverter.jsonOfObject(result);
    }

    /**
     *
     * 将数据集合变成json-String(赢率图)
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    public static List<String> convertExcessAndWin(List<ExcessAndWinRateDist> list) throws JsonProcessingException {
        List<String> result =  new ArrayList<>();
        List<List<String>> excessRate = new ArrayList<>();
        List<List<String>> winRate = new ArrayList<>();
        for(ExcessAndWinRateDist excessAndWinRateDist: list){
            String relativeCycle = String.valueOf(excessAndWinRateDist.relativeCycle);
            String excess= String.valueOf(excessAndWinRateDist.excessRate*100);
            String win = String.valueOf(excessAndWinRateDist.winRate*100);
            List<String> temp1 = new ArrayList<>();
            List<String> temp2 =  new ArrayList<>();
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
     *
     * 将数据集合变成json-String(用于正负周期的图)
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    public static List<String> convertHistogram(ReturnPeriod returnPeriod) throws JsonProcessingException {
        String[] categories = {"1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", ">12%"};
        Object[] datas1 = new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Object[] datas2 = new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        setData(returnPeriod.positiveNums,datas1);
        setData(returnPeriod.negativeNums,datas2);
        List<String> result =  new ArrayList<>();
        List<List<String>> data1 = new ArrayList<>();
        List<List<String>> data2 = new ArrayList<>();

        for(int i = 0;i< categories.length;i++){
            List<String> temp1  = new ArrayList<>();
            List<String> temp2 =  new ArrayList<>();
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
     *
     * 将数据集合变成json-String(用于比较的图)
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    public static String convertComparision(Map<LocalDate,Double> map) throws JsonProcessingException {
        List<List<String>> result = new ArrayList<>();

        for(LocalDate localDate : map.keySet()){
            List<String> temp = new ArrayList<>();
            temp.add(localDate.toString());
            temp.add(String.valueOf(map.get(localDate)));
            result.add(temp);
        }
        return JsonConverter.jsonOfObject(result);
    }

    /**
     *
     * 数据填充
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/14
     * @params object 需要转换的对象
     * @return String 转换后的json
     */
    private static void setData(Map<Double,Integer> map, Object[]data){
        for(Double key:map.keySet()){
            int index = key.intValue()-1;
            if(index>=11){
                int sum = (int)data[11];
                sum = sum+map.get(key);
                data[11] = sum;
            } else if(index>=1){
                data[index] = map.get(key);
            }
        }
    }
}
