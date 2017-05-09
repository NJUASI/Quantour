package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.User;

import java.util.Set;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/6
 */
public interface UserDao {

    /**
     * 添加用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param user 用户信息载体
     * @return 是否成功添加用户
     */
     boolean add(User user);

    /**
     * 获取指定用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param username 用户账号
     * @return 用户信息载体
     */
     User get(String username);

    /**
     * 修改用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @param user 用户信息载体
     * @return 是否成功修改用户
     */
     boolean update(User user);

    /**
     * 获取已存在的所有用户名称
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    Set<Object> getAllUserNames();

    /**
     * 获取已存在的所有用户名称
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    Set<Stock> getPrivateStock(String userName);

    /**
     * 添加自选股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean addPrivateStock(Stock stock);

    /**
     * 删除自选股
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return 用户名称集合
     */
    boolean deletePrivateStock(String stockCode);
}
