package presentation.view.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import presentation.view.util.ChartUtils;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Objects;

/**
 * Created by 61990 on 2017/3/10.
 */
public class StockSituationPieChart {

    Iterator<PriceRiseOrFallVO> priceItr;

    public StockSituationPieChart(Iterator<PriceRiseOrFallVO> priceItr) {
        this.priceItr = priceItr;
    }

    public StockSituationPieChart() {

    }


    private DefaultPieDataset createDataset() {
        DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
        String[] categories = new String[6];
        Object[] datas = new Object[6];
        int index = 0;
        while (priceItr.hasNext()) {
            PriceRiseOrFallVO vo = priceItr.next();
            categories[index] = vo.name;
            datas[index] = vo.num;
            index++;
        }
        DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);
        return dataset;
    }


    public ChartPanel createChart() {
        // 2：创建Chart[创建不同图形]
        JFreeChart chart = ChartFactory.createPieChart("Pie KChart Demo", createDataset());
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[创建不同图形]
        ChartUtils.setPieRender(chart.getPlot());
        /**
         * 可以注释测试
         */
        // plot.setSimpleLabels(true);//简单标签,不绘制线条
        // plot.setLabelGenerator(null);//不显示数字
        // 设置标注无边框
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        // 标注位于右侧
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

}
