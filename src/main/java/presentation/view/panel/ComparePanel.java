package presentation.view.panel;

import presentation.listener.comparePanelListener.*;
import presentation.view.panel.associatePanel.AssociatePanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.component.MyButton;
import presentation.view.tools.component.MyTextField;
import presentation.view.tools.component.datePicker.DoubleDatePickerPanel;
import presentation.view.tools.component.MyLabel;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import vo.StockComparisionVO;
import vo.StockSearchVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ComparePanel extends TemplatePanel {

    //比较面板
    private static ComparePanel comparePanel;

    DoubleDatePickerPanel datePanel;

    JTextField name1;

    JTextField num1;

    JTextField name2;

    JTextField num2;

    JButton compare;

    public AssociatePanel associatePanel;

    public AssociatePanel associatePanel2;

    ChartService chartService;

    public CompareChartPanel compareChartPanel;

    int count=0;
    /**
     * 比较面板构造器
     *
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ComparePanel() {
        init();
    }

    /**
     * 单件模式
     *
     * @return comparePanel 比较面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static ComparePanel getInstance() {
        if (comparePanel == null) {
            comparePanel = new ComparePanel();
        }
        return comparePanel;
    }

    /**
     * 添加日期选择器等各种原件
     *
     * @return
     * @author 61990
     * @updateTime 2017/3/6
     */
    private void init() {
        datePanel = new DoubleDatePickerPanel();
        datePanel.setBounds(width * 100 / 1920, height * 50 / 1030, 520 * width / 1920, 35 * height / 1030);
        add(datePanel);
        name1 = new MyTextField();
        num1 = new MyTextField();
        name2 = new MyTextField();
        num2 = new MyTextField();
        chartService =new ChartServiceImpl();
        //搜索按钮
        compare = new MyButton("比较");
        compare.setBounds(adaptScreen(1300, 50, 70, 35));
        compare.setFont(new Font("" ,Font.LAYOUT_NO_LIMIT_CONTEXT,16*width/1920));
        compare.addMouseListener(new CompareListener());

        //提示框面板
        associatePanel = new AssociatePanel();
        associatePanel.setVisible(false);
        add(associatePanel);
        associatePanel2 = new AssociatePanel();
        associatePanel2.setVisible(false);
        add(associatePanel2);
        addRefreshListener();

        MyLabel label=new MyLabel("股票1") ;
        label.setBounds(adaptScreen(700,40,65,35));
        add(label);
        MyLabel label1=new MyLabel("股票2") ;
        label1.setBounds(adaptScreen(700,80,65,35));
        add(label1);
        MyLabel label2=new MyLabel("代码") ;
        label2.setLocation(810*width/1920,5*height/1030);
        add(label2);
        MyLabel label3=new MyLabel("名称") ;
        label3.setLocation(960*width/1920,5*height/1030);
        add(label3);

        name1.setBounds(adaptScreen(900, 40, 150, 35));
        add(name1);

        num1.setBounds(adaptScreen(750, 40, 150, 35));
        add(num1);


        name2.setBounds(adaptScreen(900, 80, 150, 35));
        add(name2);

        num2.setBounds(adaptScreen(750, 80, 150, 35));
        add(num2);

        setBackground(ColorUtils.backgroundColor());

        add(compare);

        addFunction();
    }

    private void addRefreshListener() {
        name1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        num1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        name2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        num2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
    }

    public void setCompare(String name,String num) {
        name1.setText(name);
        num1.setText(num);
    }

    public void setDate(LocalDate start,LocalDate end) {
        datePanel.setDate(start,end);
    }

    /**
     * 增加提醒性的监听
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/8
     */
    void addFunction() {

        //给两个associatePanel的JList添加监听
        associatePanel.comparePanelChoose1();
        associatePanel2.comparePanelChooose2();

        num1.getDocument().addDocumentListener(new Num1ChangeListener());
        name1.getDocument().addDocumentListener(new Name1ChangeListener());

        num2.getDocument().addDocumentListener(new Num2ChangeListener());
        name2.getDocument().addDocumentListener(new Name2ChangeListener());

        refreshAssociate();
    }

    public void refreshAssociate(){
        associatePanel.setVisible(false);
        associatePanel2.setVisible(false);
    }


    //获取比较的起始日期和结束日期
    public LocalDate getStartDate(){
        return datePanel.getStartDate();
    }

    public LocalDate getEndDate(){
        return datePanel.getEndDate();
    }


    //获取associatePanel的选择信息
    public StockSearchVO getMessageByAssociatePanel1() {
        return associatePanel.getMessage();
    }

    public StockSearchVO getMessageByAssociatePanel2() {
        return associatePanel2.getMessage();
    }


    //向num、name中设置信息
    public void addMessageToGroup1(String name, String code) {
        name1.setText(name);
        num1.setText(code);
        associatePanel.setVisible(false);
        num1.requestFocus();
    }

    public void addMessageToGroup2(String name, String code) {
        name2.setText(name);
        num2.setText(code);
        associatePanel2.setVisible(false);
        num2.requestFocus();
    }


    //获取num、name的值
    public String getNum1() {
        return num1.getText();
    }

    public String getNum2() {
        return num2.getText();
    }

    public String getStockName1(){
        return name1.getText();
    }

    public String getStockName2(){
        return name2.getText();
    }

    public void updateJList1(String searchString) {
        associatePanel.updateJList(searchString);
    }

    public void updateJList2(String searchString){
        associatePanel2.updateJList(searchString);
    }

    public void setAssociatePanel(){
        associatePanel.setVisible(true);
        associatePanel.setBounds(adaptScreen(750, 75, 300, 300));
    }

    public void setAssociatePanel2(){
        associatePanel2.setVisible(true);
        associatePanel2.setBounds(adaptScreen(750, 115, 300, 300));
    }

    public CompareChartPanel getCompareChartPanel() {
        return compareChartPanel;
    }

    public void removesCompareChartPanel() {
        remove(compareChartPanel);
    }

    public void setCompareCharetPanel(List<StockComparisionVO> vo) {
        compareChartPanel=new CompareChartPanel(vo);
        compareChartPanel.setVisible(true);
        add(compareChartPanel);
        compareChartPanel.repaint();
        repaint();
    }

    public void setWarnMessageOnCompareChartPanel(String message) {
        JOptionPane.showMessageDialog(compareChartPanel,message);
    }
}
