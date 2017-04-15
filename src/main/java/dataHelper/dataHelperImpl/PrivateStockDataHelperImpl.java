package dataHelper.dataHelperImpl;

import dataHelper.PrivateStockDataHelper;
import utilities.exceptions.PrivateStockExistedException;
import utilities.exceptions.PrivateStockNotExistException;
import utilities.exceptions.PrivateStockNotFoundException;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/3/5.
 * Last updated by Byron Dong
 * Update time 2017/4/15
 * <p>
 */
public class PrivateStockDataHelperImpl implements PrivateStockDataHelper {

    private final String separator = System.getProperty("file.separator");
    private final String parent = System.getProperty("user.dir") + separator + ".attachments" + separator + "user";
    private final String post = ".txt";

    private BufferedReader br;
    private BufferedWriter bw;

    // br, bw未初始化，在add / delete时再分别初始化
    public PrivateStockDataHelperImpl() {
    }

    /**
     * 获取用户对应的所有自选股的代码
     *
     * @param userName 用户名称
     * @return 自选股代码列表
     * @author Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     */
    @Override
    public List<String> getPrivateStockCode(String userName) throws PrivateStockNotFoundException {
        List<String> result = new LinkedList<String>();
        String desPath = this.parent + separator + userName + post;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(desPath)));
            String thisLine = null;
            while ((thisLine = br.readLine()) != null) {
                result.add(thisLine);
            }
            br.close();
            return result;
        } catch (IOException e) {
            throw new PrivateStockNotFoundException();
        }
    }

    /**
     * 添加用户自选股
     *
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return 添加是否成功
     * @author Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) throws PrivateStockExistedException, PrivateStockNotFoundException {


        if (this.wasExists(userName)) {
            List<String> stockCodes = this.getPrivateStockCode(userName);
            if (stockCodes.contains(stockCode)) {
                throw new PrivateStockExistedException();
            }
        }


        try {
            String desPath = this.parent + this.separator + userName + post;
            bw = new BufferedWriter(new FileWriter(desPath, true));
            bw.write(stockCode);
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
     * @param userName  用户名称
     * @param stockCode 股票代码
     * @return 删除是否成功
     * @author Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) throws PrivateStockNotExistException, PrivateStockNotFoundException {

        List<String> stockCodes = null;

            stockCodes = this.getPrivateStockCode(userName);
            if (stockCodes.contains(stockCode)) {
                throw new PrivateStockNotExistException();
            }

        try {
            String desPath = this.parent + this.separator + userName + post;
            stockCodes.remove(stockCode);

            bw = new BufferedWriter(new FileWriter(desPath, false));
            for (String code : stockCodes) {
                bw.write(code);
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断该用户文件是否存在，不存在则生成
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     */
    private boolean wasExists(String userName) {
        File file = new File(parent + separator + userName + post);
        try {
            if (!file.exists()) {
                file.createNewFile();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
