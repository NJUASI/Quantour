package com.edu.nju.asi.model;

import com.edu.nju.asi.utilities.enums.Market;
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

    public StockSearch() {

    }

    public StockSearch(SearchID searchID) {
        this.searchID = searchID;
    }

    public StockSearch(SearchID searchID,String firstLetters) {
        this.searchID = searchID;
        this.firstLetters = firstLetters;
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
}
