package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Byron Dong on 2017/4/19.
 */
public class PrivatePoolNotEnoughException extends Exception{

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "请选择100只以上的自选股票";
    }
}
