package presentation.view;


import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import org.jb2011.lnf.beautyeye.*;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.utils.BEUtils.*;
/**
 * Created by 61990 on 2017/3/5.
 */
public class MainPanel extends JFrame {
    //一些主原件
    private static MainPanel mainPanel;
    private static JPanel cardPanel;
    private static CardLayout card;

    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public MainPanel() {
        try
        {
            BeautyEyeLNFHelper.frameBorderStyle= BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {

        }
        createWindow();

        cardPanel = new JPanel();
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        card = new CardLayout();
        cardPanel.setLayout(card);

        JLayeredPane mainPane = new JLayeredPane();
        cardPanel.add(mainPane, "mainPane");



        //TODO 可不可以放到控制器里面
        //加载login界面
        LoginPanel loginPanel = new LoginPanel();
        cardPanel.add(loginPanel, "loginPanel");
        card.show(cardPanel, "loginPanel");
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

        setTitle("ASI");
        getContentPane().setLayout(new BorderLayout(0, 0));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        Rectangle bounds = new Rectangle(dim);

        //set the windows large
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;

        WindowData.setWindowData(bounds.width, bounds.height);//save the window's data
        setBounds(bounds);

        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setResizable(false);
    }

//    /**
//     * 单件模式
//     *
//     * @param
//     * @return
//     * @author 61990
//     * @updateTime 2017/3/5
//     */
    public static MainPanel getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainPanel();
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
