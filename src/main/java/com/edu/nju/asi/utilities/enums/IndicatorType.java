package com.edu.nju.asi.utilities.enums;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 回测时， 形成期的形成策略， 暂定只有涨幅、乖离率 分别对应 动量策略和均值回归策略
 */
public enum IndicatorType {

    OPEN("开盘价"),
    HIGH("最高价"),
    LOW("最低价"),
    CLOSE("收盘价"),
    VOLUME("成交量"),
    TRANSACTION_AMOUNT("成交额"),
    PRE_CLOSE("前日收盘价"),
    TURNOVER_RATE("换手率"),
    TOTAL_VALUE("总市值"),
    CIRCULATION_MARKET_VALUE("流通市值"),
    AFTER_ADJ_OPEN("后复权开盘价"),
    AFTER_ADJ_HIGH("后复权最高价"),
    AFTER_ADJ_LOW("后复权最低价"),
    AFTER_ADJ_CLOSE("后复权收盘价"),


    INCREASE_MARGIN("涨跌幅"),
    FLUCTION("涨跌额"),

    DAILY_AVE_PRICE("日均成交价"),

    MOMENTUM("动量"),
    BIAS("乖离率");

    private String repre;

    IndicatorType(String repre) {
        this.repre = repre;
    }

    /**
     *
     * @return 该枚举相对应的文件中形式
     *
     * enum TO String
     * 便于写入数据库
     */
    public String getRepre() {
        return repre;
    }

    /**
     *
     * @return 该类型对应的枚举代码
     *
     * String TO enum
     * 便于从数据库读入
     */
    public static IndicatorType getEnum(String a) {
        for (IndicatorType thisEnum : IndicatorType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
