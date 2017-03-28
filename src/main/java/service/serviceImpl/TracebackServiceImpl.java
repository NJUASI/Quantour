package service.serviceImpl;

import service.TracebackService;
import vo.CumulativeReturnVO;
import vo.RelativeIndexReturnVO;
import vo.TracebackChoiceVO;
import vo.TracebackNumValVO;

import java.util.List;

/**
 * Created by harvey on 17-3-28.
 */
public class TracebackServiceImpl implements TracebackService {
    /**
     * 获取策略累计收益率
     *
     * @param tracebackChoiceVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TracebackChoiceVO tracebackChoiceVO) {
        return null;
    }

    /**
     * 获取基准累计收益率
     *
     * @param tracebackChoiceVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getBaseCumulativeReturn(TracebackChoiceVO tracebackChoiceVO) {
        return null;
    }

    /**
     * 计算回测中用列表列出的数值型数据，如阿尔法，beta
     *
     * @param tracebackChoiceVO 用户所选回测条件
     * @return TracebackNumValVO 所需的所有数值型数据保存对象
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public TracebackNumValVO getNumericalVal(TracebackChoiceVO tracebackChoiceVO) {
        return null;
    }

    /**
     * 计算相对收益指数
     *
     * @param tracebackChoiceVO 用户所选回测条件
     * @return RelativeIndexReturnVO 保存表示相对收益指数的对象，包括正周期数，负周期数和赢率
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public RelativeIndexReturnVO getRelativeIndexReturn(TracebackChoiceVO tracebackChoiceVO) {
        return null;
    }
}
