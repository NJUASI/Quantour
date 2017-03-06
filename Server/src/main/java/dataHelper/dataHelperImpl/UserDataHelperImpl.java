package dataHelper.dataHelperImpl;

import dataHelper.UserDataHelper;
import po.UserPO;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Byron Dong on 2017/3/5.
 *
 * 对用户信息进行操作
 */
public class UserDataHelperImpl implements UserDataHelper {

    /**
     * The Properties.
     */
    private Properties properties = new Properties();

    /**
     * Instantiates a new User data helper.
     */
    public UserDataHelperImpl(){
        propertiesload();
    }

    /**
     * @author Harvey
     * @time 2017/3/5 20:08
     * @method propertiesload
     * @param
     * @return void
     * @description 加载资源文件
     */
    private void propertiesload() {

        try {
            properties.load(new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream( "userInfo.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @author Harvey
     * @time 2017/3/5 20:09
     * @method add
     * @param userPO 用户信息
     * @return boolean 添加是否成功
     * @description 添加一个用户
     */
    @Override
    public boolean add(UserPO userPO) {

        properties.put(userPO.getUserName(),userPO.getPassword());
        return true;
    }

    /**
     * @author Harvey
     * @time 2017/3/5 20:10
     * @method get
     * @param username    用户姓名
     * @return po.UserPO    用户信息
     * @description     获取用户信息
     */
    @Override
    public UserPO get(String username) {

        UserPO userPO = new UserPO();
        userPO.setUserName(username);
        userPO.setPassword(properties.getProperty(username));
        return userPO;
    }

    /**
     * @author Harvey
     * @time 2017/3/5 20:11
     * @method modify
     * @param userPO  用户修改信息
     * @return boolean  修改是否成功
     * @description 修改一条用户信息
     */
    @Override
    public boolean modify(UserPO userPO) {
        properties.put(userPO.getUserName(),userPO.getPassword());
        return true;
    }

    /**
     * Gets all user names.
     *
     * @return the all user names 用户名称集合
     * @author Harvey
     * @updateTime 2017/3/5
     */
    @Override
    public Set<Object> getAllUserNames() {

        return properties.keySet();
    }

}