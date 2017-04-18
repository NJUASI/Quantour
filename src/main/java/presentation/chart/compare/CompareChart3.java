package presentation.chart.compare;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.chart.tools.CompareTool;
import presentation.chart.tools.ChartUtils;
import presentation.view.tools.ColorUtils;
import presentation.chart.tools.Serie;
import vo.StockComparisionVO;

import java.util.Vector;

/**
 * Created by 61990 on 2017/3/15.
 */
public class CompareChart3 {

    double str1, str2;

    String name1, name2;

    public CompareChart3(java.util.List<StockComparisionVO> vo) {
        str1 = vo.get(0).logarithmicYieldVariance * 10000;
        str2 = vo.get(1).logarithmicYieldVariance * 10000;
        name1 = vo.get(0).name;
        name2 = vo.get(1).name;
    }

    public ChartPanel createChart() {
        JFreeChart chart = ChartFactory.createBarChart("", "", "对数收益率方差 *10^-4", createSet());
        chart = CompareTool.setChartCategory(chart);//修饰chart

        //使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(ColorUtils.backgroundColor());
        chartPanel.setPopupMenu(null);
        return chartPanel;
    }

    private CategoryDataset createSet() {
        String[] categories = new String[]{"对数收益率方差"};
        Vector<Serie> series = new Vector<Serie>();
        Object[] datas1 = new Object[]{str1};
        Object[] datas2 = new Object[]{str2};


        series.add(new Serie(name1, datas1));
        series.add(new Serie(name2, datas2));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

}


