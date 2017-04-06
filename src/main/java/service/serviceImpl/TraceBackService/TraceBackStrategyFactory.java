package service.serviceImpl.TraceBackService;

import service.serviceImpl.TraceBackService.TraceBackStrategy.MeanReversionStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.MomentumStrategy;
import utilities.enums.TraceBackStrategy;

/**
 * Created by harvey on 17-4-3.
 */
public class TraceBackStrategyFactory {

    public static AllTraceBackStrategy createTraceBackStrategy(TraceBackStrategy traceBackStrategy) {
        switch (traceBackStrategy) {
            case MS:
                return new MomentumStrategy();
            case MR:
                return new MeanReversionStrategy();
        }

        return null;
    }

}