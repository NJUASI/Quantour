package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.model.BaseStock;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.service.StockService;
import com.edu.nju.asi.service.serviceImpl.StockService.StockServiceImpl;
import com.edu.nju.asi.utilities.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Byron Dong on 2017/4/9.
 */
public class TraceBackParameter {

    //无风险收益率，默认为4%，可更改
    private double riskFreeRate = 0.04;

    //有效投资时间
    private double effectiveInvestmentTime = 360;

    //策略的日收益均值
    private double meanStrategy;

    //基准的日收益均值
    private double meanBase;

    //策略的日收益标准差
    private double stdevStrategy;

    //基准的日收益标准差
    private double stdevBase;

    //回测所需要显示参数的信息载体
    private TraceBackNumVal traceBackNumVal;

    //回测系统需要获取的所有信息
    private TraceBackInfo traceBackInfo;

    //策略的每日收益列表
    private List<DailyRate> strategyRate;

    //基准的每日收益列表
    private List<DailyRate> baseRate;

    //股票池中所有信息
    protected Map<String, List<StrategyStock>> stockData;

    //需要计算数据的所有股票代码
    private List<String> codes;

    //数据信息
    private TraceBackCriteria traceBackCriteria;


    public TraceBackParameter() {
    }

    /**
     * 对回测所需要显示参数的初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public TraceBackParameter(TraceBackCriteria traceBackCriteria, TraceBackInfo traceBackInfo,
                              Map<String, List<StrategyStock>> stockData, List<String> codes, List<BaseStock> baseStockList) throws IOException, NoDataWithinException, DateNotWithinException {
        this.stockData = stockData;
        this.traceBackInfo = traceBackInfo;

        this.traceBackCriteria = traceBackCriteria;
        traceBackNumVal = new TraceBackNumVal();
        this.codes = codes;

        this.initBase(baseStockList);
        this.initStrategy();
        this.traceBackInfo.traceBackNumVal = this.traceBackNumVal;
        System.out.println("INIT FINISHED!!");
    }

    /**
     * 获取回测所需要显示参数
     *
     * @return TraceBackInfo 回测所需要显示载体
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public TraceBackInfo getTraceBackInfo() {
        return traceBackInfo;
    }

    /**
     * 获取无风险收益率
     *
     * @return double 无风险收益率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public double getRiskFreeRate() {
        return riskFreeRate;
    }

    /**
     * 设置无风险收益率
     *
     * @param riskFreeRate 需要修改的无风险收益率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public void setRiskFreeRate(double riskFreeRate) {
        this.riskFreeRate = riskFreeRate;
    }

    /**
     * 初始化策略的参数信息
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private void initStrategy() {

        List<List<StrategyStock>> list = new ArrayList<>();

        for (String code : codes) {
            list.add(stockData.get(code));
        }
        strategyRate = this.calStrategyDailyRateAll(list);

        //计算策略的总收益率
        traceBackNumVal.sumRate = this.calStrategySumRate();
        //计算策略的日收益率均值
        this.meanStrategy = this.calMeanOfDaily(strategyRate);
        //计算策略的日收益率标准差
        this.stdevStrategy = this.calStdevOfDaily(strategyRate, meanStrategy);
        //计算策略的年化收益率
        traceBackNumVal.annualizedRateOfReturn = this.calculateAnnualizedReturn(
                traceBackNumVal.sumRate, this.effectiveInvestmentTime);
        //计算策略的年化波动率
        traceBackNumVal.returnVolatility = this.calculateReturnVolatility(stdevStrategy);
        //计算夏普比率
        traceBackNumVal.sharpeRatio = this.calculateSharpeRatio(
                traceBackNumVal.annualizedRateOfReturn, traceBackNumVal.returnVolatility);
        //计算beta
        traceBackNumVal.beta = this.calculateBeta(strategyRate, baseRate, stdevBase);
        //计算alpha
        traceBackNumVal.alpha = this.calculateAlpha(traceBackNumVal.beta, traceBackNumVal.annualizedRateOfReturn
                , traceBackNumVal.baseAnnualizedRateOfReturn);
    }

    /**
     * 初始化基准的参数信息
     *
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private void initBase(List<BaseStock> baseStockList) throws DateNotWithinException, NoDataWithinException, IOException {
        System.out.println(traceBackCriteria.baseStockName);
        baseRate = this.calBaseDailyRate(baseStockList);

        System.out.println("initBase");
        //计算基准的总收益率
        traceBackNumVal.baseSumRate = this.calBaseSumRate();
        //计算基准的日收益率均值
        this.meanBase = this.calMeanOfDaily(baseRate);
        //计算基准的日收益率标准差
        this.stdevBase = this.calStdevOfDaily(baseRate, meanBase);
        //计算基准的年化收益率
        traceBackNumVal.baseAnnualizedRateOfReturn = this.calculateAnnualizedReturn(
                traceBackNumVal.baseSumRate, this.effectiveInvestmentTime);
        //计算基准的年化波动率
        traceBackNumVal.baseReturnVolatility = this.calculateReturnVolatility(stdevBase);
        //计算基准的夏普比率
        traceBackNumVal.baseSharpeRatio = this.calculateSharpeRatio(
                traceBackNumVal.baseAnnualizedRateOfReturn, traceBackNumVal.baseReturnVolatility);
        System.out.println("initBase Over");

    }

    /**
     * 计算Beta
     *
     * @param strategy  策略的日收益率
     * @param base      基准的日收益率
     * @param baseStdev 基准日收益率的标准差
     * @return double beta
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateBeta(List<DailyRate> strategy, List<DailyRate> base, double baseStdev) {
        List<DailyRate> result = new ArrayList<>();

        for (int i = 0; i < base.size(); i++) {
            result.add(new DailyRate(strategy.get(i).rate * base.get(i).rate, null));
        }

        double beta = this.calMeanOfDaily(result) - (meanStrategy * meanBase);
        beta = beta / Math.pow(baseStdev, 2);
        return beta;
    }

    /**
     * 计算alpha
     *
     * @param beta                       贝塔
     * @param annualizedRateOfReturn     策略的年化收益率
     * @param baseAnnualizedRateOfReturn 基准年化收益率
     * @return double alpha
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateAlpha(double beta, double annualizedRateOfReturn, double baseAnnualizedRateOfReturn) {
        double result = annualizedRateOfReturn - beta * baseAnnualizedRateOfReturn;
        result = result + (beta - 1) * riskFreeRate;
        return result;
    }

    /**
     * 计算年化收益率
     *
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
     * 计算夏普比率
     *
     * @param annualizedReturn 年化收益率
     * @param returnVolatility 年化波动收益率
     * @return double 夏普比率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateSharpeRatio(double annualizedReturn, double returnVolatility) {
        return ((annualizedReturn - this.riskFreeRate) / returnVolatility);
    }

    /**
     * 计算年化波动收益率
     *
     * @param stdevRate 年化收益率
     * @return double 年化波动收益率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateReturnVolatility(double stdevRate) {
        return (stdevRate * Math.sqrt(250));
    }

    /**
     * 计算日收益率均值
     *
     * @param rateList 日收益列表
     * @return double 日收益均值
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calMeanOfDaily(List<DailyRate> rateList) {
        double sum = 0;
        for (DailyRate dailyRate : rateList) {
            sum = sum + dailyRate.rate;
        }
        return (sum / (double) rateList.size());
    }

    /**
     * 计算日收益率标准差
     *
     * @param rateList 日收益列表
     * @param mean     均值
     * @return double 日收益标准差
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calStdevOfDaily(List<DailyRate> rateList, double mean) {

        List<DailyRate> tempList = rateList;

        for (DailyRate dailyRate : tempList) {
            dailyRate.rate = Math.pow(dailyRate.rate, 2);
        }
        double meanPow = this.calMeanOfDaily(tempList);
        double result = meanPow - Math.pow(mean, 2);
        return Math.sqrt(result);
    }

    /**
     * 计算策略总收益
     *
     * @return double 总收益
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/10
     */
    private double calStrategySumRate() {
        List<CumulativeReturn> list = traceBackInfo.strategyCumulativeReturn;
        CumulativeReturn last = list.get(list.size() - 1);
        return last.cumulativeReturn;
    }

    /**
     * 计算基准总收益
     *
     * @return double 总收益
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calBaseSumRate() {
        List<CumulativeReturn> list = traceBackInfo.baseCumulativeReturn;
        CumulativeReturn last = list.get(list.size() - 1);
        return last.cumulativeReturn;
    }

    /**
     * 获取策略的日收益列表
     *
     * @param list 板块中所有股票的日收益列表
     * @return List<Double> 策略的信息列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/14
     */
    private List<DailyRate> calStrategyDailyRateAll(List<List<StrategyStock>> list) {
        Map<LocalDate, DailyRate> map = this.convert(list);
        List<DailyRate> result = new ArrayList<>();

        for (LocalDate date : map.keySet()) {
            if(!isDateWithin(date)){continue;}
            DailyRate dailyRate = map.get(date);
            dailyRate.rate = dailyRate.rate / dailyRate.count;
            result.add(dailyRate);
        }

        return result;
    }

    /**
     * 获取策略日收益列表
     *
     * @param list 股票信息列表
     * @return List<DailyRate> 策略的日收益列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/10
     */
    private List<DailyRate> calStrategyDailyRate(List<StrategyStock> list) {
        List<DailyRate> result = new ArrayList<>();

        for (StrategyStock strategyStock : list) {
            if (strategyStock.preClose == -1) {
                result.add(new DailyRate(0.0, strategyStock.date));
            } else {
                double rate = (strategyStock.close - strategyStock.preClose) / strategyStock.preClose;
                result.add(new DailyRate(rate, strategyStock.date));
            }
        }
        return result;
    }

    /**
     * 获取基准日收益列表
     *
     * @param list 股票信息列表
     * @return List<DailyRate> 基准的日收益列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/10
     */
    private List<DailyRate> calBaseDailyRate(List<BaseStock> list) {
        System.out.println("in calculate");
        List<DailyRate> result = new ArrayList<>();

        for (BaseStock stock : list) {
            if (stock.getPreClose() == -1) {
                result.add(new DailyRate(0.0, stock.getStockID().getDate()));
            } else {
                double rate = (stock.getClose() - stock.getPreClose()) / stock.getPreClose();
                result.add(new DailyRate(rate, stock.getStockID().getDate()));
            }
        }
        System.out.println("over calculate");
        return result;
    }

    /**
     * 对多只股票的日收益率转换
     *
     * @param list 股票信息列表
     * @return Map<LocalDate, DailyRate> 策略的日收益map
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/14
     */
    private Map<LocalDate, DailyRate> convert(List<List<StrategyStock>> list) {

        Map<LocalDate, DailyRate> map = new TreeMap<>();

        for (List<StrategyStock> stock : list) {
            List<DailyRate> temp = this.calStrategyDailyRate(stock);
            for (DailyRate dailyRate : temp) {
                if (map.containsKey(dailyRate.date)) {
                    DailyRate dailyRate1 = map.get(dailyRate.date);
                    dailyRate1.rate = dailyRate1.rate + dailyRate.rate;
                    dailyRate1.count++;
                    map.put(dailyRate.date, dailyRate1);
                } else {
                    map.put(dailyRate.date, dailyRate);
                }
            }
        }
        return map;
    }

    private boolean isDateWithin(LocalDate localDate) {

        if (localDate.isEqual(traceBackCriteria.startDate) || localDate.isEqual(traceBackCriteria.endDate)) {
            return true;
        }

        if (localDate.isBefore(traceBackCriteria.endDate) && localDate.isAfter(traceBackCriteria.startDate)) {
            return true;
        }

        return false;
    }
}