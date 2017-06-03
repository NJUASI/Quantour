package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.Strategy;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/2.
 */
public interface StrategyDataHelper {

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
     * 获取单个策略
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     * @return  指定策略
     */
    Strategy getStrategy(String strategyID);

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
