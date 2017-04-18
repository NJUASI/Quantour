package presentation.view.panel;

import presentation.listener.comparePanelListener.*;
import presentation.listener.navigationBarListener.UserListener;
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
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by 61990 on 2017/3/5.
 */
public class ComparePanel extends TemplatePanel {

    //比较面板
    private static ComparePanel comparePanel;
    //日期选择
    DoubleDatePickerPanel datePanel;
    //股票1代码
    JTextField name1;
    //股票2编号
    JTextField num1;
    //股票1代码
    JTextField name2;
    //股票2代码
    JTextField num2;
    //比较按钮
    JButton compare;
    //中间load图标
    public JLabel logo;
    //联想面板1
    public AssociatePanel associatePanel;
    //联想面板2
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
        label.setBounds(adaptScreen(700,50,65,35));
        add(label);
        MyLabel label1=new MyLabel("股票2") ;
        label1.setBounds(adaptScreen(910,50,65,35));
        add(label1);




        num1.setBounds(adaptScreen(750, 50, 150, 35));
        add(num1);


        num2.setBounds(adaptScreen(750+210, 50, 150, 35));
        add(num2);

        setBackground(ColorUtils.backgroundColor());

        add(compare);

        addFunction();

        ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo4.png"));
        logo =new JLabel();
        bgPicture.setImage(bgPicture.getImage());
        logo.setIcon(bgPicture);
        logo.addMouseListener(new UserListener());
        logo.setBounds(850*width/1920-200,900*height/1920-200,400,400);
        add(logo);
    }
    /**
     * 改变时刷新联想
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/15
     */
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
    /**
     * 设置比较股票1
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/15
     */
    public void setCompare(String name,String num) {
//        name1.setText(name);
        num1.setText(num);
    }
    /**
     * 设置日期
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/15
     */
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
//        name1.setText(name);
        num1.setText(code+" "+name);
        associatePanel.setVisible(false);
        num1.requestFocus();
    }

    public void addMessageToGroup2(String name, String code) {
//        name2.setText(name);
        num2.setText(code+" "+name);
        associatePanel2.setVisible(false);
        num2.requestFocus();
    }


    //获取num、name的值
    public String getNum1() {
        return num1.getText().split(" ")[0];
    }

    public String getNum2() {
        return num2.getText().split(" ")[0];
    }

    public String getStockName1(){
        return name1.getText();
    }

    public String getStockName2(){
        return name2.getText();
    }

    public void updateJList1(String searchString) throws IOException {
        associatePanel.updateJList(searchString);
    }

    public void updateJList2(String searchString) throws IOException {
        associatePanel2.updateJList(searchString);
    }

    public void setAssociatePanel(){
        associatePanel.setVisible(true);
        associatePanel.setBounds(adaptScreen(750, 86, 200, 200));
    }

    public void setAssociatePanel2(){
        associatePanel2.setVisible(true);
        associatePanel2.setBounds(adaptScreen(750+210, 86, 200, 200));
    }

    public void setAssociatePanelUnvisible(){
        associatePanel.setVisible(false);
        associatePanel.setVisible(false);
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
