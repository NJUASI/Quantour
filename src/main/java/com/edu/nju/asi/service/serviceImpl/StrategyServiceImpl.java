package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.StrategyDao;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cuihua on 2017/6/2.
 */
@Service("StrategyService")
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    StrategyDao strategyDao;


    @Override
    public List<Strategy> getAllStrategies() {
        return strategyDao.getAllStrategies();
    }

    @Override
    public List<Strategy> getMyStrategies(User curUser) {
        return strategyDao.getAllStrategies(curUser.getUserName());
    }

    @Override
    public Strategy getOneStrategy(String strategyID) {
        return null;
    }

    // 只有创建者能够操作（修改／删除）策略
    @Override
    public boolean canUpdate(Strategy strategy, User curUser) {
        return isCreator(strategy, curUser);
    }

    @Override
    public boolean modify(Strategy modified, User curUser) {
        return strategyDao.updateStrategy(curUser.getUserName(), modified);
    }

    @Override
    public boolean delete(String strategyID, User curUser) {
        return strategyDao.deleteStrategy(curUser.getUserName(), strategyID);
    }

    @Override
    public boolean subscribe(String strategyID, User curUser) {
//        return strategyDao.addStrategy(curUser.getUserName(), );
        return false;
    }

    @Override
    public boolean revokeSubscribe(String strategyID, User curUser) {
        return strategyDao.deleteStrategy(curUser.getUserName(), strategyID);
    }


    private boolean isCreator(Strategy strategy, User user) {
        if (strategy.getCreater().equals(user.getUserName())) return true;
        else return false;
    }

}
