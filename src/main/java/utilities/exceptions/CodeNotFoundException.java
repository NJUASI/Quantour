package utilities.exceptions;

/**
 * Created by Harvey on 2017/3/18.
 */
public class CodeNotFoundException extends Exception {
    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return "所选股票代码不存在";
    }
}
