package vo;

import po.StockPO;
import utilities.enums.Market;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 股票
 */
public class StockVO {

    // 记录编号
    public int serial;

    // 日期(月/日/年)
    public LocalDate date;

    // 开盘指数
    public double open;

    // 最高指数
    public double high;

    // 最低指数
    public double low;

    // 收盘指数
    public double close;

    // 成交量
    public String volume;

    // 复权后的收盘指数
    public double adjClose;

    // 股票代码
    public int code;

    // 股票名称
    public String name;

    // 市场名称
    public Market market;

    public StockVO() {
    }

    public StockVO(StockPO po) {
        this.adjClose = po.getAdjClose();
        this.close = po.getClose();
        this.code = po.getCode();
        this.date = po.getDate();
        this.high = po.getHigh();
        this.low = po.getLow();
        this.market = po.getMarket();
        this.name = po.getName();
        this.open = po.getOpen();
        this.close = po.getClose();
        this.serial = po.getSerial();
    }
}
