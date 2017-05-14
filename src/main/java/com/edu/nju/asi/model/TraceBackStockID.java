package com.edu.nju.asi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/14.
 */
@Embeddable
public class TraceBackStockID implements Serializable{

    //用户名
    @Column(name = "userName",length = 100)
    public String userName;

    //股票代号
    @Column(name = "stockCode",length = 100)
    public String stockCode;

    public TraceBackStockID() {
    }

    public TraceBackStockID(String userName, String stockCode) {
        this.userName = userName;
        this.stockCode = stockCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TraceBackStockID that = (TraceBackStockID) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return stockCode != null ? stockCode.equals(that.stockCode) : that.stockCode == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (stockCode != null ? stockCode.hashCode() : 0);
        return result;
    }
}
