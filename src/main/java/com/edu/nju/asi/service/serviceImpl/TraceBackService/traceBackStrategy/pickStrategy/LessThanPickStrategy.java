package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.pickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.util.List;

/**
 * Created by Harvey on 2017/6/9.
 *
 * 小于选择符
 */
public class LessThanPickStrategy extends AllPickStrategy {

    public LessThanPickStrategy(int rank) {
        super(rank);
    }

    /**
     * 根据FormativePeriodRateVO中的periodReturn对股票代码进行排序并选择
     *
     * @param formateRates
     * @return List<String> 选择好的持有期的股票代码
     */
    @Override
    public List<String> pick(List<FormateRate> formateRates) {
        for(int i = 0; i < formateRates.size();){
            if(formateRates.get(i).indicatorVal >= rank){
                formateRates.remove(i);
            }else {
                i++;
            }
        }

        return convert(formateRates);
    }
}
