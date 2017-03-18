package presentation.view.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.view.util.ChartUtils;
import presentation.view.util.Serie;
import vo.PriceRiseOrFallVO;

import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by cuihua on 2017/3/18.
 *
 * 涨跌幅超过5%
 */
public class StockSituationBar5PercentChart {

    List<PriceRiseOrFallVO> priceList;

    public StockSituationBar5PercentChart(List<PriceRiseOrFallVO> priceList) {
        this.priceList = priceList;
    }

    private CategoryDataset createDataset(){
        String[] categories = new String[2];
        Vector<Serie> series = new Vector<Serie>();
        Object[] datas = new Object[2];
        for (int i = 2; i < 4; i++) {
            PriceRiseOrFallVO vo = priceList.get(i);
            categories[i-2] = vo.name;
            datas[i-2] = vo.num;
        }
        series.add(new Serie("",datas));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }


    public ChartPanel createChart() {
        // 2：创建Chart
        JFreeChart chart = ChartFactory.createBarChart("涨幅超过5% VS 跌幅超过5%", "", "股票数", createDataset());
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染
        ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//
        // 5:对其他部分进行渲染
        ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
        ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        chart.getLegend().setVisible(false);
        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }


}
