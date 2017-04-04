package service.serviceImpl.TracebackService;

import vo.CumulativeReturnVO;
import vo.TracebackCriteriaVO;

import java.util.List;

/**
 * Created by harvey on 17-4-3.
 */
public interface AllTracebackStrategy {

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     * @param stockPoolCodes 目标股票池所有股票的代码
     * @param tracebackCriteriaVO 回测的所有标准
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    public List<CumulativeReturnVO> traceback (List<String> stockPoolCodes, TracebackCriteriaVO tracebackCriteriaVO);

}
