package presentation.chart.tools;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import presentation.view.tools.ColorUtils;

import java.awt.*;

/**
 * Created by Byron Dong on 2017/3/30.
 */
public class CompareTool {

    /**
     * 修饰CategoryChart
     *
     * @param  chart 图表对象
     * @return JFreeChart 图表对象
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    public static JFreeChart setChartCategory(JFreeChart chart){

        ChartUtils.setAntiAlias(chart);// 抗锯齿
        ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        chart.getLegend().setFrame(new BlockBorder(ColorUtils.backgroundColor()));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(ColorUtils.backgroundColor());

        chart.getCategoryPlot().getRenderer().setSeriesPaint(0,ColorUtils.upColor());
        chart.getCategoryPlot().getRenderer().setSeriesPaint(1,ColorUtils.downColor());

        return chart;
    }

    /**
     * 修饰XYChart
     *
     * @param  chart 图表对象
     * @return JFreeChart 图表对象
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/30
     */
    public static JFreeChart setChartXY(JFreeChart chart){

        ChartUtils.setAntiAlias(chart);// 抗锯齿
        ChartUtils.setTimeSeriesRender(chart.getPlot(), true, false);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        ChartUtils.setXY_XAixs(xyplot);
        ChartUtils.setXY_YAixs(xyplot);

        xyplot.setBackgroundPaint(ColorUtils.backgroundColor());

        return chart;
    }
}
