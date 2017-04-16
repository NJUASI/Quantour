package presentation.view.panel;

import presentation.view.tools.BeautyEyeUtil;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/7.
 */
public class TemplatePanel extends JPanel {
    WindowData windowData;
    public int width;
    public int height;

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
        setBackground(ColorUtils.backgroundColor());


        //osLookAndDecorated
        BeautyEyeUtil.beautyEye();

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
