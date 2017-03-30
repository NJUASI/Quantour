package vo;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测累计收益率
 */
public class CumulativeReturnVO {

    //当天的累计收益率
    public double cumulativeReturn;

    //当天的日期
    public LocalDate currentDate;

    //是否为最大回测点
    public boolean isTraceBack;

    public CumulativeReturnVO(double cumulativeReturn, LocalDate currentDate, boolean isTraceBack) {
        this.cumulativeReturn = cumulativeReturn;
        this.currentDate = currentDate;
        this.isTraceBack = isTraceBack;
    }
}
