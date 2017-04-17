package service;

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
    TraceBackVO traceBack(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, UnhandleBlockTypeException;

    /**
     * 获取基准累计收益率，自选股票池
     * @param start 回测区间起始日期
     * @param end 回测区间结束日期
     * @param stockMap
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    List<CumulativeReturnVO> getCustomizedCumulativeReturn(LocalDate start, LocalDate end, Map<String, List<StockVO>> stockMap) throws IOException, NoDataWithinException, DateNotWithinException;

    /**
     * 计算回测中用列表列出的数值型数据，如阿尔法，beta
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return TraceBackNumValVO 所需的所有数值型数据保存对象
     */
    TraceBackNumValVO getNumericalVal(TraceBackCriteriaVO traceBackCriteriaVO);




}
