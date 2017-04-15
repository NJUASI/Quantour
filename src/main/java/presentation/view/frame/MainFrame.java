package presentation.view.frame;


import presentation.controller.ViewSwitchController;
import presentation.view.panel.NavigationBar;
import presentation.view.panel.TitlePanel;
import presentation.view.tools.BeautyEyeUtil;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/5.
 */
public class MainFrame extends JFrame {

    //一些主原件
    private static MainFrame mainPanel;
    private static JPanel cardPanel;
    public static JPanel barPanel,titlePanel;
    private static CardLayout card;
    int width,height;

    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public MainFrame() {

        //osLookAndDecorated
        BeautyEyeUtil.beautyEye();

        createWindow();
        setLayout(null);

        ImageIcon bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo1.png"));
        setIconImage(bgPicture.getImage());

        barPanel =  NavigationBar.getInstance();
        add(barPanel);

        titlePanel= TitlePanel.getInstance();
        add(titlePanel);

        cardPanel = new JPanel();
        cardPanel.setBounds(90*width/1920,40*height/1030,1920*width/1920,1030*height/1030);
        add(cardPanel);
        card = new CardLayout();
        cardPanel.setLayout(card);

        JLayeredPane mainPane = new JLayeredPane();
        cardPanel.add(mainPane, "mainPane");

        //进入股票信息界面
        ViewSwitchController.getInstance().viewSwitch("userPanel");


    }

    /**
     * 创建窗口
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    private void createWindow() {

        setTitle("Quantourist");
        getContentPane().setLayout(new BorderLayout(0, 0));

        width=WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        setSize(width,height);

//        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setResizable(false);
    }

    /**
     * 单件模式
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static MainFrame getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainFrame();
        }
        return mainPanel;
    }

    /**
     * @param
     * @return cardPanel
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static JPanel getCardPanel() {
        return cardPanel;
    }

    /**
     * @param
     * @return card
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static CardLayout getCard() {
        return card;
    }
}
