package dao.daoImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import po.StockSituationPO;

import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/6.
 */
public class StockSituationDaoImplTest {

    private StockSituationDaoImpl stockSituationDao;

    @Before
    public void setUp() throws Exception {
        this.stockSituationDao = new StockSituationDaoImpl();
    }

    @Test
    public void getStockSituation() throws Exception {
        LocalDate date = LocalDate.of(2014, 1, 2);

        StockSituationPO stockSituationPO = this.stockSituationDao.getStockSituation(date);

        //数据都尚未确定
        Assert.assertEquals("42246375", stockSituationPO.getVolume());
        Assert.assertEquals(10, stockSituationPO.getLimitUpNum());
        Assert.assertEquals(0, stockSituationPO.getLimitDownNum());
        Assert.assertEquals(47, stockSituationPO.getSurgingNum());
        Assert.assertEquals(1, stockSituationPO.getSlumpingNum());
        Assert.assertEquals(47, stockSituationPO.getClimbingNum());
        Assert.assertEquals(1, stockSituationPO.getSlipingNum());
    }

}