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
public class TraceBackSave implements Callable<Boolean> {

    TraceBackService traceBackService;
    StrategyDao strategyDao;

    Strategy strategy;


    final LocalDate defaultDate = LocalDate.of(2017, 5, 1);

    public TraceBackSave(TraceBackService traceBackService, StrategyDao strategyDao, Strategy strategy) {
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
        criteria.endDate = defaultDate;
        criteria.startDate = defaultDate.minusMonths(1);

        TraceBackInfo info = traceBackService.traceBack(criteria);

        // 将结果存入数据库更新
        strategy.setTraceBackInfo(JSON.toJSONString(info));
        boolean updateResult = strategyDao.updateStrategy(strategy.getCreater(), strategy);

        System.out.println(JSON.toJSONString(info) + "\n" + updateResult);

        return updateResult;
    }
}
