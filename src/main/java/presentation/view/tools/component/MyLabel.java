package presentation.view.tools.component;

import presentation.view.tools.ColorUtils;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/17.
 */
public class MyLabel extends JLabel {
    int width,height;
    public MyLabel(String str){
        setText(str);
        setBorder(null);
        width= WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        setForeground(ColorUtils.fontColor());
        setFont(new Font("微软雅黑" ,Font.CENTER_BASELINE,16*width/1920));
        if(str.length()==2){
            setSize(60,35);
        }else if(str.length()==3){
            setSize(80,35);
        }else if(str.length()==4){
            setSize(100,35);
        }else{

        }
    }
    public MyLabel(String str, int font){
        setText(str);
        setBorder(null);
        width=WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        setForeground(ColorUtils.fontColor());
        setFont(new Font("微软雅黑" ,Font.CENTER_BASELINE,font*width/1920));
        setVerticalAlignment(SwingConstants.CENTER);
        if(str.length()==2){
            setSize(60,40);
        }else if(str.length()==6){
            setSize(120,40);
        }else if(str.length()==4){
            setSize(100,40);
        }else{

        }
    }
}
