package service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;

import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.FormativePeriodRateVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期的涨幅策略
 */
public class IncreseAmountStrategy extends AllFormateStrategy {

    public IncreseAmountStrategy(List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        super(allDatesWithData, stockData);
    }

    @Override
    public List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException {
        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        LocalDate endOfFormative = allDatesWithData.get(periodStartIndex - 1);
        LocalDate startOfFormative = allDatesWithData.get(periodStartIndex - formativePeriod);


        List<FormativePeriodRateVO> formativePeriodRate = new ArrayList<>();
        for(int i = 0; i < stockCodes.size(); i++){
            List<StrategyStock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            double rate = (stockVOList.get(stockVOList.size()-1).close - stockVOList.get(0).close) / stockVOList.get(0).close;

            formativePeriodRate.add(new FormativePeriodRateVO(stockCodes.get(i), rate));
        }

        return formativePeriodRate;
    }
}
