package service.serviceImpl;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import service.StockTradingDayService;

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
     * 若参照日期为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     *
     * @param date 参照日期
     * @param stockCode
     * @return LocalDate
     */
    @Override
    public LocalDate getLastTradingDay(LocalDate date, String stockCode) throws IOException {
        return stockDao.getLastTradingDay(date, stockCode);
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

    /**
     * 若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     *
     * @param date      参照日期
     * @param stockCode 传入的股票代码
     * @return 参照日期的下一个交易日
     */
    @Override
    public LocalDate getNextTradingDay(LocalDate date, String stockCode) throws IOException {
        return stockDao.getNextTradingDay(date, stockCode);
    }

    /**
     * 根据传入的股票代码，找出传入股票代码中相对于date最晚的交易日期。若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     *
     * @param date       参照日期
     * @param stockCodes 传入的股票代码列表
     * @return 参照日期的下一个交易日
     */
    @Override
    public LocalDate getNextTradingDay(LocalDate date, List<String> stockCodes) throws IOException {
        List<LocalDate> dates = new ArrayList<LocalDate>();

        //获取列表中第一支股票的参照日期的后一个交易日
        LocalDate nextTradingDay = stockDao.getNextTradingDay(date, stockCodes.get(0));

        for(int i = 0; i < stockCodes.size(); i++){
            dates.add(stockDao.getNextTradingDay(date,stockCodes.get(i)));
        }

        for(int i = 0; i < dates.size(); i++){
            if (dates.get(i).isBefore(nextTradingDay)){
                nextTradingDay = dates.get(i);
            }
        }

        return nextTradingDay;
    }

    /**
     * 以参照日期为基准，加上plusDay的交易日天数，获取到那天的日期
     * 例： start = 4.17.2014 plusDay = 2 则返回值为4.21.2014
     *
     * @param start          作为参照的日期
     * @param plusDay        加上的交易日的天数
     * @param stockPoolCodes 传入的股票代码列表
     * @return
     */
    @Override
    public LocalDate getTradingDayPlus(LocalDate start, int plusDay, List<String> stockPoolCodes) throws IOException {

        LocalDate tradingDayPlus = start;

        for(int i = 0; i < plusDay; i++){
            tradingDayPlus = getNextTradingDay(tradingDayPlus.plusDays(1),stockPoolCodes);
        }

        return tradingDayPlus;
    }

    /**
     * 计算从起始日期到结束日期之间总共有多少天的交易日，包括起始日期和结束日期
     *
     * @param start 起始日期
     * @param end   结束日期
     * @param stockPoolCodes 股票代码列表
     * @return 起始日期到结束日期之间总共有多少天的交易日
     */
    @Override
    public int getTradingDays(LocalDate start, LocalDate end, List<String> stockPoolCodes) throws IOException {

        int tradingDays = 0;

        int diff = start.until(end).getDays();

        for(int i = 0; i <= diff; i++){

            if(stockDao.isTradingDay(start.plusDays(i), stockPoolCodes)){
                tradingDays++;
            }

        }

        return tradingDays;
    }

}
