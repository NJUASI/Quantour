package presentation.chart.traceBack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.chart.tools.ChartUtils;
import presentation.chart.tools.CompareTool;
import presentation.chart.tools.Serie;
import presentation.view.tools.ColorUtils;

import java.awt.*;
import java.util.Vector;

/**
 * Created by 61990 on 2017/4/11.
 */
public class HistogramChart {
    public  HistogramChart(){

    }
    public DefaultCategoryDataset createDataset() {
        // 标注类别
        String[] categories = { " ", "2%", "  ", "4%", "   ", "6%", "    ", "8%", "     ", "10%", "      ", "12%" };
        Vector<Serie> series = new Vector<Serie>();
        // 柱子名称：柱子所有的值集合
        series.add(new Serie("正收益次数", new Integer[] {11,11,10,9,8,7,6,5,4,3,2,1}));
        series.add(new Serie("负收益次数", new Object[] {1,11,10,9,8,7,6,5,4,3,2,1}));

        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public ChartPanel createChart() {
        // 2：创建Chart
        JFreeChart chart = ChartFactory.createBarChart("", "", "次", createDataset());
        chart = CompareTool.setChartCategory(chart);//修饰chart

        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(ColorUtils.backgroundColor());
        return chartPanel;
    }
}
