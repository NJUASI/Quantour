package com.edu.nju.asi.service.serviceImpl.strategyService;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Created by cuihua on 2017/6/10.
 */
public class Test {

    public static void main(String[] args) {

        String a = "{\"startDate\":\"2017-04-01\",\"endDate\":\"2017-05-01\",\"holdingPeriod\":\"10\",\"stockPoolCriteria\":{\"stType\":\"EXCLUDE\",\"blockTypes\":[\"ZXB\"]},\"maxHoldingNum\":\"5\",\"baseStockName\":\"沪深300\",\"filterConditions\":[{\"indicatorType\":\"VOLUME\",\"comparatorType\":\"RANK_MAX\",\"value\":\"10\",\"formativePeriod\":\"5\"}],\"rankConditions\":[]}";

        TraceBackCriteria criteria = JSON.parseObject(a, TraceBackCriteria.class);

        int holdingPeriod = criteria.holdingPeriod;
        int maxHoldingNum = criteria.maxHoldingNum;

        int filterNum = criteria.filterConditions.size();
        int rankNum = criteria.rankConditions.size();

        int score = 20 / holdingPeriod * 5 + maxHoldingNum * 2;
        if (filterNum != 0) {
            score += 5 / filterNum * 12;
        } else {
            score += 72;
        }
        if (rankNum != 0) {
            score += 5 / rankNum * 10;
        } else {
            score += 60;
        }


        System.out.println(score);

        NormalDistribution normalDistribution = new NormalDistribution(140, 60);
        System.out.println(normalDistribution.cumulativeProbability(score));
        System.out.println(normalDistribution.cumulativeProbability(100));
        System.out.println(normalDistribution.cumulativeProbability(200));
        System.out.println(normalDistribution.cumulativeProbability(0));
//        System.out.println(1-normalDistribution.cumulativeProbability(0.05 - meanBase));
//        System.out.println(1-normalDistribution.cumulativeProbability(0.1 - meanBase));
    }


}