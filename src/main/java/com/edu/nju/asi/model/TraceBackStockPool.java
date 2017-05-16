package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/14.
 */
@Entity
@Table(name = "tracebackstockpool")
public class TraceBackStockPool implements Serializable{

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    private OptionalStockID optionalStockID;

    @Basic
    @Column(length = 100)
    private String stockName;

    public TraceBackStockPool() {
    }

    public TraceBackStockPool(OptionalStockID optionalStockID, String stockName) {
        this.optionalStockID = optionalStockID;
        this.stockName = stockName;
    }

    public OptionalStockID getOptionalStockID() {
        return optionalStockID;
    }

    public void setOptionalStockID(OptionalStockID optionalStockID) {
        this.optionalStockID = optionalStockID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
