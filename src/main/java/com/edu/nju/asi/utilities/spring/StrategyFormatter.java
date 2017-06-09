package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.model.Strategy;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/6/9.
 */
public class StrategyFormatter implements Formatter<Strategy> {

    @Override
    public Strategy parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT Strategy--------------");
        System.out.println(text);
        return JSON.parseObject(text, Strategy.class);
    }

    @Override
    public String print(Strategy object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
