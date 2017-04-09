package dataHelper.dataHelperImpl;

import dataHelper.SearchDataHelper;
import utilities.StockCodeHelper;

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
     * @return 所有股票六位代码
     */
    @Override
    public List<String> getAllStockCodes() {
        propertiesload("stockName-code/stockName-code");
        List<String> result = new LinkedList<>();
        for(Map.Entry<Object,Object> entry:properties.entrySet()){
            result.add(StockCodeHelper.format((String)entry.getValue()));
        }
        return result;
    }

    /**
     * @return 所有股票名称的首字母缩写及其名称，名称作为键值
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
     * @return 返回所有股票的代码及其名称，代码作为键值
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        propertiesload("stockName-code/stockName-code");
        Map<String,String> codesAndNames = new TreeMap<String,String>();
        for(Map.Entry<Object,Object> entry:properties.entrySet()){
            codesAndNames.put((String)entry.getValue(),(String)entry.getKey());
        }
        return codesAndNames;
    }

    /**
     * @return 返回所有股票的汉语名称及其代码，名称作为键值
     */
    @Override
    public Map<String, String> getAllStocksName() {
        propertiesload("stockName-code/stockName-code");
        Map<String,String> namesAndCode = new TreeMap<String,String>();
        for(Map.Entry<Object,Object> entry:properties.entrySet()){
            namesAndCode.put((String)entry.getKey(),(String)entry.getValue());
        }
        return namesAndCode;
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
