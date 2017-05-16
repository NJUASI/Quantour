package com.edu.nju.asi.spider;

import com.csvreader.CsvReader;
import com.edu.nju.asi.spider.Model.BaseStock;
import com.edu.nju.asi.spider.Model.NormalStock;
import com.edu.nju.asi.utilities.util.JDBCUtil;
import com.fasterxml.jackson.databind.deser.Deserializers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by Harvey on 2017/5/16.
 *
 * 将csv文件转存到数据库
 */
public class DownloadDataHelper {

    public static void main(String[] args) {
        DownloadDataHelper helper = new DownloadDataHelper();
        helper.baseStockStore("G:/Quantour/baseStocks/000001.csv");
        helper.baseStockStore("G:/Quantour/baseStocks/399001.csv");
        helper.baseStockStore("G:/Quantour/baseStocks/399300.csv");
    }

    /**
     * 保存一条普通股票数据
     * @param path
     */
    public void normalStockStore(String path) {
        try {
            CsvReader reader = new CsvReader(path);
            //读取表头
            reader.readHeaders();
            //逐条读取记录
            while(reader.readRecord()){
                NormalStock normalStock = new NormalStock();
                normalStock.setCode(reader.get(1));
                normalStock.setDate(reader.get(0));
                normalStock.setOpen(reader.get(6));
                normalStock.setClose(reader.get(3));
                normalStock.setHigh(reader.get(4));
                normalStock.setLow(reader.get(5));
                normalStock.setPreClose(reader.get(7));
                normalStock.setFluctuation(reader.get(8));
                normalStock.setChangeRate(reader.get(9));
                normalStock.setVolume(reader.get(11));
                normalStock.setAmount(reader.get(12));
                normalStock.setTurnOverRate(reader.get(10));
                normalStock.setMarketCap(reader.get(13));
                normalStock.setMarketEquity(reader.get(14));
                addNormalStock(normalStock);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存一条基准股票数据
     * @param path
     */
    public void baseStockStore(String path){
        try {
            CsvReader reader = new CsvReader(path);
            //读取表头
            reader.readHeaders();
            //逐条读取记录
            while(reader.readRecord()){
                BaseStock baseStock = new BaseStock();
                baseStock.setCode(reader.get(1));
                baseStock.setDate(reader.get(0));
                baseStock.setOpen(reader.get(6));
                baseStock.setClose(reader.get(3));
                baseStock.setHigh(reader.get(4));
                baseStock.setLow(reader.get(5));
                baseStock.setPreClose(reader.get(7));
                baseStock.setFluctuation(reader.get(8));
                baseStock.setChangeRate(reader.get(9));
                baseStock.setVolume(reader.get(10));
                baseStock.setAmount(reader.get(11));
                addBaseStock(baseStock);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean addBaseStock(BaseStock baseStock) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO base_stock(code, date, open, close, high, low, preClose,fluctuation, changeRate, volume, amount)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, baseStock.getCode());
            preparedStatement.setObject(2, baseStock.getDate());
            preparedStatement.setDouble(3, baseStock.getOpen());
            preparedStatement.setDouble(4, baseStock.getClose());
            preparedStatement.setDouble(5, baseStock.getHigh());
            preparedStatement.setDouble(6, baseStock.getLow());
            preparedStatement.setDouble(7, baseStock.getPreClose());
            preparedStatement.setDouble(8, baseStock.getFluctuation());
            preparedStatement.setDouble(9, baseStock.getChangeRate());
            preparedStatement.setDouble(10, baseStock.getVolume());
            preparedStatement.setDouble(11, baseStock.getAmount());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("基准股票写入成功");
            System.out.println("-----------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        }finally {
            JDBCUtil.close(preparedStatement,connection);
        }

        return result;
    }

    private boolean addNormalStock(NormalStock normalStock){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO spider_stock(code, date, open, close, high, low, preClose,fluctuation, changeRate, volume, amount, turnOverRate, marketCap, marketEquity)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, normalStock.getCode());
            preparedStatement.setObject(2, normalStock.getDate());
            preparedStatement.setDouble(3, normalStock.getOpen());
            preparedStatement.setDouble(4, normalStock.getClose());
            preparedStatement.setDouble(5, normalStock.getHigh());
            preparedStatement.setDouble(6, normalStock.getLow());
            preparedStatement.setDouble(7, normalStock.getPreClose());
            preparedStatement.setDouble(8, normalStock.getFluctuation());
            preparedStatement.setDouble(9, normalStock.getChangeRate());
            preparedStatement.setDouble(10, normalStock.getVolume());
            preparedStatement.setDouble(11, normalStock.getAmount());
            preparedStatement.setDouble(12, normalStock.getTurnOverRate());
            preparedStatement.setDouble(13, normalStock.getMarketCap());
            preparedStatement.setDouble(14, normalStock.getMarketEquity());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("普通股票写入成功");
            System.out.println("-----------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        }finally {
            JDBCUtil.close(preparedStatement,connection);
        }

        return result;
    }
}
