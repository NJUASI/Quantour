package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockID;
import com.edu.nju.asi.utilities.StockCodeHelper;
import com.edu.nju.asi.utilities.enums.Market;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public class StockDataHelperImplTest {

    private StockDataHelper stockDataHelper;

    @Before
    public void setUp() throws Exception {
        stockDataHelper = HelperManager.stockDataHelper;
    }

    @Test
    public void getStockData() throws Exception {
        Stock stock = stockDataHelper.getStockData("000001",LocalDate.of(1017,1,1));

        assertEquals(3.29, stock.getOpen(), 0);
        assertEquals(3.31, stock.getHigh(), 0);
        assertEquals(3.23, stock.getLow(), 0);
        assertEquals(3.26, stock.getClose(), 0);
        assertEquals("60536", stock.getVolume());
        assertEquals(1.58, stock.getAdjClose(), 0);
        assertEquals("000001", stock.getStockID().getCode());
        assertEquals("景兴纸业", stock.getName());
        assertEquals(Market.SZ, stock.getMarket());
        assertEquals(3.29, stock.getPreClose(), 0);
        assertEquals(1.6, stock.getPreAdjClose(), 0);
    }

    @Test
    public void getStockData1() throws Exception {
        List<Stock> stocks1 = stockDataHelper.getStockData("000001");
        List<Stock> stocks2 = stockDataHelper.getStockData("000002");

        assertEquals(101,stocks1.size());
        assertEquals(1,stocks2.size());
    }

    @Test
    public void getStockData2() throws Exception {
        List<Stock> stocks = stockDataHelper.getStockData(LocalDate.of(1017,4,10));

        assertEquals(2,stocks.size());
        assertEquals("000001",stocks.get(0).getStockID().getCode());
        assertEquals("000100",stocks.get(1).getStockID().getCode());
    }

    @Test
    public void addStockAll() throws Exception {
        String code = "000001";
        LocalDate localDate = LocalDate.of(1017,1,1);
        List<Stock> stocks = new ArrayList<>();
        for(int i=0;i<100;i++){
            Stock stock = new Stock("景兴纸业", Market.SZ,3.29,3.31,3.23,3.26,"60536",1.58,3.29,1.6);
            stock.setStockID( new StockID(code,localDate));
            stocks.add(stock);
            code = addOne(code);
            localDate = localDate.plusDays(1);
        }
        long start = System.currentTimeMillis();
        stockDataHelper.addStockAll(stocks);
        System.out.println("添加使用时间： "+(System.currentTimeMillis()-start));
    }

    @Test
    public void getDateWithData() throws Exception {
        List<LocalDate> localDates = stockDataHelper.getDateWithData();
        assertEquals(LocalDate.of(1017,1,2),localDates.get(1));
        assertEquals(101,localDates.size());
    }

    @Test
    public void getFirstAndLastDay() throws Exception {
        List<LocalDate> localDates = stockDataHelper.getFirstAndLastDay("000001");
        assertEquals(LocalDate.of(1017,1,1),localDates.get(0));
        assertEquals(LocalDate.of(1017,4,11),localDates.get(1));
    }

    private String addOne(String code){
         return StockCodeHelper.format(String.valueOf(Integer.parseInt(code)+1));
    }

}