package presentation.view.panel.iteration2;

import presentation.controller.StrategySwitchController;
import presentation.view.panel.TemplatePanel;
import presentation.view.tools.DoubleDatePickerPanel;
import presentation.view.tools.MyLabel;
import presentation.view.tools.WindowData;

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

    int width;
    int height;
    private ChooseStrategyPanel(){
        width= WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        setLayout(null);
        setBounds(adaptScreen(0,0,1830,990));

        MyLabel label1=new MyLabel("选股票池") ;
        label1.setLocation(100*width/1920,100*height/1030);
        add(label1);

        JLabel lb= new JLabel("板块");
        lb.setForeground(Color.white);
        lb.setFont(new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,17));
        lb.setBounds(adaptScreen(200,170,60,40));
        add(lb);

        Object[] value = new String[]{"全部", "主板","中小板" , "创业板" };
        Object[] defaultValue = new String[]{ "" };
        mulit = new MultiComboBox(value, defaultValue);
        mulit.setBounds(adaptScreen(270,170,200,40));
//        System.out.println(mulit.getSelectedValues()[0]);
        add(mulit);

        MyLabel label2=new MyLabel("回测区间") ;
        label2.setLocation(100*width/1920,300*height/1030);
        add(label2);
        datePanel = new DoubleDatePickerPanel();
        datePanel.setBounds(adaptScreen(200 , 370 , 520 , 35));
        add(datePanel);


        MyLabel label3=new MyLabel("选择策略") ;
        label3.setLocation(100*width/1920,500*height/1030);
        add(label3);

        radioButton1 = new JRadioButton("动量策略");// 创建单选按钮
        radioButton1.setBounds(adaptScreen(200,550,150,40));
        add(radioButton1);// 策略1按钮
        radioButton1.setBackground(WindowData.getInstance().getColor());
        radioButton1.setForeground(Color.WHITE);
        radioButton2 = new JRadioButton("均值回归");// 创建单选按钮
        radioButton2.setBounds(adaptScreen(400,550,150,40));
        radioButton2.setBackground(WindowData.getInstance().getColor());
        radioButton2.setForeground(Color.WHITE);
        add(radioButton2);// 策略2按钮

        group = new ButtonGroup();// 创建单选按钮组
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中

        JButton button= new JButton("12121");
        button.setBounds(adaptScreen(0,0,100,30));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                StrategySwitchController.getInstance().viewSwitch("analysePanel");
            }
        });

        add(button);
    }

    public static ChooseStrategyPanel getInstance(){
        if(chooseStrategyPanel==null){
            chooseStrategyPanel=new ChooseStrategyPanel();
        }
        return chooseStrategyPanel;
    }
}
