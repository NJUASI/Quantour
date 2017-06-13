package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.pickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/20.
 *
 * 排名最小的前n条
 */
public class RankMinPickStrategy extends AllPickStrategy {

    public RankMinPickStrategy(int rank) {
        super(rank);
    }

    @Override
    public List<String> pick(List<FormateRate> formateRates) {
        List<FormateRate> sortedStockPool = ascSort(formateRates);
        return convert(sortedStockPool.subList(0,rank));
    }
}
