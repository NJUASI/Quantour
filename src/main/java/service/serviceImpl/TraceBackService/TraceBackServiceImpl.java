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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by harvey on 17-3-28.
 */
public class TraceBackServiceImpl implements TraceBackService {

    private StockService stockService;


    public TraceBackServiceImpl() {
        stockService = new StockServiceImpl();
    }

    @Override
    public TraceBackVO traceBack(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException {
        // TODO strategy获得TraceBackStrategyVO，再获得Base的数据，再将超额收益初始进表内

        return null;
    }

    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TraceBackCriteriaVO traceBackCriteriaVO) throws DateNotWithinException, NoDataWithinException, IOException, DateShortException, CodeNotFoundException {
        return null;
    }

    @Override
    public List<CumulativeReturnVO> getBaseCumulativeReturn(LocalDate start, LocalDate end, String stockName) throws IOException, NoDataWithinException, DateNotWithinException {
        return null;
    }


    //TODO gcm 看看自选股和非自选股可否分开两个类，帮忙看
    /**
     * 获取基准累计收益率，自选股
     *
     * @param start 回测区间起始日期
     * @param end 回测区间结束日期
     * @param stockCodes 所有自选股的代码
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    @Override
    public List<CumulativeReturnVO> getCustomizedCumulativeReturn(LocalDate start, LocalDate end, List<String> stockCodes) throws IOException, NoDataWithinException, DateNotWithinException {

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();
        List<Map<LocalDate,CumulativeReturnVO>> everyCumulativeReturnVOs = new ArrayList<Map<LocalDate,CumulativeReturnVO>>();

        int span = start.until(end).getDays()+1;

        //添加第一天的数据，为0;
        cumulativeReturnVOS.add(new CumulativeReturnVO(start,0,false));

        //将每一支股票的信息添加进列表
        for(int i = 0; i < stockCodes.size(); i++){
            //每一支股票在日期范围内的累计收益率
            List<StockVO> list = stockService.getOneStockData(stockCodes.get(i),start,end);
            everyCumulativeReturnVOs.add(getCumulativeReturnOfOneStockMap(list,span));
        }


        for(int i = 1; i < span; i++){

            double totalCumulativeReturn = 0;
            int notSuspended = 0;

            for (int j = 0; j < everyCumulativeReturnVOs.size(); j++){
                if(everyCumulativeReturnVOs.get(j).containsKey(start.plusDays(i))){
                    totalCumulativeReturn += everyCumulativeReturnVOs.get(j).get(start.plusDays(i)).cumulativeReturn;
                    notSuspended += 1;
                }
            }
            //未停牌的股票支数不为0,则说明当天有数据
            if(notSuspended != 0){
                cumulativeReturnVOS.add(new CumulativeReturnVO(start.plusDays(i),totalCumulativeReturn/notSuspended,false));
            }
        }

        return cumulativeReturnVOS;
    }

    @Override
    public TraceBackNumValVO getNumericalVal(TraceBackCriteriaVO traceBackCriteriaVO) {

        TraceBackParameter parameter = null;
//        try {
//            parameter = new TraceBackParameter(traceBackCriteriaVO);
//        } catch (CodeNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (DateNotWithinException e) {
//            e.printStackTrace();
//        } catch (NoDataWithinException e) {
//            e.printStackTrace();
//        } catch (DateShortException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    /**
     * 获取每一支股票的日期与累计收益率的map，日期作为键值
     * @param list 日期范围内的一支股票的信息
     * @param span 日期范围
     * @return 每天日期所对应的股票的累计收益率
     */
    private Map<LocalDate,CumulativeReturnVO> getCumulativeReturnOfOneStockMap(List<StockVO> list, int span) {
        Map<LocalDate,CumulativeReturnVO> map = new TreeMap<LocalDate,CumulativeReturnVO>();

        //TODO gcm 将第一天的数据加入进去,查询果仁网，看第一天的日期是以交易日为准，还是以用户的选择为准
        CumulativeReturnVO firstDay = new CumulativeReturnVO(list.get(0).date,0,false);
        map.put(list.get(0).date,firstDay);

        //累计收益率以第一个交易日的收益率来对比计算
        double closeOfFirstDay = list.get(0).close;

        for(int i = 1; i < list.size(); i++) {
            double sucClose = list.get(i).close;
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            map.put(list.get(i).date,new CumulativeReturnVO(list.get(i).date, cumulativeReturn, false));
        }

        return map;
    }


    /**
     *
     * @param list 单一股票的信息
     * @param start 因为起始日期可能不是交易日，但是还是以起始日期为准 //TODO 在日期上，先把周末的日期挖掉,不让用户可以选择周末的日期
     * @return List<CumulativeReturnVO> 单一股票在时间区间内的累计收益率
     */
    private List<CumulativeReturnVO> getCumulativeReturnOfOneStock(List<StockVO> list,LocalDate start){

        List<CumulativeReturnVO> cumulativeReturnVOS = new ArrayList<CumulativeReturnVO>();

        //TODO gcm 将第一天的数据加入进去,查询果仁网，看第一天的日期是以交易日为准，还是以用户的选择为准
        CumulativeReturnVO firstDay = new CumulativeReturnVO(start,0,false);
        cumulativeReturnVOS.add(firstDay);

        //累计收益率以第一个交易日的收益率来对比计算
        double closeOfFirstDay = list.get(0).close;

        for(int i = 1; i < list.size(); i++) {
            double sucClose = list.get(i).close;
            double cumulativeReturn = (sucClose - closeOfFirstDay) / closeOfFirstDay;
            //先将所有的最大回测点设为false
            cumulativeReturnVOS.add(new CumulativeReturnVO(list.get(i).date, cumulativeReturn, false));
        }

        return cumulativeReturnVOS;
    }

}
