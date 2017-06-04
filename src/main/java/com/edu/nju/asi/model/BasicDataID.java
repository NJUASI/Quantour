package com.edu.nju.asi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/6/4.
 */
@Embeddable
public class BasicDataID implements Serializable{

    //季度（使用1，2，3，4来进行标记）
    @Column(length = 1)
    private int quarter;

    //年份
    @Column(length = 4)
    private int year;

    //股票代号
    @Column(length = 100)
    private String code;

    public BasicDataID() {
    }

    public BasicDataID(int quarter, int year, String code) {
        this.quarter = quarter;
        this.year = year;
        this.code = code;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicDataID that = (BasicDataID) o;

        if (quarter != that.quarter) return false;
        if (year != that.year) return false;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = quarter;
        result = 31 * result + year;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
