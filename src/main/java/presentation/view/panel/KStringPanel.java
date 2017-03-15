package presentation.view.panel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import presentation.line.LineChart;
import presentation.view.tools.DoubleDatePickerPanel;
import presentation.view.util.ChartUtils;
import service.StockService;
import service.serviceImpl.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.ColorNotExistException;
import vo.ChartShowCriteriaVO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.*;


/**
 * Created by 61990 on 2017/3/5.
 */
public class KStringPanel extends NavigationBar {
    //k线面板
    private static KStringPanel kStringPanel;
    DoubleDatePickerPanel datePanel;
    JTextField name;
    JTextField num;
    JButton search;
    JButton searchAll;
    JButton compare;
    JButton favorite;
    public AssociatePanel associatePanel;
    ChartPanel chartPanel;

    //由于重复添加chartPanel,故以此作为flag检测是否需要remove chartPanel
    boolean first = true;

    private LineChart lineChart;
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
     * @author 61990
     * @lastUpdated Byron Dong
     * @updateTime 2017/3/12
     */
    public void findOne(String code){

        // 创建图形
        ArrayList<Integer> tag = new ArrayList<Integer>();
        tag.add(10);
        tag.add(20);
        tag.add(30);
        tag.add(60);

        try {
            lineChart = new LineChart(code,tag, new Font("宋体",Font.BOLD,10));
            JFreeChart chart = lineChart.getAll(0.1,10,2,1);
            ChartUtils.setAntiAlias(chart);
            XYPlot xyplot = (XYPlot) chart.getPlot();
            ChartUtils.setXY_XAixs(xyplot);

            chartPanel = new ChartPanel(chart);
            chartPanel.setBounds(adaptScreen(320,100,1500,850));
            add(chartPanel);
            chartPanel.repaint();
        } catch (ColorNotExistException e) {
            e.printStackTrace();
            System.out.println("该均线类型不存在"); //TODO 后期可能会更改
        }
    }
    /**
     * 通过code和前后日期寻找股票的特定时期 寻找股票的全部信息并绘图
     *
     * @param code 股票号
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @author 61990
     * @lastUpdated Byron Dong
     * @updateTime 2017/3/12
     */
    public void findSpecial(String code, LocalDate startDate,LocalDate endDate){

        //TODO 在这儿get一个ChartPanel
        ChartShowCriteriaVO chartShowCriteriaVO=new ChartShowCriteriaVO(code,startDate,endDate);
        // 创建图形

        ArrayList<Integer> tag = new ArrayList<Integer>();
        tag.add(10);
        tag.add(20);
        tag.add(30);
        tag.add(60);

        try {
            lineChart = new LineChart(chartShowCriteriaVO,tag, new Font("宋体",Font.BOLD,10));
            JFreeChart chart = lineChart.getAll(0.1,10,2,1);
            ChartUtils.setAntiAlias(chart);
            XYPlot xyplot = (XYPlot) chart.getPlot();
            ChartUtils.setXY_XAixs(xyplot);
            chartPanel = new ChartPanel(chart);

            chartPanel.setBounds(adaptScreen(320,100,1500,850));
            add(chartPanel);
            chartPanel.repaint();
        } catch (ColorNotExistException e) {
            e.printStackTrace();
            System.out.println("该均线类型不存在"); //TODO 后期可能会更改
        }
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
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        num.setBounds(adaptScreen(1000, 50, 50, 35));
        add(num);
        num.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        //提示框面板
        associatePanel = new AssociatePanel();
        associatePanel.setVisible(false);
        add(associatePanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                associatePanel.setVisible(false);
            }
        });

        //搜索按钮
        searchAll.setBounds(adaptScreen(1130, 50, 100, 35));
        searchAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshAssociate();
                if(first == true){
                    first = false;
                }
                else {
                    remove(chartPanel);
                }

                findOne(num.getText());

            }
        });
        add(searchAll);

        //搜索按钮
        search.setBounds(adaptScreen(1230, 50, 100, 35));
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                associatePanel.setVisible(false);
                associatePanel.setBounds(adaptScreen(900, 86, 250, 200));
                associatePanel.updateText(name.getText());

                if(first == true){
                    first = false;
                }
                else {
                    remove(chartPanel);
                }

                findSpecial(num.getText(),datePanel.getStartDate(),datePanel.getEndDate());

            }
        });
        add(search);
        //加入比较按钮
        compare.setBounds(adaptScreen(1400, 50, 120, 35));
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                MainPanel.getCardPanel().add(ComparePanel.getInstance(),"comparePanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "comparePanel");
                ComparePanel.getInstance().setCompare(name.getText(),num.getText());

                ComparePanel.getInstance().associatePanel.setVisible(false);
                ComparePanel.getInstance().associatePanel2.setVisible(false);
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
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(800, 86, 250, 200));
                associatePanel.updateText(name.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        Document dt1 = num.getDocument();
        dt1.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(800, 86, 250, 200));
                associatePanel.updateText(num.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
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
        associatePanel.setVisible(false);
    }

}
