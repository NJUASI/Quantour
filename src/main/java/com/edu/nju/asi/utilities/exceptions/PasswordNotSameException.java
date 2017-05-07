package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Harvey on 2017/3/12.
 */
public class PasswordNotSameException extends Exception {
    /**
     * 注册时两次密码不一致，抛出该异常
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "两次密码不一致，请检查";
    }
}
