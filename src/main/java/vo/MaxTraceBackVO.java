package vo;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/4/18.
 *
 * 保存基准、策略的最大回撤率, 最大策略回测点的日期，以及在list中的位置
 *
 * 如果还需要什么其它的回撤数据，注意是回撤，则在这个里面增加变量， 并在maxTracement方法中计算相关数据
 */
public class MaxTraceBackVO {

    /**
     * 基准最大回撤率
     */
    public double maxBaseTraceBackRate;

    /**
     * 策略最大回撤率
     */
    public double maxStrategyTraceBackRate;

    /**
     * 最大策略回撤起始日期
     */
    public LocalDate maxStartDay;

    /**
     * 最大策略回撤结束日期
     */
    public LocalDate maxEndDay;

    /**
     * 最大策略回撤结束日期在该策略日期列表中的位置
     */
    public int maxStartIndex;

    /**
     * 最大策略回撤起始日期在该策略日期列表中的位置
     */
    public int maxEndIndex;

}
