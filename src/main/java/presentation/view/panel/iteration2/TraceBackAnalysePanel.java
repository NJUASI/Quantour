package presentation.view.panel.iteration2;

import org.jfree.chart.ChartPanel;
import presentation.chart.traceBack.HistogramChart;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.WindowData;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/12.
 */
public class TraceBackAnalysePanel extends TemplatePanel {

    ChartPanel chartPanel;
    public TraceBackAnalysePanel(){

        setBackground(WindowData.getInstance().getColor());
        setLayout(null);
        chartPanel=null;
    }

    public void createChart(){
        if(chartPanel!=null){
            remove(chartPanel);
        }
        chartPanel = new HistogramChart().createChart();
        chartPanel.setBounds(adaptScreen(200, 80, 600, 400));
        chartPanel.setVisible(true);
        add(chartPanel);
    }
}
