package com.edu.nju.asi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/11.
 */
@Embeddable
public class PrivateStockID implements Serializable{

    // 用户名
    @Column(name = "userName",length = 200)
    private String userName;

    // 用户的自选股列表
    @Column(name = "stcokCode",length = 200)
    private String stockCode;

    public PrivateStockID() {
    }

    public PrivateStockID(String userName, String stockCode) {
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

        PrivateStockID that = (PrivateStockID) o;

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
