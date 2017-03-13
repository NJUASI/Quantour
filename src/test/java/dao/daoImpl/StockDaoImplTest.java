package dao.daoImpl;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import po.StockPO;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Byron Dong on 2017/3/11.
 */
public class StockDaoImplTest {

    private StockDaoImpl stock;

    @Before
    public void setUp() throws Exception {
        this.stock = new StockDaoImpl();
    }

    /**
     * Method: getStockData(String stockCode)
     */
    @Test
    public void getStockData() throws Exception {

    }

    /**
     * Method: getStockData(String stockCode)
     */
    @Test
    public void getStockData1() throws Exception {
        List<StockPO> result = stock.getStockData("1");
        TestCase.assertEquals(2383, result.size());

    }

    /**
     * Method: getStockData(LocalDate date)
     * TODO charles
     */
    @Test
    public void getStockData2() throws Exception {
        List<StockPO> result = stock.getStockData(LocalDate.of(2008, 4, 7));
        assertEquals(265, result.size());

//        StockPO testPO = result.get(9);
//        assertEquals(1560, testPO.getSerial());
//        assertEquals(16.1, testPO.getOpen(), 0);
//        assertEquals(16.1, testPO.getHigh(), 0);
//        assertEquals(16.1, testPO.getLow(), 0);
//        assertEquals(16.1, testPO.getClose(), 0);
//        assertEquals("0", testPO.getVolume());
//        assertEquals(15.65, testPO.getAdjClose(), 0);
//        assertEquals("155", testPO.getCode());
//        assertEquals("川化股份", testPO.getName());
//        assertEquals(16.1, testPO.getPreClose(), 0);
//        assertEquals(15.65, testPO.getPreAdjClose(), 0);
    }

    /**
     * Method: getStockData(LocalDate start, LocalDate end, String stockCode)
     */
    @Test
    public void getStockData3() throws Exception {
        List<StockPO> result = stock.getStockData("1", LocalDate.of(2014, 4, 25), LocalDate.of(2014, 4, 29));

        //1	2014-04-28	11.25	11.28	10.96	11.03	526045	11.03	1	深发展Ａ	SZ	11.25	11.25

        StockPO testPO = result.get(1);
        assertEquals(1, testPO.getSerial());
        assertEquals(11.25, testPO.getOpen(), 0);
        assertEquals(11.28, testPO.getHigh(), 0);
        assertEquals(10.96, testPO.getLow(), 0);
        assertEquals(11.03, testPO.getClose(), 0);
        assertEquals("526045", testPO.getVolume());
        assertEquals(11.03, testPO.getAdjClose(), 0);
        assertEquals("1", testPO.getCode());
        assertEquals("深发展Ａ", testPO.getName());
        assertEquals(11.25, testPO.getPreClose(), 0);
        assertEquals(11.25, testPO.getPreAdjClose(), 0);

    }

    /**
     * Method: getPrivateStocks(String userName, LocalDate date)
     */
    @Test
    public void getPrivateStocks() throws Exception {

    }

    /**
     * Method: addPrivateStock(String userName, String stockCode)
     */
    @Test
    public void addPrivateStock() throws Exception {

    }

    /**
     * Method: deletePrivateStock(String userName, String stockCode)
     */
    @Ignore
    @Test
    public void deletePrivateStock() throws Exception {
        //TODO 龚尘淼
    }

    /**
     * Method: createPrivateDir(String userName)
     */
    @Ignore
    @Test
    public void createPrivateDir() throws Exception {
        //TODO 龚尘淼
    }

    /**
     * Method: getFirstDay(String code)
     */
    @Test
    public void getFirstDay() throws Exception {
        assertEquals(LocalDate.of(2014, 4, 29), stock.getFirstDay("17"));
    }

}