package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy;

import com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.pickStrategy.*;
import com.edu.nju.asi.utilities.enums.ComparatorType;


/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成回测策略
 */
public class PickStrategyFactory {

    public static AllPickStrategy createPickStrategy(ComparatorType comparatorType, int value){
        switch (comparatorType){
            case RANK_MAX:
                return new RankMaxPickStrategy(value);
            case RANK_MAX_PERCENT:
                return new RankMaxPercentPickStrategy(value);
            case RANK_MIN:
                return new RankMinPickStrategy(value);
            case RANK_MIN_PERCENT:
                return new RankMinPercentPickStrategy(value);
            case RANK_GREATER:
                return new GreaterThanPickStrategy(value);
            case RANK_LESS:
                return new LessThanPickStrategy(value);
            case RANK_EQUAL:
                return new EqualToPickStrategy(value);
        }
        return null;
    }

}
