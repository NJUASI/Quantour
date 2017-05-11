package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.StockSearch;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public class StockSearchDataHelperImplTest {

    private StockSearchDataHelper stockSearchDataHelper;

    @Before
    public void setUp() throws Exception {
        stockSearchDataHelper = HelperManager.stockSearchDataHelper;
    }

    @Test
    public void getAllStocksFirstLetters() throws Exception {
        List<StockSearch> list = stockSearchDataHelper.getAllStocksFirstLetters();

        assertEquals("000001",list.get(0).getCode());
        assertEquals("s",list.get(0).getFirstLetters());
        assertEquals("深发展A",list.get(0).getName());

        assertEquals("000002",list.get(1).getCode());
        assertEquals("h",list.get(1).getFirstLetters());
        assertEquals("沪深A股",list.get(1).getName());

        assertEquals("000003",list.get(2).getCode());
        assertEquals("n",list.get(2).getFirstLetters());
        assertEquals("南京B股",list.get(2).getName());

        assertEquals("000011",list.get(3).getCode());
        assertEquals("s",list.get(3).getFirstLetters());
        assertEquals("深宝宝A股",list.get(3).getName());

    }

    @Test
    public void getAllStocksCode() throws Exception {
        Map<String,String> map = stockSearchDataHelper.getAllStocksCode();

        assertEquals("深发展A",map.get("000001"));
        assertEquals("沪深A股",map.get("000002"));
        assertEquals("南京B股",map.get("000003"));
        assertEquals("深宝宝A股",map.get("000011"));
    }

    @Test
    public void getAllStocksName() throws Exception {

        Map<String,String> map = stockSearchDataHelper.getAllStocksName();

        assertEquals("000001",map.get("深发展A"));
        assertEquals("000002",map.get("沪深A股"));
        assertEquals("000003",map.get("南京B股"));
        assertEquals("000011",map.get("深宝宝A股"));
    }

}