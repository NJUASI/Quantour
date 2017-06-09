package com.edu.nju.asi.utilities.enums;

/**
 * Created by Harvey on 2017/4/19.
 * <p>
 * 回测时， 形成期的形成策略， 暂定只有涨幅、乖离率 分别对应 动量策略和均值回归策略
 */
public enum IndicatorType {

    MOMENTUM("动量策略"),

    // 行情
    OPEN("开盘价"),
    HIGH("最高价"),
    LOW("最低价"),
    CLOSE("收盘价"),
    PRE_CLOSE("前日收盘价"),
    DAILY_AVE_PRICE("日均成交价"),

    AFTER_ADJ_OPEN("后复权开盘价"),
    AFTER_ADJ_HIGH("后复权最高价"),
    AFTER_ADJ_LOW("后复权最低价"),
    AFTER_ADJ_CLOSE("后复权收盘价"),
    AFTER_ADJ_PRE_CLOSE("昨日后复权收盘价"),
    AFTER_ADJ_DAILY_AVE_PRICE("后复权均价"),

    VOLUME("成交量"),
    TRANSACTION_AMOUNT("成交额"),

    // 股本和市值
    GENERAL_CAPITAL("总股本"),
    NEGOTIABLE_CAPITAL("流通股本"),
    TOTAL_VALUE("总市值"),
    CIRCULATION_MARKET_VALUE("流通市值"),

    INCREASE_MARGIN("涨跌幅"),
    TURNOVER_RATE("换手率"),
    SWING_RATE("股价振幅"),


    // 技术指标
    BIAS("乖离率"),
    RETURN_VOLATILITY("波动率"),

    MACD_DIF("DIFF线"),
    MACD_DEA("DEA线"),
    MACD_COLUMN_VAL("MACD柱状值"),
    BBIC("多空指标"),
    VOLUME_RATIO("1日5日量比"),
    MULTIPLE_ARRANGEMENT_MARK("多头排列标记"),
    BOLL_UP_BANDS("布林上线"),
    BOLL_DOWN_BANDS("布林上线"),
    AVE_TRUE_RANGE("平均真实波动范围"),



    // 财务指标
    PE_TTM("市盈率"),
    PB("市净率"),
    PS_TTM("市销率"),
    S_PE_TTM("静态市盈率"),
    D_PE_TTM("动态市盈率"),
    PEG("市盈率相对于盈利增长比率");



    private String repre;

    IndicatorType(String repre) {
        this.repre = repre;
    }

    /**
     * @return 该枚举相对应的文件中形式
     * <p>
     * enum TO String
     * 便于写入数据库
     */
    public String getRepre() {
        return repre;
    }

    /**
     * @return 该类型对应的枚举代码
     * <p>
     * String TO enum
     * 便于从数据库读入
     */
    public static IndicatorType getEnum(String a) {
        for (IndicatorType thisEnum : IndicatorType.values()) {
            if (thisEnum.repre.equals(a)) {
                return thisEnum;
            }
        }
        return null;
    }

}
