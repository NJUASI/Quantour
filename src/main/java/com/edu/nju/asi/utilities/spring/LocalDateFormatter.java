package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.utilities.LocalDateHelper;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Created by cuihua on 2017/5/28.
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT LocalDate--------------");
        System.out.println(text);
        return LocalDateHelper.convertString(text);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
