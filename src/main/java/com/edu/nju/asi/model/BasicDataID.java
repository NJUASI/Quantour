package com.edu.nju.asi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/6/4.
 */
@Embeddable
public class BasicDataID implements Serializable {

    //股票代号
    @Column(length = 100)
    private String code;

    @Column
    private LocalDate date;

    public BasicDataID() {
    }

    public BasicDataID(String code, LocalDate date) {
        this.code = code;
        this.date = date;
    }

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

        BasicDataID that = (BasicDataID) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}