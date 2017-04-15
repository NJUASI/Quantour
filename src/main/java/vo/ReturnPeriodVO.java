package vo;

import java.util.DoubleSummaryStatistics;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/15.
 *
 * 收益周期统计，保存绝对收益和相对收益指数，详见果仁网
 */
public class ReturnPeriodVO {

    /**
     * 正收益周期数
     */
    public int positivePeriodsNum;

    /**
     * 负收益周期数
     */
    public int negativePeriodNum;

    /**
     * 策略胜率
     */
    public double winRate;

    /**
     * 保存正收益率和对应的次数
     */
    public Map<Double, Integer> positiveNums;

    /**
     * 保存负收益率和对应的次数
     */
    public Map<Double, Integer> negativeNums;

}
