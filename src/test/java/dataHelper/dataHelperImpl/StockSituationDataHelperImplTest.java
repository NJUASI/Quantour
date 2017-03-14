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
        assertEquals(95, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(108, thisStockSituation.getSurgingNum());
        assertEquals(0, thisStockSituation.getSlumpingNum());
        assertEquals(108, thisStockSituation.getClimbingNum());
        assertEquals(0, thisStockSituation.getSlipingNum());
    }

    @Test
    public void testGetStockSituation02() throws Exception {
        StockSituationPO thisStockSituation = stockSituationDataHelper.getStockSituation(LocalDate.of(2014, 4, 29));

        assertEquals("27844947", thisStockSituation.getVolume());
        assertEquals(5, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(31, thisStockSituation.getSurgingNum());
        assertEquals(15, thisStockSituation.getSlumpingNum());
        assertEquals(31, thisStockSituation.getClimbingNum());
        assertEquals(15, thisStockSituation.getSlipingNum());
    }

    @Test
    public void testGetStockSituation03() throws Exception {
        StockSituationPO thisStockSituation = stockSituationDataHelper.getStockSituation(LocalDate.of(2008, 4, 7));

        assertEquals("11270732", thisStockSituation.getVolume());
        assertEquals(202, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(215, thisStockSituation.getSurgingNum());
        assertEquals(0, thisStockSituation.getSlumpingNum());
        assertEquals(215, thisStockSituation.getClimbingNum());
        assertEquals(0, thisStockSituation.getSlipingNum());
    }

    @Test
    public void testGetStockSituation04() throws Exception {
        StockSituationPO thisStockSituation = stockSituationDataHelper.getStockSituation(LocalDate.of(2012, 6, 8));
        assertEquals("26271420", thisStockSituation.getVolume());
        assertEquals(8, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(9, thisStockSituation.getSurgingNum());
        assertEquals(2, thisStockSituation.getSlumpingNum());
        assertEquals(7, thisStockSituation.getClimbingNum());
        assertEquals(4, thisStockSituation.getSlipingNum());
    }
}
