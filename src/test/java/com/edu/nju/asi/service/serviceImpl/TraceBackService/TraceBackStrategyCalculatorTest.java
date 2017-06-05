package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockServiceImpl;
import com.edu.nju.asi.utilities.StockList;
import com.edu.nju.asi.utilities.enums.*;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Harvey on 2017/5/29.
 */
public class TraceBackStrategyCalculatorTest {

    TraceBackStrategyCalculator traceBackStrategyCalculator;
    StockService stockService = new StockServiceImpl();
    StockDao stockDao = new StockDaoImpl();
    private List<String> traceBackStockPool;
    TraceBackCriteria traceBackCriteria;

    /**
     * 所有股票池中的股票数据
     */
    protected Map<String, List<Stock>> stockData;


    @Before
    public void setUp() {

        List<BlockType> blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.ZB);
        List<FilterCondition> filterConditions = new ArrayList<>();
        filterConditions.add(new FilterCondition(IndicatorType.MOMENTUM, ComparatorType.RANK_MAX, 5, 1, 5));

        traceBackCriteria = new TraceBackCriteria(LocalDate.of(2017,4,1), LocalDate.of(2017,5,1), 5, 10, new StockPoolCriteria(StType.INCLUDE, blockTypes),
                "沪深300", filterConditions);

        try {
            traceBackStockPool = stockService.getStockPool(traceBackCriteria.stockPoolCriteria);
            set(traceBackStockPool);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnhandleBlockTypeException e) {
            e.printStackTrace();
        }

        try {
            traceBackStrategyCalculator = new TraceBackStrategyCalculator(stockService.getStockPool(traceBackCriteria.stockPoolCriteria),traceBackCriteria,stockDao.getDateWithData(),stockData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnhandleBlockTypeException e) {
            e.printStackTrace();
        }
    }

    private void set(List<String> traceBackStockPool) throws IOException {

        stockData = new HashMap<>();
        for (String thisStockCode : traceBackStockPool) {
            List<com.edu.nju.asi.model.Stock> stocks = stockDao.getStockData(thisStockCode);
            stockData.put(thisStockCode, stocks);
        }
    }

    @Test
    public void traceBack() throws Exception {
        List<CumulativeReturn> test = traceBackStrategyCalculator.traceBack(traceBackCriteria);
        System.out.println();
    }

}