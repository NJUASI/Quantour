package com.edu.nju.asi.service.serviceImpl.stockService;

import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.StockPool;

import java.util.List;

/**
 * Created by harvey on 17-4-2.
 *
 * 通过过滤器模式、和责任链模式筛选股票
 */
public class StockPoolFilter {

    StockPoolFilter nextFilter;

    /**
     * 筛选股票
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @params List<StockVO> stocks 未经筛选的全部股票
     * @return 经过筛选后的目标股票池
     */
    public List<StockPool> meetCriteria(List<StockPool> stocks, StockPoolCriteria criteria){
        return getNextFilter().meetCriteria(stocks, criteria);
    }

    /**
     * 获取下一个过滤器
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @return 下一个过滤器
     */
    public StockPoolFilter getNextFilter(){
        return this.nextFilter;
    }

    /**
     * 设置下一个过滤器
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @params StockPoolFilter nextFilter 下一个过滤器
     */
    public void setNextFilter(StockPoolFilter nextFilter){
        this.nextFilter = nextFilter; 
    }


}
