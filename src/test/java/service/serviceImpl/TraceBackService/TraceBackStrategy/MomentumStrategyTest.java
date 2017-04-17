package service.serviceImpl.TraceBackService.TraceBackStrategy;

import org.junit.Before;
import org.junit.Test;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.Momentum.MomentumStrategy;
import utilities.enums.TraceBackStrategy;
import vo.CumulativeReturnVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackStrategyVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by harvey on 17-4-6.
 */
public class MomentumStrategyTest {

    List<String> stockCodes;

    TraceBackCriteriaVO traceBackCriteriaVO;

    AllTraceBackStrategy allTraceBackStrategy;

    @Before
    public void setUp() throws Exception {

        stockCodes = new ArrayList<String>();
        stockCodes.add("000001");

        traceBackCriteriaVO = new TraceBackCriteriaVO();
        traceBackCriteriaVO.strategyType = TraceBackStrategy.MS;
        traceBackCriteriaVO.baseStockName = null;
        traceBackCriteriaVO.formativePeriod = 10;
        traceBackCriteriaVO.holdingPeriod = 5;
        traceBackCriteriaVO.startDate = LocalDate.of(2014,2,14);
        traceBackCriteriaVO.endDate = LocalDate.of(2014,4,29);
        traceBackCriteriaVO.isCustomized = true;

        allTraceBackStrategy = new MomentumStrategy(stockCodes, traceBackCriteriaVO);
    }

    @Test
    public void traceBack() throws Exception {

        TraceBackStrategyVO traceBackStrategyVO = allTraceBackStrategy.traceBack();
        List<CumulativeReturnVO> cumulativeReturnVOS = traceBackStrategyVO.strategyCumulativeReturn;
        assertEquals(-0.02,cumulativeReturnVOS.get(1).cumulativeReturn,0.01);


    }

}