package presentation.view.panel.iteration2.traceBackPanel;

import org.jfree.chart.ChartPanel;
import presentation.chart.traceBack.WinRateChart;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.WindowData;
import vo.ExcessAndWinRateDistVO;

import javax.swing.*;
import java.util.List;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackWinRatePanel extends TemplatePanel {
    TraceBackWinTable traceBackWinTable;
    WinRateChart winRateChart1,winRateChart2;
    ChartPanel chartPanel1,chartPanel2;
    JLabel label;
    public TraceBackWinRatePanel(){
        setBackground(WindowData.getInstance().getColor());
        setLayout(null);

    }
    public void createChart(List<ExcessAndWinRateDistVO> certainFormates, List<ExcessAndWinRateDistVO> certainHoldings){
        if(chartPanel1!=null){
            remove(chartPanel1);
            remove(chartPanel2);
            remove(traceBackWinTable);
            remove(label);
        }
        try {
            winRateChart1=new WinRateChart(certainFormates,1);
            chartPanel1=winRateChart1.createChart();
            chartPanel1.setBounds(adaptScreen(700,30,800,400));
            chartPanel1.setVisible(true);
            add(chartPanel1);

            winRateChart2=new WinRateChart(certainFormates,2);
            chartPanel2=winRateChart2.createChart();
            chartPanel2.setBounds(adaptScreen(700,450,800,400));
            chartPanel2.setVisible(true);
            add(chartPanel2);

            traceBackWinTable=new TraceBackWinTable(certainFormates);
            traceBackWinTable.setBounds(adaptScreen(200,50,300,800));

            label = new JLabel();
            label.setBounds(200 * width / 1920, 30*(traceBackWinTable.jTable.getRowCount()+1)+50 * height / 1030, 300 * width / 1920 , 800* height / 1030);
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

