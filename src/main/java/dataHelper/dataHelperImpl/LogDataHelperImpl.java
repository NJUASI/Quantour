package dataHelper.dataHelperImpl;

import dataHelper.LogDataHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Harvey on 2017/3/6.
 * Last updated by Harvey
 * Update time 2017/3/6
 */
public class LogDataHelperImpl implements LogDataHelper{

    private Properties properties;

    public LogDataHelperImpl() {
        this.properties = new Properties();
        propertiesLoad();
    }

    private void propertiesLoad(){
        try {
            properties.load(new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("login_user.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 记录用户登录信息
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否登录
     */
    @Override
    public boolean login(String userName) {
        properties.put(userName,"");
        return true;
    }

    /**
     * 删除用户登录信息
     *
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return 是否成功登出
     */
    @Override
    public boolean logout(String userName) {
        properties.remove(userName);
        return true;
    }

    /**
     * @author Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/6
     * @return 已登录用户名称集合
     */
    @Override
    public Set<Object> getLoginUserNames() {
        return properties.keySet();
    }
}
