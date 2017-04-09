package service.serviceImpl.TraceBackService;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import service.StockService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.StockVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackNumValVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/4/9.
 */
public class TraceBackParameter {

    //无风险收益率，默认为4%，可更改
    private double riskFreeRate = 0.04;

    //逻辑层对象
    private StockService stockService;

    //策略的日收益均值
    private double meanStrategy;

    //基准的日收益均值
    private double meanBase;

    //策略的日收益标准差
    private double stdevStrategy;

    //基准的日收益标准差
    private double stdevBase;

    //回测所需要显示参数的信息载体
    private TraceBackNumValVO traceBackNumValVO;

    //策略的每日收益列表
    private List<Double> strategyRate;

    //基准的每日收益列表
    private List<Double> baseRate;

    /**
     * 对回测所需要显示参数的初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public TraceBackParameter(TraceBackCriteriaVO traceBackCriteriaVO) throws CodeNotFoundException, IOException, DateNotWithinException, NoDataWithinException, DateShortException {
        this.stockService = new StockServiceImpl();
        traceBackNumValVO = new TraceBackNumValVO();
        this.initStrategy(traceBackCriteriaVO);
        this.initBase(traceBackCriteriaVO);
    }

    /**
     * 获取回测所需要显示参数
     *
     * @return TraceBackNumValVO 回测所需要显示载体
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public TraceBackNumValVO getTraceBackNumValVO() {
        return traceBackNumValVO;
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
     * @param  riskFreeRate 需要修改的无风险收益率
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
     * @param traceBackCriteriaVO
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private void initStrategy(TraceBackCriteriaVO traceBackCriteriaVO) throws CodeNotFoundException, DateShortException, DateNotWithinException, NoDataWithinException, IOException {
        List<String> codes = stockService.getStockPool(traceBackCriteriaVO.stockPoolVO);
        List<List<Double>> list = new ArrayList<>();

        for(String code : codes){
            list.add(this.calDailyRate(stockService.getOneStockData(code,traceBackCriteriaVO.startDate,traceBackCriteriaVO.endDate)));
        }
        strategyRate = this.calStrategyDailyRate(list);

        //计算策略的总收益率
        traceBackNumValVO.sumRate = this.calSumRate(strategyRate);
        //计算策略的日收益率均值
        this.meanStrategy = this.calMeanOfDaily(strategyRate);
        //计算策略的日收益率标准差
        this.stdevStrategy = this.calStdevOfDaily(strategyRate, meanStrategy);
        //计算策略的年化收益率
        traceBackNumValVO.annualizedRateOfReturn = this.calculateAnnualizedReturn(
                traceBackNumValVO.sumRate,strategyRate.size());
        //计算策略的年化波动率
        traceBackNumValVO.returnVolatility = this.calculateReturnVolatility(stdevStrategy);
        //计算夏普比率
        traceBackNumValVO.sharpeRatio = this.calculateSharpeRatio(
                traceBackNumValVO.annualizedRateOfReturn,traceBackNumValVO.returnVolatility);
        //计算beta
        traceBackNumValVO.beta = this.calculateBeta(strategyRate,baseRate,stdevBase);
        //计算alpha
        traceBackNumValVO.alpha = this.calculateAlpha(traceBackNumValVO.beta,traceBackNumValVO.annualizedRateOfReturn
        ,traceBackNumValVO.baseAnnualizedRateOfReturn);
    }

    /**
     * 初始化基准的参数信息
     *
     * @param traceBackCriteriaVO
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private void initBase(TraceBackCriteriaVO traceBackCriteriaVO) throws CodeNotFoundException, DateShortException, DateNotWithinException, NoDataWithinException, IOException {
        baseRate = this.calDailyRate(stockService.getBaseStockData(traceBackCriteriaVO.baseStockName,
                traceBackCriteriaVO.startDate,traceBackCriteriaVO.endDate));

        //计算基准的总收益率
        traceBackNumValVO.baseSumRate = this.calSumRate(baseRate);
        //计算基准的日收益率均值
        this.meanBase = this.calMeanOfDaily(baseRate);
        //计算基准的日收益率标准差
        this.stdevBase = this.calStdevOfDaily(baseRate,meanBase);
        //计算基准的年化收益率
        traceBackNumValVO.baseAnnualizedRateOfReturn = this.calculateAnnualizedReturn(
                traceBackNumValVO.baseSumRate,baseRate.size());
        //计算基准的年化波动率
        traceBackNumValVO.baseReturnVolatility = this.calculateReturnVolatility(stdevBase);
        //计算基准的夏普比率
        traceBackNumValVO.baseSharpeRatio = this.calculateSharpeRatio(
                traceBackNumValVO.baseAnnualizedRateOfReturn,traceBackNumValVO.baseReturnVolatility);
    }

    /**
     * 计算Beta
     *
     * @param strategy 策略的日收益率
     * @param base 基准的日收益率
     * @param baseStdev 基准日收益率的标准差
     * @return double beta
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateBeta(List<Double> strategy, List<Double> base, double baseStdev){
        List<Double> result =  new ArrayList<>();
        for(int i=0;i<strategy.size();i++){
            result.add(strategy.get(i)*base.get(i));
        }

        double beta = this.calMeanOfDaily(result)-(meanStrategy*meanBase);
        beta = beta/Math.pow(baseStdev,2);
        return beta;
    }

    /**
     * 计算alpha
     *
     * @param beta 贝塔
     * @param annualizedRateOfReturn 策略的年化收益率
     * @param baseAnnualizedRateOfReturn 基准年化收益率
     * @return double alpha
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateAlpha(double beta,double annualizedRateOfReturn, double baseAnnualizedRateOfReturn){
        double result = annualizedRateOfReturn - beta*baseAnnualizedRateOfReturn;
        result = result + (beta-1)*riskFreeRate;
        return result;
    }

    /**
     * 计算年化收益率
     *
     * @param rate 总收益率
     * @param days 指定时间区间天数
     * @return  double 年化收益率
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateAnnualizedReturn(double rate, double days){
        double result = Math.pow((rate+1),(365.25/days))-1;
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
    private double calculateSharpeRatio(double annualizedReturn ,double returnVolatility){
        return ((annualizedReturn-this.riskFreeRate)/returnVolatility);
    }

    /**
     * 计算年化波动收益率
     *
     * @param stdevRate 年化收益率
     * @auther Byron Dong
     * @return double 年化波动收益率
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calculateReturnVolatility(double stdevRate){
        return (stdevRate*Math.sqrt(250));
    }

    /**
     * 计算日收益率均值
     *
     * @param rateList 日收益列表
     * @auther Byron Dong
     * @return double 日收益均值
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calMeanOfDaily(List<Double> rateList){
        double sum = 0;
        for(Double rate: rateList){
            sum = sum +rate;
        }
        return (sum/(double)rateList.size());
    }

    /**
     * 计算日收益率标准差
     *
     * @param rateList 日收益列表
     * @param  mean 均值
     * @return double 日收益标准差
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calStdevOfDaily(List<Double> rateList,double mean){

        List<Double> tempList = rateList;

        for(Double rate: tempList){
            rate = Math.pow(rate, 2);
        }
        double meanPow = this.calMeanOfDaily(tempList);
        double result = meanPow - Math.pow(mean,2);
        return Math.sqrt(result);
    }

    /**
     * 计算总收益
     *
     * @param rates 日收益列表
     * @return double 总收益
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private double calSumRate(List<Double> rates){
        double result = 0;
        for(Double rate: rates){
            result = result + rate;
        }
        return result;
    }

    /**
     * 获取策略的日收益列表
     *
     * @param list 板块中所有股票的日收益列表
     * @return List<Double> 策略的信息列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private List<Double> calStrategyDailyRate(List<List<Double>> list){
        List<Double> result = list.get(0);

        for(int i=1;i<list.size();i++){
            for(int j=0;j<list.get(i).size();j++){
                double dailyRate = result.get(j);
                dailyRate = dailyRate+list.get(i).get(j);
                result.remove(j);
                result.add(j,dailyRate);
            }
        }

        for(Double rate: result){
            rate = rate/((double)list.size());
        }

        return result;
    }

    /**
     * 获取日收益列表
     *
     * @param list 股票信息列表
     * @return List<Double> 策略的日收益列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private List<Double> calDailyRate(List<StockVO> list){
        List<Double> result = new ArrayList<>();
        result.add(0.0);

        for(int i=1; i<list.size();i++){
            double rate = (list.get(i).close-list.get(i).open)/list.get(i-1).close;
            result.add(rate);
        }

        return result;
    }

}
