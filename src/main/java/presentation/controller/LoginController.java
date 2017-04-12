package presentation.controller;

import presentation.view.frame.LoginFrame;
import presentation.view.frame.MainFrame;
import presentation.view.frame.RegisterFrame;
import presentation.view.panel.LoginPanel;
import presentation.view.tools.ColorUtils;
import presentation.view.tools.UI.MyScrollBarUI;
import service.UserService;
import service.serviceImpl.UserServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.DuplicateLoginException;
import utilities.exceptions.PasswordWrongException;
import utilities.exceptions.UserNotExistException;

import javax.swing.*;

/**
 * Created by 61990 on 2017/3/5.
 */
public class LoginController{

    /**
     * The constant loginController.
     */
    public static LoginController loginController = new LoginController();

    /**
     * Instantiates a new Login controller.
     */
    private LoginController(){

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static LoginController getInstance() {
        return loginController;
    }

    /**
     * 登陆检测
     *
     * @param
     * @return  登录是否成功
     * @author 61990
     * @updateTime 2017/3/9
     */
    public boolean login(){
        LoginPanel loginPanel = LoginPanel.getInstance();

        UserService userService=new UserServiceImpl();
        String name = loginPanel.getUsername();
        String password = loginPanel.getPassword();
        System.out.println(name);
        try {
            if(userService.logIn(name,password)){
                IDReserve.getInstance().setUserID(name);
                loginPanel.setUsername("Enter your admin");
                loginPanel.setPassword("");
                MainFrame.getInstance();
                LoginFrame.getInstance().refresh();
                RegisterFrame.getInstance().refresh();
                setUIManage();
                return true;
            }

        } catch (DuplicateLoginException e) {
            JOptionPane.showMessageDialog(loginPanel,"重复登录");
        } catch (UserNotExistException e) {
            JOptionPane.showMessageDialog(loginPanel,"用户名不存在");
        } catch (PasswordWrongException e) {
            JOptionPane.showMessageDialog(loginPanel,"密码错误");
        }
        return false;
    }

    private static void setUIManage(){

        UIManager.put("ToolBar.background", ColorUtils.lineColor());
        UIManager.put("ToolBar.foreground",ColorUtils.fontColor());

        UIManager.put("Panel.background",ColorUtils.backgroundColor());
        UIManager.put("Panel.foreground",ColorUtils.fontColor());

        UIManager.put("Label.foreground",ColorUtils.fontColor());

        UIManager.put("ComboBox.background", ColorUtils.makerLabelColor());
        UIManager.put("ComboBox.foreground", ColorUtils.fontColor());

        UIManager.put("TextField.background",ColorUtils.makerLabelColor());
        UIManager.put("TextField.foreground",ColorUtils.fontColor());


        UIManager.put("FileChooser.noPlacesBar", Boolean.TRUE);
    }
}
