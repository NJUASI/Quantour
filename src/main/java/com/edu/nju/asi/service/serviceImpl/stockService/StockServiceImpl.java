package com.edu.nju.asi.service.serviceImpl.stockService;

import com.edu.nju.asi.dao.BaseStockDao;
import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.serviceImpl.stockService.stockPoolFilters.AreaFilter;
import com.edu.nju.asi.service.serviceImpl.stockService.stockPoolFilters.BlockCriteriaFilter;
import com.edu.nju.asi.service.serviceImpl.stockService.stockPoolFilters.IndustryFilter;
import com.edu.nju.asi.service.serviceImpl.stockService.stockPoolFilters.StCriteriaFilter;
import com.edu.nju.asi.utilities.enums.AreaType;
import com.edu.nju.asi.utilities.enums.IndustryType;
import com.edu.nju.asi.utilities.enums.StocksSortCriteria;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.StockPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("StockService")
public class StockServiceImpl implements StockService {

    @Autowired
    StockDao stockDao;
    @Autowired
    BaseStockDao baseStockDao;

    public StockServiceImpl() {
//        stockDao = new StockDaoImpl();
//        baseStockDao = new BaseStockDaoImpl();
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
    public List<Stock> getAllStocks(LocalDate date, StocksSortCriteria sortCriteria, IndustryType industryType, AreaType areaType) throws IOException, UnhandleBlockTypeException {
        System.out.println("getAllStocks: " + stockDao);

        // 设置责任链并基于条件对股票进行筛选
        StockPoolCriteria criteria = new StockPoolCriteria(industryType, areaType);

        StockPoolFilter filter = new StockPoolFilter();
        StockPoolFilter industryFilter = new IndustryFilter();
        StockPoolFilter areaFilter = new AreaFilter();

        filter.setNextFilter(industryFilter);
        industryFilter.setNextFilter(areaFilter);
        List<StockPool> meeted = filter.meetCriteria(stockDao.getAllStockPool(), criteria);

        List<Stock> allStocks = stockDao.getStockData(date);
        for (int i = 0; i < allStocks.size();) {
            boolean isMeeted = false;
            for (StockPool temp : meeted) {
                if (temp.stockCode.equals(allStocks.get(i).getStockID().getCode())){
                    isMeeted = true;
                    break;
                }
            }

            if (!isMeeted) {
                allStocks.remove(i);
            } else {
                i++;
            }
        }


        // 按指定要求排好序
        StockSortComparatorFactory factory = new StockSortComparatorFactory();
        Comparator<Stock> comparator = factory.createSortComparator(sortCriteria);
        allStocks.sort(comparator);

        return allStocks;
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
    public List<StockSearch> searchStock(String searchString){
        System.out.println("searchStock: " + stockDao);
        return stockDao.searchStock(searchString);
    }

    @Override
    public List<BaseStock> getBaseStockDataOfOneDay(LocalDate thisDate) {
        List<String> baseStocksCode = baseStockDao.getAllBaseStocksCode();

        List<BaseStock> result = new ArrayList<>();
        for (String tempBaseStock : baseStocksCode) {
            result.add(baseStockDao.getBaseStockData(tempBaseStock, thisDate));
        }
        return result;
    }

    /**
     * 根据股票池的选择标准，选择符合标准的股票池 非自选股调用此方法
     *
     * @param stockPoolCriteria 股票池的选择标准
     * @return List<String> 符合标准的股票池中所有股票的股票代码
     */
    @Override
    public List<String> getStockPool(StockPoolCriteria stockPoolCriteria) throws IOException, UnhandleBlockTypeException {
        System.out.println("getStockPool: " + stockDao);

        //新建所有filter对象
        StockPoolFilter stockPoolFilter = new StockPoolFilter();
        StockPoolFilter blockCriteriaFilter = new BlockCriteriaFilter();
        StockPoolFilter stCriteriaFilter = new StCriteriaFilter();
        StockPoolFilter industryFilter = new IndustryFilter();
        StockPoolFilter areaFilter = new AreaFilter();

        //设置责任链
        stockPoolFilter.setNextFilter(blockCriteriaFilter);
        blockCriteriaFilter.setNextFilter(stCriteriaFilter);
        stCriteriaFilter.setNextFilter(industryFilter);
        industryFilter.setNextFilter(areaFilter);


        List<String> stockPoolCodes = new ArrayList<String>();
        //筛选股票
        List<StockPool> allStockPool = stockPoolFilter.meetCriteria(stockDao.getAllStockPool(), stockPoolCriteria);

        //只需要股票池所有股票的股票代码
        for (StockPool thisStockPool : allStockPool) {
            stockPoolCodes.add(thisStockPool.stockCode);
        }

        return stockPoolCodes;

    }

    /**
     * 增加指定股票的点击量（+1）
     *
     * @param searchID
     * @return boolean
     */
    @Override
    public boolean addClickAmount(SearchID searchID) {
        return stockDao.addClickAmount(searchID);
    }

    /**
     * 获取指定股票的点击率
     *
     * @param searchID
     * @return double
     */
    @Override
    public double getClickRate(SearchID searchID) {
        return stockDao.getClickRate(searchID);
    }

    /**
     * 获取股票排名前N（number）
     *
     * @param number
     * @return List<StockSearch>
     */
    @Override
    public List<StockSearch> getTopRankingList(int number) {
        return stockDao.getTopRankingList(number);
    }
}
