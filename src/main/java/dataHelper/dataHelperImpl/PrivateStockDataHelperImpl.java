package dataHelper.dataHelperImpl;

import dataHelper.PrivateStockDataHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/6
 */
public class PrivateStockDataHelperImpl implements PrivateStockDataHelper {

    private final static String pathPre = "privateStock/";
    private final static String pathPost = ".properties";

    // 新建资源文件时需要请求文件目录，defaultPath引导至文件目录下
    private final static String defaultPath = "default";

    /**
     * The Properties.
     */
    private Properties properties = new Properties();

    public PrivateStockDataHelperImpl() {
    }

    /**
     * 加载资源文件
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     */
    private void propertiesLoad(String userName) {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(pathPre + userName + pathPost));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户对应的所有自选股的代码
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 自选股代码列表
     */
    @Override
    public List<String> getPrivateStockCode(String userName) {
        propertiesLoad(userName);
        Set<Object> keys = properties.keySet();
        List<String> codes = new ArrayList<String>();
        for (Object code : keys) {
            codes.add((String) code);
        }
        return codes;
    }

    /**
     * 添加用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return 添加是否成功
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) {
        propertiesLoad(userName);
        properties.put(stockCode, userName);
        return true;
    }

    /**
     * 删除用户自选股
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return 删除是否成功
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) {
        propertiesLoad(userName);
        properties.remove(stockCode);
        return true;
    }

    /**
     * 用户注册时，给用户新建一个对应用户名的资源文件.
     *
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/10
     * @param userName 用户名称
     * @return 是否创建成功
     *
     * 具体实现时，基于编译后的文件目录写入，故只存在于target文件目录下，src/main/resources/privateStock下无此文件
     * 且因为是直接写入target目录下，故测试也只能跑一次。之后再跑因为文件已存在会报错。
     * 但因为最后打包后应该没有问题。。
     * 所以这个接口有点问题。。考虑其他实现方式
     */
    @Override
    public boolean createPrivateDir(String userName) {
        final String defaultFilePath = Thread.currentThread().getContextClassLoader().getResource(pathPre + defaultPath + pathPost).getPath();

        // 为了在项目内部时可以看到文件
        final String thisFilePath = defaultFilePath.substring(0, defaultFilePath.length()-46) + "src/main/resources/privateStock/" + userName + pathPost;

        // 为了打包之后可以运行
        final String thisFilePath2 = defaultFilePath.substring(0, defaultFilePath.length()-18) + userName + pathPost;

        System.out.println(defaultFilePath);
        System.out.println(thisFilePath);
        System.out.println(thisFilePath2);


        File fileName = new File(thisFilePath);
        try {
            return fileName.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
