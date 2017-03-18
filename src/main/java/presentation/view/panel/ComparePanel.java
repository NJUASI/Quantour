package presentation.view.panel;

import org.jfree.chart.ChartPanel;
import presentation.view.chart.CompareChart1;
import presentation.view.chart.CompareChartPanel;
import presentation.view.tools.DoubleDatePickerPanel;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import vo.StockComparisionVO;
import vo.StockComparsionCriteriaVO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.*;
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
    CompareChartPanel compareChartPanel;

    /**
     * 比较面板构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public ComparePanel() {
        init();
    }

    /**
     * 单件模式
     *
     * @param
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
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/6
     */
    private void init() {
        datePanel = new DoubleDatePickerPanel();
        datePanel.setBounds(width * 350 / 1920, height * 50 / 1030, 385 * width / 1920, 35 * height / 1030);
        add(datePanel);
        name1 = new JTextField();
        num1 = new JTextField();
        name2 = new JTextField();
        num2 = new JTextField();
        chartService =new ChartServiceImpl();
        //搜索按钮
        compare = new JButton("比较");
        compare.setBounds(adaptScreen(1300, 50, 70, 35));
        compare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                compareSpecial(num1.getText(),num2.getText(),datePanel.getStartDate(),datePanel.getEndDate());
                refreshAssociate();
            }
        });

        //提示框面板
        associatePanel = new AssociatePanel();
        associatePanel.setVisible(false);
        add(associatePanel);
        associatePanel2 = new AssociatePanel();
        associatePanel2.setVisible(false);
        add(associatePanel2);
        addRefreshListener();
        name1.setBounds(adaptScreen(900, 30, 150, 35));
        add(name1);

        num1.setBounds(adaptScreen(1100, 30, 50, 35));
        add(num1);


        name2.setBounds(adaptScreen(900, 70, 150, 35));
        add(name2);

        num2.setBounds(adaptScreen(1100, 70, 50, 35));
        add(num2);


        add(compare);

//        add(bg);
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
                super.mousePressed(e);
            }
        });
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
        comparePanel = null;
    }
    public void setCompare(String name,String num) {
        name1.setText(name);
        num1.setText(num);
    }
    /**
     * 通过code寻找股票的全部信息
     *
     * @param code1 股票号1
     * @param code2 股票号2
     * @return
     * @author 61990
     * @updateTime 2017/3/10
     */
    public void CompareAll(String code1,String code2){
        // 创建图形
//        chartPanel = new ().createChart();
    }
    /**
     * 通过code和前后日期寻找股票的特定时期 寻找股票的全部信息并绘图
     *
     * @param code1 股票号1
     * @param code2 股票号2
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @author 61990
     * @updateTime 2017/3/9
     */
    boolean first=true;
    public void compareSpecial(String code1,String code2, LocalDate startDate, LocalDate endDate){
        try {
            chartService=new ChartServiceImpl();
            System.out.println("22");
            List<StockComparisionVO> vo=chartService.getComparision(new StockComparsionCriteriaVO(code1, code2, startDate, endDate));
            System.out.println("33");
            if(first){
                first=false;
            }else {
                remove(compareChartPanel);
            }
            compareChartPanel=new CompareChartPanel(vo);
            compareChartPanel.setBounds(adaptScreen(200,130,1500,800));
            add(compareChartPanel);
            compareChartPanel.setVisible(true);
            compareChartPanel.repaint();
            repaint();

            compareChartPanel.requestFocus();
            compareChartPanel.s.requestFocus();
        }catch (Exception e){
            System.out.println("1234");
        }
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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refreshAssociate();
            }
        });
        associatePanel.list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                name1.setText(associatePanel.getMessage().name);
                associatePanel.setVisible(false);
                name1.requestFocus();
            }
        });
        associatePanel2.list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                name2.setText(associatePanel2.getMessage().name);
                associatePanel2.setVisible(false);
                name2.requestFocus();
            }
        });
        Document dt1 = num1.getDocument();
        dt1.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(900, 66, 250, 200));
                associatePanel.updateText(num1.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        });
        Document dt2 = name1.getDocument();
        dt2.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel.setVisible(true);
                associatePanel.setBounds(adaptScreen(900, 66, 250, 200));
                associatePanel.updateText(num1.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        });
        Document dt3 = name2.getDocument();
        dt3.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel2.setVisible(true);
                associatePanel2.setBounds(adaptScreen(900, 105, 250, 200));
                associatePanel2.updateText(name2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        });
        Document dt4 = num2.getDocument();
        dt4.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                associatePanel2.setVisible(true);
                associatePanel2.setBounds(adaptScreen(900, 105, 250, 200));
                associatePanel2.updateText(num2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                associatePanel2.setVisible(true);
                associatePanel2.setBounds(adaptScreen(900, 66, 250, 200));
                associatePanel2.updateText(num2.getText());
            }
        });
        refreshAssociate();
    }
    void refreshAssociate(){
        associatePanel.setVisible(false);
        associatePanel2.setVisible(false);
    }

    public String getNum1() {
        return num1.getText();
    }

    public String getNum2() {
        return num2.getText();
    }

    public LocalDate getStartDate(){
        return datePanel.getStartDate();
    }

    public LocalDate getEndDate(){
        return datePanel.getEndDate();
    }
}
