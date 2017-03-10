package presentation.view.util;

import java.awt.*;
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
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;

import javafx.scene.text.Font;

public class TestAve{

    private TimeSeries series[] = new TimeSeries[2];    // 间隔定长时间(如年、月、日、时、分、秒等)的数据序列

    public TimeSeriesCollection getTimeSeriesColledtion(){
        this.createDataset();
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        timeSeriesCollection.addSeries(this.series[0]);
        timeSeriesCollection.addSeries(this.series[1]);

        return timeSeriesCollection;
    }

    public XYLineAndShapeRenderer getRender(){
        XYLineAndShapeRenderer lineAndShapeRenderer = new XYLineAndShapeRenderer();
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer.setSeriesShapesVisible(0, false);//设置不显示数据点模型
        lineAndShapeRenderer.setSeriesShapesVisible(1, false);
        lineAndShapeRenderer.setSeriesPaint(0, Color.BLUE);//设置均线颜色
        lineAndShapeRenderer.setSeriesPaint(1, Color.YELLOW);
        return lineAndShapeRenderer;
    }

    private XYDataset createDataset() {
        // 生成数据序列
        this.series[0] = new TimeSeries("股票价格");
        setSeriesData(series[0], 100, new Day(1,1,2011), 365); // 以天为时间单位，从2011年1月1日开始，随机产生365天的每天的模拟数据

////////////////////////////// 新增功能点 ////////////////////////////////////
            /*
             * MovingAverage有多个创建移动平均线对象的方法，
             * 有根据一个数据序列创建返回一个移动平均线序列、有根据一个数据集合创建返回带移动平均线的数据集合的等多种方法
             * 这里用到的是第一种创建方式
             * */
        // 根据数据序列生成移动平均值序列
        this.series[1] = MovingAverage.createMovingAverage(
                series[0],      // 源数据序列
                "移动平均线",    // 要创建的移动平均线序列名字
                30,             // 计算移动平均线的数据跨度
                0);             // 初始跳过的数据点的个数
//////////////////////////////////////////////////////////////////////

        // 将两条数据序列都放在一个数据集合中
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(this.series[0]);
        dataset.addSeries(this.series[1]);

        return dataset;
    }

    /**
     * 随机生成数据,自动定位到时间序列上的下一个时间点，将新数据点加入到数据序列中
     *
     * @param series    数据序列对象
     * @param baseData  生成的随机数据的基准值
     * @param regularTime   定长的时间间隔(年、月、日、时、分、秒等)
     * @param sampleNum  生成的数据点个数
     */
    private void setSeriesData(TimeSeries series, double baseData, RegularTimePeriod regularTime, int sampleNum) {

        // 生成随机模拟数据
        double value = baseData;
        for (int i = 0; i < sampleNum; i++) {
            series.add(regularTime, value);
            regularTime = regularTime.next();   //自动定位到下一个时间点
            value *= (1.0D + (Math.random() - 0.495D) / 4.0D);
        }
    }
}