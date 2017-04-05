package service.serviceImpl.TracebackService;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;
import service.TracebackService;
import vo.CumulativeReturnVO;
import vo.TracebackCriteriaVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by harvey on 17-4-2.
 */
public class TracebackServiceImplTest {

    TracebackService tracebackService;
    LocalDate start;
    LocalDate end;
    List<String> stockCodes;
    TracebackCriteriaVO tracebackCriteriaVO;

    @Before
    public void setUp(){
        tracebackService = new TracebackServiceImpl();
        start = LocalDate.of(2014,4,19);
        end = LocalDate.of(2014,4,29);

        //设置tracebackCriteriaVO
        tracebackCriteriaVO = new TracebackCriteriaVO();
        tracebackCriteriaVO.baseStockName = "深发展Ａ";
//        tracebackCriteriaVO.baseStockName = "深物业A";
        tracebackCriteriaVO.startDate = start;
        tracebackCriteriaVO.endDate = end;


        //设置自选股的股票代码
        stockCodes = new ArrayList<>();
        stockCodes.add("000001");
        stockCodes.add("000011");
    }

    @Test
    public void getStrategyCumulativeReturn() throws Exception {
    }

//    @Test
//    public void getBaseCumulativeReturn() throws Exception {
//
//        List<CumulativeReturnVO> cumulativeReturnVOS = tracebackService.getBaseCumulativeReturn(tracebackCriteriaVO);
//        assertEquals(LocalDate.of(2014,4,19),cumulativeReturnVOS.get(0).currentDate);
//        assertEquals(-0.0102,cumulativeReturnVOS.get(1).cumulativeReturn,0.0001);
//    }
//
//    @Test
//    public void getCustomizedCumulativeReturn() throws Exception {
//
//        List<CumulativeReturnVO> cumulativeReturnVOS = tracebackService.getCustomizedCumulativeReturn(tracebackCriteriaVO,stockCodes);
//        assertEquals(-0.021,cumulativeReturnVOS.get(1).cumulativeReturn,0.001);
//
//    }

    @Test
    public void getNumericalVal() throws Exception {
    }

    @Test
    public void getRelativeIndexReturn() throws Exception {
    }

}