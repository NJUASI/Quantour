package com.edu.nju.asi.service.serviceImpl.stockService.stockPoolFilters;

import com.edu.nju.asi.infoCarrier.traceBack.StockPool;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.service.serviceImpl.stockService.StockPoolFilter;
import com.edu.nju.asi.utilities.enums.IndustryType;

import java.util.List;

/**
 * Created by cuihua on 2017/6/8.
 */
public class IndustryFilter extends StockPoolFilter {

    @Override
    public List<StockPool> meetCriteria(List<StockPool> stocks, StockPoolCriteria criteria) {
        // 全部行业板块，则跳过此过滤器
        if (criteria.industryTypes.get(0) != IndustryType.all) {
            for (int i = 0; i < stocks.size();) {
                boolean isSelected = false;
                for (IndustryType industry : criteria.industryTypes) {
                    if (stocks.get(i).industryType == industry) {
                        isSelected = true;
                        break;
                    }
                }

                if (!isSelected) {
                    stocks.remove(i);
                } else {
                    i++;
                }
            }
        }

        if (getNextFilter() == null) {
            return stocks;
        } else {
            return getNextFilter().meetCriteria(stocks, criteria);
        }
    }
}
