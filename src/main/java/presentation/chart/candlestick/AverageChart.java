package presentation.chart.candlestick;

import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import presentation.chart.tools.CandlestickChartTool;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import utilities.enums.MovingAverageType;
import utilities.exceptions.*;
import vo.ChartShowCriteriaVO;
import vo.MovingAverageVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class AverageChart {

    //逻辑层对象
    private ChartService service;

    //需要显示均线列表
    private List<MovingAverageType> days;

    //均线数据集合
    private List<TimeSeries> data;

    /**
     * 根据股票代号和均线类型进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     */
    public AverageChart(String code, List<MovingAverageType> days) {
        service = new ChartServiceImpl();
        data = new ArrayList<>();
        this.days = days;
        this.readData(code);
    }

    /**
     * 根据股票代号，起始日期和均线类型进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     */
    public AverageChart(ChartShowCriteriaVO chartShowCriteriaVO, List<MovingAverageType> days) throws NoDataWithinException, CodeNotFoundException, DateShortException, DateNotWithinException, IOException, NoMatchEnumException {
        service = new ChartServiceImpl();
        data = new ArrayList<>();
        this.days = days;
        this.readData(chartShowCriteriaVO);
    }

    /**
     * 将均线数据和均线画笔添加到K线的画板中
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  plot K线的画版
     * @return  XYPlot 包含均线数据collection的画板
     * @throws ColorNotExistException 均线类型不存在
     */
    public XYPlot set(XYPlot plot) throws ColorNotExistException {
        plot.setDataset(1,this.getTimeSeriesCollection());
        plot.setRenderer(1, CandlestickChartTool.getAveragerRender(this.days));

        return plot;
    }

    /**
     * 获取均线数据collection
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @return  TimeSeriesCollection 均线数据collection
     */
    private TimeSeriesCollection getTimeSeriesCollection() {
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

        for (TimeSeries series : data) {
            timeSeriesCollection.addSeries(series);
        }

        return timeSeriesCollection;
    }

    /**
     * 根据股票代号获取股票数据
     *
     * @param code 股票代号
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     */
    private void readData(String code) {
        try {
            Map<MovingAverageType, List<MovingAverageVO>> tempMap = this.service.getAveData(code, this.days);

            for (MovingAverageType type : days) {
                TimeSeries series = new TimeSeries(type.toString());
                List<MovingAverageVO> movingAverageVOS = tempMap.get(type);
                for(int j = 0;j<movingAverageVOS.size();j++){
                    MovingAverageVO vo = movingAverageVOS.get(j);
                    series.add(new Day(vo.date.getDayOfMonth(), vo.date.getMonth().getValue(), vo.date.getYear()), vo.average);
                }
                data.add(series);
            }
        } catch (DateShortException e) {
            e.printStackTrace();
        } catch (DateNotWithinException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 根据股票代号和日期获取股票数据
     *
     * @param chartShowCriteriaVO 股票信息载体
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @throws NoDataWithinException
     * @throws CodeNotFoundException
     * @throws IOException
     * @throws DateNotWithinException
     * @throws DateShortException
     */
    private void readData(ChartShowCriteriaVO chartShowCriteriaVO) throws NoDataWithinException, CodeNotFoundException, IOException, DateNotWithinException, DateShortException, NoMatchEnumException {

            Map<MovingAverageType, List<MovingAverageVO>> tempMap = this.service.getAveData(chartShowCriteriaVO, this.days);

            for (MovingAverageType type : days) {
                TimeSeries series = new TimeSeries(type.toString());
                List<MovingAverageVO> movingAverageVOS = tempMap.get(type);
                for(int j = 0;j<movingAverageVOS.size();j++){
                    MovingAverageVO vo = movingAverageVOS.get(j);
                    series.add(new Day(vo.date.getDayOfMonth(), vo.date.getMonth().getValue(), vo.date.getYear()), vo.average);
                }
                data.add(series);
            }
    }

}
