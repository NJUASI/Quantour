package vo;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 用于列表展示回测中的数值型数据
 */
public class TracebackNumValVO {

    //年化收益率
    double annualizedRateOfReturn;

    //基准年化收益率
    double baseAnnualizedRateOfReturn;

    //阿尔法比率
    double alpha;

    //贝塔
    double beta;

    //夏普比率
    double sharpeRatio;

    //最大回撤率
    double maxRetracementRatio;

    //最大回撤率的峰值
    double peakPointRatio;

    //最大回撤率的谷底值
    double valleyPointRatio;

    //最大回撤率起始时间
    LocalDate startDate;

    //最大回撤率结束时间
    LocalDate endDate;


    public TracebackNumValVO(double annualizedRateOfReturn, double baseAnnualizedRateOfReturn, double alpha, double beta, double sharpeRatio, double maxRetracementRatio, double peakPointRatio, double valleyPointRatio, LocalDate startDate, LocalDate endDate) {
        this.annualizedRateOfReturn = annualizedRateOfReturn;
        this.baseAnnualizedRateOfReturn = baseAnnualizedRateOfReturn;
        this.alpha = alpha;
        this.beta = beta;
        this.sharpeRatio = sharpeRatio;
        this.maxRetracementRatio = maxRetracementRatio;
        this.peakPointRatio = peakPointRatio;
        this.valleyPointRatio = valleyPointRatio;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
