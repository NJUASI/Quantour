package com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilters;

import com.edu.nju.asi.infoCarrier.traceBack.StockPool;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilter;

import java.util.List;

/**
 * Created by harvey on 17-4-2.
 */
public class BlockCriteriaFilter extends StockPoolFilter {

    /**
     * 筛选股票
     *
     * @param stocks
     * @param criteria
     * @return 经过筛选后的目标股票池
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/4/2
     * @params List<StockVO> stocks 未经筛选的全部股票
     */
    @Override
    public List<StockPool> meetCriteria(List<StockPool> stocks, StockPoolCriteria criteria) {

        for (int i = 0; i < stocks.size(); ) {
            if (criteria.blockTypes.contains(stocks.get(i).blockType)) {
                i++;
                continue;
            }

            // 此股票没有在期望的板块中
            stocks.remove(i);
        }

        if (getNextFilter() == null) {
            return stocks;
        } else {
            return getNextFilter().meetCriteria(stocks, criteria);
        }
    }
}
