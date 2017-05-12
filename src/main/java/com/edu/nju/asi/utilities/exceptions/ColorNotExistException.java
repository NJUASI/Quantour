package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Byron Dong on 2017/3/11.
 */
public class ColorNotExistException extends Exception {

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "不存在对应移动平均线的颜色";
    }
}
