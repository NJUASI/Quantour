package presentation.view.panel;

import presentation.listener.stocksTablePanelListener.DetailOfCodeListener;
import presentation.listener.stocksTablePanelListener.SearchListener;
import presentation.view.tools.MyLabel;
import presentation.view.tools.SingleDatePickerPanel;
import presentation.view.tools.StocksTablePane;
import presentation.view.tools.customizedButton.MyButton;

import javax.swing.*;
import java.awt.*;
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

    /**
     * 股票列表面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */

    public StocksTablePanel(){

        MyLabel label1=new MyLabel("日期") ;
        label1.setLocation(300*width/1920,50*height/1030);
        add(label1);

        datePickerPanel = new SingleDatePickerPanel();
        datePickerPanel.setBounds(adaptScreen(350, 50,175,35));
        add(datePickerPanel);

        JButton search = new JButton("搜索");
        search.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,15*width/1920));
        search.setBounds(adaptScreen(600,50,80,35));
        search.addMouseListener(new SearchListener());
        add(search);

        JButton detailOfCode = new JButton("查看详情");
        detailOfCode.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,15*width/1920));
        detailOfCode.setBounds(adaptScreen(1150,50,110,35));
        detailOfCode.addMouseListener(new DetailOfCodeListener());
        add(detailOfCode);

        label = new JLabel("当日无股票信息",JLabel.CENTER);
        label.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,20));
        label.setBounds(150 * width / 1920, 120 * height / 1030, 1500 * width / 1920 , 800* height / 1030);
        label.setBorder(BorderFactory.createEmptyBorder());
        label.setVisible(false);
        label.setBackground(new Color(32,36,39));
        label.setForeground(new Color(201,208,214));
        label.setOpaque(true);
        add(label);

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
     * 清除单件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void refresh() {
        stocksTablePanel =null;
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
