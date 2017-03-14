package dataHelper.dataHelperImpl;

import dataHelper.PrivateStockDataHelper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * PrivateStockDataHelperImpl Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>03/10/2017</pre>
 */
public class PrivateStockDataHelperImplTest {

    PrivateStockDataHelper dataHelper;

    @Before
    public void before() throws Exception {
        dataHelper = new PrivateStockDataHelperImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getPrivateStockCode(String userName)
     */
    @Test
    public void testGetPrivateStockCode() throws Exception {
//        List<String> result = dataHelper.getPrivateStockCode("Harvey");
//        assertEquals(2, result.size());
//        assertEquals("Charles 3001", result.get(0));
//        assertEquals("Suzy 2056", result.get(1));
//        assertEquals("1", result.get(2));
    }

    /**
     * Method: addPrivateStock(String userName, String stockCode)
     */
    @Test
    public void testAddPrivateStock() throws Exception {
    }

    /**
     * Method: deletePrivateStock(String userName, String stockCode)
     */
    @Test
    public void testDeletePrivateStock() throws Exception {
    }

}
