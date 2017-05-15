package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Harvey on 2017/3/11.
 */
public class PasswordWrongException extends Exception {

    @Override
    public String getMessage() {
        return "密码错误！";
    }
}
