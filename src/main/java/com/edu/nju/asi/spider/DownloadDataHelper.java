package com.edu.nju.asi.spider;

import com.csvreader.CsvReader;
import com.edu.nju.asi.dataHelper.BaseStockDataHelper;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.dataHelper.dataHelperImpl.BaseStockDataHelperImpl;
import com.edu.nju.asi.dataHelper.dataHelperImpl.StockDataHelperImpl;
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
 * Created by Harvey on 2017/5/16.
 *
 * 将csv文件转存到数据库
 */
public class DownloadDataHelper {

    private StockDataHelper stockDataHelper = new StockDataHelperImpl();
    private BaseStockDataHelper baseStockDataHelper = new BaseStockDataHelperImpl();

    /**
     * 保存普通股票数据
     * @param path 文件所在文件夹的路径，需要遍历文件夹
     */
    public void normalStockStore(String path) {

        File dir = new File(path);
        File[] files = dir.listFiles();
        for(int i = 0; i < files.length; i++){
            List<Stock> stocks = new ArrayList<>();

            try {
                CsvReader reader = new CsvReader(path+File.separator+files[i].getName(),',',Charset.forName("GBK"));
                //读取表头
                reader.readHeaders();
                //逐条读取记录
                while(reader.readRecord()){
                    NormalStock normalStock = new NormalStock();
                    //当天未开盘，不存入数据库中
                    if (reader.get(3).equals("0")){
                        continue;
                    }
                    normalStock.setCode(reader.get(1));
                    normalStock.setName(reader.get(2));
                    System.out.println(reader.get(2));
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

                    stocks.add(new Stock(normalStock));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            stockDataHelper.addStockAll(stocks);
        }


    }

    /**
     * 保存基准股票数据
     * @param path  文件所在文件夹的路径，需要遍历文件夹
     */
    public void baseStockStore(String path){

        File dir = new File(path);
        File[] files = dir.listFiles();
        for(int i = 0; i < files.length; i++){
            List<BaseStock> baseStockEves = new ArrayList<>();

            try {
                CsvReader reader = new CsvReader(path+File.separator+files[i].getName(),',',Charset.forName("GBK"));
                //读取表头
                reader.readHeaders();
                //逐条读取记录
                while(reader.readRecord()){
                    //当天未开盘，不存入数据库中
                    if (reader.get(3).equals("0")){
                        continue;
                    }
                    BaseStockEve baseStockEve = new BaseStockEve();
                    baseStockEve.setCode(reader.get(1));
                    baseStockEve.setName(reader.get(2));
                    baseStockEve.setDate(reader.get(0));
                    baseStockEve.setOpen(reader.get(6));
                    baseStockEve.setClose(reader.get(3));
                    baseStockEve.setHigh(reader.get(4));
                    baseStockEve.setLow(reader.get(5));
                    baseStockEve.setPreClose(reader.get(7));
                    baseStockEve.setFluctuation(reader.get(8));
                    baseStockEve.setChangeRate(reader.get(9));
                    baseStockEve.setVolume(reader.get(10));
                    baseStockEve.setAmount(reader.get(11));

                    baseStockEves.add(new BaseStock(baseStockEve));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            baseStockDataHelper.addBaseStockAll(baseStockEves);
        }
    }
}
