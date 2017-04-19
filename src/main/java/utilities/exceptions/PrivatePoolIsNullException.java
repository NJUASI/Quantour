package utilities.exceptions;

/**
 * Created by Byron Dong on 2017/4/19.
 */
public class PrivatePoolIsNullException extends Exception{

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "自选股票池中尚未添加股票";
    }
}
