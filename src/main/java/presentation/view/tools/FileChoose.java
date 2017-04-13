package presentation.view.tools;

import presentation.controller.LoginController;
import presentation.controller.StocksTableController;
import presentation.controller.UserController;
import presentation.view.panel.StocksTablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by 61990 on 2017/4/11.
 */
public class FileChoose extends MouseAdapter {
    /**
     * 文件选择监听
     *
     * @return
     */
    @Override
    public void mousePressed(MouseEvent e) {

        setUIManage();
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        jfc.showDialog(new JLabel(), "选择");
        File file = jfc.getSelectedFile();
        try {
            if (file.isDirectory()) {
                JOptionPane.showMessageDialog(null, "选择合适的文件");
            } else if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
                UserController.getInstance().importDate(file.getAbsolutePath());
            }
        } catch (NullPointerException e1) {

        }
    }

    private static void setUIManage() {

        UIManager.put("ToolBar.background", ColorUtils.backgroundColor());
        UIManager.put("ToolBar.foreground", ColorUtils.fontColor());

        UIManager.put("Panel.background", ColorUtils.backgroundColor());
        UIManager.put("Panel.foreground", ColorUtils.fontColor());

        UIManager.put("Button.background",ColorUtils.titleColor());
        UIManager.put("Button.foreground",ColorUtils.fontColor());

        UIManager.put("Label.foreground", ColorUtils.fontColor());

        UIManager.put("ComboBox.background", ColorUtils.titleColor());
        UIManager.put("ComboBox.foreground", ColorUtils.fontColor());

        UIManager.put("TextField.background", ColorUtils.titleColor());
        UIManager.put("TextField.foreground", ColorUtils.fontColor());

        UIManager.put("FileChooser.noPlacesBar", Boolean.TRUE);
    }
}
