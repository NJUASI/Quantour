package com.edu.nju.asi.utilities.exceptions;

import com.edu.nju.asi.model.TraceBackStockPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/14.
 */
public class TraceBackStockExistedException extends Exception {

    private List<String> exceptionCode;

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "该回测自选股已添加";
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public TraceBackStockExistedException() {
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public TraceBackStockExistedException(List<TraceBackStockPool> traceBackStockPools) {
        exceptionCode = new ArrayList<>();
        for(TraceBackStockPool traceBackStockPool: traceBackStockPools){
            exceptionCode.add(traceBackStockPool.getTraceBackStockID().getStockCode());
        }
    }

    /**
     * 获取重复添加的自选股，用于提示信息(size>=1)

     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/5/9
     * @return List<exceptionCode>
     */
    public List<String> getExceptionCode() {
        return exceptionCode;
    }
}

