package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;

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

    /**
     * 打分的权重
     */
    protected double weight;

    /**
     * 总股票只数，用于打分
     */
    protected int poolSize;


    public AllPickStrategy(int rank, double weight, int poolSize) {
        this.rank = rank;
        this.weight = weight;
        this.poolSize = poolSize;
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     * @param filterConditionRates
     * @return List<String> 选择好的持有期的股票代码
     */
    public List<FilterConditionRate> pick(List<FilterConditionRate> filterConditionRates){
           //打分后返回
           return marking(eachPick(filterConditionRates));
    }

    protected abstract List<FilterConditionRate> eachPick(List<FilterConditionRate> filterConditionRates);

    // 根据排名和权重打分
    private List<FilterConditionRate> marking(List<FilterConditionRate> filterConditionRates){
        for(int i = 0; i < filterConditionRates.size(); i++){
            filterConditionRates.get(i).score = (poolSize - i) / (double)poolSize * 100 * weight;
        }
        return filterConditionRates;
    }

    protected List<FilterConditionRate> ascSort(List<FilterConditionRate> filterConditionRates){
        List<FilterConditionRate> sortedStockCodes = new ArrayList<>();
        filterConditionRates.sort(new AscSorter());
        for(int i = 0; i < filterConditionRates.size(); i++){
            sortedStockCodes.add(filterConditionRates.get(i));
        }
        return sortedStockCodes;
    }

    protected List<FilterConditionRate> descSort(List<FilterConditionRate> filterConditionRates){
        List<FilterConditionRate> sortedStockCodes = new ArrayList<>();
        filterConditionRates.sort(new DescSorter());
        for(int i = 0; i < filterConditionRates.size(); i++){
            sortedStockCodes.add(filterConditionRates.get(i));
        }
        return sortedStockCodes;
    }

}

/**
 * 升序排序器
 */
class AscSorter implements Comparator<FilterConditionRate> {

    @Override
    public int compare(FilterConditionRate o1, FilterConditionRate o2) {
        //两个中有一个为null
        if(o1.indicatorVal == null && o2.indicatorVal == null){
            return 0;
        }
        if(o1.indicatorVal == null && o2.indicatorVal != null){
            return 1;
        }
        if(o1.indicatorVal != null && o2.indicatorVal == null){
            return -1;
        }
        //两个都不为null
        if(o1.indicatorVal > o2.indicatorVal){
            return -1;
        }
        else if(o1.indicatorVal.doubleValue() == o2.indicatorVal.doubleValue()){
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
class DescSorter implements Comparator<FilterConditionRate>{
    @Override
    public int compare(FilterConditionRate o1, FilterConditionRate o2) {
        //两个中有一个为null
        if(o1.indicatorVal == null && o2.indicatorVal == null){
            return 0;
        }
        if(o1.indicatorVal == null && o2.indicatorVal != null){
            return -1;
        }
        if(o1.indicatorVal != null && o2.indicatorVal == null){
            return 1;
        }
        //两个都不为null
        if(o1.indicatorVal > o2.indicatorVal){
            return 1;
        }
        else if(o1.indicatorVal.doubleValue() == o2.indicatorVal.doubleValue()){
            return 0;
        }
        else {
            return -1;
        }
    }
}
