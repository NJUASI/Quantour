package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 61990 on 2017/3/5.
 */
class RegisterPanel extends JPanel {
    public RegisterPanel() {
        setLayout(null);

        //the field to write your message to register
        JTextField name=new JTextField("Enter your name");
        name.setForeground(Color.GREEN);
        name.setFont(new Font("微软雅黑",Font.BOLD,25));
        name.setBounds(190,163,300,50);
//        name.setOpaque(false);
//        name.setBorder(null);
        name.setVisible(true);
        add(name);

        JTextField admin=new JTextField("Enter your Phone/Admin");
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

        JPasswordField password2=new JPasswordField();
        password2.setForeground(Color.GREEN);
        password2.setFont(new Font("微软雅黑",Font.BOLD,25));
        password2.setBounds(190,362,300,50);
//        password2.setBorder(null);
//        password2.setOpaque(false);
        password2.setVisible(true);
        add(password2);

        //the Button setting of opening login panel
        JButton openLogin =new JButton("login");
        openLogin.setBounds(210, 420, 80, 40);
        openLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCardPanel().remove(2);
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(openLogin);

        //the Button setting of register
        JButton register =new JButton("register");
        register.setBounds(330, 420, 80, 40);
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainPanel.getCardPanel().remove(2);
                MainPanel.getCard().show(MainPanel.getCardPanel(), "loginPanel");
            }
        });
        add(register);
    }
}
