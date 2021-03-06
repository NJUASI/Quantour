package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.UserDao;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.dataHelper.dataHelperImpl.UserDataHelperImpl;
import com.edu.nju.asi.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/6
 */
@Component("UserDao")
public class UserDaoImpl implements UserDao {

    //用户信息获取的helper对象
    private UserDataHelper userHelper;

    /**
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     */
    public UserDaoImpl() {
        this.userHelper = HelperManager.userDataHelper;
    }

    /**
     * 添加用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param user 用户信息载体
     * @return 是否成功添加用户
     */
    @Override
    public boolean add(User user) {
        return userHelper.add(user);
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
    public User get(String username) {
        return userHelper.get(username);
    }

    /**
     * 修改用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param user 用户信息载体
     * @return 是否成功修改用户
     */
    @Override
    public boolean modify(User user) {
        return userHelper.update(user);
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
    public List<String> getAllUserNames() {
        return userHelper.getAllUserNames();
    }

}
