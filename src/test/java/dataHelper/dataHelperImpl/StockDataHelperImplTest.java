package dataHelper.dataHelperImpl;

import dataHelper.StockDataHelper;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import po.StockPO;

import java.time.LocalDate;
import java.util.List;

/**
 * StockDataHelperImpl Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>03/05/2017</pre>
 */
public class StockDataHelperImplTest extends TestCase {

    private StockDataHelper stockDataHelper = new StockDataHelperImpl();

    public StockDataHelperImplTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: getStock(String stockCode)
     */
    public void testGetStock01() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("1");
        TestCase.assertEquals(2383, result.size());

        StockPO thisStock = result.get(0);
        TestCase.assertEquals(LocalDate.of(2014, 4, 29), thisStock.getDate());
        TestCase.assertEquals(11.25, thisStock.getHigh(), 0);
        TestCase.assertEquals(10.92, thisStock.getLow(), 0);
        TestCase.assertEquals(11.02, thisStock.getOpen(), 0);
        TestCase.assertEquals(11.16, thisStock.getClose(), 0);
        TestCase.assertEquals("413621", thisStock.getVolume());

    }

    public void testGetStock02() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("2044");
        TestCase.assertEquals(2291, result.size());
    }

    public void testGetStock03() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("300010");
        TestCase.assertEquals(1038, result.size());
    }

    public void testGetStock04() throws Exception {
        List<StockPO> result = stockDataHelper.getStockRecords("300187");
        TestCase.assertEquals(756, result.size());
    }


    public static Test suite() {
        return new TestSuite(StockDataHelperImplTest.class);
    }
} 