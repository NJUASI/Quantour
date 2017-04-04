package service.serviceImpl.TracebackService;

import service.serviceImpl.TracebackService.TracebackStrategy.MomentumStrategy;
import utilities.enums.TracebackStrategy;

/**
 * Created by harvey on 17-4-3.
 */
public class TracebackStrategyFactory {

    public static AllTracebackStrategy createTracebackStrategy(TracebackStrategy tracebackStrategy){
        switch (tracebackStrategy){
            case MS:
                return new MomentumStrategy();
            case MR:
                //TODO fjj 你自己加上去
        }

        return null;
    }

}
