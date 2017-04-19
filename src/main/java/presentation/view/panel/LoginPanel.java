package presentation.view.panel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import presentation.listener.loginPanelListener.LoginListener;
import presentation.listener.loginPanelListener.OpenRegisterListener;
import presentation.view.frame.LoginFrame;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.customizedButton.MyCustomizedButton;
import presentation.view.tools.component.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by 61990 on 2017/3/5.
 */
public class LoginPanel extends TemplatePanel {

    private static LoginPanel loginPanel;

    //用户名文本框
    private JTextField admin;

    //密码框
    private JPasswordField password;

    //注册按钮
    JButton openRegister;

    MyCustomizedButton close,min;
    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public LoginPanel() {
        setVisible(true);
        setBounds(adaptScreen(0,0,450,500));
        setBackground(new Color(47,51,55));
        //to write your message
//        admin = new JTextField("Enter your admin");

        // TODO jjjj
        admin = new JTextField("Guest");
        admin.setCaretPosition(admin.getText().length());
        admin.setFont(new Font("", Font.CENTER_BASELINE, 18 * width / 1920));
        admin.setBounds(adaptScreen(90, 200, 260, 38));
        admin.setBackground(ColorUtils.divideColor());
        admin.setVisible(true);
        add(admin);

        ImageIcon bgPicture= new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo.png"));
        JLabel logo1 =new JLabel();
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(120*width/1920, 120*height/1130, Image.SCALE_DEFAULT ));
        logo1.setIcon(bgPicture);
        logo1.setBounds(adaptScreen(170,50,120,120));
        add(logo1);

        MyLabel label = new MyLabel("账户");
        label.setLocation(35*width/1920,200*height/1030);
        add(label);
        MyLabel label2 = new MyLabel("密码");
        label2.setLocation(35*width/1920,255*height/1030);
        add(label2);

        password = new JPasswordField("qwertyuiop123456");
        password.setFont(new Font("", Font.CENTER_BASELINE, 13 * width / 1920));
        password.setBounds(adaptScreen(90, 255, 260, 38));
        password.setVisible(true);
        password.setBackground(ColorUtils.divideColor());
        add(password);

//        password.setEnabled(false);
//        admin.setEnabled(false);
        //the Button setting of logIn to mainPanel
        JButton login = new JButton("登  录");
        login.setBounds(adaptScreen(90, 340, 260, 40));

        login.setForeground(new Color(255,255,255));
        login.setFont(new Font("", Font.CENTER_BASELINE, 20 * width / 1920));
        login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        login.addMouseListener(new LoginListener());
        add(login);

        //the Button setting of opening register panel

        openRegister = new JButton("注册");
        openRegister.setFont(new Font("", Font.CENTER_BASELINE, 15 * width / 1920));
        openRegister.setBounds(adaptScreen(350, 350, 60, 35));
        openRegister.setContentAreaFilled(false);
        openRegister.setForeground(new Color(254, 104, 4));
        openRegister.addMouseListener(new OpenRegisterListener());
        add(openRegister);

        //关闭按钮
        close=new MyCustomizedButton("close",0);
        close.setBounds(adaptScreen(400,0,45,40));
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                close.moveIn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                close.moveOut();
            }
        });
        add(close);
        //最小化按钮
        min=new MyCustomizedButton("min",0);
        min.setBounds(adaptScreen(345,0,45,40));
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LoginFrame.getInstance().setExtendedState(JFrame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                min.moveIn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                min.moveOut();
            }
        });
        add(min);
    }
    /**
     * 获得单件
     *
     * @param
     * @return 登录面板
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static LoginPanel getInstance() {
        if(loginPanel == null){
            loginPanel = new LoginPanel();
        }
        return loginPanel;
    }
    /**
     * 获得用户名
     *
     * @param
     * @return  用户名
     * @author 61990
     * @updateTime 2017/3/5
     */
    public String getUsername() {
        return admin.getText();
    }
    /**
     * 获得密码
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public String getPassword() {
        return password.getText();
    }
    /**
     * 设置用户名
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void setUsername(String userName) {
        admin.setText(userName);
    }
    /**
     * 设置密码
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void setPassword(String password) {
        this.password.setText(password);
    }
}