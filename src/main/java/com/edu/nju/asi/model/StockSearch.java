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
    private SearchID searchID;

    // 汉字拼音首字母
    @Basic
    @Column(length = 100)
    private String firstLetters;

    @Basic
    @Column(columnDefinition = "int default 0")
    private int clickAmount;

    //是否为基准股票
    @Basic
    @Column(columnDefinition = "bit default 0")
    private boolean isBase;

    //行业
    @Basic
    @Column(length = 100,nullable = true)
    private String industry;

    //地域
    @Basic
    @Column(length = 100,nullable = true)
    private String area;

    public StockSearch() {}

    public StockSearch(SearchID searchID) {
        this.searchID = searchID;
    }

    public StockSearch(SearchID searchID, String firstLetters) {
        this.searchID = searchID;
        this.firstLetters = firstLetters;
    }

    public StockSearch(SearchID searchID,String firstLetters, boolean isBase, String industry, String area) {
        this.searchID = searchID;
        this.firstLetters = firstLetters;
        this.isBase = isBase;
        this.industry = industry;
        this.area = area;
    }

    public SearchID getSearchID() {
        return searchID;
    }

    public void setSearchID(SearchID searchID) {
        this.searchID = searchID;
    }

    public String getFirstLetters() {
        return firstLetters;
    }

    public void setFirstLetters(String firstLetters) {
        this.firstLetters = firstLetters;
    }

    public int getClickAmount() {
        return clickAmount;
    }

    public void setClickAmount(int clickAmount) {
        this.clickAmount = clickAmount;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
