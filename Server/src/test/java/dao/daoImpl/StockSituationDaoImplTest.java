package dao.daoImpl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import po.StockSituationPO;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/3/6.
 */
public class StockSituationDaoImplTest {

    private StockSituationDaoImpl stockSituationDao;

    @Before
    public void setUp() throws Exception {
        this.stockSituationDao = new StockSituationDaoImpl();
    }

    @Ignore
    @Test
    public void getStockSituation() throws Exception {
        LocalDate date = LocalDate.of(2010, 3, 4); // TODO 由于数据尚未处理，此处日期不知道结果，待定

        StockSituationPO stockSituationPO = this.stockSituationDao.getStockSituation(date);

        //数据都尚未确定
        assertEquals("", stockSituationPO.getVolume());
        assertEquals(0, stockSituationPO.getLimitUpNum());
        assertEquals(0, stockSituationPO.getLimitDownNum());
        assertEquals(0, stockSituationPO.getSurgingNum());
        assertEquals(0, stockSituationPO.getSlumpingNum());
        assertEquals(0, stockSituationPO.getClimbingNum());
        assertEquals(0, stockSituationPO.getSlipingNum());
    }

}