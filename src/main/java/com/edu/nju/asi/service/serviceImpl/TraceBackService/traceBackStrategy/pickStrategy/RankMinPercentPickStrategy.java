package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.pickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/20.
 *
 * 排名最小百分比
 */
public class RankMinPercentPickStrategy extends AllPickStrategy{

    public RankMinPercentPickStrategy(int rank) {
        super(rank);
    }

    @Override
    public List<String> pick(List<FormateRate> formateRates) {
        List<FormateRate> sortedStockPool = ascSort(formateRates);

        int size = sortedStockPool.size();
        int pickedNum =  (int)Math.ceil((double)size * rank / 100);

        return  convert(sortedStockPool.subList(0,pickedNum));
    }
}
