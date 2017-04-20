package presentation.view.panel.iteration2.traceBackPanel;


import org.jfree.chart.ChartPanel;
import presentation.chart.traceBack.TraceBackChart;
import presentation.listener.chartMouseListener.TraceBackListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.WindowData;
import vo.CumulativeReturnVO;
import vo.MaxTraceBackVO;
import vo.TraceBackNumValVO;

import java.util.List;

/**
 * Created by 61990 on 2017/4/12.
 */
public class TraceBackChartPanel extends TemplatePanel {
    ChartPanel chartPanel;
    TraceBackNumVal traceBackNumVal;

    public TraceBackChartPanel() {
        setBackground(WindowData.getInstance().getColor());
        setLayout(null);
        traceBackNumVal = null;
    }

    public void createChart(TraceBackNumValVO traceBackNumValVO, List<CumulativeReturnVO> baseCumulativeReturn,
                            List<CumulativeReturnVO> strategyCumulativeReturn, MaxTraceBackVO maxTraceBackVO) throws IllegalArgumentException{
        if (chartPanel != null) {
            remove(chartPanel);
            remove(traceBackNumVal);
        }

        chartPanel = new ChartPanel(new TraceBackChart(strategyCumulativeReturn, baseCumulativeReturn,maxTraceBackVO).createTracebackChart());
        chartPanel.setBounds(adaptScreen(100, 210, 1500, 600));
        chartPanel.setVisible(true);
        chartPanel.setPopupMenu(null);
        chartPanel.setMouseZoomable(false);
        chartPanel.addChartMouseListener(new TraceBackListener(chartPanel));
        add(chartPanel);

        traceBackNumVal = new TraceBackNumVal(traceBackNumValVO,maxTraceBackVO);
        traceBackNumVal.setBounds(adaptScreen(200, 40, 1400, 120*1030/height));
        add(traceBackNumVal);

    }
}
