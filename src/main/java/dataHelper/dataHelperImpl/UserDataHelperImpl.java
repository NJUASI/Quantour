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

    public UserDataHelperImpl(){
        propertiesload();
    }

    /**
     * 加载资源文件
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     */
    private void propertiesload() {

        try {
            properties.load(new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("userInfo.properties"))));
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

        File directory = new File("");//设定为当前文件夹
        try {
            properties.store(new FileWriter(directory.getCanonicalPath()+"userInfo.properties"),"");
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

}