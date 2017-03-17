package vo;

import utilities.enums.Market;

/**
 * Created by Harvey on 2017/3/14.
 *
 * 股票查找的信息携带者
 */
public class StockSearchVO {

    // 股票代码
    public String code;

    // 股票名称
    public String name;

    // 市场名称
    public Market market;

    // 汉字首字母名称
    public String firstLetters;

    public StockSearchVO(String code, String name, Market market, String firstLetters) {
        this.code = code;
        this.name = name;
        this.market = market;
        this.firstLetters = firstLetters;
    }

    public StockSearchVO() {

    }

    public StockSearchVO(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return code+"  "+name;
    }
}
