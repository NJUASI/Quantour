package utilities.exceptions;

/**
 * Created by Byron Dong on 2017/3/18.
 */
public class NoDataWithinException extends Exception {

    public NoDataWithinException(String message) {
        super(message);
    }

    public String getMessage() {
        return "股票" + super.getMessage() + "在所选期间内没有数据";
    }
}
