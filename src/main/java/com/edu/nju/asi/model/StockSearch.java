package com.edu.nju.asi.model;

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

    // 汉字首字母名称
    @Basic
    @Column(length = 100)
    private String firstLetters;

    public StockSearch() {
    }

    public StockSearch(String name, String firstLetters) {
        this.name = name;
        this.firstLetters = firstLetters;
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
}
