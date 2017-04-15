package utilities.exceptions;

/**
 * Created by cuihua on 2017/4/15.
 */
public class UnhandleBlockTypeException extends Exception {

    @Override
    public String getMessage() {
        return "未处理的板块类型";
    }
}
