package com.edu.nju.asi.service.serviceImpl.traceBackService;

import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihua on 2017/6/6.
 */
public class TraceBackParam {

    // 无风险收益率，默认为4%，可更改
    private double riskFreeRate = 0.04;


    // 有效投资时间
    private double effectiveInvestmentTime;


    // 策略的每日收益列表
    private List<DailyRate> strategyRateList;

    // 基准的每日收益列表
    private List<DailyRate> baseRateList;


    // 策略的日收益均值
    private double meanStrategy;

    // 基准的日收益均值
    private double meanBase;

    // 策略的日收益标准差
    private double stdevStrategy;

    // 基准的日收益标准差
    private double stdevBase;


    // 回测所需要显示数值型参数的信息载体
    private TraceBackNumVal traceBackNumVal;


    /**
     * 保存计算数值型数据的参数
     */
    // 回测标准数据信息
    private TraceBackCriteria traceBackCriteria;

    // 回测系统需要获取的所有信息
    private TraceBackInfo traceBackInfo;

    // 股票池中所有信息
    protected Map<String, List<Stock>> stockData;

    // 需要计算数据的所有股票代码
    private List<String> codes;

    // 基准股票
    private List<BaseStock> baseStockList;


    public TraceBackParam(TraceBackCriteria traceBackCriteria, TraceBackInfo traceBackInfo,
                          Map<String, List<Stock>> stockData, List<String> codes, List<BaseStock> baseStockList) throws IOException, NoDataWithinException, DateNotWithinException {
        traceBackNumVal = new TraceBackNumVal();

        this.traceBackCriteria = traceBackCriteria;
        this.traceBackInfo = traceBackInfo;
        this.stockData = stockData;
        this.codes = codes;
        this.baseStockList = baseStockList;

        this.effectiveInvestmentTime = traceBackInfo.baseCumulativeReturn.get(0).currentDate.until(
                traceBackInfo.baseCumulativeReturn.get(traceBackInfo.baseCumulativeReturn.size() - 1).currentDate,
                ChronoUnit.DAYS);
    }

    public TraceBackNumVal getNumericalVal() throws IOException, NoDataWithinException, DateNotWithinException {
        initBase();
        initStrategy();

        return traceBackNumVal;
    }


    /**
     * 初始化基准的数值型参数信息
     *
     * @auther Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/6/7
     */
    private void initBase() throws DateNotWithinException, NoDataWithinException, IOException {
        // 计算基准的总收益率、年化收益率
        traceBackNumVal.baseSumRate = calculateSumRate(true);
        traceBackNumVal.baseAnnualizedRateOfReturn = calculateAnnualizedReturn(traceBackNumVal.baseSumRate, effectiveInvestmentTime);


        // 初始化计算所需的数据（股指一只股票，直接计算）
        baseRateList = calBaseDailyRate();

        //计算基准的日收益率均值、日收益率标准差
        meanBase = calMeanOfDaily(baseRateList);
        stdevBase = calStdevOfDaily(baseRateList);

        System.out.println("基准日收益均值：" + meanBase);
        System.out.println("基准日收益标准差：" + stdevBase);


        // 计算基准的年化波动率
        traceBackNumVal.baseReturnVolatility = calculateReturnVolatility(stdevBase);

        // 计算基准的夏普比率
        traceBackNumVal.baseSharpeRatio = calculateSharpeRatio(
                traceBackNumVal.baseAnnualizedRateOfReturn, traceBackNumVal.baseReturnVolatility);
    }

    /**
     * 初始化策略的数值型参数信息
     *
     * @auther Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/6/7
     */
    private void initStrategy() {
        // 计算策略的总收益率、年化收益率
        traceBackNumVal.sumRate = calculateSumRate(false);
        traceBackNumVal.annualizedRateOfReturn = calculateAnnualizedReturn(traceBackNumVal.sumRate, this.effectiveInvestmentTime);


        // 初始化计算所需的数据
        strategyRateList = calStrategyDailyRate();

        //计算策略的日收益率均值、日收益率标准差
        meanStrategy = calMeanOfDaily(strategyRateList);
        stdevStrategy = calStdevOfDaily(strategyRateList);

        System.out.println("策略日收益均值：" + meanStrategy);
        System.out.println("策略日收益标准差：" + stdevStrategy);

        // 计算策略的年化波动率
        traceBackNumVal.returnVolatility = calculateReturnVolatility(stdevStrategy);

        // 计算（策略）夏普比率、（策略）信息比率
        traceBackNumVal.sharpeRatio = calculateSharpeRatio(traceBackNumVal.annualizedRateOfReturn, traceBackNumVal.returnVolatility);
        traceBackNumVal.informationRatio = calculateInformationRatio();

        // 计算alpha、beta
        traceBackNumVal.beta = calculateBeta(strategyRateList, baseRateList, stdevBase);
        traceBackNumVal.alpha = calculateAlpha(traceBackNumVal.beta, traceBackNumVal.annualizedRateOfReturn,
                traceBackNumVal.baseAnnualizedRateOfReturn);
    }


    private List<DailyRate> calStrategyDailyRate() {
        List<DailyRate> result = new LinkedList<>();

        double previous = 0;
        double now = 0;
        for (int i = 0; i < traceBackInfo.strategyCumulativeReturn.size(); i++) {
            CumulativeReturn temp = traceBackInfo.strategyCumulativeReturn.get(i);
            if (i == 0) {
                // 第一天赋值为0
                previous = 0;
                result.add(new DailyRate(0, temp.currentDate));
                continue;
            } else {
                now = temp.cumulativeReturn;
            }

            double baseDailyRate = (1 + now) / (1 + previous) - 1;
            result.add(new DailyRate(baseDailyRate, temp.currentDate));

            previous = now;
        }

        return result;
    }

    /**
     * @return 基准的日收益列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/10
     */
    private List<DailyRate> calBaseDailyRate() {
        List<DailyRate> result = new ArrayList<>();

        double previous = 0;
        double now = 0;
        for (int i = 0; i < traceBackInfo.baseCumulativeReturn.size(); i++) {
            CumulativeReturn temp = traceBackInfo.baseCumulativeReturn.get(i);
            if (i == 0) {
                // 第一天赋值为0
                previous = 0;
                result.add(new DailyRate(0, temp.currentDate));
                continue;
            } else {
                now = temp.cumulativeReturn;
            }

            double baseDailyRate = (1 + now) / (1 + previous) - 1;
            result.add(new DailyRate(baseDailyRate, temp.currentDate));

            previous = now;
        }

        return result;
    }

    /**
     * @param rateList 日收益列表
     * @return double 日收益率均值
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calMeanOfDaily(List<DailyRate> rateList) {
        Mean mean = new Mean();

        double[] data = new double[rateList.size()];
        for(int i = 0; i < rateList.size(); i++){
            data[i] = rateList.get(i).rate;
        }

        return mean.evaluate(data);
    }

    /**
     * @param rateList 日收益列表
     * @return double 日收益标准差
     * @auther Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/6/8
     */
    private double calStdevOfDaily(List<DailyRate> rateList) {

        StandardDeviation stdev = new StandardDeviation();

        double[] data = new double[rateList.size()];
        for(int i = 0; i < rateList.size(); i++){
            data[i] = rateList.get(i).rate;
        }

        return stdev.evaluate(data);
    }


    /**
     * @return 策略／基准总收益
     * @auther Byron Dong
     * @lastUpdatedBy cuihua
     * @updateTime 2017/6/7
     */
    private double calculateSumRate(boolean isBase) {
        List<CumulativeReturn> list;
        if (isBase) list = traceBackInfo.baseCumulativeReturn;
        else list = traceBackInfo.strategyCumulativeReturn;

        CumulativeReturn last = list.get(list.size() - 1);
        return last.cumulativeReturn;
    }

    /**
     * @param rate 总收益率
     * @param days 有效投资时间
     * @return double 年化收益率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateAnnualizedReturn(double rate, double days) {
        double result = Math.pow((rate + 1), (365.25 / days)) - 1;
        return result;
    }

    /**
     * @param annualizedReturn 年化收益率
     * @param returnVolatility 年化波动收益率
     * @return 夏普比率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateSharpeRatio(double annualizedReturn, double returnVolatility) {
        return ((annualizedReturn - this.riskFreeRate) / returnVolatility);
    }

    /**
     * @param stdevRate 日收益率标准差
     * @return 年化波动收益率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateReturnVolatility(double stdevRate) {
        return stdevRate * Math.sqrt(250);
    }


    /**
     * @return （策略）信息比率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateInformationRatio() {
        // 年化相对收益波动率
        double annualizedRelativeReturnVolatility = calStdevOfInformationRatio();

        double result = (traceBackNumVal.annualizedRateOfReturn - traceBackNumVal.baseAnnualizedRateOfReturn) / annualizedRelativeReturnVolatility;
        return result;
    }

    private double calStdevOfInformationRatio() {
        List<DailyRate> tempList = new LinkedList<>();

        for (int i = 0; i < baseRateList.size(); i++) {
            tempList.add(new DailyRate(strategyRateList.get(i).rate - baseRateList.get(i).rate, baseRateList.get(i).date));
        }
        return calStdevOfDaily(tempList) * Math.sqrt(250);
    }

    /**
     * @param strategy  策略的日收益率
     * @param base      基准的日收益率
     * @param baseStdev 基准日收益率的标准差
     * @return beta
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateBeta(List<DailyRate> strategy, List<DailyRate> base, double baseStdev) {
        List<DailyRate> result = new ArrayList<>();

        for (int i = 0; i < base.size(); i++) {
            result.add(new DailyRate(strategy.get(i).rate * base.get(i).rate, null));
        }

        double beta = calMeanOfDaily(result) - (meanStrategy * meanBase);
        beta = beta / Math.pow(baseStdev, 2);
        return beta;
    }

    /**
     * @param beta                       贝塔
     * @param annualizedRateOfReturn     策略的年化收益率
     * @param baseAnnualizedRateOfReturn 基准年化收益率
     * @return alpha
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateAlpha(double beta, double annualizedRateOfReturn, double baseAnnualizedRateOfReturn) {
        double result = annualizedRateOfReturn - beta * baseAnnualizedRateOfReturn;
        result = result + (beta - 1) * riskFreeRate;
        return result;
    }
}
