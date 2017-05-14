package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/14.
 */
@Entity
public class TraceBackStockPool implements Serializable{

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    public TraceBackStockID traceBackStockID;

    public TraceBackStockID getTraceBackStockID() {
        return traceBackStockID;
    }

    public void setTraceBackStockID(TraceBackStockID traceBackStockID) {
        this.traceBackStockID = traceBackStockID;
    }
}
