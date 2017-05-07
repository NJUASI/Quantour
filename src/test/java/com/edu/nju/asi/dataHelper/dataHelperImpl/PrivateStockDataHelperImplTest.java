package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.PrivateStockDataHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
    @Ignore
    @Test
    public void testGetPrivateStockCode() throws Exception {
        List<String> result = dataHelper.getPrivateStockCode("Guest");
        assertEquals(2, result.size());
        assertEquals("000300", result.get(0));
        assertEquals("000001", result.get(1));
    }

    /**
     * Method: addPrivateStock(String userName, String stockCode)
     */
    @Test
    public void testAddPrivateStock() throws Exception {
//        assertEquals(true, com.edu.nju.asi.dataHelper.addPrivateStock("Guest", "000001"));
    }

    /**
     * Method: deletePrivateStock(String userName, String stockCode)
     */
    @Test
    public void testDeletePrivateStock() throws Exception {
    }

}
