package presentation.view.tools.component;

import presentation.controller.LoginController;
import presentation.controller.StocksTableController;
import presentation.controller.UserController;
import presentation.view.panel.StocksTablePanel;
import presentation.view.panel.user.UserPanel;
import presentation.view.tools.PopUpFrame;
import presentation.view.tools.UIManagerUtil;

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
        UserPanel.getInstance().fileImportPanel.popLabel();
        UIManagerUtil.set();
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        jfc.showDialog(new JLabel(), "选择");
        File file = jfc.getSelectedFile();
        try {
            if (file.isDirectory()) {
                JOptionPane.showMessageDialog(null, "选择合适的文件");
            } else if (file.isFile()) {
                UserController.getInstance().importDate(file.getAbsolutePath());
            }
        } catch (NullPointerException e1) {
            new PopUpFrame(e1.getMessage());
        }
    }
}
