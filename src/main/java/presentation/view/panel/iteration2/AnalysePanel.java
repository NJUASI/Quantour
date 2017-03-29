package presentation.view.panel.iteration2;

import presentation.view.panel.TemplatePanel;
import presentation.view.tools.MyTabUI;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

/**
 * Created by day on 17/3zx/28.
 */
public class AnalysePanel extends TemplatePanel {
    private static AnalysePanel analysePanel;
    private AnalysePanel(){
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

        //对象化面板
        JPanel p1 = new JPanel();
        JButton s1= new JButton("32");
        s1.setBounds(30,30,200,200);
        p1.setBackground(WindowData.getInstance().getColor());
        p1.setLayout(null);
        p1.add(s1);
        JPanel p2 = new JPanel();
        JButton s2= new JButton("312122");
        p2.setBackground(WindowData.getInstance().getColor());
        s2.setBounds(30,30,200,200);
        p2.setLayout(null);
        p2.add(s2);
        JPanel p3 = new JPanel();
        p3.setBackground(WindowData.getInstance().getColor());
        JPanel p4 = new JPanel();
        p4.setBackground(WindowData.getInstance().getColor());


        tab.add(p1,"Panel1");
        tab.add(p2,"Panel2");
        tab.add(p3,"Panel3");
        tab.add(p4,"Panel4");
        tab.setBounds(adaptScreen(0,0,1830,990));

        setLayout(null);
        add(tab);
        tab.setBackground(WindowData.getInstance().getColor());
        tab.setForeground(new Color(201, 208, 214));
        tab.setOpaque(true);
        tab.setUI(new MyTabUI());
        add(tab);
    }

    public static AnalysePanel getInstance(){
        if(analysePanel==null){
            analysePanel=new AnalysePanel();
        }
        return analysePanel;
    }
}
