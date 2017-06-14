package com.edu.nju.asi.service.serviceImpl.strategyService;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.dao.StrategyDao;
import com.edu.nju.asi.infoCarrier.strategy.StrategyRankResult;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.TraceBackService;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by cuihua on 2017/6/9.
 * <p>
 * 在保存策略时另开一线程，对当前策略进行回测，然后保存进数据库
 */
public class TraceBackSaver implements Callable<Boolean> {

    TraceBackService traceBackService;
    StrategyDao strategyDao;

    Strategy strategy;
    User thisUser;


    final LocalDate defaultDate = LocalDate.of(2016, 5, 1);

    public TraceBackSaver(TraceBackService traceBackService, StrategyDao strategyDao, Strategy strategy, User thisUser) {
        this.traceBackService = traceBackService;
        this.strategyDao = strategyDao;
        this.strategy = strategy;
        this.thisUser = thisUser;
    }

    @Override
    public Boolean call() throws Exception {

        TraceBackCriteria criteria = JSON.parseObject(strategy.getContent(), TraceBackCriteria.class);

        // TODO 默认回测一年
//        criteria.endDate = LocalDate.now();
//        criteria.startDate = criteria.endDate.minusYears(1);
        criteria.endDate = defaultDate;
        criteria.startDate = defaultDate.minusMonths(1);

        String traceBackPoolStringRepre = thisUser.getTraceBackPool();
        List<String> customizedStockPool = JSON.parseArray(traceBackPoolStringRepre, String.class);

        // 回测结果并打分
        TraceBackInfo info = traceBackService.traceBack(criteria, customizedStockPool);

        StrategyRanker ranker = new StrategyRanker();
        StrategyRankResult rankResult = ranker.getRank(criteria, info);

        String nowInfo = JSON.toJSONString(info) + ";" + JSON.toJSONString(rankResult);

        // 将结果存入数据库更新
        strategy.setTraceBackInfo(nowInfo);
        boolean updateResult = strategyDao.updateStrategy(strategy);

        System.out.println(nowInfo + "\n" + updateResult);
        return updateResult;
    }
}
