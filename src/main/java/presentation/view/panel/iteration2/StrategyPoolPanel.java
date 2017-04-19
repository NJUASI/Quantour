package presentation.view.panel.iteration2;

import presentation.listener.strategyPanelListener.DeletePoolListener;
import presentation.listener.userPanelListener.DeleteFavoriteListener;
import presentation.view.panel.TemplatePanel;
import presentation.view.panel.user.FavoritePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.PopUpFrame;
import presentation.view.tools.WindowData;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.MyLabel;
import presentation.view.tools.component.datePicker.DoubleDatePickerPanel;
import utilities.enums.StType;
import utilities.exceptions.PrivateStockNotFoundException;
import vo.StockPoolCriteriaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 61990 on 2017/4/17.
 */
public class StrategyPoolPanel  extends TemplatePanel {

    JRadioButton radioButton1,radioButton2;
    ButtonGroup group;
    MultiComboBox mulit;
    JLabel lb,lb3,label;
    JComboBox STComboBox;
    JButton delete;
    public StockPoolTable stockPoolTable;

    public StrategyPoolPanel(){
        Color bgColor = new Color(35,39,44);
        setLayout(null);
        setBounds(adaptScreen(60,60,1200,315-90));
        setBackground(bgColor);

        radioButton1 = new JRadioButton("按板块选");// 创建单选按钮
        radioButton1.setBounds(adaptScreen(200,40,150,40));
        add(radioButton1);

        radioButton1.setBackground(bgColor);
        radioButton1.setForeground(Color.WHITE);
        radioButton1.setSelected(true);
        radioButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openPool1();
            }
        });

        radioButton2 = new JRadioButton("自选股池");// 创建单选按钮
        radioButton2.setBounds(adaptScreen(200,80,150,40));
        radioButton2.setBackground(bgColor);
        radioButton2.setForeground(Color.WHITE);
        radioButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openPool2();
            }
        });
        add(radioButton2);

        group = new ButtonGroup();// 创建单选按钮组
        group.add(radioButton1);// 将radioButton1增加到单选按钮组中
        group.add(radioButton2);// 将radioButton2增加到单选按钮组中


//        MyLabel label1=new MyLabel("选股票池",20) ;
//        label1.setLocation(40*width/1920,30*height/1030);
//        add(label1);

         lb= new MyLabel("板块",16);
        lb.setBounds(adaptScreen(410,80,60,40));
        add(lb);


        Object[] value = new String[]{"全部", "主板","中小板" , "创业板" };
        Object[] defaultValue = new String[]{ "主板","中小板" , "创业板" };
        mulit = new MultiComboBox(value, defaultValue);
        mulit.setBounds(adaptScreen(500,80,230,35));
        add(mulit);

        lb3= new MyLabel("ST");
        lb3.setBounds(adaptScreen(410,130,20,40));
        add(lb3);



        STComboBox=new JComboBox();
        STComboBox.setBounds(adaptScreen(500,130,140,35));
        STComboBox.addItem("包含ST");
        STComboBox.addItem("排除ST");
        STComboBox.addItem("仅有ST");
        STComboBox.setEditable(false);
        STComboBox.setToolTipText((String)STComboBox.getItemAt(0));

        add(STComboBox);

        delete= new MyButton("删除");
        delete.setBounds(adaptScreen(650,120,70,35));
        delete.addMouseListener(new DeletePoolListener());
        add(delete);

        openPool1();
        refreshTabel();

    }
    public void refreshTabel(){
        if (stockPoolTable!=null) {
            remove(stockPoolTable);
            remove(label);
        }
        try {
            stockPoolTable=new StockPoolTable();
            stockPoolTable.setBounds(adaptScreen(420,20,200,200));

            label = new JLabel();
            label.setBounds(420 * width / 1920, (20+30*(stockPoolTable.jTable.getRowCount()+1)) * height / 1030, 200* width / 1920 , 200* height / 1030);
            label.setBorder(BorderFactory.createEmptyBorder());
            label.setBackground( new Color(35,39,44));
            label.setForeground(Color.WHITE);
            label.setOpaque(true);
            label.setVisible(true);
            add(label);
            add(stockPoolTable);
            if(radioButton1.isSelected()){
                stockPoolTable.setVisible(false);
            }
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PrivateStockNotFoundException e) {
            e.printStackTrace();
            new PopUpFrame(e.getMessage());
            //TODO 高源后期添加
        }
    }
    public void openPool1(){
        mulit.setVisible(true);
        lb.setVisible(true);
        lb3.setVisible(true);
        STComboBox.setVisible(true);
        delete.setVisible(false);
        if(stockPoolTable!=null) {
            stockPoolTable.setVisible(false);
            label.setVisible(false);
        }
    }
//
    public void openPool2(){
        mulit.setVisible(false);
        lb.setVisible(false);
        lb3.setVisible(false);
        STComboBox.setVisible(false);
        stockPoolTable.setVisible(true);
        delete.setVisible(true);
        label.setVisible(true);
    }

    public StockPoolCriteriaVO getPoolVO(){
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

    public boolean getIsCustomized(){
        return radioButton2.isSelected();
    }
}
