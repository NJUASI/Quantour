package dataHelper.dataHelperImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import po.StockSituationPO;

import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/6.
 */
public class StockSituationDataHelperImplTest {

    private StockSituationDataHelperImpl stock;

    @Before
    public void setUp() throws Exception {
        this.stock = new StockSituationDataHelperImpl();
    }

    @Ignore
    @Test
    public void testGetStockSituation() {
        LocalDate date = LocalDate.of(2010, 3, 4); // TODO 由于数据尚未处理，此处日期不知道结果，待定

        StockSituationPO stockSituationPO = this.stock.getStockSituation(date);

        //数据都尚未确定
        Assert.assertEquals("", stockSituationPO.getVolume());
        Assert.assertEquals(0, stockSituationPO.getLimitUpNum());
        Assert.assertEquals(0, stockSituationPO.getLimitDownNum());
        Assert.assertEquals(0, stockSituationPO.getSurgingNum());
        Assert.assertEquals(0, stockSituationPO.getSlumpingNum());
        Assert.assertEquals(0, stockSituationPO.getClimbingNum());
        Assert.assertEquals(0, stockSituationPO.getSlipingNum());
    }
}