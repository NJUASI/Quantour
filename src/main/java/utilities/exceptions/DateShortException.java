package utilities.exceptions;

/**
 * Created by Administrator on 2017/3/5.
 */
public class DateShortException extends Exception {
    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "所选日期期间与均线类型不匹配";
    }
}
