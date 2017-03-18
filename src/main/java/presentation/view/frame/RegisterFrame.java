package presentation.view.frame;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import presentation.view.panel.RegisterPanel;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by 61990 on 2017/3/16.
 */
public class RegisterFrame extends JFrame {
    private static RegisterFrame registerFrame;
    RegisterPanel registerPanel;
    public RegisterFrame(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {

        }
        createWindow();
    }
    void createWindow() {

        setTitle("注册");


        setLayout(null);
        setUndecorated(true);
        int width=WindowData.getInstance().getWidth();
        int height=WindowData.getInstance().getHeight();

        setBounds((width-450)/2*width/1920, (height-380)/2*height/1030,
                450*width/1920, 440*height/1030);
        registerPanel=RegisterPanel.getInstance();
//        setBounds(100,100,100,100);
        add(registerPanel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                registerFrame=null;
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);

    }

    public static RegisterFrame getInstance(){
        if(registerFrame==null) {
            registerFrame = new RegisterFrame();
        }
        return registerFrame;
    }
    public void refresh(){
        setVisible(false);
    }
}
