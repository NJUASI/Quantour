package presentation.view.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.view.tools.ChartUtils;
import presentation.view.tools.Serie;
import vo.StockComparisionVO;

import java.awt.*;
import java.util.Vector;

/**
 * Created by 61990 on 2017/3/16.
 */
public class CompareChart2 {
    double str1,str2;
    String name1,name2;
    public CompareChart2(java.util.List<StockComparisionVO> vo) {
        str1=vo.get(0).increaseMargin;
        str2=vo.get(1).increaseMargin;
        name1=vo.get(0).name;
        name2=vo.get(1).name;
    }

    private CategoryDataset createSet(){
        String[] categories = new String[]{"涨幅/跌幅"};
        Vector<Serie> series = new Vector<Serie>();
        Object[] datas1 = new Object[]{str1};
        Object[] datas2 = new Object[]{str2};


        series.add(new Serie(name1,datas1));
        series.add(new Serie(name2,datas2));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }


    public ChartPanel createChart() {
        // 2：创建Chart
        JFreeChart chart = ChartFactory.createBarChart("", "", "涨幅跌幅", createSet());
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染
        ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//
        // 5:对其他部分进行渲染
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(32,36,39));

        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(32,36,39));
        return chartPanel;
    }

}



