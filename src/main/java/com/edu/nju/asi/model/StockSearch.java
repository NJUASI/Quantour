package com.edu.nju.asi.model;

import com.edu.nju.asi.utilities.enums.Market;
import com.sun.xml.internal.bind.v2.TODO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/7.
 *
 * 股票查找的信息携带者
 */
@Entity
@Table(name = "stocksearch")
public class StockSearch implements Serializable {

    // 股票代码
    @Id
    @GenericGenerator(name="myGenerator",strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name = "code",length = 100)
    private String code;

    // 股票名称
    @Basic
    @Column(length = 100)
    private String name;

    // 汉字拼音首字母
    @Basic
    @Column(length = 100)
    private String firstLetters;

    @Basic
    private Market market;

    public StockSearch() {
        //TODO gcm
    }

    // TODO  股票搜索Model与逻辑之间有点问题，确认之后再改
    public StockSearch(String code, String name, String firstLetters) {
        this.code = code;
        this.name = name;
        this.firstLetters = firstLetters;
    }

    public StockSearch(String code, String name, String firstLetters,Market market) {
        this.code = code;
        this.name = name;
        this.firstLetters = firstLetters;
        this.market = market;
    }


    public StockSearch(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLetters() {
        return firstLetters;
    }

    public void setFirstLetters(String firstLetters) {
        this.firstLetters = firstLetters;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
