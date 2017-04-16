package presentation.view.panel.iteration2;

import org.jfree.chart.ChartPanel;
import presentation.chart.candlestick.LineChart;
import presentation.chart.traceBack.TraceBackChart;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackWinRatePanel extends TemplatePanel {
    TraceBackWinTable traceBackWinTable;
    LineChart lineChart1,lineChart2;
    ChartPanel chartPanel1,chartPanel2;
    JLabel label;
    public TraceBackWinRatePanel(){
        setBackground(WindowData.getInstance().getColor());
        setLayout(null);

    }
    public void createChart(){
        if(chartPanel1!=null){
            remove(chartPanel1);
            remove(chartPanel2);
            remove(traceBackWinTable);
            remove(label);
        }
        try {
            lineChart1=new LineChart();
            chartPanel1=lineChart1.createChart();
            chartPanel1.setBounds(adaptScreen(700,30,800,400));
            chartPanel1.setVisible(true);
            add(chartPanel1);

            lineChart2=new LineChart();
            chartPanel2=lineChart2.createChart();
            chartPanel2.setBounds(adaptScreen(700,450,800,400));
            chartPanel2.setVisible(true);
            add(chartPanel2);

            traceBackWinTable=new TraceBackWinTable();
            traceBackWinTable.setBounds(adaptScreen(200,50,300,800));

            label = new JLabel();
            label.setBounds(200 * width / 1920, (50+30*(traceBackWinTable.jTable.getRowCount()+1)) * height / 1030, 300 * width / 1920 , 800* height / 1030);
            label.setBorder(BorderFactory.createEmptyBorder());
            label.setBackground(ColorUtils.backgroundColor());
            label.setOpaque(true);
            add(label);

            add(traceBackWinTable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

