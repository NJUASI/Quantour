package com.edu.nju.asi.utilities.spring;

import com.alibaba.fastjson.JSON;
import com.edu.nju.asi.model.User;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by cuihua on 2017/6/11.
 */
public class UserFormatter implements Formatter<User> {

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        System.out.println("--------------FORMAT User--------------");
        System.out.println(text);
        return JSON.parseObject(text, User.class);
    }

    @Override
    public String print(User object, Locale locale) {
        return JSON.toJSONString(object);
    }
}
