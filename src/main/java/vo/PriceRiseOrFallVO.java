package vo;

import java.time.LocalDate;

/**
 * Created by Harvey on 2017/3/9.
 * 用来保存价格上涨或下跌
 */
public class PriceRiseOrFallVO {

    //涨跌的名称
    public String name;

    //股票个数
    public int num;

    //当天时间
    public LocalDate date;

    public PriceRiseOrFallVO(String name, int num,LocalDate date) {
        this.name = name;
        this.num = num;
    }
}
