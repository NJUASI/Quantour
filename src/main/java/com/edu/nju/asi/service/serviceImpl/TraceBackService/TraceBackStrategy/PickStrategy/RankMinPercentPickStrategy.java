package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/20.
 *
 * 排名最小百分比
 */
public class RankMinPercentPickStrategy extends AllPickStrategy{

    public RankMinPercentPickStrategy(int rank, double weight) {
        super(rank, weight);
    }

    @Override
    protected List<FilterConditionRate> eachPick(List<FilterConditionRate> filterConditionRates) {
        List<FilterConditionRate> sortedStockPool = descSort(filterConditionRates);

        int size = sortedStockPool.size();
        int pickedNum =  (int)Math.ceil((double)size * rank / 100);

        return  sortedStockPool.subList(0,pickedNum);
    }
}
