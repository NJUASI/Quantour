package com.edu.nju.asi.model;

import java.util.List;

/**
 * Created by cuihua on 2017/3/12.
 * Last updated by cuihua
 * Update time 2017/3/12
 */
public class PrivateStock {

    // 用户名
    private String userName;

    // 用户的自选股列表
    private List<String> privateStocks;

    public PrivateStock(String userName, List<String> privateStocks) {
        this.userName = userName;
        this.privateStocks = privateStocks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getPrivateStocks() {
        return privateStocks;
    }

    public void setPrivateStocks(List<String> privateStocks) {
        this.privateStocks = privateStocks;
    }
}
