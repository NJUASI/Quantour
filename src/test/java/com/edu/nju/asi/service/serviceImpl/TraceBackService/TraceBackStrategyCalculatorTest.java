package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockServiceImpl;
import com.edu.nju.asi.utilities.StrategyStockList;
import com.edu.nju.asi.utilities.enums.BlockType;
import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.ComparotorType;
import com.edu.nju.asi.utilities.enums.StType;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    protected Map<String, List<StrategyStock>> stockData;


    @Before
    public void setUp() {

        List<BlockType> blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.ZB);

        traceBackCriteria = new TraceBackCriteria(LocalDate.of(2017,4,1), LocalDate.of(2017,5,1), 5, , new StockPoolCriteria(StType.INCLUDE, blockTypes),
                "沪深300", new FilterCondition(IndicatorType.INCEREASE_AMOUNT, ComparotorType.RANK_MAX, 5, , 5));

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
            List<Stock> stocks = stockDao.getStockData(thisStockCode);
            stockData.put(thisStockCode, convertStocks(stocks));
        }
    }

    private List<StrategyStock> convertStocks(List<Stock> stocks) {
        StrategyStockList result = new StrategyStockList();
        for (Stock thisStock : stocks) {
            result.add(new StrategyStock(thisStock));
        }
        return result;
    }

    @Test
    public void traceBack() throws Exception {
        List<CumulativeReturn> test = traceBackStrategyCalculator.traceBack(traceBackCriteria);
        System.out.println();
    }

}