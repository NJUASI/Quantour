package dataHelper.dataHelperImpl;

import dataHelper.PrivateStockDataHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/5.
 */
public class PrivateStockDataHelperImpl implements PrivateStockDataHelper{

    final String prePath = "privateStock/";


    /**
     * The Properties.
     */
    private Properties properties = new Properties();


    /**
     * @author Harvey
     * @time 2017/3/5 20:08
     * @method propertiesload
     * @param  username
     * @return void
     * @description 加载资源文件
     */
    private void propertiesload(String username) {

        try {
            properties.load(new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream( prePath+username+".properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrivateStockDataHelperImpl() {

    }

    public static void main(String[] args) {
        PrivateStockDataHelper p = new PrivateStockDataHelperImpl();
        p.createPrivateDir("123");

    }

    /**
     * 获取用户对应的所有自选股的代码
     *
     * @param userName the user name 用户名称
     * @return the private stock code 自选股代码列表
     * @author Harvey
     * @updateTime 2017/3/6
     */
    @Override
    public List<String> getPrivateStockCode(String userName) {
        propertiesload(userName);
        Set<Object> keys = properties.keySet();
        List<String> codes = new ArrayList<String>();
        for (Object code :keys) {
            codes.add((String)code);
        }
        return codes;
    }

    /**
     * 添加用户自选股
     *
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return boolean 添加是否成功
     * @author Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) {
        propertiesload(userName);
        properties.put(stockCode,userName);
        return true;
    }

    /**
     * 删除用户自选股
     *
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return boolean 删除是否成功
     * @author Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) {
        propertiesload(userName);
        properties.remove(stockCode);
        return true;
    }

    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件.
     *
     * @param userName the user name 用户名称
     * @return the boolean  是否创建成功
     * @author Harvey
     * @updateTime 2017/3/6
     */
    @Override
    public boolean createPrivateDir(String userName) {
        return false;
    }
}
