package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by harvey on 17-3-28.
 * <p>
 * 用于列表展示回测中的数值型数据
 */
public class TraceBackNumVal {

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

    // 策略夏普比率
    public double sharpeRatio;

    // 基准夏普比率
    public double baseSharpeRatio;

    // （策略）阿尔法比率
    public double alpha;

    // （策略）贝塔
    public double beta;

    // （策略）信息比率
    public double informationRatio;

    public TraceBackNumVal() {
    }

    public TraceBackNumVal(double annualizedRateOfReturn, double baseAnnualizedRateOfReturn, double returnVolatility,
                           double baseReturnVolatility, double sumRate, double baseSumRate, double sharpeRatio,
                           double baseSharpeRatio, double alpha, double beta, double informationRatio) {
        this.annualizedRateOfReturn = annualizedRateOfReturn;
        this.baseAnnualizedRateOfReturn = baseAnnualizedRateOfReturn;
        this.returnVolatility = returnVolatility;
        this.baseReturnVolatility = baseReturnVolatility;
        this.sumRate = sumRate;
        this.baseSumRate = baseSumRate;
        this.sharpeRatio = sharpeRatio;
        this.baseSharpeRatio = baseSharpeRatio;
        this.alpha = alpha;
        this.beta = beta;
        this.informationRatio = informationRatio;
    }
}
