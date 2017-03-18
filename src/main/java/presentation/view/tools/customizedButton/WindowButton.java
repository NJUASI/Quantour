package presentation.view.tools.customizedButton;

import presentation.view.tools.ButtonImageLoad;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harvey on 2017/3/17.
 */
public class WindowButton extends JLabel {

    int x=40;
    int y=35;

    public WindowButton(String name) {

        //设置大小
        int width= WindowData.getInstance().getWidth();
        int height=WindowData.getInstance().getHeight();
        setSize(x*width/1920, y*height/1030);

        //加载图片
        loadIcon(name);

    }

    public void loadIcon(String name) {
        if(name.equals("close")){
            setIcon(ButtonImageLoad.loadWindowBt("close1"));
        }
        else if(name.equals("min")){
            setIcon(ButtonImageLoad.loadWindowBt("min1"));
        }
        else {
            setIcon(ButtonImageLoad.loadWindowBt(name));
        }
    }
}
