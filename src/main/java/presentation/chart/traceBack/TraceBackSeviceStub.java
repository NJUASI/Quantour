package presentation.chart.traceBack;

import service.TraceBackService;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/3/30.
 */
public class TraceBackSeviceStub implements TraceBackService {
    @Override
    public TraceBackVO traceBack(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException {
        return null;
    }

    /**
     * 获取基准累计收益率，自选股票池
     *
     * @param start    回测区间起始日期
     * @param end      回测区间结束日期
     * @param stockMap
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     */
    @Override
    public List<CumulativeReturnVO> getCustomizedCumulativeReturn(LocalDate start, LocalDate end, Map<String, List<StockVO>> stockMap) throws IOException, NoDataWithinException, DateNotWithinException {
        return null;
    }

    /**
     * 获取策略累计收益率，自选股票池
     *
     * @param traceBackCriteriaVO 用户所选回测条件
     * @param stockCodes          策略累计收益率的列表
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     */
    public List<CumulativeReturnVO> getStrategyCumulativeReturnOfCustomized(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockCodes) {
        return null;
    }

    /**
     * 计算回测中用列表列出的数值型数据，如阿尔法，beta
     *
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return TraceBackNumValVO 所需的所有数值型数据保存对象
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public TraceBackNumValVO getNumericalVal(TraceBackCriteriaVO traceBackCriteriaVO) {
        return null;
    }

    /**
     * 计算相对收益指数
     *
     * @param traceBackCriteriaVO 用户所选回测条件
     * @return RelativeIndexReturnVO 保存表示相对收益指数的对象，包括正周期数，负周期数和赢率
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    public RelativeIndexReturnVO getRelativeIndexReturn(TraceBackCriteriaVO traceBackCriteriaVO) {
        return null;
    }
}
