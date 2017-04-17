package service.serviceImpl.TraceBackService;

import org.junit.Before;
import org.junit.Test;
import service.TraceBackService;
import vo.TraceBackCriteriaVO;

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
    TraceBackCriteriaVO TraceBackCriteriaVO;

    @Before
    public void setUp() throws IOException {
        traceBackService = new TraceBackServiceImpl();
        start = LocalDate.of(2014,4,19);
        end = LocalDate.of(2014,4,29);

        //设置TraceBackCriteriaVO
        TraceBackCriteriaVO = new TraceBackCriteriaVO();
        TraceBackCriteriaVO.baseStockName = "深发展Ａ";
        TraceBackCriteriaVO.startDate = start;
        TraceBackCriteriaVO.endDate = end;


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
//        List<CumulativeReturnVO> cumulativeReturnVOS = traceBackService.getCustomizedCumulativeReturn(TraceBackCriteriaVO.startDate,TraceBackCriteriaVO.endDate,stockCodes, stockMap);
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