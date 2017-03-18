package utilities.exceptions;

/**
 * Created by Harvey on 2017/3/18.
 */
public class NoSituationDataException extends Exception {
    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "当日无市场信息";
    }
}
