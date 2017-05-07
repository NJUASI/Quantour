package com.edu.nju.asi.service.serviceImpl;

import org.junit.Before;
import org.junit.Test;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockServiceImpl;
import com.edu.nju.asi.utilities.enums.BlockType;
import com.edu.nju.asi.utilities.enums.StType;
import com.edu.nju.asi.vo.StockPoolCriteriaVO;
import com.edu.nju.asi.vo.StockVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by harvey on 17-4-2.
 */
public class StockServiceImplTest {

    LocalDate start;
    LocalDate end;
    StockService stockService;

    @Before
    public void setUp() throws Exception {
        stockService = new StockServiceImpl();
        start = LocalDate.of(2014,4,18);
        end = LocalDate.of(2014,4,29);
    }

    @Test
    public void getAllStocks() throws Exception {
    }

    @Test
    public void getPrivateStocks() throws Exception {
    }

    @Test
    public void addPrivateStock() throws Exception {
    }

    @Test
    public void deletePrivateStock() throws Exception {
    }

    @Test
    public void searchStock() throws Exception {
    }

    @Test
    public void getOneStockData() throws Exception {
        Map<LocalDate,StockVO> stockVOS = stockService.getOneStockDateAndData("000001",start,end);
        assertEquals(8,stockVOS.size(),1);
    }

    @Test
    public void getBaseStock() throws Exception {
        List<StockVO> stockVOS = stockService.getBaseStockData("深发展Ａ",start,end);
        assertEquals(8,stockVOS.size(),1);
    }

    @Test
    public void getStockPool() throws Exception {
        List<String> stocksPool = new ArrayList<>();
        List<BlockType> blockTypes = new ArrayList<>();
        blockTypes.add(BlockType.ZB);
        StockPoolCriteriaVO stockPoolCriteriaVO = new StockPoolCriteriaVO(StType.INCLUDE, blockTypes);
        stocksPool = stockService.getStockPool(stockPoolCriteriaVO);

        System.out.println();
    }
}