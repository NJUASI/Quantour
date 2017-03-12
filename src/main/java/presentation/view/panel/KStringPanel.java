package presentation.view.panel;

import org.jfree.chart.ChartPanel;
import presentation.view.toos.DoubleDatePickerPanel;
import presentation.view.chart.KStringChart;
import service.StockService;
import service.serviceImpl.StockServiceImpl;
import utilities.IDReserve;
import vo.ChartShowCriteriaVO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;


/**
 * Created by 61990 on 2017/3/5.
 */
public class KStringPanel extends NavigationBar {
    //k线面板
    private static KStringPanel kStringPanel;

    //日期选择器面板
    DoubleDatePickerPanel datePanel;

    //
    JTextField name;

    //
    JTextField num;

    //局部信息
    JButton search;

    //总体信息
    JButton searchAll;

    //比较按钮
    JButton compare;

    //自选股按钮
    JButton favorite;

    //提示框面板
    AssociatePanel associatePanel;


    ChartPanel chartPanel;

    /**
     * k线面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public KStringPanel() {
        //初始化datePicker
        name = new JTextField();
        num = new JTextField();
        searchAll = new JButton("总体信息");
        search = new JButton("局部信息");
        favorite = new JButton("收藏");
        compare = new JButton("加入比较");
        init();
    }

    /**
     * 通过code寻找股票的全部信息并绘图
     *
     * @param code 股票号
     * @return
     * @author 61990
     * @updateTime 2017/3/9
     */
    public void findOne(String code){
        // 创建图形
        chartPanel = new KStringChart().createChart();
        chartPanel.setBounds(adaptScreen(320,100,1500,850));
        add(chartPanel);
        chartPanel.repaint();
    }

    /**
     * 通过code和前后日期寻找股票的特定时期 寻找股票的全部信息并绘图
     *
     * @param code 股票号
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @author 61990
     * @updateTime 2017/3/9
     */
    public void findSpecial(String code, LocalDate startDate,LocalDate endDate){

        //TODO 在这儿get一个ChartPanel
        ChartShowCriteriaVO chartShowCriteriaVO=new ChartShowCriteriaVO(code,startDate,endDate);
        // 创建图形
        chartPanel = new KStringChart().createChart();
        chartPanel.setBounds(adaptScreen(320,100,1500,850));
        add(chartPanel);
        chartPanel.repaint();
    }
    /**
     * 添加日期选择器等各种原件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/6
     */
    private void init() {
        datePanel = new DoubleDatePickerPanel();
        datePanel.setBounds(width * 400 / 1920, height * 50 / 1030, 370 * width / 1920, 35 * height / 1030);
        add(datePanel);

        name.setBounds(adaptScreen(800, 50, 150, 35));
        add(name);

        num.setBounds(adaptScreen(1000, 50, 50, 35));
        add(num);

        //提示框面板
        associatePanel = new AssociatePanel();
        associatePanel.setVisible(false);
        add(associatePanel);

        //搜索按钮
        searchAll.setBounds(adaptScreen(1130, 50, 100, 35));
        searchAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                findOne(num.getText());

            }
        });
        add(searchAll);

        //搜索按钮
        search.setBounds(adaptScreen(1230, 50, 100, 35));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                findSpecial(num.getText(),datePanel.getStartDate(),datePanel.getEndDate());

            }
        });
        add(search);

        //加入比较按钮
        compare.setBounds(adaptScreen(1400, 50, 120, 35));
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainPanel.getCard().show(MainPanel.getCardPanel(), "comparePanel");
                ComparePanel.getInstance().setCompare(name.getText(),num.getText());
            }
        });
        add(compare);

        favorite.setBounds(adaptScreen(1550, 50, 70, 35));
        favorite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StockService stockService =new StockServiceImpl();
                stockService.addPrivateStock(IDReserve.getInstance().getUserID(), num.getText());
            }
        });
        add(favorite);


        addFunction();

    }

    /**
     * 单间模式
     *
     * @param
     * @return KStringPanel K线面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static KStringPanel getInstance() {
        if (kStringPanel == null) {
            kStringPanel = new KStringPanel();
        }
        return kStringPanel;
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
        kStringPanel = null;
    }

    /**
     * 增加提示性的监听
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/8
     */
    void addFunction() {
        associatePanel.list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                name.setText(associatePanel.getMessage());
                associatePanel.setVisible(false);
            }
        });
        Document dt = name.getDocument();
        dt.addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                associatePanel.setVisible(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(900, 86, 250, 200));
                associatePanel.updateText(name.getText());
            }
        });
        Document dt1 = num.getDocument();
        dt1.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(900, 86, 250, 200));
                associatePanel.updateText(num.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                associatePanel.setVisible(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshAssociate();
            }
        });
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshAssociate();
            }
        });
        favorite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshAssociate();
            }
        });
    }
    void refreshAssociate(){
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                associatePanel.setVisible(false);
            }
        });
        num.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                associatePanel.setVisible(false);
            }
        });
    }

}