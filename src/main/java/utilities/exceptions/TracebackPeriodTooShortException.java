package utilities.exceptions;

/**
 * Created by cuihua on 2017/4/2.
 */
public class TracebackPeriodTooShortException extends Exception {

    @Override
    public String getMessage() {
        return "所选的回测区间太小／形成期太长，导致回测区间哪不行进行策略回测";
    }
}
