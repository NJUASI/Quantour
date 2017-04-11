package service.serviceImpl.TraceBackService;

import service.StockService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/4/9.
 */
public class TraceBackParameter {

    //无风险收益率，默认为4%，可更改
    private double riskFreeRate = 0.04;

    //有效投资时间
    private double effectiveInvestmentTime = 360;

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

    //回测系统需要获取的所有信息
    private TraceBackVO traceBackVO;

    //策略的每日收益列表
    private List<DailyRateVO> strategyRate;

    //基准的每日收益列表
    private List<DailyRateVO> baseRate;

    /**
     * 对回测所需要显示参数的初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public TraceBackParameter(TraceBackCriteriaVO traceBackCriteriaVO, TraceBackVO traceBackVO) throws CodeNotFoundException, IOException, DateNotWithinException, NoDataWithinException, DateShortException {
        this.stockService = new StockServiceImpl();
        traceBackNumValVO = new TraceBackNumValVO();
        this.traceBackVO = traceBackVO;
        this.initStrategy(traceBackCriteriaVO);
        this.initBase(traceBackCriteriaVO);
        this.traceBackVO.traceBackNumValVO = this.traceBackNumValVO;
    }

    /**
     * 获取回测所需要显示参数
     *
     * @return TraceBackVO 回测所需要显示载体
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public TraceBackVO getTraceBackVO() {
        return traceBackVO;
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
     * @param traceBackCriteriaVO
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    private void initStrategy(TraceBackCriteriaVO traceBackCriteriaVO) throws CodeNotFoundException, DateShortException, DateNotWithinException, NoDataWithinException, IOException {
        List<String> codes = stockService.getStockPool(traceBackCriteriaVO.stockPoolVO);
        List<List<StockVO>> list = new ArrayList<>();

        for (String code : codes) {
            list.add(stockService.getOneStockData(code, traceBackCriteriaVO.startDate, traceBackCriteriaVO.endDate));
        }
        strategyRate = this.calStrategyDailyRate(list);

        //计算策略的总收益率
        traceBackNumValVO.sumRate = this.calSumRate(traceBackVO.traceBackStrategyVO.holdingDetailVOS);
        //计算策略的日收益率均值
        this.meanStrategy = this.calMeanOfDaily(strategyRate);
        //计算策略的日收益率标准差
        this.stdevStrategy = this.calStdevOfDaily(strategyRate, meanStrategy);
        //计算策略的年化收益率
        traceBackNumValVO.annualizedRateOfReturn = this.calculateAnnualizedReturn(
                traceBackNumValVO.sumRate, this.effectiveInvestmentTime);
        //计算策略的年化波动率
        traceBackNumValVO.returnVolatility = this.calculateReturnVolatility(stdevStrategy);
        //计算夏普比率
        traceBackNumValVO.sharpeRatio = this.calculateSharpeRatio(
                traceBackNumValVO.annualizedRateOfReturn, traceBackNumValVO.returnVolatility);
        //计算beta
        traceBackNumValVO.beta = this.calculateBeta(strategyRate, baseRate, stdevBase);
        //计算alpha
        traceBackNumValVO.alpha = this.calculateAlpha(traceBackNumValVO.beta, traceBackNumValVO.annualizedRateOfReturn
                , traceBackNumValVO.baseAnnualizedRateOfReturn);
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
                traceBackCriteriaVO.startDate, traceBackCriteriaVO.endDate));

        //计算基准的总收益率
        traceBackNumValVO.baseSumRate = this.calSumRateBase(traceBackVO.traceBackStrategyVO.holdingDetailVOS);
        //计算基准的日收益率均值
        this.meanBase = this.calMeanOfDaily(baseRate);
        //计算基准的日收益率标准差
        this.stdevBase = this.calStdevOfDaily(baseRate, meanBase);
        //计算基准的年化收益率
        traceBackNumValVO.baseAnnualizedRateOfReturn = this.calculateAnnualizedReturn(
                traceBackNumValVO.baseSumRate, this.effectiveInvestmentTime);
        //计算基准的年化波动率
        traceBackNumValVO.baseReturnVolatility = this.calculateReturnVolatility(stdevBase);
        //计算基准的夏普比率
        traceBackNumValVO.baseSharpeRatio = this.calculateSharpeRatio(
                traceBackNumValVO.baseAnnualizedRateOfReturn, traceBackNumValVO.baseReturnVolatility);
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
    public double calculateBeta(List<DailyRateVO> strategy, List<DailyRateVO> base, double baseStdev) {
        List<DailyRateVO> result = new ArrayList<>();
        //TODO 董金玉 逻辑可能有问题
        for (int i = 0; i < strategy.size(); i++) {
            result.add(new DailyRateVO(strategy.get(i).rate * base.get(i).rate,null));
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
    public double calculateAlpha(double beta, double annualizedRateOfReturn, double baseAnnualizedRateOfReturn) {
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
    public double calculateAnnualizedReturn(double rate, double days) {
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
    public double calculateSharpeRatio(double annualizedReturn, double returnVolatility) {
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
    public double calculateReturnVolatility(double stdevRate) {
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
    public double calMeanOfDaily(List<DailyRateVO> rateList) {
        double sum = 0;
        for (DailyRateVO dailyRateVO : rateList) {
            sum = sum + dailyRateVO.rate;
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
    public double calStdevOfDaily(List<DailyRateVO> rateList, double mean) {

        List<DailyRateVO> tempList = rateList;

        for (DailyRateVO dailyRateVO : tempList) {
            dailyRateVO.rate = Math.pow(dailyRateVO.rate, 2);
        }
        double meanPow = this.calMeanOfDaily(tempList);
        double result = meanPow - Math.pow(mean, 2);
        return Math.sqrt(result);
    }

    /**
     * 计算策略总收益
     *
     * @param holdingDetailVOS 包含持仓周期的基准收益
     * @return double 总收益
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/10
     */
    public double calSumRate(List<HoldingDetailVO> holdingDetailVOS) {
        double result = (holdingDetailVOS.get(holdingDetailVOS.size() - 1).remainInvestment /
                holdingDetailVOS.get(0).remainInvestment) - 1;
        return result;
    }

    /**
     * 计算基准总收益
     *
     * @param holdingDetailVOS 包含持仓周期的基准收益
     * @return double 总收益
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/9
     */
    public double calSumRateBase(List<HoldingDetailVO> holdingDetailVOS) {
        double result = 1.0;
        for (HoldingDetailVO holdingDetailVO : holdingDetailVOS) {
            result = result * (1 + holdingDetailVO.baseReturn);
        }
        return result - 1;
    }

    /**
     * 获取策略的日收益列表
     *
     * @param list 板块中所有股票的日收益列表
     * @return List<Double> 策略的信息列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/10
     */
    private List<DailyRateVO> calStrategyDailyRate(List<List<StockVO>> list) {
        //TODO 董金玉 冯俊杰 龚尘淼 商议接口
        List<DailyRateVO> result = null;


        return result;
    }

    /**
     * 获取日收益列表
     *
     * @param list 股票信息列表
     * @return  List<DailyRateVO> 策略的日收益列表
     * @auther Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/10
     */
    private List<DailyRateVO> calDailyRate(List<StockVO> list) {
        List<DailyRateVO> result = new ArrayList<>();

        for (StockVO stockVO : list) {
            if (stockVO.preClose == -1) {
                result.add(new DailyRateVO(0.0, stockVO.date));
            } else {
                double rate = (stockVO.close - stockVO.open) / stockVO.preClose;
                result.add(new DailyRateVO(rate, stockVO.date));
            }
        }
        return result;
    }

}
