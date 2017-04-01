package presentation.view.panel;


import javafx.scene.control.*;
import presentation.view.tools.BeautyEyeUtil;
import presentation.view.tools.MyLabel;
import presentation.view.tools.SingleDatePickerPanel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ThermometerPanel extends TemplatePanel {
    //温度计面板
//    private static ThermometerPanel thermometerPanel;

    MyLabel label11,label12,label21,label22,label31,label32;
    JLabel colorLabel1,colorLabel2,colorLabel3;
    //搜索按钮
//    MyLabel search;


    /**
     * 温度计面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ThermometerPanel(int[] num) {

        setSize(new Dimension(360*WindowData.getInstance().getWidth()/1920,420*WindowData.getInstance().getHeight()/1030));
        setBackground(Color.BLACK);

        label11=new MyLabel(num[0]+"");
        label11.setBounds(adaptScreen(80,55,50,30));
        label11.setForeground(Color.RED);
        add(label11);

        colorLabel1=new JLabel();
        colorLabel1.setBounds(adaptScreen(30,85,300*num[0]/(num[0]+num[1]),20));
        colorLabel1.setOpaque(true);
        colorLabel1.setBackground(Color.RED);
        add(colorLabel1);

        label12=new MyLabel(num[1]+"");
        label12.setHorizontalAlignment(SwingConstants.RIGHT);
        label12.setBounds(adaptScreen(230,105,50,30));
        label12.setForeground(Color.GREEN);
        add(label12);

        label21=new MyLabel(num[2]+"");
        label21.setBounds(adaptScreen(140,150,50,30));
        label21.setForeground(Color.RED);
        add(label21);

        colorLabel2=new JLabel();
        colorLabel2.setBounds(adaptScreen(30,180,300*num[2]/(num[2]+num[3]),20));
        colorLabel2.setOpaque(true);
        colorLabel2.setBackground(Color.RED);
        add(colorLabel2);

        label22=new MyLabel(num[3]+"");
        label22.setHorizontalAlignment(SwingConstants.RIGHT);
        label22.setBounds(adaptScreen(360-50-140,200,50,30));
        label22.setForeground(Color.GREEN);
        add(label22);

        label31=new MyLabel(num[4]+"");
        label31.setBounds(adaptScreen(190,260,50,30));
        label31.setForeground(Color.RED);
        add(label31);

        colorLabel3=new JLabel();
        colorLabel3.setBounds(adaptScreen(30,300,300*num[4]/(num[4]+num[5]),20));
        colorLabel3.setOpaque(true);
        colorLabel3.setBackground(Color.RED);
        add(colorLabel3);

        label32=new MyLabel(num[5]+"");
        label32.setHorizontalAlignment(SwingConstants.RIGHT);
        label32.setBounds(adaptScreen(360-190-50,330,50,30));
        label32.setForeground(Color.GREEN);
        add(label32);

        createPanel();

    }


    public static void main(String[] args) {
        BeautyEyeUtil.beautyEye();
        JFrame j= new JFrame();
        j.setTitle("Quantourist");
        j.setLayout(null);

        WindowData.setWindowData(1920, 1030);//save the window's data

        int width=WindowData.getInstance().getWidth();
        int height=WindowData.getInstance().getHeight();
        j.setSize(width,height);


//        setUndecorated(true);
       j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        j.setVisible(true);
        j.setResizable(false);
        j.add(new ThermometerPanel(new int[]{1,2,3,4,5,6}));
    }

    void createPanel(){

        MyLabel label=new MyLabel("市场温度计",18) ;
        label.setBounds(adaptScreen(0,10,360,40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        MyLabel label1=new MyLabel("涨停",16) ;
        label1.setBounds(adaptScreen(30,55,40,30));
        add(label1);

        JLabel color1=new JLabel();
        color1.setBounds(adaptScreen(30,85,300,20));
        color1.setOpaque(true);
        color1.setBackground(Color.GREEN);
        add(color1);

        MyLabel label2=new MyLabel("跌停",16) ;
        label2.setBounds(adaptScreen(290,105,40,30));
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label2);

        MyLabel label3=new MyLabel("涨幅超过5%",16) ;
        label3.setBounds(adaptScreen(30,150,120,30));
        add(label3);

        JLabel color2=new JLabel();
        color2.setBounds(adaptScreen(30,180,300,20));
        color2.setOpaque(true);
        color2.setBackground(Color.GREEN);
        add(color2);

        MyLabel label4=new MyLabel("跌幅超过5%",16) ;
        label4.setBounds(adaptScreen(330-120,200,120,30));
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label4);



        MyLabel label51=new MyLabel("开盘‐收盘大于5% *") ;
        label51.setBounds(adaptScreen(30,245,160,30));
        label51.setVerticalAlignment(SwingConstants.BOTTOM);
        add(label51);
        MyLabel label52=new MyLabel("上一个交易日收盘价") ;
        label52.setBounds(adaptScreen(30,270,160,30));
        label52.setVerticalAlignment(SwingConstants.TOP);
        add(label52);
        JLabel color3=new JLabel();
        color3.setBounds(adaptScreen(30,300,300,20));
        color3.setOpaque(true);
        color3.setBackground(Color.GREEN);
        add(color3);
        MyLabel label61=new MyLabel("开盘‐收盘小于-5% *") ;
        label61.setBounds(adaptScreen(170,320,160,30));
        label61.setVerticalAlignment(SwingConstants.BOTTOM);
        label61.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label61);
        MyLabel label62=new MyLabel("上一个交易日收盘价") ;
        label62.setBounds(adaptScreen(170,350,160,30));
        label62.setVerticalAlignment(SwingConstants.TOP);
        label62.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label62);
    }
}
