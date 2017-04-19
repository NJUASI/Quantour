package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.UIManagerUtil;
import presentation.view.tools.component.MyLabel;
import presentation.view.tools.WindowData;
import utilities.enums.FormateType;
import utilities.enums.PickType;
import vo.FormateAndPickVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by day on 17/3/29.
 */
public class StrategyTypePanel extends TemplatePanel {
    MyLabel label, label1,label2,label3,label4,label5,label7;
    JComboBox createComboBox,typeComboBox,chooseComboBox;
    JTextField holdingDate,holdingNum,rank;


    public StrategyTypePanel(){

        setLayout(null);
        setBounds(adaptScreen(60,650,1200,200));
        setBackground(new Color(35,39,44));


        label= new MyLabel("形成指标",19);
        label.setLocation(55* WindowData.getInstance().getWidth()/1920,40*WindowData.getInstance().getHeight()/1030);
        add(label);

        label1= new MyLabel("形成期");
        label1.setLocation(60* WindowData.getInstance().getWidth()/1920,100*WindowData.getInstance().getHeight()/1030);
        label1.setVisible(true);
        add(label1);


        createComboBox=new JComboBox();
        createComboBox.setBounds(adaptScreen(120,100,100,35));
        createComboBox.addItem("5");
        createComboBox.addItem("10");
        createComboBox.addItem("20");
        createComboBox.addItem("30");
        createComboBox.addItem("60");
        createComboBox.addItem("120");

        createComboBox.setEditable(false);
        createComboBox.setToolTipText((String)createComboBox.getItemAt(0));
        add(createComboBox);

        label3= new MyLabel("日 ");
        label3.setLocation(225* WindowData.getInstance().getWidth()/1920,100*WindowData.getInstance().getHeight()/1030);
        add(label3);


        typeComboBox=new JComboBox();
        typeComboBox.setBounds(adaptScreen(250,100,120,35));
        typeComboBox.addItem("涨幅");
        typeComboBox.addItem("乖离率");
        typeComboBox.addItem("成交量");
        typeComboBox.setEditable(false);
        typeComboBox.setToolTipText((String)createComboBox.getItemAt(0));
        add(typeComboBox);

        label2= new MyLabel("筛选条件",19);
        label2.setLocation(445* WindowData.getInstance().getWidth()/1920,40*WindowData.getInstance().getHeight()/1030);
        add(label2);

        chooseComboBox=new JComboBox();
        chooseComboBox.setBounds(adaptScreen(455,100,140,35));
        chooseComboBox.addItem("排名最大");
        chooseComboBox.addItem("排名最小");
        chooseComboBox.addItem("排名%最大");
        chooseComboBox.addItem("排名%最小");
        chooseComboBox.setEditable(false);
        chooseComboBox.setToolTipText((String)createComboBox.getItemAt(0));
        add(chooseComboBox);

        chooseComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(chooseComboBox.getSelectedItem().equals("排名最大")) {
                    label4.setText("支");
                }else{
                    label4.setText(" %");
                }
                repaint();
            }
        });

        rank=new JTextField("5");
        rank.setBounds(adaptScreen(610,100,60,35));
        add(rank);
        label4= new MyLabel("支");
        label4.setBounds(adaptScreen(680,100,140,35));
        add(label4);



        label5= new MyLabel("调仓周期");
        label5.setLocation(800* WindowData.getInstance().getWidth()/1920,100*WindowData.getInstance().getHeight()/1030);
        add(label5);
        holdingDate=new JTextField("5");
        holdingDate.setBounds(adaptScreen(70+800,100,60,35));
        add(holdingDate);
        label7= new MyLabel("天 (输入请大于1)");
        label7.setBounds(adaptScreen(135+800,100,140,35));
        add(label7);


    }



    public int getCreatePeriod(){
        return Integer.parseInt(createComboBox.getSelectedItem().toString());
    }
    public int getHoldingPeriod(){
        return Integer.parseInt(holdingDate.getText());
    }
    public int getHoldingNum(){
        return Integer.parseInt(holdingNum.getText());
    }

    public FormateAndPickVO getFormateAndPickVO(){
      return new FormateAndPickVO( FormateType.getEnum(typeComboBox.getSelectedItem().toString()),
        PickType.getEnum(chooseComboBox.getSelectedItem().toString()),
        Integer.parseInt(rank.getText()));
    }
}
