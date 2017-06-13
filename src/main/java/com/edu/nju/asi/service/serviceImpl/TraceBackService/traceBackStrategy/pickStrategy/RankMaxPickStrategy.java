package com.edu.nju.asi.service.serviceImpl.TraceBackService.traceBackStrategy.pickStrategy;


import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期挑选股票的策略， 按排名最大的前n条来挑选
 */
public class RankMaxPickStrategy extends AllPickStrategy{

    public RankMaxPickStrategy(int rank) {
        super(rank);
    }

    @Override
    public List<String> pick(List<FormateRate> formateRates) {
        List<FormateRate> sortedStockPool = descSort(formateRates);
        return convert(sortedStockPool.subList(0,rank));
    }
}