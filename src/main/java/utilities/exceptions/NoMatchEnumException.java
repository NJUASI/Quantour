package utilities.exceptions;

/**
 * Created by cuihua on 2017/4/16.
 */
public class NoMatchEnumException extends Exception {

    @Override
    public String getMessage() {
        return "没有与此值相对应的枚举类型";
    }
}
