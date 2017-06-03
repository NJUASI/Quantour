package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 排名最大百分比
 */
public class RankMaxPercentPickStrategy extends AllPickStrategy {

    public RankMaxPercentPickStrategy(int rank, double weight, int poolSize) {
        super(rank, weight, poolSize);
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     *
     * @param filterConditionRates
     * @return List<String> 选择好的持有期的股票代码
     */
    @Override
    protected List<FilterConditionRate> eachPick(List<FilterConditionRate> filterConditionRates) {
        List<FilterConditionRate> sortedStockPool = ascSort(filterConditionRates);

        int size = sortedStockPool.size();
        int pickedNum =  (int)Math.ceil((double)size * rank / 100);

        return  sortedStockPool.subList(0,pickedNum);
    }
}
