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
import java.util.List;
import java.util.Vector;

/**
 * Created by 61990 on 2017/3/14.
 */
public class CompareChart1 {

    List<StockComparisionVO> vo;

    public CompareChart1(List<StockComparisionVO> vo) {
        this.vo = vo;
    }

    public ChartPanel createChart() {

        JFreeChart chart = ChartFactory.createBarChart("", "", "成交价", createDataset());
        chart = CompareTool.setChartCategory(chart);//修饰chart

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(ColorUtils.backgroundColor());

        return chartPanel;
    }

    private CategoryDataset createDataset() {
        String[] categories = new String[]{"最低值", "最高值"};
        Vector<Serie> series = new Vector<Serie>();

        Object[] datas1 = new Object[]{vo.get(0).min, vo.get(0).max};
        Object[] datas2 = new Object[]{vo.get(1).min, vo.get(1).max};

        //TODO 获得两股股票的名称
        series.add(new Serie(vo.get(0).name, datas1));
        series.add(new Serie(vo.get(1).name, datas2));

        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

}
