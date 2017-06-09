package com.edu.nju.asi.service.serviceImpl.strategyService;

import com.edu.nju.asi.model.Strategy;

import java.util.Comparator;

/**
 * Created by cuihua on 2017/6/9.
 */
public class StrategyUsersDescComparator implements Comparator<Strategy> {

    @Override
    public int compare(Strategy o1, Strategy o2) {
        int tempResult = o1.getUsers().size() - o2.getUsers().size();
        if (tempResult < 0) return 1;
        else if (tempResult > 0) return -1;
        else return 0;
    }
}
