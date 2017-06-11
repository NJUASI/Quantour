package com.edu.nju.asi.task.fileHelper;

import java.io.*;
import java.util.Properties;

/**
 * Created by Byron Dong on 2017/6/11.
 */
public class FileHelper {

    //文件的根目录，默认该root已加上分隔符
    private String root;

    //普通股票
    private String stocksPath;

    //基准股票
    private String baseStocksPath;

    //财务报告表
    private String financialStatementPath;
    private String balanceSheetPath;
    private String profitabilityPath;
    private String profitStatementPath;
    private String repaymentAbilityPath;
    private String statementOfCashFlowPath;

    //复权数据
    private String adjPath;
    private String front;
    private String after;

    //stockSearch
    private String stockSearchFile;
    private String stockDataFile;

    //初始化所有目录信息
    public FileHelper(String root) {

        if(!root.endsWith(File.separator)){
            root = root+File.separator;
        }
        this.root = root;

        //普通股票
        stocksPath = root+"stocks"+ File.separator;
        //基准股票
        baseStocksPath = root+"baseStocks"+File.separator;
        //财务报告表
        financialStatementPath = root+"cwzb"+File.separator;
        balanceSheetPath = financialStatementPath+"zcfzb"+File.separator;
        profitabilityPath = financialStatementPath+"ylnl"+File.separator;
        profitStatementPath = financialStatementPath+"lrb"+File.separator;
        repaymentAbilityPath = financialStatementPath+"chnl"+File.separator;
        statementOfCashFlowPath = financialStatementPath+"xjllb"+File.separator;
        //复权数据
        adjPath = root+"adjData"+File.separator;
        front = adjPath+"front"+File.separator;
        after = adjPath+"after"+File.separator;
        //stockSearch
        stockSearchFile = root+"stockSearch.txt";
        stockDataFile = root+"stockData.txt";
    }

    /**
     * 创建指定目录和文件信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/11
     */
    public void create(){
        //普通股票
        createDir(stocksPath);
        //基准股票
        createDir(baseStocksPath);
        //财务报告表
        createDir(financialStatementPath);
        createDir(balanceSheetPath);
        createDir(profitabilityPath);
        createDir(profitStatementPath);
        createDir(statementOfCashFlowPath);
        createDir(repaymentAbilityPath);
        //复权数据
        createDir(adjPath);
        createDir(front);
        createDir(after);
        //stockSearch
        createFile(stockSearchFile);
        createFile(stockDataFile);
    }

    /**
     * 删除指定目录和文件信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/11
     */
    public void delete(){
        deleteDir(stocksPath);
        deleteDir(baseStocksPath);

        deleteDir(balanceSheetPath);
        deleteDir(profitStatementPath);
        deleteDir(profitabilityPath);
        deleteDir(repaymentAbilityPath);
        deleteDir(statementOfCashFlowPath);
        deleteDir(financialStatementPath);

        deleteDir(front);
        deleteDir(after);
        deleteDir(adjPath);

        deleteFile(stockSearchFile);
        deleteFile(stockDataFile);

        deleteDir(root);
    }

    /**
     * 创建指定目录
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @param path 目录路径
     * @updateTime 2017/6/11
     */
    private void createDir(String path){
        File dir = new File(path);
        dir.mkdirs();
    }

    /**
     * 创建指定文件信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @param path 文件路径
     * @updateTime 2017/6/11
     */
    private void createFile(String path){
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定目录
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @param path 目录路径
     * @updateTime 2017/6/11
     */
    private void deleteDir(String path){
        File dir = new File(path);
        File files[] = dir.listFiles();

        //如果是空目录直接删除
        if(files==null||files.length==0){
            dir.delete();
            return;
        }

        for(File file:files){
           file.delete();
        }
        dir.delete();
    }

    /**
     * 删除指定文件
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @param path 文件路径
     * @updateTime 2017/6/11
     */
    private void deleteFile(String path){
        File file = new File(path);
        file.delete();
    }
}
