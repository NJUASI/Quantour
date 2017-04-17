package service.serviceImpl.TraceBackService.TraceBackStrategy;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import po.StockPO;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.MeanReversion.MeanReversionStrategy;
import utilities.enums.BlockType;
import utilities.enums.StType;
import utilities.enums.TraceBackStrategy;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * MeanReversionStrategy Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>四月 15, 2017</pre>
 */
public class MeanReversionStrategyTest {

    StockDao stockDao;
    AllTraceBackStrategy strategy;

    //所有有数据的日期
    List<LocalDate> allDatesWithData = new ArrayList<>();

    //所选股票池的代码和它的所有信息的映射
    Map<String, List<StrategyStock>> stockData = new TreeMap<>();

    @Before
    public void before() throws Exception {
        stockDao = new StockDaoImpl();

        List<String> stockPool = new LinkedList<>();
        stockPool.add("000001");
//        stockPool.add("000002");

        List<BlockType> blockTypeList = new LinkedList<>();
        blockTypeList.add(BlockType.ALL);

        StockPoolCriteriaVO vo = new StockPoolCriteriaVO(StType.INCLUDE, blockTypeList);
        TraceBackCriteriaVO criteriaVO = new TraceBackCriteriaVO(LocalDate.of(2014, 1,1), LocalDate.of(2014, 4,29),
                5, 10, vo, TraceBackStrategy.MR, 100, null);

        setDates();
        setStockData(stockPool);

        strategy = new MeanReversionStrategy(stockPool, criteriaVO, allDatesWithData, stockData);
    }

    private void setStockData(List<String> stockPoolCodes) throws IOException, NoDataWithinException, DateNotWithinException {
        for(int i = 0; i < stockPoolCodes.size(); i++){
            stockData.put(stockPoolCodes.get(i), convertStockPOS(stockDao.getStockData(stockPoolCodes.get(i))));
        }
    }

    private void setDates() throws IOException {
        //获取所有有数据的日期
        allDatesWithData = stockDao.getDateWithData();
    }

    private List<StrategyStock> convertStockPOS(List<StockPO> pos) {
        List<StrategyStock> result = new LinkedList<>();
        for (StockPO thisPO : pos) {
            result.add(new StrategyStock(thisPO));
        }
        return result;
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: traceBack()
     */
    @Test
    public void testTraceBack() throws Exception {
        TraceBackStrategyVO vo = strategy.traceBack();
        assertEquals(8, vo.holdingDetailVOS.size());
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
