package presentation.view.panel.iteration2;



import org.jfree.chart.ChartPanel;
import presentation.chart.traceBack.HistogramChart;
import presentation.chart.traceBack.TraceBackChart;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.WindowData;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/12.
 */
public class TraceBackChartPanel extends TemplatePanel {
    ChartPanel chartPanel;
    public TraceBackChartPanel(){
        setBackground(WindowData.getInstance().getColor());
        setLayout(null);
        ChartPanel chartPanel=null;
    }

    public void createChart(){
        if(chartPanel!=null){
            remove(chartPanel);
        }
        try {
            chartPanel = new ChartPanel(new TraceBackChart(ChooseStrategyPanel.getInstance().getInfo()).createTracebackChart());
        }catch (Exception e){

        }
        chartPanel.setBounds(adaptScreen(200, 80, 1400, 600));
        chartPanel.setVisible(true);
        add(chartPanel);
    }
}
