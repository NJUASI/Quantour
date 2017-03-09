package presentation.StockSituationChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Harvey on 2017/3/9.
 */
public class StockSituationBarChart {

    Iterator<PriceRiseOrFallVO> priceItr;

    public StockSituationBarChart(Iterator<PriceRiseOrFallVO> priceItr) {
        this.priceItr = priceItr;
    }

    private CategoryDataset creatDataSet(){
        DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
        while(priceItr.hasNext()){
            PriceRiseOrFallVO vo = priceItr.next();
            defaultCategoryDataset.setValue(vo.num,LocalDate.now(),vo.name);
        }
        return  defaultCategoryDataset;
    }

    private JFreeChart createChart(){
        JFreeChart chart = ChartFactory.createBarChart("BarChart Demo", "涨跌","股数",this.creatDataSet(), PlotOrientation.VERTICAL,true,true,false);
        chart.getLegend().setItemFont(new Font("微软雅黑",Font.BOLD,12));
        CategoryPlot plot = chart.getCategoryPlot();
        //设置横轴label的字体
        plot.getDomainAxis().setLabelFont(new Font("微软雅黑",Font.BOLD,12));
        //设置纵轴label的字体
        plot.getRangeAxis().setLabelFont(new Font("微软雅黑",Font.BOLD,12));
        plot.setNoDataMessage("No data available");
        return chart;
    }

    public JPanel createPanel(){
        return new ChartPanel(createChart());
    }

    //测试
    public static void main(String[] args) {
        java.util.List<PriceRiseOrFallVO> list = new ArrayList<PriceRiseOrFallVO>();
        LocalDate date = LocalDate.now();
        list.add(new PriceRiseOrFallVO("涨停",45,date));
        list.add(new PriceRiseOrFallVO("跌停",50,date));
        list.add(new PriceRiseOrFallVO("涨幅超过5%",20,date));
        list.add(new PriceRiseOrFallVO("跌幅超过5%",10,date));
        list.add(new PriceRiseOrFallVO("剩余",600,date));

        StockSituationBarChart barChart = new StockSituationBarChart(list.iterator());

        JFrame frame = new JFrame("Pie Chart");
        frame.add(barChart.createPanel());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
