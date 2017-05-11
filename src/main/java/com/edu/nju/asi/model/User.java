package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "user_stock",joinColumns = {@JoinColumn(name = "user_userName",referencedColumnName="userName")},
            inverseJoinColumns = {@JoinColumn(name = "privateStock_code",referencedColumnName="code"),
            @JoinColumn(name = "privateStock_date")})
    private Set<Stock> privateStock;

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

    public Set<Stock> getPrivateStock() {
        return privateStock;
    }

    public void setPrivateStock(Set<Stock> privateStock) {
        this.privateStock = privateStock;
    }

}
