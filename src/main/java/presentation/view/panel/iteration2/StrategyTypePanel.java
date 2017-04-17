package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.UIManagerUtil;
import presentation.view.tools.component.MyLabel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by day on 17/3/29.
 */
public class StrategyTypePanel extends TemplatePanel {
    MyLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JComboBox MScomboBox,MRcomboBox;
    JTextField holdingDate,holdingNum;


    public StrategyTypePanel(){

        setLayout(null);
        setBounds(adaptScreen(430,580,600,200));
        setBackground(new Color(35,39,44));


        label1= new MyLabel("形成期");
        label1.setLocation(0* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        label1.setVisible(true);
        add(label1);

        label2= new MyLabel("乖离率");
        label2.setLocation(0* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        label2.setVisible(false);
        add(label2);

        MScomboBox=new JComboBox();
        MScomboBox.setBounds(adaptScreen(110,0,80,35));
        MScomboBox.addItem("5");
        MScomboBox.addItem("10");
        MScomboBox.addItem("20");
        MScomboBox.addItem("30");
        MScomboBox.addItem("60");
        MScomboBox.addItem("120");

        MScomboBox.setEditable(false);
        MScomboBox.setToolTipText((String)MScomboBox.getItemAt(0));
        add(MScomboBox);
        label3= new MyLabel("天 ");
        label3.setLocation(215* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        add(label3);



        MRcomboBox=new JComboBox();
        MRcomboBox.setBounds(adaptScreen(110,0,80,35));
        MRcomboBox.addItem("5");
        MRcomboBox.addItem("10");
        MRcomboBox.addItem("20");
        MRcomboBox.addItem("30");
        MRcomboBox.addItem("60");
        MRcomboBox.addItem("120");
        MRcomboBox.setEditable(false);
        MRcomboBox.setToolTipText((String)MRcomboBox.getItemAt(0));
        MRcomboBox.setVisible(false);
        add(MRcomboBox);
        label4= new MyLabel("日均线");
        label4.setLocation(215* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        label4.setVisible(false);
        add(label4);

        label5= new MyLabel("调仓周期");
        label5.setLocation(0* WindowData.getInstance().getWidth()/1920,70*WindowData.getInstance().getHeight()/1030);
        add(label5);
        holdingDate=new JTextField("5");
        holdingDate.setBounds(adaptScreen(110,70,30,35));
        add(holdingDate);
        label7= new MyLabel("天 (输入请大于1)");
        label7.setBounds(adaptScreen(175,70,140,35));
        add(label7);

        label6= new MyLabel("持有股票");
        label6.setLocation(0* WindowData.getInstance().getWidth()/1920,140*WindowData.getInstance().getHeight()/1030);
        add(label6);
        holdingNum=new JTextField("5");
        holdingNum.setBounds(adaptScreen(110,140,30,35));

        add(holdingNum);
        label8= new MyLabel("支 (输入请大于1)");
        label8.setBounds(adaptScreen(175,140,140,35));
        add(label8);

    }

    public void openType1(){
        label1.setVisible(true);
        label3.setVisible(true);
        MScomboBox.setVisible(true);
        label2.setVisible(false);
        MRcomboBox.setVisible(false);
        label4.setVisible(false);
    }

    public void openType2(){
        label1.setVisible(false);
        label3.setVisible(false);
        MScomboBox.setVisible(false);
        label2.setVisible(true);
        MRcomboBox.setVisible(true);
        label4.setVisible(true);
    }

    public int getMS(){
        return Integer.parseInt(MScomboBox.getSelectedItem().toString());
    }

    public int getMR(){
        return Integer.parseInt(MRcomboBox.getSelectedItem().toString());
    }
    public int getHoldingPeriod(){
        return Integer.parseInt(holdingDate.getText());
    }
    public int getHoldingNum(){
        return Integer.parseInt(holdingNum.getText());
    }
}
