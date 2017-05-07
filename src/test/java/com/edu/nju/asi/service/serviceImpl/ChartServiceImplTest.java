package com.edu.nju.asi.service.serviceImpl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import com.edu.nju.asi.service.ChartService;
import com.edu.nju.asi.utilities.enums.MovingAverageType;
import com.edu.nju.asi.vo.ChartShowCriteriaVO;
import com.edu.nju.asi.vo.MovingAverageVO;
import com.edu.nju.asi.vo.StockComparisionVO;
import com.edu.nju.asi.vo.StockComparsionCriteriaVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * ChartServiceImpl Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>03/10/2017</pre>
 */
public class ChartServiceImplTest {

    ChartService service;

    @Before
    public void before() throws Exception {
        service = new ChartServiceImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getSingleStockRecords(String code)
     */
    @Test
    public void testGetSingleStockRecordsCode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO)
     */
    @Test
    public void testGetSingleStockRecordsChartShowCriteriaVO() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAveData(ChartShowCriteriaVO chartShowCriteriaVO, List<Integer> days)
     */
    @Ignore
    @Test
    public void testGetAveDataForChartShowCriteriaVODays() throws Exception {
        ChartShowCriteriaVO vo = new ChartShowCriteriaVO("000001",LocalDate.of(2012,2,1),LocalDate.of(2012,3,20));
        List<MovingAverageType> days = new ArrayList<>();
        days.add(MovingAverageType.MA5);
        days.add(MovingAverageType.MA10);
        days.add(MovingAverageType.MA20);

        Map<MovingAverageType,List<MovingAverageVO>> map = service.getAveData(vo,days);

//        for(int i:days){
//            List<MovingAverageVO> list = map.get(i);
//            for(int j = 0;j<list.size();j++){
//                System.out.println(list.get(j).date);
//            }
//            System.out.println();
//        }

    }

    /**
     * Method: getAveData(String code, List<Integer> days)
     */
    @Test
    public void testGetAveDataForCodeDays() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getComparision(StockComparsionCriteriaVO stockComparsionCriteriaVO)
     */
    @Ignore
    @Test
    public void testGetComparision() throws Exception {
        List<StockComparisionVO> result = service.getComparision(new StockComparsionCriteriaVO("000001",
                "000010", LocalDate.of(2014, 1, 1), LocalDate.of(2014, 1, 10)));
        StockComparisionVO vo1 = result.get(0);
        StockComparisionVO vo2 = result.get(1);

        assertEquals(11.5, vo1.min, 0);
        assertEquals(12.3, vo1.max, 0);
        assertEquals(6.95, vo2.min, 0);
        assertEquals(8.24, vo2.max, 0);
    }

    /**
     * Method: getStockPOs(ChartShowCriteriaVO com.edu.nju.asi.vo)
     */
    @Test
    public void testGetStockPOs() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: calculate(ChartShowCriteriaVO chartShowCriteriaVO, int day)
     */
    @Test
    public void testCalculate() throws Exception {
//TODO: Test goes here...
    }

} 
