package presentation.view.panel.iteration2;

import presentation.controller.StrategySwitchController;
import presentation.listener.strategyPanelListener.SearchListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.datePicker.DoubleDatePickerPanel;
import presentation.view.tools.component.MyLabel;
import presentation.view.tools.WindowData;
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
    MultiComboBox mulit;
    DoubleDatePickerPanel datePanel;
    StrategyTypePanel strategyTypePanel;
    JComboBox comboBox,STComboBox;
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
        MyLabel label1=new MyLabel("选股票池") ;
        label1.setLocation(100*width/1920,100*height/1030);
        add(label1);

        JLabel lb= new JLabel("板块");
        lb.setForeground(Color.white);
        lb.setFont(new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        lb.setBounds(adaptScreen(200,168,60,40));
        add(lb);


        Object[] value = new String[]{"全部", "主板","中小板" , "创业板" };
        Object[] defaultValue = new String[]{ "主板","中小板" , "创业板" };
        mulit = new MultiComboBox(value, defaultValue);
        mulit.setBounds(adaptScreen(270,170,230,35));
//        System.out.println(mulit.getSelectedValues()[0]);
        add(mulit);

        JLabel lb3= new JLabel("ST");
        lb3.setForeground(Color.white);
        lb3.setFont(new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        lb3.setBounds(adaptScreen(570,168,20,40));
        add(lb3);

        STComboBox=new JComboBox();
        STComboBox.setBounds(adaptScreen(620,170,140,35));
        STComboBox.addItem("包含ST");
        STComboBox.addItem("排除ST");
        STComboBox.addItem("仅有ST");
        STComboBox.setEditable(false);
        STComboBox.setToolTipText((String)STComboBox.getItemAt(0));
        add(STComboBox);

        //区间板块
        MyLabel label4=new MyLabel("回测区间") ;
        label4.setLocation(100*width/1920,300*height/1030);
        add(label4);


        JLabel lb1= new JLabel("收益基准");
        lb1.setForeground(Color.white);
        lb1.setFont(new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        lb1.setBounds(adaptScreen(760,368,80,40));
        add(lb1);

        comboBox=new JComboBox();
        comboBox.setBounds(adaptScreen(850,370,200,35));
        comboBox.addItem("沪深300");
        comboBox.addItem("上证50");
        comboBox.addItem("中证500");
        comboBox.addItem("中证1000");
        comboBox.setEditable(false);
        comboBox.setToolTipText((String)comboBox.getItemAt(0));
        add(comboBox);
        //选择策略
        MyLabel label3=new MyLabel("选择策略") ;
        label3.setLocation(100*width/1920,500*height/1030);
        add(label3);

        radioButton1 = new JRadioButton("动量策略");// 创建单选按钮
        radioButton1.setBounds(adaptScreen(200,550,150,40));
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
        radioButton2.setBounds(adaptScreen(400,550,150,40));
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
        searchBt.setFont(new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        searchBt.addMouseListener(new SearchListener());
        searchBt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StrategySwitchController.getInstance().viewSwitch("analysePanel");
            }
        });

        add(searchBt);


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
            System.out.println("动量");
        }else{
            formative=strategyTypePanel.getMR();
            traceBackStrategy=TraceBackStrategy.MR;
        }

        return new TraceBackCriteriaVO(datePanel.getStartDate(),datePanel.getEndDate(),formative,strategyTypePanel.getHoldingPeriod(),
                getPoolVO(),traceBackStrategy,strategyTypePanel.getHoldingNum(),getBasicStock());
    }
    private StockPoolCriteriaVO getPoolVO(){
        StType stType = null;

        if(STComboBox.getSelectedItem().equals("排除ST")){
            stType=StType.EXCLUDE;
        }else if(STComboBox.getSelectedItem().equals("包含ST")){
            stType=StType.INCLUDE;
        }else if(STComboBox.getSelectedItem().equals("仅有ST")){
            stType=StType.ONLY;
        }
         return new StockPoolCriteriaVO(stType , mulit.getSelectedValues());
    }
    private String getBasicStock(){
        return comboBox.getSelectedItem().toString();
    }
}
