package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by Byron Dong on 2017/4/15.
 */
public class PrivateStockNotFoundException extends Exception{

    private String msg = "您尚未添加自选股";

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public PrivateStockNotFoundException() {}

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     */
    public PrivateStockNotFoundException(String msg) {
        this.msg = msg;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return msg;
    }

}
