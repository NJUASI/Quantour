package presentation.view.tools;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harvey on 2017/3/17.
 */
public class ButtonImageLoad {

    private static final String sourcePath = Thread.currentThread().getContextClassLoader().getResource("picture").getPath();

    private static final int WINBT_WIDTH = 40;
    private static final int WINBT_HEIGHT = 35;

    private static final int WIN_WIDTH = WindowData.getInstance().getWidth();
    private static final int WIN_HEIGHT = WindowData.getInstance().getHeight();

    public static ImageIcon loadWindowBt(String source){

        String path = sourcePath+"/button/"+source+".png";
        System.out.println(path);
        ImageIcon icon = new ImageIcon(path);
        icon.setImage(icon.getImage().getScaledInstance(WINBT_WIDTH*WIN_WIDTH/1920, WINBT_HEIGHT*WIN_HEIGHT/1030, Image.SCALE_DEFAULT));
        return new ImageIcon(path);
    }

}
