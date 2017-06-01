package com.edu.nju.asi.model;

import com.edu.nju.asi.utilities.enums.Market;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/16.
 */
@Embeddable
public class SearchID  implements Serializable{

    //股票代号
    @Column(name = "code",length = 100)
    private String code;

    //股票名称
    @Column(length = 100)
    private String name;

    //市场
    @Column
    private Market market;

    public SearchID() {
    }

    public SearchID(String code, String name, Market market) {
        this.code = code;
        this.name = name;
        this.market = market;
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

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchID searchID = (SearchID) o;

        if (code != null ? !code.equals(searchID.code) : searchID.code != null) return false;
        if (name != null ? !name.equals(searchID.name) : searchID.name != null) return false;
        return market == searchID.market;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (market != null ? market.hashCode() : 0);
        return result;
    }
}
