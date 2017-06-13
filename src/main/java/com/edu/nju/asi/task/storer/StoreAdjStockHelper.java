package com.edu.nju.asi.task.storer;

import com.csvreader.CsvReader;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockID;
import com.edu.nju.asi.model.StockSearch;
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

    //存放前复权数据的父类文件路径
    private String frontPath;

    //存放后复权数据的父类文件路径
    private String afterPath;

    //stock数据对象
    private StockDataHelper stockDataHelper;

    //初始化成员信息
    public StoreAdjStockHelper(String root) {
        if (!root.endsWith(File.separator)) {
            root = root + File.separator;
        }

        frontPath = root + "adjData" + File.separator + "front";
        afterPath = root + "adjData" + File.separator + "after";
        stockDataHelper = HelperManager.stockDataHelper;
    }

    /**
     * 存储复权数据
     *
     * @author ByronDong
     * @updateTime 2017/6/12
     */
    public void handle() {
        storeFrontStock(frontPath);
        storeAfterStock(afterPath);
    }

    /**
     * 存储前复权数据
     *
     * @author ByronDong
     * @param path 前复权数据的存放路径
     * @updateTime 2017/6/12
     */
    private void storeFrontStock(String path) {
        File dir = new File(path);
        File files[] = dir.listFiles();
        System.out.println("总共文件数：" + files.length);
        System.out.println("-------开始读取------------");
        for (int i = 0; i < files.length; i++) {
            List<Stock> oneStock = new ArrayList<>();
            try {
                CsvReader reader = new CsvReader(path + File.separator + files[i].getName(), ',', Charset.forName("GBK"));
                System.out.println("-------开始读取" + files[i].getName() + "------------");
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
                System.out.println("-------读取" + files[i].getName() + "完成------------");
                System.out.println("-------开始写入" + files[i].getName() + "------------");
                stockDataHelper.updateFront(setPreFrontClose(oneStock));
                System.out.println("-------结束写入" + files[i].getName() + "------------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-------所有文件完成读取与写入------------");
    }

    /**
     * 存储后复权数据
     *
     * @author ByronDong
     * @param path 后复权数据的存放路径
     * @updateTime 2017/6/12
     */
    private void storeAfterStock(String path) {
        File dir = new File(path);
        File files[] = dir.listFiles();
        System.out.println("总共文件数：" + files.length);
        System.out.println("-------开始读取------------");
        for (int i = 0; i < files.length; i++) {
            List<Stock> oneStock = new ArrayList<>();
            try {
                CsvReader reader = new CsvReader(path + File.separator + files[i].getName(), ',', Charset.forName("GBK"));
                System.out.println("-------开始读取" + files[i].getName() + "------------");
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
                System.out.println("-------读取" + files[i].getName() + "完成------------");
                System.out.println("-------开始写入" + files[i].getName() + "------------");
                stockDataHelper.updateAfter(setPreAfterClose(oneStock));
                System.out.println("-------结束写入" + files[i].getName() + "------------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-------所有文件完成读取与写入------------");
    }

    /**
     * 设置昨日前复权数据
     *
     * @author ByronDong
     * @param stocks 需要设置昨日前复权数据列表
     * @return List<Stock> 设置完成后的前复权数据
     * @updateTime 2017/6/12
     */
    private List<Stock> setPreFrontClose(List<Stock> stocks) {

        System.out.println("-------开始设置" + stocks.get(0).getStockID().getCode() + "前复权收盘价------------");
        for (int i = 0; i < stocks.size()-1; i++) {
            stocks.get(i).setPreFrontAdjClose(stocks.get(i + 1).getFrontAdjClose());
            System.out.println("--------设置" + stocks.get(i).getStockID().getDate().toString() + "------------");
        }
        Stock preStock = stockDataHelper.getLastStock(stocks.get(stocks.size()-1).getStockID().getCode());
        System.out.println("preStock: "+preStock.getStockID().getCode()+" preFront: "+preStock.getFrontAdjClose());
        stocks.get(stocks.size()-1).setPreFrontAdjClose(preStock.getFrontAdjClose());
        System.out.println("-------完成设置" + stocks.get(0).getStockID().getCode() + "前复权收盘价------------");
        return stocks;
    }

    /**
     * 设置昨日后复权数据
     *
     * @author ByronDong
     * @param stocks 需要设置昨日后复权数据列表
     * @return 设置完成的后复权数据
     * @updateTime 2017/6/12
     */
    private List<Stock> setPreAfterClose(List<Stock> stocks) {
        System.out.println("-------开始设置" + stocks.get(0).getStockID().getCode() + "后复权收盘价------------");
        for (int i = 0; i < stocks.size()-1; i++) {
            stocks.get(i).setPreAfterAdjClose(stocks.get(i + 1).getAfterAdjClose());
            System.out.println("--------设置" + stocks.get(i).getStockID().getDate().toString() + "------------");
        }

        Stock preStock = stockDataHelper.getLastStock(stocks.get(stocks.size()-1).getStockID().getCode());
        System.out.println("preStock: "+preStock.getStockID().getCode()+preStock.getFrontAdjClose()+" preAfter: "+preStock.getAfterAdjClose());
        stocks.get(stocks.size()-1).setPreAfterAdjClose(preStock.getAfterAdjClose());
        System.out.println("-------完成设置" + stocks.get(0).getStockID().getCode() + "后复权收盘价------------");
        return stocks;
    }

    /**
     * 将不符合日期规范的日期修正为指定格式，如果已符合就不做操作
     *
     * @author ByronDong
     * @param date 日期
     * @return String 标准化后日期
     * @updateTime 2017/6/12
     */
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

    public static void main(String[] args) {
        List<StockSearch> stockSearches = HelperManager.stockSearchDataHelper.getAllStockSearchWithoutBase();
        StockDataHelper stockDataHelper = HelperManager.stockDataHelper;
        StoreAdjStockHelper storeAdjStockHelper = new StoreAdjStockHelper("");
        for(StockSearch stockSearch:stockSearches){
            System.out.println("---------------"+stockSearch.getSearchID().getCode()+"---------------------");
            List<Stock> oneStock = stockDataHelper.getStockData(stockSearch.getSearchID().getCode());
            if(oneStock==null||oneStock.isEmpty()){
                continue;
            }
            oneStock = storeAdjStockHelper.convertAfter(oneStock);
            oneStock = storeAdjStockHelper.convertFront(oneStock);
            storeAdjStockHelper.update(oneStock);
        }
    }

    public List<Stock> convertAfter(List<Stock> stocks){
        System.out.println("-------开始设置" + stocks.get(0).getStockID().getCode() + "复权收盘价------------");
        for (int i = stocks.size()-1; i > 0; i--) {
            stocks.get(i).setPreAfterAdjClose(stocks.get(i - 1).getAfterAdjClose());
            System.out.println("--------设置" + stocks.get(i).getStockID().getDate().toString() + "------------");
        }

        stocks.get(0).setPreAfterAdjClose(0);
        System.out.println("-------完成设置" + stocks.get(0).getStockID().getCode() + "复权收盘价------------");
        return stocks;
    }

    public List<Stock> convertFront(List<Stock> stocks){
        System.out.println("-------开始设置" + stocks.get(0).getStockID().getCode() + "复权收盘价------------");
        for (int i = stocks.size()-1; i > 0; i--) {
            stocks.get(i).setPreFrontAdjClose(stocks.get(i - 1).getFrontAdjClose());
            System.out.println("--------设置" + stocks.get(i).getStockID().getDate().toString() + "------------");
        }

        stocks.get(0).setPreFrontAdjClose(0);
        System.out.println("-------完成设置" + stocks.get(0).getStockID().getCode() + "复权收盘价------------");
        return stocks;
    }

    public void update(List<Stock> stocks){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE stock SET preFrontAdjClose=?,preAfterAdjClose=? WHERE code=? AND date=?";

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (Stock stock : stocks) {
                preparedStatement.setDouble(1, stock.getPreFrontAdjClose());
                preparedStatement.setDouble(2, stock.getPreAfterAdjClose());
                preparedStatement.setString(3, stock.getStockID().getCode());
                preparedStatement.setObject(4, stock.getStockID().getDate());
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
        } finally {
            JDBCUtil.close(preparedStatement, connection);
        }
    }
}