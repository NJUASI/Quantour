package presentation.chart.candlestick;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.chart.tools.ChartUtils;
import presentation.chart.tools.Serie;
import presentation.view.tools.ColorUtils;

import java.util.Vector;

/**
 * Created by 61990 on 2017/4/16.
 */
public class LineChart {
    public LineChart() {
    }

    public DefaultCategoryDataset createDataset() {
        // 标注类别
        String[] categories = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

        Vector<Serie> series = new Vector<Serie>();

        series.add(new Serie("Tyo", new Double[] { 1.0, 1.5, 6.4, 9.2, -4.0, -6.0, 5.6, 8.5, 1.4, 4.1, 5.6, 4.4}));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public ChartPanel createChart() {
        JFreeChart chart = ChartFactory.createLineChart("", "", "", createDataset());

		ChartUtils.setAntiAlias(chart);

		ChartUtils.setLineRender(chart.getCategoryPlot(), false,false);//

		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		chart.getLegend().setFrame(new BlockBorder(ColorUtils.backgroundColor()));
        ChartPanel chartPanel = new ChartPanel(chart);

        return chartPanel;
    }

}
