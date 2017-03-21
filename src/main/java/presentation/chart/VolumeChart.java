package presentation.chart;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import vo.StockVO;

import java.awt.*;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/21.
 */
public class VolumeChart {

    //数据的最高交易量
    private double highVolume = Double.MIN_VALUE;

    //数据的最低交易量
    private double lowVolume = Double.MAX_VALUE;

    //根据要求取出的数据
    private List<StockVO> data;

    private OHLCSeriesCollection ohlcSeriesCollection;

    private CandlestickRenderer candlestickRenderer;

    public VolumeChart(List<StockVO> data, OHLCSeriesCollection ohlcSeriesCollection, CandlestickRenderer candlestickRenderer) {
        this.data = data;
        this.ohlcSeriesCollection = ohlcSeriesCollection;
        this.candlestickRenderer = candlestickRenderer;
    }

    /**
     * 获取成交量的画板
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @return XYPlot 成交量画板
     */
    public XYPlot getVolumePlot(){
        XYPlot plot=new XYPlot(this.getVolumeData(),null,ChartTool.getY(this.lowVolume,this.highVolume,20),
                ChartTool.getXYBarRender(ohlcSeriesCollection,candlestickRenderer));
        //建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

        plot.setDomainGridlinePaint(new Color(192, 208, 224));
        plot.setRangeGridlinePaint(new Color(192, 208, 224));
        plot.setBackgroundPaint(new Color(32,36,39));

        return plot;
    }

    /**
     * 获取最高值和最低值(成交量)
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     */
    private void setHighAndLowVolume(TimeSeriesCollection timeSeriesCollection) {
        int seriesCount2 = timeSeriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount2; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (highVolume < timeSeriesCollection.getYValue(i, j)) {//取第i个序列中的第j个数据项的值
                    highVolume = timeSeriesCollection.getYValue(i, j);
                }
                if (lowVolume > timeSeriesCollection.getYValue(i, j)) {//取第i个序列中的第j个数据项的值
                    lowVolume = timeSeriesCollection.getYValue(i, j);
                }
            }
        }
    }

    /**
     * 获取成交量数据集合
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @Return TimeSeries 成交量数据集合
     */
    private TimeSeriesCollection getVolumeData() {
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("");//对应时间成交量数据
        //series.add(new Day(28, 9, 2007), 260659400/100);

        for (StockVO stockVO : this.data) {
            series.add(new Day(stockVO.date.getDayOfMonth(), stockVO.date.getMonth().getValue(), stockVO.date.getYear())
                    , Double.parseDouble(stockVO.volume) / 100);
        }
        timeSeriesCollection.addSeries(series);
        this.setHighAndLowVolume(timeSeriesCollection);

        return timeSeriesCollection;
    }
}
