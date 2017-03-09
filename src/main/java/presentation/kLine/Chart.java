package presentation.kLine;

import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import vo.ChartShowCriteriaVO;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/8.
 */
public class Chart {

    private  CandlestickRenderer render;
    private  DateAxis xAxis;
    private NumberAxis yAxis;
    private NumberAxis yAxisOfVol;
    private XYBarRenderer xyBarRender;
    private LineData data;

    public Chart(String code) {
       this.init(code);
    }

    public Chart(ChartShowCriteriaVO chartShowCriteriaVO) {
        this.init(chartShowCriteriaVO);
    }

    public DateAxis getXAxis(){
        return xAxis;
    }

    public XYPlot getKLinePlot(){
        OHLCSeriesCollection seriesCollection = data.seriesCollection;
        XYPlot plot=new XYPlot(seriesCollection,xAxis,yAxis,render);//设置画图区域对象

        return plot;
    }

    public XYPlot getVolumePlot(double gap){
        TimeSeriesCollection timeSeriesCollection = data.timeSeriesCollection;
        this.setXYBarRender(data.seriesCollection,this.render,gap);
        XYPlot plot=new XYPlot(timeSeriesCollection,null,yAxisOfVol,xyBarRender);//建立第二个画图区域对象，主要此时的x轴设为了null值，因为要与第一个画图区域对象共享x轴

        return plot;

    }


    public void setRenderer(double gap){
        render.setUseOutlinePaint(true); //设置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
        render.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);//设置如何对K线图的宽度进行设定
        render.setAutoWidthGap(gap);//设置各个K线图之间的间隔
        render.setUpPaint(Color.RED);//设置股票上涨的K线图颜色
        render.setDownPaint(Color.GREEN);//设置股票下跌的K线图颜色
    }

    public void setX(LocalDate start, LocalDate end,int gap){
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

    public void setY(int num){

        yAxis.setAutoRange(false);//不使用自动设定范围
        yAxis.setRange(data.getLow()*0.9, data.getHigh()*1.1);//设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        yAxis.setTickUnit(new NumberTickUnit((data.getHigh()*1.1-data.getLow()*0.9)/num));//设置刻度显示的密度

    }

    public void setYOfVol(int num){

        yAxisOfVol.setAutoRange(false);//不使用自动设定范围
        yAxisOfVol.setRange(data.getLowVolume()*0.9, data.getHighVolume()*1.1);//设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        yAxisOfVol.setTickUnit(new NumberTickUnit((data.getHighVolume()*1.1-data.getLowVolume()*0.9)/num));//设置刻度显示的密度

    }

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

    private void init(String code){
        render = new CandlestickRenderer();
        xAxis = new DateAxis();
        yAxis = new NumberAxis();
        yAxisOfVol = new NumberAxis();
        data = new LineData(code);
        data.setData();
    }

    private void init(ChartShowCriteriaVO chartShowCriteriaVO){
        render = new CandlestickRenderer();
        xAxis = new DateAxis();
        yAxis = new NumberAxis();
        yAxisOfVol = new NumberAxis();
        data = new LineData(chartShowCriteriaVO);
        data.setData();
    }

}
