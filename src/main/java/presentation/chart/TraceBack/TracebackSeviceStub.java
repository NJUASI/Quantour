package presentation.chart.TraceBack;

import service.TracebackService;
import vo.CumulativeReturnVO;
import vo.RelativeIndexReturnVO;
import vo.TracebackChoiceVO;
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
     * @param tracebackChoiceVO 用户所选回测条件
     * @return List<CumulativeReturnVO> 策略累计收益率的列表
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/28
     */
    @Override
    public List<CumulativeReturnVO> getStrategyCumulativeReturn(TracebackChoiceVO tracebackChoiceVO) {
        List<CumulativeReturnVO> list = new ArrayList<>();
        list.add(new CumulativeReturnVO(0, LocalDate.of(2014,7,1),false));
        list.add(new CumulativeReturnVO(0.0003, LocalDate.of(2014,7,2),false));
        list.add(new CumulativeReturnVO(0.0002, LocalDate.of(2014,7,3),false));
        list.add(new CumulativeReturnVO(0.0001, LocalDate.of(2014,7,4),false));
        list.add(new CumulativeReturnVO(0.0012, LocalDate.of(2014,7,5),false));
        list.add(new CumulativeReturnVO(0.011, LocalDate.of(2014,7,6),false));
        list.add(new CumulativeReturnVO(0.023, LocalDate.of(2014,7,7),false));
        list.add(new CumulativeReturnVO(0.0351, LocalDate.of(2014,7,8),true));
        list.add(new CumulativeReturnVO(0.0327, LocalDate.of(2014,7,9),false));
        list.add(new CumulativeReturnVO(0.0317, LocalDate.of(2014,7,10),false));
        list.add(new CumulativeReturnVO(0.0291, LocalDate.of(2014,7,11),false));
        list.add(new CumulativeReturnVO(0.0251, LocalDate.of(2014,7,12),false));
        list.add(new CumulativeReturnVO(0.0252, LocalDate.of(2014,7,13),false));
        list.add(new CumulativeReturnVO(0.0254, LocalDate.of(2014,7,14),false));
        list.add(new CumulativeReturnVO(0.0102, LocalDate.of(2014,7,15),false));
        list.add(new CumulativeReturnVO(-0.0001, LocalDate.of(2014,7,16),false));
        list.add(new CumulativeReturnVO(-0.0341, LocalDate.of(2014,7,17),false));
        list.add(new CumulativeReturnVO(-0.0552, LocalDate.of(2014,7,18),false));
        list.add(new CumulativeReturnVO(-0.0967, LocalDate.of(2014,7,19),false));
        list.add(new CumulativeReturnVO(-0.1598, LocalDate.of(2014,7,20),true));
        list.add(new CumulativeReturnVO(0.0289, LocalDate.of(2014,7,21),false));
        list.add(new CumulativeReturnVO(0.0369, LocalDate.of(2014,7,22),false));
        list.add(new CumulativeReturnVO(0.049, LocalDate.of(2014,7,23),false));
        list.add(new CumulativeReturnVO(0.051, LocalDate.of(2014,7,24),false));
        list.add(new CumulativeReturnVO(0.104, LocalDate.of(2014,7,25),false));
        list.add(new CumulativeReturnVO(0.137, LocalDate.of(2014,7,26),false));
        list.add(new CumulativeReturnVO(0.1976, LocalDate.of(2014,7,27),false));
        list.add(new CumulativeReturnVO(0.2357, LocalDate.of(2014,7,28),false));
        list.add(new CumulativeReturnVO(0.2732, LocalDate.of(2014,7,29),false));
        list.add(new CumulativeReturnVO(0.301, LocalDate.of(2014,7,30),false));
        list.add(new CumulativeReturnVO(0.402, LocalDate.of(2014,7,31),false));

        return list;
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
        List<CumulativeReturnVO> list = new ArrayList<>();
        list.add(new CumulativeReturnVO(0, LocalDate.of(2014,7,1),false));
        list.add(new CumulativeReturnVO(0.0001, LocalDate.of(2014,7,2),false));
        list.add(new CumulativeReturnVO(-0.0001, LocalDate.of(2014,7,3),false));
        list.add(new CumulativeReturnVO(0.0001, LocalDate.of(2014,7,4),false));
        list.add(new CumulativeReturnVO(0.0005, LocalDate.of(2014,7,5),false));
        list.add(new CumulativeReturnVO(0.0078, LocalDate.of(2014,7,6),false));
        list.add(new CumulativeReturnVO(0.0132, LocalDate.of(2014,7,7),false));
        list.add(new CumulativeReturnVO(0.0169, LocalDate.of(2014,7,8),false));
        list.add(new CumulativeReturnVO(0.0298, LocalDate.of(2014,7,9),false));
        list.add(new CumulativeReturnVO(0.0389, LocalDate.of(2014,7,10),false));
        list.add(new CumulativeReturnVO(0.0457, LocalDate.of(2014,7,11),false));
        list.add(new CumulativeReturnVO(0.0568, LocalDate.of(2014,7,12),false));
        list.add(new CumulativeReturnVO(0.0673, LocalDate.of(2014,7,13),false));
        list.add(new CumulativeReturnVO(0.0791, LocalDate.of(2014,7,14),false));
        list.add(new CumulativeReturnVO(0.0952, LocalDate.of(2014,7,15),false));
        list.add(new CumulativeReturnVO(0.1076, LocalDate.of(2014,7,16),false));
        list.add(new CumulativeReturnVO(0.153, LocalDate.of(2014,7,17),false));
        list.add(new CumulativeReturnVO(0.178, LocalDate.of(2014,7,18),false));
        list.add(new CumulativeReturnVO(0.2032, LocalDate.of(2014,7,19),false));
        list.add(new CumulativeReturnVO(0.2597, LocalDate.of(2014,7,20),false));
        list.add(new CumulativeReturnVO(0.2878, LocalDate.of(2014,7,21),false));
        list.add(new CumulativeReturnVO(0.3043, LocalDate.of(2014,7,22),false));
        list.add(new CumulativeReturnVO(0.3333, LocalDate.of(2014,7,23),false));
        list.add(new CumulativeReturnVO(0.361, LocalDate.of(2014,7,24),false));
        list.add(new CumulativeReturnVO(0.2963, LocalDate.of(2014,7,25),false));
        list.add(new CumulativeReturnVO(0.2314, LocalDate.of(2014,7,26),false));
        list.add(new CumulativeReturnVO(0.2001, LocalDate.of(2014,7,27),false));
        list.add(new CumulativeReturnVO(0.165, LocalDate.of(2014,7,28),false));
        list.add(new CumulativeReturnVO(0.2087, LocalDate.of(2014,7,29),false));
        list.add(new CumulativeReturnVO(0.2156, LocalDate.of(2014,7,30),false));
        list.add(new CumulativeReturnVO(0.2289, LocalDate.of(2014,7,31),false));

        return list;
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
