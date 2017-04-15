package service.serviceImpl.TraceBackService;

import org.junit.Before;
import org.junit.Test;
import service.TraceBackService;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import vo.CumulativeReturnVO;
import vo.TraceBackCriteriaVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by harvey on 17-4-2.
 */
public class TraceBackServiceImplTest {

    TraceBackService TraceBackService;
    LocalDate start;
    LocalDate end;
    List<String> stockCodes;
    TraceBackCriteriaVO TraceBackCriteriaVO;

    @Before
    public void setUp(){
        TraceBackService = new TraceBackServiceImpl();
        start = LocalDate.of(2014,4,19);
        end = LocalDate.of(2014,4,29);

        //设置TraceBackCriteriaVO
        TraceBackCriteriaVO = new TraceBackCriteriaVO();
        TraceBackCriteriaVO.baseStockName = "深发展Ａ";
//        TraceBackCriteriaVO.baseStockName = "深物业A";
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

    @Test
    public void getCustomizedCumulativeReturn() throws Exception {

        List<CumulativeReturnVO> cumulativeReturnVOS = TraceBackService.getCustomizedCumulativeReturn(TraceBackCriteriaVO.startDate,TraceBackCriteriaVO.endDate,stockCodes);
        assertEquals(-0.021,cumulativeReturnVOS.get(1).cumulativeReturn,0.001);

    }

    @Test
    public void getNumericalVal() throws Exception {
    }

    @Test
    public void getRelativeIndexReturn() throws Exception {
    }

}