package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.vo.FormativePeriodRateVO;

import java.util.List;

/**
 * Created by Harvey on 2017/4/20.
 *
 */
public class RankMinPercentPickStrategy extends AllPickStrategy{

    public RankMinPercentPickStrategy(int rank) {
        super(rank);
    }

    @Override
    public List<String> pick(List<FormativePeriodRateVO> formativePeriodRateVOS) {
        List<String> sortedStockPool = descSort(formativePeriodRateVOS);

        int size = sortedStockPool.size();
        int pickedNum =  (int)Math.ceil((double)size * rank / 100);

        return  sortedStockPool.subList(0,pickedNum);
    }
}
