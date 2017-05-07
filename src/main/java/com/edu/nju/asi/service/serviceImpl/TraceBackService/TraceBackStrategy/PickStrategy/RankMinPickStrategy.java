package com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackStrategy.PickStrategy;

import com.edu.nju.asi.vo.FormativePeriodRateVO;

import java.util.List;

/**
 * Created by Harvey on 2017/4/20.
 */
public class RankMinPickStrategy extends AllPickStrategy {

    public RankMinPickStrategy(int rank) {
        super(rank);
    }

    @Override
    public List<String> pick(List<FormativePeriodRateVO> formativePeriodRateVOS) {
        return null;
    }
}
