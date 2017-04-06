package presentation.chart.compare;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.chart.tools.CompareTool;
import presentation.view.tools.ChartUtils;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.Serie;
import vo.StockComparisionVO;

import java.awt.*;
import java.util.Vector;

/**
 * Created by 61990 on 2017/3/16.
 */
public class CompareChart2 {

    double str1, str2;
    String name1, name2;

    public CompareChart2(java.util.List<StockComparisionVO> vo) {
        str1 = vo.get(0).increaseMargin;
        str2 = vo.get(1).increaseMargin;
        name1 = vo.get(0).name;
        name2 = vo.get(1).name;
    }

    public ChartPanel createChart() {
        JFreeChart chart = ChartFactory.createBarChart("", "", "涨幅跌幅", createSet());
        chart = CompareTool.setChartCategory(chart);//修饰chart

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(ColorUtils.backgroundColor());
        return chartPanel;
    }

    private CategoryDataset createSet() {
        String[] categories = new String[]{"涨幅/跌幅"};
        Vector<Serie> series = new Vector<Serie>();

        Object[] datas1 = new Object[]{str1};
        Object[] datas2 = new Object[]{str2};


        series.add(new Serie(name1, datas1));
        series.add(new Serie(name2, datas2));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

}



