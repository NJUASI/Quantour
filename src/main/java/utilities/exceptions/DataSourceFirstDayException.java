package utilities.exceptions;

/**
 * Created by cuihua on 2017/3/16.
 * last updated by cuihua
 * update time 2017/3/15
 */
public class DataSourceFirstDayException extends Exception {
    @Override
    public String getMessage() {
        return "所选日期包含数据源中起始日，不能计算";
    }
}
