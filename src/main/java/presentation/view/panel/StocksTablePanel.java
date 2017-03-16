package presentation.view.panel;

import presentation.listener.stocksTablePanelListener.SearchListener;
import presentation.view.tools.SingleDatePickerPanel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/5.
 *
 * 股票列表显示面板
 */
public class StocksTablePanel extends NavigationBar {

    /**
     * The constant stocksTablePanel.
     */
    private static StocksTablePanel stocksTablePanel;

    /**
     * The Date picker panel.
     */
    SingleDatePickerPanel datePickerPanel;

    /**
     * 股票列表面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */

    public StocksTablePanel(){

        datePickerPanel = new SingleDatePickerPanel();
        datePickerPanel.setBounds(adaptScreen(500, 50,150,35));
        add(datePickerPanel);

        JButton search = new JButton("搜索");
        search.setBounds(adaptScreen(1000,50,80,40));
        search.addMouseListener(new SearchListener());
        add(search);

        JButton detailOfCode = new JButton("查看详情");
        detailOfCode.setBounds(adaptScreen(1150,50,100,40));
        detailOfCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {


            }
        });
        add(detailOfCode);


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
