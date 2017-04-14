package presentation.view.tools.component;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.WindowData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Byron Dong on 2017/4/13.
 */
public class MyButton extends JButton {

    /**
     * Creates a button with no set text or icon.
     */
    public MyButton() {

    }

    /** setButton();
     * Creates a button with an icon.
     *
     * @param icon the Icon image to display on the button
     */
    public MyButton(Icon icon) {
        super(icon);
        setButton();
    }

    /**
     * Creates a button with text.
     *
     * @param text the text of the button
     */
    public MyButton(String text) {
        super(text);
        setButton();
    }

    /**
     * Creates a button where properties are taken from the
     * <code>Action</code> supplied.
     *
     * @param a the <code>Action</code> used to specify the new button
     * @since 1.3
     */
    public MyButton(Action a) {
        super(a);
        setButton();
    }

    /**
     * Creates a button with initial text and an icon.
     *
     * @param text the text of the button
     * @param icon the Icon image to display on the button
     */
    public MyButton(String text, Icon icon) {
        super(text, icon);
        setButton();
    }

    private void setButton(){
//        setBackground(ColorUtils.titleColor());
        setForeground(new Color(255,255,255));
        setFont(new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,16* WindowData.getInstance().getWidth()/1920));
    }


}
