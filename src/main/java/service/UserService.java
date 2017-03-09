package service;

import utilities.exceptions.DuplicateLoginException;
import utilities.exceptions.DuplicatedNameException;
import vo.UserVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface UserService extends Remote{

    /**
     * 用户注册.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userVO the user vo 注册用户信息
     * @return the boolean 是否注册成功
     * @throws RemoteException the remote exception
     * @description 用户注册
     */
    public boolean registerUser(UserVO userVO) throws DuplicatedNameException;

    /**
     * 修改用户信息.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userVO the user vo 修改后的用户信息
     * @return the boolean 是否修改成功
     * @throws RemoteException the remote exception
     */
    public boolean modifyUser(UserVO userVO);

    /**
     * 查看用户信息
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param  userName 用户姓名
     * @return UserVO   用户信息
     * @throws RemoteException the remote exception
     */
    public UserVO checkUserInfo(String userName);

    /**
     * 用户登录.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @return boolean 是否登录成功
     * @throws RemoteException the remote exception
     */
    boolean login(String userName) throws DuplicateLoginException;

    /**
     * 用户注销
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName 用户姓名
     * @return boolean  是否注销成功
     * @throws RemoteException the remote exception
     */
    public boolean logout(String userName);
}