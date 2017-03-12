package dataHelper.dataHelperImpl;

import dataHelper.PrivateStockDataHelper;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/12
 *
 * TODO 发现对txt进行修改依然存在之前的问题，打包之后是相对路径，获取到的是一个InputStream，强行改路径会矛盾
 */
public class PrivateStockDataHelperImpl implements PrivateStockDataHelper {

    private final static String path = "privateStocks.txt";

    private BufferedReader br;
    private BufferedWriter bw;

    // br, bw未初始化，在add / delete时再分别初始化
    public PrivateStockDataHelperImpl() {
    }

    /**
     * 获取用户对应的所有自选股的代码
     *
     * @author Harvey
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/12
     * @param userName 用户名称
     * @return 自选股代码列表
     */
    @Override
    public List<Integer> getPrivateStockCode(String userName) {
        List<Integer> result = new LinkedList<Integer>();

        try {
            br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path)));
            String thisLine = null;
            while ((thisLine = br.readLine()) != null) {
                String[] parts = thisLine.split(" ");
                if (parts[0] == userName) {
                    result.add(Integer.parseInt(parts[1]));
                }
            }
            br.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
    public boolean addPrivateStock(String userName, int stockCode) {
        try {
            Thread.currentThread().getContextClassLoader().getResource(path);
            bw = new BufferedWriter(new FileWriter(path, true));
            bw.write(userName + "\t" + stockCode);
            bw.newLine();
            bw.flush();
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
    public boolean deletePrivateStock(String userName, int stockCode) {
        try {
            br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path)));
            StringBuffer nowData = new StringBuffer();

            String thisLine = null;
            while ((thisLine = br.readLine()) != null) {
                if (!thisLine.equals(userName + "\t" + stockCode)){
                    nowData.append(thisLine + "\n");
                }
            }
            br.close();

            bw = new BufferedWriter(new FileWriter(path, false));
            bw.write(nowData.toString());
            bw.newLine();
            bw.flush();
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
