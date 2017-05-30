package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.infoCarrier.StockComparisionCriteria;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/5/28.
 */
public class StockComparisionCriteriaFormatter implements Formatter<StockComparisionCriteria> {
    @Override
    public StockComparisionCriteria parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT StockComparisionCriteria--------------");
        System.out.println(text);
        return JSON.parseObject(text, StockComparisionCriteria.class);
    }

    @Override
    public String print(StockComparisionCriteria object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
