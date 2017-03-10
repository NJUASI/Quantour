package presentation.controller;

import service.UserService;
import service.serviceImpl.UserServiceImpl;

/**
 * Created by 61990 on 2017/3/5.
 */
public class LoginController extends ViewController{

    String ID;
    String password;
    public LoginController(String ID,String password){
        this.ID=ID;
        this.password=password;
    }
    /**
     * 登陆检测
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/9
     */
    public void login(){
        UserService userService=new UserServiceImpl();
//        userService.logIn(ID);
    }
}
