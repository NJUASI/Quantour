package dataHelper.dataHelperImpl;

import dataHelper.StockDataHelper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
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
        List<StockPO> result = stockDataHelper.getStockRecords("1");
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
        List<StockPO> result = stockDataHelper.getStockRecords("2044");
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
        // 1545	2008-04-07	5.38	5.94	5.31	5.9	190431	0.71	157	中联重科	SZ	5.45	0.66

        StockPO testPO = result.get(9);
        assertEquals(1545, testPO.getSerial());
        assertEquals(5.38, testPO.getOpen(), 0);
        assertEquals(5.94, testPO.getHigh(), 0);
        assertEquals(5.31, testPO.getLow(), 0);
        assertEquals(5.9, testPO.getClose(), 0);
        assertEquals("190431", testPO.getVolume());
        assertEquals(0.71, testPO.getAdjClose(), 0);
        assertEquals("000157", testPO.getCode());
        assertEquals("中联重科", testPO.getName());
        assertEquals(5.45, testPO.getPreClose(), 0);
        assertEquals(0.66, testPO.getPreAdjClose(), 0);
    }
    @Test
    public void testGetStockRecordsDate02() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords(LocalDate.of(2011, 2, 25));

        // expected:
        // 813	2011-02-25	5.38	5.47	5.38	5.47	13956	5.47	17	*ST中华A	SZ	5.39	5.39

        StockPO testPO = result.get(13);
        assertEquals(813, testPO.getSerial());
        assertEquals(5.38, testPO.getOpen(), 0);
        assertEquals(5.47, testPO.getHigh(), 0);
        assertEquals(5.38, testPO.getLow(), 0);
        assertEquals(5.47, testPO.getClose(), 0);
        assertEquals("13956", testPO.getVolume());
        assertEquals(5.47, testPO.getAdjClose(), 0);
        assertEquals("000017", testPO.getCode());
        assertEquals("*ST中华A", testPO.getName());
        assertEquals(5.39, testPO.getPreClose(), 0);
        assertEquals(5.39, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getFirstDay(String code)
     */
    @Test
    public void testGetFirstAndLastDay() throws Exception {
        assertEquals(LocalDate.of(2005, 2, 1), stockDataHelper.getFirstAndLastDay("17").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDataHelper.getFirstAndLastDay("17").get(1));
        assertEquals(LocalDate.of(2007, 12, 19), stockDataHelper.getFirstAndLastDay("2198").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDataHelper.getFirstAndLastDay("2198").get(1));
        assertEquals(LocalDate.of(2007, 12, 3), stockDataHelper.getFirstAndLastDay("2189").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDataHelper.getFirstAndLastDay("2189").get(1));
    }

    /**
     * Method: getDateWithoutData(String stockCode)
     */
    @Test
    public void testGetDateWithoutData() throws IOException {
        List<LocalDate> result = stockDataHelper.getDateWithoutData("17");
        assertEquals(2207, result.size());
    }
}

