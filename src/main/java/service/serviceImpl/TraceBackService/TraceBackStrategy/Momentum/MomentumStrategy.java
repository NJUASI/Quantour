package service.serviceImpl.TraceBackService.TraceBackStrategy.Momentum;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import service.StockService;
import service.TraceBackService;
import service.serviceImpl.StockService.StockServiceImpl;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by harvey on 17-3-31.
 */
public class MomentumStrategy extends AllTraceBackStrategy {

    StockService stockService;
    TraceBackService traceBackService;
    StockDao stockDao;

    public MomentumStrategy(List<String> traceBackStockPool, TraceBackCriteriaVO traceBackCriteriaVO, List<LocalDate> allDatesWithData, Map<String, List<StrategyStock>> stockData) throws IOException {
        super(traceBackStockPool, traceBackCriteriaVO, allDatesWithData, stockData);

        stockService = new StockServiceImpl();
        stockDao = new StockDaoImpl();
        traceBackService = new TraceBackServiceImpl();
    }


    /**
     * 形成期／N日均值，用于后续策略筛选
     *
     * @param stockCodes      股票列表
     * @param periodStart     持有期起始日期;
     * @param formativePeriod 形成期长度（MS）／N日均值偏离度（MR）
     * @return 形成的
     */
    @Override
    protected List<FormativePeriodRateVO> formate(List<String> stockCodes, LocalDate periodStart, int formativePeriod) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException {
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

    /**
     * 对股票排序并挑选股票购买
     * @param formativePeriodRate 形成期内，目标股票池所有股票的代码与累计收益率的列表
     * @return 选取前20%的股票购买 //TODO 目前挑选股票的参数，暂定为取前20%，不知后期是否要做活一点儿
     */
    @Override
    protected List<String> pickStocks(List<FormativePeriodRateVO> formativePeriodRate) {
       List<String> sortedStockPool = sortStocks(formativePeriodRate);

       int size = formativePeriodRate.size();

       int topTwentyPercent;
       //将所有的股票分为5组
       if(size%5 == 0) topTwentyPercent = size/5;
       else topTwentyPercent = size/5+1;

       //取前1/5，即前20%
       return  sortedStockPool.subList(0,topTwentyPercent);
    }


    /**
     * 对形成期的目标股票池进行排序
     * @param formativePeriodRate 目标股票池的股票代码与形成期的累计收益率的键值对
     * @return 经排序后的目标股票池的所有股票的代码 //TODO gcm 排序策略？这里直接按降序排序
     */
    private List<String> sortStocks(List<FormativePeriodRateVO> formativePeriodRate) {

        List<String> sortedStockCodes = new ArrayList<String>();

        //按默认方式排序
        formativePeriodRate.sort(null);

        for(int i = 0; i < formativePeriodRate.size(); i++){
            sortedStockCodes.add(formativePeriodRate.get(i).stockCode);
        }

        return sortedStockCodes;
    }


    private List<StrategyStock> findStockVOsWithinDay(String stockCode, LocalDate start, LocalDate end){
        LocalDate thisStart = start;
        LocalDate thisEnd = end;

        List<StrategyStock> stockVOList = stockData.get(stockCode);

        List<LocalDate> dates = new ArrayList<>();
        for(int j = 0; j < stockVOList.size(); j++){
            dates.add(stockVOList.get(j).date);
        }

        while(!dates.contains(thisStart) || !dates.contains(thisEnd)){
            if(!dates.contains(thisStart)){
                thisStart = thisStart.plusDays(1);
            }
            if(!dates.contains(thisEnd)){
                thisEnd = thisEnd.minusDays(1);
            }
            if(thisStart.isAfter(thisEnd)){
                break;
            }
        }
        int startIndex = dates.indexOf(thisStart);
        int endIndex = dates.indexOf(thisEnd);

        //没有数据
        if(startIndex == -1){
            return null;
        }

        return stockVOList.subList(startIndex, endIndex+1);

    }
}
