package service.serviceImpl.TraceBackService.TraceBackStrategy;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import org.junit.Before;
import org.junit.Test;
import po.StockPO;
import service.serviceImpl.TraceBackService.AllTraceBackStrategy;
import service.serviceImpl.TraceBackService.TraceBackStrategy.Momentum.MomentumStrategy;
import utilities.enums.TraceBackStrategy;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.CumulativeReturnVO;
import vo.TraceBackCriteriaVO;
import vo.TraceBackStrategyVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by harvey on 17-4-6.
 */
public class MomentumStrategyTest {

    StockDao stockDao;
    AllTraceBackStrategy allTraceBackStrategy;

    List<String> stockCodes;
    TraceBackCriteriaVO traceBackCriteriaVO;

    //所有有数据的日期
    List<LocalDate> allDatesWithData = new ArrayList<>();

    //所选股票池的代码和它的所有信息的映射
    Map<String, List<StrategyStock>> stockData = new TreeMap<>();

    @Before
    public void setUp() throws Exception {
        stockDao = new StockDaoImpl();

        stockCodes = new ArrayList<>();
        stockCodes.add("000001");

        traceBackCriteriaVO = new TraceBackCriteriaVO();
        traceBackCriteriaVO.strategyType = TraceBackStrategy.MS;
        traceBackCriteriaVO.baseStockName = null;
        traceBackCriteriaVO.formativePeriod = 10;
        traceBackCriteriaVO.holdingPeriod = 5;
        traceBackCriteriaVO.startDate = LocalDate.of(2014,2,14);
        traceBackCriteriaVO.endDate = LocalDate.of(2014,4,29);
        traceBackCriteriaVO.isCustomized = true;

        setDates();
        setStockData(stockCodes);

        allTraceBackStrategy = new MomentumStrategy(stockCodes, traceBackCriteriaVO, allDatesWithData, stockData);
    }

    @Test
    public void traceBack() throws Exception {

        List<CumulativeReturnVO> cumulativeReturnVOS = allTraceBackStrategy.traceBack(traceBackCriteriaVO);;
        assertEquals(-0.02,cumulativeReturnVOS.get(1).cumulativeReturn,0.01);


    }


    private void setStockData(List<String> stockPoolCodes) throws IOException, NoDataWithinException, DateNotWithinException {
        for(int i = 0; i < stockPoolCodes.size(); i++){
            stockData.put(stockPoolCodes.get(i), convertStockPOS(stockDao.getStockData(stockPoolCodes.get(i))));
        }
    }

    private void setDates() throws IOException {
        //获取所有有数据的日期
        allDatesWithData = stockDao.getDateWithData();
    }

    private List<StrategyStock> convertStockPOS(List<StockPO> pos) {
        List<StrategyStock> result = new LinkedList<>();
        for (StockPO thisPO : pos) {
            result.add(new StrategyStock(thisPO));
        }
        return result;
    }
}