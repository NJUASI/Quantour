package presentation.view.frame;

import presentation.view.panel.LoginPanel;
import presentation.view.tools.BeautyEyeUtil;
import presentation.view.tools.component.datePicker.DoubleDatePickerPanel;
import presentation.view.tools.component.datePicker.SingleDatePickerPanel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/16.
 */
public class LoginFrame extends JFrame {

    LoginPanel loginPanel;
    //单例
    private static LoginFrame loginFrame;

    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public LoginFrame() {

        //osLookAndDecorated
        BeautyEyeUtil.beautyEye();

        createWindow();
    }
    /**
     * 生成登录框
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    void createWindow() {

        setTitle("登录");

        ImageIcon bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo.png"));
        setIconImage(bgPicture.getImage());

        setLayout(new BorderLayout(0, 0));

        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        Rectangle bounds = new Rectangle(dim);

        //set the windows large
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;

        WindowData.setWindowData(bounds.width, bounds.height);//save the window's data
        setBounds((bounds.width-450)/2*bounds.width/1920, (bounds.height-500)/2*bounds.height/1030,
                450*bounds.width/1920, 500*bounds.height/1030);
        loginPanel=LoginPanel.getInstance();

        add(loginPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        setResizable(false);
        new SingleDatePickerPanel();
        new DoubleDatePickerPanel();
    }

    /**
     * 获得单例
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static LoginFrame getInstance(){
        if(loginFrame==null) {
            loginFrame = new LoginFrame();
        }
        return loginFrame;
    }
    /**
     * 设置不可见
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void refresh(){
        setVisible(false);
    }
}
