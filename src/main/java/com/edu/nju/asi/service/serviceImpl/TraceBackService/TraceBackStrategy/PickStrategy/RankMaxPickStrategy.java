package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;


import com.edu.nju.asi.vo.FormativePeriodRateVO;

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
    public List<String> pick(List<FormativePeriodRateVO> formativePeriodRateVOS) {
        List<String> sortedStockPool = ascSort(formativePeriodRateVOS);
        return  sortedStockPool.subList(0,rank);
    }
}