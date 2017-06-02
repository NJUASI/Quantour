package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;

import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 新增getAllUserNames接口
 */
public interface UserDataHelper {

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
    List<String> getAllUserNames();




    /*
    策略相关
     */
    /**
     * 添加策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param strategy 策略信息载体
     * @param userID 用户名
     * @return 是否成功添加策略
     */
    boolean addStrategy(String userID,Strategy strategy);

    /**
     * 更新策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param strategy 策略信息载体
     * @param userID 用户名
     * @return 是否成功更新策略
     */
    boolean updateStrategy(String userID,Strategy strategy);

    /**
     * 删除策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param strategyID 策略ID
     * @param userID 用户名
     * @return 是否成功删除策略
     */
    boolean deleteStrategy(String userID,String strategyID);

    /**
     * 获取策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param userID 用户名
     * @return Strategy 策略信息载体
     */
    List<Strategy> getStrategy(String userID);
}
