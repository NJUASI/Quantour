package presentation.view.panel.iteration2;

import presentation.view.frame.MainFrame;
import presentation.view.panel.TemplatePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/25.
 */
public class StrategyPanel extends TemplatePanel{
    private static MainFrame mainPanel;
    private static JPanel cardPanel;
    private static CardLayout card;
    static StrategyPanel strategyPanel;
    StrategyPanel(){


        setBounds(adaptScreen(0,0,1500,930));
        setVisible(true);

        //对象实例化

        cardPanel = new JPanel();
        cardPanel.setBounds(adaptScreen(0,0,1500,930));
        add(cardPanel);
        card = new CardLayout();
        cardPanel.setLayout(card);

        JLayeredPane mainPane = new JLayeredPane();
        cardPanel.add(mainPane, "mainPane");

        ChooseStrategyPanel strategyPanel= ChooseStrategyPanel.getInstance();
        cardPanel.add(strategyPanel,"strategyPanel");
        card.show(cardPanel,"strategyPanel");
//
//        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
//        //容器
//
//        //对象化面板
////        JPanel combop = new JPanel();
//        JPanel p1 = new JPanel();
//        JPanel p2 = new JPanel();
//        JPanel p3 = new JPanel();
//        JPanel p4 = new JPanel();
//
//
//        tab.add(p1,"Select");
//        tab.add(p2,"Updata");
//        tab.add(p3,"Inserte");
//        tab.add(p4,"Delete");
//        tab.setBounds(adaptScreen(0,0,1870,1000));
//
//        setLayout(null);
//        add(tab);
//
//
//        cardPanel.add(ChooseStratage.getInstance(),"choosePanel");
//        cardPanel.add(AnalysePanel.getInstance(),"analysePanel");
//        card.show(cardPanel,"choosePanel");
    }
    public static StrategyPanel getInstance(){
        if(strategyPanel==null){
          strategyPanel=new StrategyPanel();
        }
        return strategyPanel;
    }
}
