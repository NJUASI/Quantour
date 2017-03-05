package dataHelper.dataHelperImpl;

import dataHelper.StockDataHelper;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import po.StockPO;

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
    public void testGetStock() throws Exception {
        List<StockPO> result = stockDataHelper.getStock("1");
        assertEquals(2383, result.size());
    }


    public static Test suite() {
        return new TestSuite(StockDataHelperImplTest.class);
    }
} 
