package po;

import utilities.enums.Market;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 股票
 */
public class Stock {

    // 记录编号
    private int serial;

    // 日期(月/日/年)
    private LocalDate date;

    // 开盘指数
    private LocalTime open;

    // 最高指数
    private LocalTime high;

    // 最低指数
    private LocalTime low;

    // 收盘指数
    private LocalTime close;

    // 成交量
    private String volume;

    // 复权后的收盘指数
    private double adjClose;

    // 股票代码
    private int code;

    // 股票名称
    private String name;

    // 市场名称
    private Market market;


    public Stock(int serial, LocalDate date, LocalTime open, LocalTime high, LocalTime low, LocalTime close, String volume, double adjClose, int code, String name, Market market) {
        this.serial = serial;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjClose = adjClose;
        this.code = code;
        this.name = name;
        this.market = market;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getOpen() {
        return open;
    }

    public void setOpen(LocalTime open) {
        this.open = open;
    }

    public LocalTime getHigh() {
        return high;
    }

    public void setHigh(LocalTime high) {
        this.high = high;
    }

    public LocalTime getLow() {
        return low;
    }

    public void setLow(LocalTime low) {
        this.low = low;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
