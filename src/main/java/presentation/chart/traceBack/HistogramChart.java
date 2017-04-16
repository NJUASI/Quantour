package presentation.chart.traceBack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.chart.tools.ChartUtils;
import presentation.chart.tools.CompareTool;
import presentation.chart.tools.Serie;
import presentation.view.tools.ColorUtils;
import vo.ReturnPeriodVO;

import java.util.Vector;

/**
 * Created by 61990 on 2017/4/11.
 */
public class HistogramChart {
    ReturnPeriodVO returnPeriodVO;
    public  HistogramChart(ReturnPeriodVO returnPeriodVO){
        this.returnPeriodVO=returnPeriodVO;
    }
    public DefaultCategoryDataset createDataset() {
        // 标注类别

        Vector<Serie> series = new Vector<Serie>();
        // 柱子名称：柱子所有的值集合

//        ChooseStrategyPanel.getInstance().getInfo()
//todo 更改数据
        String[] categories = { "1%", "2%", "3%", "4%","5%", "6%", "7%", "8%", "9%", "10%", "11%", ">12%" };
        Object[]  datas1= new Object[]{11,11,10,9,8,7,6,5,4,3,2,1};
        Object[] datas2 = new Object[]{11,11,10,9,8,7,6,5,4,3,2,1};


        series.add(new Serie("正收益次数",datas1));
        series.add(new Serie("负收益次数", datas2));


        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public ChartPanel createChart() {
        // 创建Chart
        JFreeChart chart = ChartFactory.createBarChart("", "", "", createDataset());
        chart = CompareTool.setChartCategory(chart);//修饰chart

        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setBackground(ColorUtils.backgroundColor());
        return chartPanel;
    }
}
