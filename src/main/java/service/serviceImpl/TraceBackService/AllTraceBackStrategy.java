package service.serviceImpl.TraceBackService;

import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.DateNotWithinException;
import utilities.exceptions.DateShortException;
import utilities.exceptions.NoDataWithinException;
import vo.CumulativeReturnVO;
import vo.TraceBackCriteriaVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by harvey on 17-4-3.
 */
public abstract class AllTraceBackStrategy {


    public AllTraceBackStrategy() {
    }

    /**
     * 根据目标股票池及所给的标准，返回策略的累计收益率
     *
     * @param stockPoolCodes      目标股票池所有股票的代码
     * @param traceBackCriteriaVO 回测的所有标准
     * @return List<CumulativeReturnVO> 策略的累计收益率
     */
    public abstract List<CumulativeReturnVO> traceBack(List<String> stockPoolCodes, TraceBackCriteriaVO traceBackCriteriaVO) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException;

    /**
     * 计算最大回撤点
     *
     * @param cumulativeReturnVOS 未计算最大回测的累计收益率
     * @return List<CumulativeReturnVO> 标记了两个最大回撤点的累计收益率，标记点的isTraceBack为true
     */
    public List<CumulativeReturnVO> maxRetracement(List<CumulativeReturnVO> cumulativeReturnVOS) {

        //TODO gcm 用了两个循环，不知道怎么改进算法，你们可以帮下忙

        //回撤点的峰值在list中的位置
        int top = 0;
        //回撤点的谷值在list中的位置
        int down = 0;

        //将第一个位置默认为最大回撤值点
        cumulativeReturnVOS.get(0).isTraceBack = true;

        double max = 0;

        for (int i = 0; i < cumulativeReturnVOS.size(); i++) {
            for (int j = 0; j < cumulativeReturnVOS.size(); j++) {
                double diff = cumulativeReturnVOS.get(i).cumulativeReturn - cumulativeReturnVOS.get(j).cumulativeReturn;
                if (max < diff) {
                    //重新设置最大回撤点
                    cumulativeReturnVOS.get(top).isTraceBack = false;
                    cumulativeReturnVOS.get(down).isTraceBack = false;
                    top = i;
                    down = j;
                    cumulativeReturnVOS.get(top).isTraceBack = true;
                    cumulativeReturnVOS.get(down).isTraceBack = true;
                }
            }
        }

        return cumulativeReturnVOS;
    }

}
