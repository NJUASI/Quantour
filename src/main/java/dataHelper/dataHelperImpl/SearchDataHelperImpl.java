package dataHelper.dataHelperImpl;

import dataHelper.SearchDataHelper;
import utilities.DataSourceStateKeeper;
import utilities.enums.DataSourceState;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Harvey on 2017/3/14.
 */
public class SearchDataHelperImpl implements SearchDataHelper {

    private final static String separator = System.getProperty("file.separator");
    private final static String nameCodePathPre1 = "stocks" + separator + "stockName-code" + separator;
    private final static String nameCodePathPre2 = "base_stocks" + separator + "stockName-code" + separator;

    /**
     * The Properties.
     */
    private Properties properties;


    /**
     * @return 所有股票六位代码
     */
    @Override
    public List<String> getAllStockCodes() {
        List<String> result = new LinkedList<>();

        propertiesload(nameCodePathPre1 + "stockName-code.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            result.add((String) entry.getValue());
        }
        return result;
    }

    @Override
    public List<String> getAllBaseStockCodes() {
        List<String> result = new LinkedList<>();

        propertiesload(nameCodePathPre2 + "stockName-code.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            result.add((String) entry.getValue());
        }
        return result;
    }

    /**
     * @return 所有股票名称的首字母缩写及其名称，名称作为键值
     * <p>
     * TODO gcm未实现
     */
    @Override
    public Map<String, String> getAllStocksFirstLetters() {
        Map<String, String> shortPinyinAndNames = new TreeMap<String, String>();

        propertiesload("stocks" + separator + "shortPinyin.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            shortPinyinAndNames.put((String) entry.getValue(), (String) entry.getKey());
        }
        propertiesload("base_stocks" + separator + "shortPinyin.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            shortPinyinAndNames.put((String) entry.getValue(), (String) entry.getKey());
        }
        return shortPinyinAndNames;
    }

    /**
     * @return 返回所有股票的代码及其名称，代码作为键值
     */
    @Override
    public Map<String, String> getAllStocksCode() {
        Map<String, String> codesAndNames = new TreeMap<String, String>();

        propertiesload(nameCodePathPre1 + "stockName-code.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            codesAndNames.put((String) entry.getValue(), (String) entry.getKey());
        }
        propertiesload(nameCodePathPre2 + "stockName-code.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            codesAndNames.put((String) entry.getValue(), (String) entry.getKey());
        }
        return codesAndNames;
    }

    /**
     * @return 返回所有股票的汉语名称及其代码，名称作为键值
     */
    @Override
    public Map<String, String> getAllStocksName() {
        Map<String, String> namesAndCode = new TreeMap<String, String>();

        propertiesload(nameCodePathPre1 + "stockName-code.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            namesAndCode.put((String) entry.getKey(), (String) entry.getValue());
        }
        propertiesload(nameCodePathPre2 + "stockName-code.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            namesAndCode.put((String) entry.getKey(), (String) entry.getValue());
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
            properties = new Properties();
            if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.ORIGINAL) {
                System.out.println(DataSourceState.ORIGINAL);
                System.out.println(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
                properties.load(new BufferedReader(new InputStreamReader(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream(path))));
            } else if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.USER) {
                System.out.println(DataSourceState.USER);
                properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(
                        System.getProperty("user.dir") + separator + ".attachments" + separator + path), "UTF-8")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
