package service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by harvey on 17-4-5.
 *
 * 此接口用于交易日的寻找计算，可以通过此类方法找到参考日期的前一个、后一个、向前推移x天、向后推移x天的交易日
 */
public interface StockTradingDayService {

    /**
     * 若参照日期为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCode 股票代码
     * @return LocalDate
     */
    LocalDate getLastTradingDay(LocalDate date, String stockCode) throws IOException;

    /**
     * 根据传入的股票代码，找出传入股票代码中相对于date最晚的交易日期。若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCodes 传入的股票代码列表
     * @return
     */
    LocalDate getLastTradingDay(LocalDate date, List<String> stockCodes) throws IOException;

    /**
     * 以参照日期为基准，减去minusDay的交易日天数，获取到那天的日期
     * 例： start = 4.21.2014 minusDay = 2 则返回值为4.17.2014
     * @param start 作为参照的日期
     * @param minusDay 减去的交易日的天数
     * @param stockPoolCodes 传入的股票代码列表
     * @return
     */
    LocalDate getTradingDayMinus(LocalDate start, int minusDay, List<String> stockPoolCodes) throws IOException;

    /**
     * 根据传入的股票代码，找出传入股票代码中相对于date最晚的交易日期。若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCodes 传入的股票代码列表
     * @return 参照日期的下一个交易日
     */
    LocalDate getNextTradingDay(LocalDate date, List<String> stockCodes) throws IOException;

    /**
     * 若参照日期date为交易日，则返回参照日期;否则，返回参照日期的前一个交易日
     * @param date 参照日期
     * @param stockCode 传入的股票代码
     * @return 参照日期的下一个交易日
     */
    LocalDate getNextTradingDay(LocalDate date, String stockCode) throws IOException;

    /**
     * 以参照日期为基准，加上plusDay的交易日天数，获取到那天的日期
     * 例： start = 4.17.2014 plusDay = 2 则返回值为4.21.2014
     * @param start 作为参照的日期
     * @param plusDay 加上的交易日的天数
     * @param stockPoolCodes 传入的股票代码列表
     * @return
     */
    LocalDate getTradingDayPlus(LocalDate start, int plusDay, List<String> stockPoolCodes) throws IOException;

    /**
     * 计算从起始日期到结束日期之间总共有多少天的交易日，包括起始日期和结束日期
     * @param start 起始日期
     * @param end 结束日期
     * @param stockPoolCodes 股票代码列表
     * @return 起始日期到结束日期之间总共有多少天的交易日
     */
    int getTradingDays(LocalDate start, LocalDate end, List<String> stockPoolCodes) throws IOException;
}
