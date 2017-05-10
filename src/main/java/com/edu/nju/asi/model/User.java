package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Byron Dong on 2017/5/7.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    // 用户姓名
    @Id
    @GenericGenerator(name="myGenerator",strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name = "userName")
    private String userName;

    // 用户密码
    @Basic
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "user_stock",joinColumns = {@JoinColumn(name = "user_userName")},
            inverseJoinColumns = {@JoinColumn(name = "privateStock_code")})
    private List<Stock> privateStock;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Stock> getPrivateStock() {
        return privateStock;
    }

    public void setPrivateStock(List<Stock> privateStock) {
        this.privateStock = privateStock;
    }

}
