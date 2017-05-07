package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.po.UserPO;

import java.util.Set;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 新增getAllUserNames接口
 */
public interface UserDataHelper {

    /**
     * 添加一个用户
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息
     * @return 添加是否成功
     */
    boolean add(UserPO userPO);

    /**
     * 获取用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param username 用户姓名
     * @return 用户信息载体
     */
    UserPO get(String username);

    /**
     * 修改一条用户信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户修改信息
     * @return 修改是否成功
     */
    boolean modify(UserPO userPO);

    /**
     * 获取所有的注册过用户名称
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @return 用户名称集合
     */
    Set<Object> getAllUserNames();
}
