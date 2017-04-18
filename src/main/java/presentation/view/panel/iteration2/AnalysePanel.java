package presentation.view.panel.iteration2;

import presentation.controller.StrategySwitchController;
import presentation.view.panel.TemplatePanel;
import presentation.view.panel.iteration2.traceBackPanel.TraceBackAnalysePanel;
import presentation.view.panel.iteration2.traceBackPanel.TraceBackChartPanel;
import presentation.view.panel.iteration2.traceBackPanel.TraceBackDetail;
import presentation.view.panel.iteration2.traceBackPanel.TraceBackWinRatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.ui.MyTabUI;
import presentation.view.tools.WindowData;
import vo.TraceBackVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by day on 17/3zx/28.
 */
public class AnalysePanel extends TemplatePanel {
    private static AnalysePanel analysePanel;
    TraceBackChartPanel traceBackChartPanelPanel;
    TraceBackAnalysePanel traceBackAnalysePanelPanel;
    TraceBackDetail traceBackDetail;
    TraceBackWinRatePanel traceBackWinRatePanel;
    JPanel detailOfTraceBack;
    JLabel title,label;
    int width,height;
    private AnalysePanel(){
        width=WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

        //对象化面板
        traceBackChartPanelPanel= new TraceBackChartPanel();
        traceBackAnalysePanelPanel= new TraceBackAnalysePanel();

        detailOfTraceBack = new JPanel();
        detailOfTraceBack.setLayout(null);
        detailOfTraceBack.setBackground(WindowData.getInstance().getColor());


        traceBackWinRatePanel=new TraceBackWinRatePanel();



        tab.add(traceBackChartPanelPanel,"收益曲线");
        tab.add(traceBackAnalysePanelPanel,"收益周期统计");
        tab.add(detailOfTraceBack,"交易详情");
        tab.add(traceBackWinRatePanel,"赢率分布");

        tab.setBounds(adaptScreen(-2,60,1833,930));
        setLayout(null);
        add(tab);
        tab.setBackground(WindowData.getInstance().getColor());
        tab.setForeground(ColorUtils.fontColor());
        tab.setOpaque(true);
        tab.setUI(new MyTabUI());
        tab.setBorder(null);
        add(tab);
        setBorder(BorderFactory.createEmptyBorder());

        JButton back=new MyButton("返回");
        back.setBounds(adaptScreen(10,10,60,25));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                StrategySwitchController.getInstance().viewSwitch("chooseStrategyPanel");
            }
        });
        add(back);

        title=new JLabel("动量策略");
        title.setForeground(ColorUtils.fontColor());
        title.setFont(new Font("微软雅黑" ,Font.CENTER_BASELINE,17*WindowData.getInstance().getWidth()/1920));
        title.setBounds(adaptScreen(850,3,120,35));
        add(title);
    }

    public void setTitle(String str){
        title.setText(str);
    }

    public static AnalysePanel getInstance(){
        if(analysePanel==null){
            analysePanel=new AnalysePanel();
        }
        return analysePanel;
    }

    public void createChart(TraceBackVO traceBackVO){
        traceBackAnalysePanelPanel.createChart(traceBackVO.absoluteReturnPeriodVO,traceBackVO.relativeReturnPeriodVO);
        traceBackChartPanelPanel.createChart(traceBackVO.traceBackNumValVO,traceBackVO.baseCumulativeReturn,traceBackVO.strategyCumulativeReturn,traceBackVO.maxTraceBackVO);
        traceBackWinRatePanel.createChart(traceBackVO.certainFormates,traceBackVO.certainHoldings);

        if(traceBackDetail!=null){
            detailOfTraceBack.remove(traceBackDetail);
            detailOfTraceBack.remove(label);
        }

        traceBackDetail=new TraceBackDetail(traceBackVO.holdingDetailVOS);
        traceBackDetail.setBounds(adaptScreen(200,50,1400,800));

        label = new JLabel("");
        label.setBounds(200 * width / 1920, 30*(traceBackDetail.jTable.getRowCount()+1)+50 * height / 1030, 1400 * width / 1920 , 900* height / 1030);
        label.setBorder(BorderFactory.createEmptyBorder());
        label.setBackground(ColorUtils.backgroundColor());
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        detailOfTraceBack.add(label);

        detailOfTraceBack.add(traceBackDetail);
    }
}
