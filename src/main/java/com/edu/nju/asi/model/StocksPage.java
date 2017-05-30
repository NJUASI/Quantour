package com.edu.nju.asi.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/5/26.
 */
public class StocksPage {

    // 当日日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate thisDate;

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

    public StocksPage(LocalDate thisDate, int numOfEachPage, int curPageNum, int totalPageNum, int totalRecordNum, List<BaseStock> baseStocks, List<Stock> stocks) {
        this.thisDate = thisDate;
        this.numOfEachPage = numOfEachPage;
        this.curPageNum = curPageNum;
        this.totalPageNum = totalPageNum;
        this.totalRecordNum = totalRecordNum;
        this.baseStocks = baseStocks;
        this.stocks = stocks;
    }
}
