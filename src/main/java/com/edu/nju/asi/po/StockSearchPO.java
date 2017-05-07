package com.edu.nju.asi.po;

/**
 * Created by cuihua on 2017/4/18.
 *
 * 股票查找的信息携带者
 */
public class StockSearchPO {

    // 股票代码
    private String code;

    // 股票名称
    private String name;

    // 汉字首字母名称
    private String firstLetters;

    public StockSearchPO(String code, String name, String firstLetters) {
        this.code = code;
        this.name = name;
        this.firstLetters = firstLetters;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFirstLetters() {
        return firstLetters;
    }
}
