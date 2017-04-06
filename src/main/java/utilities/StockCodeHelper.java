package utilities;

/**
 * Created by cuihua on 2017/3/30.
 *
 * 对股票代码进行格式化处理
 */
public class StockCodeHelper {

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/30
     * @params code 源数据源中股票代码
     * @return 形式化为6位的股票代码
     */
    public static String format(String code) {
        return String.format("%0" + 6 + "d", Integer.parseInt(code));
    }

    /**
     * @auther cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/30
     * @params code 6位标准股票代码
     * @return 最简版的数据源中股票代码
     */
    public static String simplify(String code) {
        return String.valueOf(Integer.parseInt(code));
    }
}
