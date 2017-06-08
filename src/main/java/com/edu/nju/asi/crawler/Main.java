package com.edu.nju.asi.crawler;


import com.edu.nju.asi.crawler.StoreDataHelper.StoreAreaAndIndustryHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.utilities.util.JDBCUtil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public class Main {

    public static void main(String[] args) {
       Main main =  new Main();
       main.getAllStock(HelperManager.stockSearchDataHelper.getAllStocksCode().keySet());

    }

    public void test(){
        try {
            System.out.println(getClass().getClassLoader().getResource("python/CrawingToday.py").getPath());
            String path = getClass().getClassLoader().getResource("python/CrawingToday.py").getPath();
            Process proc = Runtime.getRuntime().exec("python "+path.substring(1));
            proc.waitFor();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getAllStock(Set<String> codes){
        System.out.println("------------enter-----------");
        Connection connection = JDBCUtil.getConnection();
        System.out.println("------------enter1-----------");
        PreparedStatement preparedStatement = null;
        System.out.println("------------ente2-----------");
        String sql = "SELECT * FROM stock WHERE stock.code=?";
        System.out.println("------------enter4-----------");

        try {
            long start = System.currentTimeMillis();
            for(String code:codes) {
                System.out.println("------------ente5-----------");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, code);
                System.out.println("------------ente6-----------");
                ResultSet set = preparedStatement.executeQuery();
                System.out.println("------------ente7-----------");
                while (set.next()) {
                    System.out.println(set.getString(1) + "---" + set.getObject(2).toString());
                }
                System.out.println("------------out-----------");
            }
            System.out.println(System.currentTimeMillis()-start);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(preparedStatement,connection);
        }
    }
}
