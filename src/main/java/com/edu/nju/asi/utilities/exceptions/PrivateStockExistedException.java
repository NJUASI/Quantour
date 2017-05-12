package com.edu.nju.asi.utilities.exceptions;

import com.edu.nju.asi.model.PrivateStock;

import java.util.List;

/**
 * Created by Byron Dong on 2017/4/15.
 */
public class PrivateStockExistedException extends Exception{

    private List<PrivateStock> privateStocks;

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

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public PrivateStockExistedException() {
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public PrivateStockExistedException(List<PrivateStock> privateStocks) {
        this.privateStocks = privateStocks;
    }

    /**
     * 获取重复添加的自选股，用于提示信息(size>=1)

     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return List<PrivateStock>
     */
    public List<PrivateStock> getPrivateStocks() {
        return privateStocks;
    }
}
