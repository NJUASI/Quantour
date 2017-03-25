package presentation.view.panel;

import presentation.chart.CandlestickChart;
import presentation.controller.ViewSwitchController;

import presentation.listener.kStringPanelListener.SearchAllListener;
import presentation.listener.kStringPanelListener.SearchListener;
import presentation.view.tools.DoubleDatePickerPanel;
import presentation.view.tools.MyLabel;

import utilities.exceptions.*;
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

    //日期选择器
    public DoubleDatePickerPanel datePanel;

    //股票名称搜索框
    JTextField searchTextField;

    //股票代号搜索框
    JTextField num;

    //局部信息
    JButton search;

    //总体信息
    JButton searchAll;

    //
    JButton compare;

    public AssociatePanel associatePanel;
    Panel chartPanel = null;
    //用于更新联想面板
    public int count=0;

    private CandlestickChart candlestickChart;

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
        searchAll.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        search = new JButton("局部信息");
        search.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        compare = new JButton("加入比较");
        compare.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
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
            candlestickChart = new CandlestickChart(code,tag);

            chartPanel = candlestickChart.createAllPanel();
            chartPanel.setBounds(adaptScreen(130,100,1600,850));
            chartPanel.setBackground(new Color(32, 36, 39));
            add(chartPanel);
            chartPanel.repaint();
        } catch (ColorNotExistException e) {
            e.printStackTrace();
            System.out.println("该均线类型不存在"); //TODO 后期可能会更改
        } catch (CodeNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            candlestickChart = new CandlestickChart(chartShowCriteriaVO,tag);
            chartPanel = candlestickChart.createAllPanel();

            chartPanel.setBounds(adaptScreen(130,100,1600,850));
            chartPanel.setBackground(new Color(32, 36, 39));
            add(chartPanel);
            chartPanel.repaint();
        } catch (ColorNotExistException e) {
            e.printStackTrace();
            System.out.println("该均线类型不存在"); //TODO 后期可能会更改
        } catch (CodeNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(chartPanel,"请输入股票代号");
        } catch (NoDataWithinException e) {
            JOptionPane.showMessageDialog(chartPanel,e.getMessage());
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
        //TODO gcm 给日期选择器加上监听，当选择日期选择完毕的时候，直接调出数据，而不需要用户再去按一下局部信息

        MyLabel label1=new MyLabel("股票") ;
        label1.setLocation(700*width/1920,50*height/1030);
        add(label1);
        MyLabel label2=new MyLabel("代码") ;
        label2.setLocation(810*width/1920,15*height/1030);
        add(label2);
        MyLabel label3=new MyLabel("名称") ;
        label3.setLocation(960*width/1920,15*height/1030);
        add(label3);
        
        //股票代码框
        num.setBounds(adaptScreen(750, 50, 150, 35));
        add(num);
        num.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setAssociatePanelUnvisible();
            }
        });
        
        //股票名称框
        searchTextField.setBounds(adaptScreen(900, 50, 150, 35));
        add(searchTextField);
        searchTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setAssociatePanelUnvisible();
            }
        });

        associatePanel = new AssociatePanel();
        associatePanel.setVisible(false);
        add(associatePanel);

        //隐藏联想面板
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                associatePanel.setVisible(false);
            }
        });

        //搜索按钮
        searchAll.setBounds(adaptScreen(1130, 50, 100, 35));
        searchAll.addMouseListener(new SearchAllListener());
        add(searchAll);

        //搜索按钮
        search.setBounds(adaptScreen(1230, 50, 100, 35));
        search.addMouseListener(new SearchListener());
        add(search);

        //加入比较按钮
        compare.setBounds(adaptScreen(1400, 50, 120, 35));

        //TODO gcm
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ComparePanel.getInstance().count=1;
                setAssociatePanelUnvisible();
                ViewSwitchController.getInstance().viewSwitch("comparePanel");
                NavigationBar.getInstance().whileClicked(3);
                ComparePanel.getInstance().setCompare(searchTextField.getText(),num.getText());
                ComparePanel.getInstance().setDate(datePanel.getStartDate(),datePanel.getEndDate());
                ComparePanel.getInstance().count=0;
            }
        });
        add(compare);

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

        //在联想面板上添加KStringPanel的监听
        associatePanel.kStringPanelChoose();

        Document dt = searchTextField.getDocument();
        dt.addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(750, 86, 300, 200));
                associatePanel.updateJList(getStockName());
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
                    associatePanel.setBounds(adaptScreen(750, 86, 300, 200));

                    associatePanel.updateJList(num.getText());
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
                setAssociatePanelUnvisible();
            }
        });
        datePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setAssociatePanelUnvisible();
            }
        });
    }


    //更新股票名称框和代码框
    public void addMessage(String name, String num){
        this.searchTextField.setText(name);
        this.num.setText(num);
        associatePanel.setVisible(false);
    }
    
    //获取股票代码
    public String getStockCode(){
        return num.getText();
    }

    //获取股票名称
    public String getStockName(){
        return searchTextField.getText();
    }

    //对联想面板的操作
    public void setAssociatePanelUnvisible(){
        associatePanel.setVisible(false);
    }

    public void setAssociatePanelVisible(){
        associatePanel.setVisible(true);
    }

    public Panel getChartPanel() {
        return chartPanel;
    }

    public void removeChartPanel() {
        remove(chartPanel);
    }

    public void warnMessage(String str) {
        JOptionPane.showMessageDialog(kStringPanel.getChartPanel(),str);
    }

    public LocalDate getStartDate(){
        return datePanel.getStartDate();
    }

    public LocalDate getEndDate(){
        return datePanel.getEndDate();
    }

    public void setStockCode(String code){
        num.setText(code);
    }

    public StockSearchVO getMessage() {
        return associatePanel.getMessage();
    }
}
