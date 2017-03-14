package dataHelper.dataHelperImpl;

import dataHelper.SearchDataHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Harvey on 2017/3/14.
 */
public class SearchDataHelperImpl implements SearchDataHelper {

    /**
     * The Properties.
     */
    private Properties properties = new Properties();

    /**
     * Gets all stocks code. 获取所有股票的代码
     *
     * @return the all stocks code 返回所有股票的代码及其名称，代码作为键值
     */
    @Override
    public Map<String, String> getAllStocksFirstLetters() {
        propertiesload("shortPinyin");
        Map<String,String> shortPinyinAndNames = new TreeMap<String,String>();
        for(Map.Entry<Object,Object> entry:properties.entrySet()){
            shortPinyinAndNames.put((String)entry.getValue(),(String)entry.getKey());
        }
        return shortPinyinAndNames;
    }

    /**
     * Gets all stocks first letters.获取所有股票的首字母
     *
     * @return the all stocks first letters 返回所有股票的首字母及其名称，名称作为键值
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        propertiesload("stockName-code");
        Map<String,String> codesAndNames = new TreeMap<String,String>();
        for(Map.Entry<Object,Object> entry:properties.entrySet()){
            codesAndNames.put((String)entry.getValue(),(String)entry.getKey());
        }
        return codesAndNames;
    }

    /**
     * 加载资源文件
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     */
    private void propertiesload(String path) {
        try {
            properties.load(new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path+".properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
