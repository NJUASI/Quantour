package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Harvey on 2017/3/6.
 */
public class DuplicatedNameException extends Exception {
    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "该用户名已存在";
    }
}
