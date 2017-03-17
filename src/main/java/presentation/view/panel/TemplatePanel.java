package presentation.view.panel;

import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/7.
 */
public class TemplatePanel extends JPanel {
    WindowData windowData;
    int width;
    int height;
    //背景图片
    JLabel bg1;
    /**
     * 父类构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/7
     */
    public TemplatePanel(){
        windowData = WindowData.getInstance();
        width = windowData.getWidth();
        height =windowData.getHeight();
        setLayout(null);
        setBackground(WindowData.getInstance().getColor());
        setBackground(new Color(32,36,39));

//        ImageIcon bgPicture =new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("bg.png"));
//        bgPicture.setImage(bgPicture.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
//        bg1= new JLabel(bgPicture);
//        bg1.setBounds(0,0,width,height);
//        add(bg1);
//        bg1.setOpaque(true);
//        bg1.setBackground(new Color(55,60,56));
    }

    /**
     * 适应不同大小的屏幕
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/7
     */
    public Rectangle adaptScreen(int x,int y,int width,int height){
        return new Rectangle(this.width*x/1920,this.height*y/1030,this.width*width/1920,this.height*height/1030);
    }

}
