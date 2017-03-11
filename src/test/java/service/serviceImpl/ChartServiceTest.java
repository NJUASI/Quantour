package service.serviceImpl;

import org.junit.Before;
import org.junit.Test;
import service.ChartService;
import vo.ChartShowCriteriaVO;
import vo.MovingAverageVO;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Byron Dong on 2017/3/6.
 */
public class ChartServiceTest {
    ChartService chartService;
    ChartShowCriteriaVO vo;
    List<Integer> days;

    @Before
    public void setUp() throws Exception {
       chartService = new ChartServiceImpl();
       vo = new ChartShowCriteriaVO("1",LocalDate.of(2014,4,1),LocalDate.of(2014,4,29));
       days = new ArrayList<Integer>();
       days.add(5);
    }

    @Test
    public void getAveData() throws Exception {
        Map<Integer,Iterator<MovingAverageVO>> map = chartService.getAveData(vo,days);
    }

}