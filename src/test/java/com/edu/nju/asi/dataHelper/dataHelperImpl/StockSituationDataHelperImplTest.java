package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSituationDataHelper;
import com.edu.nju.asi.model.StockSituation;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public class StockSituationDataHelperImplTest {

    private StockSituationDataHelper stockSituationDataHelper;

    @Before
    public void setUp() throws Exception {
        stockSituationDataHelper = HelperManager.stockSituationDataHelper;
    }

    @Test
    public void getStockSituation() throws Exception {
        StockSituation stockSituation1 = stockSituationDataHelper.getStockSituation(LocalDate.of(2017,2,12));
        StockSituation stockSituation2 = stockSituationDataHelper.getStockSituation(LocalDate.of(2017,3,12));
        StockSituation stockSituation3 = stockSituationDataHelper.getStockSituation(LocalDate.of(2015,7,16));
        StockSituation stockSituation4 = stockSituationDataHelper.getStockSituation(LocalDate.of(2016,2,16));

        assertEquals("6900",stockSituation1.getVolume());
        assertEquals(10,stockSituation1.getLimitUpNum());
        assertEquals(20,stockSituation1.getLimitDownNum());
        assertEquals(100,stockSituation1.getSurgingNum());
        assertEquals(20,stockSituation1.getSlumpingNum());
        assertEquals(10,stockSituation1.getClimbingNum());
        assertEquals(20,stockSituation1.getSlipingNum());

        assertEquals("130",stockSituation2.getVolume());
        assertEquals(10,stockSituation2.getLimitUpNum());
        assertEquals(20,stockSituation2.getLimitDownNum());
        assertEquals(100,stockSituation2.getSurgingNum());
        assertEquals(20,stockSituation2.getSlumpingNum());
        assertEquals(10,stockSituation2.getClimbingNum());
        assertEquals(20,stockSituation2.getSlipingNum());

        assertEquals("900",stockSituation3.getVolume());
        assertEquals(10,stockSituation3.getLimitUpNum());
        assertEquals(20,stockSituation3.getLimitDownNum());
        assertEquals(100,stockSituation3.getSurgingNum());
        assertEquals(20,stockSituation3.getSlumpingNum());
        assertEquals(10,stockSituation3.getClimbingNum());
        assertEquals(20,stockSituation3.getSlipingNum());

        assertEquals("600",stockSituation4.getVolume());
        assertEquals(10,stockSituation4.getLimitUpNum());
        assertEquals(60,stockSituation4.getLimitDownNum());
        assertEquals(100,stockSituation4.getSurgingNum());
        assertEquals(20,stockSituation4.getSlumpingNum());
        assertEquals(10,stockSituation4.getClimbingNum());
        assertEquals(20,stockSituation4.getSlipingNum());


    }

    @Test
    public void updateStockSituation() throws Exception {
        StockSituation stockSituation3 = new StockSituation("6900",10,20,100,20,10,20);
        stockSituation3.setDate(LocalDate.of(2017,2,12));
        stockSituationDataHelper.updateStockSituation(stockSituation3);
    }

    @Test
    public void addStockSituation() throws Exception {
        StockSituation stockSituation1 = new StockSituation("100",10,20,100,20,10,20);
        stockSituation1.setDate(LocalDate.of(2017,2,12));
        StockSituation stockSituation2 = new StockSituation("130",10,20,100,20,10,20);
        stockSituation2.setDate(LocalDate.of(2017,3,12));
        StockSituation stockSituation3 = new StockSituation("900",10,20,100,20,10,20);
        stockSituation3.setDate(LocalDate.of(2015,7,16));
        StockSituation stockSituation4 = new StockSituation("600",10,60,100,20,10,20);
        stockSituation4.setDate(LocalDate.of(2016,2,16));
        stockSituationDataHelper.addStockSituation(stockSituation1);
        stockSituationDataHelper.addStockSituation(stockSituation2);
        stockSituationDataHelper.addStockSituation(stockSituation3);
        stockSituationDataHelper.addStockSituation(stockSituation4);
    }

}