package vo;

import po.StockPO;
import utilities.enums.Market;

import java.time.LocalDate;

/**
 * Created by day on 17/3/30.
 */
public class StrategyPanelVO {
    // 板块
    public Object[] block;

    // 开始日期(月/日/年)
    public LocalDate startDate;

    // 结束日期(月/日/年)
    public LocalDate endDate;

    // 基准
    public String baseStock;

    // ==1 动量策略 ==2 均值回归
    public int strategyType ;

    // 形成期  或者  乖离率
    public int createDate;

    // 持有期
    public int holdingDate;

    // 持有股票数
    public int holdingNum;

}
