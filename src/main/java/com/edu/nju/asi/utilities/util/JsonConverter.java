package com.edu.nju.asi.utilities.util;

import com.edu.nju.asi.model.Stock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/13.
 */
public class JsonConverter {

    public static String jsonOfObject(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static String convertCandlestick(List<Stock> stocks) throws JsonProcessingException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        List<List<String>> result = new ArrayList<>();

        for(Stock stock : stocks){
            List<String> temp = new ArrayList<>();
            temp.add(dateFormat.format(stock.getStockID().getDate()));
            temp.add(String.valueOf(stock.getOpen()));
            temp.add(String.valueOf(stock.getClose()));
            temp.add(String.valueOf(stock.getLow()));
            temp.add(String.valueOf(stock.getHigh()));
            result.add(temp);
        }
        return JsonConverter.jsonOfObject(result);
    }

    public static String convertVolume(List<Stock> stocks) throws JsonProcessingException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        List<List<String>> result = new ArrayList<>();

        for(Stock stock : stocks){
            List<String> temp = new ArrayList<>();
            temp.add(dateFormat.format(stock.getStockID().getDate()));
            temp.add(stock.getVolume());
            result.add(temp);
        }
        return JsonConverter.jsonOfObject(result);
    }

}
