package com.edu.nju.asi.infoCarrier.strategy;

import com.edu.nju.asi.infoCarrier.traceBack.RankCondition;
import com.edu.nju.asi.utilities.IndicatorTypeHelper;

/**
 * Created by cuihua on 2017/6/9.
 *
 * 将信息转换为汉字让界面显示
 */
public class RankConditionChinese {

    /**
     * 指标
     */
    public String indicatorType;

    /**
     * 次序
     */
    public String rankType;

    /**
     * 权重
     */
    public int weight;

    public RankConditionChinese(RankCondition rankCondition) {
        this.indicatorType = IndicatorTypeHelper.convertIndicatorType(rankCondition.indicatorType, rankCondition.formativePeriod);
        this.rankType = rankCondition.rankType.getRepre();
        this.weight = rankCondition.weight;

    }
}
