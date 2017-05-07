package com.edu.nju.asi.vo;

import java.time.LocalDate;

/**
 * Created by harvey on 17-3-28.
 *
 * 用于列表展示回测中的数值型数据
 */
public class TraceBackNumValVO {

    // 策略年化收益率
    public double annualizedRateOfReturn;

    // 基准年化收益率
    public double baseAnnualizedRateOfReturn;

    // 策略年化波动率
    public double returnVolatility;

    // 基准年化波动率
    public double baseReturnVolatility;

    // 策略总收益率
    public double sumRate;

    // 基准总收益率
    public double baseSumRate;

    // 阿尔法比率
    public double alpha;

    // 贝塔
    public double beta;

    // 策略夏普比率
    public double sharpeRatio;

    // 基准夏普比率
    public double baseSharpeRatio;

    // 最大回撤率
    public double maxRetracementRatio;

    // 最大回撤率的峰值
    public double peakPointRatio;

    // 最大回撤率的谷底值
    public double valleyPointRatio;

    // 最大回撤率起始时间
    public LocalDate startDate;

    // 最大回撤率结束时间
    public LocalDate endDate;


    public TraceBackNumValVO(double annualizedRateOfReturn, double baseAnnualizedRateOfReturn, double returnVolatility,
                             double baseReturnVolatility, double sumRate, double baseSumRate, double alpha, double beta,
                             double sharpeRatio, double baseSharpeRatio, double maxRetracementRatio, double peakPointRatio,
                             double valleyPointRatio, LocalDate startDate, LocalDate endDate) {
        this.annualizedRateOfReturn = annualizedRateOfReturn;
        this.baseAnnualizedRateOfReturn = baseAnnualizedRateOfReturn;
        this.returnVolatility = returnVolatility;
        this.baseReturnVolatility = baseReturnVolatility;
        this.sumRate = sumRate;
        this.baseSumRate = baseSumRate;
        this.alpha = alpha;
        this.beta = beta;
        this.sharpeRatio = sharpeRatio;
        this.baseSharpeRatio = baseSharpeRatio;
        this.maxRetracementRatio = maxRetracementRatio;
        this.peakPointRatio = peakPointRatio;
        this.valleyPointRatio = valleyPointRatio;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TraceBackNumValVO() {
    }
}
