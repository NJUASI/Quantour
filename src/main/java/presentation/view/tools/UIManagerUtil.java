package presentation.view.tools;

import javax.swing.*;

/**
 * Created by Byron Dong on 2017/4/14.
 */
public class UIManagerUtil {

    public static void set(){
        UIManager.put("ToolBar.background", ColorUtils.backgroundColor());
        UIManager.put("ToolBar.foreground", ColorUtils.fontColor());

        UIManager.put("Panel.background", ColorUtils.backgroundColor());
        UIManager.put("Panel.foreground", ColorUtils.fontColor());

        UIManager.put("Label.foreground", ColorUtils.fontColor());

        UIManager.put("ComboBox.background", ColorUtils.titleColor());
        UIManager.put("ComboBox.foreground", ColorUtils.fontColor());

        UIManager.put("TextField.background", ColorUtils.titleColor());
        UIManager.put("TextField.foreground", ColorUtils.fontColor());

        UIManager.put("PasswordField.background",ColorUtils.titleColor());
        UIManager.put("PasswordField.foreground",ColorUtils.fontColor());

        UIManager.put("FileChooser.noPlacesBar", Boolean.TRUE);
    }
}
