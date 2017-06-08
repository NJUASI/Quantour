package com.edu.nju.asi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/6/3.
 */
@Entity
@Table(name = "basicdata")
public class BasicData implements Serializable{

    //季度（使用1，2，3，4来进行标记）
    @Id
    @GenericGenerator(name="myGenerator",strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    private BasicDataID basicDataID;

    /**
     * 资产负债表
     * */
    //资产总计（万元）
    @Basic
    @Column(length = 100)
    private String totalAssets;

    //负债合计（万元）
    @Basic
    @Column(length = 100)
    private String totalLiabilities;

    //归属于母公司股东权益合计（万元）
    @Basic
    @Column(length = 100)
    private String totalEquityAttributableToShareholdersOfTheParentCompany;

    //所有者权益合计（万元）
    @Basic
    @Column(length = 100)
    private String totalOwnerEquity;

    /**
     * 利润表
     * */
    //营业总收入（万元）
    @Basic
    @Column(length = 100)
    private String totalBusinessIncome;

    //营业总成本（万元）
    @Basic
    @Column(length = 100)
    private String totalOperatingCost;

    //营业利润（万元）
    @Basic
    @Column(length = 100)
    private String operatingProfit;

    //利润总额（万元）
    @Basic
    @Column(length = 100)
    private String totalProfit;

    //净利润（万元）
    @Basic
    @Column(length = 100)
    private String netProfit;

    //归属于母公司所有者的净利润（万元）
    @Basic
    @Column(length = 100)
    private String netProfitAttributableToTheOwnerOfTheParentCompany;

    //基本每股收益
    @Basic
    private double basicIncomePerStock;

    /**
     * 现金流量表
     * */
    //经营活动净收益（万元）
    @Basic
    @Column(length = 100)
    private String netCashFlowsFromOperatingActivities;

    /**
     * 偿还能力
     * */
    //资产负债率
    @Basic
    private double assetLiabilityRatio;

    /**
     * 盈利能力
     * */
    //净资产负债率
    @Basic
    private double netDebtRatio;

    public BasicData() {
    }

    public BasicData(String totalAssets, String totalLiabilities,
                     String totalEquityAttributableToShareholdersOfTheParentCompany, String totalOwnerEquity, String totalBusinessIncome, String totalOperatingCost,
                     String operatingProfit, String totalProfit, String netProfit, String netProfitAttributableToTheOwnerOfTheParentCompany,
                     double basicIncomePerStock, String netCashFlowsFromOperatingActivities, double assetLiabilityRatio,
                     double netDebtRatio) {
        this.totalAssets = totalAssets;
        this.totalLiabilities = totalLiabilities;
        this.totalEquityAttributableToShareholdersOfTheParentCompany = totalEquityAttributableToShareholdersOfTheParentCompany;
        this.totalOwnerEquity = totalOwnerEquity;
        this.totalBusinessIncome = totalBusinessIncome;
        this.totalOperatingCost = totalOperatingCost;
        this.operatingProfit = operatingProfit;
        this.totalProfit = totalProfit;
        this.netProfit = netProfit;
        this.netProfitAttributableToTheOwnerOfTheParentCompany = netProfitAttributableToTheOwnerOfTheParentCompany;
        this.basicIncomePerStock = basicIncomePerStock;
        this.netCashFlowsFromOperatingActivities = netCashFlowsFromOperatingActivities;
        this.assetLiabilityRatio = assetLiabilityRatio;
        this.netDebtRatio = netDebtRatio;
    }

    public BasicDataID getBasicDataID() {
        return basicDataID;
    }

    public void setBasicDataID(BasicDataID basicDataID) {
        this.basicDataID = basicDataID;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }

    public String getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(String totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public String getTotalEquityAttributableToShareholdersOfTheParentCompany() {
        return totalEquityAttributableToShareholdersOfTheParentCompany;
    }

    public void setTotalEquityAttributableToShareholdersOfTheParentCompany(String totalEquityAttributableToShareholdersOfTheParentCompany) {
        this.totalEquityAttributableToShareholdersOfTheParentCompany = totalEquityAttributableToShareholdersOfTheParentCompany;
    }

    public String getTotalOwnerEquity() {
        return totalOwnerEquity;
    }

    public void setTotalOwnerEquity(String totalOwnerEquity) {
        this.totalOwnerEquity = totalOwnerEquity;
    }

    public String getTotalBusinessIncome() {
        return totalBusinessIncome;
    }

    public void setTotalBusinessIncome(String totalBusinessIncome) {
        this.totalBusinessIncome = totalBusinessIncome;
    }

    public String getTotalOperatingCost() {
        return totalOperatingCost;
    }

    public void setTotalOperatingCost(String totalOperatingCost) {
        this.totalOperatingCost = totalOperatingCost;
    }

    public String getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(String operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }

    public String getNetProfitAttributableToTheOwnerOfTheParentCompany() {
        return netProfitAttributableToTheOwnerOfTheParentCompany;
    }

    public void setNetProfitAttributableToTheOwnerOfTheParentCompany(String netProfitAttributableToTheOwnerOfTheParentCompany) {
        this.netProfitAttributableToTheOwnerOfTheParentCompany = netProfitAttributableToTheOwnerOfTheParentCompany;
    }

    public double getBasicIncomePerStock() {
        return basicIncomePerStock;
    }

    public void setBasicIncomePerStock(double basicIncomePerStock) {
        this.basicIncomePerStock = basicIncomePerStock;
    }

    public String getNetCashFlowsFromOperatingActivities() {
        return netCashFlowsFromOperatingActivities;
    }

    public void setNetCashFlowsFromOperatingActivities(String netCashFlowsFromOperatingActivities) {
        this.netCashFlowsFromOperatingActivities = netCashFlowsFromOperatingActivities;
    }

    public double getAssetLiabilityRatio() {
        return assetLiabilityRatio;
    }

    public void setAssetLiabilityRatio(double assetLiabilityRatio) {
        this.assetLiabilityRatio = assetLiabilityRatio;
    }

    public double getNetDebtRatio() {
        return netDebtRatio;
    }

    public void setNetDebtRatio(double netDebtRatio) {
        this.netDebtRatio = netDebtRatio;
    }
}
