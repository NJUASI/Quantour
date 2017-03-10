package presentation.view;

import service.StockService;
import service.serviceImpl.StockServiceImpl;
import utilities.IDReserve;
import utilities.enums.Market;
import vo.StockVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

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
        datePickerPanel.setBounds(adaptScreen(1150, 300,150,35));
        add(datePickerPanel);

        JButton search = new JButton("查看");
        search.setBounds(adaptScreen(1150,400,80,40));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "kStringPanel");
            }
        });
        add(search);

        JButton delete = new JButton("删除");
        delete.setBounds(adaptScreen(1150,460,80,40));
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

//                stockService.deletePrivateStock(IDReserve.getInstance().getUserID(),list.getSelectedValue().toString());
            }
        });
        add(delete);

    }
    void updateText(){

        favoritesTable=new FavoritesTable();
        favoritesTable.setLocation(400,400);
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
