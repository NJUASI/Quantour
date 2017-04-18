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

import java.util.*;

/**
 * Created by 61990 on 2017/4/11.
 */
public class HistogramChart {
    ReturnPeriodVO returnPeriodVO;

    public HistogramChart(ReturnPeriodVO returnPeriodVO) {
        this.returnPeriodVO = returnPeriodVO;
    }

    public DefaultCategoryDataset createDataset() {
        // 标注类别

        Vector<Serie> series = new Vector<Serie>();

        String[] categories = {"1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", ">12%"};
        Object[] datas1 = new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Object[] datas2 = new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        this.setData(returnPeriodVO.positiveNums,datas1);
        this.setData(returnPeriodVO.negativeNums,datas2);

        series.add(new Serie("正收益次数", datas1));
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
        chartPanel.setPopupMenu(null);
        return chartPanel;
    }

    private void setData(Map<Double,Integer> map,Object[]data){
        for(Double key:map.keySet()){
            int index = key.intValue()-1;
            if(index>=11){
                int sum = (int)data[11];
                sum = sum+map.get(key);
                data[11] = sum;
            } else{
                data[index] = map.get(key);
            }
        }
    }
}
