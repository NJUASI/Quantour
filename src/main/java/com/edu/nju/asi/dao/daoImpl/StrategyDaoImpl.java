package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.StrategyDao;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StrategyDataHelper;
import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.model.Strategy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/2.
 */
@Component("StrategyDao")
public class StrategyDaoImpl implements StrategyDao {

    private UserDataHelper userDataHelper;
    private StrategyDataHelper strategyDataHelper;

    public StrategyDaoImpl() {
        userDataHelper = HelperManager.userDataHelper;
        strategyDataHelper = HelperManager.strategyDataHelper;
    }

    /**
     * 添加策略信息
     *
     * @param userID 用户名
     * @param strategy 策略信息载体
     * @return 是否成功添加策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    @Override
    public boolean addStrategy(String userID, Strategy strategy) {
        return userDataHelper.addStrategy(userID,strategy);
    }

    /**
     * 更新策略信息
     *
     * @param userID 用户名
     * @param strategy 策略信息载体
     * @return 是否成功更新策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    @Override
    public boolean updateStrategy(String userID, Strategy strategy) {
        return userDataHelper.updateStrategy(userID,strategy);
    }

    /**
     * 删除策略信息
     *
     * @param userID   用户名
     * @param strategyID 策略ID
     * @return 是否成功删除策略
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    @Override
    public boolean deleteStrategy(String userID, String strategyID) {
        return userDataHelper.deleteStrategy(userID,strategyID);
    }

    /**
     * 获取策略信息
     *
     * @param strategyID 策略ID
     * @return Strategy 策略信息载体
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/1
     */
    @Override
    public Strategy getStrategy(String strategyID) {
        return strategyDataHelper.getStrategy(strategyID);
    }

    /**
     * 获取用户的所有策略（创建和订阅）
     *
     * @param userID 用户ID
     * @return List<Strategy> 是否成功添加用户
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    @Override
    public List<Strategy> getAllStrategy(String userID) {
        return userDataHelper.getStrategy(userID);
    }

    /**
     * 获取所有策略
     *
     * @return List<Strategy> 是否成功添加用户
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    @Override
    public List<Strategy> getAllStrategy() {
        return strategyDataHelper.getAllStrategy();
    }

    /**
     * 判断策略是否已经创建
     *
     * @param strategyID
     * @return boolean true则已存在
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/2
     */
    @Override
    public boolean isExist(String strategyID) {
        return strategyDataHelper.isExist(strategyID);
    }
}
