package service.serviceImpl.TraceBackService.TraceBackStrategy;

import service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.*;
import utilities.enums.PickType;


/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成回测策略
 */
public class PickStrategyFactory {

    public static AllPickStrategy createPickStrategy(PickType pickType, int rank){
        switch (pickType){
            case RANK_MAX:
                return new RankMaxPickStrategy(rank);
            case RANK_MAX_PERCENT:
                return new RankMaxPercentPickStrategy(rank);
            case RANK_MIN:
                return new RankMinPickStrategy(rank);
            case RANK_MIN_PERCENT:
                return new RankMinPercentPickStrategy(rank);
        }
        return null;
    }

}
