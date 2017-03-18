package presentation.view.chart;
import java.awt.Color;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import presentation.view.util.ChartUtils;
import presentation.view.util.Serie;
import utilities.enums.PriceRiseOrFall;
import vo.PriceRiseOrFallVO;


/**
 * Created by 61990 on 2017/3/10.
 *
 * 涨跌停
 */
public class StockSituationBarLimitChart {

    List<PriceRiseOrFallVO> priceList;

    public StockSituationBarLimitChart(List<PriceRiseOrFallVO> priceList) {
        this.priceList = priceList;
    }

    private CategoryDataset createDataset(){
        String[] categories = new String[2];
        Vector<Serie> series = new Vector<Serie>();
        Object[] datas = new Object[2];
        for (int i = 0; i < 2; i++) {
            PriceRiseOrFallVO vo = priceList.get(i);
            categories[i] = vo.name;
            datas[i] = vo.num;
        }
        series.add(new Serie("",datas));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }


    public ChartPanel createChart() {
        // 2：创建Chart
        JFreeChart chart = ChartFactory.createBarChart("涨停 VS 跌停", "", "股票数", createDataset());
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
