package service.serviceImpl.TracebackService;

import vo.CumulativeReturnVO;
import vo.TracebackCriteriaVO;

import java.util.List;

/**
 * Created by harvey on 17-4-3.
 */
public interface AllTracebackStrategy {

    public List<CumulativeReturnVO> traceback (List<String> stockPoolCodes, TracebackCriteriaVO tracebackCriteriaVO);

}
