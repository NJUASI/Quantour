package service;

import utilities.enums.ResultMessage;
import vo.UserVO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface UserService extends Remote{

    /**
     * 用户注册.
     *
     * @param userVO the user vo
     * @return the boolean
     * @throws RemoteException the remote exception
     * @description 用户注册
     */
    public ResultMessage registerUser(UserVO userVO) throws RemoteException;

    /**
     * 修改用户信息.
     *
     * @param userVO the user vo
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    public ResultMessage modifyUser(UserVO userVO) throws RemoteException;

    /**
     * 查看用户信息
     *
     * @param  userName 用户姓名
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    public UserVO checkUserInfo(String userName) throws RemoteException;

    /**
     * 用户登录.
     *
     * @param userVo the user vo
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    public ResultMessage login(UserVO userVo) throws RemoteException;

    /**
     * 用户注销
     *
     * @param userVO the user vo
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    public ResultMessage logout(UserVO userVO) throws RemoteException;
}
