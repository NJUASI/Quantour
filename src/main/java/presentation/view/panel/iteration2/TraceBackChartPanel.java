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
    TraceBackNumVal traceBackNumVal;
    public TraceBackChartPanel(){
        setBackground(WindowData.getInstance().getColor());
        setLayout(null);
        traceBackNumVal=null;
    }

    public void createChart(){
        if(chartPanel!=null){
            remove(chartPanel);
            remove(traceBackNumVal);
        }
        try {
            chartPanel = new ChartPanel(new TraceBackChart(ChooseStrategyPanel.getInstance().getInfo()).createTracebackChart());
            chartPanel.setBounds(adaptScreen(100, 210, 1500, 600));
            chartPanel.setVisible(true);
            add(chartPanel);

            traceBackNumVal=new TraceBackNumVal();
            traceBackNumVal.setBounds(adaptScreen(200,50,1400,120));
            add(traceBackNumVal);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
