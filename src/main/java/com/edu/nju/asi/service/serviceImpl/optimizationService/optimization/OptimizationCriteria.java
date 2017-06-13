package com.edu.nju.asi.service.serviceImpl.optimizationService.optimization;

import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.utilities.enums.TargetFuncType;

import java.util.List;

/**
 * Created by Harvey on 2017/6/13.
 *
 * 优化时选择的条件，默认只有筛选条件、排名条件的值域及步长可以更改
 */
public class OptimizationCriteria {

    /**
     * 原回测方法
     */
    public TraceBackCriteria originTraceBackCriteria;

    /**
     * 每个筛选条件所对应的调整条件
     */
    public List<AdjustCriteria> filterAdjust;

    /**
     * 每个排名条件所有对应的调整条件
     */
    public List<AdjustCriteria> rankAdjust;

    /**
     * 目标核心函数
     */
    public TargetFuncType targetFuncType;

    /**
     * 搜索的空间节点数
     */
    public long searchNodes;

    public OptimizationCriteria() {
    }

    /**
     *
     * @param traceBackCriteria 原策略
     * @param filterAdjust 筛选条件依次排列的调整条件
     * @param rankAdjust 排名条件依次排列的调整条件
     * @param targetFuncType 目标核心函数
     */
    public OptimizationCriteria(TraceBackCriteria traceBackCriteria, List<AdjustCriteria> filterAdjust, List<AdjustCriteria> rankAdjust, TargetFuncType targetFuncType, long searchNodes) {

        this.originTraceBackCriteria = traceBackCriteria;
        this.filterAdjust = filterAdjust;
        this.rankAdjust = rankAdjust;
        this.targetFuncType = targetFuncType;
        this.searchNodes = searchNodes;
    }
}
