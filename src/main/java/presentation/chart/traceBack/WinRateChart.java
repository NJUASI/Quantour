package presentation.chart.traceBack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import presentation.chart.tools.ChartUtils;
import presentation.chart.tools.Serie;
import presentation.view.tools.ColorUtils;
import vo.ExcessAndWinRateDistVO;

import java.util.List;
import java.util.Vector;

/**
 * Created by 61990 on 2017/4/16.
 */
public class WinRateChart {
    List<ExcessAndWinRateDistVO> certainFormates;
    int type;
    public WinRateChart(List<ExcessAndWinRateDistVO> certainFormates, int type) {
        this.certainFormates=certainFormates;
        this.type=type;
    }

    public DefaultCategoryDataset createDataset() {
        // 标注类别

//        String[] categories = new String[certainFormates.size()];
//        Double rates[] = new Double[certainFormates.size()];
//        Vector<Serie> series = new Vector<Serie>();
//
//        for(int i = 0 ; i < certainFormates.size() ; i++) {
//            categories[i]=certainFormates.get(i).relativeCycle+"";
//            if(type==1) {
//                rates[i] = certainFormates.get(i).excessRate;
//            }else{
//                rates[i] = certainFormates.get(i).winRate;
//            }
//        }

        String[] categories = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
        Vector<Serie> series = new Vector<Serie>();
        series.add(new Serie("", new Double[] { 1.0, 1.5, 6.4, 9.2, -4.0, -6.0, 5.6, 8.5, 1.4, 4.1, 5.6, 4.4}));


        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }

    public ChartPanel createChart() {
        JFreeChart chart = ChartFactory.createAreaChart("", "", "%", createDataset());

		ChartUtils.setAntiAlias(chart);

		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		chart.getLegend().setFrame(new BlockBorder(ColorUtils.backgroundColor()));
		setChart(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPopupMenu(null);

        return chartPanel;
    }

    private void setChart(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.9f);
        plot.getRenderer().setSeriesPaint(0,ColorUtils.linkColor());
        chart.getLegend().setVisible(false);

    }

}
