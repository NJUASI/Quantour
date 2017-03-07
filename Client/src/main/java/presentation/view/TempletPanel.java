package presentation.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/7.
 */
public class TempletPanel extends JPanel {
    WindowData windowData;
    int width;
    int height;
    //背景图片
    JLabel bg;
    /**
     * 父类构造器
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/7
     */
    public TempletPanel(){
        WindowData windowData = WindowData.getInstance();

        width = windowData.getWidth();
        height =windowData.getHeight();
        setLayout(null);
        ImageIcon bgPicture =new ImageIcon(getClass().getClassLoader().getResource("bg.png"));
        bgPicture.setImage(bgPicture.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        bg= new JLabel(bgPicture);
        bg.setBounds(0,0,width,height);
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
