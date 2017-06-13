package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.OptimizationCriteria;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/6/14.
 */
public class OptimizationCriteriaFormatter implements Formatter<OptimizationCriteria> {

    @Override
    public OptimizationCriteria parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT TraceBackCriteria--------------");
        System.out.println(text);
        return JSON.parseObject(text, OptimizationCriteria.class);
    }

    @Override
    public String print(OptimizationCriteria object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
