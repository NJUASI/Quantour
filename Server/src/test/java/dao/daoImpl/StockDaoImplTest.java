package dao.daoImpl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import po.StockPO;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/3/6.
 */
public class StockDaoImplTest {

    private StockDaoImpl stockDao;

    @Before
    public void setUp() throws Exception {
        this.stockDao = new StockDaoImpl();
    }

    @Ignore
    @Test
    public void getStockData() throws Exception {
        List<StockPO> result = stockDao.getStockData("1");
        assertEquals(2383, result.size());
    }

    @Ignore
    @Test
    public void getStockData1() throws Exception {

    }

    @Ignore
    @Test
    public void getParticularDay() throws Exception {

    }

}