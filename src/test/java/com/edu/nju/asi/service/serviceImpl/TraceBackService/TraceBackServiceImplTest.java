package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import org.junit.Before;
import org.junit.Test;
import com.edu.nju.asi.service.TraceBackService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harvey on 17-4-2.
 */
public class TraceBackServiceImplTest {

    TraceBackService traceBackService;
    LocalDate start;
    LocalDate end;
    List<String> stockCodes;
    TraceBackCriteria TraceBackCriteria;

    @Before
    public void setUp() throws IOException {
        traceBackService = new TraceBackServiceImpl();
        start = LocalDate.of(2014,4,19);
        end = LocalDate.of(2014,4,29);

        //设置TraceBackCriteriaVO
        TraceBackCriteria = new TraceBackCriteria();
        TraceBackCriteria.baseStockName = "深发展Ａ";
        TraceBackCriteria.startDate = start;
        TraceBackCriteria.endDate = end;


        //设置自选股的股票代码
        stockCodes = new ArrayList<>();
        stockCodes.add("000001");
        stockCodes.add("000011");
    }

    @Test
    public void getStrategyCumulativeReturn() throws Exception {
    }

//    @Test
//    public void getCustomizedCumulativeReturn() throws Exception {
//
//        List<CumulativeReturn> cumulativeReturnVOS = traceBackService.getCustomizedCumulativeReturn(TraceBackCriteria.startDate,TraceBackCriteria.endDate,stockCodes, stockMap);
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