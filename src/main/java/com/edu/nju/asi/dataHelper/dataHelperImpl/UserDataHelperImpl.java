package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/5
 * <p>
 * 对用户信息进行操作
 */
@Repository
public class UserDataHelperImpl implements UserDataHelper {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    /**
     * 添加用户信息
     *
     * @param user 用户信息载体
     * @return 是否成功添加用户
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean add(User user) {
        return false;
    }

    /**
     * 获取指定用户信息
     *
     * @param username 用户账号
     * @return 用户信息载体
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public User get(String username) {
        return null;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息载体
     * @return 是否成功修改用户
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean update(User user) {
        return false;
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public Set<Object> getAllUserNames() {
        return null;
    }

    /**
     * 获取已存在的所有用户名称
     *
     * @param userName
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public Set<Stock> getPrivateStock(String userName) {
        return null;
    }

    /**
     * 添加自选股
     *
     * @param stock
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean addPrivateStock(Stock stock) {
        return false;
    }

    /**
     * 删除自选股
     *
     * @param stockCode
     * @return 用户名称集合
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     */
    @Override
    public boolean deletePrivateStock(String stockCode) {
        return false;
    }
}