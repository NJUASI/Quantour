package com.edu.nju.asi.utilities.exceptions;

/**
 * Created by cuihua on 2017/3/16.
 * last updated by cuihua
 * update time 2017/3/15
 */
public class DateNotWithinException extends Exception {
    @Override
    public String getMessage() {
        return "所选日期区间较数据源太广，暂无这么早的数据！";
    }
}
