package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.Strategy;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public interface StrategyDao {

    /**
     * 添加策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param userName 用户名
     * @param strategy 策略信息载体
     * @return 是否成功添加策略
     */
    boolean addStrategy(String userName,Strategy strategy);

    /**
     * 更新策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param userName 用户名
     * @param strategy 策略信息载体
     * @return 是否成功更新策略
     */
    boolean updateStrategy(String userName,Strategy strategy);

    /**
     * 删除策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param strategyID 策略ID
     * @param userName 用户名
     * @return 是否成功删除策略
     */
    boolean deleteStrategy(String userName,String strategyID);

    /**
     * 获取策略信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     * @param strategyID 策略ID
     * @return Strategy 策略信息载体
     */
    Strategy getStrategy(String strategyID);

    /**
     * 获取该用户的所有策略（创建和订阅）
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     * @param userID 用户ID
     */
    List<Strategy> getAllStrategies(String userID);

    /**
     * 获取所有用户的所有共享策略
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    List<Strategy> getAllStrategies();

    /**
     * 判断策略是否已经创建
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     * @return  boolean true则已存在
     */
    boolean isExist(String strategyID);
}
