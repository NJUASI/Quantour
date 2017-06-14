package com.edu.nju.asi.service;

import com.edu.nju.asi.infoCarrier.traceBack.FilterCondition;
import com.edu.nju.asi.infoCarrier.traceBack.RankCondition;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.OptimizationCriteria;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测功能的接口
 */
public interface TraceBackService {


    /**
     *
     * @param traceBackCriteria 回测标准
     * @param customizedStockPool 自选股池
     * @return 回测所要展示的数据
     */
    TraceBackInfo traceBack(TraceBackCriteria traceBackCriteria, List<String> customizedStockPool)  throws IOException, DataSourceFirstDayException, DateNotWithinException, NoDataWithinException, UnhandleBlockTypeException;

    /**
     * 智能调优
     */
    Map<TraceBackCriteria, TraceBackInfo> optimization(OptimizationCriteria optimizationCriteria) throws IOException, DateNotWithinException, DataSourceFirstDayException, NoDataWithinException, UnhandleBlockTypeException;

    /**
     * 策略优化时，先设置原策略的条件
     * @param traceBackCriteria 原策略的标准
     * @return
     */
    void setOriginTraceBackCriteria(TraceBackCriteria traceBackCriteria) throws IOException;

    /**
     * 进行一次适应函数的计算
     * @param filterConditions 经过遗传算法变更过参数的筛选条件
     * @param rankConditions 经过遗传算法变更过参数的排名条件
     * @return 回测结果
     */
    TraceBackInfo optimize(List<FilterCondition> filterConditions, List<RankCondition> rankConditions) throws IOException, UnhandleBlockTypeException, DataSourceFirstDayException, NoDataWithinException, DateNotWithinException;

}
