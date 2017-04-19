package presentation.controller;

import presentation.view.frame.LoginFrame;
import presentation.view.frame.MainFrame;
import presentation.view.frame.RegisterFrame;
import presentation.view.panel.LoginPanel;
import presentation.view.tools.PopUpFrame;
import service.UserService;
import service.serviceImpl.UserServiceImpl;
import utilities.IDReserve;
import utilities.exceptions.*;

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
        try {
            if(userService.logIn(name,password)){
                IDReserve.getInstance().setIDReserve(name,password);
                loginPanel.setUsername("Enter your admin");
                loginPanel.setPassword("");
                MainFrame.getInstance();
                LoginFrame.getInstance().refresh();
                RegisterFrame.getInstance().refresh();
                return true;
            }

        } catch (DuplicateLoginException e) {
            new PopUpFrame("重复登录");
        } catch (UserNotExistException e) {
            new PopUpFrame("用户名不存在");
        } catch (PasswordWrongException e) {
            new PopUpFrame("密码错误");
        } catch (InvalidInputException e) {
            new PopUpFrame(e.getMessage());
        } catch (PasswordInputException e) {
            new PopUpFrame(e.getMessage());
        }
        return false;
    }
}
