package com.edu.nju.asi.crawler;


import com.edu.nju.asi.crawler.StoreDataHelper.StoreAreaAndIndustryHelper;
import com.edu.nju.asi.crawler.StoreDataHelper.StoreBasicDataHelper;
import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.StockList;
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
        StockDataHelper stockDataHelper = HelperManager.stockDataHelper;
        StockSearchDataHelper stockSearchDataHelper = HelperManager.stockSearchDataHelper;
        StockDao stockDao = new StockDaoImpl();
        List<String> codes = new ArrayList<>();
        Set<String> set = stockSearchDataHelper.getAllStocksCode().keySet();
        Map<String,List<Stock>> data = new HashMap<>();
        for(String code:set){
            codes.add(code);
        }

        long start = System.currentTimeMillis();
        System.out.println("------------------testSql----------------");
            stockDao.getAllStockData(codes);
        System.out.println("------------------testSql时间："+(System.currentTimeMillis()-start)+"----------------");
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
}
