package com.edu.nju.asi.task.storer;

import com.csvreader.CsvReader;
import com.edu.nju.asi.dataHelper.BaseStockDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.spider.Model.BaseStockEve;
import com.edu.nju.asi.spider.Model.NormalStock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/3.
 */
public class StoreStockHelper {

    private String path = "F:\\Quant\\stocks";
    private String basePath = "F:\\Quant\\todayBaseStock";

    private StockDataHelper stockDataHelper;

    private BaseStockDataHelper baseStockDataHelper;

    public StoreStockHelper() {
        stockDataHelper = HelperManager.stockDataHelper;
        baseStockDataHelper = HelperManager.baseStockDataHelper;
    }

    public void handle() {
//        this.store();
        this.storeBase();
    }

    private void store() {

        File dir = new File(path);
        File files[] = dir.listFiles();
        List<Stock> stockList = new ArrayList<>();

        System.out.println("总共文件数：" + files.length);
        System.out.println("-------开始读取------------");
        for (int i = 0; i < files.length; i++) {
            try {
                CsvReader reader = new CsvReader(path + File.separator + files[i].getName(), ',', Charset.forName("GBK"));
                reader.readHeaders();
                while (reader.readRecord()) {

                    //当天未开盘，不存入数据库中
                    if (reader.get(3).equals("0") || reader.get(8).equals("None")) {
                        continue;
                    }

                    NormalStock normalStock = new NormalStock();

                    normalStock.setDate(reader.get(0));
                    normalStock.setCode(reader.get(1));
                    normalStock.setName(reader.get(2));
                    normalStock.setClose(reader.get(3));
                    normalStock.setHigh(reader.get(4));
                    normalStock.setLow(reader.get(5));
                    normalStock.setOpen(reader.get(6));
                    normalStock.setPreClose(reader.get(7));
                    normalStock.setFluctuation(reader.get(8));
                    normalStock.setChangeRate(reader.get(9));
                    normalStock.setTurnOverRate(reader.get(10));
                    normalStock.setVolume(reader.get(11));
                    normalStock.setAmount(reader.get(12));
                    normalStock.setMarketCap(reader.get(13));
                    normalStock.setMarketEquity(reader.get(14));

                    Stock stock = new Stock(normalStock);
                    stockList.add(stock);
                    System.out.println("Date: " + stock.getStockID().getDate().toString() + " " + "Code: " + stock.getStockID().getCode() + " " + "Name: " + stock.getName());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (i % 10 == 0) {
                System.out.println("---------------中途开始写入 " + i + "只股票----------------");
                stockDataHelper.addStockAll(stockList);
                System.out.println("---------------中途写入完成 " + i + "只股票----------------");
                stockList.clear();
            }
        }
        System.out.println("-------读取完成------------");
        System.out.println("-------开始写入------------");
        stockDataHelper.addStockAll(stockList);
        System.out.println("-------写入完成------------");
    }

    private void storeBase() {
        File dir = new File(basePath);
        File files[] = dir.listFiles();
        List<BaseStock> stockList = new ArrayList<>();

        System.out.println("总共文件数：" + files.length);
        System.out.println("-------开始读取------------");
        for (int i = 0; i < files.length; i++) {
            try {
                CsvReader reader = new CsvReader(basePath + File.separator + files[i].getName(), ',', Charset.forName("GBK"));
                reader.readHeaders();
                while (reader.readRecord()) {

                    //当天未开盘，不存入数据库中
                    if (reader.get(3).equals("0") || reader.get(8).equals("None")) {
                        continue;
                    }

                    BaseStockEve baseStockEve = new BaseStockEve();

                    baseStockEve.setDate(reader.get(0));
                    baseStockEve.setCode(reader.get(1));
                    baseStockEve.setName(reader.get(2));
                    baseStockEve.setClose(reader.get(3));
                    baseStockEve.setHigh(reader.get(4));
                    baseStockEve.setLow(reader.get(5));
                    baseStockEve.setOpen(reader.get(6));
                    baseStockEve.setPreClose(reader.get(7));
                    baseStockEve.setFluctuation(reader.get(8));
                    baseStockEve.setChangeRate(reader.get(9));
                    baseStockEve.setVolume(reader.get(10));
                    baseStockEve.setAmount(reader.get(11));

                    BaseStock baseStock = new BaseStock(baseStockEve);
                    stockList.add(baseStock);
                    System.out.println("Date: " + baseStock.getStockID().getDate().toString() + " " + "Code: " + baseStock.getStockID().getCode() + " " + "Name: " + baseStock.getName());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-------读取完成------------");
        System.out.println("-------开始写入------------");
        baseStockDataHelper.addBaseStockAll(stockList);
        System.out.println("-------写入完成------------");
    }

}
