package com.edu.nju.asi.service;

import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;

import java.util.List;

/**
 * Created by cuihua on 2017/6/2.
 *
 * 股票策略的保存、查看、刷新等
 */
public interface StrategyService {

    /*
    显示多个策略的界面
     */
    /**
     * @return 系统中的所有非私有的策略
     */
    List<Strategy> getAllStrategies();

    /**
     *
     * @param curUser 当前用户
     * @return 当前用的所有私有&共享的策略
     */
    List<Strategy> getMyStrategies(User curUser);




    /*
    单只策略详情界面
     */
    /**
     *
     * @param strategyID 策略ID
     * @return 单只策略详情
     */
    Strategy getOneStrategy(String strategyID);

    /**
     *
     * @param strategyID 当前用户想要修改的策略实体ID
     * @param curUser 当前想要修改策略的用户
     * @return 当前用户能否修改此策略（只有创建者可以）
     */
    boolean canModify(String strategyID, User curUser);

    /**
     *
     * @param modified 被修改后的策略实体
     * @return 是否成功被修改
     */
    boolean modify(Strategy modified);

    /**
     *
     * @param strategyID 当前用户想要删除的策略实体ID
     * @param curUser 当前想要删除策略的用户
     * @return 当前用户能否删除此策略（只有创建者可以）
     */
    boolean canDeleteString(String strategyID, User curUser);

    /**
     *
     * @param strategyID 被删除的策略实体ID
     * @return
     */
    boolean delete(String strategyID);

    /**
     *
     * @param strategyID 要被用户订阅的策略实体ID
     * @param curUser 要订阅的用户
     * @return 当前用户是否成功订阅此策略
     */
    boolean subscribe(String strategyID, User curUser);

    /**
     *
     * @param strategyID 要被用户取消订阅的策略实体ID
     * @param curUser 要取消订阅的用户
     * @return 当前用户是否成功取消订阅此策略
     */
    boolean revokeSubscribe(String strategyID, User curUser);


}
