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

    DoubleDatePickerPanel createDatePanel;
    MyLabel label1,label2;
    JComboBox comboBox;
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


        createDatePanel = new DoubleDatePickerPanel();
        createDatePanel.setBounds(adaptScreen(180 , 70 , 525 , 37));
        createDatePanel.setVisible(true);
        add(createDatePanel);


        comboBox=new JComboBox();
        comboBox.setBounds(adaptScreen(180,70,200,35));
        comboBox.addItem("5日均线");
        comboBox.addItem("10日均线");
        comboBox.addItem("30日均线");
        comboBox.addItem("60日均线");
        comboBox.setEditable(false);
        comboBox.setToolTipText((String)comboBox.getItemAt(0));
        comboBox.setVisible(false);
        add(comboBox);
    }

    public void openType1(){
        label1.setVisible(true);
        createDatePanel.setVisible(true);
        label2.setVisible(false);
        comboBox.setVisible(false);
    }

    public void openType2(){
        label1.setVisible(false);
        createDatePanel.setVisible(false);
        label2.setVisible(true);
        comboBox.setVisible(true);
    }




}
