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

}