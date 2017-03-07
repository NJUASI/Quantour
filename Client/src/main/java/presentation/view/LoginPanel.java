package presentation.view;

import utilities.IDReserve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by 61990 on 2017/3/5.
 */
class LoginPanel extends TempletPanel{
    //注册面板
    RegisterPanel registerPanel;
    //功能菜单
    MenuPanel menuPanel;
    //屏幕大小
//    WindowData windowData;
//    int width;
//    int height;
    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public LoginPanel() {
        //to write your message
        JTextField admin=new JTextField("Enter your admin");
        admin.setFont(new Font("微软雅黑",Font.BOLD,25));
        admin.setBounds(adaptScreen(300,400,300,50));
//        admin.setOpaque(false);
//        admin.setBorder(null);
//        admin.setForeground(new Color(122,2,2));
        admin.setVisible(true);
        add(admin);

        JPasswordField password=new JPasswordField();
        password.setFont(new Font("微软雅黑",Font.BOLD,25));
        password.setBounds(adaptScreen(300,500,300,50));

//        password.setBorder(null);
//        password.setOpaque(false);
        password.setVisible(true);
        add(password);

        //the Button setting of login to mainPanel
        JButton login =new JButton("登录");
        login.setBounds(adaptScreen(360,600,80,40));
        login.setFont(new Font("",Font.CENTER_BASELINE,20));
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
        //TODO login
//                    admin.getText();
//                    password.getText();
                    IDReserve.getInstance().setUserID(admin.getText());
                }catch (Exception el){

                }
                admin.setText("");
                password.setText("");
                menuPanel=new MenuPanel();
                MainPanel.getCardPanel().add(menuPanel, "menuPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "menuPanel");
            }
        });
        add(login);


        //the Button setting of opening register panel
        JButton openRegister =new JButton("注册");
        openRegister.setForeground(Color.BLACK);
        openRegister.setFont(new Font("",Font.CENTER_BASELINE,15));
        openRegister.setBounds(adaptScreen(470,610,60,33));
        openRegister.setContentAreaFilled(false);
        openRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                registerPanel=new RegisterPanel();
                MainPanel.getCardPanel().add(registerPanel, "registerPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "registerPanel");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                openRegister.setFont(new Font("",Font.HANGING_BASELINE,15));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                openRegister.setFont(new Font("",Font.CENTER_BASELINE,15));
            }
        });
        add(openRegister);
        add(bg);
    }
}
