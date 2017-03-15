package presentation.view.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.view.util.ChartUtils;
import presentation.view.util.Serie;
import vo.StockComparisionVO;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by 61990 on 2017/3/15.
 */
public class CompareChart4 {
    List<StockComparisionVO> vo;

    public CompareChart4(List<StockComparisionVO> vo) {
        this.vo = vo;
    }

    private CategoryDataset createDataset(){
        String[] categories = new String[]{"最低值","最高值"};
        Vector<Serie> series = new Vector<Serie>();
        Object[] datas1 = new Object[]{};
        Object[] datas2 = new Object[]{};
        //TODO 获得两股股票的名称
        series.add(new Serie("1",datas1));
        series.add(new Serie("2",datas2));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }


    public ChartPanel createChart() {
        // 2：创建Chart
        JFreeChart chart = ChartFactory.createBarChart("", "", "成交价", createDataset());
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染
        ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//
        // 5:对其他部分进行渲染
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

}
