package dao.daoImpl;

import dao.StockDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import po.StockPO;
import utilities.enums.Market;

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
        StockPO testPO = stockDao.getStockData("26", LocalDate.of(2014, 2, 25));

        // expected:
        // 45	2014-02-25	7.53	7.66	7.25	7.29	117079	7.29	26	飞亚达Ａ	SZ	7.55	7.55

        assertEquals(45, testPO.getSerial());
        assertEquals(LocalDate.of(2014, 2, 25), testPO.getDate());
        assertEquals(7.53, testPO.getOpen(), 0);
        assertEquals(7.66, testPO.getHigh(), 0);
        assertEquals(7.25, testPO.getLow(), 0);
        assertEquals(7.29, testPO.getClose(), 0);
        assertEquals("117079", testPO.getVolume());
        assertEquals(7.29, testPO.getAdjClose(), 0);
        assertEquals("26", testPO.getCode());
        assertEquals("飞亚达Ａ", testPO.getName());
        assertEquals(7.55, testPO.getPreClose(), 0);
        assertEquals(7.55, testPO.getPreAdjClose(), 0);
    }

    @Test
    public void getStockData1_2() throws Exception {
        StockPO testPO = stockDao.getStockData("2001", LocalDate.of(2012, 10, 30));

        // expected:
        // 388	2012-10-30	18.69	18.89	18.5	18.62	8408	18.15	2001	新 和 成	SZ	18.72	18.24

        assertEquals(388, testPO.getSerial());
        assertEquals(LocalDate.of(2012, 10, 30), testPO.getDate());
        assertEquals(18.69, testPO.getOpen(), 0);
        assertEquals(18.89, testPO.getHigh(), 0);
        assertEquals(18.5, testPO.getLow(), 0);
        assertEquals(18.62, testPO.getClose(), 0);
        assertEquals("8408", testPO.getVolume());
        assertEquals(18.15, testPO.getAdjClose(), 0);
        assertEquals("2001", testPO.getCode());
        assertEquals("新 和 成", testPO.getName());
        assertEquals(Market.SZ, testPO.getMarket());
        assertEquals(18.72, testPO.getPreClose(), 0);
        assertEquals(18.24, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getStockData(LocalDate start, LocalDate end, String stockCode)
     */
    @Test
    public void getStockData2_1() throws Exception {
        List<StockPO> result = stockDao.getStockData("1", LocalDate.of(2014, 4, 25), LocalDate.of(2014, 4, 29));

        StockPO testPO = result.get(1);

        // expected:
        // 1	2014-04-28	11.25	11.28	10.96	11.03	526045	11.03	1	深发展Ａ	SZ	11.25	11.25

        assertEquals(1, testPO.getSerial());
        assertEquals(LocalDate.of(2014, 4, 28), testPO.getDate());
        assertEquals(11.25, testPO.getOpen(), 0);
        assertEquals(11.28, testPO.getHigh(), 0);
        assertEquals(10.96, testPO.getLow(), 0);
        assertEquals(11.03, testPO.getClose(), 0);
        assertEquals("526045", testPO.getVolume());
        assertEquals(11.03, testPO.getAdjClose(), 0);
        assertEquals("1", testPO.getCode());
        assertEquals("深发展Ａ", testPO.getName());
        assertEquals(Market.SZ, testPO.getMarket());
        assertEquals(11.25, testPO.getPreClose(), 0);
        assertEquals(11.25, testPO.getPreAdjClose(), 0);

    }

    @Test
    public void getStockData2_2() throws Exception {
        List<StockPO> result = stockDao.getStockData("2067", LocalDate.of(2011, 1, 14), LocalDate.of(2011, 1, 24));

        // expected:
        // 838	2011-01-14	3.44	3.45	3.35	3.42	76905	1.67	2067	景兴纸业	SZ	3.45	1.68
        // 837	2011-01-17	3.4	3.43	3.25	3.25	108437	1.58	2067	景兴纸业	SZ	3.42	1.67
        // 836	2011-01-18	3.25	3.31	3.23	3.3	66930	1.6	2067	景兴纸业	SZ	3.25	1.58
        // 835	2011-01-19	3.31	3.35	3.28	3.35	86315	1.63	2067	景兴纸业	SZ	3.3	1.6
        // 834	2011-01-20	3.32	3.34	3.26	3.26	103575	1.59	2067	景兴纸业	SZ	3.35	1.63
        // 833	2011-01-21	3.24	3.32	3.23	3.29	71037	1.6	2067	景兴纸业	SZ	3.26	1.59
        // 832	2011-01-24	3.29	3.31	3.23	3.26	60536	1.59	2067	景兴纸业	SZ	3.29	1.6

        assertEquals(7, result.size());

        StockPO testPO = result.get(1);
        assertEquals(837, testPO.getSerial());
        assertEquals(LocalDate.of(2011, 1, 17), testPO.getDate());
        assertEquals(3.4, testPO.getOpen(), 0);
        assertEquals(3.43, testPO.getHigh(), 0);
        assertEquals(3.25, testPO.getLow(), 0);
        assertEquals(3.25, testPO.getClose(), 0);
        assertEquals("108437", testPO.getVolume());
        assertEquals(1.58, testPO.getAdjClose(), 0);
        assertEquals("2067", testPO.getCode());
        assertEquals("景兴纸业", testPO.getName());
        assertEquals(Market.SZ, testPO.getMarket());
        assertEquals(3.42, testPO.getPreClose(), 0);
        assertEquals(1.67, testPO.getPreAdjClose(), 0);

    }

    /**
     * Method: getStockData(String stockCode)
     */
    @Test
    public void getStockData3_1() throws Exception {
        List<StockPO> result = stockDao.getStockData("1");
        assertEquals(2383, result.size());

        StockPO testPO = result.get(10);

        // expected:
        // 2372	2005-02-15	3.17	3.17	3.17	3.17	0	1.48	1	深发展Ａ	SZ	3.17	1.48

        assertEquals(2372, testPO.getSerial());
        assertEquals(LocalDate.of(2005, 2, 15), testPO.getDate());
        assertEquals(3.17, testPO.getOpen(), 0);
        assertEquals(3.17, testPO.getHigh(), 0);
        assertEquals(3.17, testPO.getLow(), 0);
        assertEquals(3.17, testPO.getClose(), 0);
        assertEquals("0", testPO.getVolume());
        assertEquals(1.48, testPO.getAdjClose(), 0);
        assertEquals("1", testPO.getCode());
        assertEquals("深发展Ａ", testPO.getName());
        assertEquals(Market.SZ, testPO.getMarket());
        assertEquals(3.17, testPO.getPreClose(), 0);
        assertEquals(1.48, testPO.getPreAdjClose(), 0);


    }

    @Test
    public void getStockData3_2() throws Exception {
        List<StockPO> result = stockDao.getStockData("2077");
        assertEquals(1882, result.size());

        StockPO testPO = result.get(1881);

        // expected:
        // 0	2014-04-29	6.09	6.19	6.02	6.19	11719	6.19	2077	大港股份	SZ	6.09	6.09

        assertEquals(0, testPO.getSerial());
        assertEquals(LocalDate.of(2014, 4, 29), testPO.getDate());
        assertEquals(6.09, testPO.getOpen(), 0);
        assertEquals(6.19, testPO.getHigh(), 0);
        assertEquals(6.02, testPO.getLow(), 0);
        assertEquals(6.19, testPO.getClose(), 0);
        assertEquals("11719", testPO.getVolume());
        assertEquals(6.19, testPO.getAdjClose(), 0);
        assertEquals("2077", testPO.getCode());
        assertEquals("大港股份", testPO.getName());
        assertEquals(Market.SZ, testPO.getMarket());
        assertEquals(6.09, testPO.getPreClose(), 0);
        assertEquals(6.09, testPO.getPreAdjClose(), 0);


    }

    /**
     * Method: getStockData(LocalDate date)
     */
    @Test
    public void getStockData4_1() throws Exception {
        List<StockPO> result = stockDao.getStockData(LocalDate.of(2009, 4, 7));
        assertEquals(316, result.size());

        StockPO testPO = result.get(46);

        // expected:
        // 1265	2009-04-07	3.76	3.89	3.73	3.88	41541	2.18	2026	山东威达	SZ	3.75	2.1

        assertEquals(1265, testPO.getSerial());
        assertEquals(LocalDate.of(2009, 4, 7), testPO.getDate());
        assertEquals(3.76, testPO.getOpen(), 0);
        assertEquals(3.89, testPO.getHigh(), 0);
        assertEquals(3.73, testPO.getLow(), 0);
        assertEquals(3.88, testPO.getClose(), 0);
        assertEquals("41541", testPO.getVolume());
        assertEquals(2.18, testPO.getAdjClose(), 0);
        assertEquals("2026", testPO.getCode());
        assertEquals("山东威达", testPO.getName());
        assertEquals(Market.SZ, testPO.getMarket());
        assertEquals(3.75, testPO.getPreClose(), 0);
        assertEquals(2.1, testPO.getPreAdjClose(), 0);
    }

    @Test
    public void getStockData4_2() throws Exception {
        List<StockPO> result = stockDao.getStockData(LocalDate.of(2008, 8, 8));
        assertEquals(308, result.size());

        StockPO testPO = result.get(211);

        // expected:
        // 1445	2008-08-08	5.78	5.82	5.39	5.4	5521	2.69	2192	路翔股份	SZ	5.82	2.9

        assertEquals(1445, testPO.getSerial());
        assertEquals(LocalDate.of(2008, 8, 8), testPO.getDate());
        assertEquals(5.78, testPO.getOpen(), 0);
        assertEquals(5.82, testPO.getHigh(), 0);
        assertEquals(5.39, testPO.getLow(), 0);
        assertEquals(5.4, testPO.getClose(), 0);
        assertEquals("5521", testPO.getVolume());
        assertEquals(2.69, testPO.getAdjClose(), 0);
        assertEquals("2192", testPO.getCode());
        assertEquals("路翔股份", testPO.getName());
        assertEquals(Market.SZ, testPO.getMarket());
        assertEquals(5.82, testPO.getPreClose(), 0);
        assertEquals(2.9, testPO.getPreAdjClose(), 0);
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
    public void getFirstDay() throws Exception {
        assertEquals(LocalDate.of(2005, 2, 1), stockDao.getFirstDay("17"));
        assertEquals(LocalDate.of(2009, 8, 28), stockDao.getFirstDay("2286"));
        assertEquals(LocalDate.of(2009, 12, 18), stockDao.getFirstDay("2324"));
        assertEquals(LocalDate.of(2010, 8, 12), stockDao.getFirstDay("300103"));
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
}