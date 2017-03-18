package presentation.view.panel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import presentation.controller.StocksTableController;
import presentation.controller.ViewSwitchController;
import presentation.line.LineChart;
import presentation.listener.navigationBarListener.CompareListener;
import presentation.view.tools.DoubleDatePickerPanel;
import presentation.view.tools.MyLabel;
import presentation.view.util.ChartUtils;
import service.StockService;
import service.serviceImpl.StockServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.CodeNotFoundException;
import utilities.exceptions.ColorNotExistException;
import utilities.exceptions.DateNotWithinException;
import vo.ChartShowCriteriaVO;
import vo.StockSearchVO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


/**
 * Created by 61990 on 2017/3/5.
 */
public class KStringPanel extends TemplatePanel {
    //k线面板
    private static KStringPanel kStringPanel;
    public DoubleDatePickerPanel datePanel;
    JTextField searchTextField;
    JTextField num;
    JButton search;
    JButton searchAll;
    JButton compare;

    public AssociatePanel associatePanel;
    ChartPanel chartPanel = null;
    //用于更新联想面板
    public int count=0;

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
        searchTextField = new JTextField();

        num = new JTextField();
        searchAll = new JButton("总体信息");
        searchAll.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,15*width/1920));
        search = new JButton("局部信息");
        search.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,15*width/1920));
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
        tag.add(5);
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
            chartPanel.setBounds(adaptScreen(200,100,1500,850));
            chartPanel.setBackground(new Color(32, 36, 39));
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
    public void findSpecial(String code, LocalDate startDate,LocalDate endDate) throws DateNotWithinException, IOException {

        //TODO 在这儿get一个ChartPanel
        ChartShowCriteriaVO chartShowCriteriaVO=new ChartShowCriteriaVO(String.valueOf(Integer.parseInt(code)),startDate,endDate);
        // 创建图形

        ArrayList<Integer> tag = new ArrayList<Integer>();
        tag.add(5);
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

            chartPanel.setBounds(adaptScreen(200,100,1500,850));
            chartPanel.setBackground(new Color(32, 36, 39));
            add(chartPanel);
            chartPanel.repaint();
        } catch (ColorNotExistException e) {
            e.printStackTrace();
            System.out.println("该均线类型不存在"); //TODO 后期可能会更改
        } catch (CodeNotFoundException e) {
            e.printStackTrace();
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
        datePanel.setBounds(width * 100 / 1920, height * 50 / 1030, 520 * width / 1920, 35 * height / 1030);
        add(datePanel);

        MyLabel label1=new MyLabel("股票") ;
        label1.setLocation(700*width/1920,50*height/1030);
        add(label1);
        MyLabel label2=new MyLabel("代码") ;
        label2.setLocation(810*width/1920,15*height/1030);
        add(label2);
        MyLabel label3=new MyLabel("名称") ;
        label3.setLocation(960*width/1920,15*height/1030);
        add(label3);
        num.setBounds(adaptScreen(750, 50, 150, 35));
        add(num);
        num.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        //提示框面板
        searchTextField.setBounds(adaptScreen(900, 50, 150, 35));
        add(searchTextField);
        searchTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
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

                if(chartPanel!=null){
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
                associatePanel.updateText(searchTextField.getText());

                if(chartPanel!=null){
                    remove(chartPanel);
                }

                try {
                    findSpecial(num.getText(), datePanel.getStartDate(), datePanel.getEndDate());
                } catch (DateNotWithinException e1) {
                    e1.printStackTrace();
                    //TODO 需要提示数据不对
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.print("reero");

                }

            }
        });
        add(search);
        //加入比较按钮
        compare.setBounds(adaptScreen(1400, 50, 120, 35));
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ComparePanel.getInstance().count=1;
                refreshAssociate();
                ViewSwitchController.getInstance().viewSwitch("comparePanel");
                NavigationBar.getInstance().whileClicked(3);
                ComparePanel.getInstance().setCompare(searchTextField.getText(),num.getText());
                ComparePanel.getInstance().setDate(datePanel.getStartDate(),datePanel.getEndDate());
                ComparePanel.getInstance().count=0;
            }
        });
        add(compare);

//        favorite.setBounds(adaptScreen(1550, 50, 70, 35));
//        favorite.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                StockService stockService =new StockServiceImpl();
//                stockService.addPrivateStock(IDReserve.getInstance().getUserID(), num.getText());
//            }
//        });
//        add(favorite);


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
                count=1;
                StockSearchVO temp = associatePanel.getMessage();
                searchTextField.setText(temp.name);
                num.setText(temp.code);
                associatePanel.setVisible(false);
                count=0;
            }
        });
        Document dt = searchTextField.getDocument();
        dt.addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(count==0) {
                    associatePanel.setVisible(true);
                    associatePanel.setBounds(adaptScreen(800, 86, 300, 200));
                    associatePanel.updateText(searchTextField.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        Document dt1 = num.getDocument();
        dt1.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(count==0) {
                    associatePanel.setVisible(true);
                    associatePanel.setBounds(adaptScreen(800, 86, 300, 200));
                    associatePanel.updateText(num.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshAssociate();
            }
        });
        datePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
    }
    void refreshAssociate(){
        associatePanel.setVisible(false);
    }
    public void addMessage(String name, String num){
        this.searchTextField.setText(name);
        this.num.setText(num);
        associatePanel.setVisible(false);
    }
}
