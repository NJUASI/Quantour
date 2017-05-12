package com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilters;

import com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilter;
import com.edu.nju.asi.utilities.enums.StType;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.StockPool;

import java.util.List;

/**
 * Created by harvey on 17-4-2.
 */
public class StCriteriaFilter extends StockPoolFilter{

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

        //TODO 因为现在还不需要筛选出st，故先直接返回，不做筛选

        if(criteria.stType == StType.EXCLUDE){
            for(int i = 0; i < stocks.size();){
                if (stocks.get(i).isSt){
                    stocks.remove(i);
                }
                i++;
            }
        }
        else if(criteria.stType == StType.ONLY){
            for(int i = 0; i < stocks.size();){
                if(!stocks.get(i).isSt){
                    stocks.remove(i);
                }
                i++;
            }
        }

        if(getNextFilter() == null){
            return stocks;
        }
        else {
            return getNextFilter().meetCriteria(stocks, criteria);
        }
    }
}