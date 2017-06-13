package com.edu.nju.asi.task;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.strategy.StrategyRankResult;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.service.StrategyService;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.UserService;
import com.edu.nju.asi.service.serviceImpl.strategyService.StrategyRanker;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/14.
 *
 * 策略晚上自动更新它的回测结果
 */
@Component
public class StrategyUpdateTask {

    @Autowired
    StrategyService strategyService;

    @Autowired
    TraceBackService traceBackService;

    @Autowired
    UserService userService;


    // 凌晨5点自动更新
    @Scheduled(cron = "0 0 5 * * ?")
    public void update() throws UnhandleBlockTypeException, DateNotWithinException, DataSourceFirstDayException, NoDataWithinException, IOException {

        List<Strategy> allStrategies = strategyService.getAllStrategies();

        if (allStrategies != null) {
            for (Strategy nowStrategy : allStrategies) {
                TraceBackCriteria criteria = JSON.parseObject(nowStrategy.getContent(), TraceBackCriteria.class);

                // 默认回测一年
                criteria.endDate = LocalDate.now().minusDays(1);
                criteria.startDate = criteria.endDate.minusYears(1);


                String creator = nowStrategy.getCreator();
                User curUser = userService.getOne(creator);

                String traceBackPoolStringRepre = curUser.getTraceBackPool();
                List<String> customizedStockPool = JSON.parseArray(traceBackPoolStringRepre, String.class);


                TraceBackInfo info = traceBackService.traceBack(criteria, customizedStockPool);

                StrategyRanker ranker = new StrategyRanker();
                StrategyRankResult rankResult = ranker.getRank(criteria, info);

                String nowInfo = JSON.toJSONString(info) + ";" + JSON.toJSONString(rankResult);

                // 将结果存入数据库更新
                nowStrategy.setTraceBackInfo(nowInfo);
                boolean updateResult = strategyService.modify(nowStrategy, curUser);

                System.out.println(nowInfo + "\n" + updateResult);
            }
        }

    }

}
