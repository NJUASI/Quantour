package presentation.view.chart;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleEdge;

import javafx.scene.text.Font;
import presentation.view.util.ChartUtils;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import vo.StockComparisionVO;
import vo.StockComparsionCriteriaVO;


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
public class CompareChart4 {
    JFreeChart chart;
    ChartPanel chartPanel ;
    ChartService service;
    Map<LocalDate,Double> num1,num2;
    String name1,name2;
    String y,message;
    CompareChart4( Map<LocalDate,Double> num1,Map<LocalDate,Double> num2,String name1,String name2,String y, String message){
        this.num1=num1;
        this.num2=num2;
        this.name1=name1;
        this.name2=name2;
        this.y=y;
        this.message=message;
    }
    /**
     * 创建数据集合
     *
     * @return
     */
    public TimeSeriesCollection createDataset() {
//        try {
//            service=new ChartServiceImpl();
//            list = service.getComparision(new StockComparsionCriteriaVO("1",
//                    "10", LocalDate.of(2014, 1, 1), LocalDate.of(2014, 1, 10)));
//            vo1 = list.get(0);
//            vo2 = list.get(1);
//            num1=vo1.closes;
//            num2=vo2.closes;
//            System.out.println("2");
//        }catch (Exception e){
//
//        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        String[] categories = { name1, name2 };



            Vector<Object[]> dateValues = new Vector<Object[]>();
            for (Map.Entry<LocalDate, Double> m :num1.entrySet())  {
                String date = m.getKey().toString();

                Object[] dateValue = { date, m.getValue()};

                dateValues.add(dateValue);
            }
            TimeSeries timeSeries = ChartUtils.createTimeseries(categories[0], dateValues);
            dataset.addSeries(timeSeries);
            dateValues = new Vector<Object[]>();
            for (Map.Entry<LocalDate, Double> m :num2.entrySet())  {
                String date = m.getKey().toString();
                    Object[] dateValue = {date, m.getValue()};
                    dateValues.add(dateValue);


            }
           timeSeries = ChartUtils.createTimeseries(categories[1], dateValues);
            dataset.addSeries(timeSeries);

        return dataset;
    }

    public ChartPanel createChart() {
        // 2：创建Chart[创建不同图形]
        TimeSeriesCollection dataset = createDataset();
        chart = ChartFactory.createTimeSeriesChart(message, "", y, dataset);
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[创建不同图形]
        ChartUtils.setTimeSeriesRender(chart.getPlot(), true, false);
        // 5:对其他部分进行渲染
        XYPlot xyplot = (XYPlot) chart.getPlot();
        ChartUtils.setXY_XAixs(xyplot);
        ChartUtils.setXY_YAixs(xyplot);
        // 日期X坐标轴
        DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
        domainAxis.setAutoTickUnitSelection(false);
        DateTickUnit dateTickUnit = null;
        domainAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
        if (dataset.getItemCount(0) < 20) {
            //刻度单位月,半年为间隔
            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 4, new SimpleDateFormat("yyyy-MM-dd")); // 第二个参数是时间轴间距
        } else {// 数据过多,不显示数据
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
        return chartPanel;
    }



//    public static void main(String[] args) {
//        final JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1024, 420);
//        frame.setLocationRelativeTo(null);
//
//
//                ChartPanel chartPanel = new CompareChart4().createChart();
//                frame.getContentPane().add(chartPanel);
//                frame.setVisible(true);
//
//
//    }





}
