package presentation.controller;

import presentation.view.panel.LoginPanel;
import presentation.view.panel.MainPanel;
import presentation.view.panel.RegisterPanel;
import service.UserService;
import service.serviceImpl.UserServiceImpl;
import utilities.exceptions.DuplicatedNameException;
import utilities.exceptions.PasswordNotSameException;
import vo.UserVO;

/**
 * Created by Harvey on 2017/3/12.
 */
public class RegisterController {

    private UserService userService;

    /**
     * The constant registerController.
     */
    public static RegisterController registerController = new RegisterController();

    /**
     * Instantiates a new Register controller.
     */
    private RegisterController() {
        userService = new UserServiceImpl();
    }

    /**
     * Get instance register controller.
     *
     * @return the register controller
     */
    public static RegisterController getInstance(){
        return registerController;
    }


    /**
     * Register.
     */
    public void register(){
        RegisterPanel registerPanel = RegisterPanel.getInstance();

        String userName = registerPanel.getUserName();
        String password = registerPanel.getPassword();
        String password2 = registerPanel.getPassword2();

        try {
            if(userService.registerUser(new UserVO(userName,password),password2)){
                MainPanel.getCard().show(MainPanel.getCardPanel(),"loginPanel");
            }
        } catch (DuplicatedNameException e) {
            e.printStackTrace();
        } catch (PasswordNotSameException e) {
            e.printStackTrace();
        }

        registerPanel.setUserName("请输入姓名");
        registerPanel.setPassword("请输入密码");
        registerPanel.setPassword2("请确认密码");

    }


    /**
     * 跳转到注册界面
     */
    public void openLogin() {
        MainPanel.getCard().show(MainPanel.getCardPanel(),"loginPanel");
    }
}
