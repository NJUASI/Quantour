package com.edu.nju.asi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/5/10.
 */
@Embeddable
public class StockID implements Serializable{

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private LocalDate date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockID stockID = (StockID) o;

        if (code != null ? !code.equals(stockID.code) : stockID.code != null) return false;
        return date != null ? date.equals(stockID.date) : stockID.date == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

}
