package presentation.view.panel.iteration2;

import presentation.controller.StrategySwitchController;
import presentation.listener.strategyPanelListener.SearchListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.*;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.PopupProgress;
import presentation.view.tools.component.datePicker.DoubleDatePickerPanel;
import presentation.view.tools.component.MyLabel;
import utilities.enums.StType;
import utilities.enums.TraceBackStrategy;
import vo.StockPoolCriteriaVO;
import vo.TraceBackCriteriaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by day on 17/3/28.
 */


public class ChooseStrategyPanel extends TemplatePanel {
    private static ChooseStrategyPanel chooseStrategyPanel;
    JRadioButton radioButton1,radioButton2;
    ButtonGroup group;

    DoubleDatePickerPanel datePanel;
    StrategyTypePanel strategyTypePanel;
    public StrategyPoolPanel strategyPoolPanel;
    JComboBox comboBox;
    JLabel progressBar,message;
     public LoadingPanel loadingPanel;
     PopupProgress popupProgress;

    int width;
    int height;
    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/28
     */
    private ChooseStrategyPanel(){

        width= WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        setLayout(null);
        setBounds(adaptScreen(0,0,1830,990));

        datePanel = new DoubleDatePickerPanel();
        datePanel.setBounds(adaptScreen(200 , 370 , 520 , 37));
        add(datePanel);
        //股票池区域
        strategyPoolPanel=new StrategyPoolPanel();
        strategyPoolPanel.setBounds(adaptScreen(0,0,1600,280));
        add(strategyPoolPanel);

        //区间板块
        MyLabel label4=new MyLabel("回测区间",20) ;
        label4.setLocation(100*width/1920,300*height/1030);
        add(label4);


        JLabel lb1= new MyLabel("收益基准");
        lb1.setBounds(adaptScreen(760,368,80,40));
        add(lb1);

        comboBox=new JComboBox();
        comboBox.setBounds(adaptScreen(850,370,200,35));
        comboBox.addItem("沪深300");
        comboBox.addItem("创业板指");
        comboBox.addItem("中小板指");
        comboBox.setEditable(false);
        comboBox.setToolTipText((String)comboBox.getItemAt(0));
        add(comboBox);
        //选择策略
        MyLabel label3=new MyLabel("选择策略",20) ;
        label3.setLocation(100*width/1920,500*height/1030);
        add(label3);

        radioButton1 = new JRadioButton("动量策略");// 创建单选按钮
        radioButton1.setBounds(adaptScreen(250,550,150,40));
        add(radioButton1);// 策略1按钮
        radioButton1.setBackground(WindowData.getInstance().getColor());
        radioButton1.setForeground(Color.WHITE);
        radioButton1.setSelected(true);
        radioButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                strategyTypePanel.openType1();
            }
        });

        radioButton2 = new JRadioButton("均值回归");// 创建单选按钮
        radioButton2.setBounds(adaptScreen(250,590,150,40));
        radioButton2.setBackground(WindowData.getInstance().getColor());
        radioButton2.setForeground(Color.WHITE);
        radioButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                strategyTypePanel.openType2();
            }
        });
        add(radioButton2);// 策略2按钮

        group = new ButtonGroup();// 创建单选按钮组
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中

        strategyTypePanel=new StrategyTypePanel();
        add(strategyTypePanel);


        JButton searchBt= new MyButton("开始回测");
        searchBt.setBounds(adaptScreen(1200,550,100,35));

        searchBt.addMouseListener(new SearchListener());
        searchBt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StrategySwitchController.getInstance().viewSwitch("analysePanel");
            }
        });

        add(searchBt);

        JButton returnBt= new MyButton("查看上次");
        returnBt.setBounds(adaptScreen(1200,500,100,35));
        returnBt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StrategySwitchController.getInstance().viewSwitch("analysePanel");
            }
        });


        add(returnBt);

    }

    public void popup(){
        progressBar = new JLabel();
        progressBar.setBounds(1100*width/1920, 420*height/1030, 305, 42);
        add(progressBar);
        message = new JLabel();
        message.setBounds(1200*width/1920, 350*height/1030, 305, 42);
        add(message);
        popupProgress=new PopupProgress(progressBar,message);
        popupProgress.start();
    }
    public void popdown(){
        popupProgress.stop();
        remove(progressBar);
        remove(message);
    }
    /**
     * 单件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/28
     */
    public static ChooseStrategyPanel getInstance(){
        if(chooseStrategyPanel==null){
            chooseStrategyPanel=new ChooseStrategyPanel();
        }
        return chooseStrategyPanel;
    }

    public String getStrategyType(){
        if(radioButton1.isSelected()){
            return "动量策略";
        }else{
            return "均值回归";
        }

    }

   public void refreshTabel(){
        strategyPoolPanel.refreshTabel();
    }
    /**
     * get ALL panel message
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/4/9
     */
    public TraceBackCriteriaVO getInfo(){
        int formative;
        TraceBackStrategy traceBackStrategy=null;
        if(radioButton1.isSelected()){
            formative=strategyTypePanel.getMS();
            traceBackStrategy=TraceBackStrategy.MS;
        }else{
            formative=strategyTypePanel.getMR();
            traceBackStrategy=TraceBackStrategy.MR;
        }

        return new TraceBackCriteriaVO(datePanel.getStartDate(),datePanel.getEndDate(),formative,strategyTypePanel.getHoldingPeriod(),
               strategyPoolPanel.getPoolVO(),traceBackStrategy,strategyTypePanel.getHoldingNum(),getBasicStock());
    }

    private String getBasicStock(){
        return comboBox.getSelectedItem().toString();
    }

}
