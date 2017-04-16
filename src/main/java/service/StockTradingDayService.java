package service;

import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;

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
}
