package presentation.chart.TraceBack;

import service.TracebackService;
import vo.CumulativeReturnVO;
import vo.RelativeIndexReturnVO;
import vo.TracebackCriteriaVO;
import vo.TracebackNumValVO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/30.
 */
public class TracebackSeviceStub implements TracebackService {
    /**
     * 获取策略累计收益率
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO) {
        List<CumulativeReturnVO> list = new ArrayList<>();
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,1), 0, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,2), 0.0003, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,3), 0.0002, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,4), 0.0001, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,5), 0.0012, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,6), 0.011, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,7), 0.023, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,8), 0.0351, true));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,9), 0.0327, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,10), 0.0317, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,11), 0.0291, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,12), 0.0251, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,13), 0.0252, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,14), 0.0254, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,15), 0.0102, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,16), -0.0001, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,17), -0.0341, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,18), -0.0552, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,19), -0.0967, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,20), -0.1598, true));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,21), 0.0289, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,22), 0.0369, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,23), 0.049, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,24), 0.051, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,25), 0.104, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,26), 0.137, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,27), 0.1976, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,28), 0.2357, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,29), 0.2732, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,30), 0.301, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,31), 0.402, false));

        return list;
    }

    /**
     * 获取基准累计收益率
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 基准累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getBaseCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO) {
        List<CumulativeReturnVO> list = new ArrayList<>();
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,1), 0, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,2), 0.0001, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,3), -0.0001, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,4), 0.0001, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,5), 0.0005, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,6), 0.0078, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,7), 0.0132, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,8), 0.0169, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,9), 0.0298, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,10), 0.0389, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,11), 0.0457, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,12), 0.0568, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,13), 0.0673, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,14), 0.0791, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,15), 0.0952, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,16), 0.1076, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,17), 0.153, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,18), 0.178, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,19), 0.2032, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,20), 0.2597, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,21), 0.2878, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,22), 0.3043, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,23), 0.3333, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,24), 0.361, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,25), 0.2963, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,26), 0.2314, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,27), 0.2001, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,28), 0.165, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,29), 0.2087, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,30), 0.2156, false));
        list.add(new CumulativeReturnVO(LocalDate.of(2014,7,31), 0.2289, false));

        return list;
    }

    @Override
    public List<CumulativeReturnVO> getCustomizedCumulativeReturn(TracebackCriteriaVO tracebackCriteriaVO, List<String> stockCodes) {
        return null;
    }

    /**
     * 计算回测中用列表列出的数值型数据，如阿尔法，beta
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return TracebackNumValVO 所需的所有数值型数据保存对象
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public TracebackNumValVO getNumericalVal(TracebackCriteriaVO tracebackCriteriaVO) {
        return null;
    }

    /**
     * 计算相对收益指数
     *
     * @param tracebackCriteriaVO 用户所选回测条件
     * @return RelativeIndexReturnVO 保存表示相对收益指数的对象，包括正周期数，负周期数和赢率
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public RelativeIndexReturnVO getRelativeIndexReturn(TracebackCriteriaVO tracebackCriteriaVO) {
        return null;
    }
}
