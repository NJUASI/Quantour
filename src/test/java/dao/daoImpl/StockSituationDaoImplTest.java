package dao.daoImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import po.StockSituationPO;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by Byron Dong on 2017/3/6.
 * Last updated by cuihua
 * Update time 2017/3/13
 */
public class StockSituationDaoImplTest {

    private StockSituationDaoImpl stockSituationDao;

    @Before
    public void before() throws Exception {
        this.stockSituationDao = new StockSituationDaoImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getStockSituation(LocalDate date)
     */
    @Test
    public void getStockSituation_1() throws Exception {
        StockSituationPO thisStockSituationPO = this.stockSituationDao.getStockSituation(LocalDate.of(2009, 1, 9));

        assertEquals("26794465", thisStockSituationPO.getVolume());
        assertEquals(95, thisStockSituationPO.getLimitUpNum());
        assertEquals(0, thisStockSituationPO.getLimitDownNum());
        assertEquals(108, thisStockSituationPO.getSurgingNum());
        assertEquals(0, thisStockSituationPO.getSlumpingNum());
        assertEquals(108, thisStockSituationPO.getClimbingNum());
        assertEquals(0, thisStockSituationPO.getSlipingNum());
    }

    @Test
    public void getStockSituation_2() throws Exception {
        StockSituationPO thisStockSituation = this.stockSituationDao.getStockSituation(LocalDate.of(2014, 4, 29));

        assertEquals("27844947", thisStockSituation.getVolume());
        assertEquals(5, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(31, thisStockSituation.getSurgingNum());
        assertEquals(15, thisStockSituation.getSlumpingNum());
        assertEquals(31, thisStockSituation.getClimbingNum());
        assertEquals(15, thisStockSituation.getSlipingNum());

    }

    @Test
    public void getStockSituation_3() throws Exception {
        StockSituationPO thisStockSituation = this.stockSituationDao.getStockSituation(LocalDate.of(2005, 12, 30));

        assertEquals("5187455", thisStockSituation.getVolume());
        assertEquals(2, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(2, thisStockSituation.getSurgingNum());
        assertEquals(0, thisStockSituation.getSlumpingNum());
        assertEquals(1, thisStockSituation.getClimbingNum());
        assertEquals(1, thisStockSituation.getSlipingNum());
    }

    @Test
    public void getStockSituation_4() throws Exception {
        StockSituationPO thisStockSituation = this.stockSituationDao.getStockSituation(LocalDate.of(2013, 6, 7));

        assertEquals("42804884", thisStockSituation.getVolume());
        assertEquals(7, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(12, thisStockSituation.getSurgingNum());
        assertEquals(33, thisStockSituation.getSlumpingNum());
        assertEquals(8, thisStockSituation.getClimbingNum());
        assertEquals(37, thisStockSituation.getSlipingNum());
    }



}