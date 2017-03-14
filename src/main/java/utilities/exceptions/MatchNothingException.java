package utilities.exceptions;

/**
 * Created by Harvey on 2017/3/14.
 */
public class MatchNothingException extends Exception{

    /**
     * 用户输入代码或者股票首字母，查找符合条件的股票,当所有匹配条件都不符合时,抛出该异常
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public String getMessage() {
        return "没有任何股票匹配";
    }
}
