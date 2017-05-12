package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormativePeriodRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 */
public class RankMaxPercentPickStrategy extends AllPickStrategy {

    public RankMaxPercentPickStrategy(int rank) {
        super(rank);
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     *
     * @param formativePeriodRates
     * @return List<String> 选择好的持有期的股票代码
     */
    @Override
    public List<String> pick(List<FormativePeriodRate> formativePeriodRates) {
        List<String> sortedStockPool = ascSort(formativePeriodRates);

        int size = sortedStockPool.size();
        int pickedNum =  (int)Math.ceil((double)size * rank / 100);

        return  sortedStockPool.subList(0,pickedNum);
    }
}
