package com.edu.nju.asi.crawler;


import com.edu.nju.asi.crawler.StoreDataHelper.StoreAreaAndIndustryHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.util.JDBCUtil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Connection connection = JDBCUtil.getConnection();
        Set<String> codes = HelperManager.stockSearchDataHelper.getAllStocksCode().keySet();
        Map<String, List<Stock>> map = new HashMap<>();

        long start = System.currentTimeMillis();
        for (String code : codes) {
            map.put(code, main.getAllStock(code, connection));
        }
        System.out.println("总时间： "+(System.currentTimeMillis()-start));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        try {
            System.out.println(getClass().getClassLoader().getResource("python/CrawingToday.py").getPath());
            String path = getClass().getClassLoader().getResource("python/CrawingToday.py").getPath();
            Process proc = Runtime.getRuntime().exec("python " + path.substring(1));
            proc.waitFor();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Stock> getAllStock(String code, Connection connection) {
        System.out.println("------------enter-----------");
        List<Stock> stocks = new ArrayList<>();
        System.out.println("------------enter1-----------");
        PreparedStatement preparedStatement = null;
        System.out.println("------------ente2-----------");
        String sql = "SELECT * FROM stock WHERE stock.code=?";
        System.out.println("------------enter4-----------");

        try {
            long start = System.currentTimeMillis();
            System.out.println("------------ente5-----------");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            System.out.println("------------ente6-----------");
            ResultSet set = preparedStatement.executeQuery();
            System.out.println("------------ente7-----------");
            while (set.next()) {
                String code1 = set.getString(1);
                Date date = set.getDate(2);
                System.out.println(date.toString());
                LocalDate localDate = LocalDate.parse(date.toString());
                stocks.add(new Stock(set.getString(1), localDate));
            }
            System.out.println("------------out-----------");
            System.out.println(System.currentTimeMillis() - start);
            set.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }
}
