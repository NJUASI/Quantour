package presentation.controller;

import presentation.view.frame.LoginFrame;
import presentation.view.frame.MainFrame;
import presentation.view.frame.RegisterFrame;
import presentation.view.panel.LoginPanel;
import service.UserService;
import service.serviceImpl.UserServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.DuplicateLoginException;
import utilities.exceptions.PasswordWrongException;
import utilities.exceptions.UserNotExistException;

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
//                ViewSwitchController.getInstance().viewSwitch("stocksTablePanel");
                LoginFrame.getInstance().refresh();
                RegisterFrame.getInstance().refresh();
                return true;
            }

        } catch (DuplicateLoginException e) {
            e.printStackTrace();
        } catch (UserNotExistException e) {
            e.printStackTrace();
        } catch (PasswordWrongException e) {
            e.printStackTrace();
        }
        return false;
    }
}
