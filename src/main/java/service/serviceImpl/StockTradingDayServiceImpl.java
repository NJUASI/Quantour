package service.serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import service.StockTradingDayService;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harvey on 17-4-5.
 */
public class StockTradingDayServiceImpl implements StockTradingDayService {

    StockDao stockDao;

    public StockTradingDayServiceImpl() {
        this.stockDao = new StockDaoImpl();
    }

    /**
     * 根据传入的股票代码，找出传入股票代码中相对于date最晚的交易日期。若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     *
     * @param date       参照日期
     * @param stockCodes 传入的股票代码列表
     * @return
     */
    @Override
    public LocalDate getLastTradingDay(LocalDate date, List<String> stockCodes) throws IOException {
        List<LocalDate> dates = new ArrayList<LocalDate>();

        //获取列表中第一支股票的参照日期的前一个交易日
        LocalDate lastTradingDay = stockDao.getLastTradingDay(date, stockCodes.get(0));

        for(int i = 0; i < stockCodes.size(); i++){
            dates.add(stockDao.getLastTradingDay(date,stockCodes.get(i)));
        }

        for(int i = 0; i < dates.size(); i++){
            if (dates.get(i).isAfter(lastTradingDay)){
                lastTradingDay = dates.get(i);
            }
        }

        return lastTradingDay;
    }

    /**
     * 以参照日期为基准，减去minusDay的交易日天数，获取到那天的日期
     * 例： start = 4.21.2014 minusDay = 2 则返回值为4.17.2014
     *
     * @param start          作为参照的日期
     * @param minusDay       减去的交易日的天数
     * @param stockPoolCodes 传入的股票代码列表
     * @return
     */
    @Override
    public LocalDate getTradingDayMinus(LocalDate start, int minusDay, List<String> stockPoolCodes) throws IOException {

        LocalDate tradingDayMinus = start;

        for(int i = 0; i < minusDay; i++){
            tradingDayMinus = getLastTradingDay(tradingDayMinus.minusDays(1),stockPoolCodes);
        }

        return tradingDayMinus;
    }

}
