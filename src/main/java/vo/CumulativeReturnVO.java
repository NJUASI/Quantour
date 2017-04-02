package vo;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测累计收益率
 */
public class CumulativeReturnVO {

    // 当天的日期
    public LocalDate currentDate;

    // 当天的累计收益率
    public double cumulativeReturn;

    // 是否为最大回测点
    //TODO 是否要在这里面加入这个布尔值 gcm
    public boolean isTraceBack;

    public CumulativeReturnVO(LocalDate currentDate, double cumulativeReturn, boolean isTraceBack) {
        this.currentDate = currentDate;
        this.cumulativeReturn = cumulativeReturn;
        this.isTraceBack = isTraceBack;
    }
}
