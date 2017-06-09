package com.edu.nju.asi.service.serviceImpl.strategyService;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.dao.StrategyDao;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.service.TraceBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.Callable;

/**
 * Created by cuihua on 2017/6/9.
 * <p>
 * 在保存策略时另开一线程，对当前策略进行回测，然后保存进数据库
 */
public class TraceBackTask implements Callable<Boolean> {

    TraceBackService traceBackService;
    StrategyDao strategyDao;

    Strategy strategy;

    public TraceBackTask() {
    }

    public TraceBackTask(TraceBackService traceBackService, StrategyDao strategyDao, Strategy strategy) {
        this.traceBackService = traceBackService;
        this.strategyDao = strategyDao;
        this.strategy = strategy;
    }

    @Override
    public Boolean call() throws Exception {

        TraceBackCriteria criteria = JSON.parseObject(strategy.getContent(), TraceBackCriteria.class);

        // TODO 默认回测一年
//        criteria.endDate = LocalDate.now();
//        criteria.startDate = criteria.endDate.minusYears(1);
        criteria.endDate = LocalDate.now();
        criteria.startDate = criteria.endDate.minusMonths(1);

        TraceBackInfo info = traceBackService.traceBack(criteria);

        // 将结果存入数据库更新
        strategy.setTraceBackInfo(JSON.toJSONString(info));
        boolean updateResult = strategyDao.updateStrategy(strategy.getCreater(), strategy);
        return updateResult;
    }
}
