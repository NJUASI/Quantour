package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/1.
 */
@Entity
@Table(name = "strategy")
public class Strategy implements Serializable{

    //策略ID
    @Id
    @GenericGenerator(name="myGenerator",strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(length = 100)
    private String strategyID;

    //策略创建日期
    @Basic
    private LocalDate date;

    //策略创建者
    @Basic
    @Column(length = 100)
    private String creater;

    //策略是否私有
    @Basic
    @Column(columnDefinition = "bit default 0")
    private boolean isPrivate;

    //策略内容
    @Basic
    @Column(columnDefinition = "TEXT",nullable = true)
    private String content;

    //策略说明
    @Basic
    @Column(columnDefinition = "TEXT",nullable = true)
    private String description;

    @Basic
    @Column(columnDefinition = "TEXT",nullable = true)
    private String traceBackInfo;

    @ManyToMany(mappedBy = "strategies")
    private List<User> users = new ArrayList<>();

    public Strategy() {
    }

    public Strategy(LocalDate date, String creater, boolean isPrivate, String content, String description, String traceBackInfo) {
        this.date = date;
        this.creater = creater;
        this.isPrivate = isPrivate;
        this.content = content;
        this.description = description;
        this.traceBackInfo = traceBackInfo;
    }

    public String getStrategyID() {
        return strategyID;
    }

    public void setStrategyID(String strategyID) {
        this.strategyID = strategyID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraceBackInfo() {
        return traceBackInfo;
    }

    public void setTraceBackInfo(String traceBackInfo) {
        this.traceBackInfo = traceBackInfo;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
