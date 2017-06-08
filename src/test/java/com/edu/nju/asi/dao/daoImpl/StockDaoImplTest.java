package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.StockDao;
import com.edu.nju.asi.infoCarrier.traceBack.StockPool;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.edu.nju.asi.utilities.enums.Market;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Byron Dong on 2017/3/11.
 * Last updated by cuihua
 * Update time 2017/3/13
 */
public class StockDaoImplTest {

    private StockDao stockDao;

    @Before
    public void before() throws Exception {
        this.stockDao = new StockDaoImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getStockData(String stockCode, LocalDate date)
     */
    @Test
    public void getStockData1_1() throws Exception {
        Stock testPO = stockDao.getStockData("000026", LocalDate.of(2014, 2, 25));

        // expected:
        // 45	2014-02-25	7.53	7.66	7.25	7.29	117079	7.29	26	飞亚达Ａ	SZ	7.55	7.55
//
//        assertEquals(LocalDate.of(2014, 2, 25), testPO.getStockID().getDate());
//        assertEquals(7.53, testPO.getOpen(), 0);
//        assertEquals(7.66, testPO.getHigh(), 0);
//        assertEquals(7.25, testPO.getLow(), 0);
//        assertEquals(7.29, testPO.getClose(), 0);
//        assertEquals("117079", testPO.getVolume());
//        assertEquals(7.29, testPO.getAdjClose(), 0);
//        assertEquals("000026", testPO.getStockID().getCode());
//        assertEquals("飞亚达Ａ", testPO.getName());
//        assertEquals(7.55, testPO.getPreClose(), 0);
//        assertEquals(7.55, testPO.getPreAdjClose(), 0);
    }

    @Test
    public void getStockData1_2() throws Exception {
        Stock testPO = stockDao.getStockData("002001", LocalDate.of(2012, 10, 30));

        // expected:
        // 388	2012-10-30	18.69	18.89	18.5	18.62	8408	18.15	2001	新 和 成	SZ	18.72	18.24

//        assertEquals(LocalDate.of(2012, 10, 30), testPO.getStockID().getDate());
//        assertEquals(18.69, testPO.getOpen(), 0);
//        assertEquals(18.89, testPO.getHigh(), 0);
//        assertEquals(18.5, testPO.getLow(), 0);
//        assertEquals(18.62, testPO.getClose(), 0);
//        assertEquals("8408", testPO.getVolume());
//        assertEquals(18.15, testPO.getAdjClose(), 0);
//        assertEquals("002001", testPO.getStockID().getCode());
//        assertEquals("新 和 成", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(18.72, testPO.getPreClose(), 0);
//        assertEquals(18.24, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getStockData(LocalDate start, LocalDate end, String stockCode)
     */
    @Test
    public void getStockData2_1() throws Exception {
        List<Stock> result = stockDao.getStockData("000001", LocalDate.of(2014, 4, 25), LocalDate.of(2014, 4, 29));

        Stock testPO = result.get(1);

        // expected:
        // 1	2014-04-28	11.25	11.28	10.96	11.03	526045	11.03	1	深发展Ａ	SZ	11.25	11.25

//        assertEquals(LocalDate.of(2014, 4, 28), testPO.getStockID().getDate());
//        assertEquals(11.25, testPO.getOpen(), 0);
//        assertEquals(11.28, testPO.getHigh(), 0);
//        assertEquals(10.96, testPO.getLow(), 0);
//        assertEquals(11.03, testPO.getClose(), 0);
//        assertEquals("526045", testPO.getVolume());
//        assertEquals(11.03, testPO.getAdjClose(), 0);
//        assertEquals("000001", testPO.getStockID().getCode());
//        assertEquals("深发展Ａ", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(11.25, testPO.getPreClose(), 0);
//        assertEquals(11.25, testPO.getPreAdjClose(), 0);

    }

    @Test
    public void getStockData2_2() throws Exception {
        List<Stock> result = stockDao.getStockData("002067", LocalDate.of(2011, 1, 14), LocalDate.of(2011, 1, 24));

        // expected:
        // 838	2011-01-14	3.44	3.45	3.35	3.42	76905	1.67	2067	景兴纸业	SZ	3.45	1.68
        // 837	2011-01-17	3.4	3.43	3.25	3.25	108437	1.58	2067	景兴纸业	SZ	3.42	1.67
        // 836	2011-01-18	3.25	3.31	3.23	3.3	66930	1.6	2067	景兴纸业	SZ	3.25	1.58
        // 835	2011-01-19	3.31	3.35	3.28	3.35	86315	1.63	2067	景兴纸业	SZ	3.3	1.6
        // 834	2011-01-20	3.32	3.34	3.26	3.26	103575	1.59	2067	景兴纸业	SZ	3.35	1.63
        // 833	2011-01-21	3.24	3.32	3.23	3.29	71037	1.6	2067	景兴纸业	SZ	3.26	1.59
        // 832	2011-01-24	3.29	3.31	3.23	3.26	60536	1.59	2067	景兴纸业	SZ	3.29	1.6

//        assertEquals(7, result.size());
//
//        Stock testPO = result.get(1);
//        assertEquals(LocalDate.of(2011, 1, 17), testPO.getStockID().getDate());
//        assertEquals(3.4, testPO.getOpen(), 0);
//        assertEquals(3.43, testPO.getHigh(), 0);
//        assertEquals(3.25, testPO.getLow(), 0);
//        assertEquals(3.25, testPO.getClose(), 0);
//        assertEquals("108437", testPO.getVolume());
//        assertEquals(1.58, testPO.getAdjClose(), 0);
//        assertEquals("002067", testPO.getStockID().getCode());
//        assertEquals("景兴纸业", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(3.42, testPO.getPreClose(), 0);
//        assertEquals(1.67, testPO.getPreAdjClose(), 0);
    }

    @Test
    public void getStockData2_3() throws Exception {
        List<Stock> result = stockDao.getStockData("002067", LocalDate.of(2011, 1, 15), LocalDate.of(2011, 1, 24));

        // expected:
        // 837	2011-01-17	3.4	3.43	3.25	3.25	108437	1.58	2067	景兴纸业	SZ	3.42	1.67
        // 836	2011-01-18	3.25	3.31	3.23	3.3	66930	1.6	2067	景兴纸业	SZ	3.25	1.58
        // 835	2011-01-19	3.31	3.35	3.28	3.35	86315	1.63	2067	景兴纸业	SZ	3.3	1.6
        // 834	2011-01-20	3.32	3.34	3.26	3.26	103575	1.59	2067	景兴纸业	SZ	3.35	1.63
        // 833	2011-01-21	3.24	3.32	3.23	3.29	71037	1.6	2067	景兴纸业	SZ	3.26	1.59
        // 832	2011-01-24	3.29	3.31	3.23	3.26	60536	1.59	2067	景兴纸业	SZ	3.29	1.6

        assertEquals(6, result.size());

        Stock testPO = result.get(0);
//        assertEquals(LocalDate.of(2011, 1, 17), testPO.getStockID().getDate());
//        assertEquals(3.4, testPO.getOpen(), 0);
//        assertEquals(3.43, testPO.getHigh(), 0);
//        assertEquals(3.25, testPO.getLow(), 0);
//        assertEquals(3.25, testPO.getClose(), 0);
//        assertEquals("108437", testPO.getVolume());
//        assertEquals(1.58, testPO.getAdjClose(), 0);
//        assertEquals("002067", testPO.getStockID().getCode());
//        assertEquals("景兴纸业", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(3.42, testPO.getPreClose(), 0);
//        assertEquals(1.67, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getStockData(String stockCode)
     */
    @Test
    public void getStockData3_1() throws Exception {
        List<Stock> result = stockDao.getStockData("000001");
        assertEquals(2094, result.size());

        Stock testPO = result.get(10);

        // expected:
        // 2365	2005-02-24	3.21	3.21	3.16	3.19	103734	1.49	1	深发展Ａ	SZ	3.21	1.5

//        assertEquals(LocalDate.of(2005, 2, 24), testPO.getStockID().getDate());
//        assertEquals(3.21, testPO.getOpen(), 0);
//        assertEquals(3.21, testPO.getHigh(), 0);
//        assertEquals(3.16, testPO.getLow(), 0);
//        assertEquals(3.19, testPO.getClose(), 0);
//        assertEquals("103734", testPO.getVolume());
//        assertEquals(1.49, testPO.getAdjClose(), 0);
//        assertEquals("000001", testPO.getStockID().getCode());
//        assertEquals("深发展Ａ", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(3.21, testPO.getPreClose(), 0);
//        assertEquals(1.5, testPO.getPreAdjClose(), 0);


    }

    @Test
    public void getStockData3_2() throws Exception {
        List<Stock> result = stockDao.getStockData("002077");
        assertEquals(1762, result.size());

        Stock testPO = result.get(1761);

        // expected:
        // 0	2014-04-29	6.09	6.19	6.02	6.19	11719	6.19	2077	大港股份	SZ	6.09	6.09

//        assertEquals(LocalDate.of(2014, 4, 29), testPO.getStockID().getDate());
//        assertEquals(6.09, testPO.getOpen(), 0);
//        assertEquals(6.19, testPO.getHigh(), 0);
//        assertEquals(6.02, testPO.getLow(), 0);
//        assertEquals(6.19, testPO.getClose(), 0);
//        assertEquals("11719", testPO.getVolume());
//        assertEquals(6.19, testPO.getAdjClose(), 0);
//        assertEquals("002077", testPO.getStockID().getCode());
//        assertEquals("大港股份", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(6.09, testPO.getPreClose(), 0);
//        assertEquals(6.09, testPO.getPreAdjClose(), 0);


    }

    /**
     * Method: getStockData(LocalDate date)
     */
    @Test
    public void getStockData4_1() throws Exception {
        List<Stock> result = stockDao.getStockData(LocalDate.of(2009, 4, 7));
        assertEquals(317, result.size());

        Stock testPO = result.get(69);

        // expected:
        // 1284	2009-04-07	18.78	18.88	18.46	18.62	59933	10.88	2028	思源电气	SZ	18.81	10.99

//        assertEquals(LocalDate.of(2009, 4, 7), testPO.getStockID().getDate());
//        assertEquals(18.78, testPO.getOpen(), 0);
//        assertEquals(18.88, testPO.getHigh(), 0);
//        assertEquals(18.46, testPO.getLow(), 0);
//        assertEquals(18.62, testPO.getClose(), 0);
//        assertEquals("59933", testPO.getVolume());
//        assertEquals(10.88, testPO.getAdjClose(), 0);
//        assertEquals("002028", testPO.getStockID().getCode());
//        assertEquals("思源电气", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(18.81, testPO.getPreClose(), 0);
//        assertEquals(10.99, testPO.getPreAdjClose(), 0);
    }

    @Test
    public void getStockData4_2() throws Exception {
        List<Stock> result = stockDao.getStockData(LocalDate.of(2008, 8, 8));
        assertEquals(305, result.size());

        Stock testPO = result.get(233);

        // expected:
        // 1446	2008-08-08	2.3	2.31	2.16	2.16	24555	0.43	2198	嘉应制药	SZ	2.36	0.46

//        assertEquals(LocalDate.of(2008, 8, 8), testPO.getStockID().getDate());
//        assertEquals(2.3, testPO.getOpen(), 0);
//        assertEquals(2.31, testPO.getHigh(), 0);
//        assertEquals(2.16, testPO.getLow(), 0);
//        assertEquals(2.16, testPO.getClose(), 0);
//        assertEquals("24555", testPO.getVolume());
//        assertEquals(0.43, testPO.getAdjClose(), 0);
//        assertEquals("002198", testPO.getStockID().getCode());
//        assertEquals("嘉应制药", testPO.getName());
//        assertEquals(Market.SZ, testPO.getMarket());
//        assertEquals(2.36, testPO.getPreClose(), 0);
//        assertEquals(0.46, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getDateWithoutData(String stockCode)
     */
    @Test
    public void testGetDateWithoutData_11() throws Exception {
        List<LocalDate> result = stockDao.getDateWithoutData("000017");
        assertEquals(2209, result.size());
    }

    /**
     * Method: getDateWithoutData(String stockCode, LocalDate start, LocalDate end)
     */
    @Test
    public void testGetDateWithoutData_21() throws Exception {
        List<LocalDate> result = stockDao.getDateWithoutData("000017", LocalDate.of(2014, 4, 10), LocalDate.of(2014, 4, 29));
        assertEquals(6, result.size());
    }

    @Test
    public void testGetDateWithoutData_22() throws Exception {
        List<LocalDate> result = stockDao.getDateWithoutData("002037", LocalDate.of(2010, 6, 1), LocalDate.of(2010, 6, 30));
        assertEquals(11, result.size());
    }

    @Test
    public void testGetDateWithoutData_23() throws Exception {
        List<LocalDate> result = stockDao.getDateWithoutData("300122", LocalDate.of(2010, 9, 28), LocalDate.of(2010, 11, 6));
        assertEquals(16, result.size());
    }


    /**
     * Method: getPrivateStocks(String userName, LocalDate date)
     */
    @Ignore
    @Test
    public void getPrivateStocks() throws Exception {
    }

    /**
     * Method: addPrivateStock(String userName, String stockCode)
     */
    @Ignore
    @Test
    public void addPrivateStock() throws Exception {
    }

    /**
     * Method: deletePrivateStock(String userName, String stockCode)
     */
    @Ignore
    @Test
    public void deletePrivateStock() throws Exception {
    }

    /**
     * Method: createPrivateDir(String userName)
     */
    @Ignore
    @Test
    public void createPrivateDir() throws Exception {
    }

    /**
     * Method: getFirstDay(String code)
     */
    @Test
    public void getFirstAndLastDay() throws Exception {
        assertEquals(LocalDate.of(2005, 2, 1), stockDao.getFirstAndLastDay("000017").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDao.getFirstAndLastDay("000017").get(1));
        assertEquals(LocalDate.of(2009, 8, 28), stockDao.getFirstAndLastDay("002286").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDao.getFirstAndLastDay("002286").get(1));
        assertEquals(LocalDate.of(2009, 12, 18), stockDao.getFirstAndLastDay("002324").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDao.getFirstAndLastDay("002324").get(1));
        assertEquals(LocalDate.of(2010, 8, 12), stockDao.getFirstAndLastDay("300103").get(0));
        assertEquals(LocalDate.of(2014, 4, 29), stockDao.getFirstAndLastDay("300103").get(1));

    }

    // 将接口改为public测试的，测完之后恢复为private
//    @Test
//    public void isDateWithinSource() throws Exception {
//        assertEquals(true, stockDao.isDateWithinSource("17", LocalDate.of(2005, 2, 1), LocalDate.of(2014,2,1)));
//        assertEquals(false, stockDao.isDateWithinSource("17", LocalDate.of(2004, 2, 1), LocalDate.of(2014,2,1)));
//        assertEquals(false, stockDao.isDateWithinSource("17", LocalDate.of(2005, 1, 31), LocalDate.of(2014,2,1)));
//        assertEquals(true, stockDao.isDateWithinSource("2286", LocalDate.of(2009, 8, 28), LocalDate.of(2014,2,1)));
//        assertEquals(false, stockDao.isDateWithinSource("2286", LocalDate.of(2009, 8, 27), LocalDate.of(2014,2,1)));
//        assertEquals(true, stockDao.isDateWithinSource("2286", LocalDate.of(2009, 8, 28), LocalDate.of(2014,4,29)));
//        assertEquals(false, stockDao.isDateWithinSource("2286", LocalDate.of(2009, 2, 28), LocalDate.of(2014,4,29)));
//        assertEquals(false, stockDao.isDateWithinSource("2286", LocalDate.of(2005, 2, 1), LocalDate.of(2014,2,1)));
//    }


    @Test
    public void getAllStockPool() throws IOException, UnhandleBlockTypeException {
        List<StockPool> result = stockDao.getAllStockPool();
    }
}