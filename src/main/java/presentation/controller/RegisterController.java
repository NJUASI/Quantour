package presentation.controller;

import presentation.view.frame.RegisterFrame;
import presentation.view.panel.RegisterPanel;
import service.UserService;
import service.serviceImpl.UserServiceImpl;
import utilities.exceptions.DuplicatedNameException;
import utilities.exceptions.PasswordNotSameException;
import vo.UserVO;

import java.io.IOException;

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
    public static RegisterController getInstance() {
        return registerController;
    }


    /**
     * Register.
     */
    public void register() {
        RegisterPanel registerPanel = RegisterPanel.getInstance();
        String userName = registerPanel.getUserName();
        String password = registerPanel.getPassword();
        String password2 = registerPanel.getPassword2();

        try {
            //TODO 注册
            if (userService.registerUser(new UserVO(userName, password), password2)) {
                RegisterFrame.getInstance().refresh();
                RegisterFrame.getInstance().setVisible(false);
            }
        } catch (DuplicatedNameException e) {
            e.printStackTrace();
        } catch (PasswordNotSameException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        registerPanel.setUserName("请输入姓名");
        registerPanel.setPassword("请输入密码");
        registerPanel.setPassword2("请确认密码");

    }

}