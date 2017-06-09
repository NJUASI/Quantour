package com.edu.nju.asi.utilities;

import com.edu.nju.asi.utilities.enums.IndicatorType;

/**
 * Created by cuihua on 2017/6/9.
 * <p>
 * 帮助将IndicatorType转换为汉字，让界面显示
 */
public class IndicatorTypeHelper {


    public static String convertIndicatorType(IndicatorType indicatorType, int formativePeriod) {
        switch (indicatorType) {
            case OPEN:
            case CLOSE:
            case HIGH:
            case LOW:
            case PRE_CLOSE:
            case DAILY_AVE_PRICE:
            case AFTER_ADJ_OPEN:
            case AFTER_ADJ_CLOSE:
            case AFTER_ADJ_HIGH:
            case AFTER_ADJ_LOW:
            case AFTER_ADJ_PRE_CLOSE:
            case AFTER_ADJ_DAILY_AVE_PRICE:
            case GENERAL_CAPITAL:
            case NEGOTIABLE_CAPITAL:
            case TOTAL_VALUE:
            case CIRCULATION_MARKET_VALUE:
            case SWING_RATE:
            case PE_TTM:
            case PB:
            case PS_TTM:
            case S_PE_TTM:
            case D_PE_TTM:
                return indicatorType.getRepre();
            case VOLUME:
            case TRANSACTION_AMOUNT:
            case INCREASE_MARGIN:
            case TURNOVER_RATE:
            case BIAS:
            case RETURN_VOLATILITY:
                return convertFormativePeriod(formativePeriod) + "日" + indicatorType.getRepre();
        }
        return "";
    }


    private static String convertFormativePeriod(int formativePeriod) {
        if (formativePeriod == 1) return "当";
        else return String.valueOf(formativePeriod);
    }
}
