package com.edu.nju.asi.service.serviceImpl.StockService.stockPoolFilters;

import com.edu.nju.asi.infoCarrier.traceBack.StockPool;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.service.serviceImpl.StockService.StockPoolFilter;
import com.edu.nju.asi.utilities.enums.IndustryType;

import java.util.List;

/**
 * Created by cuihua on 2017/6/8.
 */
public class IndustryFilter extends StockPoolFilter {

    @Override
    public List<StockPool> meetCriteria(List<StockPool> stocks, StockPoolCriteria criteria) {
        if (criteria.industryType != IndustryType.all) {
            IndustryType wantedIndustry = criteria.industryType;
            for (int i = 0; i < stocks.size(); ) {
                if (stocks.get(i).industryType != wantedIndustry) {
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
