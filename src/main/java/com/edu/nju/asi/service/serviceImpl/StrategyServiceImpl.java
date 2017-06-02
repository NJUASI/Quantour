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
        return null;
    }

    @Override
    public List<Strategy> getMyStrategies(User curUser) {
        return null;
    }

    @Override
    public Strategy getOneStrategy(String strategyID) {
        return null;
    }

    @Override
    public boolean canModify(String strategyID, User curUser) {
        return false;
    }

    @Override
    public boolean modify(Strategy modified) {
        return false;
    }

    @Override
    public boolean canDeleteString(String strategyID, User curUser) {
        return false;
    }

    @Override
    public boolean delete(String strategyID) {
        return false;
    }

    @Override
    public boolean subscribe(String strategyID, User curUser) {
        return false;
    }

    @Override
    public boolean revokeSubscribe(String strategyID, User curUser) {
        return false;
    }
}
