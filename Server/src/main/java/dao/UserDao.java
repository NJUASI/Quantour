package dao;

import po.UserPO;
import utilities.enums.ResultMessage;
import vo.UserVO;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface UserDao {

    /**
     * 添加用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return ResultMessage 是否成功添加用户
     */
     boolean add(UserPO userPO);

    /**
     * 获取指定用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param username 用户账号
     * @return UserPO 用户信息载体
     */
     UserPO get(String username);

    /**
     * 修改用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return boolean 是否成功修改用户
     */
     boolean modify(UserPO userPO);

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
}
