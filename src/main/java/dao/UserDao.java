package dao;

import po.UserPO;

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
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return 是否成功添加用户
     */
     boolean add(UserPO userPO);

    /**
     * 获取指定用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param username 用户账号
     * @return 用户信息载体
     */
     UserPO get(String username);

    /**
     * 修改用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return 是否成功修改用户
     */
     boolean modify(UserPO userPO);

    /**
     * 记录用户登录信息
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否登录
     */
    boolean logIn(String userName);

    /**
     * 获取已存在的所有用户名称
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @return 用户名称集合
     */
    Set<Object> getAllUserNames();
}
