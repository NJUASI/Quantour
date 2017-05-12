package com.edu.nju.asi.service.serviceImpl.StockService;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.dao.daoImpl.StockDaoImpl;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilters.BlockCriteriaFilter;
import com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilters.StCriteriaFilter;
import com.edu.nju.asi.utilities.StockCodeHelper;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.StockPool;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * 股票信息查看、自选股操作
 * <p>
 * Created by Harvey on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/12
 * 因修改下层接口而修改
 */
public class StockServiceImpl implements StockService {

    StockDao stockDao;

    public StockServiceImpl() {
        stockDao = new StockDaoImpl();
    }

    /**
     * 显示所有股票信息的列表
     *
     * @return 股票信息列表
     * @throws IOException IO
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @params date 用户选择日期
     */
    @Override
    public List<Stock> getAllStocks(LocalDate date) throws IOException {
        return stockDao.getStockData(date);
    }


    /**
     * 用户输入代码或者股票首字母，查找符合条件的股票
     *
     * @param searchString 代码或股票首字母
     * @return List<StockSearchVO> 符合条件的股票简要信息
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public List<StockSearch> searchStock(String searchString) throws IOException {

        List<StockSearch> result = new ArrayList<>();

        //通过匹配股票的代码来查询
        if (searchString.matches("[0-9]+")) {
            searchString = StockCodeHelper.simplify(searchString);
            Map<String, String> codeAndName = stockDao.getAllStocksCode();
            Set<String> codes = codeAndName.keySet();
            for (String code : codes) {
                if (code.contains(searchString)) {
                    StockSearch ss = new StockSearch(StockCodeHelper.format(code), codeAndName.get(code));
                    result.add(ss);
                }
            }
        }

        //通过名称匹配股票
        else {
            Map<String, String> namesAndCode = stockDao.getAllStocksName();
            Set<String> names = namesAndCode.keySet();
            for (String name : names) {
                if (name.contains(searchString)) {
                    StockSearch ss = new StockSearch(StockCodeHelper.format(namesAndCode.get(name)), name);
                    result.add(ss);
                }
            }
        }

        //通过匹配股票的首字母来查询
        if (searchString.matches("[a-zA-Z]+")) {
            List<StockSearch> firstLetters = stockDao.getAllStocksFirstLetters();
            for (StockSearch tempSearch : firstLetters) {
                if (tempSearch.getFirstLetters().contains(searchString)) {
                    result.add(tempSearch);
                }
            }
        }
        return result;
    }


    /**
     * 根据基准股票名称，起始日期，结束日期，获得该基准股票在此期间的数据
     *
     * @param stockName 股票名称
     * @param start     起始日期
     * @param end       结束日期
     * @return List<StockVO> 基准股票信息的列表
     */
    @Override
    public List<Stock> getBaseStockData(String stockName, LocalDate start, LocalDate end) throws IOException, NoDataWithinException, DateNotWithinException {
        Map<String, String> map = stockDao.getAllStocksName();
        String baseStockCode = StockCodeHelper.format(map.get(stockName));
        return stockDao.getStockData(baseStockCode, start, end);
    }

    /**
     * 根据股票池的选择标准，选择符合标准的股票池 非自选股调用此方法
     *
     * @param stockPoolCriteria 股票池的选择标准
     * @return List<String> 符合标准的股票池中所有股票的股票代码
     */
    @Override
    public List<String> getStockPool(StockPoolCriteria stockPoolCriteria) throws IOException, UnhandleBlockTypeException {

        //新建所有filter对象
        StockPoolFilter stockPoolFilter = new StockPoolFilter();
        StockPoolFilter blockCriteriaFilter = new BlockCriteriaFilter();
        StockPoolFilter stCriteriaFilter = new StCriteriaFilter();

        //设置责任链
        stockPoolFilter.setNextFilter(blockCriteriaFilter);
        blockCriteriaFilter.setNextFilter(stCriteriaFilter);


        List<String> stockPoolCodes = new ArrayList<String>();
        //筛选股票
        List<StockPool> allStockPool = stockPoolFilter.meetCriteria(stockDao.getAllStockPool(), stockPoolCriteria);

        //只需要股票池所有股票的股票代码
        for (StockPool thisStockPool : allStockPool) {
            stockPoolCodes.add(thisStockPool.stockCode);
        }

        return stockPoolCodes;

    }
}