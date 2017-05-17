package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.BaseStockDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockID;
import com.edu.nju.asi.utilities.StockCodeHelper;
import com.edu.nju.asi.utilities.enums.Market;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/15.
 */
public class BaseStockDataHelperImplTest {

    private BaseStockDataHelper baseStockDataHelper;

    @Before
    public void setUp() throws Exception {
        baseStockDataHelper = HelperManager.baseStockDataHelper;
    }

    @Test
    public void getStockData() throws Exception {
        BaseStock stock = baseStockDataHelper.getStockData("000001",LocalDate.of(1017,1,1));

        assertEquals(3.29, stock.getOpen(), 0);
        assertEquals(3.31, stock.getHigh(), 0);
        assertEquals(3.23, stock.getLow(), 0);
        assertEquals(3.26, stock.getClose(), 0);
        assertEquals("60536", stock.getVolume());
        assertEquals("000001", stock.getStockID().getCode());
        assertEquals("景兴纸业", stock.getName());
        assertEquals(1.58, stock.getPreClose(), 0);

    }

    @Test
    public void getStockData1() throws Exception {
        List<BaseStock> list = baseStockDataHelper.getStockData("000001",
                LocalDate.of(1017,1,1),LocalDate.of(1017,4,11));
        assertEquals(101,list.size());
    }

    @Test
    public void addBaseStockAll() throws Exception {
        String code = "000001";
        LocalDate localDate = LocalDate.of(1017,1,1);
        List<BaseStock> stocks = new ArrayList<>();
        for(int i=0;i<100;i++){
            BaseStock stock = new BaseStock("景兴纸业",3.29,3.31,3.23,3.26,"60536",
                    "605366",1.58,3.29,1.6);
            stock.setStockID( new StockID(code,localDate));
            stocks.add(stock);
            code = addOne(code);
            localDate = localDate.plusDays(1);
        }
        long start = System.currentTimeMillis();
        baseStockDataHelper.addBaseStockAll(stocks);
        System.out.println("添加使用时间： "+(System.currentTimeMillis()-start));
    }

    private String addOne(String code){
        return StockCodeHelper.format(String.valueOf(Integer.parseInt(code)+1));
    }
}