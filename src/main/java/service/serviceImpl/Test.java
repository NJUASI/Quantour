package service.serviceImpl;

import service.ChartService;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.ChartShowCriteriaVO;
import vo.CumulativeReturnVO;
import vo.MovingAverageVO;
import vo.TracebackCriteriaVO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/3/31.
 */
public class Test {


    ChartService service;

    public Test() {
        service = new ChartServiceImpl();

    }

    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO) throws CodeNotFoundException, IOException, DateNotWithinException, NoDataWithinException, DateShortException {
        // 一次性获得所有的均线数据（向前走了一天，获得前一天的）
        ChartShowCriteriaVO criteriaVO = new ChartShowCriteriaVO("12", tracebackCriteriaVO.startDate.minusDays(1), tracebackCriteriaVO.endDate);
//        ChartShowCriteriaVO criteriaVO = new ChartShowCriteriaVO(tracebackCriteriaVO, tracebackCriteriaVO.startDate, tracebackCriteriaVO.endDate);
        List<Integer> formatAve = new LinkedList<>();
        formatAve.add(tracebackCriteriaVO.formativePeriod);
        Map<Integer, List<MovingAverageVO>> aveInfo = service.getAveData(criteriaVO, formatAve);
        List<MovingAverageVO> aves = aveInfo.values().iterator().next();

        int myStockNum = tracebackCriteriaVO.holdingNum;
        int holdingPeriod = tracebackCriteriaVO.holdingPeriod;

        int cycles = (int) tracebackCriteriaVO.startDate.until(tracebackCriteriaVO.endDate, ChronoUnit.DAYS);

        // 整个周期的计算
        for (int i = 0; i < cycles; i++) {

        }

        // 最后一个不足周期的计算

        return null;
    }



    // 一个持有期周期内的收益率
    private double getPeriodYield(String[] stockCodes, LocalDate start, LocalDate end) {


        return 0;
    }
}
