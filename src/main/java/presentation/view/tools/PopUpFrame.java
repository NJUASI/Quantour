package presentation.view.tools;

import presentation.listener.registerPanelListener.RegisterListener;
import presentation.view.frame.RegisterFrame;
import presentation.view.panel.RegisterPanel;
import presentation.view.tools.component.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by 61990 on 2017/4/15.
 */
public class PopUpFrame extends JFrame {
    private static PopUpFrame popUpFrame;
    MyLabel message;
    public PopUpFrame(String message){
        createWindow(message);
    }
    void createWindow(String message) {

        setTitle("提示！");
        ImageIcon bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo.png"));
        setIconImage(bgPicture.getImage());

        setLayout(null);
//        setUndecorated(true);
        int width=WindowData.getInstance().getWidth();
        int height=WindowData.getInstance().getHeight();

        setBounds((width-450)/2*width/1920, (height-420)/2*height/1030,
                450*width/1920, 230*height/1030);

        this.message=new MyLabel(message);
        this.message.setBounds(0*width/1920, 30*height/1030,
                450*width/1920, 100*height/1030);
        this.message.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.message);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        JButton close =new JButton("确定");
        close.setBounds(188*width/1920, 140*height/1030,80*width/1920, 30*height/1030);
        close.setForeground(new Color(255,255,255));
        close.setFont(new Font("",Font.CENTER_BASELINE,16*width/1920));
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        add(close);

    }

    public static PopUpFrame getInstance(){

        if(popUpFrame==null) {
            popUpFrame = new PopUpFrame("");
        }
        return popUpFrame;
    }

}
