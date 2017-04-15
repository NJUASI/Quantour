package dataHelper.dataHelperImpl;

import dataHelper.StockDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import po.StockPO;

import java.io.IOException;
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
        List<StockPO> result = stockDataHelper.getStockRecords("000001");
        assertEquals(2100, result.size());

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
        List<StockPO> result = stockDataHelper.getStockRecords("002044");
        assertEquals(2117, result.size());
    }
    @Test
    public void testGetStockStockCode03() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("300010");
        assertEquals(933, result.size());
    }
    @Test
    public void testGetStockStockCode04() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("300187");
        assertEquals(711, result.size());
    }

    /**
     * Method: getStockRecords(LocalDate date)
     */
    @Test
    public void testGetStockRecordsDate01() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords(LocalDate.of(2008, 4, 7));

        // expected:
        // 1496	2008-04-07	12.5	13.2	12.31	13.2	18026	12.87	000019	深深宝Ａ	SZ	12	11.7

        StockPO testPO = result.get(9);
        assertEquals(1496, testPO.getSerial());
        assertEquals(12.5, testPO.getOpen(), 0);
        assertEquals(13.2, testPO.getHigh(), 0);
        assertEquals(12.31, testPO.getLow(), 0);
        assertEquals(13.2, testPO.getClose(), 0);
        assertEquals("18026", testPO.getVolume());
        assertEquals(12.87, testPO.getAdjClose(), 0);
        assertEquals("000019", testPO.getCode());
        assertEquals("深深宝Ａ", testPO.getName());
        assertEquals(12, testPO.getPreClose(), 0);
        assertEquals(11.7, testPO.getPreAdjClose(), 0);
    }
    @Test
    public void testGetStockRecordsDate02() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords(LocalDate.of(2011, 2, 25));

        // expected:
        // 813	2011-02-25	9.33	9.39	9	9.23	93635	9.15	000023	深天地Ａ	SZ	9.47	9.39

        StockPO testPO = result.get(13);
        assertEquals(813, testPO.getSerial());
        assertEquals(9.33, testPO.getOpen(), 0);
        assertEquals(9.39, testPO.getHigh(), 0);
        assertEquals(9, testPO.getLow(), 0);
        assertEquals(9.23, testPO.getClose(), 0);
        assertEquals("93635", testPO.getVolume());
        assertEquals(9.15, testPO.getAdjClose(), 0);
        assertEquals("000023", testPO.getCode());
        assertEquals("深天地Ａ", testPO.getName());
        assertEquals(9.47, testPO.getPreClose(), 0);
        assertEquals(9.39, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getFirstDay(String code)
     */
    @Test
    public void testGetFirstAndLastDay() throws Exception {
        assertEquals(LocalDate.of(2005, 2, 1), stockDataHelper.getFirstAndLastDay("000017").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDataHelper.getFirstAndLastDay("000017").get(1));
        assertEquals(LocalDate.of(2007, 12, 19), stockDataHelper.getFirstAndLastDay("002198").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDataHelper.getFirstAndLastDay("002198").get(1));
        assertEquals(LocalDate.of(2007, 12, 3), stockDataHelper.getFirstAndLastDay("002189").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDataHelper.getFirstAndLastDay("002189").get(1));
    }

    /**
     * Method: getDateWithoutData(String stockCode)
     */
    @Test
    public void testGetDateWithoutData() throws IOException {
        List<LocalDate> result = stockDataHelper.getDateWithoutData("000017");
        assertEquals(2207, result.size());
    }

    /**
     * Method: getDateWithData()
     */
    @Test
    public void testGetDateWithData() throws IOException {
        List<LocalDate> result = stockDataHelper.getDateWithData();
        assertEquals(2246, result.size());
    }

}

