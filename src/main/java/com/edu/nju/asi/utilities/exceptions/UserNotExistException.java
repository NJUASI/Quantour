package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Harvey on 2017/3/11.
 */
public class UserNotExistException extends Exception {
    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "用户不存在,请检查用户名";
    }
}
