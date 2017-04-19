package presentation.view.panel.iteration2.traceBackPanel;

import org.jfree.chart.ChartPanel;
import presentation.chart.traceBack.WinRateChart;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyLabel;
import vo.ExcessAndWinRateDistVO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by 61990 on 2017/4/16.
 */
public class TraceBackWinRatePanel extends TemplatePanel {
    TraceBackWinTable traceBackWinTable,traceBackWinTable2;
    WinRateChart winRateChart1,winRateChart2;
    ChartPanel chartPanel1,chartPanel2;
    JLabel label;
    WinRateChart winRateChart3,winRateChart4;
    ChartPanel chartPanel3,chartPanel4;
    JLabel label3;
    JRadioButton radioButton1,radioButton2;
    ButtonGroup group;
    public TraceBackWinRatePanel(){
        setBackground(WindowData.getInstance().getColor());
        setLayout(null);

        radioButton1 = new JRadioButton("固定形成期");// 创建单选按钮
        radioButton1.setBounds(adaptScreen(20,20,150,35));
        add(radioButton1);
        radioButton1.setBackground(WindowData.getInstance().getColor());
        radioButton1.setForeground(Color.WHITE);
        radioButton1.setSelected(true);
        radioButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openGroup1();
            }
        });

        radioButton2 = new JRadioButton("固定持有期");// 创建单选按钮
        radioButton2.setBounds(adaptScreen(170,20,150,35));
        radioButton2.setBackground(WindowData.getInstance().getColor());
        radioButton2.setForeground(Color.WHITE);
        radioButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openGroup2();
            }
        });
        add(radioButton2);// 策略2按钮

        group = new ButtonGroup();// 创建单选按钮组
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中

        MyLabel label1=new MyLabel("超额收益",20) ;
        label1.setLocation(700*width/1920,40*height/1030);
        add(label1);
        MyLabel label2=new MyLabel("赢率",20) ;
        label2.setLocation(700*width/1920,450*height/1030);
        add(label2);


    }
    public void createChart(List<ExcessAndWinRateDistVO> certainFormates, List<ExcessAndWinRateDistVO> certainHoldings){
        if(chartPanel1!=null){
            remove(chartPanel1);
            remove(chartPanel2);
            remove(traceBackWinTable);
            remove(label);
            remove(chartPanel3);
            remove(chartPanel4);
            remove(traceBackWinTable2);
        }
        try {
            winRateChart1=new WinRateChart(certainFormates,1);
            chartPanel1=winRateChart1.createChart();
            chartPanel1.setBounds(adaptScreen(700,80,800,350));
            chartPanel1.setVisible(true);
            add(chartPanel1);

            winRateChart2=new WinRateChart(certainFormates,2);
            chartPanel2=winRateChart2.createChart();
            chartPanel2.setBounds(adaptScreen(700,490,800,350));
            chartPanel2.setVisible(true);
            add(chartPanel2);

            traceBackWinTable=new TraceBackWinTable(certainFormates);
            traceBackWinTable.setBounds(adaptScreen(250,120,300,800));

            label = new JLabel();
            label.setBounds(250 * width / 1920, 30*(traceBackWinTable.jTable.getRowCount()+1)+120 * height / 1030, 300 * width / 1920 , 800* height / 1030);
            label.setBorder(BorderFactory.createEmptyBorder());
            label.setBackground(ColorUtils.backgroundColor());
            label.setOpaque(true);
            add(label);

            add(traceBackWinTable);


            winRateChart3=new WinRateChart(certainHoldings,1);
            chartPanel3=winRateChart3.createChart();
            chartPanel3.setBounds(adaptScreen(700,80,800,350));
            chartPanel3.setVisible(true);
            add(chartPanel3);

            winRateChart4=new WinRateChart(certainHoldings,2);
            chartPanel4=winRateChart4.createChart();
            chartPanel4.setBounds(adaptScreen(700,490,800,350));
            chartPanel4.setVisible(true);
            add(chartPanel4);

            traceBackWinTable2=new TraceBackWinTable(certainHoldings);
            traceBackWinTable2.setBounds(adaptScreen(250,120,300,600));
            add(traceBackWinTable2);

            label3 = new JLabel();
            label3.setBounds(250 * width / 1920, 30*(traceBackWinTable2.jTable.getRowCount()+1)+120 * height / 1030, 300 * width / 1920 , 600* height / 1030);
            label3.setBorder(BorderFactory.createEmptyBorder());
            label3.setBackground(ColorUtils.backgroundColor());
            label3.setOpaque(true);
            add(label3);

            openGroup1();
            radioButton1.setSelected(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void openGroup1(){
        traceBackWinTable.setVisible(true);
        traceBackWinTable2.setVisible(false);
        chartPanel1.setVisible(true);
        chartPanel2.setVisible(true);
        label.setVisible(true);
        chartPanel3.setVisible(false);
        chartPanel4.setVisible(false);
        label3.setVisible(false);
    }
    private void openGroup2(){
        traceBackWinTable2.setVisible(true);
        traceBackWinTable.setVisible(false);
        chartPanel1.setVisible(false);
        chartPanel2.setVisible(false);
        label.setVisible(false);
        chartPanel3.setVisible(true);
        chartPanel4.setVisible(true);
        label3.setVisible(true);
    }
}

