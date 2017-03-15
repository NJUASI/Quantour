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
import java.time.LocalDate;

/**
 * Created by 61990 on 2017/3/5.
 */
public class FavoritesPanel extends NavigationBar {
    //自选股面板
    FavoritesTable favoritesTable;
    private static FavoritesPanel favoritesPanel;
    StockService stockService;
    boolean first=true;
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
//        updateText();
        WindowData windowData = WindowData.getInstance();
        int width = windowData.getWidth();
        int height =windowData.getHeight();

        datePickerPanel = new SingleDatePickerPanel();
        datePickerPanel.setBounds(adaptScreen(500, 50,150,35));
        add(datePickerPanel);

        JButton search = new JButton("搜索");
        search.setBounds(adaptScreen(1000,50,80,40));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 updateText();
            }
        });
        add(search);

        JButton detailOfCode = new JButton("查看详情");
        detailOfCode.setBounds(adaptScreen(1150,50,80,40));
        detailOfCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCardPanel().add(KStringPanel.getInstance(),"kStringPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "kStringPanel");
                KStringPanel.getInstance().datePanel.setDate(datePickerPanel.getDate());
                KStringPanel.getInstance().addMessage(favoritesTable.getName(),favoritesTable.getCode());

            }
        });
        add(detailOfCode);


    }
    void updateText(){
        if(first == true){
            first = false;
            favoritesTable = new FavoritesTable(datePickerPanel.getDate());
            favoritesTable.setLocation(300*width/1920,130*height/1030);
            add(favoritesTable);
            favoritesTable.repaint();
        }
        else {
            remove(favoritesTable);
            favoritesTable = new FavoritesTable(datePickerPanel.getDate());
            favoritesTable.setLocation(300*width/1920,130*height/1030);
            add(favoritesTable);
            favoritesTable.repaint();
        }

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
