package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.IndicatorTypeHelper;
import com.edu.nju.asi.utilities.enums.IndicatorType;

/**
 * Created by cuihua on 2017/6/9.
 * <p>
 * 将信息转换为汉字让界面显示
 */
public class FilterConditionChinese {

    /**
     * 指标
     */
    public String indicatorType;

    /**
     * 比较符
     */
    public String comparatorType;

    /**
     * 比较符的值
     */
    public int value;

    public FilterConditionChinese(FilterCondition filterCondition) {
        this.indicatorType = IndicatorTypeHelper.convertIndicatorType(filterCondition.indicatorType, filterCondition.formativePeriod);
        this.comparatorType = filterCondition.comparatorType.getRepre();
        this.value = filterCondition.value;
    }

}
