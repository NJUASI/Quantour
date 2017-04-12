package presentation.view.panel.iteration2;

import presentation.controller.StrategySwitchController;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.MyTabUI;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created by day on 17/3zx/28.
 */
public class AnalysePanel extends TemplatePanel implements ActionListener {
    private static AnalysePanel analysePanel;
    TraceBackChartPanel traceBackChartPanelPanel;
    TraceBackAnalysePanel traceBackAnalysePanelPanel;
    JLabel title;
    private AnalysePanel(){
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

        //对象化面板
        traceBackChartPanelPanel= new TraceBackChartPanel();
        traceBackAnalysePanelPanel= new TraceBackAnalysePanel();

        JPanel p3 = new JPanel();
        p3.setBackground(WindowData.getInstance().getColor());
        JPanel p4 = new JPanel();
        p4.setBackground(WindowData.getInstance().getColor());

        //todo 画各种各样的图！

        tab.add(traceBackChartPanelPanel,"Panel1");
        tab.add(traceBackAnalysePanelPanel,"Panel2");
        tab.add(p3,"Panel3");
        tab.add(p4,"Panel4");
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

        JButton back=new JButton("返回");
        back.setBounds(adaptScreen(10,10,60,25));
        back.setFont(new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,16*WindowData.getInstance().getWidth()/1920));
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

    public static AnalysePanel getInstance(){
        if(analysePanel==null){
            analysePanel=new AnalysePanel();
        }
        return analysePanel;
    }

    public void createChart(){
        traceBackAnalysePanelPanel.createChart();
        traceBackChartPanelPanel.createChart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if(file.isDirectory()){
            System.out.println("文件夹:"+file.getAbsolutePath());
        }else if(file.isFile()){
            System.out.println("文件:"+file.getAbsolutePath());
        }
        System.out.println(jfc.getSelectedFile().getName());
    }
}
