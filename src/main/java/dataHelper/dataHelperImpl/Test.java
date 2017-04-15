package dataHelper.dataHelperImpl;

import dataHelper.DataSourceDataHelper;

import java.io.IOException;

/**
 * Created by cuihua on 2017/4/15.
 */
public class Test {

    public static void main(String[] args) {
        DataSourceDataHelper helper = new DataSourceDataHelperImpl();
        try {
            helper.upload("/Users/cuihua/Documents/大学学习/大二/软件工程与计算三/数据/股票历史数据ALL.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
