package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @Column(name = "userName",length = 100)
    private String userName;

    // 用户密码
    @Basic
    @Column(length = 100)
    private String password;

    // 用户邮箱
    @Basic
    @Column(length = 100,nullable = true)
    private String email;

    //回测股池
    @Basic
    @Column(columnDefinition = "TEXT",nullable = true)
    private String traceBackPool;

    @ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "user_strategy",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "userName")},
            inverseJoinColumns = {@JoinColumn(name = "strategy_id",referencedColumnName = "strategyID")})
    private List<Strategy> strategies = new ArrayList<>();

    public User() {
    }

    public User(String userName,String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(String userName,String password, String email, String traceBackPool) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.traceBackPool = traceBackPool;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTraceBackPool() {
        return traceBackPool;
    }

    public void setTraceBackPool(String traceBackPool) {
        this.traceBackPool = traceBackPool;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }
}
