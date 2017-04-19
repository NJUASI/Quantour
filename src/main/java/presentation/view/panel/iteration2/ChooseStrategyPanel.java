package presentation.view.panel.iteration2;

import presentation.controller.StrategySwitchController;
import presentation.listener.strategyPanelListener.SearchListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.*;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.datePicker.DoubleDatePickerPanel;
import presentation.view.tools.component.MyLabel;
import utilities.enums.FormateType;
import utilities.enums.PickType;
import vo.FormateAndPickVO;
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


    DoubleDatePickerPanel datePanel;
    StrategyTypePanel strategyTypePanel;
    public StrategyPoolPanel strategyPoolPanel;
    JComboBox comboBox;
    JLabel progressBar;
    Thread thread;
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
        Color color =new Color(35,39,44);
        width= WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        setLayout(null);
        setBounds(adaptScreen(0,0,1830,990));

        datePanel = new DoubleDatePickerPanel();
        datePanel.setBounds(adaptScreen(200 , 370+70 , 520 , 37));
        add(datePanel);
        //股票池区域
        MyLabel user=new MyLabel("   选 股 票 池",19) ;
        user.setBounds(adaptScreen(60,25,1200,35));
        user.setBackground(ColorUtils.titleBgColor());
        user.setOpaque(true);
        add(user);

        strategyPoolPanel=new StrategyPoolPanel();
        add(strategyPoolPanel);

        //区间板块
        MyLabel block2title=new MyLabel("   回 测 区 间",18) ;
        block2title.setBounds(adaptScreen(60,310,1200,35));
        block2title.setBackground(ColorUtils.titleBgColor());
        block2title.setOpaque(true);
        add(block2title);


        JLabel lb1= new MyLabel("收益基准");
        lb1.setBounds(adaptScreen(820,368+70,80,40));
        add(lb1);

        comboBox=new JComboBox();
        comboBox.setBounds(adaptScreen(910,370+70,200,35));
        comboBox.addItem("沪深300");
        comboBox.addItem("创业板指");
        comboBox.addItem("中小板指");
        comboBox.addItem("中证100");
        comboBox.addItem("中证500");
        comboBox.addItem("中证1000");
        comboBox.addItem("中证红利");
        comboBox.setEditable(false);
        comboBox.setToolTipText((String)comboBox.getItemAt(0));
        add(comboBox);
        //选择策略

        MyLabel block3title=new MyLabel("   选 择 策 略" ,18) ;
        block3title.setBounds(adaptScreen(60,595,1200,35));
        block3title.setBackground(ColorUtils.titleBgColor());
        block3title.setOpaque(true);
        add(block3title);


        strategyTypePanel=new StrategyTypePanel();
        add(strategyTypePanel);


        JButton searchBt= new MyButton("开始回测");
        searchBt.setBounds(adaptScreen(1400,550,100,35));

        searchBt.addMouseListener(new SearchListener());
//        searchBt.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                StrategySwitchController.getInstance().viewSwitch("analysePanel");
//            }
//        });

        add(searchBt);

        JButton returnBt= new MyButton("查看上次");
        returnBt.setBounds(adaptScreen(1400,480,100,35));
        returnBt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StrategySwitchController.getInstance().viewSwitch("analysePanel");
            }
        });
        add(returnBt);



        MyLabel block2=new MyLabel("") ;
        block2.setBounds(adaptScreen(60,315*2,1200,315-30));
        block2.setBackground(color);
        block2.setOpaque(true);
        add(block2);

        MyLabel block1=new MyLabel("") ;
        block1.setBounds(adaptScreen(60,315+30,1200,315-90));
        block1.setBackground(color);
        block1.setOpaque(true);
        add(block1);
    }

    public void popup(){
        progressBar = new MyLabel("正在回测..");
        progressBar.setBounds(adaptScreen(1400, 400, 100, 30));
        add(progressBar);
        thread=new Thread(() ->{
            int num =0;
            while (true){
                try{
                    Thread.sleep(400);
                }catch (Exception e){
                    e.printStackTrace();
                }
                switch (num){
                    case 0:
                        progressBar.setText("正在回测...");
                        break;
                    case 1:
                        progressBar.setText("正在回测....");
                        break;
                    case 2:
                        progressBar.setText("正在回测.....");
                        break;
                    case 3:
                        progressBar.setText("正在回测..");
                        break;
                }
                num=(num+1)%4;
            }
        });
        thread.start();
        progressBar.repaint();
        repaint();
//        progressBar = new JLabel();
//        progressBar.setBounds(1100*width/1920, 420*height/1030, 305, 42);
//        add(progressBar);
//        message = new JLabel();
//        message.setBounds(1200*width/1920, 350*height/1030, 305, 42);
//        add(message);
//        popupProgress=new PopupProgress(progressBar,message);
//        popupProgress.start();
    }
    public void popdown(){
//        popupProgress.stop();
//        remove(progressBar);
//        remove(message);
        thread.stop();
        remove(progressBar);
        repaint();
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

        formative=strategyTypePanel.getCreatePeriod();

        return new TraceBackCriteriaVO(datePanel.getStartDate(),datePanel.getEndDate(),formative,strategyTypePanel.getHoldingPeriod(),
               strategyPoolPanel.getPoolVO(),getBasicStock(), strategyPoolPanel.getIsCustomized(), strategyTypePanel.getFormateAndPickVO());
    }

    public String getBasicStock(){
        return comboBox.getSelectedItem().toString();
    }

}
