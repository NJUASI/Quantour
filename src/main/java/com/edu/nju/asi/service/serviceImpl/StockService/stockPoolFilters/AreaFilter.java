package com.edu.nju.asi.service.serviceImpl.StockService.stockPoolFilters;

import com.edu.nju.asi.infoCarrier.traceBack.StockPool;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilter;
import com.edu.nju.asi.utilities.enums.AreaType;

import java.util.List;

/**
 * Created by cuihua on 2017/6/8.
 */
public class AreaFilter extends StockPoolFilter {

    @Override
    public List<StockPool> meetCriteria(List<StockPool> stocks, StockPoolCriteria criteria) {
        // 全部地域板块，则跳过此过滤器
        if (criteria.areaTypes.get(0) != AreaType.all) {
            for (int i = 0; i < stocks.size(); i++) {
                boolean isSelected = false;
                for (AreaType areaType : criteria.areaTypes) {
                    if (stocks.get(i).areaType == areaType) {
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
