package service.serviceImpl.TraceBackService.TraceBackStrategy;

import service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.AllPickStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.RankMaxPercentPickStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy.RankMaxPickStrategy;
import utilities.enums.PickType;


/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成回测策略
 */
public class PickStrategyFactory {

    public static AllPickStrategy createPickStrategy(PickType pickType, int rank){
        switch (pickType){
            case RANK_Max:
                return new RankMaxPickStrategy(rank);
            case RANK_Max_Percent:
                return new RankMaxPercentPickStrategy(rank);
        }
        return null;
    }

}
