package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FilterConditionRate;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/6.
 */
public class MACD_FormateStrategy extends AllFormateStrategy{

    private IndicatorType MACD_Type;

    public MACD_FormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<Stock>> stockData, IndicatorType indicatorType) {
        super(allDatesWithData, stockData);
        this.MACD_Type = indicatorType;
    }

    /**
     * 根据当日或N日平均形成
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期;
     * @param formativePeriod 形成期长度
     * @return 形成的数据
     */
    @Override
    public List<FilterConditionRate> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws DataSourceFirstDayException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        if (periodStartIndex == 0) throw new DataSourceFirstDayException();

        List<FilterConditionRate> filterConditionRate = new ArrayList<>();

        for (int i = 0; i < stockCodes.size(); i++) {

            switch (MACD_Type){
                case MACD_DIF:
                    filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), computeDIF(stockCodes.get(i), periodStartIndex-1) , 0));
                    break;
                case MACD_DEA:
                    filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), computeDEA(stockCodes.get(i), periodStartIndex-1) , 0));
                    break;
                case MACD_COLUMN_VAL:
                    filterConditionRate.add(new FilterConditionRate(stockCodes.get(i), computeMACD_COLUMN(stockCodes.get(i), periodStartIndex-1) , 0));
                    break;
            }
        }

        return filterConditionRate;
    }

    /**
     * 计算某只股票的DIF值，短线的时间为12天，长线的时间为26天
     * @param code 需要计算DIF值的股票code
     * @param endIndex 计算日期的最后位置
     * @return
     */
    private Double computeDIF(String code, int endIndex){
        //短期——12天
        List<Stock> stockList_short = getDataWithoutHaltDay(code, endIndex, 12);
        //长期——26天
        List<Stock> stockList_long = getDataWithoutHaltDay(code, endIndex, 26);

        if(stockList_long == null){
            // 若数据不存在，indicatorVal为null
            return null;
        }

        return EMA_AfterAdjClose(stockList_short)- EMA_AfterAdjClose(stockList_long);
    }

    /**
     * 计算某只股票的DIF值，短线的时间为12天，长线的时间为26天
     * @param code 需要计算DIF值的股票code
     * @param endIndex 计算日期的最后位置
     * @return
     */
    private Double computeDEA(String code, int endIndex){

        //平滑指数, 一般取作2/(N+1) MID = 9
        double k = 2.0 / (9 + 1);

        //第一天的macd_dea以第一次计算的dif为准
        Double macd_dea = computeDIF(code, endIndex-8);
        if(macd_dea == null){
            return null;
        }

        for (int j = 1; j < 9; j++) {
            Double next = computeDIF(code, endIndex - j);

            // 数据不存在，返回null
            if(next == null){
                return null;
            }

            macd_dea = k*next + (1 - k) * macd_dea;
        }
        return macd_dea;
    }

    /**
     * 计算某只股票的MACD柱状值
     * @param code 需要计算DIF值的股票code
     * @param endIndex 计算日期的最后位置
     * @return
     */
    private Double computeMACD_COLUMN(String code, int endIndex){
        Double dif = computeDIF(code, endIndex);
        Double dea = computeDEA(code, endIndex);

        if(dif == null || dea == null){
            return null;
        }

        return 2*(dif - dea);
    }


    /**
     * 计算某只股票N个交易日的eam
     * @param stockList 需要计算的某只股票N个交易日的详情
     * @return
     */
    protected double EMA_AfterAdjClose(List<Stock> stockList){
        //平滑指数, 一般取作2/(N+1)
        double k = 2.0 / (stockList.size() + 1);

        //第一天ema等于当天的收盘价
        double ema = stockList.get(0).getAfterAdjClose();
        for(int i = 1; i < stockList.size(); i++){
            //第二天以后，当天收盘价 * 系数 加上昨天的ema*系数-1
            ema = k*stockList.get(i).getAfterAdjClose() + (1 - k) * ema;
        }

        return ema;
    }
}
