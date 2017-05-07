package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.SearchDataHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.edu.nju.asi.po.StockSearchPO;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Harvey on 2017/3/14.
 */
public class SearchDataHelperImplTest {

    SearchDataHelper searchDataHelper;

    @Before
    public void setUp() throws Exception {
        searchDataHelper = new SearchDataHelperImpl();
    }

    @Test
    public void getAllStockCodes() throws Exception {
        List<String> result  = searchDataHelper.getAllStockCodes();
        assertEquals(791, result.size());
    }

    @Test
    public void getAllBaseStockCodes() throws Exception {
        List<String> result  = searchDataHelper.getAllBaseStockCodes();
        assertEquals(7, result.size());
    }

    @Ignore
    @Test
    public void getAllStocksFirstLetters() throws Exception {
        List<StockSearchPO> result = searchDataHelper.getAllStocksFirstLetters();
        assertEquals(798, result.size());
    }

    @Test
    public void getAllStocksCode() throws Exception {
        Map<String,String> map = searchDataHelper.getAllStocksCode();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("code:"+entry.getKey()+"  name:"+entry.getValue());
        }

        assertEquals(798, map.entrySet().size());
    }

    @Test
    public void getAllStocksName() throws Exception {
        Map<String,String> map = searchDataHelper.getAllStocksName();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key:"+entry.getKey()+"  value:"+entry.getValue());
        }

        assertEquals(798, map.entrySet().size());
    }

}