package presentation.chart.compare;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import presentation.chart.tools.CompareTool;
import presentation.chart.tools.DateTickUnitFactory;
import presentation.view.tools.ChartUtils;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


/**
 * @author ccw
 * @date 2014-6-11
 * <p>
 * 创建图表步骤：<br/>
 * 1：创建数据集合<br/>
 * 2：创建Chart：<br/>
 * 3:设置抗锯齿，防止字体显示不清楚<br/>
 * 4:对柱子进行渲染，<br/>
 * 5:对其他部分进行渲染<br/>
 * 6:使用chartPanel接收<br/>
 * <p>
 * </p>
 */
public class CompareChart4 {

    JFreeChart chart;
    ChartPanel chartPanel;
    Map<LocalDate, Double> num1, num2;
    String name1, name2;
    String y, message;

    public CompareChart4(Map<LocalDate, Double> num1, Map<LocalDate, Double> num2, String name1, String name2, String y, String message) {
        this.num1 = num1;
        this.num2 = num2;
        this.name1 = name1;
        this.name2 = name2;
        this.y = y;
        this.message = message;
    }

    public ChartPanel createChart(String code) throws IOException {
        TimeSeriesCollection dataset = createDataset();
        chart = ChartFactory.createTimeSeriesChart(message, "", y, dataset);
        chart = CompareTool.setChartXY(chart);
        XYPlot xyplot = (XYPlot) chart.getPlot();

        // 日期X坐标轴
        DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
        int num = dataset.getItemCount(0);
        this.setDateAxis(domainAxis,num,code);

        XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
        this.setRenderer(xyRenderer);

        ChartUtils.setLegendEmptyBorder(chart);

        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(32, 36, 39));

        return chartPanel;
    }

    /**
     * 创建数据集合
     *lastUpdatedBy Byron Dong
     * @return
     */
    public TimeSeriesCollection createDataset() {

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        String[] categories = {name1, name2};

        Vector<Object[]> dateValues = this.getDateValues(num1);

        TimeSeries timeSeries = ChartUtils.createTimeseries(categories[0], dateValues);
        dataset.addSeries(timeSeries);

        if (num2 != null) {
            dateValues = this.getDateValues(num2);
            timeSeries = ChartUtils.createTimeseries(categories[1], dateValues);
            dataset.addSeries(timeSeries);
        }

        return dataset;
    }

    private void setDateAxis(DateAxis dateAxis,int num, String code) throws IOException {
        DateTickUnitFactory factory = new DateTickUnitFactory();
        DateTickUnit dateTickUnit = factory.createDateTickUnit(num);

        dateAxis.setTimeline(this.getSegmentedTimeline(code));
        dateAxis.setAutoTickUnitSelection(false);
        dateAxis.setTickUnit(dateTickUnit);

    }

    private void setRenderer(XYLineAndShapeRenderer renderer) {
        renderer.setBaseItemLabelsVisible(false);
    }

    private SegmentedTimeline getSegmentedTimeline(String code) throws IOException {
        SegmentedTimeline timeline = SegmentedTimeline.newMondayThroughFridayTimeline();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        ChartService  chartService = new ChartServiceImpl();
        java.util.List<LocalDate> dateException = chartService.getDateWithoutData(code);

        try {
            for (LocalDate date : dateException) {
                timeline.addException(dateFormat.parse(date.toString()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeline;
    }

    private Vector<Object[]> getDateValues( Map<LocalDate, Double> map){
        Vector<Object[]> dateValues = new Vector<Object[]>();
        for (Map.Entry<LocalDate, Double> m : map.entrySet()) {
            String date = m.getKey().toString();

            Object[] dateValue = {date, m.getValue()};

            dateValues.add(dateValue);
        }
        return dateValues;
    }

}
