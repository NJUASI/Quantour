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
 * Last updated by Harvey
 * Update time 2017/3/6
 */
public class UserDaoImpl implements UserDao {

    //用户信息获取的helper对象
    private UserDataHelper userHelper;
    private LogDataHelper logDataHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public UserDaoImpl() {
        this.userHelper = new UserDataHelperImpl();
        logDataHelper = new LogDataHelperImpl();
    }

    /**
     * 添加用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return 是否成功添加用户
     */
    @Override
    public boolean add(UserPO userPO) {
        return this.userHelper.add(userPO);
    }

    /**
     * 获取指定用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param username 用户账号
     * @return 用户信息载体
     */
    @Override
    public UserPO get(String username) {
        return this.userHelper.get(username);
    }

    /**
     * 修改用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return 是否成功修改用户
     */
    @Override
    public boolean modify(UserPO userPO) {
        return this.userHelper.modify(userPO);
    }

    /**
     * 记录用户登录信息
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否登录
     */
    @Override
    public boolean logIn(String userName) {
        return logDataHelper.login(userName);
    }

    /**
     * 删除用户登录信息
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否成功登出
     */
    @Override
    public boolean logOut(String userName) {
        return logDataHelper.logout(userName);
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @return 用户名称集合
     */
    @Override
    public Set<Object> getAllUserNames() {
        return userHelper.getAllUserNames();
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @return 已登陆用户名称集合
     */
    @Override
    public Set<Object> getLoginUserNames() {
        return logDataHelper.getLoginUserNames();
    }

}
