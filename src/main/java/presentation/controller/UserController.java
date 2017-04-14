package presentation.controller;

import presentation.view.panel.user.UserPanel;
import presentation.view.tools.component.ProgressBar;
import service.StockSituationService;

import javax.swing.*;

/**
 * Created by 61990 on 2017/4/13.
 */
public class UserController {
    /**
     * The constant User controller.
     */
    private static UserController userController = new UserController();

    /**
     * The user panel.
     */
    private UserPanel userPanel;



    JProgressBar progressBar;
    StockSituationService stockSituationService;
    /**
     * Instantiates a new User controller.
     */
    private UserController(){
        userPanel = UserPanel.getInstance();
    }

    /**
     * Get instance  User controller.
     *
     * @return the  User controller.
     */
    public static UserController getInstance(){
        return userController;
    }
    /**
     * TODO 导入数据
     * filePath 路径
     */

    public void importDate(String filePath) {

        if(progressBar!=null){
            userPanel.fileImportPanel.remove(progressBar);
        }
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);  //显示提示信息
        progressBar.setIndeterminate(false);
        progressBar.setBounds(60,340,200,35);
        userPanel.fileImportPanel.add(progressBar);
        new ProgressBar(progressBar).start();
    }

    public void modifyPassword(){
        String newPassword =userPanel.messagePanel.getPassword();
        if(newPassword!=null){
            //TODO 修改密码
            try {
//         账号   IDReserve.getInstance().getUserID();
//         新密码   newPassword;
                userPanel.messagePanel.cancelModify();
            }catch (Exception e){

            }

        }

    }
}