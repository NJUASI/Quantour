package presentation.view.panel;

import presentation.view.tools.FavoritesTable;
import presentation.view.tools.SingleDatePickerPanel;
import presentation.view.tools.WindowData;
import service.StockService;
import service.serviceImpl.StockServiceImpl;
import utilities.IDReserve;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
public class FavoritesPanel extends NavigationBar {
    //自选股面板
    FavoritesTable favoritesTable;
    private static FavoritesPanel favoritesPanel;
    StockService stockService;

    SingleDatePickerPanel datePickerPanel;
    /**
     * 自选股票面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public FavoritesPanel(){
        updateText();
        stockService=new StockServiceImpl();
        WindowData windowData = WindowData.getInstance();
        int width = windowData.getWidth();
        int height =windowData.getHeight();

        datePickerPanel = new SingleDatePickerPanel();
        datePickerPanel.setBounds(adaptScreen(500, 50,150,35));
        add(datePickerPanel);

        JButton search = new JButton("查看");
        search.setBounds(adaptScreen(1000,50,80,40));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //TODO 打开均线k线面板 并初始化
                favoritesTable.getCode();
                datePickerPanel.getDate();

                MainPanel.getCard().show(MainPanel.getCardPanel(), "kStringPanel");
            }
        });
        add(search);

        JButton delete = new JButton("删除");
        delete.setBounds(adaptScreen(1150,50,80,40));
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println(favoritesTable.getCode());
                //TODO
                stockService.deletePrivateStock(IDReserve.getInstance().getUserID(),favoritesTable.getCode());
            }
        });
        add(delete);

    }
    void updateText(){

        favoritesTable=new FavoritesTable();
        favoritesTable.setLocation(300*width/1920,130*height/1030);
        add(favoritesTable);
    }

    /**
     * 单件模式
     *
     * @param
     * @return FavoritesPanel 自选股票
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static FavoritesPanel getInstance() {
        if (favoritesPanel == null) {
            favoritesPanel = new FavoritesPanel();
        }
        return favoritesPanel;
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
        favoritesPanel=null;
    }
}
