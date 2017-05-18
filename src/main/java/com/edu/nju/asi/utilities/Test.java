package com.edu.nju.asi.utilities;

import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockID;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.enums.Market;
import com.edu.nju.asi.utilities.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/5/18.
 */
public class Test {

    public static void main(String[] args) {
        StockSearch stockSearch1 = new StockSearch(new SearchID("000001", "上海神话", Market.SH), "jklh");
        StockSearch stockSearch2 = new StockSearch(new SearchID("000001", "上海神话", Market.SH), "jklh");
        List<StockSearch> list = new ArrayList<>();
        list.add(stockSearch1);
        list.add(stockSearch2);
        String searilize = "";
        try {
            searilize = JsonConverter.jsonOfObject(list);
            System.out.println(searilize);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode node = null;
        try {
            node = new ObjectMapper().readTree(searilize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < node.size(); i++){
            System.out.println(node.get(i).get("searchID").get("code"));
        }

//        StockSearch stockSearch1 = null;
//        try {
//            stockSearch1 = new ObjectMapper().readValue(searilize,.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(stockSearch1.getSearchID().getName());

    }

}
