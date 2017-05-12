package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Byron Dong on 2017/4/15.
 */
public class PrivateStockExistedException extends Exception{

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "该自选股已添加";
    }
}
