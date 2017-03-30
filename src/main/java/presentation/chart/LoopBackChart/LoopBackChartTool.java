package presentation.chart.LoopBackChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;
import presentation.chart.Candlestick.CandlestickChartTool;
import presentation.chart.tools.LoopBackXYLineRender;

import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/28.
 */
public class LoopBackChartTool {

    public static JFreeChart createLoopBackChart(){
        CandlestickChartTool.setChartTheme();
        JFreeChart chart = ChartFactory.createXYLineChart("","","", LoopBackChartTool.creatDataSet());
        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(LoopBackChartTool.getRender());
        plot.setDomainAxis(LoopBackChartTool.getX());
        plot.setRangeAxis(LoopBackChartTool.getY());

        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(new Color(44, 50, 54));
        plot.setRangeGridlinePaint(new Color(44, 50, 54));
        plot.setDomainGridlineStroke(new BasicStroke());
        plot.setRangeGridlineStroke(new BasicStroke());



        chart.setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setItemPaint(new Color(201, 208, 214));
        chart.getLegend().setBackgroundPaint(new Color(32,36,39));
        chart.getLegend().setFrame(new BlockBorder(new Color(32,36,39)));
        chart.getXYPlot().getDomainAxis().setVisible(true);
        chart.getTitle().setPaint(new Color(201, 208, 214));
        chart.setTextAntiAlias(false);


        return chart;
    }


    public static LoopBackXYLineRender getRender() {
        LoopBackXYLineRender lineAndShapeRenderer = new LoopBackXYLineRender();
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);

        lineAndShapeRenderer.setSeriesShapesVisible(0, false);
        lineAndShapeRenderer.setSeriesShapesVisible(1, false);

        lineAndShapeRenderer.setSeriesPaint(0, new Color(255,0,0));
        lineAndShapeRenderer.setSeriesPaint(1, new Color(39,118,192));

        lineAndShapeRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));
        lineAndShapeRenderer.setBaseShapesVisible(false);
        lineAndShapeRenderer.setSeriesShapesVisible(0,true);

        return lineAndShapeRenderer;
    }

    /**
     * 设置X轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/21
     * @return  DateAxis
     */
    public static DateAxis getX () {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        DateAxis xAxis = new DateAxis();
        LocalDate start =  LocalDate.of(2014,7,1);
        LocalDate end  = LocalDate.of(2014,7,31);

        xAxis.setAutoRange(true);//设置不采用自动设置时间范围
        try {
            xAxis.setRange(dateFormat.parse(start.toString()), dateFormat.parse(end.toString()));//设置时间范围，注意时间的最大值要比已有的时间最大值要多一天
        } catch (Exception e) {
            e.printStackTrace();
        }
//        xAxis.setTimeline(timeline);//设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期，使图形看上去连续
        xAxis.setAutoTickUnitSelection(false);//设置不采用自动选择刻度值
        xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);//设置标记的位置
        xAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());//设置标准的时间刻度单位
        xAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,3));//设置时间刻度的间隔，一般以周为单位
        xAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));//设置显示时间的格式
        xAxis.setLabelPaint(new Color(201, 208, 214));
        xAxis.setTickLabelPaint(new Color(201, 208, 214));
        xAxis.setAxisLineVisible(false);

        return xAxis;
    }

    /**
     * 设置K线的Y轴
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/11
     */
    public static NumberAxis getY(){
        NumberAxis yAxis = new NumberAxis();

        yAxis.setAutoRange(false);//不使用自动设定范围
        yAxis.setRange(-0.5, 0.5);//设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        yAxis.setTickUnit(new NumberTickUnit((0.1)));//设置刻度显示的密度
        yAxis.setLabelPaint(new Color(201, 208, 214));
        yAxis.setTickLabelPaint(new Color(201, 208, 214));
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());

        return yAxis;

    }

    public static TimeSeriesCollection creatDataSet(){
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        timeSeriesCollection.addSeries(LoopBackChartTool.createSeries1());
        timeSeriesCollection.addSeries(LoopBackChartTool.createSeries2());
        return timeSeriesCollection;
    }

    public static TimeSeries createSeries1(){
        TimeSeries series1 = new TimeSeries("本策略");
        series1.add(new Day(1,7,2014),0);
        series1.add(new Day(2,7,2014),0.0003);
        series1.add(new Day(3,7,2014),0.0002);
        series1.add(new Day(4,7,2014),0.0001);
        series1.add(new Day(5,7,2014),0.0012);
        series1.add(new Day(6,7,2014),0.011);
        series1.add(new Day(7,7,2014),0.023);
        series1.add(new Day(8,7,2014),0.0351);
        series1.add(new Day(9,7,2014),0.0327);
        series1.add(new Day(10,7,2014),0.0317);
        series1.add(new Day(11,7,2014),0.0291);
        series1.add(new Day(12,7,2014),0.0251);
        series1.add(new Day(13,7,2014),0.0252);
        series1.add(new Day(14,7,2014),0.0254);
        series1.add(new Day(15,7,2014),0.0102);
        series1.add(new Day(16,7,2014),-0.0001);
        series1.add(new Day(17,7,2014),-0.0341);
        series1.add(new Day(18,7,2014),-0.0552);
        series1.add(new Day(19,7,2014),-0.0967);
        series1.add(new Day(20,7,2014),-0.1598);
        series1.add(new Day(21,7,2014),0.0289);
        series1.add(new Day(22,7,2014),0.0369);
        series1.add(new Day(23,7,2014),0.049);
        series1.add(new Day(24,7,2014),0.051);
        series1.add(new Day(25,7,2014),0.104);
        series1.add(new Day(26,7,2014),0.137);
        series1.add(new Day(27,7,2014),0.1976);
        series1.add(new Day(28,7,2014),0.2357);
        series1.add(new Day(29,7,2014),0.2732);
        series1.add(new Day(30,7,2014),0.301);
        series1.add(new Day(31,7,2014),0.402);
        return series1;

    }

    public static TimeSeries createSeries2(){
        TimeSeries series2 = new TimeSeries("基准");
        series2.add(new Day(1,7,2014),0);
        series2.add(new Day(2,7,2014),0.0001);
        series2.add(new Day(3,7,2014),-0.0001);
        series2.add(new Day(4,7,2014),0.0001);
        series2.add(new Day(5,7,2014),0.0005);
        series2.add(new Day(6,7,2014),0.0078);
        series2.add(new Day(7,7,2014),0.0132);
        series2.add(new Day(8,7,2014),0.0169);
        series2.add(new Day(9,7,2014),0.0298);
        series2.add(new Day(10,7,2014),0.0389);
        series2.add(new Day(11,7,2014),0.0457);
        series2.add(new Day(12,7,2014),0.0568);
        series2.add(new Day(13,7,2014),0.0673);
        series2.add(new Day(14,7,2014),0.0791);
        series2.add(new Day(15,7,2014),0.0952);
        series2.add(new Day(16,7,2014),0.1076);
        series2.add(new Day(17,7,2014),0.153);
        series2.add(new Day(18,7,2014),0.178);
        series2.add(new Day(19,7,2014),0.2032);
        series2.add(new Day(20,7,2014),0.2597);
        series2.add(new Day(21,7,2014),0.2878);
        series2.add(new Day(22,7,2014),0.3043);
        series2.add(new Day(23,7,2014),0.3333);
        series2.add(new Day(24,7,2014),0.361);
        series2.add(new Day(25,7,2014),0.2963);
        series2.add(new Day(26,7,2014),0.2314);
        series2.add(new Day(27,7,2014),0.2001);
        series2.add(new Day(28,7,2014),0.165);
        series2.add(new Day(29,7,2014),0.2087);
        series2.add(new Day(30,7,2014),0.2156);
        series2.add(new Day(31,7,2014),0.2289);

        return series2;
    }
}
