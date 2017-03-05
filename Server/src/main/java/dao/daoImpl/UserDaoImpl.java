package dao.daoImpl;

import dao.UserDao;
import dataHelper.UserDataHelper;
import dataHelper.dataHelperImpl.UserDataHelperImpl;
import po.UserPO;
import utilities.enums.ResultMessage;

/**
 * Created by cuihua on 2017/3/4.
 */
public class UserDaoImpl implements UserDao {

    //用户信息获取的helper对象
    private UserDataHelper userHelper;

    /**
     * @author Byron Dong
     * @updateTime 2017/3/5 构造函数，初始化成员变量userHelper
     */
    public UserDaoImpl() {
        this.userHelper = new UserDataHelperImpl();
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
}
