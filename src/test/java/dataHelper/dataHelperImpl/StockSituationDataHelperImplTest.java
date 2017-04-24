package dataHelper.dataHelperImpl;

import dataHelper.StockSituationDataHelper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import po.StockSituationPO;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * StockSituationDataHelperImpl Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>03/09/2017</pre>
 */
public class StockSituationDataHelperImplTest {

    private StockSituationDataHelper stockSituationDataHelper;

    @Before
    public void before() throws Exception {
        stockSituationDataHelper = new StockSituationDataHelperImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getStockSituation(LocalDate date)
     */
    @Test
    public void testGetStockSituation01() throws Exception {
        StockSituationPO thisStockSituation = stockSituationDataHelper.getStockSituation(LocalDate.of(2009, 1, 9));

        assertEquals("26794465", thisStockSituation.getVolume());
        assertEquals(5, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(104, thisStockSituation.getSurgingNum());
        assertEquals(0, thisStockSituation.getSlumpingNum());
        assertEquals(108, thisStockSituation.getClimbingNum());
        assertEquals(0, thisStockSituation.getSlipingNum());
    }

    @Test
    public void testGetStockSituation02() throws Exception {
        StockSituationPO thisStockSituation = stockSituationDataHelper.getStockSituation(LocalDate.of(2014, 4, 29));

        assertEquals("27844947", thisStockSituation.getVolume());
        assertEquals(4, thisStockSituation.getLimitUpNum());
        assertEquals(3, thisStockSituation.getLimitDownNum());
        assertEquals(32, thisStockSituation.getSurgingNum());
        assertEquals(18, thisStockSituation.getSlumpingNum());
        assertEquals(38, thisStockSituation.getClimbingNum());
        assertEquals(2, thisStockSituation.getSlipingNum());
    }

    @Test
    public void testGetStockSituation03() throws Exception {
        StockSituationPO thisStockSituation = stockSituationDataHelper.getStockSituation(LocalDate.of(2008, 4, 7));

        assertEquals("11270732", thisStockSituation.getVolume());
        assertEquals(9, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(178, thisStockSituation.getSurgingNum());
        assertEquals(1, thisStockSituation.getSlumpingNum());
        assertEquals(221, thisStockSituation.getClimbingNum());
        assertEquals(0, thisStockSituation.getSlipingNum());
    }

    @Test
    public void testGetStockSituation04() throws Exception {
        StockSituationPO thisStockSituation = stockSituationDataHelper.getStockSituation(LocalDate.of(2012, 6, 8));
        assertEquals("26271420", thisStockSituation.getVolume());
        assertEquals(9, thisStockSituation.getLimitUpNum());
        assertEquals(1, thisStockSituation.getLimitDownNum());
        assertEquals(22, thisStockSituation.getSurgingNum());
        assertEquals(5, thisStockSituation.getSlumpingNum());
        assertEquals(7, thisStockSituation.getClimbingNum());
        assertEquals(10, thisStockSituation.getSlipingNum());
    }
}
