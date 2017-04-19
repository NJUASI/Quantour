package presentation.view.panel;

import presentation.controller.StocksTableController;
import presentation.listener.navigationBarListener.UserListener;
import presentation.listener.stocksTablePanelListener.AddFavoriteListener;
import presentation.listener.stocksTablePanelListener.DetailOfCodeListener;
import presentation.listener.stocksTablePanelListener.SearchListener;
import presentation.view.frame.MainFrame;
import presentation.view.tools.*;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.MyLabel;
import presentation.view.tools.component.datePicker.SingleDatePickerPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/5.
 *
 * 股票列表显示面板
 */
public class StocksTablePanel extends TemplatePanel {

    /**
     * The constant stocksTablePanel.
     */
    private static StocksTablePanel stocksTablePanel;

    /**
     * The Date picker panel.
     */
    SingleDatePickerPanel datePickerPanel;
    public JLabel label;
    public JButton search;
    public JLabel message,title,notFound,success;
    /**
     * 股票列表面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */

    public StocksTablePanel(){

        setBounds(adaptScreen(0,0,1900,1400));

        MyLabel label1=new MyLabel("日期") ;
        label1.setLocation(60*width/1920,50*height/1030);
        add(label1);

        datePickerPanel = new SingleDatePickerPanel();
        datePickerPanel.setBounds(adaptScreen(120, 50,175,35));
        add(datePickerPanel);

        search = new MyButton("搜索");
        search.setBounds(adaptScreen(330,50,80,35));
        search.addMouseListener(new SearchListener());
        add(search);

        success=new MyLabel("",19) ;
        success.setBounds(adaptScreen(1150,870,210,35));
        success.setVisible(false);
        add(success);

        JButton importData= new MyButton("加入收藏");
        importData.setBounds(adaptScreen(1550,870,110,35));
        importData.addMouseListener(new AddFavoriteListener());
        add(importData);

        title=new MyLabel("   市 场 行 情",22) ;
        title.setBounds(adaptScreen(460,80-40,1300,35));
        title.setBackground(ColorUtils.titleBgColor());
        title.setOpaque(true);
//        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);


        notFound=new MyLabel("没有找到数据",22) ;
        notFound.setBounds(1100*width/1920-200,830*height/1920-200,400*height/1920,30*height/1030);
        notFound.setVisible(false);
        add(notFound);


        ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/notFoundLogo.png"));
        message =new JLabel();
        bgPicture.setImage(bgPicture.getImage());
        message.setIcon(bgPicture);
        message.setVisible(false);
        message.setBounds(850*width/1920-200,900*height/1920-200,400,400);
        add(message);

        label = new JLabel("",JLabel.CENTER);
        label.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,26* WindowData.getInstance().getWidth()/1920));
        label.setBounds(0 * width / 1920, 100 * height / 1030, (1920-120) * width / 1920 , 850* height / 1030);
        label.setBorder(BorderFactory.createEmptyBorder());
        label.setVisible(false);
        label.setBackground(ColorUtils.backgroundColor());
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        add(label);

    }

    public void popUp(String str){
        success.setText(str);
        success.setVisible(true);
        repaint();
    }
    /**
     * 单件模式
     *
     * @param
     * @return StocksTablePanel 自选股票
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static StocksTablePanel getInstance() {
        if (stocksTablePanel == null) {
            stocksTablePanel = new StocksTablePanel();
        }
        return stocksTablePanel;
    }


    /**
     * 获取选择的日期
     *
     * @return the local date 选择的日期
     */
    public LocalDate getChooseDate(){
        return datePickerPanel.getDate();
    }

}
