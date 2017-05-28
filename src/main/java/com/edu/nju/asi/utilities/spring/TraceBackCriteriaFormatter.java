package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/5/28.
 */
public class TraceBackCriteriaFormatter implements Formatter<TraceBackCriteria> {
    @Override
    public TraceBackCriteria parse(String text, Locale locale) throws ParseException {
        return JSON.parseObject(text, TraceBackCriteria.class);
    }

    @Override
    public String print(TraceBackCriteria object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
