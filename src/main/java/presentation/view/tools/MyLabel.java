package presentation.view.tools;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 61990 on 2017/3/17.
 */
public class MyLabel extends JLabel {
    int width,height;
    public MyLabel(String str){
        setText(str);
        width=WindowData.getInstance().getWidth();
        height=WindowData.getInstance().getHeight();
        setForeground(new Color(255,255,255));
        setFont(new Font("微软雅黑" ,Font.CENTER_BASELINE,16*width/1920));
        if(str.length()==2){
            setSize(60,35);
        }
        else if(str.length()==4){
            setSize(100,35);
        }else{

        }
    }
}
