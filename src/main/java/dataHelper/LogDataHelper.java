package dataHelper;

import java.util.Set;

/**
 * Created by Harvey on 2017/3/6.
 */
public interface LogDataHelper {

    /**
     * 记录用户登录信息
     *
     * @author Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return boolean 是否登录
     */
    boolean login(String userName);

    /**
     * 删除用户登录信息
     *
     * @author Harvey
     * @updateTime 2017/3/6
     * @param userName 用户名称
     * @return boolean 是否成功登出
     */
    boolean logout(String userName);


    Set<Object> getLoginUserNames();
}