package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

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
     * @param formateRates
     * @return List<String> 选择好的持有期的股票代码
     */
    public abstract List<String> pick(List<FormateRate> formateRates);

    protected List<FormateRate> ascSort(List<FormateRate> formateRates){
        List<FormateRate> sortedStockCodes = new ArrayList<>();
        formateRates.sort(new AscSorter());
        for(int i = 0; i < formateRates.size(); i++){
            sortedStockCodes.add(formateRates.get(i));
        }
        return sortedStockCodes;
    }

    protected List<FormateRate> descSort(List<FormateRate> formateRates){
        List<FormateRate> sortedStockCodes = new ArrayList<>();
        formateRates.sort(new DescSorter());
        for(int i = 0; i < formateRates.size(); i++){
            sortedStockCodes.add(formateRates.get(i));
        }
        return sortedStockCodes;
    }

    protected List<String> convert(List<FormateRate> formateRates){
        List<String> filterWantedCodes = new ArrayList<>();
        for (FormateRate formateRate : formateRates){
            filterWantedCodes.add(formateRate.stockCode);
        }
        return filterWantedCodes;
    }

}

/**
 * 升序排序器
 */
class AscSorter implements Comparator<FormateRate> {

    @Override
    public int compare(FormateRate o1, FormateRate o2) {
        //两个中至少有一个为null
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
class DescSorter implements Comparator<FormateRate>{
    @Override
    public int compare(FormateRate o1, FormateRate o2) {
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
