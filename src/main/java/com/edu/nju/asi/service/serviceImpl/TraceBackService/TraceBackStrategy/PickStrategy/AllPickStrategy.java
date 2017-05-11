package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormativePeriodRate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期的挑选股票的策略， 按排名区间 和 绝对排名 分开
 */
public abstract class AllPickStrategy {

    /**
     * 绝对排名：rank表示绝对名次； 区间排名：rank表示percent排名
     */
    protected int rank;


    public AllPickStrategy(int rank) {
        this.rank = rank;
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     * @param formativePeriodRates
     * @return List<String> 选择好的持有期的股票代码
     */
    public abstract List<String> pick(List<FormativePeriodRate> formativePeriodRates);

    protected List<String> ascSort(List<FormativePeriodRate> formativePeriodRates){
        List<String> sortedStockCodes = new ArrayList<>();
        formativePeriodRates.sort(new AscSorter());
        for(int i = 0; i < formativePeriodRates.size(); i++){
            sortedStockCodes.add(formativePeriodRates.get(i).stockCode);
        }
        return sortedStockCodes;
    }

    protected List<String> descSort(List<FormativePeriodRate> formativePeriodRates){
        List<String> sortedStockCodes = new ArrayList<>();
        formativePeriodRates.sort(new DescSorter());
        for(int i = 0; i < formativePeriodRates.size(); i++){
            sortedStockCodes.add(formativePeriodRates.get(i).stockCode);
        }
        return sortedStockCodes;
    }

}

/**
 * 升序排序器
 */
class AscSorter implements Comparator<FormativePeriodRate> {

    @Override
    public int compare(FormativePeriodRate o1, FormativePeriodRate o2) {
        if(o1.periodReturn > o2.periodReturn){
            return -1;
        }
        else if(o1.periodReturn == o2.periodReturn){
            return 0;
        }
        else {
            return 1;
        }
    }
}

/**
 * 降序排序器
 */
class DescSorter implements Comparator<FormativePeriodRate>{
    @Override
    public int compare(FormativePeriodRate o1, FormativePeriodRate o2) {
        if(o1.periodReturn > o2.periodReturn){
            return 1;
        }
        else if(o1.periodReturn == o2.periodReturn){
            return 0;
        }
        else {
            return -1;
        }
    }
}
