package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/20.
 *
 * 排名最小的前n条
 */
public class RankMinPickStrategy extends AllPickStrategy {

    public RankMinPickStrategy(int rank, double weight) {
        super(rank, weight);
    }

    @Override
    protected List<FilterConditionRate> eachPick(List<FilterConditionRate> filterConditionRates) {
        List<FilterConditionRate> sortedStockPool = descSort(filterConditionRates);
        return sortedStockPool.subList(0,rank);
    }
}
