package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.utilities.enums.AreaType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/6/8.
 */
public class AreaTypeFormatter implements Formatter<AreaType> {

    @Override
    public AreaType parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT AreaType--------------");
        System.out.println(text);
        if (text.equals("全部")) return AreaType.all;
        if (text.equals("其他")) return AreaType.none;
        return AreaType.valueOf(text);
    }

    @Override
    public String print(AreaType object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
