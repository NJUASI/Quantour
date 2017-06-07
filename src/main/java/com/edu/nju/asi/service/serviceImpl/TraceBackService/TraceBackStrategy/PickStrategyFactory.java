package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy;

import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.*;
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
        }
        return null;
    }

}
