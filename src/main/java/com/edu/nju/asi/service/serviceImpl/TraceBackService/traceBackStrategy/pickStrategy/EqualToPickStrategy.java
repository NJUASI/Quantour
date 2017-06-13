package com.edu.nju.asi.service.serviceImpl.traceBackService.traceBackStrategy.pickStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.FormateRate;

import java.util.List;

/**
 * Created by Harvey on 2017/6/9.
 *
 * 等于选择符
 */
public class EqualToPickStrategy extends AllPickStrategy {

    public EqualToPickStrategy(int rank) {
        super(rank);
    }

    @Override
    public List<String> pick(List<FormateRate> formateRates) {

        for(int i = 0; i < formateRates.size();){
            if(formateRates.get(i).indicatorVal == null || formateRates.get(i).indicatorVal != rank){
                formateRates.remove(i);
            }else {
                i++;
            }
        }

        return convert(formateRates);
    }
}
