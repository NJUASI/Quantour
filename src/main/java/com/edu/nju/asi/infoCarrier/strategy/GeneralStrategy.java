package com.edu.nju.asi.infoCarrier.strategy;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.utilities.NumberFormat;
import com.edu.nju.asi.utilities.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/6/9.
 *
 * 策略的大致信息
 */
public class GeneralStrategy {

    /**
     * 策略实体ID
     */
    public String strategyID;

    /**
     * 策略创建者ID
     */
    public String creator;

    /**
     * 策略的创建日期
     */
    public LocalDate createDate;

    /**
     * 此策略的订阅人数
     */
    public int subscribeNum;

    /**
     * 策略的年化收益率
     */
    public String annualizedRateOfReturn;

    /**
     * 策略的最大回撤率
     */
    public String maxStrategyTraceBackRate;

    /**
     * 回测图表的策略累计收益率Json String
     */
    public String strategyCumulativeReturnChart;

    /**
     * 回测图表的股指累计收益率Json String
     */
    public String baseCumulativeReturnChart;

    public GeneralStrategy(Strategy strategy) {
        this.strategyID = strategy.getStrategyID();
        this.creator = strategy.getCreater();
        this.createDate = strategy.getDate();
        this.subscribeNum = strategy.getUsers().size() - 1;


        TraceBackInfo info = JSON.parseObject(strategy.getTraceBackInfo(), TraceBackInfo.class);
        this.annualizedRateOfReturn = NumberFormat.percentFormat(info.traceBackNumVal.annualizedRateOfReturn, 2);
        this.maxStrategyTraceBackRate = NumberFormat.percentFormat(info.maxTraceBack.maxStrategyTraceBackRate, 2);
        try {
            // 加入累计收益率
            this.strategyCumulativeReturnChart = JsonConverter.convertTraceBack(info.strategyCumulativeReturn);
            this.baseCumulativeReturnChart = JsonConverter.convertTraceBack(info.baseCumulativeReturn);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
