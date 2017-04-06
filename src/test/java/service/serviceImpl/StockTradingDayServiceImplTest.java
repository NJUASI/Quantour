package service.serviceImpl;

import com.sun.javafx.fxml.LoadListener;
import org.junit.*;
import org.junit.Test;
import service.StockTradingDayService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by harvey on 17-4-5.
 */
public class StockTradingDayServiceImplTest {

    StockTradingDayService stockTradingDayService;

    List<String> stockPool;

    @Before
    public void setUp() throws Exception {

        stockTradingDayService = new StockTradingDayServiceImpl();

        stockPool = new ArrayList<String>();
        //因为这两只股票的开盘时间有差异，故用这两只股票做对比
        stockPool.add("1");
        stockPool.add("12");

    }

    @Test
    public void getLastTradingDay() throws Exception {

        LocalDate baseDate = LocalDate.of(2014,3,24);

        LocalDate lastTradingDay = stockTradingDayService.getLastTradingDay(baseDate,"1");
        assertEquals(baseDate,lastTradingDay);

        LocalDate lastTradingDay1 = stockTradingDayService.getLastTradingDay(baseDate,"12");
        assertEquals(LocalDate.of(2014,3,21),lastTradingDay1);
    }

    @Test
    public void getLastTradingDay1() throws Exception {

        LocalDate baseDate = LocalDate.of(2014,3,24);

        LocalDate lastTradingDay = stockTradingDayService.getLastTradingDay(baseDate,stockPool);
        assertEquals(baseDate,lastTradingDay);

    }

    @Test
    public void getTradingDayMinus() throws Exception {

        LocalDate baseDate = LocalDate.of(2014,4,29);

        LocalDate tradingDayMinus = stockTradingDayService.getTradingDayMinus(baseDate,10,stockPool);
        assertEquals(LocalDate.of(2014,4,15),tradingDayMinus);
    }

    @Test
    public void getNextTradingDay() throws Exception {

        LocalDate baseDate = LocalDate.of(2014,3,22);

        LocalDate nextDay = stockTradingDayService.getNextTradingDay(baseDate,"1");
        assertEquals(LocalDate.of(2014,3,24),nextDay);

        LocalDate nextDay1 = stockTradingDayService.getNextTradingDay(baseDate,"12");
        assertEquals(LocalDate.of(2014,3,25),nextDay1);

    }

    @Test
    public void getNextTradingDay1() throws Exception {

        LocalDate baseDate = LocalDate.of(2014,3,22);

        LocalDate nextDay = stockTradingDayService.getNextTradingDay(baseDate,stockPool);
        assertEquals(LocalDate.of(2014,3,24),nextDay);

    }

    @Test
    public void getTradingDayPlus() throws Exception {

        LocalDate baseDate = LocalDate.of(2014,3,21);

        LocalDate plusDay = stockTradingDayService.getTradingDayPlus(baseDate,2,stockPool);
        assertEquals(LocalDate.of(2014,3,25),plusDay);

    }

    @Test
    public void getTradingDays() throws Exception {

        LocalDate start = LocalDate.of(2014,3,21);
        LocalDate end = LocalDate.of(2014,3,25);

        int days = stockTradingDayService.getTradingDays(start,end,stockPool);

        assertEquals(3,days,1);

    }

}