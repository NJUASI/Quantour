package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.DoubleDatePickerPanel;
import presentation.view.tools.MyLabel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by day on 17/3/29.
 */
public class StrategyTypePanel extends TemplatePanel {
    MyLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JComboBox comboBox1,comboBox2,comboBox3,comboBox4;
    public StrategyTypePanel(){
        setLayout(null);
        setBounds(adaptScreen(100,630,1400,200));
        setBackground(WindowData.getInstance().getColor());

        label1= new MyLabel("形成期");
        label1.setLocation(100* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        label1.setVisible(true);
        add(label1);

        label2= new MyLabel("乖离率");
        label2.setLocation(100* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        label2.setVisible(false);
        add(label2);

        comboBox1=new JComboBox();
        comboBox1.setBounds(adaptScreen(180,50,85,35));
        comboBox1.addItem("5");
        comboBox1.addItem("10");
        comboBox1.addItem("20");
        comboBox1.addItem("30");
        comboBox1.setEditable(false);
        comboBox1.setToolTipText((String)comboBox1.getItemAt(0));
        add(comboBox1);
        label3= new MyLabel("天 ");
        label3.setLocation(275* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
        add(label3);



        comboBox2=new JComboBox();
        comboBox2.setBounds(adaptScreen(180,50,85,35));
        comboBox2.addItem("5");
        comboBox2.addItem("10");
        comboBox2.addItem("30");
        comboBox2.addItem("60");
        comboBox2.setEditable(false);
        comboBox2.setToolTipText((String)comboBox2.getItemAt(0));
        comboBox2.setVisible(false);
        add(comboBox2);
        label4= new MyLabel("日均线");
        label4.setLocation(275* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
        label4.setVisible(false);
        add(label4);

        label5= new MyLabel("调仓周期");
        label5.setLocation(400* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        add(label5);
        comboBox3=new JComboBox();
        comboBox3.setBounds(adaptScreen(480,50,85,35));
        comboBox3.addItem("5");
        comboBox3.addItem("10");
        comboBox3.addItem("20");
        comboBox3.addItem("30");
        comboBox3.setEditable(false);
        comboBox3.setToolTipText((String)comboBox3.getItemAt(0));
        add(comboBox3);
        label7= new MyLabel("天 ");
        label7.setLocation(575* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
        add(label7);

        label6= new MyLabel("持有股票");
        label6.setLocation(700* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        add(label6);
        comboBox4=new JComboBox();
        comboBox4.setBounds(adaptScreen(780,50,85,35));
        comboBox4.addItem("5");
        comboBox4.addItem("10");
        comboBox4.addItem("20");
        comboBox4.addItem("30");
        comboBox4.setEditable(false);
        comboBox4.setToolTipText((String)comboBox3.getItemAt(0));
        add(comboBox4);
        label8= new MyLabel("支 ");
        label8.setLocation(875* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
        add(label8);

    }

    public void openType1(){
        label1.setVisible(true);
        label3.setVisible(true);
        comboBox1.setVisible(true);
        label2.setVisible(false);
        comboBox2.setVisible(false);
        label4.setVisible(false);
    }

    public void openType2(){
        label1.setVisible(false);
        label3.setVisible(false);
        comboBox1.setVisible(false);
        label2.setVisible(true);
        comboBox2.setVisible(true);
        label4.setVisible(true);
    }




}
