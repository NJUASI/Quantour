package service;

import service.serviceImpl.TraceBackService.TraceBackStrategy.StrategyStock;
import utilities.exceptions.*;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测功能的接口
 */
public interface TraceBackService {


    /**
     *
     * @param traceBackCriteriaVO 回测标准
     * @param stockPool 自选股的代码列表
     * @return 回测所要展示的数据
     */
    TraceBackVO traceBack(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, UnhandleBlockTypeException, DataSourceFirstDayException, InvalidInputException;

    /**
     * 获取基准累计收益率，自选股票池
     * @param start 回测区间起始日期
     * @param end 回测区间结束日期
     * @param stockMap
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    List<CumulativeReturnVO> getCustomizedCumulativeReturn(LocalDate start, LocalDate end, Map<String, List<StrategyStock>> stockMap) throws IOException, NoDataWithinException, DateNotWithinException;

}
