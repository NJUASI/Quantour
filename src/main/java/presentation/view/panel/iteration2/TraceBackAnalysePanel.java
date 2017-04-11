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
    public TraceBackAnalysePanel(){

        setBackground(WindowData.getInstance().getColor());
        setLayout(null);

        ChartPanel chartPanel2 = new HistogramChart().createChart();
        chartPanel2.setBounds(adaptScreen(200, 80, 600, 600));
        chartPanel2.setVisible(true);
        add(chartPanel2);


    }
}
