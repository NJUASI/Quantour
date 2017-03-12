package presentation.line.kLine;

import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import presentation.view.util.ChartUtils;
import vo.ChartShowCriteriaVO;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/8.
 */
public class KChart {

    //K线的画笔
    private  CandlestickRenderer render;

    //K线与成交量共享的x轴
    private  DateAxis xAxis;

    //K线的y轴
    private NumberAxis yAxis;

    //成交量的y轴
    private NumberAxis yAxisOfVol;

    //成交量的画笔
    private XYBarRenderer xyBarRender;

    //图表的数据对象
    private LineData data;

    /**
     * 根据股票代号进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public KChart(String code) {
       this.init(code);
    }


    /**
     * 根据股票代号和起始日期进行初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public KChart(ChartShowCriteriaVO chartShowCriteriaVO) {
        this.init(chartShowCriteriaVO);
    }

    /**
     * 获取X轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return DateAxis x轴对象
     */
    public DateAxis getXAxis(){
        return xAxis;
    }

    /**
     * 获取图表title
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return DateAxis x轴对象
     */
    public String getTitle(){
        return this.data.getStockName();
    }

    /**
     * 设置画板，其中的参数后期商议后在提供接口
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  start x轴的最小时间
     * @param  end x轴的最大时间
     * @param  gap 显示时间的间隔
     */
    public void setPlot(LocalDate start,LocalDate end,int gap){
        this.setRenderer(0.001);//设置各个K线图之间的间隔
        this.setY(50);
        this.setYOfVol(10);
        this.setX(start,end,gap);
    }

    /**
     * 获取K线的画板
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return XYPlot K线画板
     */
    public XYPlot getKLinePlot(){
        OHLCSeriesCollection seriesCollection = data.seriesCollection;
        XYPlot plot=new XYPlot(null,xAxis,yAxis,null);//设置画图区域对象
        plot.setDomainGridlinesVisible(true);
        plot.setDataset(0,seriesCollection);
        plot.setRenderer(0,render);

        return plot;
    }

    /**
     * 获取成交量的画板
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @return XYPlot 成交量画板
     */
    public XYPlot getVolumePlot(double gap){
        TimeSeriesCollection timeSeriesCollection = data.timeSeriesCollection;
        this.setXYBarRender(data.seriesCollection,this.render,gap);
        XYPlot plot=new XYPlot(timeSeriesCollection,null,yAxisOfVol,xyBarRender);//建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

        return plot;

    }

    /**
     * 设置画笔
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  gap 两个蜡烛图间的间隔
     */
    private void setRenderer(double gap){
        render.setUseOutlinePaint(true); //设置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
        render.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);//设置如何对K线图的宽度进行设定
        render.setAutoWidthGap(gap);//设置各个K线图之间的间隔
        render.setUpPaint(Color.RED);//设置股票上涨的K线图颜色
        render.setDownPaint(Color.GREEN);//设置股票下跌的K线图颜色
    }

    /**
     * 设置X轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  gap 两个蜡烛图间的间隔
     * @param  start 开始时间
     * @param  end 结束时间
     */
    private void setX(LocalDate start, LocalDate end,int gap){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

        xAxis.setAutoRange(false);//设置不采用自动设置时间范围
        try{
            xAxis.setRange(dateFormat.parse(start.toString()),dateFormat.parse(end.toString()));//设置时间范围，注意时间的最大值要比已有的时间最大值要多一天
        }catch(Exception e){
            e.printStackTrace();
        }
        xAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());//设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期，使图形看上去连续
        xAxis.setAutoTickUnitSelection(false);//设置不采用自动选择刻度值
        xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);//设置标记的位置
        xAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());//设置标准的时间刻度单位
        xAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,gap));//设置时间刻度的间隔，一般以周为单位
        xAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));//设置显示时间的格式
    }

    /**
     * 设置K线的Y轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  num 密度（值越大密度越小）
     */
    private void setY(int num){

        yAxis.setAutoRange(false);//不使用自动设定范围
        yAxis.setRange(data.getLow()*0.9, data.getHigh()*1.1);//设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        yAxis.setTickUnit(new NumberTickUnit((data.getHigh()*1.1-data.getLow()*0.9)/num));//设置刻度显示的密度

    }


    /**
     * 设置成交量的Y轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  num 密度（值越大密度越小）
     */
    private void setYOfVol(int num){

        yAxisOfVol.setAutoRange(false);//不使用自动设定范围
        yAxisOfVol.setRange(data.getLowVolume()*0.9, data.getHighVolume()*1.1);//设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        yAxisOfVol.setTickUnit(new NumberTickUnit((data.getHighVolume()*1.1-data.getLowVolume()*0.9)/num));//设置刻度显示的密度

    }

    /**
     * 设置成交量的画笔
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  seriesCollection 数据集合
     * @param  candlestickRenderer K线的画笔
     * @param  gap 柱形图之间的间隔
     */
    private void setXYBarRender(OHLCSeriesCollection seriesCollection,CandlestickRenderer candlestickRenderer,double gap){
         xyBarRender=new XYBarRenderer(){

            private static final long serialVersionUID = 1L;

            public Paint getItemPaint(int i, int j){//匿名内部类用来处理当日的成交量柱形图的颜色与K线图的颜色保持一致
                if(seriesCollection.getCloseValue(i,j)>seriesCollection.getOpenValue(i,j)){//收盘价高于开盘价，股票上涨，选用股票上涨的颜色
                    return candlestickRenderer.getUpPaint();
                }else{
                    return candlestickRenderer.getDownPaint();
                }
            }};
        xyBarRender.setMargin(gap);//设置柱形图之间的间隔
    }

    /**
     * 初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  code 股票代号
     */
    private void init(String code){
        render = new CandlestickRenderer();
        xAxis = new DateAxis();
        yAxis = new NumberAxis();
        yAxisOfVol = new NumberAxis();
        data = new LineData(code);
        data.setData();
    }

    /**
     * 初始化
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     * @param  chartShowCriteriaVO 股票代号和日期
     */
    private void init(ChartShowCriteriaVO chartShowCriteriaVO){
        render = new CandlestickRenderer();
        xAxis = new DateAxis();
        yAxis = new NumberAxis();
        yAxisOfVol = new NumberAxis();
        data = new LineData(chartShowCriteriaVO);
        data.setData();
    }

}
