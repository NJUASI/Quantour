package presentation.view.tools.component;

import presentation.view.tools.ButtonImageLoad;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harvey on 2017/3/18.
 */
public class SearchTextField extends JTextField {

    private ImageIcon icon;

    public SearchTextField() {

        //TODO
        icon = ButtonImageLoad.loadWindowBt("min1");

        //设置文本输入距左边20
        Insets insets = new Insets(0, 40, 0, 0);
        setMargin(insets);
    }

    @Override
    public void paintComponent(Graphics g) {
        Insets insets = getInsets();
        super.paintComponent(g);
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        int Height = this.getHeight();
        //在文本框中画上之前图片
        icon.paintIcon(this, g, (insets.left - iconWidth)/2, (Height - iconHeight)/2);
    }
}
