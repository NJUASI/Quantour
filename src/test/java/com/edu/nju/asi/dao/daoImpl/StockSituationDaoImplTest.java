package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.model.StockSituation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        StockSituation thisStockSituation = this.stockSituationDao.getStockSituation(LocalDate.of(2009, 1, 9));

        assertEquals("26794465", thisStockSituation.getVolume());
        assertEquals(5, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(104, thisStockSituation.getSurgingNum());
        assertEquals(0, thisStockSituation.getSlumpingNum());
        assertEquals(108, thisStockSituation.getClimbingNum());
        assertEquals(0, thisStockSituation.getSlipingNum());
    }

    @Test
    public void getStockSituation_2() throws Exception {
        StockSituation thisStockSituation = this.stockSituationDao.getStockSituation(LocalDate.of(2014, 4, 29));

        assertEquals("27844947", thisStockSituation.getVolume());
        assertEquals(4, thisStockSituation.getLimitUpNum());
        assertEquals(3, thisStockSituation.getLimitDownNum());
        assertEquals(32, thisStockSituation.getSurgingNum());
        assertEquals(18, thisStockSituation.getSlumpingNum());
        assertEquals(38, thisStockSituation.getClimbingNum());
        assertEquals(2, thisStockSituation.getSlipingNum());

    }

    @Test
    public void getStockSituation_3() throws Exception {
        StockSituation thisStockSituation = this.stockSituationDao.getStockSituation(LocalDate.of(2005, 12, 30));

        assertEquals("5187455", thisStockSituation.getVolume());
        assertEquals(0, thisStockSituation.getLimitUpNum());
        assertEquals(0, thisStockSituation.getLimitDownNum());
        assertEquals(2, thisStockSituation.getSurgingNum());
        assertEquals(1, thisStockSituation.getSlumpingNum());
        assertEquals(1, thisStockSituation.getClimbingNum());
        assertEquals(1, thisStockSituation.getSlipingNum());
    }

    @Test
    public void getStockSituation_4() throws Exception {
        StockSituation thisStockSituation = this.stockSituationDao.getStockSituation(LocalDate.of(2013, 6, 7));

        assertEquals("42804884", thisStockSituation.getVolume());
        assertEquals(4, thisStockSituation.getLimitUpNum());
        assertEquals(1, thisStockSituation.getLimitDownNum());
        assertEquals(15, thisStockSituation.getSurgingNum());
        assertEquals(45, thisStockSituation.getSlumpingNum());
        assertEquals(9, thisStockSituation.getClimbingNum());
        assertEquals(35, thisStockSituation.getSlipingNum());
    }



}