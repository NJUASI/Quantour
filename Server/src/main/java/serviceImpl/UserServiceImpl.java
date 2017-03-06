package serviceImpl;

import dao.StockDao;
import dao.UserDao;
import dao.daoImpl.StockDaoImpl;
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
    StockDao stockDao;

    public UserServiceImpl() throws RemoteException {
         userDao = new UserDaoImpl();
         stockDao = new StockDaoImpl();
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

        if(userDao.add(new UserPO(userVO))){
            //给用户创建一个资源文件，存取用户的自选股代码
            stockDao.createPrivateDir(userVO.userName);
            return true;
        }
        return false;
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
     * @param userName 用户名称
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    @Override
    public boolean login(String userName) throws RemoteException {

        return userDao.login(userName);
    }

    /**
     * 用户注销
     * @auther Harvey
     * @updateTime 2017/3/5
     * @param userName 用户姓名
     * @return ResultMessage  是否注销成功
     * @throws RemoteException the remote exception
     * @auther Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean logout(String userName) throws RemoteException {
        return userDao.logout(userName);
    }

}
