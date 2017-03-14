package dataHelper.dataHelperImpl;

import dataHelper.StockDataHelper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import po.StockPO;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * StockDataHelperImpl Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>03/09/2017</pre>
 */
public class StockDataHelperImplTest {

    private StockDataHelper stockDataHelper;


    @Before
    public void before() throws Exception {
        stockDataHelper = new StockDataHelperImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getStock(String stockCode)
     */
    @Test
    public void testGetStockStockCode01() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("1");
        assertEquals(2383, result.size());

        StockPO thisStock = result.get(0);
        assertEquals(LocalDate.of(2014, 4, 29), thisStock.getDate());
        assertEquals(11.25, thisStock.getHigh(), 0);
        assertEquals(10.92, thisStock.getLow(), 0);
        assertEquals(11.02, thisStock.getOpen(), 0);
        assertEquals(11.16, thisStock.getClose(), 0);
        assertEquals("413621", thisStock.getVolume());

    }
    @Test
    public void testGetStockStockCode02() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("2044");
        assertEquals(2291, result.size());
    }
    @Test
    public void testGetStockStockCode03() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("300010");
        assertEquals(1038, result.size());
    }
    @Test
    public void testGetStockStockCode04() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("300187");
        assertEquals(756, result.size());
    }

    /**
     * Method: getStockRecords(LocalDate date)
     */
    @Test
    public void testGetStockRecordsDate01() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords(LocalDate.of(2008, 4, 7));

        StockPO testPO = result.get(9);
        assertEquals(1560, testPO.getSerial());
        assertEquals(16.1, testPO.getOpen(), 0);
        assertEquals(16.1, testPO.getHigh(), 0);
        assertEquals(16.1, testPO.getLow(), 0);
        assertEquals(16.1, testPO.getClose(), 0);
        assertEquals("0", testPO.getVolume());
        assertEquals(15.65, testPO.getAdjClose(), 0);
        assertEquals("155", testPO.getCode());
        assertEquals("川化股份", testPO.getName());
        assertEquals(16.1, testPO.getPreClose(), 0);
        assertEquals(15.65, testPO.getPreAdjClose(), 0);
    }
    @Test
    public void testGetStockRecordsDate02() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords(LocalDate.of(2011, 2, 25));

        StockPO testPO = result.get(13);
        assertEquals(813, testPO.getSerial());
        assertEquals(5.38, testPO.getOpen(), 0);
        assertEquals(5.47, testPO.getHigh(), 0);
        assertEquals(5.38, testPO.getLow(), 0);
        assertEquals(5.47, testPO.getClose(), 0);
        assertEquals("13956", testPO.getVolume());
        assertEquals(5.47, testPO.getAdjClose(), 0);
        assertEquals("17", testPO.getCode());
        assertEquals("*ST中华A", testPO.getName());
        assertEquals(5.39, testPO.getPreClose(), 0);
        assertEquals(5.39, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getFirstDay(String code)
     */
    @Test
    public void testGetFirstDay() throws Exception {
        assertEquals(LocalDate.of(2014, 4, 29), stockDataHelper.getFirstDay("17"));
    }
}

