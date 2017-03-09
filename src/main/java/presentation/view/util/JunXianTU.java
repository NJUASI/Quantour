package presentation.view.util;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleEdge;

import javafx.scene.text.Font;


/**
 *
 * @author ccw
 * @date 2014-6-11
 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 *
 *       </p>
 */
class JunXianTu implements ChartMouseListener{
    JFreeChart chart;
    ChartPanel chartPanel ;
    /**
     * 创建数据集合
     *
     * @return
     */
    public TimeSeriesCollection createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        String[] categories = { "日均线", "10日均线","20日" };

        for (int row = 0; row < categories.length; row++) {
            Vector<Object[]> dateValues = new Vector<Object[]>();

            for (int i = 0; i < 5; i++) {
                String date = (2000 ) + "-1" +  "-1"+i;

                Object[] dateValue = { date, 1000*((int)(Math.random()*10))};

                dateValues.add(dateValue);
            }

            TimeSeries timeSeries = ChartUtils.createTimeseries(categories[row], dateValues);
            dataset.addSeries(timeSeries);
        }
        return dataset;
    }

    public ChartPanel createChart() {
        // 2：创建Chart[创建不同图形]
        TimeSeriesCollection dataset = createDataset();
        chart = ChartFactory.createTimeSeriesChart("日线k线均线图", "订单", "订单", dataset);
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[创建不同图形]
        ChartUtils.setTimeSeriesRender(chart.getPlot(), false, false);
        // 5:对其他部分进行渲染
        XYPlot xyplot = (XYPlot) chart.getPlot();
        ChartUtils.setXY_XAixs(xyplot);
        ChartUtils.setXY_YAixs(xyplot);
        // 日期X坐标轴
        DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
        domainAxis.setAutoTickUnitSelection(false);
        DateTickUnit dateTickUnit = null;
        domainAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());


        if (dataset.getItemCount(0) < 15) {
            //刻度单位月,半年为间隔
            XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
            xyRenderer.setBaseItemLabelsVisible(false);
            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat("yyyy-MM-dd")); // 第二个参数是时间轴间距
        } else if(dataset.getItemCount(0) < 366) {// 数据过多,不显示数据
            XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
            xyRenderer.setBaseItemLabelsVisible(false);
            dateTickUnit = new DateTickUnit(DateTickUnitType.MONTH, 1, new SimpleDateFormat("yyyy-MM")); // 第二个参数是时间轴间距
        }else{// 数据过多,不显示数据
            XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
            xyRenderer.setBaseItemLabelsVisible(false);
            dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 1, new SimpleDateFormat("yyyy")); // 第二个参数是时间轴间距
        }


        // 设置时间单位
        domainAxis.setTickUnit(dateTickUnit);
        ChartUtils.setLegendEmptyBorder(chart);
        // 设置图例位置
        // 6:使用chartPanel接收
        chartPanel = new ChartPanel(chart);
//		chartPanel.addChartMouseListener(this);
        return chartPanel;
    }



    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 850);
        frame.setLocationRelativeTo(null);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建图形
                ChartPanel chartPanel = new JunXianTu().createChart();
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });

    }

    @Override
    public void chartMouseClicked(ChartMouseEvent arg0) {

    }

    @Override
    public void chartMouseMoved(ChartMouseEvent arg0) {
        // TODO Auto-generated method stub
        int xPos = arg0.getTrigger().getX();
        int yPos = arg0.getTrigger().getY();
        System.out.println("x = " + xPos + ", y = " + yPos);
        Point2D point2D = chartPanel.translateScreenToJava2D(new Point(xPos, yPos));
        XYPlot xyPlot = (XYPlot)chart.getPlot();
        ChartRenderingInfo chartRenderingInfo = chartPanel.getChartRenderingInfo();
        Rectangle2D rectangle2D = chartRenderingInfo.getPlotInfo().getDataArea();
        ValueAxis valueAxis1 = xyPlot.getDomainAxis();
        RectangleEdge rectangleEdge1 = xyPlot.getDomainAxisEdge();
        ValueAxis valueAxis2 = xyPlot.getRangeAxis();
        RectangleEdge rectangleEdge2 = xyPlot.getRangeAxisEdge();
        double d1 = valueAxis1.java2DToValue(point2D.getX(), rectangle2D, rectangleEdge1);
        double d2 = valueAxis2.java2DToValue(point2D.getY(), rectangle2D, rectangleEdge2);
        System.out.println("Chart: x = " + d1 + ", y = " + d2);

    }



}