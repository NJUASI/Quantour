package vo;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 保存用户在界面上选择的回测的条件
 */
public class TracebackChoiceVO {

    //回测区间的起始时间
    LocalDate startDate;

    //回测区间的结束时间
    LocalDate endDate;

    //所选股票池
    String stockPool;

    //所选策略类型
    String strategy;

    //持有期
    int holdingPeriod;

    //形成期
    int formativePeriod;

}
