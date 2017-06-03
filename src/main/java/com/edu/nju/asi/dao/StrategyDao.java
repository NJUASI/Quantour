package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.Strategy;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public interface StrategyDao {

    /**
     * 添加策略信息(创建者)
     *
     * @param userName 用户名
     * @param strategy 策略信息载体
     * @return 是否成功添加策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    boolean addStrategyByCreator(String userName, Strategy strategy);

    /**
     * 添加策略信息（订阅者）
     *
     * @param userName 用户名
     * @param strategyID 策略ID
     * @return 是否成功添加策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    boolean addStrategyByChecker(String userName, String strategyID);

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
     * @param strategyID 策略ID
     * @return Strategy 策略信息载体
     */
    Strategy getStrategy(String strategyID);

    /**
     * 获取用户的所有策略（创建和订阅）
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     * @param userID 用户ID
     * @return  List<Strategy> 是否成功添加用户
     */
    List<Strategy> getAllStrategy(String userID);

    /**
     * 获取所有策略
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     * @return  List<Strategy> 是否成功添加用户
     */
    List<Strategy> getAllStrategy();

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
