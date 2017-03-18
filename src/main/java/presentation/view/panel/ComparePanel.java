package presentation.view.panel;

import org.jfree.chart.ChartPanel;
import presentation.view.chart.CompareChart1;
import presentation.view.chart.CompareChartPanel;
import presentation.view.tools.DoubleDatePickerPanel;
import presentation.view.tools.MyLabel;
import service.ChartService;
import service.serviceImpl.ChartServiceImpl;
import vo.StockComparisionVO;
import vo.StockComparsionCriteriaVO;
import vo.StockSearchVO;

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


        name2.setBounds(adaptScreen(750, 80, 150, 35));
        add(name2);

        num2.setBounds(adaptScreen(900, 80, 150, 35));
        add(num2);

        setBackground(new Color(32,36,39));

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
    public void setDate(LocalDate start,LocalDate end) {
        datePanel.setDate(start,end);
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
            List<StockComparisionVO> vo=chartService.getComparision(new StockComparsionCriteriaVO(code1, code2, startDate, endDate));
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
        }catch (Exception e){
            first=true;
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
                count=1;
                StockSearchVO temp = associatePanel.getMessage();
                name1.setText(temp.name);
                num1.setText(temp.code);
                associatePanel.setVisible(false);
                num1.requestFocus();
                count=0;
            }
        });
        associatePanel2.list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                count=1;
                StockSearchVO temp = associatePanel2.getMessage();
                name2.setText(temp.name);
                num2.setText(temp.code);
                associatePanel2.setVisible(false);
                num2.requestFocus();
                count=0;
            }
        });
        Document dt1 = num1.getDocument();
        dt1.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(count==0) {
                    associatePanel.setVisible(true);
                    associatePanel.setBounds(adaptScreen(900, 66, 300, 200));
                    associatePanel.updateText(num1.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }
        });
        Document dt2 = name1.getDocument();
        dt2.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(count==0) {
                    associatePanel.setVisible(true);
                    associatePanel.setBounds(adaptScreen(900, 66, 300, 200));
                     associatePanel.updateText(name1.getText());
                }
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
                if(count==0) {
                    associatePanel2.setVisible(true);
                    associatePanel2.setBounds(adaptScreen(900, 105, 300, 200));
                    associatePanel2.updateText(name2.getText());
                }
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
                if (count==0){
                    associatePanel2.setVisible(true);
                    associatePanel2.setBounds(adaptScreen(900, 105, 300, 200));
                    associatePanel2.updateText(num2.getText());
                }
        }

            @Override
            public void removeUpdate(DocumentEvent e) {

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
