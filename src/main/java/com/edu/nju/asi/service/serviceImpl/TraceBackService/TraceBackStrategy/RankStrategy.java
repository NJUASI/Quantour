package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;
import com.edu.nju.asi.infoCarrier.traceBack.RankConditionRate;
import com.edu.nju.asi.utilities.enums.RankType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Harvey on 2017/6/7.
 *
 * 排名策略
 */
public class RankStrategy {

    /**
     * 得分权重
     */
    int weight;

    /**
     * 排名类型
     */
    RankType rankType;

    public RankStrategy(int weight, RankType rankType) {
        this.weight = weight;
        this.rankType = rankType;
    }

    /**
     * 对已经排好序的股票代码进行打分
     * @param unOrderedCodes 还没有排好序的股票代码
     * @return
     */
    public List<RankConditionRate> mark(List<FormateRate> unOrderedCodes){
        
        List<String> orderedCodes = rank(unOrderedCodes);
        
       List<RankConditionRate> rankConditionRates = new ArrayList<>();

       for(int i = 0; i < orderedCodes.size(); i++){
           // 排名分的公式是 （股票数– 股票排名 + 1）/股票数 * 100
           rankConditionRates.add(new RankConditionRate(orderedCodes.get(i), ((double) (orderedCodes.size() - i)) / orderedCodes.size() * 100 * weight));
       }
       return rankConditionRates;
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     * @param formateRates
     * @return List<String> 选择好的持有期的股票代码
     */
    protected List<String> rank(List<FormateRate> formateRates){
        if(rankType == RankType.DESC_RANK){
             formateRates.sort(new DescSorter());
        }
        else {
            formateRates.sort(new AscSorter());
        }
        return convert(formateRates);
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
