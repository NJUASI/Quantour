package service.serviceImpl.TraceBackService;

import service.serviceImpl.TraceBackService.TraceBackStrategy.MeanReversion.MeanReversionStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.Momentum.MomentumStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.TraceBackCriteriaVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by harvey on 17-4-3.
 */
public class TraceBackStrategyFactory {

    public static AllTraceBackStrategy createTraceBackStrategy(List<String> traceBackStockPool, TraceBackCriteriaVO traceBackCriteriaVO, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) throws IOException, DateNotWithinException, NoDataWithinException {
        switch (traceBackCriteriaVO.strategyType) {
            case MS:
                return new MomentumStrategy(traceBackStockPool, traceBackCriteriaVO, allDatesWithData, stockData);
            case MR:
                return new MeanReversionStrategy(traceBackStockPool, traceBackCriteriaVO, allDatesWithData, stockData);
        }

        return null;
    }

}
