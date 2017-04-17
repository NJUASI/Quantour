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
    //注册窗口
    private static RegisterFrame registerFrame;

    //注册界面
    RegisterPanel registerPanel;
    /**
     * 构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public RegisterFrame(){

        createWindow();
    }
    /**
     * 生成窗口
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    void createWindow() {

        setTitle("注册");
        ImageIcon bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("picture/logo1.png"));
        setIconImage(bgPicture.getImage());

        setLayout(null);
//        setUndecorated(true);
        int width=WindowData.getInstance().getWidth();
        int height=WindowData.getInstance().getHeight();

        setBounds((width-450)/2*width/1920, (height-420)/2*height/1030,
                450*width/1920, 440*height/1030);

        registerPanel=RegisterPanel.getInstance();
        registerPanel.setBounds(0,0,450*width/1920, 440*height/1030);
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

    /**
     * 获得单件
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static RegisterFrame getInstance(){
        if(registerFrame==null) {
            registerFrame = new RegisterFrame();
        }
        return registerFrame;
    }
    /**
     * 设置窗口不可见
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
