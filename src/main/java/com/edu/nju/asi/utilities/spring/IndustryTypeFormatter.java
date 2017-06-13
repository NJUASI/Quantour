package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.utilities.enums.IndustryType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/6/8.
 *
 * 行情界面选择板块类型
 */
public class IndustryTypeFormatter implements Formatter<IndustryType> {

    @Override
    public IndustryType parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT IndustryType--------------");
        System.out.println(text);
        if (text.equals("全部")) return IndustryType.all;
        if (text.equals("其他")) return IndustryType.none;
        return IndustryType.valueOf(text);
    }

    @Override
    public String print(IndustryType object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
