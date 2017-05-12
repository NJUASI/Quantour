package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Harvey on 2017/3/6.
 */
public class DuplicateLoginException extends Exception {
    /**
     * Returns the detail message string of this throwable.
     *
     * @return 用户已登录信息
     */
    @Override
    public String getMessage() {
        return  "该用户已登录";
    }
}
