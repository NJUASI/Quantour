package dataHelper.dataHelperImpl;

import dataHelper.UserDataHelper;
import po.UserPO;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 对用户信息进行操作
 */
public class UserDataHelperImpl implements UserDataHelper {

    /**
     * The Properties.
     */
    private Properties properties = new Properties();

    private final String separator = System.getProperty("file.separator");
    private final String parent = System.getProperty("user.dir") + separator + ".attachments" + separator + "user";

    public UserDataHelperImpl(){

        wasExists(); //user文件夹和userInfo.properties未创建，则创建
        propertiesload();
    }

    /**
     * 加载资源文件
     *
     * @author Harvey
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     */
    private void propertiesload() {

        try {
            properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(parent+separator+"userInfo.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加一个用户
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userPO 用户信息
     * @return 添加是否成功
     */
    @Override
    public boolean add(UserPO userPO) {

        properties.put(userPO.getUserName(),userPO.getPassword());

        File directory = new File(parent);//设定为当前文件夹
        try {
            properties.store(new FileWriter(directory.getCanonicalPath()+separator+"userInfo.properties"),"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 获取用户信息
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param username 用户姓名
     * @return 用户信息载体
     */
    @Override
    public UserPO get(String username) {

        UserPO userPO = new UserPO();
        userPO.setUserName(username);
        userPO.setPassword(properties.getProperty(username));
        return userPO;
    }

    /**
     * 修改一条用户信息
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userPO 用户修改信息
     * @return 修改是否成功
     */
    @Override
    public boolean modify(UserPO userPO) {

        properties.put(userPO.getUserName(),userPO.getPassword());
        File directory = new File(parent);//设定为当前文件夹
        try {
            properties.store(new FileWriter(directory.getCanonicalPath()+separator+"userInfo.properties"),"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 获取所有的注册过用户名称
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @return 用户名称集合
     */
    @Override
    public Set<Object> getAllUserNames() {

        return properties.keySet();
    }

    /**
     * 判断该用户文件及文件夹是否存在，不存在则生成
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/15
     */
    private boolean wasExists() {
        File file = new File(parent);
        try {
            if (!file.exists()&& !file.isDirectory()) {
                file.mkdir();
                File tempFile = new File(parent+separator+"userInfo.properties");
                tempFile.createNewFile();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}