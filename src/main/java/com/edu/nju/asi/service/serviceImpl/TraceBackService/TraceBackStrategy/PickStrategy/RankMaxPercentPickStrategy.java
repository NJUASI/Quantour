package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 排名最大百分比
 */
public class RankMaxPercentPickStrategy extends AllPickStrategy {

    public RankMaxPercentPickStrategy(int rank) {
        super(rank);
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     *
     * @param formateRates
     * @return List<String> 选择好的持有期的股票代码
     */
    @Override
    public List<String> pick(List<FormateRate> formateRates) {
        List<FormateRate> sortedStockPool = ascSort(formateRates);

        int size = sortedStockPool.size();
        int pickedNum =  (int)Math.ceil((double)size * rank / 100);

        return  convert(sortedStockPool.subList(0,pickedNum));
    }
}
