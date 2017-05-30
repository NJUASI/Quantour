package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.utilities.enums.StocksSortCriteria;
import com.edu.nju.asi.utilities.exceptions.NoMatchEnumException;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/5/30.
 */
public class StocksSortCriteriaFormatter implements Formatter<StocksSortCriteria> {
    @Override
    public StocksSortCriteria parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT StocksSortCriteria--------------");
        System.out.println(text);
        try {
            return StocksSortCriteria.getEnum(Integer.parseInt(text));
        } catch (NoMatchEnumException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String print(StocksSortCriteria object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
