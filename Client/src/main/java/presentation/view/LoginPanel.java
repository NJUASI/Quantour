package presentation.view;

import com.sun.xml.internal.bind.v2.model.core.ID;
import utilities.IDReserve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by 61990 on 2017/3/5.
 */
class LoginPanel extends JPanel {
    //注册面板
    RegisterPanel registerPanel;
    //功能菜单
    MenuPanel menuPanel;

    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public LoginPanel() {
        setLayout(null);

        WindowData windowData = WindowData.getInstance();
        System.out.println(windowData.getHeight()+windowData.getWidth());
        //to write your message
        JTextField admin=new JTextField("Enter your admin");
        admin.setForeground(Color.GREEN);
        admin.setFont(new Font("微软雅黑",Font.BOLD,25));
        admin.setBounds(190,203,300,50);
//        admin.setOpaque(false);
//        admin.setBorder(null);
        admin.setVisible(true);
        add(admin);

        JPasswordField password=new JPasswordField();
        password.setForeground(Color.GREEN);
        password.setFont(new Font("微软雅黑",Font.BOLD,25));
        password.setBounds(190,282,300,50);
//        password.setBorder(null);
//        password.setOpaque(false);
        password.setVisible(true);
        add(password);

        //the Button setting of login to mainPanel
        JButton login =new JButton("login");
        login.setBounds(210, 360, 80, 40);
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
        JButton openRegister =new JButton("register");
        openRegister.setBounds(330, 360, 80, 40);
        openRegister.setContentAreaFilled(false);
        openRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                registerPanel=new RegisterPanel();
                MainPanel.getCardPanel().add(registerPanel, "registerPanel");
                MainPanel.getCard().show(MainPanel.getCardPanel(), "registerPanel");
            }
        });
        add(openRegister);
    }
}
