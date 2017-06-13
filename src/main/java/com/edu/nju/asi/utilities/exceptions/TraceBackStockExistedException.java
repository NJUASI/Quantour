package com.edu.nju.asi.utilities.exceptions;

import java.util.List;

/**
 * Created by cuihua on 2017/6/13.
 *
 * 私改自选股票里面的股票代码时，代码池中不存在的异常情况
 */
public class TraceBackStockExistedException extends Exception {

    private List<String> notExistCodes;

    public TraceBackStockExistedException(List<String> notExistCodes) {
        this.notExistCodes = notExistCodes;
    }

    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < notExistCodes.size()-1; i++) {
            sb.append(notExistCodes.get(i)).append(" ");
        }
        return sb.toString();
    }
}
