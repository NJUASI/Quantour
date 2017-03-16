package utilities.exceptions;

/**
 * Created by cuihua on 2017/3/16.
 * last updated by cuihua
 * update time 2017/3/15
 */
public class DateNotWithinException extends Exception {
    @Override
    public String getMessage() {
        return "所选日期区间不在数据源库内，请重选！";
    }
}
