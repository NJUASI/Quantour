package vo;

import com.sun.deploy.trace.Trace;

import java.util.List;

/**
 * Created by harvey on 17-4-9.
 */
public class TraceBackVO {

    // 基准累计收益率
    public List<CumulativeReturnVO> baseCumulativeReturn;

    // 策略累计收益率，历史持仓详情
    public TraceBackStrategyVO traceBackStrategyVO;




    // 基准的数值型数据
    public TraceBackNumValVO baseTraceBackNumValVO;

    // 策略的数值型数据
    public TraceBackNumValVO strategyTraceBackNumValVO;

}
