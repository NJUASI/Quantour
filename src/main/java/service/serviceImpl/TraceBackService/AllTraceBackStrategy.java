package service.serviceImpl.TraceBackService;

import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.*;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by harvey on 17-4-3.
 */
public abstract class AllTraceBackStrategy {

    /**
     * 目标股票池
     */
    public List<String> traceBackStockPool;

    /**
     * 回测标准
     */
    protected TraceBackCriteriaVO traceBackCriteriaVO;


    /*
    需要重复计算的一些东西，故保存
     */
    /**
     * 所有有股票数据的交易日
     */
    protected List<LocalDate> allDatesWithData;

    /**
     * 所有股票池中的股票数据
     */
    protected Map<String, List<StrategyStock>> stockData;


    public AllTraceBackStrategy(List<String> traceBackStockPool, TraceBackCriteriaVO traceBackCriteriaVO, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        this.traceBackStockPool = traceBackStockPool;
        this.traceBackCriteriaVO = traceBackCriteriaVO;
        this.allDatesWithData = allDatesWithData;
        this.stockData = stockData;
    }

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    public abstract TraceBackStrategyVO traceBack() throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException;


    /**
     * 形成期／N日均值，用于后续策略筛选
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期
     * @param formativePeriod 形成期长度（MS）／N日均值偏离度（MR）
     * @return 形成的
     */
    protected abstract List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, DataSourceFirstDayException;

    /**
     * 挑选形成期后的股票数据，用于后续策略计算
     *
     * @param formativePeriodRate 形成期长度（MS）／N日均值偏离度（MR）
     * @return 持有期中会持有的股票代码
     */
    protected abstract List<String> pickStocks(List<FormativePeriodRateVO> formativePeriodRate);

    /**
     * 计算
     *
     * @param periodStart  持有期起始日期
     * @param periodEnd    持有期结束日期
     * @param periodSerial 周期序号
     */
    protected abstract void calculate(LocalDate periodStart, LocalDate periodEnd, int periodSerial) throws DateNotWithinException, NoDataWithinException, IOException;


    /**
     * 计算最大回撤点
     *
     * @param cumulativeReturnVOS 未计算最大回测的累计收益率
     * @return List<CumulativeReturnVO> 标记了两个最大回撤点的累计收益率，标记点的isTraceBack为true
     */
    public List<CumulativeReturnVO> maxRetracement(List<CumulativeReturnVO> cumulativeReturnVOS) {

        //TODO gcm 用了两个循环，不知道怎么改进算法，你们可以帮下忙

        //回撤点的峰值在list中的位置
        int top = 0;
        //回撤点的谷值在list中的位置
        int down = 0;

        //将第一个位置默认为最大回撤值点
        cumulativeReturnVOS.get(0).isTraceBack = true;

        double max = 0;

        for (int i = 0; i < cumulativeReturnVOS.size(); i++) {
            for (int j = i + 1; j < cumulativeReturnVOS.size(); j++) {
                double diff = cumulativeReturnVOS.get(i).cumulativeReturn - cumulativeReturnVOS.get(j).cumulativeReturn;
                if (max < diff) {
                    //重新设置最大回撤点
                    cumulativeReturnVOS.get(top).isTraceBack = false;
                    cumulativeReturnVOS.get(down).isTraceBack = false;
                    top = i;
                    down = j;
                    cumulativeReturnVOS.get(top).isTraceBack = true;
                    cumulativeReturnVOS.get(down).isTraceBack = true;
                    max = diff;
                }
            }
        }

        return cumulativeReturnVOS;
    }
}
