package service.serviceImpl.TracebackService;

import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.NoDataWithinException;
import vo.CumulativeReturnVO;
import vo.TraceBackCriteriaVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by harvey on 17-4-3.
 */
public interface AllTracebackStrategy {

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     * @param stockPoolCodes 目标股票池所有股票的代码
     * @param traceBackCriteriaVO 回测的所有标准
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    public List<CumulativeReturnVO> traceback (List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException;

}
