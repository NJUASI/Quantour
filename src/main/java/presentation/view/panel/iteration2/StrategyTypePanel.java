package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.component.MyLabel;
import presentation.view.tools.WindowData;

import javax.swing.*;

/**
 * Created by day on 17/3/29.
 */
public class StrategyTypePanel extends TemplatePanel {
    MyLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JComboBox MScomboBox,MRcomboBox,holdingDate,holdingNum;
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

        MScomboBox=new JComboBox();
        MScomboBox.setBounds(adaptScreen(180,50,85,35));
        MScomboBox.addItem("5");
        MScomboBox.addItem("10");
        MScomboBox.addItem("20");
        MScomboBox.addItem("30");
        MScomboBox.setEditable(false);
        MScomboBox.setToolTipText((String)MScomboBox.getItemAt(0));
        add(MScomboBox);
        label3= new MyLabel("天 ");
        label3.setLocation(275* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
        add(label3);



        MRcomboBox=new JComboBox();
        MRcomboBox.setBounds(adaptScreen(180,50,85,35));
        MRcomboBox.addItem("5");
        MRcomboBox.addItem("10");
        MRcomboBox.addItem("30");
        MRcomboBox.addItem("60");
        MRcomboBox.setEditable(false);
        MRcomboBox.setToolTipText((String)MRcomboBox.getItemAt(0));
        MRcomboBox.setVisible(false);
        add(MRcomboBox);
        label4= new MyLabel("日均线");
        label4.setLocation(275* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
        label4.setVisible(false);
        add(label4);

        label5= new MyLabel("调仓周期");
        label5.setLocation(400* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        add(label5);
        holdingDate=new JComboBox();
        holdingDate.setBounds(adaptScreen(480,50,85,35));
        holdingDate.addItem("5");
        holdingDate.addItem("10");
        holdingDate.addItem("20");
        holdingDate.addItem("30");
        holdingDate.setEditable(false);
        holdingDate.setToolTipText((String)holdingDate.getItemAt(0));
        add(holdingDate);
        label7= new MyLabel("天 ");
        label7.setLocation(575* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
        add(label7);

        label6= new MyLabel("持有股票");
        label6.setLocation(700* WindowData.getInstance().getWidth()/1920,0*WindowData.getInstance().getHeight()/1030);
        add(label6);
        holdingNum=new JComboBox();
        holdingNum.setBounds(adaptScreen(780,50,85,35));
        holdingNum.addItem("5");
        holdingNum.addItem("10");
        holdingNum.addItem("20");
        holdingNum.addItem("30");
        holdingNum.setEditable(false);
        holdingNum.setToolTipText((String)holdingNum.getItemAt(0));
        add(holdingNum);
        label8= new MyLabel("支 ");
        label8.setLocation(875* WindowData.getInstance().getWidth()/1920,50*WindowData.getInstance().getHeight()/1030);
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
        return Integer.parseInt(holdingDate.getSelectedItem().toString());
    }
    public int getHoldingNum(){
        return Integer.parseInt(holdingNum.getSelectedItem().toString());
    }
}
