package serviceImpl;

import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import po.UserPO;
import service.UserService;
import vo.UserVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 用户操作
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    UserDao userDao;
    public UserServiceImpl() throws RemoteException {
         userDao = new UserDaoImpl();
    }

    /**
     * 用户注册.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userVO the user vo
     * @return the boolean
     * @throws RemoteException the remote exception
     * @description 用户注册
     */
    @Override
    public boolean registerUser(UserVO userVO) throws RemoteException {
        return userDao.add(new UserPO(userVO));
    }

    /**
     * 修改用户信息.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userVO the user vo
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    @Override
    public boolean modifyUser(UserVO userVO) throws RemoteException {
        return userDao.modify(new UserPO(userVO));
    }

    /**
     * 查看用户信息
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName 用户姓名
     * @return
     * @throws RemoteException the remote exception
     */
    @Override
    public UserVO checkUserInfo(String userName) throws RemoteException {
        UserPO po = userDao.get(userName);
        return new UserVO(po);
    }

    /**
     * 用户登录.
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userVo the user vo
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    @Override
    public boolean login(UserVO userVo) throws RemoteException {
        // TODO
        return true;
    }

    /**
     * 用户注销
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName 用户姓名
     * @return boolean  是否注销成功
     * @throws RemoteException the remote exception
     * @auther Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean logout(String userName) throws RemoteException {
        // TODO
        return true;
    }

}
