package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
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

    //用户头像
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "headPotrait",columnDefinition = "BLOB",nullable = true)
    private Blob headPortrait;

    @ManyToMany(cascade={CascadeType.ALL})
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

    public User(String userName, String password, Blob headPortrait) {
        this.userName = userName;
        this.password = password;
        this.headPortrait = headPortrait;
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

    public Blob getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(Blob headPortrait) {
        this.headPortrait = headPortrait;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }
}
