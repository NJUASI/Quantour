package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cuihua on 2017/3/12.
 * Last updated by Byron Dong
 * Update time 2017/5/11
 */
@Entity
public class PrivateStock implements Serializable {

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    private PrivateStockID privateStockID;

    public PrivateStockID getPrivateStockID() {
        return privateStockID;
    }

    public void setPrivateStockID(PrivateStockID privateStockID) {
        this.privateStockID = privateStockID;
    }
}
