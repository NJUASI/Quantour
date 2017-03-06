package dao.daoImpl;

import dao.UserDao;
import dataHelper.LogDataHelper;
import dataHelper.UserDataHelper;
import dataHelper.dataHelperImpl.LogDataHelperImpl;
import dataHelper.dataHelperImpl.UserDataHelperImpl;
import po.UserPO;

import java.util.Set;

/**
 * Created by cuihua on 2017/3/4.
 */
public class UserDaoImpl implements UserDao {

    //用户信息获取的helper对象
    private UserDataHelper userHelper;
    private LogDataHelper logDataHelper;

    /**
     * @author Byron Dong
     * @updateTime 2017/3/5 构造函数，初始化成员变量userHelper
     */
    public UserDaoImpl() {
        this.userHelper = new UserDataHelperImpl();
        logDataHelper = new LogDataHelperImpl();
    }

    /**
     * 添加用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return ResultMessage 是否成功添加用户
     */
    @Override
    public boolean add(UserPO userPO) {
        return this.userHelper.add(userPO);
    }

    /**
     * 获取指定用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param username 用户账号
     * @return UserPO 用户信息载体
     */
    @Override
    public UserPO get(String username) {
        return this.userHelper.get(username);
    }

    /**
     * 修改用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return ResultMessage 是否成功修改用户
     */
    @Override
    public boolean modify(UserPO userPO) {
        return this.userHelper.modify(userPO);
    }

    /**
     * 修改用户信息
     *
     * @param userName 用户名称
     * @return boolean 是否登录
     * @author Harvey
     * @updateTime 2017/3/6
     */
    @Override
    public boolean login(String userName) {
        return logDataHelper.login(userName);
    }

    /**
     * 修改用户信息
     *
     * @param userName 用户名称
     * @return boolean 是否成功登出
     * @author Harvey
     * @updateTime 2017/3/6
     */
    @Override
    public boolean logout(String userName) {
        return logDataHelper.logout(userName);
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @return boolean 是否成功登出
     * @author Harvey
     * @updateTime 2017/3/6
     */
    @Override
    public Set<Object> getAllUserNames() {
        return userHelper.getAllUserNames();
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @return Set<String> 已登陆用户名称集合
     * @author Harvey
     * @updateTime 2017/3/6
     */
    @Override
    public Set<Object> getLoginUserNames() {
        return logDataHelper.getLoginUserNames();
    }
}
