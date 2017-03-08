package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class RegisterPanel extends TempletPanel {
    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public RegisterPanel() {
        //the field to write your message to register
        JTextField admin=new JTextField("your Phone/Admin");
        admin.setFont(new Font("微软雅黑",Font.BOLD,25*width/1920));
        admin.setBounds(adaptScreen(300,340,300,50));
//        admin.setOpaque(false);
//        admin.setBorder(null);
        admin.setVisible(true);
        add(admin);


        JPasswordField password=new JPasswordField();
        password.setFont(new Font("微软雅黑",Font.BOLD,25*width/1920));
        password.setBounds(adaptScreen(300,420,300,50));
//        password.setBorder(null);
//        password.setOpaque(false);
        password.setVisible(true);
        add(password);

        JPasswordField password2=new JPasswordField();
        password2.setFont(new Font("微软雅黑",Font.BOLD,25*width/1920));
        password2.setBounds(adaptScreen(300,500,300,50));
//        password2.setBorder(null);
//        password2.setOpaque(false);
        password2.setVisible(true);
        add(password2);

        //the Button setting of opening login panel
        JButton openLogin =new JButton("登录");
        openLogin.setBounds(adaptScreen(470,610,60,30));
        openLogin.setForeground(Color.BLACK);
        openLogin.setFont(new Font("",Font.CENTER_BASELINE,15));
        openLogin.setContentAreaFilled(false);
        openLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCardPanel().remove(2);
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(openLogin);

        //the Button setting of register
        JButton register =new JButton("注册");
        register.setBounds(adaptScreen(360,600,80,40));
        register.setFont(new Font("",Font.CENTER_BASELINE,20));
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCardPanel().remove(2);
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(register);
        add(bg);
    }
}
