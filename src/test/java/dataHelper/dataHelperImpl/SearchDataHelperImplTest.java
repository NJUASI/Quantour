package dataHelper.dataHelperImpl;

import dataHelper.SearchDataHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Harvey on 2017/3/14.
 */
public class SearchDataHelperImplTest {

    SearchDataHelper searchDataHelper;

    @Before
    public void setUp() throws Exception {
        searchDataHelper = new SearchDataHelperImpl();
    }

    @Ignore
    @Test
    public void getAllStocksFirstLetters() throws Exception {
        //TODO 龚尘淼 还未实现
    }

    @Test
    public void getAllStocksCode() throws Exception {
        Map<String,String> map = searchDataHelper.getAllStocksCode();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("code:"+entry.getKey()+"  name:"+entry.getValue());
        }
    }

}