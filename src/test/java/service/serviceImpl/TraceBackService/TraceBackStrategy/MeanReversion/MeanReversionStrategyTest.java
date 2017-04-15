package service.serviceImpl.TraceBackService.TraceBackStrategy.MeanReversion;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import utilities.enums.BlockType;
import utilities.enums.StType;
import utilities.enums.TraceBackStrategy;
import vo.StockPoolCriteriaVO;
import vo.StockPoolVO;
import vo.TraceBackCriteriaVO;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * MeanReversionStrategy Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>四月 15, 2017</pre>
 */
public class MeanReversionStrategyTest {

    AllTraceBackStrategy strategy;

    @Before
    public void before() throws Exception {
        List<String> stockPool = new LinkedList<>();
        stockPool.add("000001");
        stockPool.add("000002");

        List<BlockType> blockTypeList = new LinkedList<>();
        blockTypeList.add(BlockType.ALL);

        StockPoolCriteriaVO vo = new StockPoolCriteriaVO(StType.INCLUDE, blockTypeList);
        TraceBackCriteriaVO criteriaVO = new TraceBackCriteriaVO(LocalDate.of(2010, 1,1), LocalDate.of(2014, 4,29),
                5, 10, vo, TraceBackStrategy.MR, 100, "hushen");

        strategy = new MeanReversionStrategy(stockPool, criteriaVO);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: traceBack()
     */
    @Test
    public void testTraceBack() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod)
     */
    @Test
    public void testFormate() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: pickStocks(List<FormativePeriodRateVO> formativePeriodRate)
     */
    @Test
    public void testPickStocks() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: calculate(LocalDate periodStart, LocalDate periodEnd, int periodSerial)
     */
    @Test
    public void testCalculate() throws Exception {
//TODO: Test goes here...
    }


    /**
     * Method: getMin(Map<String, Double> result)
     */
    @Test
    public void testGetMin() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = MeanReversionStrategy.getClass().getMethod("getMin", Map<String,.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getProfit(StockPO po)
     */
    @Test
    public void testGetProfit() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = MeanReversionStrategy.getClass().getMethod("getProfit", StockPO.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getYield(List<Double> value)
     */
    @Test
    public void testGetYield() throws Exception {
//TODO: Test goes here...
/* 
try { 
   Method method = MeanReversionStrategy.getClass().getMethod("getYield", List<Double>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
