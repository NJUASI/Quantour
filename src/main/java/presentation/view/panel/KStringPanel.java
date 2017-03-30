package presentation.view.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import presentation.chart.candlestick.CandlestickChart;
import presentation.controller.KStringPanelController;
import presentation.controller.ViewSwitchController;

import presentation.listener.kStringPanelListener.SearchAllListener;
import presentation.listener.kStringPanelListener.SearchListener;
import presentation.listener.kStringPanelListener.StockCodeDocListener;
import presentation.listener.kStringPanelListener.StockNameDocListener;
import presentation.view.panel.associatePanel.AssociatePanel;
import presentation.view.tools.DoubleDatePickerPanel;
import presentation.view.tools.MyLabel;

import utilities.exceptions.ColorNotExistException;
import vo.StockSearchVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;


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
    public Panel chartPanel = null;
    //用于更新联想面板
    public int count=0;

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
        num.setText("");
        searchAll = new JButton("总体信息");
        searchAll.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        search = new JButton("局部信息");
        search.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        compare = new JButton("加入比较");
        compare.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        init();
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

        associatePanel = new AssociatePanel();
        associatePanel.setVisible(false);
        add(associatePanel);


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

        datePanel.startDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                KStringPanelController.getInstance().search();
            }
        });
        datePanel.endDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                KStringPanelController.getInstance().search();
            }
        });


        //TODO gcm 这里我想加一个比较列表，而不是直接跳转到比较界面，比较列表在旁边显示出来，以便后面添加多只股票比较
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
     * 单件模式
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

        searchTextField.getDocument().addDocumentListener(new StockNameDocListener());

        num.getDocument().addDocumentListener(new StockCodeDocListener());

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

    public void warnMessageOnKStringPanel(String str) {
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

    public void setAssociatePanelBounds(){
        associatePanel.setBounds(adaptScreen(750, 86, 300, 200));
    }

    public void associatePanelSetting() {
        setAssociatePanelVisible();
        setAssociatePanelBounds();
    }

    public void updateAssociateJList(String searchString) {
        associatePanel.updateJList(searchString);
    }

    public void setChartPanel(CandlestickChart candlestickChart) {
        try {
            chartPanel = candlestickChart.createAllPanel();
        } catch (ColorNotExistException e) {
            System.out.println("该均线类型不存在"); //TODO 后期可能会更改
            e.printStackTrace();
        }
        chartPanel.setBounds(adaptScreen(130,100,1620,850));
        chartPanel.setBackground(new Color(32, 36, 39));
        add(chartPanel);
        chartPanel.repaint();
    }
}
