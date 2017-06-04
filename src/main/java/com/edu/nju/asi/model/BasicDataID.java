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

    public BasicDataID() {
    }

    public BasicDataID(int quarter, int year) {
        this.quarter = quarter;
        this.year = year;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicDataID that = (BasicDataID) o;

        if (quarter != that.quarter) return false;
        return year == that.year;
    }

    @Override
    public int hashCode() {
        int result = quarter;
        result = 31 * result + year;
        return result;
    }
}
