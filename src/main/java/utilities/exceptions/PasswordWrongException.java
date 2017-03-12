package utilities.exceptions;

/**
 * Created by Harvey on 2017/3/11.
 */
public class PasswordWrongException extends Exception {
    /**
     * Returns the detail message string of this throwable.
     *
     * @return 密码错误
     */
    @Override
    public String getMessage() {
        return "密码错误，请检查密码";
    }
}
