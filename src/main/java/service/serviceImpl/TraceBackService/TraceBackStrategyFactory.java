package service.serviceImpl.TracebackService;

import service.serviceImpl.TracebackService.TracebackStrategy.MeanReversionStrategy;
import service.serviceImpl.TracebackService.TracebackStrategy.MomentumStrategy;
import vo.TraceBackCriteriaVO;

import java.util.List;

/**
 * Created by harvey on 17-4-3.
 */
public class TraceBackStrategyFactory {

    public static AllTraceBackStrategy createTraceBackStrategy(List<String> stockCodes, TraceBackCriteriaVO traceBackCriteriaVO) {
        switch (traceBackCriteriaVO.strategyType) {
            case MS:
                return new MomentumStrategy(stockCodes,traceBackCriteriaVO);
            case MR:
                return new MeanReversionStrategy(stockCodes,traceBackCriteriaVO);
        }

        return null;
    }

}
