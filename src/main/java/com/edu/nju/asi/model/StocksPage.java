package com.edu.nju.asi.model;

import java.util.List;

/**
 * Created by cuihua on 2017/5/26.
 */
public class StocksPage {

    // 每页有多少条记录
    public int numOfEachPage;

    // 总的当前页数
    public int curPageNum;

    // 总的页数
    public int totalPageNum;

    // 总的记录数目
    public int totalRecordNum;

    // 股指的数据
    public List<BaseStock> baseStocks;

    // 一页的结果数据
    public List<Stock> stocks;

    public StocksPage(int numOfEachPage, int curPageNum, int totalPageNum, int totalRecordNum, List<BaseStock> baseStocks, List<Stock> stocks) {
        this.numOfEachPage = numOfEachPage;
        this.curPageNum = curPageNum;
        this.totalPageNum = totalPageNum;
        this.totalRecordNum = totalRecordNum;
        this.baseStocks = baseStocks;
        this.stocks = stocks;
    }
}
