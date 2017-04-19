package service.serviceImpl.TraceBackService.TraceBackStrategy.FormateStrategy;


import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.*;
import vo.FormativePeriodRateVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/4/20.
 *
 * 根据成交量形成
 */
public class VolumeFormateStrategy extends AllFormateStrategy {

    public VolumeFormateStrategy(List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) {
        super(allDatesWithData, stockData);
    }

    /**
     * 形成期／N日均值，用于后续策略筛选
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期;
     * @param formativePeriod 形成期长度（MS）／N日均值偏离度（MR）
     * @return 形成的数据
     */
    @Override
    public List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, DataSourceFirstDayException {

        //形成期的起讫日期
        int periodStartIndex = allDatesWithData.indexOf(periodStart);
        LocalDate endOfFormative = allDatesWithData.get(periodStartIndex - 1);
        LocalDate startOfFormative = allDatesWithData.get(periodStartIndex - formativePeriod);

        List<FormativePeriodRateVO> formativePeriodRate = new ArrayList<>();

        for(int i = 0; i < stockCodes.size(); i++){
            double totalVolume = 0;
            List<StrategyStock> stockVOList = findStockVOsWithinDay(stockCodes.get(i), startOfFormative, endOfFormative);
            //说明为该形成期没有数据
            if(null == stockVOList){
                continue;
            }

            for(int j = 0; j < stockVOList.size(); j++){
                totalVolume += stockVOList.get(j).volume;
            }

            formativePeriodRate.add(new FormativePeriodRateVO(stockCodes.get(i), totalVolume));
        }

        return formativePeriodRate;
    }
}
