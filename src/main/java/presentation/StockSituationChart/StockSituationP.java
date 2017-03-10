package presentation.StockSituationChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import vo.PriceRiseOrFallVO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Harvey on 2017/3/9.
 */
public class StockSituationP {

    Iterator<PriceRiseOrFallVO> priceItr;

    public StockSituationP(Iterator<PriceRiseOrFallVO> priceItr) {
        this.priceItr = priceItr;
    }


    //创建饼状图数据集
    private PieDataset createDataSet(){
        DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
        while(priceItr.hasNext()){
            PriceRiseOrFallVO vo = priceItr.next();
            defaultPieDataset.setValue(vo.name,vo.num);
        }
        return  defaultPieDataset;
    }

    //用数据集创建一个饼图
    private JFreeChart createChart(){
        JFreeChart jFreeChart = ChartFactory.createPieChart("Pie KChart Demo",this.createDataSet(),true,true,false);
        PiePlot plot = (PiePlot)jFreeChart.getPlot();
        plot.setNoDataMessage("No data available");
        plot.setLabelFont(new Font("微软雅黑",Font.BOLD,12));
        jFreeChart.getLegend().setItemFont(new Font("微软雅黑",Font.BOLD,12));
        return jFreeChart;
    }

    //返回一个画了饼状图的面板
    public JPanel creatPanel(){
        return new ChartPanel(createChart());
    }



    //测试
    public static void main(String[] args) {
        List<PriceRiseOrFallVO> list = new ArrayList<PriceRiseOrFallVO>();
        LocalDate date = LocalDate.now();
        list.add(new PriceRiseOrFallVO("涨停",45,date));
        list.add(new PriceRiseOrFallVO("跌停",50,date));
        list.add(new PriceRiseOrFallVO("涨幅超过5%",20,date));
        list.add(new PriceRiseOrFallVO("跌幅超过5%",10,date));
        list.add(new PriceRiseOrFallVO("剩余",600,date));


        StockSituationP pieChart = new StockSituationP(list.iterator());

        JFrame frame = new JFrame("Pie KChart");
        frame.add(pieChart.creatPanel());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
