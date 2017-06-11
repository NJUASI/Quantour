package com.edu.nju.asi.task.storer;

import com.csvreader.CsvReader;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockID;
import com.edu.nju.asi.utilities.util.JDBCUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/4.
 */
public class StoreAdjStockHelper {

    private String frontPath = "F:\\Quant\\adjData\\today\\front\\";
    private String afterPath = "F:\\Quant\\adjData\\today\\after\\";

    public void handle() {
        storeFrontStock(frontPath+"date2");
        storeAfterStock(afterPath+"date2");
    }

    private void storeFrontStock(String path) {
        File dir = new File(path);
        File files[] = dir.listFiles();
        System.out.println("总共文件数：" + files.length);
        System.out.println("-------开始读取------------");
        for (int i = 0; i < files.length; i++) {
            List<Stock> oneStock = new ArrayList<>();
            try {
                CsvReader reader = new CsvReader(path + File.separator + files[i].getName(), ',', Charset.forName("GBK"));
                System.out.println("-------开始读取"+files[i].getName()+"------------");
                reader.readHeaders();
                while (reader.readRecord()) {

                    //当天未开盘，不存入数据库中
                    if (reader.get(3).equals("0")) {
                        continue;
                    }
                    Stock stock = new Stock();
                    StockID stockID = new StockID();
                    stockID.setCode(files[i].getName().substring(0, 6));
                    stockID.setDate(LocalDate.parse(format(reader.get(0))));
                    stock.setStockID(stockID);
                    stock.setFrontAdjOpen(Double.parseDouble(reader.get(1)));
                    stock.setFrontAdjHigh(Double.parseDouble(reader.get(2)));
                    stock.setFrontAdjClose(Double.parseDouble(reader.get(3)));
                    stock.setFrontAdjLow(Double.parseDouble(reader.get(4)));
                    oneStock.add(stock);
                    System.out.println("Date: " + stock.getStockID().getDate().toString() + " " + "Code: " + stock.getStockID().getCode());
                }
                System.out.println("-------读取"+files[i].getName()+"完成------------");
                System.out.println("-------开始写入"+files[i].getName()+"------------");
                updateFront(setPreFrontClose(oneStock));
                System.out.println("-------结束写入"+files[i].getName()+"------------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-------所有文件完成读取与写入------------");
    }

    private void storeAfterStock(String path) {
        File dir = new File(path);
        File files[] = dir.listFiles();
        System.out.println("总共文件数：" + files.length);
        System.out.println("-------开始读取------------");
        for (int i = 0; i < files.length; i++) {
            List<Stock> oneStock = new ArrayList<>();
            try {
                CsvReader reader = new CsvReader(path + File.separator + files[i].getName(), ',', Charset.forName("GBK"));
                System.out.println("-------开始读取"+files[i].getName()+"------------");
                reader.readHeaders();
                while (reader.readRecord()) {

                    //当天未开盘，不存入数据库中
                    if (reader.get(3).equals("0")) {
                        continue;
                    }
                    Stock stock = new Stock();
                    StockID stockID = new StockID();
                    stockID.setCode(files[i].getName().substring(0, 6));
                    stockID.setDate(LocalDate.parse(format(reader.get(0))));
                    stock.setStockID(stockID);
                    stock.setAfterAdjOpen(Double.parseDouble(reader.get(1)));
                    stock.setAfterAdjHigh(Double.parseDouble(reader.get(2)));
                    stock.setAfterAdjClose(Double.parseDouble(reader.get(3)));
                    stock.setAfterAdjLow(Double.parseDouble(reader.get(4)));
                    oneStock.add(stock);
                    System.out.println("Date: " + stock.getStockID().getDate().toString() + " " + "Code: " + stock.getStockID().getCode());
                }
                System.out.println("-------读取"+files[i].getName()+"完成------------");
                System.out.println("-------开始写入"+files[i].getName()+"------------");
                updateAfter(setPreAfterClose(oneStock));
                System.out.println("-------结束写入"+files[i].getName()+"------------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-------所有文件完成读取与写入------------");
    }

    private boolean updateFront(List<Stock> stocks) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE stock SET frontAdjOpen=?,frontAdjHigh=?,frontAdjLow=?,frontAdjClose=?," +
                "preFrontAdjClose=? WHERE code=? AND date=?";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (Stock stock : stocks) {
                preparedStatement.setDouble(1, stock.getFrontAdjOpen());
                preparedStatement.setDouble(2, stock.getFrontAdjHigh());
                preparedStatement.setDouble(3, stock.getFrontAdjLow());
                preparedStatement.setDouble(4, stock.getFrontAdjClose());
                preparedStatement.setDouble(5, stock.getPreFrontAdjClose());
                preparedStatement.setString(6, stock.getStockID().getCode());
                preparedStatement.setObject(7, stock.getStockID().getDate());
                System.out.println("更新： "+stock.getStockID().getCode() + " " + stock.getStockID().getDate().toString());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        } finally {
            JDBCUtil.close(preparedStatement, connection);
        }
        return result;
    }

    private boolean updateAfter(List<Stock> stocks) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE stock SET afterAdjOpen=?,afterAdjHigh=?,afterAdjLow=?,afterAdjClose=?," +
                "preAfterAdjClose=? WHERE code=? AND date=?";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (Stock stock : stocks) {
                preparedStatement.setDouble(1, stock.getAfterAdjOpen());
                preparedStatement.setDouble(2, stock.getAfterAdjHigh());
                preparedStatement.setDouble(3, stock.getAfterAdjLow());
                preparedStatement.setDouble(4, stock.getAfterAdjClose());
                preparedStatement.setDouble(5, stock.getPreAfterAdjClose());
                preparedStatement.setString(6, stock.getStockID().getCode());
                preparedStatement.setObject(7, stock.getStockID().getDate());
                System.out.println("更新： "+stock.getStockID().getCode() + " " + stock.getStockID().getDate().toString());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        } finally {
            JDBCUtil.close(preparedStatement, connection);
        }
        return result;
    }

    private List<Stock> setPreFrontClose(List<Stock> stocks){
        System.out.println("-------开始设置"+stocks.get(0).getStockID().getCode()+"前复权收盘价------------");
        for(int i = stocks.size()-1;i>0;i--){
            stocks.get(i).setPreFrontAdjClose(stocks.get(i-1).getFrontAdjClose());
            System.out.println("--------设置"+stocks.get(i).getStockID().getDate().toString()+"------------");
        }
        stocks.get(0).setPreFrontAdjClose(0);
        System.out.println("-------完成设置"+stocks.get(0).getStockID().getCode()+"前复权收盘价------------");
        return stocks;
    }

    private List<Stock> setPreAfterClose(List<Stock> stocks){
        System.out.println("-------开始设置"+stocks.get(0).getStockID().getCode()+"后复权收盘价------------");
        for(int i = stocks.size()-1;i>0;i--){
            stocks.get(i).setPreAfterAdjClose(stocks.get(i-1).getAfterAdjClose());
            System.out.println("--------设置"+stocks.get(i).getStockID().getDate().toString()+"------------");
        }
        stocks.get(0).setPreAfterAdjClose(0);
        System.out.println("-------完成设置"+stocks.get(0).getStockID().getCode()+"后复权收盘价------------");
        return stocks;
    }

    private static String format(String date) {
        if (date.indexOf("/") != -1) {
            String temp[] = date.split("/");
            if (temp[1].length() == 1) {
                temp[1] = "0" + temp[1];
            }

            if (temp[2].length() == 1) {
                temp[2] = "0" + temp[1];
            }
            return temp[0] + "-" + temp[1] + "-" + temp[2];
        }
        return date;
    }
}
