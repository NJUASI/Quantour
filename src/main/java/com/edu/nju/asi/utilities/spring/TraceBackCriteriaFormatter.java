package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.utilities.enums.AreaType;
import com.edu.nju.asi.utilities.enums.IndustryType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by cuihua on 2017/5/28.
 */
public class TraceBackCriteriaFormatter implements Formatter<TraceBackCriteria> {

    @Override
    public TraceBackCriteria parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT TraceBackCriteria--------------");
        System.out.println(text);

        TraceBackCriteria criteria = JSON.parseObject(text, TraceBackCriteria.class);

        // 直接处理掉选择全部的情况
        if (criteria.stockPoolCriteria.industryTypes.size() == IndustryType.values().length - 1) {
            List<IndustryType> industryTypes = new LinkedList<>();
            industryTypes.add(IndustryType.all);
            criteria.stockPoolCriteria.industryTypes = industryTypes;
        }

        if (criteria.stockPoolCriteria.areaTypes.size() == AreaType.values().length - 1) {
            List<AreaType> areaTypes = new LinkedList<>();
            areaTypes.add(AreaType.all);
            criteria.stockPoolCriteria.areaTypes = areaTypes;
        }

        return criteria;
    }

    @Override
    public String print(TraceBackCriteria object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
