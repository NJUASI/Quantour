package presentation.view.chart;
import java.awt.Color;
import java.time.LocalDate;
import java.util.Iterator;
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
import vo.PriceRiseOrFallVO;


/**
 * Created by 61990 on 2017/3/10.
 */
public class StockSituationBarChart {
    Iterator<PriceRiseOrFallVO> priceItr;

    public StockSituationBarChart(Iterator<PriceRiseOrFallVO> priceItr) {
        this.priceItr = priceItr;
    }

    private CategoryDataset createDataset(){
        String[] categories = new String[6];
        Vector<Serie> series = new Vector<Serie>();
        Object[] datas = new Object[6];
        int index = 0;
        while (priceItr.hasNext()) {
            PriceRiseOrFallVO vo = priceItr.next();
            categories[index] = vo.name;
            datas[index] = vo.num;
            index++;
        }
        series.add(new Serie("",datas));
        DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
        return dataset;
    }


    public ChartPanel createChart() {
        // 2：创建Chart
        JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "", "Rainfall (mm)", createDataset());
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
