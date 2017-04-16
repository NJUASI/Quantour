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

import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by 61990 on 2017/4/11.
 */
public class HistogramChart {
    ReturnPeriodVO returnPeriodVO;
    public  HistogramChart(ReturnPeriodVO returnPeriodVO){
//        this.returnPeriodVO=returnPeriodVO;
    }
    public DefaultCategoryDataset createDataset() {
        // 标注类别

        Vector<Serie> series = new Vector<Serie>();

//        Iterator iterator1 =  returnPeriodVO.positiveNums.entrySet().iterator();
//        Iterator iterator2 =  returnPeriodVO.negativeNums.entrySet().iterator();

        String[] categories = { "1%", "2%", "3%", "4%","5%", "6%", "7%", "8%", "9%", "10%", "11%", ">12%" };
        Object[]  datas1= new Object[]{1,1,1,1,1,0,0,0,0,0,0,0};
        Object[] datas2 = new Object[]{0,0,0,0,2,2,2,0,0,0,0,0};
//        while(iterator1.hasNext()){
//            Object key = iterator1.next();
//            int num=Integer.parseInt(key.toString());
//            datas1[num-1]=returnPeriodVO.positiveNums.get(key);
//        }
//        while(iterator2.hasNext()){
//            Object key = iterator2.next();
//            int num=Integer.parseInt(key.toString());
//            datas2[num-1]=returnPeriodVO.negativeNums.get(key);
//        }



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
