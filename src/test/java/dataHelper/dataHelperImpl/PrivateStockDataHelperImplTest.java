package dataHelper.dataHelperImpl;

import dataHelper.PrivateStockDataHelper;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.List;

/**
 * PrivateStockDataHelperImpl Tester.
 *
 * @author cuihua
 * @version 1.0
 * @since <pre>03/10/2017</pre>
 */
public class PrivateStockDataHelperImplTest extends TestCase {

    PrivateStockDataHelper dataHelper = new PrivateStockDataHelperImpl();

    public PrivateStockDataHelperImplTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: getPrivateStockCode(String userName)
     */
    public void testGetPrivateStockCode() throws Exception {
        List<String> result = dataHelper.getPrivateStockCode("Harvey");
        assertEquals(3, result.size());
        assertEquals("3", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("1", result.get(2));
    }

    /**
     * Method: addPrivateStock(String userName, String stockCode)
     */
    public void testAddPrivateStock() throws Exception {
    }

    /**
     * Method: deletePrivateStock(String userName, String stockCode)
     */
    public void testDeletePrivateStock() throws Exception {
    }

    /**
     * Method: createPrivateDir(String userName)
     */
    public void testCreatePrivateDir() throws Exception {
        assertEquals(true, dataHelper.createPrivateDir("Charles"));
    }


    /**
     * Method: propertiesLoad(String username)
     */
    public void testPropertiesLoad() throws Exception {

    }


    public static Test suite() {
        return new TestSuite(PrivateStockDataHelperImplTest.class);
    }
} 
