package service.serviceImpl;

import dao.StockDao;
import dao.UserDao;
import dao.daoImpl.StockDaoImpl;
import dao.daoImpl.UserDaoImpl;
import utilities.exceptions.*;
import po.UserPO;
import service.UserService;
import vo.UserVO;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;
    StockDao stockDao;

    public UserServiceImpl() {
         userDao = new UserDaoImpl();
         stockDao = new StockDaoImpl();
    }

    /**
     * 用户注册.
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userVO 注册用户信息
     * @param password2
     * @return 是否注册成功
     * @throws DuplicatedNameException 用户名重复
     */
    @Override
    public boolean registerUser(UserVO userVO, String password2) throws DuplicatedNameException, PasswordNotSameException {

        if(userDao.getAllUserNames().contains(userVO.userName)){
            throw new DuplicatedNameException();
        }
        else if(userVO.password.equals(password2)){
            throw new PasswordNotSameException();
        }
        userDao.add(new UserPO(userVO));
        //为用户新建一个保存自选股的文件
        stockDao.createPrivateDir(userVO.userName);
        return true;
    }

    /**
     * 修改用户信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userVO 修改后的用户信息
     * @return 是否修改成功
     */
    @Override
    public boolean modifyUser(UserVO userVO) {
        return userDao.modify(new UserPO(userVO));
    }

    /**
     * 查看用户信息
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param  userName 用户姓名
     * @return 用户信息
     */
    @Override
    public UserVO checkUserInfo(String userName) {
        UserPO po = userDao.get(userName);
        return new UserVO(po);
    }

    /**
     * 用户登录
     *
     * @param userName 用户名称
     * @param password
     * @return 是否登录成功
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean logIn(String userName, String password) throws UserNotExistException, DuplicateLoginException, PasswordWrongException {
        if(!userDao.getAllUserNames().contains(userName)){
            throw new UserNotExistException();
        }
        else{
            if(userDao.getLoginUserNames().contains(userName)){
                throw new DuplicateLoginException();
            }
            else if (!userDao.get(userName).getPassword().equals(password)){
                throw new PasswordWrongException();
            }
            else{
                return userDao.logIn(userName);
            }
        }
    }

    /**
     * 用户登出
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户姓名
     * @return 是否注销成功
     */
    @Override
    public boolean logOut(String userName) {
        return userDao.logOut(userName);
    }

}
