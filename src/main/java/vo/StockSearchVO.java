package vo;

import po.StockSearchPO;
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

    // 汉字首字母名称
    public String firstLetters;

    public StockSearchVO(StockSearchPO po) {
        this.code = po.getCode();
        this.name = po.getName();
        this.firstLetters = po.getFirstLetters();
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
