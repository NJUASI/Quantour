package vo;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 对股票的选择条件
 */
public class ChartShowCriteriaVO {

    // 股票代码
    public String stockCode;

    // 区间内开始时间
    public LocalDate start;

    // 区间内结束时间
    public LocalDate end;

    public ChartShowCriteriaVO(String stockCode, LocalDate start, LocalDate end) {
        this.stockCode = stockCode;
        this.start = start;
        this.end = end;
    }
}
